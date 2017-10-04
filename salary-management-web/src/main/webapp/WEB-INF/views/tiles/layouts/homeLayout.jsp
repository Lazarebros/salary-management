<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title><tiles:getAsString name="title" /></title>
	    
	    <spring:url value="/static/css/main.css" var="mainCss" />
	    <spring:url value="/static/css/home.css" var="homeCss" />
	    
		<spring:url value="/static/js/jquery.min.js" var="jqueryJs" />
		<spring:url value="/static/bootstrap-3.3.7/js/bootstrap.js" var="bootstrapJs" />
	    
	    <link href="${mainCss}" rel="stylesheet"></link>
	    <link href="${homeCss}" rel="stylesheet"></link>
	    
	    <script src="${jqueryJs}" type="text/javascript"></script>
	    <script src="${bootstrapJs}" type="text/javascript"></script>
	</head>
	<body>
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
     	
       	<div class="container-home">
	        <div class="left-menu-container">
	            <tiles:insertAttribute name="menu" />
	        </div>
	        <div class="site-content">
	            <tiles:insertAttribute name="body" />
	        </div>
		</div>
		
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
	</body>
</html>