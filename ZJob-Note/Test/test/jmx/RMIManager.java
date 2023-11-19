package test.jmx;

import javax.management.Attribute;
import javax.management.ObjectName;
import javax.management.remote.rmi.RmiConnector;

public class RMIManager {

    public static void main(String[] args) {
        RmiConnector client = new RmiConnector();
        RmiConnectorAddress address = new RmiConnectorAddress();
        try {
            client.connect(address);
            ObjectName helloWorldName = ObjectName
                    .getInstance("HelloAgent:name=helloWorld1");
            client.invoke(helloWorldName, "sayHello", null, null);
            client.setAttribute(helloWorldName, new Attribute("Hello",
                    new String("hello girls!")));
            client.invoke(helloWorldName, "sayHello", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
