<?php
//include"operator/operations.php";
$logMSG = "";
$regMSG = "";
$regPassMsg = "";
$regPhoneMsg = "";
$regMailMsg = "";
$regConPassMsg = "";
$regNameMsg = "";
//$registeredmail="";
$value = filter_input(INPUT_GET, 'value', FILTER_SANITIZE_STRING);
$registeredmail = filter_input(INPUT_GET, 'emailregisterated', FILTER_SANITIZE_STRING);
$valueproduct = filter_input(INPUT_GET, 'addpro', FILTER_SANITIZE_STRING);
if (isset($_SESSION["sexion"])) {
    if (isset($value) || isset($valueproduct)) {

        header("location:productadd.php");
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
<?php
if ($value === 'Login') {
    echo "<title>FARMiCONN || $value</title>";
} elseif ($value === 'Register') {
    echo "<title>FARMiCONN || $value</title>";
}
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

                </div>
                <div class="clearfix"></div>
            </div>

        </div>

<?php
if ($value === 'Login') {
    ?>
            <div class="container">
                <div class="spec ">
                    <h3>Sign In</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>

                <form action="operator/operations.php" method="post" class="login">
                    <input type="hidden" name="login" value="login">
                    <input type="hidden" name="loginvalue" value="<?php echo $valueproduct; ?>">
                    <i><?php echo $valueproduct; ?></i>
                    <div>
                        <i class="fa fa-envelope-square " aria-hidden="true" > </i>
                        <input type="email" name="emaillog" value="<?php echo $registeredmail; ?>" placeholder="Email Address" required>
                        <div class="clearfix"></div>
                    </div>
                    <div>
                        <i class="fa fa-key" aria-hidden="true" > </i>
                        <input type="password" name="passwordlog" placeholder="***********" required>
                        <div class="clearfix"></div>
                    </div>
                    <div>
                        <input type="submit" value="Login">
                    </div>
                    <span><?php echo $logMSG; ?></span>
                </form>

            </div>
    <?php
} elseif ($value === 'Register') {
    ?>
            <div class="container">
                <div class="spec ">
                    <h3>Sign Up</h3>
                    <div class="ser-t">
                        <b></b>
                        <span><i></i></span>
                        <b class="line"></b>
                    </div>
                </div>
                <form action="operator/operations.php" method="post" class="register">
                    <input type="hidden" name="register" value="register">


                    <i><?php echo $valueproduct; ?></i>

                    <div>
                        <i class="fa fa-user-circle" aria-hidden="true" > </i>
                        <input type="text" name="namer" id="name" placeholder="Full Name" required>
                        <div class="clearfix"></div>
                    </div>
                    <span><?php echo $regNameMsg; ?></span>
                    <div>
                        <i class="fa fa-envelope-o" aria-hidden="true"> </i>
                        <input type="email" name="email" id="email" placeholder="Email Address" required>
                        <div class="clearfix"></div>
                    </div>
                    <span><?php echo $regMailMsg; ?></span>

                    <div>
                        <i class="fa fa-phone" aria-hidden="true" > </i>
                        <input type="tel" name="phonenumber" id="phonenumber" placeholder="phonenumber" required>
                        <div class="clearfix"></div>
                    </div>
                    <span><?php echo $regPhoneMsg; ?></span>
                    <div>
                        <i class="fa fa-key" aria-hidden="true" > </i>
                        <input type="password" name="passwordreg" id="password" placeholder="password" required>
                        <div class="clearfix"></div>
                    </div>
                    <span><?php echo $regPassMsg; ?></span>
                    <div>
                        <i class="fa fa-key" aria-hidden="true" > </i>
                        <input type="password" name="passwordregcon" id="password" placeholder="password" required>
                        <div class="clearfix"></div>
                    </div>
                    <span><?php echo $regConPassMsg; ?></span>
                    <div>
                        <input type="submit" value="Register">
                    </div>
                    <span><?php echo $regMSG; ?></span>


                </form>


            </div>

    <?php
}
?>
    </body>
</html>