package com.plantflashcards.plantflashcards

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class HighestScoreSynchronizer : BroadcastReceiver() {
    var power = false
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_POWER_CONNECTED)) {
            power = true
            synchronize()
        } else if (intent?.action.equals(Intent.ACTION_GTALK_SERVICE_DISCONNECTED)) {
            power = false
        }
    }

    private fun synchronize() {
        if (power){

        }else{

        }
    }

}
