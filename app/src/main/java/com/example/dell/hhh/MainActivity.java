package com.example.dell.hhh;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dell.hhh.view.CircleProgressBar;
import com.example.dell.hhh.view.SecondActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private CircleProgressBar circleProgressBar;
    private float progress = 0;
    private TextView mT1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mT1 = findViewById(R.id.text111);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.circle_progress);
        handler.sendEmptyMessageDelayed(1, 100);
        initAnmint();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (progress <= 99) {
                    ++progress;

                    circleProgressBar.setProgress(progress);  //更新进度条
                    this.sendEmptyMessageDelayed(1, 100);

                } else {

                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
                    finish();
                }
            }
        }
    };


    private void initAnmint() {
        //做缩放动画
        ObjectAnimator scale = ObjectAnimator.ofFloat(mT1, "scaleX", new float[]{0f, 0.5f, 1f, 3f, 1f});
        //做透明动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mT1, "alpha", new float[]{0.2f, 0.4f, 0.6f, 0.8f, 1.0f});
        //做旋转动画
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(mT1, "rotationX", new float[]{0f, 90f, 160f, 270f, 360f});
        AnimatorSet set = new AnimatorSet();
        set.setDuration(5000);
        set.playTogether(scale, alpha, rotationY);
        set.start();
    }


}
