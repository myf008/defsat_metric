
function DelBlacklistCtrl($scope, $modalInstance, blacklist, BlacklistService){
	

	    $scope.name = 'blacklist : ' + blacklist.appId + '_' + blacklist.dbName
			+ '_' + blacklist.measurement + '_' + blacklist.fieldName;
    $scope.blacklist = blacklist;

    $scope.alert = {
        type: '',
        msg: '',
        isShow: false
    };
    
    $scope.del = function(){
    	var blacklistResult = BlacklistService.delBlacklist({'blacklist' : blacklist});
    	
    	blacklistResult.$promise.then(function (data) {
        	$scope.isLoading = false;
        	if (data.success) {           		
        		$modalInstance.close('ok');
        	}else{
        		$scope.alert = {
					type: 'alert-danger',
					msg: data.messages,
					isShow: true
    			};  
        	}
        });
    };
    
    $scope.cancel = function(){
        $modalInstance.close();
    };
    
}