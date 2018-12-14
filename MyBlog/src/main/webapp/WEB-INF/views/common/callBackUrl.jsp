<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var='message' value="${message}" />
<c:set var='url' value="${url}" />
<script src="assets/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		alert('${message}');
		document.location.href='${url}';		
	});
</script>
