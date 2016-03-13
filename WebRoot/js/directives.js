'use strict';

/* Directives */
/**
 * 参考tmPagination
 * http://www.cnblogs.com/sword-successful/archive/2015/06/28/4605222.html
 */

var clientDirectives = angular.module('ClientDirectives', []);

clientDirectives.directive('tmPagination', [function() {
	return {
		restrict: 'EA',
		templateUrl: 'partials/template_page.html',
		replace: true,
		scope: {
			conf: '='
		},
		link: function(scope, element, attrs) {

			// 变更当前页
			scope.changeCurrentPage = function(item) {
				scope.conf.currentPage = item;
			};

			// prevPage
			scope.prevPage = function() {
				if (scope.conf.currentPage > 1) {
					scope.conf.currentPage -= 1;
				}
			};

			// nextPage
			scope.nextPage = function() {
				if (scope.conf.currentPage < scope.conf.totalPage) {
					scope.conf.currentPage += 1;
				}
			};

			//firstPage
			scope.firstPage = function() {
				scope.conf.currentPage = 1;
			};

			// lastPage
			scope.lastPage = function() {
				scope.conf.currentPage = scope.conf.totalPage;
			};

			// 跳转页
			scope.gotoPage = function() {
				scope.jumpPageNum = scope.jumpPageNum.replace(/[^0-9]/g, '');
				if (scope.jumpPageNum !== '') {
					scope.conf.currentPage = scope.jumpPageNum;
				}
			};

			// conf.erPageOptions
			if (!scope.conf.perPageOptions) {
				scope.conf.perPageOptions = [5, 10, 15, 20, 30, 50];
			}

			// pageList数组
			function getPagination() {
				//console.info('---------------enter getPagination .... ');
				// conf.currentPage
				scope.conf.currentPage = parseInt(scope.conf.currentPage) ? parseInt(scope.conf.currentPage) : 1;
				// conf.totalItems
				scope.conf.totalItems = parseInt(scope.conf.totalItems);
				// conf.itemsPerPage
				scope.conf.itemsPerPage = parseInt(scope.conf.itemsPerPage) ? parseInt(scope.conf.itemsPerPage) : 10;

				// judge currentPage > scope.totalPage
				if (scope.conf.currentPage < 1) {
					scope.conf.currentPage = 1;
				}
				
				console.info( 'here -=- > ' + scope.conf.totalPage);
				
				if (scope.conf.currentPage > scope.conf.totalPage) {
					scope.conf.currentPage = scope.conf.totalPage;
				}

				// jumpPageNum
				scope.jumpPageNum = scope.conf.currentPage;

				// 如果itemsPerPage在不在perPageOptions数组中，就把itemsPerPage加入这个数组中
				var perPageOptionsLength = scope.conf.perPageOptions.length;
				// 定义状态
				var perPageOptionsStatus;
				for (var i = 0; i < perPageOptionsLength; i++) {
					if (scope.conf.perPageOptions[i] == scope.conf.itemsPerPage) {
						perPageOptionsStatus = true;
					}
				}
				// 如果itemsPerPage在不在perPageOptions数组中，就把itemsPerPage加入这个数组中
				if (!perPageOptionsStatus) {
					scope.conf.perPageOptions.push(scope.conf.itemsPerPage);
				}

				// 对选项进行sort
				scope.conf.perPageOptions.sort(function(a, b) {
					return a - b
				});

				scope.pageList = [];
				//scope.conf.pagesLength 最好是奇数，否则你会发现效果不是很漂亮
				scope.conf.pagesLength = parseInt(scope.conf.pagesLength, 10) ? parseInt(scope.conf.pagesLength, 10) : 3;

				if (scope.conf.totalPage <= scope.conf.pagesLength) {
					// 判断总页数如果小于等于分页的长度，若小于则直接显示
					for (i = 1; i <= scope.conf.totalPage; i++) {
						scope.pageList.push(i);
					}
				} else {
					var offset = scope.conf.pagesLength - 1;
					if (scope.conf.currentPage <= Math.ceil(offset / 2)) {
						// 从1开始
						for (i = 1; i <= offset + 1; i++) {
							scope.pageList.push(i);
						}
					}

					if (scope.conf.currentPage > Math.ceil(offset / 2)) {

						if (scope.conf.currentPage < (scope.conf.totalPage - Math.ceil(offset / 2))) {
							// 两边都有
							for (i = Math.ceil(offset / 2); i >= 1; i--) {
								scope.pageList.push(scope.conf.currentPage - i);
							}
							scope.pageList.push(scope.conf.currentPage);
							for (i = 1; i <= offset / 2; i++) {
								scope.pageList.push(scope.conf.currentPage + i);
							}
							console.info('enter if part');
						} else {
							console.info('enter else part');
							for (var i = offset; i >= 0; i--) {
								scope.pageList.push(scope.conf.totalPage - i);
							}
						}
					}

				}

				if (scope.conf.onChange) {
					scope.conf.onChange();
				}
				scope.$parent.conf = scope.conf;
			}

			scope.$watch(function() {
				console.info('currentPage = ' + scope.conf.currentPage + ', totalItems = ' + scope.conf.totalItems + ', itemsPerPage = ' + scope.conf.itemsPerPage);

				var newValue = scope.conf.currentPage + ' ' + scope.conf.totalItems + ' ' + scope.conf.itemsPerPage;

				return newValue;
			}, getPagination);

		}
	};
}]);