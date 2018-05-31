package xyz.supermoonie.parser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

/**
 * @author Administrator
 * @date 2018/5/31 0031
 */
public class ScreenshotParser implements Parser<BufferedImage> {

    @Override
    public BufferedImage parse(String result) throws Exception {
        if (null == result || "".equals(result)) {
            return null;
        }
        byte[] bytes = Base64.getDecoder().decode(result);
        if (bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return ImageIO.read(in);
    }
}
