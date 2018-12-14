var server_url = "192.168.0.8:8080";
//var server_url = "54.169.52.109";
window.onload = function() {
 	
  // Get references to elements on the page.
  var form = document.getElementById('message-form');
  var messageField = document.getElementById('message');
  var messagesList = document.getElementById('messages');
  var socketStatus = document.getElementById('status');
  var closeBtn = document.getElementById('close');

  var hostName = "";

  // Create a new WebSocket.
  //var socket = new WebSocket('ws://echo.websocket.org');
  var socket = new WebSocket('ws://'+server_url+'/broadsocket');



  // Handle any errors that occur.
  socket.onerror = function(error) {
    console.log('WebSocket Error: ' + error);
  };


  // Show a connected message when the WebSocket is opened.
  socket.onopen = function(event) {
    socketStatus.innerHTML = 'Connected to: ' + event.currentTarget.URL;
    socketStatus.className = 'open';
  };


  // Handle messages sent by the server.
  socket.onmessage = function(event) {
    var message = event.data;
    
    if (message != null)
	{
    	var msg_name = "";
		var jParse = JSON.parse(message);
		
//		if(hostName ==''){
//			hostName = jParse.username;
//		}
//		else{
//			if (hostName == jParse.username){
//				msg_name = '<li class="received"><span ></span>';
//			}else{
//				msg_name = '<li class="received"><span >'+jParse.username+':</span>';
//			}
//		}
		
		if(hostName ==''){
			hostName = jParse.username;
			
		}else{
			msg_name = '<li class="received"><span >'+jParse.username+':</span>';
		}
		
		messagesList.innerHTML +=  msg_name +
	    jParse.message + ' <span style="color:green;width:200px;text-align:right;">'+jParse.yymmddss+'</span></li>';
	}
    //messagesList.innerHTML += '<li class="received"><span>Received:</span>' +
//                               message + '</li>';
    
    
    
  };


  // Show a disconnected message when the WebSocket is closed.
  socket.onclose = function(event) {
    socketStatus.innerHTML = 'Disconnected from WebSocket.';
    socketStatus.className = 'closed';
  };


  // Send a message when the form is submitted.
  form.onsubmit = function(e) {
    e.preventDefault();

    // Retrieve the message from the textarea.
    var message = messageField.value;

    // Send the message through the WebSocket.
    socket.send(message);

    // Add the message to the messages list.
    //messagesList.innerHTML += '<li class="sent"><span>Sent:</span>' + message + '</li>';

    // Clear out the message field.
    messageField.value = '';

    return false;
  };


  // Close the WebSocket connection when the close button is clicked.
  closeBtn.onclick = function(e) {
    e.preventDefault();

    // Close the WebSocket.
    socket.close();

    return false;
  };

};
