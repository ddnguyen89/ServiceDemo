package nguyen.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import nguyen.servicedemo.R;

public class BaconActivity extends AppCompatActivity {

    Button baconButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacon);

        Intent i = new Intent(this, MyService.class);
        startService(i);
    }

    public void appleButton(View view) {

        final EditText mBaconET = (EditText) findViewById(R.id.baconET);
        Intent i = new Intent(this, AppleActivity.class);

        String msg = mBaconET.getText().toString();
        i.putExtra("msg", msg);

        didTapButton(view);

        startActivity(i);
    }

    public void didTapButton(View view) {

        baconButton = (Button) findViewById(R.id.appleButton);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        //use bounce interpolator with amplitude 0.2 and frequency 20

        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);

        myAnim.setInterpolator(interpolator);

        baconButton.startAnimation(myAnim);
    }
}
