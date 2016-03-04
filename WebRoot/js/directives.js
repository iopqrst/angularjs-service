'use strict';

/* Directives */

var clientDirectives = angular.module('ClientDirectives', []);

clientDirectives.directive('pager', function() {
	return {
		replace: true,
		restrict: 'A',
		scope: {
			page:'=pageObject'
		},
		controller: function($scope, $element) {
			console.info ($scope.page);
		},
		link: function(scope, element, attrs, someController) {
			console.info (scope.page);
		}
	};
});