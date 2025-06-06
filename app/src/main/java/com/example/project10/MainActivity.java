package com.example.project10;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkChangeListener {

    private Button internetButton;
    private NetworkChangeReceiver receiver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internetButton = findViewById(R.id.internetButton);
        internetButton.setVisibility(View.GONE);
        internetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Move =new Intent(MainActivity.this,MainActivity2.class);
            startActivity(Move);
            }
        });
        receiver = new NetworkChangeReceiver();
        NetworkChangeReceiver.listener = this;

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onNetworkChanged(boolean isConnected) {
        if (isConnected) {
            internetButton.setVisibility(View.VISIBLE);
        } else {
            internetButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}