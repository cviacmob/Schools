package com.cviac.s4iApp.notificationreceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.activities.NotificationActivity;
import com.cviac.s4iApp.datamodel.NotificationInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by john on 1/6/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";


    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationInfo info = new NotificationInfo();
        info.setTitle("Renewal");
        info.setDescription("Your membership expires shortly, please apply renewal");
        info.setDate(new Date());
        info.save();

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.school)
                .setContentTitle(info.getTitle())
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentText(info.getDescription());

        Intent resultIntent = new Intent(context, NotificationActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotificationActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());


//        final String MyPREFERENCES = "MyPrefs";
//        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
//
//
//        String lastAlarm = prefs.getString("lastalaram", null);
//        if (lastAlarm != null) {
//            if (lastAlarm.equalsIgnoreCase(getDate())) {
//                return;
//            }
//        }
//        String srt = null;
//        List<NotificationInfo> notelist = NotificationInfo.applaydate();
//
//        int counter = 0;
//        for (NotificationInfo note : notelist) {
//            NotificationInfo notee = new NotificationInfo();
//            notee.setTitle("Reminding your update");
//            notee.setDescription("Update your Membership");
//            notee.setDate(new Date());
//            notee.save();
//            srt = notee.getTitle();
//            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                    .setSmallIcon(R.drawable.school)
//                    .setContentTitle(srt)
//                    .setAutoCancel(true)
//                    .setSound(soundUri)
//                    .setContentText("Update your Membership");
//
//
//            Intent resultIntent = new Intent(context, NotificationActivity.class);
//            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//            // Adds the back stack for the Intent (but not the Intent itself)
//            stackBuilder.addParentStack(NotificationActivity.class);
//            // Adds the Intent that starts the Activity to the top of the stack
//            stackBuilder.addNextIntent(resultIntent);
//            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            mBuilder.setContentIntent(resultPendingIntent);
//            NotificationManager mNotificationManager =
//                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            // mId allows you to update the notification later on.
//
//            mNotificationManager.notify(counter, mBuilder.build());
//            counter++;
//        }
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("lastalaram", getDate());
//        editor.commit();

    }

    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

}
