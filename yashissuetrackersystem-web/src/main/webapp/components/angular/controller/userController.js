angular.module('issueTrackingSystem.userModule').controller('userController',['$scope','$http','userService',function($scope,$http,userService,issuesList){
	
	alert("In User controller");
	$scope.issueList = [];
	
	var issues=$http({
 		 method:'GET',
 		     url:'./defaultIssuesList' //spring controller call, use @ResponseBody
 		 }).success(function(data){
 		 alert(data);
 		 	$scope.issueList = issuesList;
 		 })
 		 
	
		/*issueService returns list to populate drop-down*/
	/*userService.initializeSelect()
        .then(
                 function(d) {
                	 
                	 retrieve and assign value from list
                	 angular.forEach(d,function(value,key){
            			 switch(key){
            			 
            			 	case 0:
            					 $scope.priorities=value;
            					 break;
            			 	case 1:
            			 		$scope.assigneeList=value;
            			 		break;
            			 	case 2:
            			 		$scope.issueTypeList=value;
            			 		break;
            			 	case 3:
            			 		$scope.projects=value;
            			 		break;
            			 }
            		 });
                	 
                 },
                  function(errResponse){
                      console.error('Error while fetching');
                  }
             );
		
		this.createIssue={};
		this.add=function(){
			
			// call service to persist in db
			userService.submitCreateIssue(this.createIssue);
	        .then(
	                 function(d) {
	                	 
	                 },
	                  function(errResponse){
	                      console.error('Error while fetching');
	                  }
	             );
			this.createIssue={};
		};*/
	}]);