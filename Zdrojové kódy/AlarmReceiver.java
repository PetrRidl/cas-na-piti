package com.example.drinkingtime;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent){
        HlavniStrana hlavniStrana = new HlavniStrana();


        Intent i = new Intent(context,PridaniNapoje.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);

        NotificationCompat.Builder builder =  new NotificationCompat.Builder(context, "drinkingtime")
                .setContentTitle("Je čas na pití")
                .setSmallIcon(R.color.white)
                .setContentText("Je na čase vypít přibližně toto množství " + HlavniStrana.kolikVypit/10+ " ml vody")
                .setAutoCancel(false)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());



    }
}
