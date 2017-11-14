<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="paychecks-table">
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
	  	<table class="tbl-body">
		  	<tbody>
		       	<c:if test="${!paychecksView.monthlyPaychecks.isEmpty()}">
			       	<c:forEach var="paycheck" items="${paychecksView.monthlyPaychecks}">
			        	<tr>
					        <td>${paycheck.companyCode}</td>
					        <td>${paycheck.year}</td>
						  	<td>${paycheck.monthName}</td>
						  	<td>${paycheck.grossAmount}</td>
						  	<td class="pay-state-${paycheck.monthPayState}">${paycheck.grossRemain}</td>
						  	<td>${paycheck.reimbursement}</td>
						  	<td>${paycheck.netPay}</td>
						  	<td>${paycheck.realNetPay}</td>
						  	<td class="pay-state-${paycheck.monthPayState}">${paycheck.netPayRemain}</td>
			        	</tr>
			    	</c:forEach>
			    </c:if>
			    <c:if test="${paychecksView.monthlyPaychecks.isEmpty()}">
			    	<div class="white-text text-center div-center">
					    <p>No data to display</p>
					</div>
			    </c:if>
		   	</tbody>
	  	</table>
	</div>
</div>

<div class="charts">
	<div id="grossAmountChartDiv" class="grossAmountChart" ></div>
	<div id="realNetPayChartDiv" class="realNetPayChart" ></div>
</div>
<br/>
<div class="charts">
	<div id="grossAmountCumulativeChartDiv" class="grossAmountChart" ></div>
	<div id="realNetPayCumulativeChartDiv" class="realNetPayChart" ></div>
</div>

<script type="text/javascript">
    var grossAmountChartDiv = echarts.init(document.getElementById('grossAmountChartDiv'));
    var grossAmountChart = ${paychecksView.chartBean.grossAmountChart};
    grossAmountChartDiv.setOption(grossAmountChart);

    var realNetPayChartDiv = echarts.init(document.getElementById('realNetPayChartDiv'));
    var realNetPayChart = ${paychecksView.chartBean.realNetPayChart};
    realNetPayChartDiv.setOption(realNetPayChart);

    var grossAmountCumulativeChartDiv = echarts.init(document.getElementById('grossAmountCumulativeChartDiv'));
    var grossAmountCumulativeChart = ${paychecksView.chartBean.grossAmountCumulativeChart};
    grossAmountCumulativeChartDiv.setOption(grossAmountCumulativeChart);
    
    var realNetPayCumulativeChartDiv = echarts.init(document.getElementById('realNetPayCumulativeChartDiv'));
    var realNetPayCumulativeChart = ${paychecksView.chartBean.realNetPayCumulativeChart};
    realNetPayCumulativeChartDiv.setOption(realNetPayCumulativeChart);
</script>
