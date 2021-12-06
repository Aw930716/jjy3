package com.gblfy.render.utils;

import com.gblfy.render.OFDRender;
import com.gblfy.render.dir.OFDDir;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.TestOnly;
import org.ofdrw.converter.ConvertHelper;
import org.ofdrw.converter.FontLoader;
import org.ofdrw.converter.GeneralConvertException;
import org.ofdrw.converter.ImageMaker;
import org.ofdrw.reader.OFDReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * pdf转ofd
 *
 * @author gblfy
 * @date 2021-12-06
 */
public class OfdPdfUtil {
    private static final Logger logger = LoggerFactory.getLogger(OfdPdfUtil.class);

    /**
     * pdf转ofd
     *
     * @param pdfFileInputPath
     * @param ofdFileOutputPath
     */
    public static void convertToOfdByStream(String pdfFileInputPath, String ofdFileOutputPath) {
        Path input = Paths.get(pdfFileInputPath);
        Path output = Paths.get(ofdFileOutputPath);
        for (int i = 0; i < 1; i++) {
            try {
                OFDRender.convertPdfToOfd(Files.newInputStream(input), Files.newOutputStream(output));
            } catch (Exception e) {
                logger.error("test convert failed", e);
            }
        }
    }

    /**
     * pdf转ofd
     *
     * @param pdfFileInputPath
     * @param ofdFileOutputPath
     */
    public static void convertToOfd(String pdfFileInputPath, String ofdFileOutputPath) {
        Path input = Paths.get(pdfFileInputPath);
        Path output = Paths.get(ofdFileOutputPath);
        for (int i = 0; i < 1; i++) {
            try {
                byte[] pdfBytes = FileUtils.readFileToByteArray(input.toFile());
                byte[] ofdBytes = OFDRender.convertPdfToOfd(pdfBytes);
                if (Objects.nonNull(ofdBytes)) {
                    FileUtils.writeByteArrayToFile(output.toFile(), ofdBytes);
                    logger.info("pdf convert to ofd done, pdf file save to {}", output.toAbsolutePath().toString());
                } else {
                    logger.error("pdf convert to ofd failed");
                }
            } catch (Exception e) {
                logger.error("test convert failed", e);
            }
        }
    }

    /**
     * ofd转pdf
     *
     * <p>
     * 转换文档你需要:
     * 1.提供待转换OFD文档，支持Path、InputStream。
     * 2.提供转换后PDF文档位置。
     * 3.调用转换工具执行文档转换。
     *
     * @param ofdFileOutputPath
     * @param pdfFileInputPath
     */
    public static void ofdToPdf(String ofdFileOutputPath, String pdfFileInputPath) {
        // 1. 文件输入路径
        Path src = Paths.get(ofdFileOutputPath);
        // 2. 转换后文件输出位置
        Path dst = Paths.get(pdfFileInputPath);
        try {
            // 3. OFD转换PDF
            ConvertHelper.toPdf(src, dst);
            System.out.println("生成文档位置: " + dst.toAbsolutePath());
        } catch (GeneralConvertException e) {
            // GeneralConvertException 类型错误表明转换过程中发生异常
            e.printStackTrace();
        }
    }


}
