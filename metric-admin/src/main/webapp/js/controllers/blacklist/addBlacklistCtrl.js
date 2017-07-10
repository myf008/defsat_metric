'use strict';

//展示信息的dialog的controller
function AddBlacklistCtrl($scope, $modalInstance, data, AppService, BlacklistService) {
	
    /***********************执行开始***********************/
    initScope();
    /***********************执行结束***********************/

    /***********************functions***********************/
    function initScope() {
        initParameters();
        initFunctions();
    }
    
    function initParameters() {
    	 $scope.data = data;
    	 $scope.blacklist = {
    			 appId:'',
    			 dbName:'',
    			 measurement:'',
    			 fieldName:''
    	 }
    	
    	 $scope.appIds = [];
    	 
    	 refreshData();
    }
	
    function refreshData() {
    	getAppIds();
	}
    
    function getAppIds() {
		var getResult = AppService.getAllApps();
		getResult.$promise.then(function(data) {
			if (data.success) {
				var apps = data.result;
				angular.forEach(apps, function(app){
					$scope.appIds.push(app.appId);
				});
				
			} else {
				//do nothing
			}
		});
	}
    
    
    function initFunctions() {
    	    	
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.data.isShow = false;
            
        };  
    	
    }
    
    $scope.addBlacklist = function(){
    	var result = BlacklistService.addBlacklist({
            'blacklist': $scope.blacklist
          });
    	processData(result);
    }
    
	function processData(result) {
        result.$promise.then(
            function (data) {
            	console.log(data);
                $scope.isLoading = false;
                if (data.success) {
                   $modalInstance.close('ok');    
 
                } else {
                    showAlert('warning', data.messages);
                }
         });
    } 
	
    
    $scope.cancel = function(){
        $modalInstance.dismiss('cancel');
    };
    
    
    
    //显示提示信息
    function showAlert(type, msg) {
        $scope.data.msg = msg;
        $scope.data.type = type;
        $scope.data.isShow = true;
    }
}