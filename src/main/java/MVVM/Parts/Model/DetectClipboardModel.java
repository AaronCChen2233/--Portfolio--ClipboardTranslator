package MVVM.Parts.Model;

import Bootstrap.Tools.StringTools;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class DetectClipboardModel implements ClipboardOwner, IMVVM_Model {
    private Clipboard clipboard;
    private Transferable content;

    public DetectClipboardModel() {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    /**
     * get Clipboard
     * @return if return is null mean doesn't have string
     */
    public String tryGetClipboardString() {
        try {
            content = clipboard.getContents(this);
            if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String clipboardString = (String) content.getTransferData(DataFlavor.stringFlavor);
                return clipboardString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //System.out.println("lostOwnership...");
    }

    public static abstract class DetectClipboardThread extends Thread implements Runnable {
        public void run() {
            Timer timer = new Timer();

            timer.schedule(new DateTask() {
                @Override
                public void clipboardStringChange(String newString) {
                    /*If newString have space don't search because now only support single vocabulary*/
                    newString = newString.trim();

                    /*If coped vocabulary is Camel-Case or StudlyCaps don't search(special rule for software engineer haha)*/
                    if(StringTools.checkIsCamelCase(newString)){
                        return;
                    }
                    if(StringTools.checkIsStudlyCaps(newString)){
                        return;
                    }

                    newString = newString.toLowerCase();
                    if(Pattern.matches("([a-z])\\w+", newString)) {
                        detectClipboardStringChange(newString);
                    }
                }
            // check every 0.2 seconds
            }, 200, 200);
        }
        public abstract void detectClipboardStringChange(String newString);
    }

    static abstract class DateTask extends TimerTask {
        DetectClipboardModel dc = new DetectClipboardModel();
        String beforeString = "";

        @Override
        public void run() {
            String newString = dc.tryGetClipboardString();
            /*if newString is null mean Clipboard doesn't have string*/
            if (newString != null) {
                /*String change*/
                if (!beforeString.equals(newString)) {
                    clipboardStringChange(newString);
                    beforeString = newString;
                }
            }
        }

        public abstract void clipboardStringChange(String newString);
    }
}
