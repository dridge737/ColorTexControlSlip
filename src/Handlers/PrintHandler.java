/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;
 
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author imbuenyson
 */
public class PrintHandler {
    public static final String DEST = "C:\\chapter_title.pdf";
 
    private int chapterNumber;
    
    public void createPDF() throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PrintHandler().renderPDF(DEST);
    }
 
    public void renderPDF(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        document.setPageSize(PageSize.LETTER);
        document.setMargins(72, 36, 36, 36);
        document.open();
        
         ////////////////////////***************BEGIN FIRST SECTION***************////////////////////////
        Font companyHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC);
        
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 1;
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("Pushcart Control Slip", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.setNumberDepth(0);
        chapter.add(controlSlipHeader);
        
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase pFiller1 = new Phrase();
        pFiller1.add(" ");
        PdfPCell pCell1 = new PdfPCell(pFiller1);
        table.addCell(pFiller1);
        table.addCell(pFiller1);
        
        Phrase p = new Phrase();
        p.add("Customer: ");
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase();
        p2.add("Batch #: ");
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase();
        p3.add("Job#/DR#: ");
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase();
        p6.add("Weight: ");
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase();
        p5.add("Design: ");
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add(" ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase();
        p7.add("Color: ");
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase();
        p8.add(" ");
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        
        chapter.add(table);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase filler1 = new Phrase();
        filler1.add(" ");
        pCell = new PdfPCell(filler1);
        table.addCell(filler1);
        table.addCell(filler1);
        
        Phrase dyeingType = new Phrase();
        dyeingType.add("Dyeing Type:________________ ");
        pCell = new PdfPCell(dyeingType);
        table.addCell(dyeingType);
        
        Phrase numOfPushCartsUsed = new Phrase();
        numOfPushCartsUsed.add("# of push carts used:___");
        pCell = new PdfPCell(numOfPushCartsUsed);
        table.addCell(numOfPushCartsUsed);
        
        Phrase greigeWidth = new Phrase();
        greigeWidth.add("Greige Width:_______________");
        pCell = new PdfPCell(greigeWidth);
        table.addCell(greigeWidth);
        
        Phrase pushCartNum = new Phrase();
        pushCartNum.add("Push Cart #:____    # of Rolls: ____");
        pCell = new PdfPCell(pushCartNum);
        table.addCell(pushCartNum);
        
        Phrase finishedWidth = new Phrase();
        finishedWidth.add("Finished Width:______________ ");
        pCell = new PdfPCell(finishedWidth);
        table.addCell(finishedWidth);
        
        table.addCell(pushCartNum);
        
        Phrase yield = new Phrase();
        yield.add("Yield:____________________");
        pCell = new PdfPCell(yield);
        table.addCell(yield);
        
        Phrase preparedBy = new Phrase();
        preparedBy.add("Prepared By:________________ ");
        pCell = new PdfPCell(preparedBy);
        table.addCell(preparedBy);
        
        Phrase resin = new Phrase();
        resin.add("Resin:___________________");
        pCell = new PdfPCell(resin);
        table.addCell(resin);
        
        Phrase checkedBy = new Phrase();
        checkedBy.add("Checked By:________________ ");
        pCell = new PdfPCell(checkedBy);
        table.addCell(checkedBy);
        
        chapter.add(table);
        
        table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase dyeingOperators = new Phrase();
        dyeingOperators.add("Dyeing Operators: 1)_______________ 2)_______________ 3)_______________");
        pCell = new PdfPCell(dyeingOperators);
        table.addCell(dyeingOperators);
        
        Phrase remarks = new Phrase();
        remarks.add("Remarks:___________________________________________________________");
        Phrase line = new Phrase();
        line.add("__________________________________________________________________");
        pCell = new PdfPCell(remarks);
        table.addCell(remarks);
        table.addCell(line);
        table.addCell(line);
        table.addCell(filler1);
        table.addCell(filler1);
        table.addCell(filler1);
        table.addCell(filler1);
        
        chapter.add(table);
                
        ////////////////////////***************END FIRST SECTION***************////////////////////////
        
        ////////////////////////***************BEGIN SECOND SECTION***************////////////////////////
        companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.add(companyHeader);
        
        controlSlipHeader = new Paragraph("Pushcart Control Slip", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.add(controlSlipHeader);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        table.addCell(pFiller1);
        table.addCell(pFiller1);
        
        p = new Phrase();
        p.add("Customer: ");
        pCell = new PdfPCell(p);
        table.addCell(p);
        
        p2 = new Phrase();
        p2.add("Batch #: ");
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        p3 = new Phrase();
        p3.add("Job#/DR#: ");
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        p6 = new Phrase();
        p6.add("Weight: ");
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        p5 = new Phrase();
        p5.add("Design: ");
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        p4 = new Phrase();
        p4.add(" ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        p7 = new Phrase();
        p7.add("Color: ");
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        p8 = new Phrase();
        p8.add(" ");
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        
        chapter.add(table);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        filler1 = new Phrase();
        filler1.add(" ");
        pCell = new PdfPCell(filler1);
        table.addCell(filler1);
        table.addCell(filler1);
        
        dyeingType = new Phrase();
        dyeingType.add("Dyeing Type:________________ ");
        pCell = new PdfPCell(dyeingType);
        table.addCell(dyeingType);
        
        numOfPushCartsUsed = new Phrase();
        numOfPushCartsUsed.add("# of push carts used:___");
        pCell = new PdfPCell(numOfPushCartsUsed);
        table.addCell(numOfPushCartsUsed);
        
        greigeWidth = new Phrase();
        greigeWidth.add("Greige Width:_______________");
        pCell = new PdfPCell(greigeWidth);
        table.addCell(greigeWidth);
        
        pushCartNum = new Phrase();
        pushCartNum.add("Push Cart #:____    # of Rolls: ____");
        pCell = new PdfPCell(pushCartNum);
        table.addCell(pushCartNum);
        
        finishedWidth = new Phrase();
        finishedWidth.add("Finished Width:______________ ");
        pCell = new PdfPCell(finishedWidth);
        table.addCell(finishedWidth);
        
        table.addCell(pushCartNum);
        
        yield = new Phrase();
        yield.add("Yield:____________________");
        pCell = new PdfPCell(yield);
        table.addCell(yield);
        
        preparedBy = new Phrase();
        preparedBy.add("Prepared By:________________ ");
        pCell = new PdfPCell(preparedBy);
        table.addCell(preparedBy);
        
        resin = new Phrase();
        resin.add("Resin:___________________");
        pCell = new PdfPCell(resin);
        table.addCell(resin);
        
        checkedBy = new Phrase();
        checkedBy.add("Checked By:________________ ");
        pCell = new PdfPCell(checkedBy);
        table.addCell(checkedBy);
        
        chapter.add(table);
        
        table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        dyeingOperators = new Phrase();
        dyeingOperators.add("Dyeing Operators: 1)_______________ 2)_______________ 3)_______________");
        pCell = new PdfPCell(dyeingOperators);
        table.addCell(dyeingOperators);
        
        remarks = new Phrase();
        remarks.add("Remarks:___________________________________________________________");
        line = new Phrase();
        line.add("__________________________________________________________________");
        pCell = new PdfPCell(remarks);
        table.addCell(remarks);
        table.addCell(line);
        table.addCell(line);
        
        chapter.add(table);
        ////////////////////////***************END SECOND SECTION***************////////////////////////
        document.add(chapter);
        
        document = AddSecondPage(document, writer);
        
        document.close();
    }
    
    public Document AddSecondPage(Document document, PdfWriter writer)  throws IOException, DocumentException 
    {
        Font companyHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC);
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(companyHeader);
        //Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 2;
        //chapter.setNumberDepth(0);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("Dyeing Control Slip", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(controlSlipHeader);
        
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase filler = new Phrase();
        filler.add(" ");
        PdfPCell pCell1 = new PdfPCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
        Paragraph dyeingProcessHeader = new Paragraph("Change to dyeing process name", dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(dyeingProcessHeader);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        
        Phrase p = new Phrase();
        p.add("Customer: ");
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase();
        p2.add("Batch NO.: ");
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase();
        p3.add("Job NO.: ");
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase();
        p6.add("Machine #: ");
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase();
        p5.add("Design #: ");
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add("Weight: ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase();
        p7.add("Color: ");
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase();
        p7.add("Vol. of Water: ");
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_________ Drugman:_________ Operator:_________ Date:_________");
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        float[] columnWidths = {5, 2, 2, 2};
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        PdfPCell cell = new PdfPCell();
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell("Process Name");
            table.addCell("GPL");
            table.addCell("%");
            table.addCell("Quantity");
            
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
        int currentPageNumber = writer.getPageNumber();
        
        for (int counter = 1; counter < 101; counter++) {
                table.addCell(String.valueOf(counter));
                table.addCell("key " + counter);
                table.addCell("value " + counter);
                table.addCell("value " + counter);
        }
        
        document.add(table);
        
        //document.add(chapter);
        
        return document;
    }
    
    
    
    public void printPDF()
    {
        
    }

}
