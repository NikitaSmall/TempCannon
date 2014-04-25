package com.github.alexYer.tempCannon;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Никита on 19.04.14.
 */


public class MenuActivity extends Activity implements OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.menumain);
        //setContentView(new GameView(this,null));

        Button startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            //переход на сюрфейс
            case R.id.startButton: {
                Intent intent = new Intent();
                intent.setClass(this, GameActivity.class);
                startActivity(intent);
            }break;

            //выход
            case R.id.exitButton: {
                finish();
                System.exit(0);
            }break;

            default:
                break;
        }
    }
}
