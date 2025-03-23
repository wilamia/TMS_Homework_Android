package com.example.studyingproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log


class WifiReceiver : BroadcastReceiver() {
    private val TAG = "WifiReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
        var wifiStateText = "No State"

        when (wifiState) {
            WifiManager.WIFI_STATE_DISABLING -> wifiStateText = "WIFI_STATE_DISABLING"
            WifiManager.WIFI_STATE_DISABLED -> wifiStateText = "WIFI_STATE_DISABLED"
            WifiManager.WIFI_STATE_ENABLING -> wifiStateText = "WIFI_STATE_ENABLING"
            WifiManager.WIFI_STATE_ENABLED -> wifiStateText = "WIFI_STATE_ENABLED"
            WifiManager.WIFI_STATE_UNKNOWN -> wifiStateText = "WIFI_STATE_UNKNOWN"
        }

        Log.d(TAG, "Current Wi-Fi state: $wifiStateText")
        println("Wi-Fi connection state changed: $wifiStateText")
    }
}