<form method="POST" enctype="multipart/form-data">
  
     <input type="file" name="image">
    <input type="submit" name="sub">
</form>
<?php
$tex= filter_input(INPUT_POST, "sub");
  if(isset($tex)){

 $filename = $_FILES['image']['name'];
        $filedata = $_FILES['image']['tmp_name'];
        $filesize = $_FILES['image']['size'];
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
  CURLOPT_POSTFIELDS => "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"file\"; filename=\"$filename\"\r\nContent-Type: image/jpeg\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",
  CURLOPT_HTTPHEADER => array(
    "cache-control: no-cache",
    "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW",
    "postman-token: 7dcf55e0-ac29-d1d3-570c-1eec43c9c9ae"
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
  }
  