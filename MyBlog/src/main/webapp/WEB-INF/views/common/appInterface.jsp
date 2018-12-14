<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/common.jsp" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>${TITLE_TEXT}</title>
		<meta charset="utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1" /> -->
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>
		<!-- Content -->
			<fmt:setLocale value="en_US"/>
			<div id="content">
				
				<div>
					<a class="button" id="btn_camera">Call Camera</a>
					<a class="button" id="btn_map">Call Daum Map</a>
					<a class="button" id="btn_qr">Call QR</a>
				</div>
				
				<div style='height:100;'>
					<input id="input_upload_sample" type="file"></input>
				</div>
				
			</div>
			
			<div id="sidebar"></div>
			
		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>
			<script src="assets/js/list.js"></script>
			<script src="assets/js/webView.js"></script>
	</body>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//카메라 호출
			$('#btn_camera').click(function() {
				webView.call_native_camera();
			});
			
			//다음 지도 호출
			$('#btn_map').click(function() {
				webView.call_native_map();
			});
			
			//QR 호출
			$('#btn_qr').click(function() {
				webView.call_qr();
			});
		});
		
	</script>
</html>