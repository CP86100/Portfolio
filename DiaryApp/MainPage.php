<!DOCTYPE html>
    
    <html>
    
        <body>
            <head><link rel="stylesheet" type="text/css" href="style.css"></head>
            
            
            <script>
            //This methd will check which button has been pressed then directed to entry.php which in turn will perform the demanded task.
            //This will check if all the textareas in the form have been filled if you have clicked on the save entry button.
            //If every text area is filled it will allow the form to go to entry.php
            //Else you will stay on MainPage.php and be notified which textareas have not been filled.
            
            checkform = function()
            {
                var a = document.forms["form"]["when"].value;
                var b = document.forms["form"]["event"].value;
                var c = document.forms["form"]["emotion"].value;
                var d = document.forms["form"]["auto"].value;
                var e = document.forms["form"]["response"].value;
                
                var x = document.getElementById("temp").innerHTML;
                
                var text = "You must fill in the following textarea(s):\n";
                var check = text;
                
                if(x.localeCompare("save") === 0)
                    {
                        if(a == null || a == "")
                            text += '- When/Where\n';
                        if(b == null || b == "")
                            text += '- Event\n';
                        if(c == null || c == "")
                            text += '- Emotion\n';
                        if(d == null || d == "")
                            text += '- Automatic thoughts\n';
                        if(e == null || e == "")
                            text += '- Rational response\n';
                        if(text !== check)
                            {
                                alert(text);
                                return false;
                            }
                            
                    }
                if(x.localeCompare("delete") === 0)
                {
                    var check = confirm("Are you sure you want to delete all the diary entries?");
                    if(check == false)
                    {
                        return false;
                    }
                }
                
            }
            setsave = function()
            {
                document.getElementById("temp").innerHTML = "save";
            }
            setshow = function()
            {
                document.getElementById("temp").innerHTML = "show";
            }
            setall = function()
            {
                document.getElementById("temp").innerHTML = "all";
            }
            deleteall = function()
            {
                document.getElementById("temp").innerHTML = "delete";
            }
            </script>
        <div id="main">
            <h1>Blank Diary</h1>
        <form action="entry.php" method="post" onsubmit="return checkform()" name="form">
            <div>When/Where:<br></div>
            <textarea name="when" rows="5" cols="40"></textarea><br>
            <div>Event:<br></div>
            <textarea name="event" rows="5" cols="40"></textarea><br>
            <div>Emotion:<br></div>
            <textarea name="emotion" rows="5" cols="40"></textarea><br>
            <div>Automatic thoughts:<br></div>
            <textarea name="auto" rows="5" cols="40"></textarea><br>
            <div>Rational response:<br></div>
            <textarea name="response" rows="5" cols="40"></textarea><br>
            
            <input type="submit" value="Save Entry" name="save" onclick="setsave()">
            <input type="submit" value="Show Last" name="show" onclick="setshow()">
            <input type="submit" value="Show All" name="all" onclick="setall()">
            <input type="submit" value="Delete All" name="delete" onclick="deleteall()">
            </form>
            <p hidden id="temp"></p>
            </div>
            
        </body>
    
    
    </html>
