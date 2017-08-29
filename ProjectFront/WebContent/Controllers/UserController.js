/**
 * 
 */
app.controller('UserController',function(UserService,$scope,$location,$rootScope,$cookieStore){
	$scope.registrationSuccess=''
$scope.register=function(){
	UserService.registerUser($scope.user).then(function(response){
		$scope.registrationSuccess="Registered successfully.please login"
			console.log('registered')
			$location.path('/login')
	},function(response){
		$scope.error=response.data;
		$location.path('/registration')
		
	
	})
}
	
	$scope.login=function(){
		UserService.login($scope.user).then (function(response)
{
			$rootScope.currentUser=response.data
			console.log('currentUser****')
			$cookieStore.put("currentUser",response.data)
			console.log(response.data)
			//console.log(currentUser)
			$location.path('/home')
			},
			function(response){
				$scope.message=response.data.message
				$location.path('/login')
			})}
	
})