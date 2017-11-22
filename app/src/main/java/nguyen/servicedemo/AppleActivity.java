package nguyen.servicedemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import nguyen.servicedemo.R;

public class AppleActivity extends AppCompatActivity {


    Button appleButton;

    //Build the object that is going to be the notification itself
    NotificationCompat.Builder notification;

    private static final int uniqueID = 45233; //the notification has to be assigned a unique ID



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);

        //Build the new notification
        notification = new NotificationCompat.Builder(this);
        //remove notification once it has been visited
        notification.setAutoCancel(true);

        Intent i = new Intent(this, MyIntentService.class);
        startService(i);

        Bundle data = getIntent().getExtras();

        if(data == null) {
            return;
        }

        String msg = data.getString("msg");
        final TextView mEditTV = (TextView) findViewById(R.id.editTV);
        mEditTV.setText(msg);
    }

    public void baconButton (View view) {

        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is the title");
        notification.setContentText("I am the body text of your notification");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(alarmSound);

        //send the notification to the apple activity
        Intent appleIntent = new Intent(this, AppleActivity.class);

        //give the device access to perform this intent by calling the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, appleIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //send out this notification
        NotificationManager mn = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mn.notify(uniqueID, notification.build());

        didTapButton(view);
        Intent i = new Intent(this, BaconActivity.class);
        startActivity(i);
    }

    public void didTapButton(View view) {

        appleButton = (Button) findViewById(R.id.baconButton);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //use bounce interpolator with amplitude 0.2 and frequency 20

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);

        myAnim.setInterpolator(interpolator);

        appleButton.startAnimation(myAnim);
    }
}
