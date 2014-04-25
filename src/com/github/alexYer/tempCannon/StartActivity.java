package com.github.alexYer.tempCannon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Никита on 19.04.14.
 */


public class StartActivity extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // если хотим, чтобы приложение постоянно имело портретную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // если хотим, чтобы приложение было полноэкранным
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // и без заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.menumain);
        //setContentView(new GameView(this,null));

        Button startButton = (Button)findViewById(R.id.button1);
        startButton.setOnClickListener(this);

        Button exitButton = (Button)findViewById(R.id.button2);
        exitButton.setOnClickListener(this);
    }

    /** Обработка нажатия кнопок */
    public void onClick(View v) {
        switch (v.getId()) {
            //переход на сюрфейс
            case R.id.button1: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
            }break;

            //выход
            case R.id.button2: {
                finish();
            }break;

            default:
                break;
        }
    }
}
