package nguyen.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand method called");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++) {
                    long futureTime = System.currentTimeMillis() + 5000;
                    while(System.currentTimeMillis() < futureTime) {
                        synchronized (this) {
                            try {
                                wait(futureTime - System.currentTimeMillis());
                                Log.i(TAG, "Service is doing something");
                            } catch (Exception e) {

                            }
                        }
                    } //end while loop
                } //end for loop
            } //end run method
        }; //end Runnable

        //get all your code is the thread and start it

        Thread myThread = new Thread(r);
        myThread.start();

        //restart the service after user turns it off
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy method Call");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
