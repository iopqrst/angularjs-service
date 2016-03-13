'use strict';

/* Controllers */
var clientControllers = angular.module('ClientControllers', []);

clientControllers.controller('ClientListController', ['$scope', 'Client',
	function($scope, Client) {
		$scope.client = {};

		$scope.paginationConf = {
			currentPage: 1,
			itemsPerPage: 2,
			totalPage : 1
		};

		this.queryList = function() {
			$scope.client.pageNo = $scope.paginationConf.currentPage;
			$scope.client.pageSize = $scope.paginationConf.itemsPerPage;

			Client.list($scope.client).success(function(data) {
				if (data && data.code == 1) {
					$scope.clientList = data.data.clientList;
					$scope.pagerinfo = data.data.pager;
					
					//console.log('this is pager info:');
					//console.info(data.data.pager);

					$scope.paginationConf.totalItems = data.data.pager.totalRow;
					$scope.paginationConf.totalPage = data.data.pager.totalPage;
				}
			});
		}

		this.queryList(); //init
		var that = this;

		$scope.search = function() {
			that.queryList();
		}

		$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', this.queryList);
	}
]);