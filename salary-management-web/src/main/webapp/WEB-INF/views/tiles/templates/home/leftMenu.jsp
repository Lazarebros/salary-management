<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-sm-3 col-md-3">
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><span class="glyphicon glyphicon-home"></span>Companies</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table">
						<c:forEach var="comapany" items="${homeView.companies}">
							<tr>
								<td>
									<%-- <h:commandLink action="#{homeController.filterCompany(comapany.id)}" value="#{comapany.code}"/> --%>
									<label class="side-menu-item"><input type="checkbox" id="company" name="checkbox"><span class="side-menu-item">${comapany.code}</span></label>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><span class="glyphicon glyphicon-calendar">
					</span>Years</a>
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table">
						<c:forEach var="year" items="${homeView.years}">
							<tr>
								<td>
									<label class="side-menu-item"><input type="checkbox" id="company" name="checkbox"><span class="side-menu-item">${year}</span></label>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>