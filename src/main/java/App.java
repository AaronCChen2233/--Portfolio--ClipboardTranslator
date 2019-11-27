import Bootstrap.App.EApp;
import Bootstrap.Parts.AbstractBootstrap;
import Bootstrap.Tools.Alog;
import MVVM.Parts.Model.DetectClipboardModel;
import MVVM.Parts.Model.InformationConverterModel;
import java.util.Scanner;

/***
 * @author Aaron Chen
 */
/*
 * App Description:
 *   This Application is for people learning vocabulary in English. Couldn't for commercial use
 *   When user copy a vocabulary will Popup a small window automatically
 *   that show some information about this vocabulary.
 *   User can save this vocabulary and use "Anki" app for study those vocabulary which you had saved.
 *
 * To Do List:
 * I.Research:
 *   V1.How to get clipboard string
 *   V2.How to get website info
 *   V3.Popup a small window at corner
 *   V4.How to open web browser
 *   V5.Save in a .txt file use html format(Save use UTF-8)
 *   V6.How to play a .mp3 url
 *
 * II.Function:
 *   V1.Copy a vocabulary then show a small popup window at corner
 *   V2.Information include
 *       V1.Vocabulary definition in English
 *       V2.Vocabulary definition in Chinese
 *       V3.Vocabulary example
 *       V4.Vocabulary image
 *   V3.Click will show on web browser
 *   V4.Save this Vocabulary use a special html format which can import by use "Anki" app
 *   5.Setting function-let user can setting
 *       1.is windows popup automatically?
 *       2.Max showing Example count
 *       3.is speech automatically?
 *       4.default open url
 *   V6.Speech button
 *   V7.Need refactoring
 *   V8.Add commend
 *   9.Made the popup windows more beautiful
 *
 * III.Found Bugs List:
 *   V1.If Vocabulary not found should show error
 *   V2.new vocabulary popup scrollView should scroll to top
 *   V3.Save in a .txt file use html format should save as UTF-8
 *   V4.If vocabulary already saved disable save button
 *   V5.If coped vocabulary is Camel-Case don't search (special rule for programmer ha ha)
 *
 *   gradle fatJar
 * * */
public class App extends AbstractBootstrap {
    static App app;

    public App(String[] CommandLineArguments) {
        super(CommandLineArguments);
    }

    public static void main(String[] args) {
        Alog.logStartup("Started App Main");
        app = new App(args);
    }

    @Override
    public void OnInitialized() {
        /*When Clipboard change*/
        DetectClipboardModel.DetectClipboardThread timeSetter = new DetectClipboardModel.DetectClipboardThread() {
            @Override
            public void detectClipboardStringChange(String newString) {
                try {
                    InformationConverterModel.ConvertInformationToVocabularyInfo(newString);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        Thread t = new Thread(timeSetter);
        t.start();
    }

    @Override
    public void OnShutdown() {

    }

    @Override
    public void OnApplicationUpdate() {
        Scanner input = new Scanner(System.in);
        Alog.logInfo("If you want to close this application please type \"exit\"");

        String exit = input.nextLine();

        if (exit.toLowerCase().equals("exit")) {
            setAppState(EApp.ShuttingDown);
        }
    }
}