package com.example.android.hometask1;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class LocalService extends Service {

    private final IBinder binder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }

    public void changeWifiState(WifiManager wifiManager){
        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
    }
}
