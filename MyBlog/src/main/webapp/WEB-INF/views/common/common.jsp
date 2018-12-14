<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var='TITLE_TEXT' value="Untitled" />
<c:set var='USERID' value="${sessionScope.USERID}" />
<c:set var='USERNAME' value="${sessionScope.USERNAME}" />
<c:set var='GRADE' value="${sessionScope.GRADE}" />
<c:set var='isMobile' value="${sessionScope.isMobile}" />
