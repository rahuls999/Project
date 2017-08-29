/**
 * 
 */
var app=angular.module("app", ['ngRoute', 'ngCookies'])
app.config(function($routeProvider){

	$routeProvider.when('/registration',{
		templateUrl : 'Views/Registration.html',
	controller : 'UserController'
})


                .when ('/login',{
                	templateUrl : 'Views/Login.html',
                	controller : 'UserController'
                		})
                		
                		
                .when('/chat',{
                templateUrl : 'Views/chat.html',
                controller : 'ChatController'
                })		
                		.when('/',{
                		templateUrl:'Views/Home.html'
                		})
                		
                		
                		.otherwise('/',{
                		templateUrl : 'Views/Home.html'
                })
})


app.run(function($rootScope,$location,UserService,$cookieStore){
	if($rootScope.currentUser== undefined)
		$rootScope.currentUser=$cookieStore.get("currentUser")
		
		
		$rootScope.logout=function(){
		console.log('app.run logout is called**')
		delete $rootScope.currentUser;
		$cookieStore.remove('currentUser')
		UserService.logout().then(function(response){
			$location.path('/login')
		},function(response){
			$rootScope.message=response.data.message
		
		
		})
	}
		
})
