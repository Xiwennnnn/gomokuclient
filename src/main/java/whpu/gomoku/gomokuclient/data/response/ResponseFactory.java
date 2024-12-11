package whpu.gomoku.gomokuclient.data.response;

import com.fasterxml.jackson.databind.JsonNode;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseFactory {
    private static final Map<String, Class<? extends AbstractResponse>> responseMap;
    private static final Logger log = LoggerFactory.getLogger(ResponseFactory.class);

    static {
        responseMap = new ConcurrentHashMap<>();
        String packageName = AbstractResponse.class.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends AbstractResponse>> classes = reflections.getSubTypesOf(AbstractResponse.class);
        try {
            for (Class<? extends AbstractResponse> clazz : classes) {
                Method method = clazz.getMethod("getActionType");
                String actionType = (String) method.invoke(clazz.getConstructor().newInstance());
                responseMap.put(actionType, clazz);
            }
        } catch (Exception ignored) {
        }
    }

    public static AbstractResponse createResponse(JsonNode json) {
        Class<? extends AbstractResponse> clazz = responseMap.get(json.get("action").asText());
        try {
            Method method = clazz.getMethod("fromJson", JsonNode.class);
            return (AbstractResponse) method.invoke(null, json);
        } catch (Exception e) {
            log.error("Failed to create response for action type: {}", json.get("action").asText(), e);
        }
        return null;
    }
}
