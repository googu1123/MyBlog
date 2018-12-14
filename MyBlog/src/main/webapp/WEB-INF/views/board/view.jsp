<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/common.jsp" %>
<c:set var='category' value="${category}" />
<c:set var='pageNum' value="${pageNum}" />
<script type="text/javascript">
	var category = "${category}";
	var pageNum = "${pageNum}";
</script>
<!DOCTYPE HTML>
<!--
	Striped by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>${TITLE_TEXT}</title>
		<meta charset="utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1" /> -->
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>

	<body>

		<!-- Content -->
			<div id="content">
				<div class="inner">
					<article class="box post post-excerpt">
						<table class="default_1">
							<thead>
							<tr>
								<th class="th_left" width="60%">
								<c:if test="${!empty boardView.codename}">[${boardView.codename}]&nbsp;</c:if>
								${boardView.title}
								</th>
								<th class="th_right" width="40%"><fmt:formatDate value="${boardView.regdate}" pattern="yyyy.MM.dd kk:mm:ss (E)"/></th>
							</tr>
							</thead>
							<thead>
							<tr>
								<th class="th_right2" colspan="2">
								조회수: ${boardView.readcnt}&nbsp;
								추천수: 10&nbsp;
								댓글: 0 &nbsp;
								</th>
							</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="2" style='word-break:break-all;overflow-y:scroll;-webkit-overflow-scrolling : touch;'>
									<%-- ${fn:trim(boardView.contents)} --%>
									<c:out  value="${boardView.contents}" escapeXml="false"/>
									</td>
								</tr>
							</tbody>
							<!-- 
							<tfoot>
								<th>
									<td colspan="2"></td>
								</th>
							</tfoot>
							 -->
						</table>
					</article>
					
					<div class="footer_btn">
						<a class="button next" id="btn_list">LIST</a>
						<c:if test="${GRADE eq 9}">
							<a class="button next" id="btn_edit">EDIT</a>
							<a class="button next" id="btn_delete" onClick="boardDelete('${boardView.seq}')">DELETE</a>
						</c:if>
					</div>
				</div>
			</div>

		<!-- Sidebar -->
			<%@include file="./sidebar.jsp" %>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/view.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>