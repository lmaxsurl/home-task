package com.example.android.hometask1.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.hometask1.LocalService;
import com.example.android.hometask1.R;

public class HomeTask5Activity extends AppCompatActivity {

    private Button switchButton;
    private TextView textView;
    private BroadcastReceiver wifiReceiver;
    private LocalService localService;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HomeTask5Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ht5);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver, intentFilter);

        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiReceiver);
        unbindService(connection);
    }

    private void showState(int state){
        switch (state) {
            case WifiManager.WIFI_STATE_ENABLED:
                textView.setText(R.string.enabled);
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                textView.setText(R.string.enabled);
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                textView.setText(R.string.disabled);
                break;
            case WifiManager.WIFI_STATE_DISABLING:
                textView.setText(R.string.disabled);
                break;
            default:
                textView.setText("Error");
                break;
        }
    }

    private void init() {
        switchButton = findViewById(R.id.ht5_switch_button);
        textView = findViewById(R.id.ht5_text_view);
        switchButton = findViewById(R.id.ht5_switch_button);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localService.changeWifiState((WifiManager) HomeTask5Activity.this
                        .getApplicationContext().getSystemService(WIFI_SERVICE));
            }
        });

        wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                    showState(intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1));
                }
            }
        };
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) iBinder;
            localService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}

