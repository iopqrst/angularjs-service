'use strict';

/* Controllers */
var clientControllers = angular.module('ClientControllers', []);

clientControllers.controller('ClientListController', ['$scope', 'Client',
	function($scope, Client) {
		$scope.client = {};

		$scope.paginationConf = {
			currentPage: 1,
			itemsPerPage: 10
		};

		var postData = {
			pageNo: $scope.paginationConf.currentPage,
			pageSize: $scope.paginationConf.itemsPerPage
		}

		this.queryList = function() {
			Client.list($scope.client).success(function(data) {
				if (data && data.code == 1) {
					$scope.clientList = data.data.clientList;
					$scope.pagerinfo = data.data.pager;
					
					$scope.paginationConf.totalItems = data.data.pager.totalRow;
					console.log(data.data.pager);
				}
			});
		}

		this.queryList(); //init
		var that = this;

		$scope.search = function() {
			that.queryList();
		}
	}
]);