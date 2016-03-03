'use strict';

/* Controllers */
var clientControllers = angular.module('ClientControllers', []);

clientControllers.controller('ClientListController', ['$scope', 'ClientList',
	function($scope, ClientList) {
		ClientList.success(function(data) {
			if (data && data.code == 1) {
				$scope.clientList = data.data.clientList;
			}
		});
	}
]);