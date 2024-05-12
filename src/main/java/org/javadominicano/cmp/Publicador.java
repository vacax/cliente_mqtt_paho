package org.javadominicano.cmp;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;

/**
 * 
 */
public class Publicador {

    private static final String BROKER_URL = "tcp://test.mosquitto.org:1883";
    private MqttClient client;

    public Publicador(String id){
        String clientId = id;
        try {
            client = new MqttClient(BROKER_URL, clientId);
            
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 
     * @param topic
     * @param mensaje
     */
    public void enviarMensaje(String topic, String mensaje){
        System.out.println("Enviando Información Topic: "+ topic);
        try {
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setCleanSession(false);
            client.connect(connectOptions);
            client.publish(topic, mensaje.getBytes(), 2, false);
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    public static void iniciarPrueba() throws Exception {
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Gson gson = new Gson();
                    new Publicador("sensora").enviarMensaje("/casa/cocina/sensora/temp-humedad", gson.toJson(new Sensor("sensora")));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hilo1.start();

        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Gson gson = new Gson();
                    new Publicador("sensorb").enviarMensaje("/casa/sala/sensorb/temp-humedad", gson.toJson(new Sensor("sensorb")));

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        hilo2.start();

    }
}
