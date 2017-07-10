'use strict';

//展示信息的dialog的controller
function AddRelationCtrl($scope, $modalInstance, data,RelationService,AppService,InfluxdbService) {
	
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
    	 $scope.relation = {
    			 appId:'',
    			 influxdbId:'',
    			 dbName:'',
    			 retention:'default'
    	 }
    	 $scope.appIds = [];
    	 $scope.influxdbs =[];
    	 
    	 refreshData();
    }
	
    function refreshData() {
    	getAppIds();
    	getInfluxdbs();
	}
    
    function initFunctions() {
    	    	
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.data.isShow = false;
            
        };  
    	
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
    
    function getInfluxdbs() {
    	var getResult = InfluxdbService.getAllInfluxdbs();
    	getResult.$promise.then(function(data) {
    		if (data.success) {
    			var objs = data.result;
				angular.forEach(objs, function(influxdb){
					$scope.influxdbs.push(influxdb.influxdbId);
				});
    		} else {
    			//do nothing
    		}
    	});
    }
    
    $scope.addRelation = function(){
    	var result = RelationService.addRelation({
            'relation': $scope.relation
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
                    showAlert('error', data.messages);
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