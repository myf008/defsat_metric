

function BlacklistListCtrl($scope,$rootScope,BlacklistService,BaseTableService,$modal,$filter){

	

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
    	
    	var blacklistResult = BlacklistService.getAllBlacklists();
    	
    	blacklistResult.$promise.then(
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
	    $scope.addBlacklist = function () {
	           $scope.message = {
	                title: 'Add Blacklist',
	                msg: '',
	                type:'',
	                isShow:false
	            };
	            var modalInstance = $modal.open({
	                templateUrl: '/metric-admin/partials/blacklist/addBlacklist.html',
	                controller: AddBlacklistCtrl,
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
	    
	    $scope.deleteBlacklist = function(blacklist){
	        var modalInstance = $modal.open({
	 
	            templateUrl: '/metric-admin/partials/common/deleteConfirm.html',
	            controller: 'DelBlacklistCtrl',
	            resolve: {
	            	blacklist: function () {
	                    return blacklist;
	                }
	            }
	        });
	       

	        modalInstance.result.then(function () {
	            refreshData();
	        }, function () {
	        });
	    }
	    
	    
	    $scope.updateBlacklist = function(blacklist){
	    	var modalInstance = $modal.open({
	    		
	    		templateUrl: '/metric-admin/partials/blacklist/showBlacklist.html',
	    		controller: 'ShowBlacklistCtrl',
	    		resolve: {
	    			blacklist: function () {
	    				return blacklist;
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