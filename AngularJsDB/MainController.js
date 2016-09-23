app.controller('MainController',['$scope','$http', function($scope,$http){
    var vm = this;
    
    $scope.title = 'Plant Database';
    $scope.plants = [];
    
    $http.get('data.json').then(function(data){
        $scope.plants = data.data;
        $scope.currentId = $scope.plants[$scope.plants.length-1]['id']+1;
    });
    
    $scope.searchPlant = '';
    $scope.sortBy = '';

    $scope.new = {};
    
    $scope.toBeDeleted = '';
    $scope.toModify = {};
    
    //This function updates the JSON file.
    $scope.addPlant = function(){
        
    $scope.new.id = $scope.currentId;
        
    $scope.plants.push($scope.new);
    $scope.new = {};

    var newjson = angular.toJson($scope.plants);
    $.ajax({

        url: 'add.php',
        type: 'post',
        data: {post: newjson},
        dataType: 'text',
        success: function (res){alert(res)}

        });
        
    };
    $scope.removeData = function(){
        var key;
        
        for(var i = 0; i < $scope.plants.length; i++){
            if($scope.toBeDeleted.toLowerCase().localeCompare($scope.plants[i]['nomFr'].toLowerCase()) == 0){
                var conf = confirm('Appuyer sur OK pour supprimer '+$scope.toBeDeleted);
                if(conf == true){
                    key = i;
                }
                break;
            }
        }
        var array = [];
        for(var i = 0; i < $scope.plants.length; i++){
            if(i != key){
                array.push($scope.plants[i]);
            }
        }
        $scope.plants = array;
        var newjson = angular.toJson(array);
        $.ajax({

        url: 'add.php',
        type: 'post',
        data: {del: newjson},
        dataType: 'text',
        success: function (res){alert(res)}
   
        });
        
    };
    $scope.modify = function(){
        var key;
        
        for(var i = 0; i < $scope.plants.length; i++){
            if($scope.toBeDeleted.toLowerCase().localeCompare($scope.plants[i]['nomFr'].toLowerCase()) == 0){
                key = i;
                break;
            }
        }
        $scope.toModify = $scope.plants[i];
        
        if($scope.toModify['image'] != null){
            document.getElementById('image').value = $scope.toModify['image'];
        }
        if($scope.toModify['nomFr'] != null){
            document.getElementById('nomFr').value = $scope.toModify['nomFr'];
        }
        if($scope.toModify['nomEn'] != null){
            document.getElementById('nomEn').value = $scope.toModify['nomEn'];
        }
        if($scope.toModify['nomRo'] != null){
            document.getElementById('nomRo').value = $scope.toModify['nomRo'];
        }
        if($scope.toModify['nomLt'] != null){
            document.getElementById('nomLt').value = $scope.toModify['nomLt'];
        }
        if($scope.toModify['famille'] != null){
            document.getElementById('famille').value = $scope.toModify['famille'];
        }
        if($scope.toModify['groupe'] != null){
            document.getElementById('groupe').value = $scope.toModify['groupe'];
        }
        if($scope.toModify['liens'] != null){
            document.getElementById('liens').value = $scope.toModify['liens'];
        }
    };
    
     $scope.modifytwo = function(){
         var key;
         for(var i = 0; i < $scope.plants.length; i++){
            if($scope.toBeDeleted.toLowerCase().localeCompare($scope.plants[i]['nomFr'].toLowerCase()) == 0){
                key = i;
                break;
            }
        }
         
         var array = [];
        for(var i = 0; i < $scope.plants.length; i++){
            if(i != key){
                array.push($scope.plants[i]);
            }
        }
         $scope.toModify['image'] = document.getElementById('image').value; 
         $scope.toModify['nomFr'] = document.getElementById('nomFr').value;
         $scope.toModify['nomEn'] = document.getElementById('nomEn').value;
         $scope.toModify['nomRo'] = document.getElementById('nomRo').value;
         $scope.toModify['nomLt'] = document.getElementById('nomLt').value;
         $scope.toModify['famille'] = document.getElementById('famille').value;
         $scope.toModify['groupe'] = document.getElementById('groupe').value;
         $scope.toModify['liens'] = document.getElementById('liens').value;
         
         
         document.getElementById('image').value = ''; 
         document.getElementById('nomFr').value = '';
         document.getElementById('nomEn').value = '';
         document.getElementById('nomRo').value = '';
         document.getElementById('nomLt').value = '';
         document.getElementById('famille').value = '';
         document.getElementById('groupe').value = '';
         document.getElementById('liens').value = '';
         
         
         $scope.toModify.id = $scope.currentId;
         
         array.push($scope.toModify);
         $scope.toModify = {};
         
        var newjson = angular.toJson(array);
        $.ajax({

        url: 'add.php',
        type: 'post',
        data: {post: newjson},
        dataType: 'text',
        success: function (res){alert('Successfully modified')}

        });      
    };
    
}]);