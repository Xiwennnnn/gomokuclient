package whpu.gomoku.gomokuclient.util;

import whpu.gomoku.gomokuclient.data.common.GomokuConstant;
import whpu.gomoku.gomokuclient.exception.ClientException;
import whpu.gomoku.gomokuclient.exception.common.ErrorCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public final class GomokuUtil {
    /**
     * 读取token文件
     * @return token字符串
     * @throws ClientException
     */
    public static String loadToken() throws ClientException {
        File tokenFile = new File(GomokuConstant.TOKEN_POSITION);
        try {
            if (!tokenFile.exists()) {
                boolean success = tokenFile.createNewFile();
                if (!success) {
                    throw new ClientException(ErrorCode.TOKEN_FILE_CREATE_FAILED);
                }
            }
        } catch (IOException ioException) {
            throw new ClientException(ErrorCode.TOKEN_FILE_CREATE_FAILED);
        }
        try {
            String token = Files.readString(tokenFile.toPath());
            return Optional.ofNullable(token).orElse("");
        } catch (IOException ioException) {
            throw new ClientException(ErrorCode.TOKEN_FILE_READ_FAILED);
        }
    }

    /**
     * 保存token文件
     * @param token
     */
    public static void saveToken(String token) {
        File tokenFile = new File(GomokuConstant.TOKEN_POSITION);
        try {
            if (!tokenFile.exists()) {
                boolean success = tokenFile.createNewFile();
                if (!success) {
                    throw new ClientException(ErrorCode.TOKEN_FILE_CREATE_FAILED);
                }
            }
            clearToken();
            Files.writeString(tokenFile.toPath(), token);
            System.out.println("Token saved successfully.");
        } catch (IOException ioException) {
            throw new ClientException(ErrorCode.TOKEN_FILE_WRITE_FAILED);
        }
    }

    /**
     * 清除token文件
     * @return 是否成功
     */
    public static boolean clearToken() {
        File tokenFile = new File(GomokuConstant.TOKEN_POSITION);
        if (tokenFile.exists()) {
            return tokenFile.delete();
        }
        return false;
    }
}
