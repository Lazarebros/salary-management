%dw 1.0
%output application/json
---
payload groupBy $.year map ((paychecks, index) -> {
	year: paychecks[0].year,
	numnerOfMonths: sizeOf paychecks,
	grossAmount: sum paychecks.*gross_amount,
	//grossRemain: ((sum paychecks.*number_of_hours) * paychecks[0].hourly_rate) - (sum paychecks.*gross_amount),
	reimbursement: sum paychecks.*reimbursement,
	netPay: sum paychecks.*net_pay,
	realRet: 0.00,
	netRemain: 0.00,
	paychecks: paychecks map ((paycheck, index) -> {
		id: paycheck.paycheck_id,
		startDate: paycheck.start_date,
		endDate: paycheck.end_date,
		numberOfHours: paycheck.number_of_Hours,
		//hourlyRate: paycheck.hourly_rate,
		grossAmount: paycheck.gross_amount,
		netPay: paycheck.net_pay,
		reimbursement: paycheck.reimbursement
	})
})