package Bootstrap.Tools;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class linkToWebSite {
    public static String getInnerStringByElementId(String url, String id) {
        Document document = null;
        String content = "";
        try {
            document = Jsoup.connect(url).get();
            Element element = document.getElementById(id);
            /*if element is null return "" */
            if (element != null) {
                content = element.outerHtml();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static Elements getElementsByClass(String url, String className) {
        Document document = null;
        Elements content = new Elements();
        try {
            document = Jsoup.connect(url).get();
            content = document.select(className);
        } catch (IOException e) {
            if (((HttpStatusException) e).getStatusCode() == 404) {
                Alog.logWarning(url + " return 404 Not Found");
            } else {
                e.printStackTrace();
            }
        }
        return content;
    }

    public static Document getDocumentURL(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            return document;
        } catch (IOException e) {
            if (((HttpStatusException) e).getStatusCode() == 404) {
                Alog.logWarning(url + " return 404 Not Found");
            } else {
                e.printStackTrace();
            }
        }
        return document;
    }
}
