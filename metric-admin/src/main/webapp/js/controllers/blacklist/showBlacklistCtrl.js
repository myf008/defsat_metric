

function ShowBlacklistCtrl($scope, $http, $modal, $routeParams, BlacklistService) {
    
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
        
        $scope.blacklist = {
        	appId : $routeParams.appId,
        	dbName : $routeParams.dbName,
        	measurement : $routeParams.measurement,
        	fieldName : $routeParams.fieldName
        };
        $scope.isLoading = false;
		$scope.name = $routeParams.appId + '_' + $routeParams.dbName + '_' + $routeParams.measurement + '_' + $routeParams.fieldName;
    }
    
    function initFunctions() {
    	
    	$scope.closeBlacklist = function(){
    		 window.location = "/metric-admin/#/blacklist";
    	};
        
        $scope.closeAlert = function () {
        	$scope.data.isShow = false;
        };
        
        $scope.updateBlacklist = function (blacklist) {
        	blacklist.appId = $scope.blacklist.appId;
        	blacklist.dbName = $scope.blacklist.dbName;
        	blacklist.measurement = $scope.blacklist.measurement;
        	blacklist.fieldName = $scope.blacklist.fieldName;
        	
            var updateResult = BlacklistService.updateBlacklist({'blacklist':blacklist});
            
            updateResult.$promise.then(function (data) {
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