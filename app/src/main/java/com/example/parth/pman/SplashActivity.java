package com.example.parth.pman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {
    Thread splashTread;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        loadAnim();
    }
public void loadAnim(){
        Animation animation = AnimationUtils.loadAnimation( this,R.anim.alpha );
        animation.reset();
        ConstraintLayout r =findViewById( R.id.cslyt );
r.clearAnimation();
r.startAnimation( animation );
animation =AnimationUtils.loadAnimation( this,R.anim.translate );
animation.reset();
ImageView img = findViewById( R.id.imgLogo );
img.clearAnimation();
img.startAnimation( animation );
  TextView txt = findViewById( R.id.maintext );
    Animation txanim;
    txanim= AnimationUtils.loadAnimation( this, R.anim.alpha2 );
    txanim.reset();
   txanim =AnimationUtils.loadAnimation( this,R.anim.translatetx );
    txanim.reset();
    txanim.reset();
    txt.clearAnimation();
    txt.startAnimation( txanim );
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2500) {
                        sleep(100);
                        waited += 80;
                    }
                    Intent intent = new Intent(SplashActivity.this,
                           Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();
    }
}
