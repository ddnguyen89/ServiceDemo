package nguyen.servicedemo;

/**
 * Created by 660253185 on 11/20/2017.
 */

public class MyBounceInterpolator implements android.view.animation.Interpolator {

    private double mAmplitude = 1;
    private double mFrequency = 10;

    public MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }
    @Override
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, - time / mAmplitude) *
            Math.cos(mFrequency * time) + 1);
    }


}
