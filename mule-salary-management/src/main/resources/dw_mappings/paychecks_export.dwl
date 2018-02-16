%dw 1.0
%output application/json
%function getRealNet(paychecks) (sum paychecks.*net_pay) - (sum paychecks.*reimbursement)
---
payload groupBy $.year map ((paychecks, index) -> {
	year: paychecks[0].year,
	numnerOfMonths: sizeOf paychecks,
	expectedGrossAmount: sum paychecks.*expected_gross,
	grossAmount: sum paychecks.*gross_amount,
	grossRemain: (sum paychecks.*gross_amount) - (sum paychecks.*expected_gross),
	reimbursement: sum paychecks.*reimbursement,
	netPay: sum paychecks.*net_pay,
	realNet: getRealNet(paychecks),
	netRemain: getRealNet(paychecks) - (sum paychecks.*expected_net_pay),
	paychecks: paychecks map ((paycheck, index) -> {
		id: paycheck.paycheck_id,
		startDate: paycheck.start_date,
		endDate: paycheck.end_date,
		numberOfHours: paycheck.number_of_Hours,
		hourlyRate: paycheck.hourly_rate,
		expectedGrossAmount: paycheck.expected_gross,
		grossAmount: paycheck.gross_amount,
		expected_net: paycheck.expected_net_pay,
		netPay: paycheck.net_pay,
		reimbursement: paycheck.reimbursement
	})
})