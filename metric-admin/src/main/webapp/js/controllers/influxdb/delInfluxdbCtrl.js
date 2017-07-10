
function DelInfluxdbCtrl($scope, $modalInstance, influxdb, InfluxdbService){
	
    $scope.name = 'influxdb : '+influxdb.influxdbId;
    $scope.influxdb = influxdb;

    $scope.alert = {
        type: '',
        msg: '',
        isShow: false
    };
    
    $scope.del = function(){
    	var influxdbResult = InfluxdbService.delInfluxdbByID({'influxdbId':influxdb.influxdbId});
    	
    	influxdbResult.$promise.then(function (data) {
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