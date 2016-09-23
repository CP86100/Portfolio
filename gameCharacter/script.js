$(document).ready(function(){
   
    /**
    * The code needs to be improved.
    */
    
    //Game music looping for ever.
    var gameTune = new Audio('./sound/gameTune.mp3');
    gameTune.addEventListener('ended',function(){
        gameTune.play();
    }, false);
    gameTune.play();
    
    //images representing the character.
    var images = ['normLeft.png','breathLeft.png','walkLeft1.png','walkLeft2.png','normRight.png','breathRight.png','walkRight1.png','walkRight2.png','normUp.png','breathUp.png','walkUp1.png','walkUp2.png','normDown.png','breathDown.png','walkDown1.png','walkDown2.png'];
    //index pointing to the current image being displayed.
    var index = 4;
    
    //interval for the character's breathing when at rest.
    var breathInterval = setInterval(breath, 500);
    
    //Breating interval at work and character's movements.
    var keydown = false;
    var previouskey = 0;
    $(document).keydown(function(key){
        
        if(!keydown){
            keydown = true;
            previouskey = key.which;
            setTimeout(function(){
                keydown = false;
            },150);
        }
        else if(previouskey != key.which){
            keydown = false;
        }
        else{
            return false;
        }
        
        clearInterval(breathInterval);
        
        gameInput(key);

        breathInterval = setInterval(breath, 500);


    });

    //function taking the game's input and making movements.
    function gameInput(key){
        
        switch(parseInt(key.which,10)){
                
            //Right Arrow pressed go right
            case 39:
                if(index != 6 && index != 7)
                    index = 6;
                
                if(index == 6){
                    index = 7;
                }
                else{
                    index = 6;
                }
                $('#character').attr('src','./character/'+images[index]).animate({left: '+=10'}, 0);
                break;
            //Left Arrow pressed go left
            case 37:
                if(index != 2 && index != 3)
                    index = 2;
                
                if(index == 2){
                    index = 3;
                }
                else{
                    index = 2;
                }
                $('#character').attr('src','./character/'+images[index]).animate({left: '-=10'}, 0);
                break;
            //Up Arrow pressed go up
            case 38:
                if(index != 10 && index != 11)
                    index = 10;
                
                if(index == 10){
                    index = 11;
                }
                else{
                    index = 10;
                }
                $('#character').attr('src','./character/'+images[index]).animate({top: '-=10'}, 0);
                break;
            //Down Arrow pressed go down
            case 40:
                if(index != 14 && index != 15)
                    index = 14;
                
                if(index == 14){
                    index = 15;
                }
                else{
                    index = 14;
                }
                $('#character').attr('src','./character/'+images[index]).animate({top: '+=10'}, 0);
                break;
        }
    }
    
    function breath(){
        
        switch(index){
            case 0: index = 1; break;
            case 1: index = 0; break;
            case 4: index = 5; break;
            case 5: index = 4; break;
            case 8: index = 9; break;
            case 9: index = 8; break;
            case 12: index = 13; break;
            case 13: index = 12; break;
            case 2: index = 0; break;
            case 3: index = 0; break;
            case 6: index = 4; break;
            case 7: index = 4; break;
            case 10: index = 8; break;
            case 11: index = 8; break;
            case 14: index = 12; break;
            case 15: index = 12; break;
        }
        $('#character').attr('src','./character/'+images[index]);
    }
});
