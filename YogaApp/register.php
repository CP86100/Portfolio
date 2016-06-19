<html>
    
    <body>
        
        <head>

        </head>
        
    </body>

</html>
<?php

$username = $_POST['fname'];
$password = $_POST['fpassword'];

$text = file_get_contents("Accounts.json");
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
if($done == true)
{
    echo "<script> alert('Sorry this username is already taken.'); window.location.replace('Main.php'); </script>";
}
else
{
    $len = sizeOf($text);
 
    $text = substr($text,0,$len-3);
    $text = $text.',{"name":"'.$username.'","password":"'.$password.'"} ]}';

    file_put_contents('Accounts.json', $text); 
    echo "<script> alert('Registration Successful'); window.location.replace('QuizPage.html'); </script>";
}

?>
