package com.example.rcarb.flightservice.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.rcarb.flightservice.utilities.IntentActions;

/**
 * Created by rcarb on 3/7/2018.
 */

public class ProcessSecondAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra(IntentActions.INTENT_SEND_STRING_FLIGHT);
        long column = intent.getLongExtra(IntentActions.INTENT_SEND_FLIGHT_COLUMN_ID, -2);
        String status = intent.getStringExtra(IntentActions.INTENT_SEND_FLIGHT_STATUS);
        int requestCode = intent.getIntExtra(IntentActions.INTENT_REQUEST_CODE, -2);

        Intent sendFOrUpdate = new Intent(IntentActions.ACTION_GET_SECOND_STATUS_FLIGHT);
        sendFOrUpdate.putExtra(IntentActions.INTENT_SEND_STRING,name);
        sendFOrUpdate.putExtra(IntentActions.INTENT_SEND_FLIGHT_COLUMN_ID, column);
        sendFOrUpdate.putExtra(IntentActions.INTENT_SEND_FLIGHT_STATUS, status);
        sendFOrUpdate.putExtra(IntentActions.INTENT_REQUEST_CODE, requestCode);
        context.sendBroadcast(sendFOrUpdate);


    }
}
