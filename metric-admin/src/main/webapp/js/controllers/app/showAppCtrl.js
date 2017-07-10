

function ShowAppCtrl($scope, $http, $modal, $routeParams, AppService) {
    
	initScope();

    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }

    function initParameters() {
        //提示信息
        $scope.data = {
            type: '',
            msg: '',
            isShow: false
        };
        
        
        var appResult = AppService.getAppByID({'action':$routeParams.appId});
        
        appResult.$promise.then(function (data) {
        	$scope.isLoading = false;
        	if (data.success) {           		
        		$scope.app = data.result;
        		$scope.description = data.result.description;
        		$scope.owner = data.result.owner;
        	}else{
        		showAlert('warning', data.messages);
        	}
        });
    
    }
    
    function initFunctions() {
    	
    	$scope.closeApp = function(){
    		 window.location = "/metric-admin/#/app";
    	};
        
        $scope.closeAlert = function () {
        	$scope.data.isShow = false;
        };
        
        $scope.updateApp = function (app) {
        	app.description = $scope.description;
        	app.owner = $scope.owner;
            var appResult = AppService.updateApp({'app':app});
            
            appResult.$promise.then(function (data) {
            	$scope.isLoading = false;
            	if (data.success) {           		
            		showAlert('info', 'OK'); 
            	}else{
            		showAlert('warning', data.messages);
            	}
            });
        }
    }
    
    //显示提示信息
    function showAlert(type, msg) {
        $scope.data.msg = msg;
        $scope.data.type = type;
        $scope.data.isShow = true;
    }
}