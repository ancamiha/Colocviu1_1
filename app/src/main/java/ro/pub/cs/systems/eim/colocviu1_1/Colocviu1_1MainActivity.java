package ro.pub.cs.systems.eim.colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviu1_1MainActivity extends AppCompatActivity {

    Button northBtn, eastBtn, westBtn, southBtn;
    Button secondActivityBtn;
    TextView textView;
    Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_1_main);

        northBtn = findViewById(R.id.northBtn);
        eastBtn = findViewById(R.id.eastBtn);
        southBtn = findViewById(R.id.southBtn);
        westBtn = findViewById(R.id.westBtn);

        secondActivityBtn = findViewById(R.id.secondActivityBtn);
        textView = findViewById(R.id.tv);

        northBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,North";
            textView.setText(value);
            counter++;
        });

        eastBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,East";
            textView.setText(value);
            counter++;
        });
        southBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,South";
            textView.setText(value);
            counter++;
        });

        westBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,West";
            textView.setText(value);
            counter++;
        });

        Log.d("[Counter]", String.valueOf(counter));
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("counter", counter);
        Log.d("[Counter]", String.valueOf(counter));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("counter")) {
            counter = savedInstanceState.getInt("counter");
        } else {
            counter = 0;
        }
        Log.d("[Counter]", String.valueOf(counter));
    }
}