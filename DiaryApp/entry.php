    <!doctype html>
    <html>
    <body>

        <head><link rel="stylesheet" type="text/css" href="style.css"</head>



        <?php
            
         //This will create the database if it does not exist when any button has been pressed on MainPage.php   

        $servername = "localhost";
        $username = "Corentin";
        $password = "123";
        $dbname = "myDB";

        //Methods for database MySQL1



            // Create connection
            $conn = new mysqli($servername, $username, $password);
            // Check connection
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }

            // Create database
            $sql = "CREATE DATABASE myDB";
            if ($conn->query($sql) === TRUE) {
                echo "";
            } else {
                echo "";
            }

            $conn->close();


        // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);
        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        // sql to create table
        $sql = "CREATE TABLE diary (
            whenwhere VARCHAR(100) NOT NULL,
            event VARCHAR(100) NOT NULL,
            emotion VARCHAR(100) NOT NULL,
            auto VARCHAR(100) NOT NULL,
            response VARCHAR(100) NOT NULL)";
        if ($conn->query($sql) === TRUE) {
            echo "";
        } else {
            echo "";
        }

        $conn->close();
            
            //These are the default values for the "Show Last" button if no entries have been saved.

            $a = "No entries saved";
            $b = "No entries saved";
            $c = "No entries saved";
            $d = "No entries saved";
            $e = "No entries saved";

        if(isset($_POST['show']))
        {

                    // Create connection
                    $conn = new mysqli($servername, $username, $password, $dbname);
                    // Check connection
                    if ($conn->connect_error) {
                      die("Connection failed: " . $conn->connect_error);
                    }

                    $sql = "SELECT whenwhere, event, emotion, auto, response FROM diary";
                    $result = $conn->query($sql);

                    if ($result->num_rows > 0) {
                    // output data of each row
                    while($row = $result->fetch_assoc()) {
                        $a = $row['whenwhere'];
                        $b = $row['event'];
                        $c = $row['emotion'];
                        $d = $row['auto'];
                        $e = $row['response'];
                    }
                } 
                //echo $a . $b . $c . $d . $e;
                echo "<div id='main'>";
                echo "<h1>Last Entry</h1>";
                echo "<div>When/Where:</div>";
                echo '<textarea type="text" rows="5" cols="40">' . $a . "</textarea><br>";
                echo "<div>Event:</div>";
                echo '<textarea type="text" rows="5" cols="40">' . $b . "</textarea><br>";
                echo "<div>Emotion:</div>";
                echo '<textarea type="text" rows="5" cols="40">' . $c . "</textarea><br>";
                echo "<div>Automatic thoughts:</div>";
                echo '<textarea type="text" rows="5" cols="40">' . $d . "</textarea><br>";
                echo "<div>Rational response:</div>";
                echo '<textarea type="text" rows="5" cols="40">' . $e . "</textarea><br>";
                echo '<a href="MainPage.php">back to blank diary</a>';

                $conn->close();

        }
        else if(isset($_POST['save']))
        {  
            
        //Code once Submitted
        $a = $_POST['when'];
        $b = $_POST['event'];
        $c = $_POST['emotion'];
        $d = $_POST['auto'];
        $e = $_POST['response'];    

        // Create connection
        $conn = mysqli_connect($servername, $username, $password, $dbname);
        // Check connection
        if (!$conn) {
            die("Connection failed: " . mysqli_connect_error());
        }

        $sql = "INSERT INTO diary (whenwhere, event, emotion, auto, response)
        VALUES ('" . $a ."', '" . $b . "', '" . $c ."', '" . $d . "', '" . $e . "')";

        if (mysqli_query($conn, $sql)) {
            echo "New entry successfully saved";
        } else {
            echo "Error: " . $sql . "<br>" . mysqli_error($conn);
        }

        mysqli_close($conn);
        echo '<a href="MainPage.php">back to blank diary</a>';
        }
        else if(isset($_POST['all']))
        {

            // Create connection
            $conn = new mysqli($servername, $username, $password, $dbname);
            // Check connection
            if ($conn->connect_error) {
                 die("Connection failed: " . $conn->connect_error);
            }

            $sql = "SELECT whenwhere, event, emotion, auto, response FROM diary";
            $result = $conn->query($sql);

            echo "<table style='border-collapse: collapse'>";
            echo "<tr>";
            echo "<td id='title'>When/Where</td>";
            echo "<td id='title'>Event</td>";
            echo "<td id='title'>Emotion</td>";
            echo "<td id='title'>Automatic thoughts</td>";
            echo "<td id='title'>Rational response</td>";
            echo "</tr>";

            if ($result->num_rows > 0) {
                 // output data of each row
                 while($row = $result->fetch_assoc()) {

                       echo "<tr>";
                       echo "<td>". $row['whenwhere'] . "</td>";
                       echo "<td>". $row['event'] . "</td>";
                       echo "<td>". $row['emotion'] . "</td>";
                       echo "<td>". $row['auto'] . "</td>";
                       echo "<td>". $row['response'] . "</td>";
                       echo "</tr>";
                 }
            } else {
                 echo "No entries saved.";
            }
            echo "</table>";
            echo '<a id="link" href="MainPage.php">back to blank diary</a>';
        }
        else if(isset($_POST['delete']))
        {
            // Create connection
            $conn = new mysqli($servername, $username, $password, $dbname);
            // Check connection
            if ($conn->connect_error) {
                 die("Connection failed: " . $conn->connect_error);
            }

            $sql = "DELETE FROM diary";
            $result = $conn->query($sql);
            echo 'All entries have been deleted';
            echo '<a href="MainPage.php">back to blank diary</a>';
        }

        ?>

    </body>
    </html>
