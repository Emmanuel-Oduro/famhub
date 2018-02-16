<form method="POST" enctype="multipart/form-data">
    <input type="file" name="image">
    <input type="submit" name="sub">

</form>
<?php
$tex = filter_input(INPUT_POST, "sub");
if (isset($tex)) {

    $productaudio = basename($_FILES['image']['name']);

    $productaudiotemp = explode(".", $_FILES["image"]["name"]);
    $productaudionewfilename = round(microtime(true)) - 1 . '.' . end($productaudiotemp);
    $productaudiocontenttyp = end($productaudiotemp);
    echo $productaudio . "<br>" . $productaudiotemp . "<br>" . $productaudionewfilename . "<br>";

    echo$productaudio . "<br><br>";
    $fp = "upload/" . $productaudionewfilename;

    $zaza = move_uploaded_file($_FILES["image"]["tmp_name"], $fp);
    if ($zaza) {
        echo realpath($fp);
    } else {
        echo 'ohhh';
    }
}