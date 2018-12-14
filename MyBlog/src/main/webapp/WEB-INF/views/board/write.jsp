<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/common.jsp" %>
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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<style>
		.contents
		{
			diyplay:none;
		}
		</style>
	</head>

	<body>

		<!-- Content -->
			<div id="content">
				<div class="inner">
				<article class="box post post-excerpt">
					<form id="requestform" name="requestform" method="post" enctype="multipart/form-data">	
					<table class="default_1">
						<tr>
							<th width="180" > 작성자 </th>
							<td><input type="text" name="username" id="username" value="관리자"></td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>
								<select name="category" id="category">
									<option value="">선택해주세요.</option>
									<c:forEach var="categoryList" items="${categoryList}">
										<option value="${categoryList.seq}">${categoryList.codename}</option>
									</c:forEach>
									<!-- 
									<option value="1" selected>JAVA</option>
									<option value="2">DB</option>
									<option value="3">LINUX</option>
									<option value="4">ANDROID</option>
									<option value="5">Mac OS X</option>
									<option value="6">여행</option>
									 -->
								</select>
							</td>
						</tr>
						<tr>
							<th> 제 목 </th>
							<td><input type="text" name="title" id="title" value=""></td>
						</tr>
						<tr>
							<th> 내 용 </th> 
							<td>
								<div id="contentsView" style="width:100%;height:400px;overflow:no;-webkit-overflow-scrolling:touch;padding:0 0 0 0;">
									<textarea id="contents" name="contents" style="width:100%;height:100%;border:0px;background-color:#fdfeff;display:none" wrap="hard"></textarea>
									<!-- <iframe id="editor" src="blogWriteIframe.do" width="100%" height="100%" frameborder="0" scrolling="auto" noresize  marginwidth="0" marginheight="0"></iframe> -->
									<iframe id="editor" width="100%" height="100%" frameborder="0" scrolling="auto" noresize  marginwidth="0" marginheight="0" style="background-color:#fdfeff"></iframe>
								</div>
							</td>
						</tr>
						<tr>
							<th> 이미지 파일</th>
							<td>
								<input type="file" name="fileName" id="fileName" title="파일 찾기">
							</td>
						</tr>
						<tr>
							<th> 공지 여부</th>
							<td>
								<input type="radio" value="0"> Y 
								&nbsp;&nbsp;&nbsp;
								<input type="radio" value="1" checked> N
							</td>
						</tr>
					</table>
					</form>
				</article>
					
				<div class="footer_btn">
					<a class="button next" id="btn_save">SAVE</a>
					<a class="button next" id="btn_list">LIST</a>
				</div>

				</div>
			</div>

		<!-- Sidebar -->
			<%-- <jsp:include page="/viewPage.do?pageName=sidebar" flush="false" ></jsp:include> --%>
			<%@include file="./sidebar.jsp" %>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/write.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>