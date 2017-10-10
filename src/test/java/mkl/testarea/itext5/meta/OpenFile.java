package mkl.testarea.itext5.meta;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;

/**
 * @author mkl
 */
public class OpenFile
{
    /**
     * <a href="http://stackoverflow.com/questions/39284508/itext-pdfreader-not-able-to-read-pdf">
     * ITEXT PDFReader not able to read PDF
     * </a>
     * <br/>
     * <a href="http://www.fundslibrary.co.uk/FundsLibrary.DataRetrieval/Documents.aspx?type=fund_class_kiid&id=f096b13b-3d0e-4580-8d3d-87cf4d002650&user=fidelitydocumentreport">
     * Aviva_Investors_UK_Fund_Services_Limited_UK_Equity_Income_Class_2_Income_[GBP].pdf
     * </a>
     * <p>
     * Indeed, iText fails to open the PDF in question. Further analysis (cf. the answer to the question) shows
     * that the file is not properly encrypted.
     * </p>
     */
    @Test
    public void testOpenAviva_Investors_UK_Fund_Services_Limited_UK_Equity_Income_Class_2_Income_GBP() throws IOException
    {
        try ( InputStream resource = getClass().getResourceAsStream("Aviva_Investors_UK_Fund_Services_Limited_UK_Equity_Income_Class_2_Income_[GBP].pdf") )
        {
            PdfReader pdfReader = new PdfReader(resource);
            Assert.assertEquals("", "Key Investor Information", pdfReader.getInfo().get("Title"));
        }
    }

    /**
     * <a href="http://stackoverflow.com/questions/42778329/printing-multiple-pdf-in-itext-from-single-page-template-using-java">
     * printing multiple pdf in itext from single page template using java
     * </a>
     * <br/>
     * <a href="https://drive.google.com/file/d/0B1kzuWl4_DrdaW14VlNyZXN5bW8/view?usp=sharing">
     * LegalNotice.pdf
     * </a>
     * <p>
     * Can read without issue.
     * </p>
     */
    @Test
    public void testLegalNotice() throws IOException
    {
        try ( InputStream resource = getClass().getResourceAsStream("LegalNotice.pdf") )
        {
            PdfReader pdfReader = new PdfReader(resource);
            Assert.assertEquals("", "Jigyasu", pdfReader.getInfo().get("Author"));
        }
    }

    /**
     * <a href="https://stackoverflow.com/questions/46663640/itext-pdf-fails-with-message-dictionary-key-endstream-is-not-a-name">
     * iText PDF fails with message “Dictionary key endstream is not a name”
     * </a>
     * <br/>
     * <a href="https://www.dropbox.com/s/xz77curzkigod0k/itext_banner_InvalidPdfException.pdf">
     * itext_banner_InvalidPdfException.pdf
     * </a>
     * <p>
     * There is a syntax error inside one of the objects of the PDF.
     * As this object is referenced from no other object, though, it
     * is ignored if the PDF is read in partial mode.
     * </p>
     */
    @Test
    public void testItextBannerInvalidPdfException() throws IOException
    {
        try ( InputStream resource = getClass().getResourceAsStream("itext_banner_InvalidPdfException.pdf") )
        {
            PdfReader pdfReader = new PdfReader(new RandomAccessFileOrArray(resource), null);
            Assert.assertEquals("", "GraphicsMagick 1.3.26 2017-07-04 Q16 http://www.GraphicsMagick.org/", pdfReader.getInfo().get("Producer"));
        }
    }
}
