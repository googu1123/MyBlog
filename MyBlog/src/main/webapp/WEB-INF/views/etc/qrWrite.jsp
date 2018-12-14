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
					<form id="requestform" name="requestform" method="post">
					<table class="default_1">
					<input type="hidden" name="qr_version" value="3.0"/>
					
						<tr>
							<td colspan="2" align="center">QRCode Generation</td>
						</tr>
						<tr>
							<td>성명</td>
							<td><input type="text" id="qr_name" name="qr_name" value="" size="50"/></td>
						</tr>
						<tr>
							<td>회사명</td>
							<td><input type="text" id="qr_company" name="qr_company" value="" size="50"/></td>
						</tr>
						<tr>
							<td>부서/직책</td>
							<td><input type="text" id="qr_title" name="qr_title" value="" size="50"/></td>
						</tr>
						<tr>
							<td>URL</td>
							<td><input type="text" id="qr_url" name="qr_url" value="" size="50"/></td>
						</tr>
					</table>
					</form>
				</article>
					
				<div class="footer_btn">
					<a class="button next" id="btn_save">SAVE</a>
					<a class="button next" id="btn_list">LIST</a>
				</div>
				<div id="qr_img"></div>
				</div>
			</div>

		<!-- Sidebar -->
			<%-- <jsp:include page="/viewPage.do?pageName=sidebar" flush="false" ></jsp:include> --%>
			<%@include file="../board/sidebar.jsp" %>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>
			
			<script type="text/javascript">
			$(document).ready(function() {
				
				var mForm = document.requestform; 		// form의 값을 가져오기 위함
				
				//QR save
				$('#btn_save').click(function() {
					
					var obj = new Object(); 
					
					obj.qr_name = mForm.qr_name.value;
			   		obj.qr_company = mForm.qr_company.value;
			   		obj.qr_title = mForm.qr_title.value;
			   		obj.qr_url = mForm.qr_url.value;
			   		
			   		var json_data = JSON.stringify(obj);
					//alert(json_data);
					$("#requestform").submit(function(e){
					
						var postData = $(this).serializeArray();
				 	    var formURL = "qrSave.do";
				 	    $.ajax(
				 	    {
				 	        url : formURL,
				 	        type: "POST",
				 	        data : json_data,
				 	        dataType: "json",
				 	        processData: false,
					 	    contentType: false,
				 	        success:function(data, textStatus, jqXHR) 
				 	        {
				 	            //data: return data from server
				 	            var jsonObj = JSON.stringify(data);
					    		var jParse = JSON.parse(jsonObj);
				    			
				    			if(jParse.rt_code=="0000")
				    			{
				    				alert(jParse.rt_msg);
				    				view_qr_img(jParse.rt_qrImg);
				    				//document.location.href="boardList.do";
				    			}
				    			else
				    			{
				    				alert(jParse.rt_msg + " ["+jParse.rt_code+"]");
				    				//document.location.reload();
				    			}
				    			
				    			return;
				    			
				 	        },
				 	        error: function(jqXHR, textStatus, errorThrown) 
				 	        {
				 	        	alert('save fail!!');
				 	            //if fails      
				 	        }
				 	    });

						e.preventDefault(); //STOP default action
						$("#requestform").unbind('submit');
						//e.unbind(); //unbind. to stop multiple form submit.
						
				 	});
					
					$("#requestform").submit();
					
				});
				
				$('#btn_list').click(function() {
					document.location.href="boardList.do";
				});
				
			});
			
			
			
			function view_qr_img(img)
			{
				document.getElementById("qr_img").innerHTML ="<img src="+img+">";
			}
			</script>
	</body>
</html>