package whpu.gomoku.gomokuclient.service;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import whpu.gomoku.gomokuclient.action.Action;
import whpu.gomoku.gomokuclient.action.LoginSuccessAction;
import whpu.gomoku.gomokuclient.action.UpdateRoomAction;
import whpu.gomoku.gomokuclient.data.common.ActionEnum;
import whpu.gomoku.gomokuclient.data.response.AbstractResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class GomokuBusiness {
    private static List<Action<? extends AbstractResponse>> actions;

    static {
        actions = new ArrayList<>();
        String packageName = Action.class.getPackageName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Action>> classes = reflections.getSubTypesOf(Action.class);
        try {
            for (Class<? extends Action> clazz : classes) {
                actions.add(clazz.getConstructor().newInstance());
            }
        } catch (Exception e) {
            log.error("初始化Action失败", e);
        }
    }

    public static void doBusiness(AbstractResponse response) {
        for (Action<? extends AbstractResponse> action : actions) {
            if (action.getActionType().equals(response.getActionType())) {
                doActionSafely(action, response);
            }
        }
    }

    private static <T extends AbstractResponse> void doActionSafely(Action<T> action, AbstractResponse response) {
        @SuppressWarnings("unchecked")
        T t = (T) response;
        action.doAction(t);
    }
}
