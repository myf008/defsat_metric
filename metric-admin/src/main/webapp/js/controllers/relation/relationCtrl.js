

function RelationListCtrl($scope,$rootScope,RelationService,BaseTableService,$modal,$filter){

	

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
    	
    	var getAllResult = RelationService.getAllRelations();
    	
    	getAllResult.$promise.then(
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
	    $scope.addRelation = function () {
	           $scope.message = {
	                title: '添加数据库配置',
	                msg: '',
	                type:'',
	                isShow:false
	            };
	            var modalInstance = $modal.open({
	                templateUrl: '/metric-admin/partials/relation/addRelation.html',
	                controller: 'AddRelationCtrl',
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
	    
	    $scope.deleteRelation = function(relation){
	        var modalInstance = $modal.open({
	 
	            templateUrl: '/metric-admin/partials/common/deleteConfirm.html',
	            controller: 'DelRelationCtrl',
	            resolve: {
	                relation: function () {
	                    return relation;
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