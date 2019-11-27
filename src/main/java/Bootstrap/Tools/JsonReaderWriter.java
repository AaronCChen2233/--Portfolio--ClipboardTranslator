package Bootstrap.Tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class JsonReaderWriter {
    /***
     * @param path
     * @return
     */
    public static ArrayList<String> stringListReader(String path, String objName) {
        ArrayList<String> arrayList = new ArrayList<String>();

        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader(path);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray msg = (JSONArray) jsonObject.get(objName);
            arrayList = (ArrayList<String>) msg.iterator();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static JSONObject readerObject(String path) {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(path)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            return jsonObject;
        } catch (Exception ex) {
            return null;
        }
    }

    /***
     * @param path
     * @param obj
     * @return isWriteSuccess
     */
    public static boolean writerObject(String path, Object obj) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(JSONValue.toJSONString(obj));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
