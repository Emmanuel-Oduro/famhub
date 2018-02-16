package com.example.nanayawzaza.farmiconn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.nanayawzaza.farmiconn.ApiRepository.Api;
import com.example.nanayawzaza.farmiconn.Utils.Config;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PublishProduct extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = PublishProduct.class.getSimpleName();
    /* home settings*/

    ImageView imageView;
    LinearLayout pickim, takebtn;
    ImageButton ShowVideoDialog, ShowAudioDialog, LocationPicker;
    private static final int PICK_IMAGE = 100;
    MediaPlayer mediaPlayer;
    private Button mButtomrecord, mPlay, addProduct;
    private VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP = 1;
    private Uri video_uri, imageUri;
    String videoRealPath, imageRealPath, audioRealPath,LocationValue, farmerAddress;
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    TextView loc;
    String selectedItemText;
    Spinner categoryText;
    EditText productname, productPrice, productDescription;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_product);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        farmerAddress = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, "");

        Utilities.disPlayToast(this, farmerAddress);
        categoryText = findViewById(R.id.planets_spinner);
        categoryText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemText = (String) adapterView.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        imageView = findViewById(R.id.myimage);
        pickim = findViewById(R.id.imageUpload);
        takebtn = findViewById(R.id.TakeShot);
        addProduct = findViewById(R.id.addProduct);
        ShowVideoDialog = findViewById(R.id.ShowVideoDialog);
        ShowAudioDialog = findViewById(R.id.ShowAudioDialog);
        LocationPicker = findViewById(R.id.LocationPicker);
        productname = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);
        loc=findViewById(R.id.loce);
        //IMAGE UPLOADS
        pickim.setOnClickListener(this);
        takebtn.setOnClickListener(this);


        //OTHER FILES UPLOAD
        ShowVideoDialog.setOnClickListener(this);
        ShowAudioDialog.setOnClickListener(this);
        LocationPicker.setOnClickListener(this);
        addProduct.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.imageUpload) {
            LoadImage();
        } else if (id == R.id.TakeShot) {
            TakeShort();
        } else if (id == R.id.ShowVideoDialog) {
            DisplayVideoDialog();
        } else if (id == R.id.ShowAudioDialog) {
            DisplayAudioDialog();
        } else if (id == R.id.LocationPicker) {
            GetLocation();
        } else if (id == R.id.addProduct) {
            ADDproduct();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK) {
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {
                Utilities.disPlayToast(this, "Did not work");
            }

            video_uri = data.getData();
            mVideoView.setVideoURI(video_uri);
            videoRealPath = getRealPathFromURI(this, video_uri);
            Utilities.displayLog(TAG + " videoRealPath ", videoRealPath);
        } else if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_CANCELED) {
            Utilities.disPlayToast(this, "Did not work");

        } else if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                imageRealPath = getRealPathFromURI(this, imageUri);
                Utilities.displayLog(TAG + " ImageRealPath ", imageRealPath);

                imageView.setImageURI(imageUri);
            } else {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }


    }

    public void LoadImage() {
        Intent ga = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(ga, PICK_IMAGE);
    }

    public void TakeShort() {
      /*  Calendar cal = Calendar.getInstance();
        File file = new File(Environment.getExternalStorageDirectory(),  (cal.getTimeInMillis()+".jpg"));
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Uri capturedImageUri = Uri.fromFile(file);
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
        startActivityForResult(i, PICK_IMAGE);
        Utilities.disPlayToast(this, String.valueOf(capturedImageUri));
      *//*  Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Calendar cal = Calendar.getInstance();
        File f = new File(Environment.getExternalStorageDirectory(),  (cal.getTimeInMillis()+".jpg"));
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
*//*


        //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Intent ga=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(ga, PICK_IMAGE);
     *//*   Calendar cal = Calendar.getInstance();
        File file = new File(Environment.getExternalStorageDirectory(),  (cal.getTimeInMillis()+".jpg"));
        if(!file.exists()){
            try {
                file.createNewFile();
                Utilities.disPlayToast(this, String.valueOf(file));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }*//*
        //startActivityForResult(ga, PICK_IMAGE);*/
    }


    public void DisplayVideoDialog() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_videorecord, null);
        mVideoView = mView.findViewById(R.id.videoView);
        mButtomrecord = mView.findViewById(R.id.btnrecord);
        mPlay = mView.findViewById(R.id.playv);

        //play recorded video
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.start();
            }
        });

        // record video
        mButtomrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camstart = new Intent();
                camstart.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(camstart, ACTIVITY_START_CAMERA_APP);
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void DisplayAudioDialog() {
        final AlertDialog.Builder audBuilder = new AlertDialog.Builder(PublishProduct.this);
        @SuppressLint("InflateParams") final View audView = getLayoutInflater().inflate(R.layout.alert_audio, null);
        buttonStart = audView.findViewById(R.id.recorau);
        buttonStop = audView.findViewById(R.id.stopau);
        buttonPlayLastRecordAudio = audView.findViewById(R.id.playau);
        buttonStopPlayingRecording = audView.findViewById(R.id.playstop);
        //parth = (TextView) audView.findViewById(R.id.textView);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);
        random = new Random();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {

                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    CreateRandomAudioFileName(5) + "AudioRecording.mp3";

                    MediaRecorderReady();

                    audioRealPath = AudioSavePathInDevice;
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    buttonStart.setEnabled(false);
                    buttonStop.setEnabled(true);

                    Toast.makeText(PublishProduct.this, "Recording started",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }

            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                Utilities.disPlayToast(PublishProduct.this, "Recording Completed");
                Utilities.displayLog(TAG + " Audio >", audioRealPath);
            }
        });
        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Utilities.disPlayToast(PublishProduct.this, "Recording Playing");

            }
        });
        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MediaRecorderReady();
                }
            }
        });


        audBuilder.setView(audView);
        AlertDialog dialog = audBuilder.create();
        dialog.show();

    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String CreateRandomAudioFileName(int string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int i = 0;
        while (i < string) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++;
        }
        return stringBuilder.toString();
    }

    private void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(PublishProduct.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    private void GetLocation() {
        LocationValue = getIntent().getExtras().getString("location");
      // onCurrentLocationClick();

    }

    private void ADDproduct() {

        if (TextUtils.isEmpty(imageRealPath)) {
            Utilities.disPlayToast(this, "Please Add the Product Image");
            return;
        }
        if (TextUtils.isEmpty(productname.getText())) {
            productname.setError("Please add the product Nane");
            productname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(LocationValue)) {
            Utilities.disPlayToast(this, "Please Add your location");
            return;
        }
        if (selectedItemText.equals("Category")) {
            Utilities.disPlayToast(this, "Please Pick a Category");
            return;
        }

        postProduct(imageRealPath, audioRealPath, videoRealPath);

    }


    public void postProduct(String imageRealPath, String audioRealPath, String videoRealPath) {
        File imageFile = new File(imageRealPath);
        MultipartBody.Part image;
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        image = MultipartBody.Part.createFormData("image", imageFile.getName(), imageBody);

        File audioFile = new File(audioRealPath);
        MultipartBody.Part audio;
        RequestBody audioBody = RequestBody.create(MediaType.parse("multipart/form-data"), audioFile);
        audio = MultipartBody.Part.createFormData("audio", audioFile.getName(), audioBody);

        File videoFile = new File(videoRealPath);
        MultipartBody.Part video;
        RequestBody videoBody = RequestBody.create(MediaType.parse("multipart/form-data"), videoFile);
        video = MultipartBody.Part.createFormData("video", videoFile.getName(), videoBody);

        RequestBody productName = RequestBody.create(MediaType.parse("multipart/form-data"), productname.getText().toString());

        RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"), productPrice.getText().toString());

        RequestBody farmer = RequestBody.create(MediaType.parse("multipart/form-data"), farmerAddress);

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), productDescription.getText().toString());

        RequestBody location = RequestBody.create(MediaType.parse("multipart/form-data"), LocationValue);

        RequestBody category = RequestBody.create(MediaType.parse("multipart/form-data"), selectedItemText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.IP_ADDRESS + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);


        Call<RequestBody> call = api.upload(productName, price, farmer, description, location, category, image, video, audio);

        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(@NonNull Call<RequestBody> call, @NonNull Response<RequestBody> response) {
                RequestBody apiResponse = response.body();

                Utilities.displayLog(TAG + " >product Add >>", "yes "+apiResponse.toString());
                Utilities.disPlayToast(PublishProduct.this, "Product has successfully been added");
                Intent intent =new Intent(PublishProduct.this,ProductByMail.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<RequestBody> call, @NonNull Throwable t) {
                Utilities.displayLog(TAG + " >product Add >>", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }





}
