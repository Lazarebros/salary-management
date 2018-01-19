<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-9">
	<div class="well">
		<div class="row">
			<c:forEach var="paycheck" items="${homeView.yearlyPaychecks}">
				<div class="col-sm-3 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h2 style="text-align: center">${paycheck.year}</h2>
						</div>
						<div class="modal-footer" style="text-align: left">
							<div class="progress">
							    <div class="progress-bar progress-bar-info" style="width:${paycheck.yearProgress}%">
							    	<span class="white-text">${paycheck.yearProgress}% Complete</span>
							    </div>
  							</div>
							<div class="row-fluid">
								<div class="span4">
									<b>Gross: </b><small>${paycheck.grossAmount}</small>
								</div>
								<div class="span4">
									<b>Net: </b><small>${paycheck.netPay}</small>
								</div>
								<div class="span4">
									<b>Reimb.: </b><small>${paycheck.reimbursement}</small>
								</div>
								<div class="span4">
									<b>Real Net: </b><small>${paycheck.realNetPay}</small>
								</div>
								<div class="span4">
									<b>Real Net Mean: </b><small>${paycheck.realNetPayMean}</small>
								</div>
								<div>
									<a href="<c:url value='/paychecks-${paycheck.year}' />" class="btn btn-danger btn-custom">Details</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
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
    var grossAmountChart = ${homeView.chartBean.grossAmountChart};
    grossAmountChartDiv.setOption(grossAmountChart);

    var realNetPayChartDiv = echarts.init(document.getElementById('realNetPayChartDiv'));
    var realNetPayChart = ${homeView.chartBean.realNetPayChart};
    realNetPayChartDiv.setOption(realNetPayChart);

    var grossAmountCumulativeChartDiv = echarts.init(document.getElementById('grossAmountCumulativeChartDiv'));
    var grossAmountCumulativeChart = ${homeView.chartBean.grossAmountCumulativeChart};
    grossAmountCumulativeChartDiv.setOption(grossAmountCumulativeChart);
    
    var realNetPayCumulativeChartDiv = echarts.init(document.getElementById('realNetPayCumulativeChartDiv'));
    var realNetPayCumulativeChart = ${homeView.chartBean.realNetPayCumulativeChart};
    realNetPayCumulativeChartDiv.setOption(realNetPayCumulativeChart);
</script>
