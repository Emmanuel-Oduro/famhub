<?php
session_start();
require_once '../pages/../config.php';
$ipclass = new ip();
//echo $ipclass->getIp();
$ip = $ipclass->getIp();
if (isset($_SESSION['sexion'])) {
    $MSG = "";
    $name = '';
    $MSG .= filter_input(INPUT_GET, 'message', FILTER_SANITIZE_STRING);
    $value1 = $_SESSION['sexion'];
    if (isset($value) || isset($valueproduct)) {
        if (isset($_SESSION["sexion"])) {
            header("location:productadd.php");
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
            <script src="../pages/../js/jquery.min.js" type="text/javascript"></script>
            <link rel="shortcut icon" href="" type="image/x-icon">
            <script type="application/x-javascript">
                addEventListener("load", function() {
                setTimeout(hideURLbar, 0);
                }, false);

                function hideURLbar() {
                window.scrollTo(0, 1);
                }

            </script>
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

                        <nav>
                            <ul>
                                <li><i class="fa fa-sign-in" aria-hidden="true"></i>
                                    <a href="../pages/../index.php">Home</a></li>
                                <li>

                                    <a href="productadd.php">Add Product</a></li>

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



                    </div>
                    <div class="clearfix"></div>
                </div>

            </div>
            <div class="container">
                <div class="navigate">
                    <nav>
                        <ul>
                            <li id="link1" class="link1"><a id="link1">Profile</a></li>
                            <li id="link2" class="link2"><a id="link2">Product History</a></li>
                            <li id="link3" class="link3"><a id="link3">Change Password</a></li>
                        </ul>
                    </nav>
                </div>
                <span class="msgs"><?php echo $MSG; ?></span>
            </div>
            <div class="forprofile" id="forprofile">
                <div class="spec ">
                    <h3>YOUR PROFILE</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>
                <?php
                //echo $value1."<br>";
                /* @var $curl type */
                $curl = curl_init();

                curl_setopt_array($curl, array(
                    CURLOPT_PORT => "8080",
                    CURLOPT_URL => $ip . "/user?email=$value1",
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
                    $outcome = json_decode($response, TRUE);
                    if ($outcome['status'] == 1 && count($outcome['result'])) {
                        //echo $outcome['result'];
                        ?>
                        <form action="operator/operations.php" method="post" >
                            <input type="hidden" name="updatevalue" value="updateprofile">

                            <div>
                                <i class="fa fa-user-circle" aria-hidden="true" > </i>
                                <input type="text" name="updatename" id="name" value="<?php echo $outcome['result']['name']; ?>">
                                <i class="fa fa-eyedropper" aria-hidden="true" id="forname" > </i>
                                <div class="clearfix"></div>
                            </div>

                            <div>
                                <i class="fa fa-envelope-o" aria-hidden="true"> </i>
                                <input type="email" name="updateemail" id="email" disabled  value="<?php echo $outcome['result']['email']; ?>">
                                <div class="clearfix"></div>
                            </div>


                            <div>
                                <i class="fa fa-phone" aria-hidden="true" > </i>
                                <input type="tel" name="updatephonenumber" id="phonenumber"   value="<?php echo $outcome['result']['phoneNumber']; ?>">
                                <i class="fa fa-eyedropper" aria-hidden="true" id="forphonenumber" > </i>
                                <div class="clearfix"></div>
                            </div>
                            <div id="up">
                                <input type="submit" value="UPDATE PROFILE" id="update">
                            </div>



                        </form>

                        <?php
                    }
                }
                ?>



            </div>
            <div class="history" id="history">

                <div class="spec ">
                    <h3>YOUR HISTORY</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>
                <?php
                $curl = curl_init();

                curl_setopt_array($curl, array(
                    CURLOPT_PORT => "8080",
                    CURLOPT_URL => $ip . "/user/product?email=$value1",
                    CURLOPT_RETURNTRANSFER => true,
                    CURLOPT_ENCODING => "",
                    CURLOPT_MAXREDIRS => 10,
                    CURLOPT_TIMEOUT => 30,
                    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                    CURLOPT_CUSTOMREQUEST => "GET",
                    CURLOPT_POSTFIELDS => "{\n\t\"email\":\"mac.millsq@gmails.com\",\n\t\"password\":\"king1234\"\n}",
                    CURLOPT_HTTPHEADER => array(
                        "cache-control: no-cache",
                        "content-type: application/json",
                        "postman-token: ac4843ed-1dde-4650-72dd-b0de56791436"
                    ),
                ));

                $response = curl_exec($curl);
                $err = curl_error($curl);

                curl_close($curl);

                if ($err) {
                    echo "cURL Error #:" . $err;
                } else {
                    echo $response;
                }
                ?>
            </div>
            <div class="cpass" id="cpass">
                <div class="spec ">
                    <h3>CHANGE YOUR PASSWORD</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>

                <form action="operator/operations.php" method="post" class="register">
                    <input type="hidden" name="updatevalue" value="changePass">

                    <div>
                        <i class="fa fa-key" aria-hidden="true" > </i>
                        <input type="password" name="passwordreg" id="password" placeholder="password" required>
                        <div class="clearfix"></div>
                    </div>

                    <div>
                        <i class="fa fa-key" aria-hidden="true" > </i>
                        <input type="password" name="passwordregcon" id="password" placeholder="password" required>
                        <div class="clearfix"></div>
                    </div>

                    <div>
                        <input type="submit" value="UPDATE PASSWORD">
                    </div>



                </form>
            </div>
            <script type="text/javascript">
                $(document).ready(function () {
                    $("#up").hide();
                    $("#name").attr('disabled', 'disabled');
                    $("#phonenumber").attr('disabled', 'disabled');
                    $("#cpass").hide();
                    $("#history").hide();
                    $('.link1').addClass("active");
                    $("#forname").click(function () {
                        $("#name").removeAttr('disabled');
                        $("#up").fadeIn(1000);
                    });

                    $("#forphonenumber").click(function () {
                        $("#phonenumber").removeAttr('disabled');
                        $("#up").fadeIn(1000);

                    });
                    $("#update").click(function () {
                        $("#email").removeAttr('disabled');
                        $("#phonenumber").removeAttr('disabled');
                        $("#name").removeAttr('disabled');
                    });

                    $("#link1").click(function () {
                        $("#history").hide();
                        $("#cpass").hide();
                        $('#forprofile').fadeIn(1000);
                        $('.link2').removeClass("active");
                        $('.link2').removeClass("active");
                        $('.link1').addClass("active");
                    });
                    $("#link2").click(function () {
                        $("#forprofile").hide();
                        $("#cpass").hide();
                        $('#history').fadeIn(1000);
                        $('.link3').removeClass("active");
                        $('.link1').removeClass("active");
                        $('.link2').addClass("active");

                    });
                    $("#link3").click(function () {
                        $("#history").hide();
                        $("#forprofile").hide();
                        $('#cpass').fadeIn(1000);
                        $('.link1').removeClass("active");
                        $('.link2').removeClass("active");
                        $('.link3').addClass("active");

                    });

                    setInterval(function () {
                        $('.msgs').empty();
                    }, 3000);
                });
            </script>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
            <script src="../pages/../js/dropdown.js"type="text/javascript"></script>
        </body>
    </html>
    <?php
} else {
    header("location:../pages/../index.php");
}

