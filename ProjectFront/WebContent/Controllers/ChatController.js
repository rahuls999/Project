/**
 * 
 */

app.controller('ChatController',function($scope,$rootScope,ChatService,$window){
	console.log('inside chatcontroller.js****')
	$scope.message=[];
	$scope.message="";
	$scope.max=140;
	$scope.addMessage=function(){
		$window.alert("sending Message")
		console.log('entering add message function')
		ChatService.send($rootScope.currentUser.username+ " : " +$scope.message);
		$scope.message="";
		
	};
	
	
	ChatService.recieve().then(null,null,function(message){
		console.log('inside recive function')
		$scope.message.push(message);
	});
});