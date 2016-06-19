<html>
    
    <body>
        
        <head>
        <link rel="stylesheet" type="text/css" href="style.css">
            <div id="main"></div>
        </head>
        
    </body>

</html>

<?php

$username = $_POST['fname'];
$password = $_POST['fpassword'];

$data = json_decode(file_get_contents("Accounts.json"),true);

$json = $data['Accounts'];

$length = sizeOf($json);

$i = 0;
$done = FALSE;
while($i < $length && $done == FALSE)
{
    if(strcmp($json[$i]['name'],$username) == 0)
        $done = TRUE;
    $i = $i+1;
}
if($done == TRUE)
{
    $i = $i-1;
    if(strcmp($json[$i]['password'],$password) == 0)
    {
        echo "<script> alert('Login Successful'); window.location.replace('YogaApp1.html'); </script>";
    }
    else
        echo "<script> alert('Login failed: Wrong password'); window.location.replace('YogaAppLogin.php'); </script>";
}
else
{
    echo "<script> alert('Login failed: User not found'); window.location.replace('YogaAppLogin.php'); </script>";
}




?>