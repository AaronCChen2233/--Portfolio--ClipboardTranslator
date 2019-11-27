package MVVM.Parts.Model;

import Bootstrap.Tools.GetConfigProperty;
import Bootstrap.Tools.linkToWebSite;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetTranslateInfoModel {
    private static final String VURL = GetConfigProperty.vURL;
    private static final String OURL = GetConfigProperty.oFURL;
    private static final String IURL = GetConfigProperty.gISURL;
    private static final String VC = GetConfigProperty.vDclass;
    private static final String OC = GetConfigProperty.oFDclass;
    private static final String IC = GetConfigProperty.Imclass;

    public static String getByVoiceTube(String word) {
        String url = VURL + word;
        return linkToWebSite.getInnerStringByElementId(url, VC);
    }

    public static Elements getByOxford(String word) {
        String url = OURL + word;
        return linkToWebSite.getElementsByClass(url, OC);
    }

    public static Document getOxfordDocument(String word){
        String url = OURL + word;
        return linkToWebSite.getDocumentURL(url);
    }

    public static Document getVocDocument(String word){
        String url = VURL + word;
        return linkToWebSite.getDocumentURL(url);
    }

    public static Elements getImgSrcs(String word){
        String url = IURL + word;
        return linkToWebSite.getElementsByClass(url, IC);
    }
}
