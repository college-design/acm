var app = angular.module("hh", []);
app.controller('ProblemCtrl', function($scope, $http) {
	// $scope.count = 0;
	// if (!location.hash) {
	// 	location.hash = "#/problem/list/1/20"
	// }
    //
	// var getData = function(str) {
	// 	var url = str.substring(str.indexOf("#/") + 1);
	// 	$http.get("json" + url).success(function(data) {
	// 		$scope.data = data;
	// 	});
	// 	$scope.count++;
	// }
    //
	// $("a").bind("click", function(e) {
	// 	getData(this.href);
	// });

});

(function() {

	var resColor = {
		0 : "text-success",
		2 : "text-warning",
		3 : "text-warning",
		4 : "text-error",
		7 : "text-info",
	};
	var lineColor = {
		0 : "success",
		2 : "warning",
		3 : "warning",
		4 : "error",
		7 : "info",
	};
	var resTypes = {
		0 : "Accepted",
		1 : "Rejudge",
		2 : "TimeLimitExceed",
		3 : "PE",
		4 : "WrongAnswer",
		5 : "RuntimeError",
		6 : "OutputLimitExceed",
		7 : "CompileError",
		8 : "RF",
		98 : "SystemError",
		99 : "OTHER",
		100 : "RUN",
		1000 : "WAITING"
	};
	$("span[status-res]").each(function() {
		var res = $(this);
		res.text(resTypes[res.attr("status-res")]);
		res.addClass(resColor[res.attr("status-res")]);
		res.css("font-weight", "bolder");
	});
	$("table").find("tr[status-res]").each(function() {
		var res = $(this);
		res.addClass(lineColor[res.attr("status-res")]);
	});

})();
