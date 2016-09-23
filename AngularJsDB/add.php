<?php

if(isset($_POST['post']))
{
    $text = $_POST['post'];
    file_put_contents("data.json", $text);
    echo "New plant Added Successfully";
}
else if(isset($_POST['del']))
{
    $text = $_POST['del'];
    file_put_contents("data.json", $text);
    echo "Plant successfully deleted";
}
else
{
    echo "Failed";  
}

?>