package com.dabaicai.common.export;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author jhl on 2017/6/27.
 */

public class StreamTools {
    /**
     * 关闭输出流
     *
     * @param outputStream
     */
    public static void closeStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param inputStream
     */
    public static void closeStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
