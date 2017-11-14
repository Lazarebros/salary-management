<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="page-login">
	<div class="page-body">
	 	<div class="login-body">
			<c:url var="loginUrl" value="/login" />
			<form:form action="${loginUrl}" method="POST" class="form-horizontal">
		    	<div class="login-head">
		    		<p class="white-text">Welcome</p>
		    	</div>
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Invalid username and password.</p>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">
						<p>You have been logged out successfully.</p>
					</div>
				</c:if>
				<div class="input-group input-sm">
					<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
					<input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
				</div>
				<div class="input-group input-sm">
					<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
					<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
				</div>
				<div class="input-group input-sm">
                 	<div class="checkbox white-text">
                   		<label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>  
                 	</div>
              	</div>
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
					
				<div class="form-actions">
					<input type="submit" class="btn btn-block btn-primary btn-default btn-custom" value="Log in">
				</div>
				<div class="login-bottom">
		    	</div>
			</form:form>
		</div>
	</div>
</div>
