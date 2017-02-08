/**
 * Created by Alessandro Lia Fook Santos on 25/11/2016.
 */

var app = angular.module('myApp', []);

app.controller('controller', function ($scope, $http) {

    $scope.taskName = "";
    $scope.tasks = new Array();

    $scope.listTitle = "";
    $scope.taskLists = new Array();

    $scope.addTaskList = function () {
        $scope.taskLists.push($scope.listTitle);
        console.log($scope.taskLists);
    }


    $scope.testPost = function(){
        $http({
            method:"POST",
            url:"/task"
        }).then(function (data) {
            console.log(JSON.stringify(data));
        });
    };


    $scope.testGet = function(){
        $http({
            method:"GET",
            url:"/task"
        }).then(function (data) {
            console.log(JSON.stringify(data));
        });
    };

    //base object of the system
    var task = function (taskName) {
        this.titleName = taskName;
        this.isDone = false;
        this.priority = "warning";
    };

    //creating start tasks and add to list to accomplish design request

    //first task
    var wakeUp = new task("Wake Up");
    $scope.tasks.push(wakeUp);

    //second task
    var breakfast = new task("Breakfast");
    $scope.tasks.push(breakfast);

    //second task
    var sleep = new task("Sleep");

    $scope.tasks.push(sleep);

    // functions that manipulate the tasks
    $scope.addTask = function () {

        if (!containTaskOnTaskList($scope.taskName)) {

            $scope.tasks.push(new task($scope.taskName));
            $scope.updateDonePercentage();
        }
    };

    function containTaskOnTaskList(taskName) {

        for (var index = 0; index < $scope.tasks.length; index++) {

            var taskTitle = $scope.tasks[index].titleName;
            if (taskTitle === taskName) {return true;}
        }
        return false;
    };

    $scope.deleteTask = function (index) {

        var task = $scope.tasks[index];
        task.isDone = false;

        $scope.updateConcludedList(index);
        $scope.tasks.splice(index, 1);
        $scope.updateDonePercentage();
    };

    $scope.removeDoneTasks = function () {
        var index = $scope.tasks.length;
        while(--index >= 0){
            var task = $scope.tasks[index];
            if (task.isDone === true) {$scope.deleteTask(index)}
        }
    };

    //Auxiliary array that stores done tasks
    $scope.concludedList = new Array();
    $scope.donePercentage = 0;

    $scope.updateConcludedList = function (index) {

        var task = $scope.tasks[index];
        var taskIndex = $scope.concludedList.indexOf(task);

        if(task.isDone === true &&  taskIndex < 0) {
            $scope.concludedList.push(task);
            $scope.tasks[index].priority = "success";
        }

        if(task.isDone === false && taskIndex >= 0) {
            $scope.concludedList.splice(taskIndex, 1);
            $scope.tasks[index].priority = "warning";
        }
        $scope.updateDonePercentage();
    };

    $scope.updateDonePercentage = function () {

        $scope.donePercentage = Math.floor(($scope.concludedList.length / $scope.tasks.length) * 100);
        var undone = 100 - $scope.donePercentage;

        document.getElementById('done-bar').style.width = $scope.donePercentage+"%";
        document.getElementById('undone-bar').style.width = undone+"%";
    };
});