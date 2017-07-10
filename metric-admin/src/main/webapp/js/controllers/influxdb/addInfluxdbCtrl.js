'use strict';

//展示信息的dialog的controller
function AddInfluxdbCtrl($scope, $modalInstance, data,InfluxdbService) {
	
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
    	 $scope.influxdb = {
    			 influxdbId:'',
    			 userName:'',
    			 password:''
    	 }
    	 
    	 
    }
	
    function initFunctions() {
    	    	
        //不显示提示信息
        $scope.closeAlert = function () {
            $scope.data.isShow = false;
            
        };  
    	
    }
    
    $scope.addInfluxdb = function(){
    	var result = InfluxdbService.addInfluxdb({
            'influxdb': $scope.influxdb
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