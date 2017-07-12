package com.uzumaki.saurabh.countit;

import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Counter extends AppCompatActivity {
    TextView tv1;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("0");
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK once again to go back", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void goBack(View view){
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (MainActivity.maxCount == 0) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                tv1.setText("" + String.valueOf(++MainActivity.counter));
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                tv1.setText("" + String.valueOf(++MainActivity.counter));
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            Vibrator v = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

            if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                tv1.setText("" + String.valueOf(++MainActivity.counter));
                if (MainActivity.counter == MainActivity.maxCount - 3) {
                    v.vibrate(200);
                } else if (MainActivity.counter == MainActivity.maxCount - 2) {
                    v.vibrate(200);
                } else if (MainActivity.counter == MainActivity.maxCount - 1) {
                    v.vibrate(200);
                } else if (MainActivity.counter == MainActivity.maxCount) {
                    v.vibrate(750);
                }
                return true;
            }
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                tv1.setText("" + String.valueOf(++MainActivity.counter));

                if (MainActivity.counter == MainActivity.maxCount - 3) {
                    v.vibrate(200);
                } else if (MainActivity.counter == MainActivity.maxCount - 2) {
                    v.vibrate(200);
                } else if (MainActivity.counter == MainActivity.maxCount - 1) {
                    v.vibrate(200);
                } else if (MainActivity.counter == MainActivity.maxCount) {
                    v.vibrate(750);
                }
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
    }

}