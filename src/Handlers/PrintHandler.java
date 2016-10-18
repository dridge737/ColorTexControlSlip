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
import DataEntities.ProcessOrder;
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
import java.awt.print.PrinterJob;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
/**
 *
 * @author imbuenyson
 */
public class PrintHandler {
    public static final String DEST = "C:\\chapter_title.pdf";
 
    private int chapterNumber;
    
    public void createPDF(Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, ProcessOrder processOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PrintHandler().renderPDF(DEST, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, processOrderDetails, dyeingProgramDetails, volume);
        printPDF();
    }
 
    public void renderPDF(String dest, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, ProcessOrder processOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException {
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
        p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase();
        p2.add("Batch #: " + jobOrderDetails.getBatchNo());
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase();
        p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber());
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase();
        p6.add("Weight: " + processOrderDetails.getWeight());
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase();
        p5.add("Design: " + designDetails.getDesignName());
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add(" ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase();
        p7.add("Color: " + chemicalDetails.getColorName());
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
        p.add("Customer: " + customerDetails.getCustomerName());
        pCell = new PdfPCell(p);
        table.addCell(p);
        
        p2 = new Phrase();
        p2.add("Batch #: " + jobOrderDetails.getBatchNo());
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        p3 = new Phrase();
        p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber());
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        p6 = new Phrase();
        p6.add("Weight: " + processOrderDetails.getWeight());
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        p5 = new Phrase();
        p5.add("Design: " + designDetails.getDesignName());
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        p4 = new Phrase();
        p4.add(" ");
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        p7 = new Phrase();
        p7.add("Color: " + chemicalDetails.getColorName());
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
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document = AddSecondPage(document, writer, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, processOrderDetails, dyeingProgramDetails, volume);
        
        //CHANGE CONDITION TO IF RESIGNPROGRAM EXISTING
        if(processOrderDetails.getResinProgramID() > 0)
        {
            document = AddThirdPage(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, processOrderDetails, dyeingProgramDetails, volume);
        }
        
        document.close();
    }
    
    public Document AddSecondPage(Document document, PdfWriter writer, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, ProcessOrder processOrderDetails, DyeingProgram dyeingProgramDetails, String volume)  throws IOException, DocumentException 
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
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramDetails.getDyeingProgramName(), dyeingProcessFont);
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
        p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase();
        p2.add("Batch #: " + jobOrderDetails.getBatchNo());
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase();
        p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber());
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase();
        p6.add("Machine: " + machineDetails.getMachineName());
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase();
        p5.add("Design #: " + designDetails.getDesignName());
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add("Weight: " + processOrderDetails.getWeight());
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase();
        p7.add("Color: " + chemicalDetails.getColorName());
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase();
        p8.add("Vol. of Water: " + volume);
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
            table.addCell(" ");
            table.addCell("GPL");
            table.addCell("%");
            table.addCell("Quantity");
            
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
        DyeingChemicalHandler dChemHandler = new DyeingChemicalHandler();
        DyeingProcessHandler dProcessHandler = new DyeingProcessHandler();
        ChemicalHandler chemHandler = new ChemicalHandler();
        ArrayList<DyeingProcess> dyeingProcessList = dProcessHandler.GetAllDyeingProcessAndSubProcessByDyeingProgramId(dyeingProgramDetails.getDyeingProgramId());
        ArrayList<DyeingChemical> dyeingChemicalList = null;
        int rows = 0;
        
        for(int x=0; x<dyeingProcessList.size(); x++)
        {
            if(rows%30 != 0 || rows == 0){
                if (dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+")){
                    table.addCell(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(x).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(x).getDyeingProcessName());
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    rows++;
                }
                else if(!(dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+"))){
                    String dyeingSubProcessLetter = dyeingProcessList.get(x).getdyeingProcessOrder().replaceAll("[^A-Za-z]+", "");

                    table.addCell("    " + dyeingSubProcessLetter.toUpperCase() + ". " + dyeingProcessList.get(x).getDyeingProcessName());
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
                        table.addCell("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalID()));
                        if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                            table.addCell(String.valueOf(dyeingChemicalList.get(i).getValue()));
                            table.addCell(" ");
                        }
                        else
                        {
                            table.addCell(" ");
                            table.addCell(String.valueOf(dyeingChemicalList.get(i).getValue()));
                        }
                        
                        if(dyeingChemicalList.get(i).getType()== "%")
                        {
                           Float quantity = processOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                           table.addCell(quantity.toString() + dyeingChemicalList.get(i).getState()); 
                        }
                        else
                        {
                            Float quantity = Float.parseFloat(volume) * dyeingChemicalList.get(i).getValue();
                            table.addCell(quantity.toString() + dyeingChemicalList.get(i).getState());
                        }
                        
                        rows++;
                    }
                }
            }
            else
            {
                document.add(table);
                document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, processOrderDetails, dyeingProgramDetails, volume);
                table = new PdfPTable(columnWidths);
                table.setWidthPercentage(100);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.getDefaultCell().setUseAscender(true);
                table.getDefaultCell().setUseDescender(true);
                cell = new PdfPCell();
                table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
                table.addCell("Process Name");
                table.addCell("GPL");
                table.addCell("%");
                table.addCell("Quantity");

                table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                
                if (dyeingProcessList.get(x).getdyeingProcessOrder().contains("[a-zA-Z]+") == false){
                    table.addCell(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(x).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(x).getDyeingProcessName());
                    table.addCell(" ");
                    table.addCell(" ");
                    table.addCell(" ");
                    rows++;
                }
                else if(dyeingProcessList.get(x).getdyeingProcessOrder().contains("[a-zA-Z]+") == true){
                    String dyeingSubProcessLetter = dyeingProcessList.get(x).getdyeingProcessOrder().replaceAll("[^A-Za-z]+", "");

                    table.addCell("    " + dyeingSubProcessLetter.toUpperCase() + ". " + dyeingProcessList.get(x).getDyeingProcessName());
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
                        table.addCell("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalID()));
                        if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                            table.addCell(String.valueOf(dyeingChemicalList.get(i).getValue()));
                            table.addCell(" ");
                        }
                        else
                        {
                            table.addCell(" ");
                            table.addCell(String.valueOf(dyeingChemicalList.get(i).getValue()));
                        }
                        table.addCell(String.valueOf(dyeingChemicalList.get(i).getValue() * processOrderDetails.getVolumeH20()));
                        rows++;
                    }
                }
            }
        }
        
        document.add(table);
        
        document.add(Chunk.NEWLINE);
        
        table = new PdfPTable(3);
        
        Paragraph rectangleText = new Paragraph("SAMPLE", controlSlipHeaderFont);
        
        PdfPCell cellMasterSample = new PdfPCell(rectangleText);
        cellMasterSample.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellMasterSample.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellMasterSample.setFixedHeight(80f);
        table.addCell(cellMasterSample);
        
        PdfPCell cellFiller = new PdfPCell();
        cellFiller.setFixedHeight(80f);
        cellFiller.setBorder(Rectangle.NO_BORDER);
        table.addCell(cellFiller);
        
        PdfPCell cellCottonDyeing = new PdfPCell(rectangleText);
        cellCottonDyeing.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellCottonDyeing.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCottonDyeing.setFixedHeight(80f);
        table.addCell(cellCottonDyeing);
        
        document.add(table);
        
        document.add(Chunk.NEWLINE);
        
        table = new PdfPTable(3);
        
        PdfPCell cellPesDyeing = new PdfPCell(rectangleText);
        cellPesDyeing.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellPesDyeing.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellPesDyeing.setFixedHeight(80f);
        table.addCell(cellPesDyeing);
        
        table.addCell(cellFiller);
        
        PdfPCell cellTreatment = new PdfPCell(rectangleText);
        cellTreatment.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cellTreatment.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTreatment.setFixedHeight(80f);
        table.addCell(cellTreatment);
        
        document.add(table);
        //document.add(chapter);
        
        return document;
    }
    
    public Document AddSecondPageHeader(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, ProcessOrder processOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
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
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramDetails.getDyeingProgramName(), dyeingProcessFont);
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
        p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase();
        p2.add("Batch #: " + jobOrderDetails.getBatchNo());
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase();
        p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber());
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase();
        p6.add("Machine: " + machineDetails.getMachineName());
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase();
        p5.add("Design: " + designDetails.getDesignName());
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add("Weight: " + processOrderDetails.getWeight());
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase();
        p7.add("Color: " + chemicalDetails.getColorName());
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase();
        p8.add("Vol. of Water: " + volume);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_________ Drugman:_________ Operator:_________ Date:_________");
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        return document;
    }
    
    public Document AddThirdPage(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, ChemicalColor chemicalDetails, JobOrder jobOrderDetails, ProcessOrder processOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException 
    {
        Font companyHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC);
        Paragraph companyHeader = new Paragraph("Colortex Processing Inc.", companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(companyHeader);
        Chapter chapter = new Chapter(companyHeader, 1);
        chapterNumber = 3;
        chapter.setNumberDepth(0);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
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
        
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.NORMAL);
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramDetails.getDyeingProgramName(), dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.add(dyeingProcessHeader);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        table.addCell(filler);
        
        Phrase p = new Phrase();
        p.add("Customer: " + customerDetails.getCustomerName());
        PdfPCell pCell = new PdfPCell(p);
        table.addCell(p);
        
        Phrase p2 = new Phrase();
        p2.add("Batch #: " + jobOrderDetails.getBatchNo());
        pCell = new PdfPCell(p2);
        table.addCell(p2);
        
        Phrase p3 = new Phrase();
        p3.add("Job#/DR#: " + jobOrderDetails.getDrNumber());
        pCell = new PdfPCell(p3);
        table.addCell(p3);
        
        Phrase p6 = new Phrase();
        p6.add("Machine: " + machineDetails.getMachineName());
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase();
        p5.add("Design: " + designDetails.getDesignName());
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase();
        p4.add("Weight: " + processOrderDetails.getWeight());
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase();
        p7.add("Color: " + chemicalDetails.getColorName());
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase();
        p8.add("Vol. of Water: " + volume);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        chapter.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_________ Drugman:_________ Operator:_________ Date:_________");
        chapter.add(paragraph);
        
        paragraph = new Paragraph("Loading Time:_______________________");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        
        chapter.add(paragraph);
        
        document.add(chapter);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        String resinProgramName = resinProgramHandler.GetResinProgramNameFromResinProgramID(processOrderDetails.getResinProgramID());
        ArrayList<ResinChemical> resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(processOrderDetails.getResinProgramID());
        
        paragraph = new Paragraph(resinProgramName);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);
        
        float[] columnWidths = {5, 3, 3};
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        PdfPCell cell = new PdfPCell();
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell("Resin Chemical");
            table.addCell("GPL");
            table.addCell("Quantity");

            
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
            table.addCell(" ");
            table.addCell(" ");
            table.addCell(" ");
        
        for (int x = 0; x < resinChemicalList.size(); x++) {
                table.addCell((x+1) + ".  " + chemicalHandler.GetChemicalNameFromChemicalID(resinChemicalList.get(x).getChemicalID()));
                table.addCell(String.valueOf(resinChemicalList.get(x).getGPLValue()));
                table.addCell(String.valueOf(processOrderDetails.getVolumeH20() * resinChemicalList.get(x).getGPLValue()));
                table.addCell(" ");
                table.addCell(" ");
                table.addCell(" ");
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
