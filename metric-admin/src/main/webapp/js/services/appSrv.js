angular.module("appService", ['ngResource'])
    .factory("AppService", ['$resource', '$routeParams',
        function ($resource, $routeParams) {
            return $resource("/metric-admin/app/:action",
                {},
                {
                	getAllApps: {
                		method: 'GET',
                		params: {
                            action: 'appList'
                		}
                	},    
                    getAppByID: {
                        method: 'GET',
                        params: {
                            action: '@appId'
                        }
                    }, 
                    delAppByID: {
                        method: 'POST',
                        params: {
                            action: 'delete',
                            appId: '@appId'
                        }
                    }, 
                    updateApp:{
                    	method:'POST',
                        params: {
                            action: 'update',
                            app: '@app'
                        }
                       
                    },
                    addApp:{
                    	method:'POST',
                        params: {
                            action: 'add',
                            app:'@app'
                        }
                    }
                }
            );
        }
]);