$(document).ready(function() 
{	
	$('#btn_list').click(function() 
	{
		document.location.href='boardList.do?category='+category+'&pageNum='+pageNum;
	});
	
});


function boardDelete(seq)
{
	if(confirm('게시물을 삭제 하시겠습니까?'))
	{
		var formURL = "boardDelete.do";
		var obj = new Object();
		obj.seq = seq;
		
		var json_data = JSON.stringify(obj); // form의 값을 넣은 오브젝트를 JSON형식으로 변환
		
	    $.ajax(
	    {
	        url : formURL,
	        type: "POST",
	        //data : postData,
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
					document.location.href="boardList.do";
				}
				else
				{
					alert(jParse.rt_msg + " ["+jParse.rt_code+"]");
					if (jParse.rt_msg=="8888"){
						localhost.href="/";
					}
					//document.location.reload();
				}
				
				return;
			
	        },
	        error: function(jqXHR, textStatus, errorThrown) 
	        {
	        	alert('delete Fail!!');
	            //if fails      
	        }
	    });
		
	}
	
}