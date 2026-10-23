
package sesion;

import device.FactoryDevice;
import io.appium.java_client.AppiumDriver;

public class Sesion {

    private static Sesion sesion = null;
    private AppiumDriver device;

    // Crea la sesión utilizando el dispositivo seleccionado.
    private Sesion(String deviceType) {
        device = FactoryDevice.make(deviceType).create();
    }

    // Conserva el comportamiento original para WhenDo.
    public static Sesion getSesion() {
        return getSesion("android");
    }

    // Permite elegir otra aplicación, por ejemplo Gmail.
    public static Sesion getSesion(String deviceType) {
        if (sesion == null) {
            sesion = new Sesion(deviceType);
        }

        return sesion;
    }

    public AppiumDriver getDevice() {
        return device;
    }

    public void closeSesion() {
        device.quit();
        sesion = null;
    }
}
