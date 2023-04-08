package com.etang.mt_launcher.tool.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {
    private static String TAG = "StreamTool";

    public static String decodeStream(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[3072];
        while ((len = in.read(buf)) > 0) {
            baos.write(buf, 0, len);
        }
        String data = baos.toString();
        return data;
    }
}