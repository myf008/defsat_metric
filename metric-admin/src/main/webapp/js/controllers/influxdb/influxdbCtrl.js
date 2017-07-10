

function InfluxdbListCtrl($scope,$rootScope,InfluxdbService,BaseTableService,$modal,$filter){

	

	initScope();
	  
/***********************functions***********************/
	function initScope() {
	      initParameters();
	      initFunctions();
	}
	   
	function initParameters() {
		refreshData();
	}
	
	
    function refreshData() {
    	
    	var influxdbResult = InfluxdbService.getAllInfluxdbs();
    	
    	influxdbResult.$promise.then(
                function (data) {
                    $scope.isLoading = false;
                    if (data.success) {
	                    $scope.dataList = data.result;    
	                    $scope.table = BaseTableService.getCustomizedTable($scope, $filter);  
	                    
                    }else{
                    	displayMsgDialog('warning', data.messages);
                    }
                }
         );
    	
    }
	
    function initFunctions() {
	    $scope.addInfluxdb = function () {
	           $scope.message = {
	                title: 'Add Influxdb',
	                msg: '',
	                type:'',
	                isShow:false
	            };
	            var modalInstance = $modal.open({
	                templateUrl: '/metric-admin/partials/influxdb/addInfluxdb.html',
	                controller: AddInfluxdbCtrl,
	                resolve: {
	                    data: function () {
	                        return $scope.message;
	                    }
	                }
	            });
	            modalInstance.result.then(function (data) {
					refreshData();
	            }, function () {
	            });
	    };   
	    
	    $scope.deleteInfluxdb = function(influxdb){
	        var modalInstance = $modal.open({
	 
	            templateUrl: '/metric-admin/partials/common/deleteConfirm.html',
	            controller: 'DelInfluxdbCtrl',
	            resolve: {
	            	influxdb: function () {
	                    return influxdb;
	                }
	            }
	        });
	       

	        modalInstance.result.then(function () {
	            refreshData();
	        }, function () {
	        });
	    }
	    
    }
    
    
    
    function displayMsgDialog(header,body){
		var modalInstance = $modal.open({
				            templateUrl: '/padis-admin/partials/common/message.html',
				            controller: 'MessageCtrl',
				            resolve: {
				                msg: function () {
				                    return {
												headerText:header,
												bodyText:body
											};
				                }
				            }
		});
	}
}