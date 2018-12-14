<!doctype html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>WebSocket Chat</title>
  </head>
  <body>
    <h1>WebSocket Chat</h1>
    <section id="content"></section>
    <input id="message" type="text"/>
    <script src="http://www.google.com/jsapi"></script>
    <script>google.load("jquery", "1.3")</script>
    <script src="https://raw.github.com/douglascrockford/JSON-js/master/json2.js"></script>
    <script src="https://raw.github.com/dchelimsky/jquery-websocket/v0.0.4/jquery.websocket.js"></script>
    <script>
      var ws = $.websocket("ws://127.0.0.1:8080/", {
          events: {
              message: function(e) { $('#content').append(e.data + '<br>') }
          }
      });
      $('#message').change(function(){
          ws.send('message', this.value);
          this.value = '';
      });
    </script>
  </body>
</html>