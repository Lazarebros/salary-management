<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="paychecks-content">
    <h1>${paychecksView.year}</h1>
    <h1>Paychecks</h1>
    <br/>
    <div class="tbl-header">
    	<table>
	        <thead>
		    	<tr>
		            <th>Company</th>
		            <th>Year</th>
		            <th>Month</th>
		            <th>Gross Amount</th>
		            <th>Gross Remain</th>
		            <th>Reimbursement</th>
		            <th>Net Pay</th>
		            <th>Real Net</th>
		            <th>Net Remain</th>
		      	</tr>
	        </thead>
      	</table>
    </div>
    <div class="tbl-content">
      	<table class="paychecks-table">
	     	<tbody>
	          	<c:forEach var="paycheck" items="${paychecksView.paychecks}">
		          	<tr class="pay-state-${paycheck.monthPayState}">
			            <td>${paycheck.companyCode}</td>
			            <td>${paycheck.year}</td>
					  	<td>${paycheck.monthName}</td>
					  	<td>${paycheck.grossAmount}</td>
					  	<td>Gross Remain</td>
					  	<td>${paycheck.reimbursement}</td>
					  	<td>${paycheck.netPay}</td>
					  	<td>${paycheck.realNetPay}</td>
					  	<td>${paycheck.netPayRemain}</td>
		          	</tr>
		      	</c:forEach>
	      	</tbody>
      	</table>
    </div>
</div>
