<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//
//$curl = curl_init();
//
//curl_setopt_array($curl, array(
//  CURLOPT_PORT => "8080",
//  CURLOPT_URL => "http://10.42.0.1/user/update",
//  CURLOPT_RETURNTRANSFER => true,
//  CURLOPT_ENCODING => "",
//  CURLOPT_MAXREDIRS => 10,
//  CURLOPT_TIMEOUT => 30,
//  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
//  CURLOPT_CUSTOMREQUEST => "POST",
//  CURLOPT_POSTFIELDS => "{\n\t\"name\":\"zaza oduro\",\n\t\"email\":\"zaza@z.com\",\n\t\"password\":\"\",\n\t\"phoneNumber\":\"\"\n}",
//  CURLOPT_HTTPHEADER => array(
//    "cache-control: no-cache",
//    "content-type: application/json",
//    "postman-token: d22748bf-add6-66e5-e50d-049f114fae96"
//  ),
//));
//
//$response = curl_exec($curl);
//$err = curl_error($curl);
//
//curl_close($curl);
//if ($err) {
//  echo "cURL Error #:" . $err;
//} else {
//  echo $response;
//}
            $ip='http://192.168.43.104:8080';
            $curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_PORT => "8080",
  CURLOPT_URL => "$ip/product/category?category=Tuber",
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
    $ui=json_decode($response,TRUE);
//    $uii=$ui['result']['1'];
//   echo json_encode($uii);
  //  var_dump(array_slice($ui['result'], 0, 10));
    $uii=array_slice($ui['result'], 0, 10);
    $uiii=json_encode($uii);
  $v= json_decode($uiii,TRUE);
  for ($index = 0; $index < count($v); $index++) {
      echo $v[$index]['productName'];  
      echo $v[$index]['price'];
      echo $v[$index]['location'];

  }
 // var_dump($v) ;
    
    
    
    
    
    
    
    
    
//      $uii=array_slice($ui['result'], 0, 10);
//    $uiii=json_encode($uii);
//    $u= json_decode($uiii,TRUE);
//    echo $u['1'];
}