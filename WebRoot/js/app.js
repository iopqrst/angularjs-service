'use strict';

/* App Module */
var xtggApp = angular.module('xtggApp', ['ngRoute', 'ClientControllers', 'ClientFilters', 'MyClientServices', 'ClientDirectives']);

xtggApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
		when('/', {
				templateUrl: 'partials/client_list.html',
				controller: 'ClientListController'
			}).
			//		when('/phones/:phoneId', {
			//			templateUrl: 'partials/phone-detail.html',
			//			controller: 'MyPhoneDetailCtrl'
			//		}).
		otherwise({
			redirectTo: '/'
		});
	}
]);