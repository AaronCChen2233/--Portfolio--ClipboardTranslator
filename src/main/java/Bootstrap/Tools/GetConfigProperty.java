package Bootstrap.Tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfigProperty {
    public static boolean isRunInIDE = false;
    public static boolean isUseColorLog = false;
    public static String vURL = "";
    public static String oFURL = "";
    public static String gISURL = "";
    public static String vDclass = "";
    public static String oFDclass = "";
    public static String Imclass = "";
    public static String Vau = "";
    public static String oFau = "";
    public static String saveFileName = "";

    static {
        InputStream inputStream = null;
        try {
            /*load config file*/
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = GetConfigProperty.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            /*load property*/
            isRunInIDE = Boolean.parseBoolean(prop.getProperty("runInIDE"));
            isUseColorLog = Boolean.parseBoolean(prop.getProperty("useColorLog"));
            vURL = prop.getProperty("vURL");
            oFURL = prop.getProperty("oFURL");
            gISURL = prop.getProperty("gISURL");
            vDclass = prop.getProperty("vDclass");
            oFDclass = prop.getProperty("oFDclass");
            Imclass = prop.getProperty("Imclass");
            Vau = prop.getProperty("Vau");
            oFau = prop.getProperty("oFau");
            saveFileName= prop.getProperty("saveFileName");

            /*Because log will use property so here use println*/
            System.out.println("Load " + propFileName + " Success!");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
