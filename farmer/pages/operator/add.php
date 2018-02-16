   <?php

   session_start();

   require_once '../operator/../../pages/../config.php';
   $ipclass = new ip();
//echo $ipclass->getIp();
   $ip = $ipclass->getIp();
   $productname = filter_input(INPUT_POST, "productname", FILTER_SANITIZE_STRING);
   $productprice = filter_input(INPUT_POST, "price", FILTER_SANITIZE_NUMBER_FLOAT);
   $productuploaderemail = $_SESSION['sexion'];
   $productcategory = filter_input(INPUT_POST, "category", FILTER_SANITIZE_STRING);
   $productlocation = filter_input(INPUT_POST, "location", FILTER_SANITIZE_STRING);
   $productdescription = filter_input(INPUT_POST, "description", FILTER_SANITIZE_STRING);
   echo $productname . "<br>" . $productprice . "<br>" . $productcategory . "<br>" .
   $productlocation . "<br>" . $productdescription . "<br>";
   $imageURL = "";
   $videoUrl = "";
   $audioUrl = "";

   $productimage = $_FILES['image']["name"];
   $productimagetmpfile = $_FILES['image']["tmp_name"];
   $productimagetemp = explode(".", $_FILES["image"]["name"]);
   $productimagenewfilename = round(microtime(true)) . '.' . end($productimagetemp);
   $productimagecontenttyp = end($productimagetemp);
   //echo 'image';
   $fp1 = "../operator/../../upload/" . $productimagenewfilename;

   $productaudio = $_FILES['audio']['name'];
   $productaudiotmpfile = $_FILES['audio']["tmp_name"];
   $productaudiotemp = explode(".", $_FILES["audio"]["name"]);
   $productaudionewfilename = round(microtime(true)) - 1 . '.' . end($productaudiotemp);
   $productaudiocontenttyp = end($productaudiotemp);
   $fp2 = "../operator/../../upload/" . $productaudionewfilename;

   $productvideo = $_FILES['video']['name'];
   $productvideotmpfile = $_FILES['video']["tmp_name"];
   $productvideotemp = explode(".", $_FILES["video"]["name"]);
   $productvideonewfilename = round(microtime(true)) - 2 . '.' . end($productvideotemp);
   $productvideocontenttyp = end($productvideotemp);
   $fp3 = "../operator/../../upload/" . $productvideonewfilename;

   $IMAGE = move_uploaded_file($_FILES['image']["tmp_name"], $fp1);
   if ($IMAGE) {
       $imageURL .= realpath($fp1);
       echo $imageURL;
       $VIDEO = move_uploaded_file($_FILES['video']["tmp_name"], $fp2);
       if ($VIDEO) {
           $videoUrl .= realpath($fp2);
           echo $videoUrl;
           $AUDIO = move_uploaded_file($_FILES['audio']["tmp_name"], $fp3);
           if ($AUDIO) {
               $audioUrl .= realpath($fp3);
               echo $audioUrl;
           }
       }
   }
   //echo realpath($fp);
// . "<br>" . $productimagecontenttyp . "<br>" . $productaudiocontenttyp . "<br><br>" . $productvideocontenttyp . "<br>" . $productaudionewfilename;
//$curl = curl_init();
//curl_setopt_array($curl, array(
//  CURLOPT_PORT => "8080",
//  CURLOPT_URL => "http://".$ip.":8080/product/create",
//  CURLOPT_RETURNTRANSFER => true,
//    CURLOPT_SAFE_UPLOAD=> true,
//  CURLOPT_ENCODING => "",
//  CURLOPT_MAXREDIRS => 10,
//  CURLOPT_TIMEOUT => 30,
//    CURLOPT_BUFFERSIZE=>128,
//  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
//  CURLOPT_CUSTOMREQUEST => "POST",
//  CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//    . "name=\"productName\"\r\n\r\n$productname\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//    . "name=\"price\"\r\n\r\n$productprice\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//    . "name=\"farmer\"\r\n\r\n$productuploaderemail\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//    . "name=\"description\"\r\n\r\n$productdescription \r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;"
//    . " name=\"location\"\r\n\r\n$productlocation\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;"
//    . " name=\"category\"\r\n\r\n$productcategory\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//    . "name=\"image\"; filename=\"$productimagetmpfile\"\r\nContent-Type: image/$productimagecontenttyp\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; "
//    . "name=\"video\"; filename=\"$productvideotmpfile\"\r\nContent-Type: video/$productvideocontenttyp\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;"
//    . " name=\"audio\"; filename=\"$productaudiotmpfile\"\r\nContent-Type: audio/$productaudiocontenttyp\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
//  CURLOPT_HTTPHEADER => array(
//    "cache-control: no-cache",
//    "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW"
//  ),
//));
//
//$response = curl_exec($curl);
//$err = curl_error($curl);
//
//curl_close($curl);
//
//if ($err) {
//  echo "cURL Error #:" . $err;
//} else {
//  echo $response;
//}
