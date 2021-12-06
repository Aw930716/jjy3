package com.gblfy;

import com.gblfy.render.utils.OfdPdfUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OfdPdfApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //测试pdf转ofd
    @Test
    public void ofdToPdf() {
        String pdfFileInputPath = "src/test/resources/n.pdf";
        String ofdFileOutputPath = "target/n-from-pdf.ofd";
        OfdPdfUtil.convertToOfdByStream(pdfFileInputPath, ofdFileOutputPath);
    }

    //测试pdf转ofd
    @Test
    public void PdfToOfd() {
        String ofdFileInputPath = "target/n-from-pdf.ofd";
        String pdfFileOutputPath = "target/n-from-pdf.pdf";
        OfdPdfUtil.ofdToPdf(ofdFileInputPath, pdfFileOutputPath);
    }
}
