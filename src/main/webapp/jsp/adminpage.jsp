<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="label.title" /></title>
	<meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
	<title>Dictionary</title>
	<link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/themes/base/jquery.ui.base.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/themes/redmond/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="resources/css/lib/ui.jqgrid.css"/>
</head>
<body>
	<div><a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
	</a>
	</div>
	<div>
	<table id="grid"></table>
	<div id="pager"></div>
	</div>
	<div>
	<table id="grid2"></table>
	<div id="pager2"></div>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="resources/js/lib/grid.locale-en.js"></script>
	<script src="resources/js/lib/jquery.jqGrid.src.js"></script>
	
	<script src="resources/js/adminpage.js"></script>
</body>

</html>