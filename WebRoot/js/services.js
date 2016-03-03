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

clientServices.factory('Client', ['$http', function($http) {
	return {
		list: function(params) {
			console.info (params );
			return $http({
				method: 'post',
				url: '/client/list',
				params : {
					mobile : params.mobile,
					name : params.name
				}
			})
		},
		add: function(params) {

		}
	};
}]);


// $resource(url [, paramDefaults 可选 对象] , action[可选，对象]);