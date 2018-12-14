<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/common.jsp" %>
<jsp:useBean id="dateUtil" class="com.myblog.util.DateUtil"/>
<c:set var='pageBlock' value="${pageBlock}" />
<c:set var='startPage' value="${startPage}" />
<c:set var='endPage' value="${endPage}" />
<c:set var='totalPage' value="${totalPage}" />

<c:set var='pageNum' value="${pageNum}" />
<c:set var='category' value="${category}" />

<%--   
totalCount : ${totalCount} <br/>
pageBlock : ${pageBlock} <br/>
totalPage : ${totalPage} <br/>
pageNum : ${pageNum} <br/>
startPage : ${startPage} <br/>
endPage : ${endPage} <br/>
 --%>
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
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<script type="text/javascript">
			var category = '${category}';
			var pageNum = '${pageNum}';
		</script>
		<style>
		/* Full-width input fields */
		input[type=text], input[type=password] {
		    width: 100%;
		    padding: 12px 20px;
		    margin: 8px 0;
		    display: inline-block;
		    border: 1px solid #ccc;
		    box-sizing: border-box;
		}
		
		/* Set a style for all buttons */
		/* button {
		    background-color: #4CAF50;
		    color: white;
		    padding: 14px 20px;
		    margin: 8px 0;
		    border: none;
		    cursor: pointer;
		    width: 100%;
		} */
		
		.loginbtn {
		    width: auto;
		    padding: 10px 20px;
		    background-color: #4CAF50;
		    font-size: 1.0em;
		}
		
		/* Extra styles for the cancel button */
		.cancelbtn {
		    width: auto;
		    padding: 10px 20px;
		    background-color: #f44336;
		   	font-size: 1.0em;
		}
		
		/* Center the image and position the close button */
		.imgcontainer {
		    text-align: left;
		    margin: 24px 10px 12px 15px;
		    position: relative;
		}
		
		img.avatar {
		    width: 40%;
		    border-radius: 50%;
		}
		
		.container {
		    padding: 16px;
		}
		
		span.psw {
		    float: right;
		    /* padding-top: 16px; */
		}
		
		/* The Modal (background) */
		.modal {
		    display: none; /* Hidden by default */
		    position: fixed; /* Stay in place */
		    z-index: 1; /* Sit on top */
		    left: 0;
		    top: 0;
		    width: 100%; /* Full width */
		    height: 100%; /* Full height */
		    overflow: auto; /* Enable scroll if needed */
		    background-color: rgb(0,0,0); /* Fallback color */
		    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
		    padding-top: 60px;
		}
		
		/* Modal Content/Box */
		.modal-content {
		    background-color: #fefefe;
		    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
		    border: 1px solid #888;
		    width: 50%; /* Could be more or less, depending on screen size */
		}
		
		.loginTitle {
		    /* position: absolute; */
		    
		    top: 0;
		    font-size: 30px;
		    font-weight: bold;
		    color: #4CAF50;
		}
		
		/* The Close Button (x) */
		.close {
		    position: absolute;
		    right: 25px;
		    top: 0;
		    color: #f44336;
		    font-size: 35px;
		    font-weight: bold;
		}
		
		.close:hover,
		.close:focus {
		    color: red;
		    cursor: pointer;
		}
		
		/* Add Zoom Animation */
		.animate {
		    -webkit-animation: animatezoom 0.3s;
		    animation: animatezoom 0.3s
		}
		
		@-webkit-keyframes animatezoom {
		    from {-webkit-transform: scale(0)}
		    to {-webkit-transform: scale(1)}
		}
		    
		@keyframes animatezoom {
		    from {transform: scale(0)}
		    to {transform: scale(1)}
		}
		
		/* Change styles for span and cancel button on extra small screens */
		@media screen and (max-width: 300px) {
		    span.psw {
		       display: block;
		       float: none;
		    }
		    .cancelbtn {
		       width: 100%;
		    }
		}
		
		</style>
	</head>
	<body>
		<!-- Content -->
			<fmt:setLocale value="en_US"/>
			<div id="content">
				<div id="topLogin" style="text-align: right">
					<c:choose>
						<c:when test="${!empty USERID}">
							<c:if test="${GRADE eq 9}">
								<a class="button" id="btn_write">WRITE</a>
							</c:if>
	 						<a class="button" id="btn_logout">LOGOUT</a>
						</c:when>
						<c:when test="${empty USERID}">
	 						<a class="button" id="btn_login">LOGIN</a>
							<a class="button" id="btn_register">REGISTER</a>
						</c:when>
					</c:choose>
				</div>
				<div class="inner">
					<!-- Post -->
						<form id="requestform" name="requestform" method="post">
						<c:forEach var="boardList" items="${boardList}">
						<c:set var="MM">
							<fmt:formatDate value="${boardList.regdate}" pattern="MM"/>
						</c:set>
						<article class="box post post-excerpt">
							<header style="word-break:break-all;">
								<h2><a href="javascript:viewPage(${boardList.seq});">${boardList.title}</a></h2>
								<!-- <p>A free, fully responsive HTML5 site template by HTML5 UP</p> -->
							</header>
							<div class="info">
								<span class="date">
									<span class="month">
									<% 
										String MM = (String)pageContext.getAttribute("MM"); 
										out.print(dateUtil.getConvertMonth(MM));
									%>
									<span>
									</span></span>
									<span class="day"><fmt:formatDate value="${boardList.regdate}" type="date" pattern="dd"/></span>
									<span class="year">, <fmt:formatDate value="${boardList.regdate}" pattern="yyyy"/></span>
								</span>
								<ul class="stats">
									<li><a href="#" class="icon fa-comment">16</a></li>
									<li><a href="#" class="icon fa-heart">32</a></li>
									
									<!-- 	
									<li><a href="#" class="icon fa-twitter">64</a></li>
									<li><a href="#" class="icon fa-facebook">128</a></li> 
									--> 
								</ul>
							</div>
							<%-- 
							<c:if test="${boardList.filename ne null && boardList.filename ne ''}">
 								<!-- <a href="#" class="image featured"><img src="images/pic02.jpg" alt="" /></a> -->
								<a href="#" class="image featured"><img src="/upload_file/${boardList.filename}" alt="" /></a>
							</c:if> 
							--%>
							<p>
								<div style='min-height:50px;word-break:break-all;'>
								<c:choose>
									<c:when test="${fn:length(boardList.contents)>500}">
										<%-- <c:out  value="${fn:substring(fn:escapeXml(boardList.contents),0,500)}....."/> --%>
										<c:out  value="${fn:substring(boardList.contents,0,500)}....."/>
										<%-- <c:out value='${boardList.contents.replacAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")}' /> --%>
										<%-- <c:out value='${boardList.contents.replacAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")}' /> --%>
									</c:when>
									
									<c:when test="${fn:length(boardList.contents)<500}">
										<c:out  value="${boardList.contents}"/>
									</c:when>
								</c:choose>
								</div>
							</p>
						</article>
						</c:forEach>
						</form>
					<!-- Pagination -->
						<div class="pagination" style="text-align:center">
							<c:if test="${pageNum gt 1}">
								<a href="javascript:goPage(${pageNum}-1);" class="button previous">Prev Page</a>
							</c:if>
							<div class="pages">
								<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="status">
									<c:choose>
										<c:when test="${pageNum eq status.index}">
											<a href="javascript:goPage(${status.index});" class="active">${status.index}</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:goPage(${status.index});">${status.index}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
							<c:if test="${pageNum lt totalPage}">
								<a href="javascript:goPage(${pageNum}+1);" class="button next">Next Page</a>
							</c:if>
							<%-- <c:if test="${MASTER_ID ne null}"> --%>
								<!-- <a class="button" id="btn_write_bottom">WRITE</a> -->
							<%-- </c:if> --%>
						</div>
				</div>
			</div>
			
		<!-- Sidebar -->
			<%@include file="./sidebar.jsp" %>
			
			<!-- 로그인 팝업 -->	
			<div id="id01" class="modal">
				<form class="modal-content animate" id="loginform" method="post">
					<div class="imgcontainer">
						<span class="loginTitle">Login</span>
						<span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
						<!-- <img src="images/user.jpeg" alt="Avatar" class="avatar" width="100" height="100"> -->
					</div>
			
					<div class="container">
						<label><b>User ID</b></label>
						<input type="text" placeholder="Enter User ID" id="userid" name="userid" required>
						<label><b>Password</b></label>
						<input type="password" placeholder="Enter Password" id="pwd" name="pwd" required>
					</div>
					<div class="container" style="text-align:center">
						<button type="submit" id="login_submit" class="loginbtn">Login</button>
						<button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
					</div>
			
					<div class="container" style="background-color:#f1f1f1;">
						<input type="checkbox" checked="checked"> Remember me
						<span class="psw">Forgot <a href="#">password?</a></span>
					</div>
				</form>
			</div>
			
		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>
			<script src="assets/js/list.js"></script>
			
			
	</body>
</html>