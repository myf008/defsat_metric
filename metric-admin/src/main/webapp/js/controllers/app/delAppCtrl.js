
function DelAppCtrl($scope, $modalInstance, app, AppService){
	
    $scope.name = '业务模块 : '+app.appId;
    $scope.app = app;

    $scope.alert = {
        type: '',
        msg: '',
        isShow: false
    };
    
    $scope.del = function(){
    	var appResult = AppService.delAppByID({'appId':app.appId});
    	
        appResult.$promise.then(function (data) {
        	$scope.isLoading = false;
        	if (data.success) {           		
        		$modalInstance.close('ok');
        	}else{
        		$scope.alert = {
					type: 'alert-danger',
					msg: data.messages,
					isShow: true
    			};  
        	}
        });
    };
    
    $scope.cancel = function(){
        $modalInstance.close();
    };
    
}