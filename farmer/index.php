<?php
session_start();
require_once 'config.php';

$ipclass = new ip();
//echo $ipclass->getIp();
$ip = $ipclass->getIp() . "/home";
$ip2 = $ipclass->getIp();

//$_SESSION['sexion'] = "nana@nana.com";
$value = filter_input(INPUT_GET, 'value', FILTER_SANITIZE_STRING);
$valueproduct = filter_input(INPUT_GET, 'addpro', FILTER_SANITIZE_STRING);


if (isset($value) || isset($valueproduct)) {
    if (isset($_SESSION["sexion"])) {
        header("location:pages/productadd.php");
    } else {
        header("location:pages/logreg.php?value=Login&addpro=Please login to add product");
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
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/main.css" type="text/css">


    </head>
    <body>
        <div class="header">
            <div class="logo">
                <div class="logosection">

                    <img src="media/images/gabriel-jimenez-241711.jpg" alt="logo">
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
                                <li>

                                    <a href="?value=Login&addpro=Please login to add product">Add Product</a></li>

                                <li class="button-dropdown"><i class="fa fa-user" aria-hidden="true"></i>
                                    <a href="javascript:void(0)" class="dropdown-toggle"><?php echo $_SESSION["sexion"]; ?><span>▼</span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="pages/manageaccount.php">Manage Account</a></li>
                                        <li><hr></li>
                                        <li><a href="logout.php">Log Out</a></li>
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
                                    <a href="pages/logreg.php?value=Login">Login</a></li>
                                <li><i class="fa fa-registered" aria-hidden="true"></i>
                                    <a href="pages/logreg.php?value=Register">Register</a></li>
                                <li><i class="fa fa-product-hunt" aria-hidden="true"></i>
                                    <a href="?value=Login&addpro=Please login to add product">Add Product</a></li>
                            </ul>
                        </nav>

                        <?php
                    }
                    ?>

                </div>
                <div class="clearfix"></div>
            </div>

        </div>

        <?php
        if (isset($_SESSION["sexion"])) {
            ?>



            <div class="container">
                <div class="spec ">
                    <h3>Available Categories</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>

                <div class="sap_tabs">
                    <div id="horizontalTab" >
                        <ul class="resp-tabs-list">
                            <a href="pages/itemlisview.php?value1=Tuber" >
                                <li class="resp-tab-item" role="tab"><div class="top-img"><img src="media/images/amarga.jpg" alt=""/></div><span>Tuber</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Fruit">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/allspice1.jpg" alt=""/></div><span>Fruit</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Vegetable">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/asas.jpg" alt=""/></div><span>Vegetable</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Cereal">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/hhgjf.jpg" alt=""/></div><span>Cereal</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Oils">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/howtad.jpg" alt=""/></div><span>Oils</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=LiveStock">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/index.jpg" alt=""/></div><span>LiveStock</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Fiber">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/tuber1.jpg" alt=""/></div><span>Fiber</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Timber">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/timx.jpg" alt=""/></div><span>Timber</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Spices">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/asas.jpg" alt=""/></div><span>Spices</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <div class="clearfix"></div>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="container">
                <div class="spec ">
                    <h3>Available Products</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>


                <div id="producttab" >
                    <ul class="resp-tabs-list">
                        <?php
                        $curl = curl_init();

                        curl_setopt_array($curl, array(
                            CURLOPT_PORT => "8080",
                            CURLOPT_URL => $ip,
                            CURLOPT_RETURNTRANSFER => true,
                            CURLOPT_ENCODING => "",
                            CURLOPT_MAXREDIRS => 10,
                            CURLOPT_TIMEOUT => 30,
                            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                            CURLOPT_CUSTOMREQUEST => "GET",
                            CURLOPT_HTTPHEADER => array(
                                "cache-control: no-cache"
                            ),
                        ));

                        $response = curl_exec($curl);
                        $err = curl_error($curl);

                        curl_close($curl);

                        if ($err) {
                            echo "cURL Error #:" . $err;
                        } else {

                            // echo $response;
                            $outcome = json_decode($response, TRUE);
                            if ($outcome['status'] == 1 && count($outcome['result'])) {
                                for ($i = 0; $i < count($outcome['result']); $i++) {

                                    $prodname = $outcome['result']["$i"]['productName'];
                                    $price = $outcome['result']["$i"]['price'];
                                    $locat = $outcome['result']["$i"]['location'];
                                    $cat = $outcome['result']["$i"]['category'];

                                    $date = $outcome['result']["$i"]['dateCreated'];

                                    echo "<li class='resp-tab-item' role='tab'>
<div class='top-img'>
<img src='" . $ip2 . $outcome['result'][$i]['image'] . "' alt='" . $outcome['result'][$i]['image'] . "'>
<i class='money'> <i class='fa fa-money' aria-hidden='true' > </i>" . $price . " </i>
</div><span><a href='pages/displayDetails.php?value=$i& value1i=" . $outcome['result']["$i"]['category'] . "'>"
                                    . "<h4><i class='fa fa-product-hunt' aria-hidden='true' > </i> " . $outcome['result'][$i]['productName'] . " </h4>
<i><i class='fa fa-times' aria-hidden='true' > </i>" . $date . "</i><p><i class='fa fa-map-marker' aria-hidden='true'></i>" . $outcome['result'][$i]['location'] . "</p>
</a></span></li>";
                                }
                            } else {
                                echo 'SORRY THERE ARE NO PRODUCTS AVAILABLE';
                            }
                        }
                        ?>



                        <div class="clearfix"></div>
                    </ul>

                </div>

            </div>



            <?php
        } else {
            ?>



            <div class="container">
                <div class="spec ">
                    <h3>Available Categories</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>

                <div class="sap_tabs">
                    <div id="horizontalTab" >
                        <ul class="resp-tabs-list">
                            <a href="pages/itemlisview.php?value1=Tuber" >
                                <li class="resp-tab-item" role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Tuber</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Fruit">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Fruit</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Vegetable">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Vegetable</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Cereal">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Cereal</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Oils">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Oils</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=LiveStock">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>LiveStock</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Fiber">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Fiber</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Timber">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Timber</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <a href="pages/itemlisview.php?value1=Spices">
                                <li class="resp-tab-item"  role="tab"><div class="top-img"><img src="media/images/p.png" alt=""/></div><span>Spices</span>
                                    <p>this is how it goes.</p>
                                </li>
                            </a>
                            <div class="clearfix"></div>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="container">
                <div class="spec ">
                    <h3>Available Products</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>


                <div id="producttab" >
                    <ul class="resp-tabs-list">
                        <?php
                        $curl = curl_init();

                        curl_setopt_array($curl, array(
                            CURLOPT_PORT => "8080",
                            CURLOPT_URL => $ip,
                            CURLOPT_RETURNTRANSFER => true,
                            CURLOPT_ENCODING => "",
                            CURLOPT_MAXREDIRS => 10,
                            CURLOPT_TIMEOUT => 30,
                            CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                            CURLOPT_CUSTOMREQUEST => "GET",
                            CURLOPT_HTTPHEADER => array(
                                "cache-control: no-cache"
                            ),
                        ));

                        $response = curl_exec($curl);
                        $err = curl_error($curl);

                        curl_close($curl);

                        if ($err) {
                            echo "cURL Error #:" . $err;
                        } else {
                            $arrayValues = array();
                            // echo $response;
                            $outcome = json_decode($response, TRUE);
                            if ($outcome['status'] == 1 && count($outcome['result'])) {
                                for ($i = 0; $i < count($outcome['result']); $i++) {
                                    $prodname = $outcome['result']["$i"]['productName'];
                                    $price = $outcome['result']["$i"]['price'];
                                    $locat = $outcome['result']["$i"]['location'];
                                    $cat = $outcome['result']["$i"]['category'];
                                    $img = $outcome['result']["$i"]['image'];
                                    $date = $outcome['result']["$i"]['dateCreated'];
                                    $farmername = $outcome['result']["$i"]['farmer']['name'];
                                    $farmeremail = $outcome['result']["$i"]['farmer']['email'];
                                    $farmerphone = $outcome['result']["$i"]['farmer']['phoneNumber'];
                                    $vid = $outcome['result']["$i"]['video'];
                                    $aud = $outcome['result']["$i"]['audio'];
                                    $des = $outcome['result']["$i"]['description'];

                                    array_push($arrayValues, $farmername);
                                    array_push($arrayValues, $farmeremail);
                                    array_push($arrayValues, $prodname);
                                    array_push($arrayValues, $price);
                                    array_push($arrayValues, $farmerphone);
                                    array_push($arrayValues, $locat);
                                    array_push($arrayValues, $ip2 . $aud);
                                    array_push($arrayValues, $ip2 . $vid);
                                    array_push($arrayValues, $date);
                                    array_push($arrayValues, $ip2 . $img);
                                    array_push($arrayValues, $des);
                                    $var = htmlspecialchars(serialize($arrayValues), ENT_QUOTES);
                                    echo "<li class='resp-tab-item' role='tab'>
<div class='top-img'>
<img src='" . $ip2 . $outcome['result'][$i]['image'] . "' alt='" . $outcome['result'][$i]['image'] . "'>
<i class='money'> <i class='fa fa-money' aria-hidden='true' > </i>" . $price . " </i>
</div><span>
<a href='pages/displayDetails.php?value=$i&value1i=" . $outcome['result']["$i"]['category'] . "'>"
                                    . "<h4><i class='fa fa-product-hunt' aria-hidden='true' > </i> " . $outcome['result'][$i]['productName'] . " </h4>
<i><i class='fa fa-times' aria-hidden='true' > </i>" . $date . "</i><p><i class='fa fa-map-marker' aria-hidden='true'></i>" . $outcome['result'][$i]['location'] . "</p>
</a></span></li>";
                                }
                            } else {
                                echo 'SORRY THERE ARE NO PRODUCTS AVAILABLE';
                            }
                        }





















//
//
//              $outcome= json_decode(file_get_contents($ip),TRUE);
//
//               if($outcome['status']==1 && count($outcome['result'])){
////             $outcome1=array_slice($outcome['result'], $start, $end);
////    $uiii=json_encode($outcome1);
// // $outcomez= json_decode($outcome,TRUE);
//  for ($i = 0; $i < count($outcome); $i++) {
//      echo "<li class='resp-tab-item' role='tab'>
//<div class='top-img'><img src='".$outcomez[$i]['image']."' alt='".$outcomez[$i]['image']."'/>
//<i class='money'> <i class='fa fa-money' aria-hidden='true' > </i>" .$outcomez[$i]['price'] ." </i>
//</div><span><a href='pages/displayDetails.php?value='".$outcomez[$i]['productName']."''><h4><i class='fa fa-product-hunt' aria-hidden='true' > </i> ".$outcomez[$i]['productName']." </h4>
//<i><i class='fa fa-times' aria-hidden='true' > </i>".$outcomez[$i]['dateCreated']."</i><p><i class='fa fa-map-marker' aria-hidden='true'></i>".$outcomez[$i]['location']."</p>
//</a></span></li>";
//
//  }
//  if($end >count($outcomez)){
//      $start =0; $end =12;
//      echo "<a href='?start=$start&end=$end'> << </a> <a href='?start=$start&end=$end' class='disable'> >></a>";
//  }
// else {
//     $start +=12; $end +=10;
//        echo "<a href='?start=$start-10&end=$end-10'> << </a> <a href='?start=$start&end=$end'> >></a>";
//
//  }
//
//               } else {
//                 echo 'SORRY THERE ARE NO PRODUCTS AVAILABLE';
//               }
                        ?>



                        <div class="clearfix"></div>
                    </ul>

                </div>

            </div>



            <?php
        }
        ?>
        <div class="footer">


        </div>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script src="js/dropdown.js"type="text/javascript"></script>
    </body>
</html>
