package com.etang.mt_launcher.tool.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {
    private static String TAG = "StreamTool";

    public static String decodeStream(InputStream in, String charset) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int len;
            byte[] buf = new byte[3072];
            try (BufferedInputStream bis = new BufferedInputStream(in)) {
                while ((len = bis.read(buf)) > 0) {
                    baos.write(buf, 0, len);
                }
            } catch (IOException e) {
                // 处理异常情况，例如记录日志或抛出自定义异常
                throw new RuntimeException("Error reading input stream", e);
            }
            String data = new String(baos.toByteArray(), charset);
            return data;
        }
    }
}
