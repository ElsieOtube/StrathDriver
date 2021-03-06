package com.example.strathdriver.service;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.strathdriver.CustomerCallActivity;
import com.example.strathdriver.common.Common;
import com.example.strathdriver.model.firebase.Pickup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

public class firebaseMessaging extends FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification().getTitle().equals("Pickup")){
            Pickup pickup = new Gson().fromJson(remoteMessage.getNotification().getBody(), Pickup.class);
            Intent intent = new Intent(getBaseContext(), CustomerCallActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("lat", pickup.getLat());
            intent.putExtra("lng", pickup.getLng());
            intent.putExtra("rider", pickup.getID());
            intent.putExtra("token", pickup.getToken());
            startActivity(intent);
        }

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference tokens=db.getReference(Common.token_tbl);
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
            tokens.child(FirebaseAuth.getInstance().getUid())
                .setValue(token);
    }
}
