$(document).ready(function() {
	$('#btn_write').click(function() {
		document.location.href="boardWrite.do";
	});
	
	$('#btn_login').click(function() {
		document.getElementById('id01').style.display='block'
		$('#userid').focus();
	});
	
	$('#btn_logout').click(function() {
		document.location.href="logout.do";
	});
	
	$("#loginform").submit(function(e){
		
		var form = document.getElementById('loginform');
		var obj = new Object(); 			// JSON형식으로 변환 할 오브젝트
		obj.userid = form.userid.value; 	// form의 값을 오브젝트에 저장
		obj.pwd = form.pwd.value;
		
		var json_data = JSON.stringify(obj); // form의 값을 넣은 오브젝트를 JSON형식으로 변환
		
	    var formURL = "/login.do";
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
					//alert(jParse.rt_msg);
					document.location.href="boardList.do";
				}
				else if(jParse.rt_code=="7777")
				{
					$('#pwd').val('');
					$('#pwd').focus();
					alert(jParse.rt_msg);
				}
				else
				{
					$('#userid').val('');
					$('#pwd').val('');
					$('#userid').focus();
					alert(jParse.rt_msg);
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
		$("#lofinform").unbind('submit');
		//e.unbind(); //unbind. to stop multiple form submit.
		
	});
	
});

function goPage(page) 
{
	document.location.href="boardList.do?category="+category+"&pageNum="+page;
}

function viewPage(seq) 
{
	document.location.href="boardView.do?seq="+seq+"&category="+category+"&pageNum="+pageNum;
}

// Get the modal
var modal = document.getElementById('id01');
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        loginFormReset();
    }
}

$(document).keyup(function(e) {
    if (e.keyCode == 27) {
    	modal.style.display = "none";
    	loginFormReset();
   }
});

function loginFormReset()
{
	$('#userid').val('');
	$('#pwd').val('');	
}