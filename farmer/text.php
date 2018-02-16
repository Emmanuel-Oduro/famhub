
<!--<form method="POST" enctype="multipart/form-data">
    <input type="file" name="image">
    <input type="text" name="text1">
     <input type="file" name="image">
    <input type="submit" name="sub">
    
</form>-->
 
<?php
//include "register.json";
//$f=file_get_contents("register.json");
//$json= json_decode($f,true);
////var_dump($json);
//
//echo $json['result']['email'];
////
// if($json['status']==1){
//    echo "<br>c<br>";
////     echo $json->message;
////     //echo $json->result[''];
////     echo $json->result['name'];
//  }
// //  
//   $tex= filter_input(INPUT_POST, "text1");
//   $productaudio=$_FILES['image']['name'];
//     $productaudiotemp = explode(".", $_FILES["image"]["name"]);
// 	$productaudionewfilename = round(microtime(true))-1 . '.' . end($productaudiotemp);
//          $productaudiocontenttyp=end($productaudiotemp);
//          echo $productaudio ."<br>".$productaudiotemp ."<br>".$productaudionewfilename."<br>".$productaudiocontenttyp;
 
         

         

// $curl = curl_init();

// curl_setopt_array($curl, array(
//   CURLOPT_PORT => "8080",
//   CURLOPT_URL => "http://192.168.43.104:8080/product/image",
//   CURLOPT_RETURNTRANSFER => true,
//   CURLOPT_ENCODING => "",
//   CURLOPT_MAXREDIRS => 10,
//   CURLOPT_TIMEOUT => 30,
//   CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
//   CURLOPT_CUSTOMREQUEST => "POST",
//   CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: "
//     . "form-data; name=\"productName\"\r\n\r\n$tex\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//     . "name=\"file\"; filename=\"$productaudionewfilename\"\r\nContent-Type: image/$productaudiocontenttyp\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
//   CURLOPT_HTTPHEADER => array(
//     "cache-control: no-cache",
//     "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW",
//     "postman-token: e79896ef-a230-25c1-e3e9-efb4c8728bc3"
//   ),
// ));

// $response = curl_exec($curl);
// $err = curl_error($curl);

// curl_close($curl);

// if ($err) {
//   echo "cURL Error #:" . $err;
// } else {
//   echo $response;

?>
<form method="POST" enctype="multipart/form-data">
  <img src="<?php file_get_contents("http://10.42.0.1:8080/image/ama@n.com/108142/jpg")?>" alt="http://10.42.0.1:8080/image/ama@n.com/108142/jpg">
     <input type="file" name="image">
    <input type="submit" name="sub">

</form>
 
<?php
echo "10.42.0.1:8080/image/ama@n.com/108142/jpg";
echo file_get_contents("http://10.42.0.1:8080/image/ama@n.com/108142/jpg");
 $tex= filter_input(INPUT_POST, "sub");
  if(isset($tex)){

      $filename = file_get_contents('http://localhost/farmer/media/images/gabriel-jimenez-241711.jpg');
        $filedata = $_FILES['image']['name'];
        $filesize = $_FILES['image']['size'];
        $productimagetemp = explode(".", $_FILES["image"]["name"]);
	$productimagenewfilename = round(microtime(true)) . '.' . end($productimagetemp);
        $productimagecontenttyp= end($productimagetemp);
      

$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_PORT => "8080",
  CURLOPT_URL => "http://10.42.0.1:8080/product/image",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"file\"; filename=\"$filedata\"\r\nContent-Type: image/png\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
  CURLOPT_HTTPHEADER => array(
    "cache-control: no-cache",
    "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW",
    "postman-token: 7cf72cde-0c65-185e-204d-45d10fb698df"
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
//        curlPost('http://10.42.0.1:8080/product/image', file_get_contents('http://localhost/farmer/media/images/gabriel-jimenez-241711.jpg')) ;
// $curl = curl_init();
// 
// curl_setopt_array($curl, array(
//   CURLOPT_PORT => "8080",
//     CURLOPT_BINARYTRANSFER=>true,
//     CURLOPT_SSL_VERIFYHOST=>FALSE,
//     CURLOPT_SSL_VERIFYPEER=>FALSE,
//   CURLOPT_URL => "http://10.42.0.1:8080/product/image",
//   CURLOPT_RETURNTRANSFER => true,
//   CURLOPT_ENCODING => "",
//   CURLOPT_MAXREDIRS => 10,
//   CURLOPT_TIMEOUT => 30,
//   CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
//   CURLOPT_CUSTOMREQUEST => "POST",
//   CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: "
//     . "form-data; name=\"productName\"\r\n\r\nzazaa\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//     . "name=\"file\"; filename=\"$filename\"\r\nContent-Type: image/$productimagecontenttyp\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
//     CURLOPT_INFILESIZE => $filesize,
//   CURLOPT_HTTPHEADER => array(
//     "cache-control: no-cache",
//     "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW"
//   ),
// ));
//
// $response = curl_exec($curl);
// $err = curl_error($curl);
//
// curl_close($curl);
//
// if ($err) {
//   echo "cURL Error #:" . $err;
// } else {
//   echo $response;
// } 
 }
//include "register.json";
//$f=file_get_contents("register.json");
//$json= json_decode($f);
// if($json->status==1){
//    echo "c<br>";
//     echo $json->message;
//     echo $json->result;
//  }
// //  
//   $tex= filter_input(INPUT_POST, "sub");
//   if(isset($tex)){
//     
//
//   $productaudio= basename($_FILES['image']['name']);
//  
//     $productaudiotemp = explode(".", $_FILES["image"]["name"]);
// 	$productaudionewfilename = round(microtime(true))-1 . '.' . end($productaudiotemp);
//          $productaudiocontenttyp=end($productaudiotemp);
//         // echo $productaudio ."<br>".$productaudiotemp ."<br>".$productaudionewfilename."<br>".$productaudiocontenttyp;
//         // curlPost("http://10.42.0.1:8080/product/image",$_FILES["image"]["name"]);
////         echo$productaudio."<br><br>" ;
////        echo $_SERVER['REQUEST_URI']."/". $_FILES['image']['name'];
//        //$_SERVER['REQUEST_URI'];
//               $fp="/".$productaudionewfilename;  
//
//   $zaza= move_uploaded_file($_FILES["image"]["tmp_name"],$fp );
//   if($zaza){
//       echo 'wow';
//   }
// else {
//       echo 'ohhh';    
//   }
//   ?>
//<?php
//$args=file_get_contents($fp);
// $curl = curl_init();
// 
// curl_setopt_array($curl, array(
//   CURLOPT_PORT => "8080",
//     CURLOPT_BINARYTRANSFER=>true,
//     CURLOPT_SSL_VERIFYHOST=>FALSE,
//     CURLOPT_SSL_VERIFYPEER=>FALSE,
//   CURLOPT_URL => "http://10.42.0.1:8080/product/image",
//   CURLOPT_RETURNTRANSFER => true,
//   CURLOPT_ENCODING => "",
//   CURLOPT_MAXREDIRS => 10,
//   CURLOPT_TIMEOUT => 30,
//   CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
//   CURLOPT_CUSTOMREQUEST => "POST",
//   CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: "
//     . "form-data; name=\"productName\"\r\n\r\n$tex\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//     . "name=\"file\"; filename=\"$fp\"\r\nContent-Type: image/$productaudiocontenttyp\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
//   CURLOPT_HTTPHEADER => array(
//     "cache-control: no-cache",
//     "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW"
//   ),
// ));
//
// $response = curl_exec($curl);
// $err = curl_error($curl);
//
// curl_close($curl);
//
// if ($err) {
//   echo "cURL Error #:" . $err;
// } else {
//   echo $response;
// }  
// }
  function curlPost($url,$file) {
  $ch = curl_init();
  if (!is_resource($ch)) return false;
  curl_setopt( $ch , CURLOPT_SSL_VERIFYPEER , 0 );
  curl_setopt( $ch , CURLOPT_FOLLOWLOCATION , 0 );
  curl_setopt( $ch , CURLOPT_URL , $url );
  curl_setopt( $ch , CURLOPT_POST , 1 );
  curl_setopt( $ch , CURLOPT_POSTFIELDS , '@' . $file );
  curl_setopt( $ch , CURLOPT_RETURNTRANSFER , 1 );
  curl_setopt( $ch , CURLOPT_VERBOSE , 0 );
  $response = curl_exec($ch);
  curl_close($ch);
  return $response;
}