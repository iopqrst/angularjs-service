'use strict';

/* App Module */
var xtggApp = angular.module('xtggApp', ['ngRoute', 'ClientControllers', 'ClientFilters', 'MyClientServices', 'ClientDirectives']);

xtggApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
		when('/client/list', {
			templateUrl: 'partials/client_list.html',
			controller: 'ClientListController'
		}).
		when('/client/unassign-list', {
			templateUrl: 'partials/client_unassign_list.html',
			controller: 'ClientUnassignListController'
		}).
		otherwise({
			redirectTo: '/client/list'
		});
	}
]);