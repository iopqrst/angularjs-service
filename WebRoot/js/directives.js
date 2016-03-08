'use strict';

/* Directives */

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
				if (item == '...') {
					return;
				} else {
					scope.conf.currentPage = item;
				}
			};

			// conf.erPageOptions
			if (!scope.conf.perPageOptions) {
				scope.conf.perPageOptions = [5, 10, 15, 20, 30, 50];
			}

			// pageList数组
			function getPagination() {
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
				scope.conf.pagesLength = parseInt(scope.conf.pagesLength, 10) ? parseInt(scope.conf.pagesLength, 10) : 3;

				if (scope.conf.totalPage <= scope.conf.pagesLength) {
					// 判断总页数如果小于等于分页的长度，若小于则直接显示
					for (i = 1; i <= scope.conf.totalPage; i++) {
						scope.pageList.push(i);
					}
				} else {
					var offset = scope.conf.pagesLength - 1;
					if (scope.conf.currentPage <= offset) {
						// 从1开始
						for (i = 1; i <= offset + 1; i++) {
							scope.pageList.push(i);
						}
					}

					if (scope.conf.currentPage > offset && scope.conf.currentPage < scope.conf.totalPage) {
						// 两边都有
						scope.pageList.push(scope.conf.currentPage - 1);
						scope.pageList.push(scope.conf.currentPage);
						scope.pageList.push(scope.conf.currentPage + 1);
					}

					if (scope.conf.currentPage == scope.conf.totalPage) {
						scope.pageList.push(scope.conf.currentPage - 2);
						scope.pageList.push(scope.conf.currentPage - 1);
						scope.pageList.push(scope.conf.currentPage);
					}

					// 总页数大于分页长度（此时分为三种情况：1.左边没有...2.右边没有...3.左右都有...）
					// 计算中心偏移量
					//					var offset = (scope.conf.pagesLength - 1) / 2;
					//					if (scope.conf.currentPage <= offset) {
					//						// 左边没有...
					//						for (i = 1; i <= offset + 1; i++) {
					//							scope.pageList.push(i);
					//						}
					//						scope.pageList.push(scope.conf.totalPage);
					//					} else if (scope.conf.currentPage > scope.conf.totalPage - offset) {
					//						scope.pageList.push(1);
					//						for (i = offset + 1; i >= 1; i--) {
					//							scope.pageList.push(scope.conf.totalPage - i);
					//						}
					//						scope.pageList.push(scope.conf.totalPage);
					//					} else {
					//						// 最后一种情况，两边都有...
					//						scope.pageList.push(1);
					//
					//						for (i = Math.ceil(offset / 2); i >= 1; i--) {
					//							scope.pageList.push(scope.conf.currentPage - i);
					//						}
					//						scope.pageList.push(scope.conf.currentPage);
					//						for (i = 1; i <= offset / 2; i++) {
					//							scope.pageList.push(scope.conf.currentPage + i);
					//						}
					//
					//						scope.pageList.push(scope.conf.totalPage);
					//					}
				}

				if (scope.conf.onChange) {
					scope.conf.onChange();
				}
				scope.$parent.conf = scope.conf;
			}

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

			scope.$watch(function() {
				var newValue = scope.conf.currentPage + ' ' + scope.conf.totalItems + ' ';
				// 如果直接watch perPage变化的时候，因为记住功能的原因，所以一开始可能调用两次。
				//所以用了如下方式处理
				if (scope.conf.rememberPerPage) {
					// 由于记住的时候需要特别处理一下，不然可能造成反复请求
					// 之所以不监控localStorage[scope.conf.rememberPerPage]是因为在删除的时候会undefind
					// 然后又一次请求
					if (localStorage[scope.conf.rememberPerPage]) {
						newValue += localStorage[scope.conf.rememberPerPage];
					} else {
						newValue += scope.conf.itemsPerPage;
					}
				} else {
					newValue += scope.conf.itemsPerPage;
				}
				return newValue;

			}, getPagination);

		}
	};
}]);