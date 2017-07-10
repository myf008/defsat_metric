angular.module("relationService", ['ngResource'])
    .factory("RelationService", ['$resource', '$routeParams',
        function ($resource, $routeParams) {
            return $resource("/metric-admin/relation/:action",
                {},
                {
                	getAllRelations: {
                		method: 'GET',
                		params: {
                            action: 'relationList'
                		}
                	},    
                    getRelationByID: {
                        method: 'GET',
                        params: {
                            action: '@appId'
                        }
                    }, 
                    delRelationByID: {
                        method: 'POST',
                        params: {
                            action: 'delete',
                            appId: '@appId'
                        }
                    }, 
                    updateRelation:{
                    	method:'POST',
                        params: {
                            action: 'update',
                            relation: '@relation'
                        }
                       
                    },
                    addRelation:{
                    	method:'POST',
                        params: {
                            action: 'add',
                            relation: '@relation'
                        }
                    }
                }
            );
        }
]);