package com.etang.nt_launcher.tool.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: StreamTool
 * @Description: 字节流工具
 * @CreateDate: 2021/3/19 8:29
 * @UpdateDate: 2021/3/19 8:29
 */
public class StreamTool {
    //当前页面TAG
    private static String TAG = "StreamTool";

    /**
     * @param in
     * @return
     * @throws IOException
     */
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