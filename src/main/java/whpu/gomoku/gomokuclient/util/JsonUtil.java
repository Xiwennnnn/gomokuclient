package whpu.gomoku.gomokuclient.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ThreadLocal<ObjectMapper> objMapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    public static String turnObjectToJson(Object obj) throws JsonProcessingException {
        return objMapperThreadLocal.get().writeValueAsString(obj);
    }
}
