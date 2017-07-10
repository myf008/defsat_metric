

function AppListCtrl($scope,$rootScope,AppService,BaseTableService,$modal,$filter){

	

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
    	
    	var appResult = AppService.getAllApps();
    	
    	appResult.$promise.then(
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
	    $scope.addApp = function () {
	           $scope.message = {
	                title: 'Add App',
	                msg: '',
	                type:'',
	                isShow:false
	            };
	            var modalInstance = $modal.open({
	                templateUrl: '/metric-admin/partials/app/addApp.html',
	                controller: AddAppCtrl,
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
	    
	    $scope.deleteApp = function(app){
	        var modalInstance = $modal.open({
	 
	            templateUrl: '/metric-admin/partials/common/deleteConfirm.html',
	            controller: 'DelAppCtrl',
	            resolve: {
	                app: function () {
	                    return app;
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
				            templateUrl: '/metric-admin/partials/common/message.html',
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