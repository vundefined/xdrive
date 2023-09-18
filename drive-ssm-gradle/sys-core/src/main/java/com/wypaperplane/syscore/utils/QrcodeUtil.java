package com.wypaperplane.syscore.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class QrcodeUtil {

    public static void generateQrcode(String qrContent, String descContent, String dirName, String fileName) {
        try {
            /**
             * EncodeHintType.ERROR_CORRECTION：设置误差校正
             * ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
             * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
             * */
            Map<EncodeHintType, Object> hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(qrContent, BarcodeFormat.QR_CODE, 400, 400, hints);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
            BufferedImage bgimage = new BufferedImage(400, 440, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics = (Graphics2D) bgimage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 400, 440);
            graphics.drawImage(qrImage, 0,0, 400, 400, null);
            graphics.setFont(font);
            graphics.setColor(Color.BLACK);
            graphics.drawString(descContent, 100, 420);

            Path _path = Paths.get(McommonUtil.getDirPath(dirName), fileName);
            ImageIO.write(bgimage, "png", _path.toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
