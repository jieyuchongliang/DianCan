package com.fujisoft.diancan.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.fujisoft.diancan.MainActivity;
import com.fujisoft.diancan.R;
import com.fujisoft.diancan.customview.CustomVideoView;

public class GuideActivity extends AppCompatActivity {
    private VideoView videoview;
    private Button button;
    private TextView textView;
    private final int HANDLER_TYPE = 1;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_TYPE:
                    changTime();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        View decorview = getWindow().getDecorView(); //1.全屏状态
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorview.setSystemUiVisibility(option);

        initView();
    }

    private void initView() {
        videoview = (VideoView) findViewById(R.id.videoview);
        videoview = (CustomVideoView) findViewById(R.id.videoview);
        //设置播放加载路径
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.guide_3));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //videoview.start();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        button = (Button) findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
        textView = (TextView) findViewById(R.id.tv_time);
        changTime();
    }

    private int time = 4;

    private void changTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(HANDLER_TYPE);
                        if (time == 0) {
                            textView.setText("进入");
                        }
                        textView.setText(time + "秒");
                        time--;
                    }
                });
            }
        }).start();
    }
}
