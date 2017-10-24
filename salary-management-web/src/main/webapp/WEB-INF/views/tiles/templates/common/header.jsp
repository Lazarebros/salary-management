<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-findcond navbar-fixed-top">
    <div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<p class="navbar-brand">Salary Management</p>
		</div>
		<c:if test="${loggedinuser != null}">
			<div class="collapse navbar-collapse" id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active-home"><a href="home">Home</a></li>
					 <sec:authorize access="hasRole('NOT_IMPLEMENTED')">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Settings <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">Company</a></li>
								<li><a href="#">Rate</a></li>
							</ul>
						</li>
					</sec:authorize>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">User: ${loggedinuser} <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							 <sec:authorize access="hasRole('NOT_IMPLEMENTED')">
								<li><a href="profile">Profile</a></li>
							</sec:authorize>
							<li><a href="<c:url value="/logout" />">Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</c:if>
	</div>
</nav>