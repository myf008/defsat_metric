angular.module("influxdbService", ['ngResource'])
    .factory("InfluxdbService", ['$resource', '$routeParams',
        function ($resource, $routeParams) {
            return $resource("/metric-admin/influxdb/:action",
                {},
                {
                	getAllInfluxdbs: {
                		method: 'GET',
                		params: {
                            action: 'influxdbList'
                		}
                	},    
                    getInfluxdbByID: {
                        method: 'GET',
                        params: {
                            action: '@influxdbId'
                        }
                    }, 
                    delInfluxdbByID: {
                        method: 'POST',
                        params: {
                            action: 'delete',
                            influxdbId: '@influxdbId'
                        }
                    }, 
                    updateInfluxdb:{
                    	method:'POST',
                        params: {
                            action: 'update',
                            influxdb:'@influxdb'
                        }
                    },
                    addInfluxdb:{
                    	method:'POST',
                        params: {
                            action: 'add',
                            influxdb:'@influxdb'
                        }
                    }
                }
            );
        }
]);