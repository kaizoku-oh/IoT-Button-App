package com.iot.iotbuttonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.MqttException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        MQTTClient mqttClient = new MQTTClient(this);
        try {
            mqttClient.connectToMQTT();
            mqttClient.subscribeToMQTT();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void updateView(final String sensorMessage) {
        //textView.setText(sensorMessage); --> Exception
        //Only the original thread that created a view hierarchy can touch its views.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(sensorMessage);
            }
        });
    }
}
