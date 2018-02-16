<form method="POST" enctype="multipart/form-data" >
    <input type="file" name="image" id="file1">    
    <input type="text" name="image" id="file">

    <input type="button" name="sub" onclick="getPath()">
    
</form>
<script language="javascript" type="text/javascript">
function getPath() {
     var inputName = document.getElementById('file1'); 
     var Name = document.getElementById('file');

     var imgPath;

     imgPath = inputName.value;
     Name.value=imgPath;
    // alert(imgPath);
}
</script>
<?php //
//if (isset($_POST["sub"])){
//    $path=$_FILES["image"]["name"];
//   // $productaudiotemp = explode(".", $_FILES["image"]["name"]);
////	$productaudionewfilename = round(microtime(true))-1 . '.' . end($productaudiotemp);
//        
//    //     $productaudiocontenttyp=end($productaudiotemp);
//    echo dirname($_FILES['image']['tmp_name']);  
//    var_dump( pathinfo($path)) ;
//    echo $_FILES['image']['tmp_name'];
//    
//    echo realpath($path);  //  echo realpath($productaudionewfilename);
//
//}