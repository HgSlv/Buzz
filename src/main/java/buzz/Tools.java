package buzz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Tools {

    private static Properties localProperties = null;

    public static Properties getLocalProperties() {
        if (Tools.localProperties == null) {
            setLocalProperties();
        }
        return Tools.localProperties;
    }

    private static void setLocalProperties() {

        Properties localProperties = new Properties();
        InputStream inputStream = null;

        try {

            inputStream = new FileInputStream("local.properties");
            localProperties.load(inputStream);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Tools.localProperties = localProperties;
    }
}
