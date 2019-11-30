package Bootstrap.Tools;

public class StringTools {
    public static boolean checkIsCamelCase(String checkedString){
        String camelCasePattern = "([a-z]+[A-Z]+\\w+)+";
        return checkedString.matches(camelCasePattern);
    }

    public static boolean checkIsStudlyCaps(String checkedString){
        String camelCasePattern = "([A-Z]+[a-z]+[A-Z]+\\w+)+";
        return checkedString.matches(camelCasePattern);
    }


}
