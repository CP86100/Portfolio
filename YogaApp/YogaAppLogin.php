<!DOCTYPE html>
<html>
    
    <body>
        
        <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        </head>
        
        <div id="main"></div>
        
    </body>

<?php
      
    
?>
    <script>
        
    document.getElementById('main').innerHTML = '<h1>YOGA QUIZ CS230</h1><p id="text">With 20 yoga cards</p><button id="signup" type="button" onclick="signUp();">SIGN UP</button><button id="signin" type="button" onclick="signIn();">SIGN IN</button>';
    
    homePage = function()
    {
        document.getElementById('main').innerHTML = '<button id="signup" type="button" onclick="signUp();">SIGN UP</button><button id="signin" type="button" onclick="signIn();">SIGN IN</button>';
    }
    signUp = function()
    {
        document.getElementById('main').innerHTML = '<h1 id="sign">Sign Up</h1><form name="myForm1" action="register.php" onsubmit="return checkPassword()" method="post">Username:<br> <input id="box" type="text" name="fname" required><br><br>Passwrord:<br> <input id="box" type="password" name="fpassword" required><br><br>Confirm Password:<br> <input id="box" type="password" name="confpassword" required><br><br><input id="submit" type="submit" value="Submit"><button type="button" onclick="homePage();" id="back">BACK</button><br></form>';
    }
    signIn = function()
    {
        document.getElementById('main').innerHTML = '<h1 id="sign">Sign In</h1><form name="myForm2" action="login.php" onsubmit="setCookie(); return true;" method="post">Username:<br> <input id="box" type="text" name="fname" required><br><br>Password:<br> <input id="box" type="password" name="fpassword"required><br><br><input id="submit" type="submit" value="LOGIN"><button type="button" onclick="homePage();" id="back">BACK</button><br></form>';
        checkCookie();
   
    }
    setCookie = function()
    {
        document.cookie = document.forms['myForm2']['fname'].value+"; ";
    }
    checkCookie = function()
    {
        var cookie = document.cookie;
        if(cookie != null || cookie != "")
            {
                var data = cookie.split(";");
                document.forms['myForm2']['fname'].value = data[0];
                alert("Welcome "+data[0]);
            }
    }
    checkPassword = function()
    {
        var a = document.forms['myForm1']['fpassword'].value;
        var b = document.forms['myForm1']['confpassword'].value
        if(a.localeCompare(b) != 0)
        {
        alert("passwords do not match");
            return false;
        }
    }
    
    </script>

    