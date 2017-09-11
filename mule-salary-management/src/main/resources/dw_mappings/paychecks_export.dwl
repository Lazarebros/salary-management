%dw 1.0
%output application/json
%function getUniqID(data) data.company_code ++ data.year ++ data.month
---
payload groupBy $.company_code map ((companies, index) -> {
	companyCode: companies[0].company_code,
	salary: companies groupBy getUniqID($) map ((paychecks, index) -> {
		year: paychecks[0].year,
		month: paychecks[0].month,
		gross_amount: sum paychecks.*gross_amount,
		gross_remain: ((sum paychecks.*number_of_hours) * paychecks[0].hourly_rate) - (sum paychecks.*gross_amount),
		reimbursement: sum paychecks.*reimbursement,
		net_pay: sum paychecks.*net_pay,
		real_net: 0.00,
		net_remain: 0.00,
		paychecks_count: sizeOf paychecks,
		paychecks: paychecks map ((paycheck, index) -> {
			paycheck_id: paycheck.paycheck_id,
			start_date: paycheck.start_date,
			end_date: paycheck.end_date,
			number_of_Hours: paycheck.number_of_Hours,
			hourly_rate: paycheck.hourly_rate,
			gross_amount: paycheck.gross_amount,
			net_pay: paycheck.net_pay,
			reimbursement: paycheck.reimbursement
		})
	})
})