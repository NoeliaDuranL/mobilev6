package device;

import java.util.HashMap;
import java.util.Map;

public class FactoryDevice {

    public static IDevice make(String deviceType){
        Map<String,IDevice> deviceMap = new HashMap<>();
        deviceMap.put("android", new Android());
        deviceMap.put("ios", new Ios());
        deviceMap.put("cloud", new Cloud());
        deviceMap.put("gmail", new GmailAndroid());
        return  deviceMap.containsKey(deviceType.toLowerCase())?
                deviceMap.get(deviceType.toLowerCase()):
                new Android();
    }

}
