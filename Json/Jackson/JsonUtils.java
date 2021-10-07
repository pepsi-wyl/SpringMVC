package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author by wyl
 * @date 2021/10/5.20点22分
 */
public class JsonUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";    //解析时间格式
    private static ObjectMapper mapper = null;

    //无参的私有构造方法
    private JsonUtils() {

    }

    //初始化mapper变量
    static {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        }
    }

    /**
     * 得到json字符串
     */
    @SneakyThrows
    public static String toJson(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    /**
     * json转化为Obj
     */
    @SneakyThrows
    public static <T> T jsonToObj(String jsonString, Class<T> clazz) {
        return (T) mapper.readValue(jsonString, clazz);
    }

    /**
     * json转化为List
     */
    @SneakyThrows
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        return mapper.readValue(jsonString, mapper.getTypeFactory().constructParametricType(List.class, clazz));
    }

    /**
     * json转化为Map
     */
    @SneakyThrows
    public static <K, V> Map<K, V> jsonToMap(String jsonString, Class<K> keyType, Class<V> valueType) {
        return mapper.readValue(jsonString, mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType));
    }

}
