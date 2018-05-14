package org.javadominicano.cmp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SuscriptorCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Conexión Perdida...");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(String.format("Topic: %s - Mensaje Recibido: %s", topic, message.toString()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Información Recibida.");
    }
}
