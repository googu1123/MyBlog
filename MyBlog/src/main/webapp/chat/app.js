window.onload = function() {
	
  var USER = "";
  // Get references to elements on the page.
  var form = document.getElementById('message-form');
  var messageField = document.getElementById('message');
  var messagesList = document.getElementById('messages');
  var socketStatus = document.getElementById('status');
  var closeBtn = document.getElementById('close');


  // Create a new WebSocket.
  //var url = "192.168.200.166:8080";
  var url = "10.8.0.14:8080";
  //var url = "172.20.10.2:8080";
  //var socket = new WebSocket('ws://echo.websocket.org');
  var socket = new WebSocket('ws://'+url+'/broadsocket');


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
    var data = event.data;
    var jsonData = JSON.parse(data);
    if(jsonData.message != null) {
        //messageTextArea.value += jsonData.message + "\n"
    	var message = jsonData.message;
    	var username = jsonData.username;
    	var create = jsonData.create;
    	var yymmddss = jsonData.yymmddss;
        
    	if(create=='Y')
    	{
    		USER = username;
    		messagesList.innerHTML += '<li class="sent">'+username+'' + message + '</li>';
    	}
    	else
    	{
    		var classnm = 'sent';
    		if (USER!=username){
    			classnm = 'received';
    		} 
    		messagesList.innerHTML += '<li class="'+classnm+'"><span>'+username+'</span>' + message + ' <span class="yymmdd">'+yymmddss+'</span></li>';
    	}
        
    };
    
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
//    messagesList.innerHTML += '<li class="sent"><span>Sent:</span>' + message + '</li>';

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
