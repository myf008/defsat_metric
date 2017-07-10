

function ShowRelationCtrl($scope, $http, $modal, $routeParams, RelationService,InfluxdbService) {
    
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
        
        $scope.influxdbs =[];
   	 
   	 	refreshData();
    }
    
    function refreshData(){
    	getInfluxdbs();
    	getRelationByID();
    }
    
    function getRelationByID(){
    	var getResult = RelationService.getRelationByID({'action':$routeParams.appId});
        getResult.$promise.then(function (data) {
        	$scope.isLoading = false;
        	if (data.success) {           		
        		$scope.relation = data.result;
        	}else{
        		showAlert('warning', data.messages);
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
    
    function initFunctions() {
    	
    	$scope.closeRelation = function(){
    		 window.location = "/metric-admin/#/relation";
    	};
        
        $scope.closeAlert = function () {
        	$scope.data.isShow = false;
        };
        
        $scope.updateRelation = function (relation) {
        	relation.influxdbId = $scope.relation.influxdbId;
        	relation.dbName = $scope.relation.dbName;
        	relation.retention = $scope.relation.retention;
        	
            var updateResult = RelationService.updateRelation({'relation':relation});
            
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