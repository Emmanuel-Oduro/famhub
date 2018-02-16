package com.example.nanayawzaza.farmiconn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.nanayawzaza.farmiconn.Adopter.Products;
import com.example.nanayawzaza.farmiconn.Utils.Utilities;

import de.hdodenhof.circleimageview.CircleImageView;

public class FullProductInfoDisplaer extends AppCompatActivity {
    TextView Productname, Price, Datecreated, Location, category, Describtion, name, phone, email/*, Audio*/;
    ImageView imageView, emailico, call,callmsg;
    VideoView Video;
    CircleImageView circleImageView;
    Button audioPlay, audioStiop;
    MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_product_info_displaer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //audioStiop.setEnabled(false);
        email = findViewById(R.id.farmemail);
        name = findViewById(R.id.farmdname);
        phone = findViewById(R.id.farmcontact);
        imageView = findViewById(R.id.productpic);
        Productname = findViewById(R.id.proname);
        category = findViewById(R.id.procategory);
        Price = findViewById(R.id.cost);
        Location = findViewById(R.id.locat);
        Describtion = findViewById(R.id.description);
        callmsg = findViewById(R.id.callmsg);
        callmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + String.valueOf(phone))));
            }
        });
        Video = findViewById(R.id.video);
       // Audio = findViewById(R.id.audio);
        Datecreated = findViewById(R.id.date);
        emailico = findViewById(R.id.email);
        emailico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.EXTRA_EMAIL);
                callIntent.setData(Uri.parse(String.valueOf(email)));
                if (ActivityCompat.checkSelfPermission(FullProductInfoDisplaer.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
        call = findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(String.valueOf(phone)));
                if (ActivityCompat.checkSelfPermission(FullProductInfoDisplaer.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
    }
});

        String proname = getIntent().getExtras().getString("proname");
        String proprice = getIntent().getExtras().getString("price");
        String proimg = getIntent().getExtras().getString("pimg");
        String plocation = getIntent().getExtras().getString("plocation");
        String pdate = getIntent().getExtras().getString("pdate");
        String pfname = getIntent().getExtras().getString("pfname");
        String pfphone = getIntent().getExtras().getString("pfphone");
        String pfemail = getIntent().getExtras().getString("pfemail");
        final String paudioa = getIntent().getExtras().getString("paudioa");
        String pvideo = getIntent().getExtras().getString("pvideo");
        String cate = getIntent().getExtras().getString("cat");
        String Des = getIntent().getExtras().getString("des");

        Productname.setText(proname);
        Price.setText(proprice);
        Describtion.setText(Des);
        Location.setText(plocation);
        category.setText(cate);
        name.setText(pfname);
        email.setText(pfemail);
        phone.setText(pfphone);
        Datecreated.setText(pdate);
        Uri uri = Uri.parse(pvideo);
        Video.setVideoURI(uri);
        Video.pause(); // pause a video
        MediaController mediaController = new MediaController(this);
        Video.setMediaController(mediaController);
        //mediaController.show();
        mediaController.setAnchorView(Video);

        //loading the image
        Glide.with(FullProductInfoDisplaer.this)
                .load(proimg)
                .into(imageView);

        Products products = new Products();


        Utilities.disPlayToast(this, products.getEmail());
        audioPlay = findViewById(R.id.butplay);
        audioStiop = findViewById(R.id.butstop);
        audioPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioStiop.setEnabled(true);
                try {
                    playAudio(paudioa);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(FullProductInfoDisplaer.this, "Recording Playing", Toast.LENGTH_LONG).show();

            }
        });
        audioStiop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stop audio
                audioPlay.setEnabled(false);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                        playbackPosition = 0;

                    mediaPlayer.release();

                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }
    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
