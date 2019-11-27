package MVVM.Parts.Model;

import Bootstrap.Tools.GetConfigProperty;
import Bootstrap.Tools.ReaderWriter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaveModel {
    public static boolean save(String saveString) {
        /*ClipboardTranslaterSave.txt*/
        String savePath = System.getProperty("user.dir") + "\\" + GetConfigProperty.saveFileName;
        boolean isSaveSuccess = false;
        if (ReaderWriter.isFileExist(GetConfigProperty.saveFileName)) {
            /*If file exist*/
            isSaveSuccess = ReaderWriter.pushWriterStandardCharset(savePath, saveString, StandardCharsets.UTF_8);
        } else {
            /*If file not exist*/
            isSaveSuccess = ReaderWriter.writerStandardCharset(savePath, saveString, StandardCharsets.UTF_8);
        }
        return isSaveSuccess;
    }

    public static List<String> getSavedVocabularyFromTxtFile() {
        String savePath = System.getProperty("user.dir") + "\\" + GetConfigProperty.saveFileName;
        if (ReaderWriter.isFileExist(GetConfigProperty.saveFileName)) {
            /*If file exist*/
            return ReaderWriter.reader(savePath).stream().map(s -> s.split("\t")[0]).collect(Collectors.toList());
        } else {
            return new ArrayList<String>();
        }
    }
}
