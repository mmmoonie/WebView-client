package xyz.supermoonie.util;

import java.io.UnsupportedEncodingException;

/**
 * @author wangchao
 * @date 2018/05/29
 */
public class WebViewClientUtils {

    private WebViewClientUtils() {
    }

    public static String stringToHexString(String src) {
        try {
            return bytesToHexString(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
