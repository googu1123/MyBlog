<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="assets/js/calendar.js"></script>
<style>
/* 
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
* {font-family: 'Nanum Gothic', serif;}

#kCalendar {width: 250px; height: 250px; border: 1px solid black;}

#kCalendar #header {height: 50px; line-height: 50px; text-align: center; font-size: 18px; font-weight: bold}
#kCalendar .button {color: #000; text-decoration: none;}

#kCalendar table {width: 250px; height: 200px;}
#kCalendar caption {display: none;}
*/
#kCalendar .sun {text-align: center; color: deeppink;}
#kCalendar .mon {text-align: center;}
#kCalendar .tue {text-align: center;}
#kCalendar .wed {text-align: center;}
#kCalendar .thu {text-align: center;}
#kCalendar .fri {text-align: center;}
#kCalendar .sat {text-align: center; color: deepskyblue;}

</style>
<script text="text/javascript">
	function categoryView(category) 
	{
		location.href = "boardList.do?category="+category
	}
</script>
<div id="sidebar">
	<!-- Logo -->
		<c:choose>
			<c:when test="${empty USERID}">
				<h1 id="logo"><a href="#">${TITLE_TEXT}</a></h1>
			</c:when>
			<c:when test="${!empty USERID }">
				<h1 id="logo"><a href="#">${USERNAME}님 어서오세요.</a></h1>
			</c:when>
		</c:choose>
	<!-- Nav -->
		<form id="categoryform" name="categoryform" method="post">
		<nav id="nav">
			<!-- 
			<header>
				<h2>Category</a></h2>
			</header>
			 -->
			<ul>
				<c:forEach var="categoryList" items="${categoryList}">
					<%-- <c:if test="${categoryList.seq eq category}"></c:if> --%>
					<li <c:if test="${categoryList.seq eq category}">class="current"</c:if>><a href="javascript:categoryView('${categoryList.seq}');">${categoryList.codename} (${categoryList.cnt})</a></li>
				</c:forEach>
				
				
				<c:if test="${GRADE eq 9}">
				<li><a href="/chatBoard.do">Chat Board</a></li>
				<li><a href="qrWrite.do">QR 등록</a></li>
				</c:if>
				<!-- 모바일 전용 메뉴 -->
				<c:if test="${isMobile}">
					<li><a href="javascript:call_native_camera();">Camera</a></li>
					<li><a href="javascript:call_native_map();">Daum Map</a></li>
				</c:if>
				
				
			</ul>
		</nav>
		</form>
		
	<!-- Search -->
		<section class="box search">
			<form>
				<input type="text" class="text" id="search" name="search" placeholder="Search" />
			</form>
		</section>
		
	<!-- Text -->
		<!-- <section class="box text-style1">
			<div class="inner">
				<p>
					<strong>Striped:</strong> A free and fully responsive HTML5 site
					template designed by <a href="http://twitter.com/ajlkn">AJ</a> for <a href="/appInterface.do">HTML5 UP</a>
				</p>
			</div>
		</section> -->

	<!-- Recent Posts -->
		<section class="box recent-posts">
			<header>
				<h2>Recent Posts</h2>
			</header>
			<ul>
				<c:forEach var="boardRecentList" items="${boardRecentList}">
				<%-- <li><a href="#"><c:out value="${fn:substring(boardRecentList.title,0,3)}" /></a></li> --%>
				
				<%-- <li><a href="#">${boardRecentList.title}" /></a></li> --%>
				<c:choose>
					<c:when test="${fn:length(boardRecentList.title)>17}">
						<li><a href="boardView.do?seq=${boardRecentList.seq}">${fn:substring(boardRecentList.title,0,17)} ..</a></li>
					</c:when>
						
					<c:when test="${fn:length(boardRecentList.title)<17}">
						<li><a href="boardView.do?seq=${boardRecentList.seq}">${boardRecentList.title}</a></li>
					</c:when>
				</c:choose>
				
				</c:forEach>
			</ul>
		</section>

	<!-- Recent Comments -->
	<!-- 
		<section class="box recent-comments">
			<header>
				<h2>Recent Comments</h2>
			</header>
			<ul>
				<li>case on <a href="">Lorem ipsum dolor</a></li>
				<li>molly on <a href="#">Sed dolore magna</a></li>
				<li>case on <a href="#">Sed dolore magna</a></li>
			</ul>
		</section>
 	-->
	<!-- Calendar -->
		<section class="box calendar">
		<div class="inner" id="kCalendar"></div>
		</section>

	<!-- Copyright -->
		<!-- <ul id="copyright">
			<li>&copy; Untitled.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
		</ul> -->
		
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/webView.js"></script>
<script>
	window.onload = function () {
		kCalendar('kCalendar');
	};
	
	$(document).ready(function() {
		$('#btn_camera').click(function() {
			webView.call_native_camera();
		});	
	});
	
	//카메라 호출
	function call_native_camera(){
		webView.call_native_camera();
	}
	
	//지도 호출
	function call_native_map(){
		webView.call_native_map();
	}
</script>