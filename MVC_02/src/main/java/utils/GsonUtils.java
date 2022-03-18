package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class GsonUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";    // 解析时间格式
    private static Gson gson = null;                                    // 私有化Gosn对象

    // 无参的私有构造方法
    private GsonUtils() {

    }

    static {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setLenient()                          //json宽松
                    .enableComplexMapKeySerialization()    //支持Map的key为复杂对象的形式
                    .serializeNulls()                      //智能null
                    .setPrettyPrinting()                   //调教格式
                    .disableHtmlEscaping()                 //默认是GSON把HTML 转义的
                    .setDateFormat(DATE_FORMAT)
                    .create();
        }
    }

    /**
     * 得到Gson对象
     */
    public static Gson getGosn() {
        return gson;
    }

    /**
     * 将对象转成json格式
     */
    @SneakyThrows
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将json转成特定的class的对象
     */
    @SneakyThrows
    public static <T> T jsonToObj(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    /**
     * json字符串转成list
     */
    @SneakyThrows
    public static <T> List<T> jsonToList(String jsonString, TypeToken typeToken) {
        return gson.fromJson(jsonString, typeToken.getType());
    }

    @SneakyThrows
    public static <T> List<T> jsonToList(String jsonString, TypeToken typeToken, Class<T> clazz) {
        return gson.fromJson(jsonString, typeToken.getType());
    }

    /**
     * json字符串转成Map
     */
    @SneakyThrows
    public static <K, T> Map<K, T> jsonToMap(String jsonString, TypeToken typeToken) {
        return gson.fromJson(jsonString, typeToken.getType());
    }

    @SneakyThrows
    public static <K, T> Map<K, T> jsonToMap(String jsonString, TypeToken typeToken, Class<K> k, Class<T> t) {
        return gson.fromJson(jsonString, typeToken.getType());
    }
}
