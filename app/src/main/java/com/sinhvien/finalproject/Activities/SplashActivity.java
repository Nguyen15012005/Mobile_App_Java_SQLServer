package com.sinhvien.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinhvien.finalproject.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 4500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        //tạo đối tượng view
        ImageView IMGLogo = (ImageView)findViewById(R.id.imgLogo);

        //lấy đối tượng animation
        Animation sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);


        //thiết lập animation cho component
        IMGLogo.setAnimation(sideAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish(); //destroy activity khi back sẽ ko về splash
            }
        },SPLASH_TIMER);
    }
}