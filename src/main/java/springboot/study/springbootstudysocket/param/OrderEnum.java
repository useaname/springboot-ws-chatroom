package springboot.study.springbootstudysocket.param;


import java.util.HashMap;
import java.util.Map;

public enum OrderEnum {
    start,
    reset,
    resizeFontSize,
    customMessage;

    private static Map<String, String> statusNameMap;

    private OrderEnum() {}

    public static String getName(String checkStatus) {
        return statusNameMap.get(checkStatus);
    }

    static {
        statusNameMap = new HashMap<>();
        statusNameMap.put("start", "开始放送字幕");
        statusNameMap.put("reset", "重置字幕");
        statusNameMap.put("resizeFontSize", "重置字体大小");
        statusNameMap.put("customMessage", "自定义消息");
    }
}
