package com.weibo.fullwindowimgdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    View v ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(v);
        addImage();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    void addImage() {

        final WindowManager windowManager = getWindowManager();
        final ImageView imageView = getImageView();

        WindowManager.LayoutParams wlp = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);

        wlp.x = 0;
        wlp.y = 0;

        windowManager.addView(imageView, wlp);
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0.4f),
                ObjectAnimator.ofFloat(imageView, "scaleY", 1, 0.4f)
        );
        animatorSet.setDuration(4500);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animatorSet.start();
            }
        }, 3000);

    }

    ImageView getImageView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.images);
        return imageView;
    }

}
