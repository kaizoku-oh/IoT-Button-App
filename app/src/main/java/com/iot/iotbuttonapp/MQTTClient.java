package com.iot.iotbuttonapp;

import android.util.Log;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTClient {
    private static final String TAG = "MQTTClient";
    private String mqttBroker = "tcp://iot.eclipse.org:1883";//iot.eclipse.org
    private String mqttTopic = "esp8266/button";
    private String deviceId = "androidClient";

    private MqttMessage message = new MqttMessage();

    MqttClient client;

    // Variables to store reference to the user interface activity.
    private MainActivity activity = null;

    public MQTTClient(MainActivity activity) {
        this.activity = activity;
    }

    public void connectToMQTT() throws MqttException {
        // Request clean session in the connection options.
        Log.i(TAG, "Setting Connection Options");
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        // Attempt a connection to MQTT broker using the values of
        // connection variables.
        Log.i(TAG, "Creating New Client");
        client = new MqttClient(mqttBroker, deviceId, new MemoryPersistence());
        client.connect(options);
    }

    public void subscribeToMQTT() throws MqttException {
        // Set callback method name that will be invoked when a new message
        // is posted to topic, MqttEventCallback class is defined later in
        // the code.
        Log.i(TAG, "Subscribing to Topic");
        client.setCallback(new MqttEventCallback());
        // Subscribe to topic "esp8266/button", whenever a
        // new message is published to this topic
        // MqttEventCallback.messageArrived will be called.
        client.subscribe(mqttTopic, 0);
    }

    // Implementation of the MqttCallback.messageArrived method, which is
    // invoked whenever a new message is published to the topic
    // "esp8266/button".

    private class MqttEventCallback implements MqttCallback {
        @Override
        public void connectionLost(Throwable arg0) {
            // Do nothing
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken arg0) {
            // Do nothing
        }
        int i = 0;
        @Override
        public void messageArrived(String topic, final MqttMessage msg) throws Exception {
            Log.i(TAG, "New Message Arrived from Topic - " + topic);
            try {
                String sensorMessage = new String(msg.getPayload());
                if (sensorMessage.equalsIgnoreCase("button pressed")) {
                    // Update the screen with newly received message.
                    i++;
                    sensorMessage = "You pressed the button "+i+" times";
                    activity.updateView(sensorMessage);
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage());
            }
        }
    }
}
