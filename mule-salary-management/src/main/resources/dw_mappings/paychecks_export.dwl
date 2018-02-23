%dw 1.0
%output application/json
%function getRealNet(paychecks) (sum paychecks.*net_pay) - (sum paychecks.*reimbursement)
%function getExpectedGross(paychecks) (sum (paychecks map {
		expected_gross: $.expected_number_of_hours * $.hourly_rate
	}).*expected_gross
)
%function getExpectedNet(paychecks) (sum (paychecks map {
	expected_net: $.expected_number_of_hours * $.hourly_rate * $.net_percentage_of_gross
	}).*expected_net
)

%type currency = :string {format: ".00"}
---
(payload groupBy $.year map ((paychecks, index) -> {
	year: paychecks[0].year,
	numnerOfPaychecks: sizeOf paychecks,
	expectedGrossAmount: getExpectedGross(paychecks),
	grossAmount: sum paychecks.*gross_amount,
	grossRemain: (sum paychecks.*gross_amount) - getExpectedGross(paychecks),
	reimbursement: sum paychecks.*reimbursement,
	netPay: sum paychecks.*net_pay,
	realNet: getRealNet(paychecks),
	netRemain: (getRealNet(paychecks) - getExpectedNet(paychecks)) as :currency as :number,
	paychecks: paychecks map ((paycheck, index) -> {
		id: paycheck.paycheck_id,
		companyCode: paycheck.company_code,
		startDate: paycheck.start_date,
		endDate: paycheck.end_date,
		numberOfHours: paycheck.number_of_Hours,
		hourlyRate: paycheck.hourly_rate,
		expectedGrossAmount: paycheck.expected_number_of_hours * paycheck.hourly_rate,
		grossAmount: paycheck.gross_amount,
		expected_net: (paycheck.expected_number_of_hours * paycheck.hourly_rate * paycheck.net_percentage_of_gross) as :currency as :number,
		netPay: paycheck.net_pay,
		reimbursement: paycheck.reimbursement
	})
}) orderBy $.year)[-1 to 0]