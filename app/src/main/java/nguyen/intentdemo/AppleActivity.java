package nguyen.intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AppleActivity extends AppCompatActivity {


    Button appleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);

        Bundle data = getIntent().getExtras();

        if(data == null) {
            return;
        }

        String msg = data.getString("msg");
        final TextView mEditTV = (TextView) findViewById(R.id.editTV);
        mEditTV.setText(msg);
    }

    public void baconButton (View view) {

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
