$(document).ready(function() {
	
	var iframeDocument = document.getElementById('editor').contentDocument;
	iframeDocument.designMode = 'on';
	$(iframeDocument).find('body').append('');
	
	$(".editable").each(function(){
	    this.contentEditable = true;
	});
	
	$('#btn_list').click(function() {
		document.location.href="boardList.do";
	});
	
	$('#btn_save').click(function() {
		if (formCk())
 	    {
			var form = new FormData(document.getElementById('requestform'));
			
			$("#requestform").submit(function(e)
		 	{
				var postData = $(this).serializeArray();
		 	    var formURL = "boardInsert.do";
		 	    $.ajax(
		 	    {
		 	        url : formURL,
		 	        type: "POST",
		 	        //data : postData,
		 	        data : form,
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
		    				document.location.href="boardList.do";
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
 	    }
		
	});
	
		
});

function formCk()
{
	var iframeDocument = document.getElementById('editor').contentDocument;
	iframeDocument.designMode = 'on';
	
	if ($("#category").val()=="")
	{
		alert('게시판 종류를 선택해주세요.');
		$("#category").focus();
		return false;
	}
	
	if ($("#title").val()=="")
	{
		alert('제목을 입력해주세요.');
		$("#title").focus();
		return false;
	}
	
	var editorVal = document.getElementById("editor").contentWindow.document.body.innerHTML;
	$("#contents").val(editorVal);
	
	if ($("#contents").val()=="" || $("#contents").val().length==0)
	{
		alert('내용을 입력해주세요.');
		$("#contents").focus();
		return false;
	}

	return true;
}
