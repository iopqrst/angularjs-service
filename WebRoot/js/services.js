'use strict';

/* Services */
var clientServices = angular.module('MyClientServices', []);

clientServices.factory('Client', ['$http', function($http) {
	return {
		list: function(params) {
			//console.info(params);
			return $http({
				method: 'post',
				url: '/client/list',
				params: {
					mobile: params.mobile,
					name: params.name,
					pageNo: params.pageNo,
					pageSize: params.pageSize
				}
			})
		},
		add: function(params) {

		}
	};
}]);


// $resource(url [, paramDefaults 可选 对象] , action[可选，对象]);