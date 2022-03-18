package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * JackSonUtils
 * @author by wyl
 * @date 2021/10/5.20点22分
 */
public class JacksonUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";    //解析时间格式
    private static ObjectMapper mapper = null;

    //无参的私有构造方法
    private JacksonUtils() {

    }

    //初始化mapper变量
    static {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);    //关闭时间戳的方式
            mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));                          //设置时间解析方式
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
