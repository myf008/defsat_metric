

function ShowInfluxdbCtrl($scope, $http, $modal, $routeParams, InfluxdbService) {
    
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
        
        var influxdbResult = InfluxdbService.getInfluxdbByID({'action':$routeParams.influxdbId});
        
        influxdbResult.$promise.then(function (data) {
        	$scope.isLoading = false;
        	if (data.success) {           		
        		$scope.influxdb = data.result;
        		$scope.userName = data.result.userName;
        		$scope.password = data.result.password;
        	}else{
        		showAlert('warning', data.messages);
        	}
        });
    
    }
    
    function initFunctions() {
    	
    	$scope.closeInfluxdb = function(){
    		 window.location = "/metric-admin/#/influxdb";
    	};
        
        $scope.closeAlert = function () {
        	$scope.data.isShow = false;
        };
        
        $scope.updateInfluxdb = function (influxdb) {
        	influxdb.userName = $scope.userName;
        	influxdb.password = $scope.password;
            var influxdbResult = InfluxdbService.updateInfluxdb({'influxdb':influxdb});
            
            influxdbResult.$promise.then(function (data) {
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