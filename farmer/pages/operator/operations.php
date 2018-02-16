
<?php

session_start();
require_once '../operator/../../pages/../config.php';
$ipclass = new ip();
//echo $ipclass->getIp();
$ip = $ipclass->getIp();
$logEmail = filter_input(INPUT_POST, 'emaillog', FILTER_SANITIZE_EMAIL);
$regEmail = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
$logPass = filter_input(INPUT_POST, 'passwordlog', FILTER_SANITIZE_STRING);
$regPass = filter_input(INPUT_POST, 'passwordreg', FILTER_SANITIZE_STRING);
$regConPass = filter_input(INPUT_POST, 'passwordregcon', FILTER_SANITIZE_STRING);
$regPhone = filter_input(INPUT_POST, 'phonenumber', FILTER_SANITIZE_STRING);
$regName = filter_input(INPUT_POST, 'namer', FILTER_SANITIZE_STRING);
$login = filter_input(INPUT_POST, 'login', FILTER_SANITIZE_STRING);
$loginvalue = filter_input(INPUT_POST, 'loginvalue', FILTER_SANITIZE_STRING);
$register = filter_input(INPUT_POST, 'register', FILTER_SANITIZE_STRING);

$updatevalue = filter_input(INPUT_POST, 'updatevalue', FILTER_SANITIZE_STRING);
$updatename = filter_input(INPUT_POST, 'updatename', FILTER_SANITIZE_STRING);
$updatephonenumber = filter_input(INPUT_POST, 'updatephonenumber', FILTER_SANITIZE_STRING);
$updateemail = filter_input(INPUT_POST, 'updateemail', FILTER_SANITIZE_STRING);




if ($login === "login") {
    if (!empty($logEmail) && !empty($logPass)) {
        LOGINOPERATION($logEmail, $logPass, $loginvalue, $ip);
    } else {
        header("location:../operator/../logreg.php?value=Login&addpro=EMAIL and PPASSWORD fields cannot be empty,Please try again");
    }
} elseif ($register === "register") {
    if (strcmp($regConPass, $regPass) == 0) {
        if (!empty($regPass)) {
            if (strlen($regName) > 7 || strlen($regPhone) >= 9 || !$regEmail === "") {

                REGISTERATIONOPERATION($regName, $regEmail, $regPhone, $regPass, $ip);
            } else {
                header("location:../operator/../logreg.php?value=Register&addpro=NAME, PHONE NUMBER and EMAIL fields cannot be less than seven and nine,Please try again");
            }
        } else {
            header("location:../operator/../logreg.php?value=Register&addpro=PASSWORD field cannot be blank,Please try again");
        }
    } else {
        header("location:../operator/../logreg.php?value=Register&addpro=PASSWORDS do not match,Please try again");
    }
} elseif ($updatevalue === "updateprofile") {

    UPDATEINFO($updatename, $updatephonenumber, $updateemail, $ip);
} elseif ($updatevalue === "changePass") {
    UPDATEINFO($updatename, $updatephonenumber, $updateemail, $ip);
}

function LOGINOPERATION($mail, $password, $loginvalue, $ip) {
    $curl = curl_init();
    curl_setopt_array($curl, array(
        CURLOPT_PORT => "8080", CURLOPT_URL => $ip . "/user/login",
        CURLOPT_RETURNTRANSFER => true, CURLOPT_ENCODING => "", CURLOPT_MAXREDIRS => 10, CURLOPT_TIMEOUT => 30, CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => "POST", CURLOPT_POSTFIELDS => "{\n\t\"email\":\"$mail\",\n\t\"password\":\"$password\"\n}",
        CURLOPT_HTTPHEADER => array(
            "cache-control: no-cache",
            "content-type: application/json",
            "postman-token: 1aadbb23-14a5-b453-dff5-2b2b8ae69ed5"
        ),));
    $response = curl_exec($curl);
    $err = curl_error($curl);
    curl_close($curl);
    if ($err) {
        $values = json_decode($response, TRUE);

        echo "cURL Error #:" . $err;
        header("location:../operator/../logreg.php?value=Login&emailregisterated=$values->result &addpro=cURL Error #: . $err");
    } else {
        // echo $response;
        $values = json_decode($response, TRUE);
        if ($values['status'] == 1) {
            $_SESSION['sexion'] = $values['result'];
            if ($loginvalue === "Please login to add product") {
                $_SESSION['sexion'] = $values['result'];
                header("location:../operator/../productadd.php");
            } else {
                $_SESSION['sexion'] = $values['result'];

                header("location:../operator/../../index.php");
            }
        } else {
            header("location:../operator/../logreg.php?value=Login&emailregisterated=$values->result &addpro=$values->message" . "ed. Please Try again!!");
        }
    }
}

function REGISTERATIONOPERATION($name, $emanil, $phone, $pass, $ip) {
    $curl = curl_init();

    curl_setopt_array($curl, array(
        CURLOPT_PORT => "8080",
        CURLOPT_URL => $ip . "/user/create",
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => "",
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 30,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => "POST",
        CURLOPT_POSTFIELDS => "{\n\t\"name\":\"$name\",\n\t\"email\":\"$emanil\",\n\t\"password\":\"$pass\",\n\t\"phoneNumber\":\"$phone\"\n}",
        CURLOPT_HTTPHEADER => array(
            "cache-control: no-cache",
            "content-type: application/json",
            "postman-token: d22748bf-add6-66e5-e50d-049f114fae96"
        ),
    ));

    $response = curl_exec($curl);
    $err = curl_error($curl);

    curl_close($curl);

    if ($err) {
        echo "cURL Error #:" . $err;
    } else {
        // echo $response;
        $json = json_decode($response, TRUE);

        if ($json['status'] == 1) {

            header("location:../operator/../logreg.php?value=Login&emailregisterated=" . $json['result']['email'] . "&addpro=Please Login");
        } else {
            header("location:../operator/../logreg.php?value=Register&addpro=Unsuccessfull Registeration,Please try again");
        }
    }
}

function UPDATEINFO($name, $phone, $email, $ip) {
    $curl = curl_init();

    curl_setopt_array($curl, array(
        CURLOPT_PORT => "8080",
        CURLOPT_URL => $ip . "/user/update",
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => "",
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 30,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => "POST",
        CURLOPT_POSTFIELDS => "{\n\t\"name\":\"$name\",\n\t\"email\":\"$email\",\n\t\"password\":\"\",\n\t\"phoneNumber\":\"$phone\"\n}",
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
        $json = json_decode($response, TRUE);

        if ($json['status'] == 1) {
            echo $response;
            //header("location:../operator/../manageaccount.php?message=Profile successfully updated");
        } else {
            echo $response;
            // header("location:../operator/../manageaccount.php?message=Profile was not successfully updated");
        }
    }
}
