package org.javadominicano.cmp;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Representa el suscriptor
 */
public class Suscriptor {

    public static final String BROKER_URL = "tcp://test.mosquitto.org:1883";
    private MqttClient client;

    public Suscriptor() {

        String clientId = "suscriptor-1";
        try {
            client = new MqttClient(BROKER_URL, clientId);
        }
        catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Inicio de la 
     */
    public void start() {

        try {
            client.setCallback(new SuscriptorCallback());
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setCleanSession(false);  // habilitando la opción de persistencia.
            client.connect(connectOptions);
            client.subscribe("/casa/cocina/#"); // Recuperando la información desde la jerarquia superior.
            client.subscribe("/casa/sala/#");
        }
        catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
