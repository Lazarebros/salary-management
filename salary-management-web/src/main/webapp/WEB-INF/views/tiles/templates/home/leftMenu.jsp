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
						<%-- <c:forEach var="comapany" items="#{homeView.companies}"> --%>
						<tr>
							<td>
								<%-- <h:commandLink action="#{homeController.filterCompany(comapany.id)}" value="#{comapany.code}"/> --%>
								<label><input type="checkbox" id="company" name="checkbox">Company</label>
							</td>
						</tr>
						<%-- </c:forEach> --%>
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
						<tr>
							<td><a href="http://www.jquery2dotnet.com">Orders</a><span class="label label-success">$ 320</span></td>
						</tr>
						<tr>
							<td><a href="http://www.jquery2dotnet.com">Invoices</a></td>
						</tr>
						<tr>
							<td><a href="http://www.jquery2dotnet.com">Shipments</a></td>
						</tr>
						<tr>
							<td><a href="http://www.jquery2dotnet.com">Tex</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><span class="glyphicon glyphicon-user">
					</span>Charts</a>
				</h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse">
				<div class="panel-body">
					<table class="table">
						<tr>
							<td><a href="http://www.jquery2dotnet.com">Change Password</a></td>
						</tr>
						<tr>
							<td>
								<a href="http://www.jquery2dotnet.com">Notifications</a><span class="label label-info">5</span>
							</td>
						</tr>
						<tr>
							<td><a href="http://www.jquery2dotnet.com">Import/Export</a></td>
						</tr>
						<tr>
							<td><span class="glyphicon glyphicon-trash text-danger"></span><a href="#" class="text-danger">Delete Account</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>