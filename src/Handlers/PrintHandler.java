/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;
 
import DataEntities.ChemicalColor;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.Machine;
import DataEntities.DyeingProcess;
import DataEntities.DyeingChemical;
import DataEntities.ResinChemical;
import Helpers.RomanNumber;
import java.util.ArrayList;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author imbuenyson
 */
public class PrintHandler {
    public static final String DEST = "C:\\chapter_title.pdf";
 
    private Font f  = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
    private int chapterNumber;
    
    
    public void createPDF(Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PrintHandler().renderPDF(DEST, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        printPDF();
    }
 
    public void renderPDF(String dest, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        document.setPageSize(PageSize.LETTER);
        document.setMargins(60, 36, 18, 36);
        document.open();
        
         ////////////////////////***************BEGIN FIRST SECTION***************////////////////////////
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 1;
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
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
        
        Phrase p = new Phrase("Customer: " + customerDetails.getCustomerName(), f);
        //p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase("Batch #: " + jobOrderDetails.getBatchNo(), f);
        //p2.add("Batch #: " + jobOrderDetails.getBatchNo());
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        //p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase("Weight: " + jobOrderDetails.getWeight(), f);
        //p6.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase("Design: " + designDetails.getDesignName(), f);
        //p5.add("Design: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add(" ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("Color: " + chemicalDetails.getColorName(), f);
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
        
        Phrase dyeingType = new Phrase("Dyeing Type:________________", f);
        //dyeingType.add("Dyeing Type:________________ ", f);
        pCell = new PdfPCell(dyeingType);
        table.addCell(dyeingType);
        
        Phrase numOfPushCartsUsed = new Phrase("# of push carts used:___", f);
        //numOfPushCartsUsed.add("# of push carts used:___", f);
        pCell = new PdfPCell(numOfPushCartsUsed);
        table.addCell(numOfPushCartsUsed);
        
        Phrase greigeWidth = new Phrase("Greige Width:________________", f);
        //greigeWidth.add("Greige Width:_______________", f);
        pCell = new PdfPCell(greigeWidth);
        table.addCell(greigeWidth);
        
        Phrase pushCartNum = new Phrase("Push Cart #:____    # of Rolls: ____", f);
        //pushCartNum.add("Push Cart #:____    # of Rolls: ____", f);
        pCell = new PdfPCell(pushCartNum);
        table.addCell(pushCartNum);
        
        Phrase finishedWidth = new Phrase("Finished Width:______________ ", f);
        //finishedWidth.add("Finished Width:______________ ", f);
        pCell = new PdfPCell(finishedWidth);
        table.addCell(finishedWidth);
        
        table.addCell(pushCartNum);
        
        Phrase yield = new Phrase("Yield:______________________", f);
        //yield.add("Yield:____________________", f);
        pCell = new PdfPCell(yield);
        table.addCell(yield);
        
        Phrase preparedBy = new Phrase("Prepared By:__________________", f);
        //preparedBy.add("Prepared By:________________ ", f);
        pCell = new PdfPCell(preparedBy);
        table.addCell(preparedBy);
        
        Phrase resin = new Phrase("Resin:______________________", f);
        //resin.add("Resin:___________________", f);
        pCell = new PdfPCell(resin);
        table.addCell(resin);
        
        Phrase checkedBy = new Phrase("Checked By:__________________", f);
        //checkedBy.add("Checked By:________________ ", f);
        pCell = new PdfPCell(checkedBy);
        table.addCell(checkedBy);
        
        chapter.add(table);
        
        table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase dyeingOperators = new Phrase("Dyeing Operators: 1)____________________ 2)_____________________ 3)_____________________", f);
        //dyeingOperators.add("Dyeing Operators: 1)_______________ 2)_______________ 3)_______________", f);
        pCell = new PdfPCell(dyeingOperators);
        table.addCell(dyeingOperators);
        
        Phrase remarks = new Phrase("Remarks:", f);
        //remarks.add("Remarks:___________________________________________________________", f);
        Phrase line = new Phrase("___________________________________________________________________________________", f);
        //line.add("__________________________________________________________________", f);
        pCell = new PdfPCell(remarks);
        table.addCell(remarks);
        table.addCell(line);
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
        
        p = new Phrase("Customer: " + customerDetails.getCustomerName(), f);
        //p.add("Customer: " + customerDetails.getCustomerName(), f);
        pCell = new PdfPCell(p);
        table.addCell(p);
        
        p2 = new Phrase("Batch #: " + jobOrderDetails.getBatchNo(), f);
        //p2.add("Batch #: " + jobOrderDetails.getBatchNo(), f);
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        p3 = new Phrase("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        //p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        p6 = new Phrase("Weight: " + jobOrderDetails.getWeight(), f);
        //p6.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        p5 = new Phrase("Design: " + designDetails.getDesignName(), f);
        //p5.add("Design: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        p4 = new Phrase();
        p4.add(" ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("Color: " + chemicalDetails.getColorName(), f);
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
        
        dyeingType = new Phrase("Dyeing Type:________________ ", f);
        //dyeingType.add("Dyeing Type:________________ ", f);
        pCell = new PdfPCell(dyeingType);
        table.addCell(dyeingType);
        
        numOfPushCartsUsed = new Phrase("# of push carts used:___", f);
        //numOfPushCartsUsed.add("# of push carts used:___", f);
        pCell = new PdfPCell(numOfPushCartsUsed);
        table.addCell(numOfPushCartsUsed);
        
        greigeWidth = new Phrase("Greige Width:________________", f);
        //greigeWidth.add("Greige Width:_______________", f);
        pCell = new PdfPCell(greigeWidth);
        table.addCell(greigeWidth);
        
        pushCartNum = new Phrase("Push Cart #:____    # of Rolls: ____", f);
        //pushCartNum.add("Push Cart #:____    # of Rolls: ____", f);
        pCell = new PdfPCell(pushCartNum);
        table.addCell(pushCartNum);
        
        finishedWidth = new Phrase("Finished Width:______________ ",f);
        //finishedWidth.add("Finished Width:______________ ",f);
        pCell = new PdfPCell(finishedWidth);
        table.addCell(finishedWidth);
        
        table.addCell(pushCartNum);
        
        yield = new Phrase("Yield:______________________", f);
        //yield.add("Yield:____________________", f);
        pCell = new PdfPCell(yield);
        table.addCell(yield);
        
        preparedBy = new Phrase("Prepared By:__________________", f);
        //preparedBy.add("Prepared By:________________ ", f);
        pCell = new PdfPCell(preparedBy);
        table.addCell(preparedBy);
        
        resin = new Phrase("Resin:______________________", f);
        //resin.add("Resin:___________________", f);
        pCell = new PdfPCell(resin);
        table.addCell(resin);
        
        checkedBy = new Phrase("Checked By:__________________", f);
        //checkedBy.add("Checked By:________________ ", f);
        pCell = new PdfPCell(checkedBy);
        table.addCell(checkedBy);
        
        chapter.add(table);
        
        table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        dyeingOperators = new Phrase("Dyeing Operators: 1)____________________ 2)_____________________ 3)_____________________", f);
        //dyeingOperators.add("Dyeing Operators: 1)_______________ 2)_______________ 3)_______________", f);
        pCell = new PdfPCell(dyeingOperators);
        table.addCell(dyeingOperators);
        
        remarks = new Phrase("Remarks:", f);
        //remarks.add("Remarks:___________________________________________________________", f);
        line = new Phrase("___________________________________________________________________________________", f);
        //line.add("__________________________________________________________________", f);
        pCell = new PdfPCell(remarks);
        table.addCell(remarks);
        table.addCell(line);
        table.addCell(line);
        table.addCell(line);
        
        chapter.add(table);
        ////////////////////////***************END SECOND SECTION***************////////////////////////
        document.add(chapter);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document = AddSecondPage(document, writer, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        
        //CHANGE CONDITION TO IF RESIGNPROGRAM EXISTING
        if(jobOrderDetails.getResinProgramID() > 0)
        {
            document = AddThirdPage(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        }
        
        document.close();
    }
    
    public Document AddSecondPage(Document document, PdfWriter writer, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume)  throws IOException, DocumentException 
    {
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(companyHeader);
        DyeingProgramNameHandler dyeingProgramNameHandler = new DyeingProgramNameHandler();
        //Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 2;
        //chapter.setNumberDepth(0);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
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
        
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(dyeingProcessHeader);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        
        Phrase p = new Phrase("Customer: " + customerDetails.getCustomerName(), f);
        //p.add("Customer: " + customerDetails.getCustomerName(), f);
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase("Batch #: " + jobOrderDetails.getBatchNo(), f);
        //p2.add("Batch #: " + jobOrderDetails.getBatchNo(), f);
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        //p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase("Machine: " + machineDetails.getMachineName(), f);
        //p6.add("Machine: " + machineDetails.getMachineName(), f);
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase("Design #: " + designDetails.getDesignName(), f);
        //p5.add("Design #: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase("Weight: " + jobOrderDetails.getWeight(), f);
        //p4.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("Color: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase("Vol. of Water: " + volume, f);
        //p8.add("Vol. of Water: " + volume, f);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_____________ Drugman:_____________ Operator:_____________ Date:_____________", f);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        float[] columnWidths = {5, 2, 2, 2};
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        PdfPCell cell = new PdfPCell();
        //table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell(" ");
            table.addCell(new Phrase("GPL", f));
            table.addCell(new Phrase("%", f));
            table.addCell(new Phrase("Quantity", f));
            
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
        DyeingChemicalHandler dChemHandler = new DyeingChemicalHandler();
        DyeingProcessHandler dProcessHandler = new DyeingProcessHandler();
        ChemicalHandler chemHandler = new ChemicalHandler();
        ArrayList<DyeingProcess> dyeingProcessList = dProcessHandler.GetAllDyeingProcessAndSubProcessByDyeingProgramId(dyeingProgramDetails.getID());
        ArrayList<DyeingChemical> dyeingChemicalList = null;
        int rows = 0;
        
        if(dyeingProcessList.size() > 1)
        {
            for(int x=0; x<dyeingProcessList.size(); x++)
            {
                if(rows%30 != 0 || rows == 0)
                {
                    if (dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+")){
                        table.addCell(new Phrase(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(x).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                        table.addCell(" ");
                        table.addCell(" ");
                        table.addCell(" ");
                        rows++;
                    }
                    else if(!(dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+"))){
                        String dyeingSubProcessLetter = dyeingProcessList.get(x).getdyeingProcessOrder().replaceAll("[^A-Za-z]+", "");

                        table.addCell(new Phrase("    " + dyeingSubProcessLetter.toUpperCase() + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                        table.addCell(" ");
                        table.addCell(" ");
                        table.addCell(" ");
                        rows++;

                        dyeingChemicalList = dChemHandler.GetAllDyeingChemicalFromDyeingProcessID(dyeingProcessList.get(x).getId());

                        if(dyeingChemicalList.size() > 0)
                        {
                            Collections.sort(dyeingChemicalList, new Comparator<DyeingChemical>(){
                                @Override
                                public int compare(DyeingChemical o1, DyeingChemical o2){
                                    int rollno1 = o1.getOrder();
                                    int rollno2 = o2.getOrder();

                                    /*For ascending order*/
                                    return rollno1-rollno2;

                                }                    
                            });
                        }

                        for(int i = 0; i<dyeingChemicalList.size(); i++)
                        {
                            table.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalID()), f));
                            if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                                table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue()), f));
                                table.addCell(" ");
                            }
                            else
                            {
                                table.addCell(" ");
                                table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue()),f ));
                            }

                            if(dyeingChemicalList.get(i).getType()== "%")
                            {
                                Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                                Double quantity = Double.parseDouble(fQuantity.toString());
                                if(quantity.toString().contains(".0") == true)
                                {
                                    DecimalFormat df = new DecimalFormat("#,###.00");
                                    df.setRoundingMode(RoundingMode.CEILING);
                                    table.addCell(new Phrase(df.format(quantity) + dyeingChemicalList.get(i).getState(), f)); 
                                    
                                }
                                else
                                {
                                    DecimalFormat df = new DecimalFormat("#,###.##");
                                    df.setRoundingMode(RoundingMode.CEILING);
                                    table.addCell(new Phrase(df.format(quantity) + dyeingChemicalList.get(i).getState(), f)); 
                                }
                            }
                            else
                            {
                                Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                                if(quantity.toString().contains(".0") == true)
                                {
                                    DecimalFormat df = new DecimalFormat("#,###.00");
                                    df.setRoundingMode(RoundingMode.CEILING);
                                    table.addCell(new Phrase(df.format(quantity) + dyeingChemicalList.get(i).getState(), f));
                                    
                                    
                                }
                                else
                                {
                                    DecimalFormat df = new DecimalFormat("#,###.##");
                                    df.setRoundingMode(RoundingMode.CEILING);
                                    table.addCell(new Phrase(df.format(quantity)+ dyeingChemicalList.get(i).getState(), f));
                                }
                            }
                            
                            if(i == dyeingChemicalList.size() - 1)
                            {
                                table.addCell(" ");
                                table.addCell(" ");
                                table.addCell(" ");
                                table.addCell(" ");
                            }
                            
                            rows++;
                        }
                    }
                }
                else
                {
                    document.add(table);
                    document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                    table = new PdfPTable(columnWidths);
                    table.setWidthPercentage(100);
                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    table.getDefaultCell().setUseAscender(true);
                    table.getDefaultCell().setUseDescender(true);
                    cell = new PdfPCell();
                    //table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
                    table.addCell(new Phrase("Process Name", f));
                    table.addCell(new Phrase("GPL", f));
                    table.addCell(new Phrase("%", f));
                    table.addCell(new Phrase("Quantity", f));

                    table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);

                    if (dyeingProcessList.get(x).getdyeingProcessOrder().contains("[a-zA-Z]+") == false){
                        table.addCell(new Phrase(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(x).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                        table.addCell(" ");
                        table.addCell(" ");
                        table.addCell(" ");
                        rows++;
                    }
                    else if(dyeingProcessList.get(x).getdyeingProcessOrder().contains("[a-zA-Z]+") == true){
                        String dyeingSubProcessLetter = dyeingProcessList.get(x).getdyeingProcessOrder().replaceAll("[^A-Za-z]+", "");

                        table.addCell(new Phrase("    " + dyeingSubProcessLetter.toUpperCase() + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                        table.addCell(" ");
                        table.addCell(" ");
                        table.addCell(" ");
                        rows++;

                        dyeingChemicalList = dChemHandler.GetAllDyeingChemicalFromDyeingProcessID(dyeingProcessList.get(x).getId());

                        if(dyeingChemicalList.size() > 0)
                        {
                            Collections.sort(dyeingChemicalList, new Comparator<DyeingChemical>(){
                                @Override
                                public int compare(DyeingChemical o1, DyeingChemical o2){
                                    int rollno1 = o1.getOrder();
                                    int rollno2 = o2.getOrder();

                                    /*For ascending order*/
                                    return rollno1-rollno2;

                                }                    
                            });
                        }

                        for(int i = 0; i<dyeingChemicalList.size(); i++)
                        {
                            table.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalID()), f));
                            if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                                table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue()), f));
                                table.addCell(" ");
                            }
                            else
                            {
                                table.addCell(" ");
                                table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue()), f));
                            }
                            table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue() * jobOrderDetails.getVolumeH20()), f));
                            rows++;
                        }
                    }
                }
            }
        }
        else
        {
            if (dyeingProcessList.get(0).getdyeingProcessOrder().matches("[0-9]+")){
                    table.addCell(new Phrase(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(0).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(0).getDyeingProcessName(), f));
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    rows++;
                    dyeingChemicalList = dChemHandler.GetAllDyeingChemicalFromDyeingProcessID(dyeingProcessList.get(0).getId());

                    if(dyeingChemicalList.size() > 0)
                    {
                        Collections.sort(dyeingChemicalList, new Comparator<DyeingChemical>(){
                            @Override
                            public int compare(DyeingChemical o1, DyeingChemical o2){
                                int rollno1 = o1.getOrder();
                                int rollno2 = o2.getOrder();

                                /*For ascending order*/
                                return rollno1-rollno2;

                            }                    
                        });
                    }

                    for(int i = 0; i<dyeingChemicalList.size(); i++)
                    {
                        table.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalID()), f));
                        if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                            table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue()), f));
                            table.addCell(" ");
                        }
                        else
                        {
                            table.addCell(" ");
                            table.addCell(new Phrase(String.valueOf(dyeingChemicalList.get(i).getValue()), f));
                        }

                        if(dyeingChemicalList.get(i).getType()== "%")
                        {
                           Float fquantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                           Double quantity = Double.parseDouble(fquantity.toString());
                           if(quantity.toString().contains(".0") == true)
                           {
                               DecimalFormat df = new DecimalFormat("#,###.00");
                                df.setRoundingMode(RoundingMode.CEILING);
                                table.addCell(new Phrase(df.format(quantity).toString() + dyeingChemicalList.get(i).getState(), f)); 
                                
                           }
                           else
                           {
                               DecimalFormat df = new DecimalFormat("#,###.##");
                                df.setRoundingMode(RoundingMode.CEILING);
                                table.addCell(new Phrase(df.format(quantity).toString() + dyeingChemicalList.get(i).getState(), f)); 
                           }
                           
                        }
                        else
                        {
                            Float fquantity = Float.parseFloat(volume) * dyeingChemicalList.get(i).getValue();
                            Double quantity = Double.parseDouble(fquantity.toString());
                           if(quantity.toString().contains(".0"))
                           {
                               DecimalFormat df = new DecimalFormat("#,###.00");
                            df.setRoundingMode(RoundingMode.CEILING);
                            table.addCell(new Phrase(df.format(quantity).toString() + dyeingChemicalList.get(i).getState(), f));
                               
                           }
                           else
                           {
                               DecimalFormat df = new DecimalFormat("#,###.##");
                            df.setRoundingMode(RoundingMode.CEILING);
                            table.addCell(new Phrase(df.format(quantity).toString() + dyeingChemicalList.get(i).getState(), f));
                           }
                            
                        }
                        
                        if(i == dyeingChemicalList.size() - 1)
                        {
                            table.addCell(" ");
                            table.addCell(" ");
                            table.addCell(" ");
                            table.addCell(" ");
                        }
                        rows++;
                    }
                }
        }
        
        document.add(table);
        
        document.add(Chunk.NEWLINE);
        
        table = new PdfPTable(3);
        
        Paragraph masterRectangleText = new Paragraph("Master Sample", controlSlipHeaderFont);
        Paragraph prodRectangleText = new Paragraph("Production Sample", controlSlipHeaderFont);
        
        PdfPCell cellMasterSample = new PdfPCell(masterRectangleText);
        cellMasterSample.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellMasterSample.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellMasterSample.setFixedHeight(80f);
        table.addCell(cellMasterSample);
        
        PdfPCell cellFiller = new PdfPCell();
        cellFiller.setFixedHeight(80f);
        cellFiller.setBorder(Rectangle.NO_BORDER);
        table.addCell(cellFiller);
        
        PdfPCell cellCottonDyeing = new PdfPCell(prodRectangleText);
        cellCottonDyeing.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellCottonDyeing.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCottonDyeing.setFixedHeight(80f);
        table.addCell(cellCottonDyeing);
        
        document.add(table);
        
        //document.add(chapter);
        
        return document;
    }
    
    public Document AddSecondPageHeader(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
    {
        DyeingProgramNameHandler dyeingProgramNameHandler = new DyeingProgramNameHandler();
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(companyHeader);
        //Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 2;
        //chapter.setNumberDepth(0);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
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
        
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(dyeingProcessHeader);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        
        Phrase p = new Phrase("Customer: " + customerDetails.getCustomerName(), f);
        //p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase("Batch #: " + jobOrderDetails.getBatchNo(), f);
        //p2.add("Batch #: " + jobOrderDetails.getBatchNo(), f);
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        //p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber());
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase("Machine: " + machineDetails.getMachineName(), f);
        //p6.add("Machine: " + machineDetails.getMachineName(), f);
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase("Design: " + designDetails.getDesignName(), f);
        //p5.add("Design: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase("Weight: " + jobOrderDetails.getWeight(), f);
        //p4.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("Color: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase("Vol. of Water: " + volume, f);
        //p8.add("Vol. of Water: " + volume, f);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_____________ Drugman:_____________ Operator:_____________ Date:_____________", f);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        return document;
    }
    
    public Document AddThirdPage(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException 
    {
        ResinProgramHandler resinProgramNameHandler = new ResinProgramHandler();
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(companyHeader);
        Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 3;
        chapter.setNumberDepth(0);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("Dyeing Control Slip", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.add(controlSlipHeader);
        
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase filler = new Phrase();
        filler.add(" ");
        PdfPCell pCell1 = new PdfPCell(filler);
        table.addCell(filler);
        chapter.add(table);
        
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph resinProgramHeader = new Paragraph(resinProgramNameHandler.GetResinProgramNameFromResinProgramID(jobOrderDetails.getResinProgramID()), dyeingProcessFont);
        resinProgramHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.add(resinProgramHeader);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        
        Phrase p = new Phrase("Customer: " + customerDetails.getCustomerName(), f);
        //p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase("Batch #: " + jobOrderDetails.getBatchNo(), f);
        //p2.add("Batch #: " + jobOrderDetails.getBatchNo(), f);
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        //p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber(), f);
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase("Machine: " + machineDetails.getMachineName(), f);
        //p6.add("Machine: " + machineDetails.getMachineName(), f);
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase("Design: " + designDetails.getDesignName(), f);
        //p5.add("Design: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase("Weight: " + jobOrderDetails.getWeight(), f);
        //p4.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("Color: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase("Vol. of Water: " + volume, f);
        //p8.add("Vol. of Water: " + volume, f);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        chapter.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_____________ Drugman:_____________ Operator:_____________ Date:_____________", f);
        chapter.add(paragraph);
        
        paragraph = new Paragraph("Loading Time:_______________________", f);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        
        chapter.add(paragraph);
        
        document.add(chapter);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        String resinProgramName = resinProgramHandler.GetResinProgramNameFromResinProgramID(jobOrderDetails.getResinProgramID());
        ArrayList<ResinChemical> resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(jobOrderDetails.getResinProgramID());
        
        paragraph = new Paragraph(resinProgramName, dyeingProcessFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        float[] columnWidths = {5, 2, 2, 2};
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        PdfPCell cell = new PdfPCell();
        //table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell(new Phrase("Resin Chemical", f));
            table.addCell(new Phrase("GPL",f));
            table.addCell(new Phrase("%",f));
            table.addCell(new Phrase("Quantity",f));

            
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        
        for (int x = 0; x < resinChemicalList.size(); x++) {
                table.addCell(new Phrase((x+1) + ".  " + chemicalHandler.GetChemicalNameFromChemicalID(resinChemicalList.get(x).getChemicalID()), f));
                //table.addCell(String.valueOf(resinChemicalList.get(x).getGPLValue()));
                if("GPL".equals(resinChemicalList.get(x).getType().toUpperCase())){
                    table.addCell(new Phrase(String.valueOf(resinChemicalList.get(x).getGPLValue()), f));
                    table.addCell(" ");
                }
                else
                {
                    table.addCell(" ");
                    table.addCell(new Phrase(String.valueOf(resinChemicalList.get(x).getGPLValue()), f));
                }
                //table.addCell(String.valueOf(jobOrderDetails.getVolumeH20() * resinChemicalList.get(x).getGPLValue()));
                if(resinChemicalList.get(x).getType()== "%")
                {
                    Float fQuantity= jobOrderDetails.getWeight() * resinChemicalList.get(x).getGPLValue();
                   Double quantity = Double.parseDouble(fQuantity.toString());
                   if(quantity.toString().contains(".0"))
                    {
                        DecimalFormat df = new DecimalFormat("#,###.00");
                            df.setRoundingMode(RoundingMode.CEILING);
                   table.addCell(new Phrase(df.format(quantity).toString() + resinChemicalList.get(x).getState(), f)); 

                    }
                    else
                    {
                        DecimalFormat df = new DecimalFormat("#,###.##");
                            df.setRoundingMode(RoundingMode.CEILING);
                   table.addCell(new Phrase(df.format(quantity).toString() + resinChemicalList.get(x).getState(), f)); 
                    }
                               
                }
                else
                {
                    Double quantity = Double.parseDouble(volume) * resinChemicalList.get(x).getGPLValue();
                    if(quantity.toString().contains(".0"))
                    {
                        DecimalFormat df = new DecimalFormat("#,###.00");
                            df.setRoundingMode(RoundingMode.CEILING);
                            
                    table.addCell(new Phrase(df.format(quantity).toString() + resinChemicalList.get(x).getState(), f));

                    }
                    else
                    {
                        DecimalFormat df = new DecimalFormat("#,###.##");
                            df.setRoundingMode(RoundingMode.CEILING);
                            
                    table.addCell(new Phrase(df.format(quantity).toString() + resinChemicalList.get(x).getState(), f));
                    }
                               
                }
                //table.addCell(" ");
                //table.addCell(" ");
                //table.addCell(" ");
        }
        
        document.add(table);
        
        return document;
    }
    
    public void printPDF () throws DocumentException, IOException
    {
        /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter f1 =PdfWriter.getInstance(document,baos);
        
        try{ 
            byte[] pdfbyte = baos.toByteArray();
            //System.out.println(pdf);
            InputStream bis = new ByteArrayInputStream(pdfbyte);
            SimpleDoc pdfp = new SimpleDoc(bis, DocFlavor.BYTE_ARRAY.AUTOSENSE, null);
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob printjob= printService.createPrintJob();
            printjob.print(pdfp, new HashPrintRequestAttributeSet());
            bis.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "EEE :"+e);
            e.printStackTrace();
        }
        catch (PrintException ex) {    
        } */
        
        File myFile = new File("C:\\chapter_title.pdf");
        Desktop.getDesktop().open(myFile);
        
        /*FileInputStream psStream = null;
        try {
            psStream = new FileInputStream("C:\\chapter_title.pdf");
            } catch (FileNotFoundException ffne) {
              ffne.printStackTrace();
            }
            if (psStream == null) {
                return;
            }
        DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc myDoc = new SimpleDoc(psStream, psInFormat, null);  
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        //PrintService[] services = PrintServiceLookup.lookupPrintServices(psInFormat, aset);
        
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        PrintService printService = null;
        if(printerJob.printDialog())
        {
            printService = printerJob.getPrintService();
        }
        
         
        // this step is necessary because I have several printers configured
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++){
            String svcName = services[i].toString();           
            if (svcName.contains("printer closest to me")){
                myPrinter = services[i];
                System.out.println("my printer found: "+svcName);
                break;
            }
        }
         
        if (printService != null) {            
            DocPrintJob job = printService.createPrintJob();
            try {
            job.print(myDoc, aset);
             
            } catch (Exception pe) {
            }
        } else {
            System.out.println("no printer services found");
        }*/
   }
    

}
