package pageObjects;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Props {
    static Properties properties;

    public static void loadRunConfigProps() throws IOException {
        properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/config/config.properties");
            properties.load(input);

        } catch (FileNotFoundException e) {
            e.getMessage();

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }


    }


    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);
        }
    }

}
