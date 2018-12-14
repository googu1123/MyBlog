var webView = new function() 
{
		    
    this.getMsg = function (msg) 
    {
    	alert(msg);
    };
    
    //Native Camera Call
    this.call_native_camera = function()
    {
    	Hybrid.callCamera('test','call()');
    };
    
    //Native Call Map
    this.call_native_map = function()
    {
    	Hybrid.callDaumMap();
    };
    
    //Native Call QR
    this.call_qr = function()
    {
    	Hybrid.call_qr();
    };
    
};