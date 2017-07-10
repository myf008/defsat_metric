/**
 * create by feiyongjun 
 */
'use strict';

var module = angular.module('portal', ['ngRoute',
				'ngResource',
				'appService',
				'influxdbService',
				'relationService',
				'blacklistService',
				'baseTableService',
				'ui.bootstrap']);
				
	module.config(['$routeProvider',
    function ($routeProvider) {

        $routeProvider
        	.when("/app",{
                templateUrl: '/metric-admin/partials/app/app.html',
                reloadOnSearch: false
            }).when("/app/:appId",{
                templateUrl: '/metric-admin/partials/app/showApp.html',
                reloadOnSearch: false
            }).when("/influxdb",{
            	templateUrl: '/metric-admin/partials/influxdb/influxdb.html',
            	reloadOnSearch: false
            }).when("/influxdb/:influxdbId",{
            	templateUrl: '/metric-admin/partials/influxdb/showInfluxdb.html',
            	reloadOnSearch: false
            }).when("/relation",{
            	templateUrl: '/metric-admin/partials/relation/relation.html',
            	reloadOnSearch: false
            }).when("/relation/:appId",{
            	templateUrl: '/metric-admin/partials/relation/showRelation.html',
            	reloadOnSearch: false
            }).when("/blacklist",{
            	templateUrl: '/metric-admin/partials/blacklist/blacklist.html',
            	reloadOnSearch: false
            }).when("/blacklist/:appId/:dbName/:measurement/:fieldName",{
                templateUrl: '/metric-admin/partials/blacklist/showBlacklist.html',
                reloadOnSearch: false
            }).otherwise({
                templateUrl: '/metric-admin/partials/app/app.html',
                reloadOnSearch: false
            });
    }]);
	 