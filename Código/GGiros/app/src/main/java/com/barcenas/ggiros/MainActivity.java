package com.barcenas.ggiros;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
      import java.util.List;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
    /** Called when the activity is first created. */
    TextView x,y,z;
    private  Sensor mAccelerometer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x = (TextView)findViewById(R.id.xID);
        y = (TextView)findViewById(R.id.yID);
        z = (TextView)findViewById(R.id.zID);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void onResume()
    {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) //dispositivo android tiene acelerometro
        {
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }
    protected void onPause()
    {
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, mAccelerometer);
        super.onPause();
    }
    protected void onStop()
    {
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, mAccelerometer);
        super.onStop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        this.x.setText("X = "+event.values[SensorManager.DATA_X]);
        this.y.setText("Y = "+event.values[SensorManager.DATA_Y]);
        this.z.setText("Z = "+event.values[SensorManager.DATA_Z]);

        if(event.values[SensorManager.DATA_X] >= 1.5f) { // izquierda
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        } else if(event.values[SensorManager.DATA_X] <= -1.5f) { // derecha
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

        }else if(event.values[SensorManager.DATA_X] <= 1.5f && event.values[SensorManager.DATA_X] >= -1f) { // clockwise
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);}
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}