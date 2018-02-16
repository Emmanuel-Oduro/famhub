<?php
session_start();
if (isset($_SESSION['sexion'])) {
    // echo $_SESSION['sexion'];
    //  $submit=filter_input(INPUT_POST,"Submit");
    $message = "";
    $url = json_decode(file_get_contents("http://api.ipinfodb.com/v3/ip-city/?key=2b3d7d0ad1a285279139487ce77f3f58d980eea9546b5ccc5d08f5ee62ce7471&ip=" . file_get_contents("http://bot.whatismyipaddress.com") . "&format=json"));

    $message = filter_input(INPUT_GET, 'message', FILTER_SANITIZE_STRING);
    ?>


    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <link rel="stylesheet" href="../pages/../css/main.css">
            <link rel="stylesheet" href="../pages/../css/font-awesome.css">
            <link rel="stylesheet" href="../pages/../css/font-awesome.min.css">

            <link rel="shortcut icon" href="" type="image/x-icon">
            <title>Document</title>
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
                        <nav>
                            <ul>
                                <li><i class="fa fa-sign-in" aria-hidden="true"></i>
                                    <a href="../pages/../index.php">Home</a></li>


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
                <!-- operator/add.php -->
                <form action="operator/add.php" method="POST" enctype="multipart/form-data">
                    <div class="message">
                        <span><?php
                            if (empty($message)) {
                                echo "<div class='spec'>
			<h3>Add Product</h3>
			<div class='ser-t'>
				<b></b>
				<span><i></i></span>
				<b class='line'></b>
			</div>
		</div>";
                            } else {
                                echo $message;
                            }
                            ?></span>
                    </div>
                    <input type="hidden" value="<?php echo $_SESSION['sexion']; ?>" id="mail">
                    <div >
                        <img src="../pages/../media/images/asas.jpg"  width="100" height="100" id="output_image" class=""/>

                        <input type="file" name="image" id="image" accept="image/*" onchange="preview_image(event)">
                        <div class="clearfix"></div>
                    </div>
                    <div><i class="fa fa-sign-in" aria-hidden="true"></i>
                        <input type="text" name="productname" placeholder="Product Name" id="productname">
                        <div class="clearfix"></div>
                    </div>
                    <div ><i class="fa fa-sign-in" aria-hidden="true"></i>
                        <input type="text" name="price"  placeholder="Price" id="price">            <div class="clearfix"></div>

                    </div>
                    <div ><i class="fa fa-sign-in" aria-hidden="true"></i>
                        <input type="text" name="location"  placeholder="Location" value="<?php echo $url->regionName; ?>"id="location">            <div class="clearfix"></div>

                    </div>
                    <div ><i class="fa fa-sign-in" aria-hidden="true"></i>
                        <select name="category" id="category">
                            <option value="Tuber">Tuber</option>
                            <option value="Fruit">Fruit</option>
                            <option value="Vegetable">Vegetable</option>
                            <option value="Cereal">Cereal</option>
                            <option value="Oils">Oils</option>
                            <option value="LiveStock">LiveStock</option>
                            <option value="Fiber">Fiber</option>
                            <option value="Timber">Timber</option>
                            <option value="Spices">Spices</option>
                        </select>

                        <div class="clearfix"></div>

                    </div>
                    <div ><i class="fa fa-sign-in" aria-hidden="true"></i>
                        <input type="text" name="description"  placeholder="Description" id="description">            <div class="clearfix"></div>

                    </div>

                    <div ><i class="fa fa-sign-in" aria-hidden="true"> Audio</i>
                        <input type="file" name="audio"accept="audio/*"  id="audio">            <div class="clearfix"></div>

                    </div>
                    <div ><i class="fa fa-sign-in" aria-hidden="true">Video</i>
                        <input type="file" name="video"accept="video/*"  id="video" >            <div class="clearfix"></div>

                    </div>
                    <div>
                        <input type="submit" name="Submit" value="Submit"  id="submit">

                    </div>
                </form>
            </div>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
            <script src="../pages/../js/dropdown.js"type="text/javascript"></script>
            <script type='text/javascript'>
                            function preview_image(event)
                            {
                                var reader = new FileReader();
                                reader.onload = function ()
                                {
                                    var output = document.getElementById('output_image');
                                    output.src = reader.result;
                                }
                                reader.readAsDataURL(event.target.files[0]);
                            }
            </script>
            <script type="text/javascript">
                function DoInsert() {
                    var prodname = document.getElementById("productname").value;
                    var proprice = document.getElementById("price").value;
                    var prodlocation = document.getElementById("location").value;
                    var prodcategory = document.getElementById("category").value;
                    var proddescription = document.getElementById("description").value;
                    var prodmail = document.getElementById("mail").value;


                    var form = new FormData();
                    form.append("productName", prodname);
                    form.append("price", proprice);
                    form.append("farmer", prodmail);
                    form.append("description", proddescription);
                    form.append("location", prodlocation);
                    form.append("category", prodcategory);
                    form.append("image", $('input[type=file]')[0].files[0]);
                    form.append("video", $('input[type=file]')[0].files[0]);
                    form.append("audio", $('input[type=file]')[0].files[0]);
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

                }
            </script>

        </body>
    </html>
    <?php
} else {
    header("location:../pages/../index.php");
}






/**
 * PHP's curl extension won't let you pass in strings as multipart file upload bodies; you
 * have to direct it at an existing file (either with deprecated @ syntax or the CURLFile
 * type). You can use php://temp to get around this for one file, but if you want to upload
 * multiple files then you've got a bit more work.
 *
 * This function manually constructs the multipart request body from strings and injects it
 * into the supplied curl handle, with no need to touch the file system.
 *
 * @param $ch resource curl handle
 * @param $boundary string a unique string to use for the each multipart boundary
 * @param $fields string[] fields to be sent as fields rather than files, as key-value pairs
 * @param $files string[] fields to be sent as files, as key-value pairs
 * @return resource the curl handle with request body, and content type set
 * @see http://stackoverflow.com/a/3086055/2476827 was what I used as the basis for this
 **/
//function buildMultiPartRequest($ch, $boundary, $fields, $files) {
//    $delimiter = '-------------' . $boundary;
//    $data = '';
//    foreach ($fields as $name => $content) {
//        $data .= "--" . $delimiter . "\r\n"
//            . 'Content-Disposition: form-data; name="' . $name . "\"\r\n\r\n"
//            . $content . "\r\n";
//    }
//    foreach ($files as $name => $content) {
//        $data .= "--" . $delimiter . "\r\n"
//            . 'Content-Disposition: form-data; name="' . $name . '"; filename="' . $name . '"' . "\r\n\r\n"
//            . $content . "\r\n";
//    }
//    $data .= "--" . $delimiter . "--\r\n";
//    curl_setopt_array($ch, [
//        CURLOPT_POST => true,
//        CURLOPT_HTTPHEADER => [
//            'Content-Type: multipart/form-data; boundary=' . $delimiter,
//            'Content-Length: ' . strlen($data)
//        ],
//        CURLOPT_POSTFIELDS => $data
//    ]);
//    return $ch;
//}
//// and here's how you'd use it
//$ch = curl_init('http://httpbin.org/post');
//$ch = buildMultiPartRequest($ch, uniqid(),
//    ['key' => 'value', 'key2' => 'value2'], ['somefile' => 'contents!', 'someotherfile' => 'yoloswag']);
//curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//echo curl_exec($ch);
