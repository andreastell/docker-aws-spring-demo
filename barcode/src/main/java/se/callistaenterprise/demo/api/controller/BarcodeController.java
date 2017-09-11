package se.callistaenterprise.demo.api.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.BarcodeFormat.*;
import static com.google.zxing.EncodeHintType.CHARACTER_SET;
import static com.google.zxing.EncodeHintType.MARGIN;
import static com.google.zxing.client.j2se.MatrixToImageWriter.writeToStream;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * Created by atell on 2017-05-26.
 */
@Api(value = "Generates Barcodes and QR codes", tags = "DEMO",
        description = "")
@RestController
@RequestMapping("/barcodes")
@Slf4j
public class BarcodeController {


    @RequestMapping(method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public void createBarcode(
            @RequestParam(value = "format", defaultValue = "QR_CODE") String format,
            @RequestParam(value = "content", defaultValue = "http://callistaenterprise.se/event/") String content,
            @RequestParam(value = "margin", defaultValue = "0") int margin,
            @RequestParam(value = "height", defaultValue = "200") int height,
            @RequestParam(value = "width", defaultValue = "200") int width,
            HttpServletResponse res) throws Exception {

        log.info("RENDER barcode [height:{}, width:{}, margin:{}, content:{}, format:{}]", height, width, margin, content, format);

        res.setContentType(IMAGE_PNG_VALUE);

        disableCache(res);

        writeToStream(renderBarcode(content, margin, height, width, format), "png", res.getOutputStream());
    }

    private BitMatrix renderBarcode(final String content, final int margin, final int height, final int width, String format) {

        Map hints = new HashMap();
        hints.put(MARGIN, margin);
        hints.put(CHARACTER_SET, "UTF-8");

        try {
            switch (format) {
                case "EAN-13":
                    return new MultiFormatWriter().encode(content, EAN_13, width, height, hints);
                case "QR_CODE":
                    return new MultiFormatWriter().encode(content, QR_CODE, width, height, hints);
                case "CODE_128":
                    return new MultiFormatWriter().encode(content, CODE_128, width, height, hints);
                default:
                    return new MultiFormatWriter().encode(content, QR_CODE, width, height, hints);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error rendering barcode for content " + content, ex);
        }
    }

    private void disableCache(HttpServletResponse res) {
        res.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.addHeader("Expires", "0");
        res.addHeader("Pragma", "no-cache");
    }

}
