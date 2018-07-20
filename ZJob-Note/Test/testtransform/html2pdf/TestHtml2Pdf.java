package testtransform.html2pdf;

import com.lowagie.text.pdf.BaseFont;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestHtml2Pdf
{


    @Test
    public void test() throws Exception{
        String url = "D:/work/upgrade/yht-p2p/333.htm";
        String targetPath = "D:/work/upgrade/yht-p2p/111.pdf";
        String fontPath = "attach/font/simsun.ttc";
        File targetFile = new File(targetPath);
        if (targetFile.exists())

        {
            targetFile.delete();
        }
        try (OutputStream os = new FileOutputStream(targetFile))
        {
//			PDFEncryption pdfEncryption = new PDFEncryption(null, null, PdfWriter.ALLOW_PRINTING);
            ITextRenderer renderer = new ITextRenderer();
//			renderer.setPDFEncryption(pdfEncryption);
            renderer.setDocument(new File(url));
            // 解决中文支持
            ITextFontResolver fontResolver = renderer.getFontResolver();
            fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(os);
        }
    }

}
