package com.uzumaki.saurabh.countit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends Activity {
    static int counter = 0;
    TextView tv2;
    String count = "";
    Button bt2;
    EditText ed1;
    static int maxCount = 0;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = new Button(this);
        tv2 = (TextView) findViewById(R.id.doIt);
        start.setText("Start");

        bt2 = (Button) findViewById(R.id.bt2);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed1.setText("0");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Counter.class);
                startActivity(intent);
                counter = 0;

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count = ed1.getText().toString();
                if (Objects.equals(count, "0")) {
                    maxCount = 0;
                } else {
                    maxCount = Integer.parseInt(count);
                }
                if (maxCount == 0)
                    tv2.setText("JUST DO IT!!!");

                ed1.setText("0");
                ed1.clearFocus();

                hideKeyboard();

                counter = 0;
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.BELOW, tv2.getId());
                rl.removeView(start);
                rl.addView(start, lp);
            }
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}