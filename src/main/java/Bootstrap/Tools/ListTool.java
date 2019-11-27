package Bootstrap.Tools;

import java.util.List;

public class ListTool {
    public static List<String> subList(List<String> list, int leftCount) {
        if(list.size()!=0){
            list = list.subList(0, (list.size() < leftCount ? list.size() : leftCount));
        }
        return list;
    }

    public static List<String> clearList(List<String> list) {
        if(list != null){
           list.clear();
        }
        return list;
    }
}
