
<?php

$curl = curl_init();

curl_setopt_array($curl, array(
    CURLOPT_PORT => "8080",
    CURLOPT_URL => "http://172.16.40.195:8080/home",
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
            // $value1 = $outcome['result']["$i"]['category'];
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
            array_push($arrayValues, $aud);
            array_push($arrayValues, $vid);
            array_push($arrayValues, $date);
            array_push($arrayValues, $img);
            array_push($arrayValues, $des);

            $var = serialize($arrayValues);

            echo "<a href='pages/displayDetails.php?value=$var '>$prodname</a><br>";
            //   echo json_encode($_SESSION["var"]);
        }
    }
}

