angular.module("blacklistService", ['ngResource'])
    .factory("BlacklistService", ['$resource', '$routeParams',
        function ($resource, $routeParams) {
            return $resource("/metric-admin/blacklist/:action",
                {},
                {
                	getAllBlacklists: {
                		method: 'GET',
                		params: {
                            action: 'all'
                		}
                	},    
                    getBlacklistByID: {
                        method: 'GET',
                        params: {
                            action: '@appId'
                        }
                    }, 
                    delBlacklist: {
                        method: 'POST',
                        params: {
                            action: 'delOne',
                            blacklist: '@blacklist'
                        }
                    }, 
                    updateBlacklist:{
                    	method:'POST',
                        params: {
                            action: 'update',
                            blacklist: '@blacklist'
                        }
                       
                    },
                    addBlacklist:{
                    	method:'POST',
                        params: {
                            action: 'add',
                            blacklist: '@blacklist'
                        }
                    }
                }
            );
        }
]);