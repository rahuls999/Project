/**
 * 
 */
app.factory('UserService',function($http){
	var userservice={}
	userservice.registerUser=function(user){
		return $http.post("http://localhost:8580/Project2/registration",user)
		
	}
	
	userservice.login=function(user){
		return $http.post("http://localhost:8580/Project2/login",user)
	}
	
	
	
	userservice.logout=function(){
		return $http.get("http://localhost:8580/Project2/logout")
	}
	return userservice;
})