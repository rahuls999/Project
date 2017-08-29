/**
 * 
 */

app.service("ChatService",function($q,$timeout){
	var service={},
	listner=$q.defer(),socket={
		client:null,
		stomp:null},
		messageIds=[];
	
	service.RECONNECT_TIMEOUT=30000;
	//service.SOCKET_URL="http://localhost:8580/Project2/chatting";
	service.CHAT_TOPIC="/topic/message";
	service.CHAT_BROKER="/app/chatting";
	
	service.recieve=function(){
		return listener.promise;
	};
	
	
	service.send=function(message){
		var id=Math.floor(Math.random()*1000000);
		socket.stomp.send(service.CHAT_BROKER,{
			priority:9},
	JSON.stringify({
			message:message,
			id:id}));
messageIds.push(id);

		};
		
		
		var reconnect=function(){
			$timeout(function(){
				initilize();
			},this.RECONNECT_TIMEOUT);
			
		};
		
		
		
		
		var getMessage=function(data){
			console.log(data);
			var message=JSON.parse(data),out={};
			out.message=message.message;
			//out.time=newDate(message.time);
			return out;
		};
		
	
	
	
	var startListener=function(){
		socket.stomp.subscribe(service.CHAT_TOPIC,function(data){
			listener.notify(getMessage(data.body));
		});
	};
	
	
	var initilize=function(){
		
		socket.client=new SockJS('/Project2/chatting');
		socket.stomp=Stomp.over(socket.client);
		socket.stomp.connect({},startListener);
		socket.stomp.client=reconnect;
	};
	initilize();
	return service;
	
});