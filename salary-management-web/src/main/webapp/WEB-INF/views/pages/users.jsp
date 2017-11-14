<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="users-table">
	<h1>List of Users</h1>
	<br/>
	<div class="tbl-header">
		<table>
			<thead>
		  		<tr>
			        <th>Username</th>
		        	<th>First name</th>
			        <th>Last name</th>
			        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
			        	<th></th>
			        </sec:authorize>
			        <sec:authorize access="hasRole('ADMIN')">
			        	<th></th>
			        </sec:authorize>
		    	</tr>
		     </thead>
	  	</table>
	</div>
	<div class="tbl-content">
	  	<table class="tbl-body">
		  	<tbody>
		       	<c:if test="${!usersView.users.isEmpty()}">
			       	<c:forEach var="user" items="${usersView.users}">
			        	<tr>
							<td>${user.username}</td>
					        <td>${user.firstName}</td>
							<td>${user.lastName}</td>
						    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								<td>
									<a href="<c:url value='/edit-user-${user.username}' />" class="btn btn-success custom-width">edit</a>
								</td>
					        </sec:authorize>
					        <sec:authorize access="hasRole('ADMIN')">
								<td>
									<a href="<c:url value='/delete-user-${user.username}' />" class="btn btn-danger custom-width">delete</a>
								</td>
	        				</sec:authorize>
			        	</tr>
			    	</c:forEach>
			    </c:if>
			    <c:if test="${usersView.users.isEmpty()}">
			    	<div class="white-text text-center div-center">
					    <p>No data to display</p>
					</div>
			    </c:if>
		   	</tbody>
	  	</table>
	</div>
	<br/>
	<sec:authorize access="hasRole('ADMIN')">
	 	<div class="add-user-btn-div">
	 		<a href="<c:url value='/newuser' />" class="btn btn-danger btn-custom">Add New User</a>
	 	</div>
 	</sec:authorize>
</div>
