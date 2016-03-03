'use strict';

/* Filters */
var clientFilters = angular.module('ClientFilters', [])

clientFilters.filter('getAreaId', function() {
	return function(input) {
		if (input === 1) {
			return "北京";
		} else if (input === 2) {
			return "上海";
		} else {
			return "其他地区";
		}
	}
});

clientFilters.filter('getSource', function() {
	return function(input) {
		if (input === 'android') {
			return "安卓端";
		} else if (input === 2) {
			return "苹果端";
		} else {
			return "网页端";
		}
	}
});