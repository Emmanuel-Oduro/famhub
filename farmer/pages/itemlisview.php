
<?php
require_once '../pages/../config.php';
$value1 = filter_input(INPUT_GET, 'value1', FILTER_SANITIZE_STRING);
session_start();
$ipclass = new ip();
//echo $ipclass->getIp();
$ip = $ipclass->getIp();



$value = filter_input(INPUT_GET, 'value', FILTER_SANITIZE_STRING);
$valueproduct = filter_input(INPUT_GET, 'addpro', FILTER_SANITIZE_STRING);

if (isset($value) || isset($valueproduct)) {
    if (isset($_SESSION["sexion"])) {
        header("location:pages/productadd.php");
    } else {
        header("location:logreg.php?value=Login&addpro=Please login to add product");
    }
}
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
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

                                    <a href="logreg.php?value=Login&addpro=Please login to add product">Add Product</a></li>

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

        </div>
        <div class="container">
            <div class="content">

                <div class="sap_tabs">
                    <div id="veticalTab" >
                        <ul class="resp-tabs-list">
                            <li class="resp-tab-item" role="tab"><div class="top-img"><img src="../pages/../media/images/amarga.jpg" alt=""/></div><span><a href="?value1=Tuber" >Tuber</a></span></li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/allspice1.jpg" alt=""/></div><span><a href="?value1=Fruit">Fruit</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/asas.jpg" alt=""/></div><span><a href="?value1=Vegetable">Vegetable</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/hhgjf.jpg" alt=""/></div><span><a href="?value1=Cereal">Cereal</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/howtad.jpg" alt=""/></div><span><a href="?value1=Oils">Oils</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/index.jpg" alt=""/></div><span><a href="?value1=LiveStock">LiveStock</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/tuber1.jpg" alt=""/></div><span><a href="?value1=Fiber">Fiber</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/timx.jpg" alt=""/></div><span><a href="?value1=Timber">Timber</a></span>
                                <div class="clearfix"></div>
                            </li>
                            <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="../pages/../media/images/asas.jpg" alt=""/></div><span><a href="?value1=Spices">Spices</a></span>
                                <div class="clearfix"></div>
                            </li>

                            <div class="clearfix"></div>
                        </ul>

                    </div>
                </div>


            </div>
            <div class="content1">
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
//  echo $response;
    $outcome = json_decode($response, TRUE);
    if ($outcome['status'] == 1 && count($outcome['result'])) {
        for ($i = 0; $i < count($outcome['result']); $i++) {
            echo "<div class='mainitem'>
                <div class='itemimg'>
                    <img src='" . $ip . $outcome['result'][$i]['image'] . "' alt='" . $outcome['result'][$i]['image'] . "'>
                    <span class='price'><i class='fa fa-money' aria-hidden='true' ></i>" . $outcome['result'][$i]['price'] . "</span>
                </div>
                <div class='itemMsg'>
                <a href='displayDetails.php?value=$i&value1i=$value1'>
                    <h3><i class='fa fa-navicon' aria-hidden='true' ></i>" . $outcome['result'][$i]['productName'] . "</h3>
                </a>
                <p><i class='fa fa-location-arrow' aria-hidden='true' ></i> " . $outcome['result'][$i]['location'] . "</p>

                <span class='itemdate'><i class='fa fa-times-circle' aria-hidden='true' ></i>" . $outcome['result'][$i]['dateCreated'] . "</span>
               <div class='clearfix'></div>
                </div>

<div class='clearfix'></div>
            </div>";
        }
    } else {
        echo 'SORRY THERE ARE NO PRODUCTS AVAILABLE';
    }
}
?>


                <div class="clearfix"></div>
            </div>



            <div class="clearfix"></div>
        </div>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script src="../pages/../js/dropdown.js"type="text/javascript"></script>
    </body>
</html>