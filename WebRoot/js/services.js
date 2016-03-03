'use strict';

/* Services */
var clientServices = angular.module('MyClientServices', []);

//clientServices.factory('Client', ['$resource', function($resource) {
//	return $resource('data/:data', {}, {
//		queryList: {
//			method: 'GET',
//			params: {
//				data: 'client-list.json'
//			}
//		},
//		add: {
//
//		}
//	})
//}]);

clientServices.factory('ClientList', ['$http', function($http) {
	return $http({
		method: 'get',
		url: 'http://192.168.1.101/client/list',
		data: {
			
		}
	});
}]);


// $resource(url [, paramDefaults 可选 对象] , action[可选，对象]);