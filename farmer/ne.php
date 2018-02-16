<form action="" method="post" name="frmUpload" enctype="multipart/form-data">
    <tr>
      <td>Upload</td>
      <td align="center">:</td>
      <td><input name="file" type="file" id="file"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center">&nbsp;</td>
      <td><input name="btnUpload" type="submit" value="Upload" onclick="return DoInsert()"/></td>
    </tr>
  </form>
 <script type="text/javascript">
   
        var form = new FormData();

form.append("image", $('input[type=file]')[0].files[0]);
 //alert(prodname+"\n"+proprice+"\n"+prodlocation+"\n"+prodcategory+"\n"+proddescription +"\n"+prodmail+"\n"+$('input[type=file]')[0].files[0]+"\n"+ $('input[type=file]')[0].files[0] +"\n"+$('input[type=file]')[0].files[0]  +"\n");
var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://10.42.0.1:8080/product/create",
  "method": "POST",
  "headers": {
    "cache-control": "no-cache",
    "postman-token": "3d4e5d6f-3585-eef8-ec59-0deac908760a"
  },
  "processData": false,
  "contentType": false,
  "mimeType": "multipart/form-data",
  "data": form
};

$.ajax(settings).done(function (response) {
  console.log(response);
  alert(response);
});
   
        
</script>
<?php
////$imageURL = 'http://localhost/farmer/media/images/gabriel-jimenez-241711.jpg';
//
$handle = fopen('http://localhost/farmer/media/images/gabriel-jimenez-241711.jpg', 'rb');
$img = new Imagick();
$img->readImageFile($handle);
$img->resizeImage(128, 128, 0, 0);
$img->writeImage('images/jpg');
$ch = curl_init($imageURL);    

curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);

$data = curl_exec($ch);
curl_close($ch);

if ($data === false) {
        die('cURL failed');
}

header('Content-Type: image/jpg');
header('Content-Length: ' . curl_getinfo( $ch, CURLINFO_CONTENT_LENGTH_DOWNLOAD ) );
echo $data;
?>
<?php

//$errmsg = '';
//    if (isset($_POST['btnUpload']))
//    {
//        $url = "http://10.42.0.1:8080/product/image"; // e.g. http://localhost/myuploader/upload.php // request URL
//        $filename = $_FILES['file']['name'];
//        $filedata = $_FILES['file']['tmp_name'];
//        $filesize = $_FILES['file']['size'];
//        if ($filedata != '')
//        {
//            $headers = array("Content-Type:multipart/form-data"); // cURL headers for file uploading
//            $postfields = array("file" => "@$filedata");
//            $ch = curl_init();
//            $options = array(
//                CURLOPT_URL => $url,
//                CURLOPT_HEADER => true,
//                CURLOPT_POST => 1,
//                CURLOPT_HTTPHEADER => $headers,
//                CURLOPT_POSTFIELDS => $postfields,
//                CURLOPT_INFILESIZE => $filesize,
//                CURLOPT_RETURNTRANSFER => true
//            ); // cURL options
//            curl_setopt_array($ch, $options);
//            curl_exec($ch);
//            if(!curl_errno($ch))
//            {
//                $info = curl_getinfo($ch);
//                if ($info['http_code'] == 200)
//                    $errmsg = "File uploaded successfully";
//            }
//            else
//            {
//                $errmsg = curl_error($ch);
//            }
//            curl_close($ch);
//        }
//        else
//        {
//            $errmsg = "Please select the file";
//        }
//    }