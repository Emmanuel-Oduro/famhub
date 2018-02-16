<?php
session_start();
require_once '../pages/../config.php';
$ipclass = new ip();
//echo $ipclass->getIp();
$ip = $ipclass->getIp();
$value1 = filter_input(INPUT_GET, 'value1i', FILTER_SANITIZE_STRING);
$value = filter_input(INPUT_GET, 'value', FILTER_SANITIZE_STRING);

$valueindex = filter_input(INPUT_GET, 'value', FILTER_SANITIZE_STRING);
//if (isset($value)) {
//    if (isset($_SESSION["sexion"])) {
//        header("location:productadd.php");
//    } else {
//        header("location:logreg.php?value=Login&addpro=Please login to add product");
//    }
//}
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <?php
        echo "<title>FARMiCONN || Full details</title>";
        ?>
        <link rel="stylesheet" href="../pages/../css/main.css">
        <link rel="stylesheet" href="../pages/../css/font-awesome.css">
        <link rel="stylesheet" href="../pages/../css/font-awesome.min.css">

        <link rel="shortcut icon" href="" type="image/x-icon">
    </head>
    <body>
        <div class="header">
            <div class="logo">
                <div class="logosection">

                    <img src="../pages/../media/images/gabriel-jimenez-241711.jpg" alt="logo">
                    <div class="line"></div>

                    <div class="tit">

                        <h3>FARMiCONN</h3>
                        <p>best in farming</p>
                    </div>

                    <div class="clearfix"></div>

                </div>
                <div class="mainnav">
                    <?php
                    if (isset($_SESSION["sexion"])) {
                        ?>
                        <nav>
                            <ul>
                                <li><i class="fa fa-sign-in" aria-hidden="true"></i>
                                    <a href="../pages/../index.php">Home</a></li>
                                <li>

                                    <a href="?value=Login&addpro=Please login to add product">Add Product</a></li>

                                <li class="button-dropdown"><i class="fa fa-user" aria-hidden="true"></i>
                                    <a href="javascript:void(0)" class="dropdown-toggle"><?php echo $_SESSION["sexion"]; ?><span>▼</span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="manageaccount.php">Manage Account</a></li>

                                        <li><hr></li>
                                        <li><a href="../pages/../logout.php">Log Out</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>

                        <?php
                    } else {
                        ?>

                        <nav>
                            <ul>
                                <li><i class="fa fa-sign-in" aria-hidden="true"></i>
                                    <a href="../pages/../index.php">Home</a></li>
                                <li><i class="fa fa-sign-in" aria-hidden="true"></i>
                                    <a href="logreg.php?value=Login">Login</a></li>
                                <li><i class="fa fa-registered" aria-hidden="true"></i>
                                    <a href="logreg.php?value=Register">Register</a></li>
                                <li><i class="fa fa-product-hunt" aria-hidden="true"></i>
                                    <a href="logreg.php?value=Login&addpro=Please login to add product">Add Product</a></li>
                            </ul>
                        </nav>

                        <?php
                    }
                    ?>

                </div>
                <div class="clearfix"></div>
            </div>
            <div class="container">
                <?php
                $curl = curl_init();

                curl_setopt_array($curl, array(
                    CURLOPT_PORT => "8080",
                    CURLOPT_URL => "$ip/product/category?category=$value1",
                    CURLOPT_RETURNTRANSFER => true,
                    CURLOPT_ENCODING => "",
                    CURLOPT_MAXREDIRS => 10,
                    CURLOPT_TIMEOUT => 30,
                    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                    CURLOPT_CUSTOMREQUEST => "GET",
                    CURLOPT_HTTPHEADER => array(
                        "cache-control: no-cache",
                        "content-type: application/json"
                    ),
                ));

                $response = curl_exec($curl);
                $err = curl_error($curl);

                curl_close($curl);

                if ($err) {
                    echo "cURL Error #:" . $err;
                } else {
                    // echo $response;
                    $ui = json_decode($response, TRUE);
                    $uii = $ui['result'][$valueindex];
                    $val = json_encode($uii);
                    $outcome = json_decode($val, TRUE);
                    $farmername = $outcome['farmer']['name'];
                    $farmeremail = $outcome['farmer']['email'];
                    $prodname = $outcome['productName'];
                    $price = $outcome['price'];
                    $farmerphone = $outcome['farmer']['phoneNumber'];
                    $locat = $outcome['location'];
                    $cat = $outcome['category'];
                    $vid = $ip . $outcome['video'];
                    $aud = $ip . $outcome['audio'];
                    $date = $outcome['dateCreated'];
                    $des = $outcome['description'];
                    echo"<div class='values detailsimg'><img src='" . $ip . $outcome['image'] . "'></div>";
                    echo " <div class='values farmernproductinfo'>";
                    echo"<div class='farmer'><h3>Farmer Details</h3>"
                    . "<ul><li><i class='fa fa-tag' aria-hidden='true'></i>Farmer Name: <h4>$farmername </h4></li>
<li><i class='fa fa-envelope' aria-hidden='true'></i>Email: <h5> $farmeremail</h5></li>
<li><i class='fa fa-phone' aria-hidden='true'></i>Phone Number: $farmerphone</li>
</ul><span><a href='mailto:$farmeremail'>Send Mail</a></span><span><a href='tel:$farmerphone'> Call $farmername</a></span>

        </div>




      <div class='farmerprod'><h3>Product Details</h3>
        <ul><li><i class='fa fa-tag' aria-hidden='true'></i>Product Name: <h4>$prodname </h4></li>
        <li><i class='fa fa-cart-plus' aria-hidden='true'></i>Category: <h5> $cat</h5></li>
        <li><i class='fa fa-money' aria-hidden='true'></i>Price: $price</li>
        <li><i class='fa fa-text-width' aria-hidden='true'></i>Description: $des</li>
        <li><i class='fa fa-times' aria-hidden='true'></i>Date Added: $date</li>
        </ul>

                </div>
        <div class='clearfix'></div>
    </div>";
                    echo "<div class='values medias'>
    <div class='aud'>
        <h3>Audio</h3>
        <audio controls>
  <source src='$aud' type='audio/3gp'>
  <source src='$aud' type='audio/3gp'>
  <source src='$aud' type='audio/mp3'>

Your browser does not support the audio element.
</audio>

    </div>  <div class='vid'>
        <video  controls>
  <source src='$vid' type='video/mp4'>

  Your browser does not support HTML5 video.
</video>

    </div>

    <div class='clearfix'></div>
</div>";
                }
                ?>

            </div>













            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
            <script src="../pages/../js/dropdown.js"type="text/javascript"></script>
        </div>
    </body>
</html>
