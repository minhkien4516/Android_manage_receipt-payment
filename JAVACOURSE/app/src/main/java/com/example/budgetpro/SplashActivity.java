package com.example.budgetpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView textView;
    EditText editText,editText1,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //hooks
        image=findViewById(R.id.imageView2);
        textView=findViewById(R.id.textView4);
        editText=findViewById(R.id.editTextTextMultiLine);
        editText1=findViewById(R.id.editTextTextMultiLine2);
        editText2=findViewById(R.id.editTextTextMultiLine3);

        image.setAnimation(topAnim);
        textView.setAnimation(topAnim);
        editText.setAnimation(bottomAnim);
        editText1.setAnimation(bottomAnim);
        editText2.setAnimation(bottomAnim);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logo");
                pairs[1]=new Pair<View,String>(textView,"text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        }, 5000);
    }
}