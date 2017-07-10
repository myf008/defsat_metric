
function DelRelationCtrl($scope, $modalInstance, relation, RelationService){
	
    $scope.name = '业务模块 : '+relation.appId + '的数据库配置';
    $scope.relation = relation;

    $scope.alert = {
        type: '',
        msg: '',
        isShow: false
    };
    
    $scope.del = function(){
    	var delResult = RelationService.delRelationByID({'appId':relation.appId});
    	
        delResult.$promise.then(function (data) {
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