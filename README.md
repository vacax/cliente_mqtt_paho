## Proyecto Demo MQTT

Demostración sobre el uso MQTT utilizando Java, 
creando Publicadores y Subcriptores mediante el servidor externo de
Mosquitto.

### Ejecutar directamente desde los fuentes

Es necesario tener Java 21 instalando, pueden utilizar https://sdkman.io/

**Linux:**
```
./gradlew run
```

**Windows:**
```
./gradlew.bat run
```

### Ejecutar desde Docker

Deben tener instalado Docker según su sistema operativo https://docs.docker.com/engine/install/

```
docker build -t prueba-mqtt . && docker run --rm -i prueba-mqtt
```