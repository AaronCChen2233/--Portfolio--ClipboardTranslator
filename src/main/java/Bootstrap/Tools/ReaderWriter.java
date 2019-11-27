package Bootstrap.Tools;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ReaderWriter {
    /***
     * @param path
     * @return DataStringList
     */
    public static ArrayList<String> reader(String path) {
        ArrayList<String> lines = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            isr = new InputStreamReader(new FileInputStream(path));
            reader = new BufferedReader(isr);
            String line = null;
            lines = new ArrayList<String>();

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    /***
     * @param path
     * @param pushData
     * @return is Write Success
     */
    public static boolean pushWriter(String path, String pushData) {
        InputStreamReader isr = null;
        BufferedReader reader = null;
        BufferedWriter bw = null;
        try {
            /*Read*/
            isr = new InputStreamReader(new FileInputStream(path));
            reader = new BufferedReader(isr);

            /*keep in lines*/
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            /*write*/
            bw = new BufferedWriter(new FileWriter(path));
            for (String ostr : lines) {
                bw.write(ostr);
                bw.newLine();
            }

            /*push*/
            bw.write(pushData);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * @param path
     * @param Data
     * @return is Write Success
     */
    public static boolean writer(String path, String Data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(Data);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     *
     * @param path
     * @param pushData
     * @param standardCharsets
     * @return is Write Success
     */
    public static boolean pushWriterStandardCharset(String path, String pushData, Charset standardCharsets) {
        InputStreamReader isr = null;
        BufferedReader reader = null;
        BufferedWriter bw = null;
        try {
            /*Read*/
            isr = new InputStreamReader(new FileInputStream(path), standardCharsets);
            reader = new BufferedReader(isr);

            /*keep in lines*/
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            /*write*/
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), standardCharsets));
            for (String ostr : lines) {
                bw.write(ostr);
                bw.newLine();
            }

            /*push*/
            bw.write(pushData);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     *
     * @param path
     * @param Data
     * @param standardCharsets
     * @return is Write Success
     */
    public static boolean writerStandardCharset(String path, String Data, Charset standardCharsets) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), standardCharsets));
            bw.write(Data);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isFileExist(String fileName){
        File file = new File(System.getProperty("user.dir"), fileName);
        return  file.isFile();
    }
}
