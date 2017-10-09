<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/login" />
<form action="${loginUrl}" method="post" class="form-horizontal">

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
								<div class="progress progress-striped active" style="background: #ddd">
									<div class="bar bar-warning" style="width: 60%;"></div>
								</div>
								<div class="row-fluid">
									<div class="span4">
										<b>Gross</b><br />
										<small>${paycheck.grossAmount}</small>
									</div>
									<div class="span4">
										<b>Net</b><br />
										<small>${paycheck.netPay}</small>
									</div>
									<div class="span4">
										<b>Real Net</b><br />
										<small>${paycheck.realNetPay}</small>
									</div>
									<div class="span4">
										<b>Reimbursement</b><br />
										<small>${paycheck.reimbursement}</small>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="charts">
			<div id="grossAmountChart" style="width:500px;height:300px; float: left;"></div>
			<div id="realNetPayChart" style="width:500px;height:300px;float:right;"></div>
		</div>
	</div>
	<br/>
</form>
<script type="text/javascript">
    var grossAmountChart = echarts.init(document.getElementById('grossAmountChart'));
    grossAmountChart.setOption(${homeView.chartBean.grossAmountChart});

    var realNetPayChart = echarts.init(document.getElementById('realNetPayChart'));
    realNetPayChart.setOption(${homeView.chartBean.realNetPayChart});
</script>
