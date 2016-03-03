'use strict';

/* Controllers */
var clientControllers = angular.module('ClientControllers', []);

clientControllers.controller('ClientListController', ['$scope', 'Client',
	function($scope, Client) {
		$scope.client = {};
		
		this.queryList = function(){
			Client.list($scope.client).success(function(data) {
				if (data && data.code == 1) {
					$scope.clientList = data.data.clientList;
				}
			});
		}
		
		this.queryList(); //init
		var that = this;
		
		$scope.search = function(){
			that.queryList();
		}
	}
]);