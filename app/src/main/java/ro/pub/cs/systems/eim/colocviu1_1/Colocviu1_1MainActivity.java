package ro.pub.cs.systems.eim.colocviu1_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_1MainActivity extends AppCompatActivity {

    Button northBtn, eastBtn, westBtn, southBtn;
    Button secondActivityBtn;
    TextView textView;
    Integer counter = 0;

    int serviceStatus = 0;

    private IntentFilter intentFilter = new IntentFilter();


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
            special(counter);
        });

        eastBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,East";
            textView.setText(value);
            counter++;
            special(counter);
        });
        southBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,South";
            textView.setText(value);
            counter++;
            special(counter);
        });

        westBtn.setOnClickListener(view -> {
            String value = textView.getText().toString();
            value += " ,West";
            textView.setText(value);
            counter++;
            special(counter);
        });

        Log.d("[Counter]", String.valueOf(counter));

        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_1SecondaryActivity.class);
                intent.putExtra("counter", counter);
                startActivityForResult(intent, 1);
            }
        });

        intentFilter.addAction("ro.pub.cs.systems.eim.colocviu1_1.instruction");
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

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "The button pressed was register", Toast.LENGTH_LONG).show();
            } else {
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "The button pressed was cancel", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void special(Integer counter) {
        if (counter > 4) {
            Intent intent = new Intent(getApplicationContext(), Colocviu1_1Service.class);
            intent.putExtra("instruction", counter);
            getApplicationContext().startService(intent);
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_1Service.class);
        stopService(intent);
        super.onDestroy();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

}
