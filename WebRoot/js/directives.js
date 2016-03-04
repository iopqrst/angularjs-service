'use strict';

/* Directives */

var clientDirectives = angular.module('ClientDirectives', []);

clientDirectives.directive('pager', function() {
	return {
		replace: true,
		restrict: 'A',
		scope: {
			pageinfo:'=pageObject'
		},
		controller: function($scope, $element) {
			console.info ($scope.pageinfo);
		},
		link: function(scope, element, attrs, someController) {
			console.info (scope.pageinfo);
			
		}
	};
});