<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="project-name">
	<span>Salary Management</span>
</div>
<c:if test="${loggedinuser != null}">
	<div class="header-navbar">
		<ul class="nav navbar-nav ">
			<li class="active-home"><a href="<c:url value="/home" />">Home</a></li>
			 <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Settings <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="<c:url value="/users" />">Users</a></li>
						<sec:authorize access="hasRole('NOT_IMPLEMENTED')">
							<li><a href="#">Company</a></li>
							<li><a href="#">Rate</a></li>
						</sec:authorize>
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
		