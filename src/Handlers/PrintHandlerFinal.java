/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;
import DataEntities.DesignColor;
import DataEntities.Customer;
import DataEntities.Design;
import DataEntities.DyeingChemical;
import DataEntities.DyeingProcess;
import DataEntities.DyeingProgram;
import DataEntities.JobOrder;
import DataEntities.JobOrderExtended;
import DataEntities.Machine;
import DataEntities.ResinChemical;
import DataEntities.ResinJob;
import Helpers.RomanNumber;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.FontSelector;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 *
 * @author imbuenyson
 */
public class PrintHandlerFinal {
    public static final String DEST = "C:\\chapter_title.pdf";
 
    private ComputeHelper computeHelper = new ComputeHelper();
    private Font f  = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
    private Font f1  = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);
    
    public void createPDF(Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrderExtended jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        renderPDF(DEST, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        printPDF();
    }
    
    public void renderPDF(String dest, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrderExtended jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException {
        Document document = new Document();
        ArrayList<ResinJob> resinList = jobOrderDetails.getThisResinJob();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        //612x756
        Rectangle pageSize = new Rectangle(612,756);
        document.setPageSize(PageSize.LETTER);
        document.setMargins(60, 36, 12, 30);
        document.open();
        document = addFirstPageSection(DEST, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume, document);
        document = addFirstPageSection(DEST, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume, document);

        document.newPage();
        document = AddSecondPage(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        document.newPage();
        
        if(resinList.size() > 0)
        {
            document = AddThirdPage(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume, resinList.get(0));
        }
        document.close();
    }
    public PdfPTable AddLongTable()throws IOException, DocumentException
    {
        PdfPTable table = new PdfPTable(20);
        table.setWidths(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
        table.setWidthPercentage(100);
        
        table.addCell("1");
        table.addCell("2");
        table.addCell("3");
        table.addCell("4");
        table.addCell("5");
        table.addCell("6");
        table.addCell("7");
        table.addCell("8");
        table.addCell("9");
        table.addCell("10");
        table.addCell("11");
        table.addCell("12");
        table.addCell("13");
        table.addCell("14");
        table.addCell("15");
        table.addCell("16");
        table.addCell("17");
        table.addCell("18");
        table.addCell("19");
        table.addCell("20");
        table.getDefaultCell().setFixedHeight(60f);
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        
                table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        
        
        return table;
    }
    
    public Document AddSecondPageOtherDetails(Document document, JobOrder jobOrderDetails, Machine machineDetails)throws IOException, DocumentException
    {
        float[] rootTableColumn = {4, 1, 4};
        PdfPTable rootTable = new PdfPTable(rootTableColumn);
        rootTable.setWidthPercentage(100);
        rootTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        
        PdfPTable referenceTable = new PdfPTable(2);
        referenceTable.setWidths(new int[]{1,1});
        referenceTable.setWidthPercentage(50);
        referenceTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        referenceTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        Paragraph reference = new Paragraph("Reference: ", f);
        Paragraph referenceValue = new Paragraph(jobOrderDetails.getReference(), f);
        Paragraph programNumber = new Paragraph("Program Number: ", f);
        Paragraph programNumberValue = new Paragraph(jobOrderDetails.getProgramNumber(), f);
        Paragraph location = new Paragraph("Location: ", f);
        Paragraph locationValue = new Paragraph(jobOrderDetails.getLocation(), f);
        referenceTable.addCell(reference);
        referenceTable.addCell(referenceValue);
        referenceTable.addCell(programNumber);
        referenceTable.addCell(programNumberValue);
        referenceTable.addCell(location);
        referenceTable.addCell(locationValue);
        rootTable.addCell(referenceTable);
        PdfPCell fillerCell = new PdfPCell(new Phrase(" ", f1));
        fillerCell.setBorder(PdfPCell.NO_BORDER);
        rootTable.addCell(fillerCell);
        
        MachineHandler machineHandler = new MachineHandler();
        machineDetails = machineHandler.GetMachineDetailsById(machineDetails.getMachineId());
        
        float[] columnWidths = new float[machineDetails.getNumOfLoad() + 1];
        columnWidths[0] = 4;
        for (int i = 0; i < machineDetails.getNumOfLoad(); i++) {
                columnWidths[i+1] = 1;
            
        }
        PdfPTable loadTable = new PdfPTable(columnWidths);
        loadTable.setWidthPercentage(40);
        loadTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
                PdfPCell cellOne = new PdfPCell(new Phrase("Loading Arrangment", f1));
                cellOne.setBorder(PdfPCell.NO_BORDER);
                loadTable.addCell(cellOne);
                
        Machine dyeingMachine = machineHandler.GetMachineDetailsById(jobOrderDetails.getDyeingMachineID());
        
        for (int i = 0; i < machineDetails.getNumOfLoad(); i++) {
            
                cellOne = new PdfPCell(new Phrase(" ", f1));
                    cellOne.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellOne.setHorizontalAlignment(Element.ALIGN_CENTER);
                    loadTable.addCell(cellOne);            
        }
        
        
        rootTable.addCell(loadTable);
        //document.add(loadTable);
        document.add(rootTable);
        
        return document;
    }
            
    public Document AddSecondPage(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
    {
        document = AddSecondPageHeaderWithSig(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        //document = addCheckedByImage(document);
        PdfPTable twentyColTable = AddLongTable();
        document.add(twentyColTable);
        document.add(new Paragraph(" "));
        //document.add(Chunk.NEWLINE);
        document = AddSecondPageOtherDetails(document, jobOrderDetails, machineDetails);
        document.add(new Paragraph(" "));
        //document.add(Chunk.NEWLINE);
        document = AddDyeingProgramTableFinal(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
        return document;
    }
    
    public Document AddDyeingProgramTableFinal(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
    {
        float[] columnWidths = {5, 2, 2, 2};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
            table.addCell(" ");
            PdfPCell gplHeader = new PdfPCell();
            Paragraph p = new Paragraph("GPL",f );
            p.setLeading(8f, 0f);
            p.setAlignment(Element.ALIGN_RIGHT);
            gplHeader.setBorder(Rectangle.BOTTOM);
            gplHeader.addElement(p);
            table.addCell(gplHeader);
            PdfPCell percentHeader = new PdfPCell();
            p = new Paragraph("%",f );
            p.setLeading(8f, 0f);
            p.setAlignment(Element.ALIGN_RIGHT);
            percentHeader.setBorder(Rectangle.BOTTOM);
            percentHeader.addElement(p);
            table.addCell(percentHeader);
            PdfPCell quantityHeader = new PdfPCell();
            p = new Paragraph("Quantity",f );
            p.setLeading(8f, 0f);
            p.setAlignment(Element.ALIGN_RIGHT);
            quantityHeader.setBorder(Rectangle.BOTTOM);
            quantityHeader.addElement(p);
            table.addCell(quantityHeader);
                        
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        document.add(table);
        DyeingChemicalHandler dChemHandler = new DyeingChemicalHandler();
        DyeingProcessHandler dProcessHandler = new DyeingProcessHandler();
        ChemicalHandler chemHandler = new ChemicalHandler();
        JobHandler jHandler = new JobHandler();
        ArrayList<DyeingProcess> dyeingProcessList = dProcessHandler.GetAllDyeingProcessAndSubProcessByDyeingProgramId(dyeingProgramDetails.getID());
        ArrayList<DyeingChemical> dyeingChemicalList = null;
        int rows = 1;
        int rowLimit = 17;
        int rowLimit2 = 32;
        PdfPCell dyeingCell;
        dyeingCell = new PdfPCell(new Phrase("S/N"));
        dyeingCell.setRowspan(4);
        PdfPTable processTable = new PdfPTable(columnWidths);
        processTable.setWidthPercentage(100);
        processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
        processTable.getDefaultCell().setUseAscender(true);
        processTable.getDefaultCell().setUseDescender(true);
        for(int x=0; x<dyeingProcessList.size(); x++)
        {
            if (dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+")){
                dyeingCell = new PdfPCell(new Phrase(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(x).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                dyeingCell.setColspan(4);
                dyeingCell.setBorder(Rectangle.BOTTOM);
                processTable.addCell(dyeingCell);      

                dyeingChemicalList = dChemHandler.GetAllDyeingChemicalFromDyeingProcessID(dyeingProcessList.get(x).getId());
                if(dyeingChemicalList.size() > 0)
                {
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
                        processTable.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalId()), f));
                        if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                            PdfPCell gplCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                Float gplFloat = dyeingChemicalList.get(i).getValue();
                                Double gplDouble = Double.parseDouble(gplFloat.toString());
                                p = new Paragraph(df.format(gplDouble),f );
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                gplCell.setBorder(Rectangle.BOTTOM);
                                gplCell.addElement(p);
                                processTable.addCell(gplCell);
                            processTable.addCell(" ");
                        }
                        else
                        {
                            processTable.addCell(" ");
                            PdfPCell gplCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                Float gplFloat = dyeingChemicalList.get(i).getValue();
                                Double gplDouble = Double.parseDouble(gplFloat.toString());
                                p = new Paragraph(df.format(gplDouble),f );
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                gplCell.setBorder(Rectangle.BOTTOM);
                                gplCell.addElement(p);
                                processTable.addCell(gplCell);
                        }


                        if(dyeingChemicalList.get(i).getType()== "%")
                        {
                            Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                             Double quantity = Double.parseDouble(fquantity.toString());
                            //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                            //Double quantity = Double.parseDouble(fQuantity.toString());

                            if(quantity.toString().contains(".0") == true)
                            {
                                PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);

                            }
                            else
                            {
                                PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);
                            }
                        }
                        else
                        {
                            //Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                            //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                            //Double quantity = Double.parseDouble(fQuantity.toString());
                            Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                             Double quantity = Double.parseDouble(fquantity.toString());
                            if(quantity.toString().contains(".0") == true)
                            {
                                PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);
                            }
                            else
                            {
                                PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);
                            }
                        }
                    }
                    rows = rows + processTable.size();
                    if(rows == rowLimit)
                    {
                        rowLimit = rowLimit2;
                        document.add(processTable);
                        
                        rows = processTable.size() + 1;
                        
                        processTable = new PdfPTable(columnWidths);
                        processTable.setWidthPercentage(100);
                        processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        processTable.getDefaultCell().setUseAscender(true);
                        processTable.getDefaultCell().setUseDescender(true);
                        
                        document.newPage();
                        document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                        document.add(table);
                    }
                    else if(rows > rowLimit)
                    {
                        rowLimit = rowLimit2;
                        document.newPage();
                        document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                        document.add(table);
                        document.add(processTable);
                        
                        rows = processTable.size() + 1;
                        
                        processTable = new PdfPTable(columnWidths);
                        processTable.setWidthPercentage(100);
                        processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        processTable.getDefaultCell().setUseAscender(true);
                        processTable.getDefaultCell().setUseDescender(true);
                    }
                    else
                    {
                        document.add(processTable);
                        
                        processTable = new PdfPTable(columnWidths);
                        processTable.setWidthPercentage(100);
                        processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                        processTable.getDefaultCell().setUseAscender(true);
                        processTable.getDefaultCell().setUseDescender(true);
                    }
                }
            }
            else if(!(dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+"))){
                String dyeingSubProcessLetter = dyeingProcessList.get(x).getdyeingProcessOrder().replaceAll("[^A-Za-z]+", "");

                dyeingCell = new PdfPCell(new Phrase("    " + dyeingSubProcessLetter.toUpperCase() + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                dyeingCell.setColspan(4);
                dyeingCell.setBorder(Rectangle.BOTTOM);
                processTable.addCell(dyeingCell);

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
                    processTable.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalId()), f));
                    if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                        PdfPCell gplCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                Float gplFloat = dyeingChemicalList.get(i).getValue();
                                Double gplDouble = Double.parseDouble(gplFloat.toString());
                                p = new Paragraph(df.format(gplDouble),f );
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                gplCell.setBorder(Rectangle.BOTTOM);
                                gplCell.addElement(p);
                                processTable.addCell(gplCell);
                        processTable.addCell(" ");
                    }
                    else
                    {
                        processTable.addCell(" ");
                        PdfPCell gplCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                Float gplFloat = dyeingChemicalList.get(i).getValue();
                                Double gplDouble = Double.parseDouble(gplFloat.toString());
                                p = new Paragraph(df.format(gplDouble),f );
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                gplCell.setBorder(Rectangle.BOTTOM);
                                gplCell.addElement(p);
                                processTable.addCell(gplCell);
                    }

                    if(dyeingChemicalList.get(i).getType()== "%")
                    {
                        Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                             Double quantity = Double.parseDouble(fquantity.toString());
                        //Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                        //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                        //Double quantity = Double.parseDouble(fQuantity.toString());
                        if(quantity.toString().contains(".0") == true)
                        {
                            PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity)  + " " + dyeingChemicalList.get(i).getState(), f);
                               p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);

                        }
                        else
                        {
                            PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,###.00");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);
                        }
                    }
                    else
                    {
                        Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                             Double quantity = Double.parseDouble(fquantity.toString());
                        //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                        //Double quantity = Double.parseDouble(fQuantity.toString());
                        //Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                        if(quantity.toString().contains(".0") == true)
                        {
                            PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);


                        }
                        else
                        {
                            PdfPCell dyeingQuantityCell = new PdfPCell();
                                DecimalFormat df = new DecimalFormat("#,##0.00000");
                                df.setRoundingMode(RoundingMode.CEILING);
                                p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                p.setLeading(8f, 0f);
                                p.setAlignment(Element.ALIGN_RIGHT);
                                dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                dyeingQuantityCell.addElement(p);
                                processTable.addCell(dyeingQuantityCell);
                        }
                    }
                }
                rows = rows + processTable.size();
                if(rows == rowLimit)
                {
                    rowLimit = rowLimit2;
                    document.add(processTable);

                    rows = processTable.size() + 1;

                    processTable = new PdfPTable(columnWidths);
                    processTable.setWidthPercentage(100);
                    processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    processTable.getDefaultCell().setUseAscender(true);
                    processTable.getDefaultCell().setUseDescender(true);

                    document.newPage();
                    document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                    document.add(table);
                }
                else if(rows > rowLimit)
                {
                    rowLimit = rowLimit2;
                    document.newPage();
                    document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                    document.add(table);
                    document.add(processTable);

                    rows = processTable.size() + 1;

                    processTable = new PdfPTable(columnWidths);
                    processTable.setWidthPercentage(100);
                    processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    processTable.getDefaultCell().setUseAscender(true);
                    processTable.getDefaultCell().setUseDescender(true);
                }
                else
                {
                    document.add(processTable);

                    processTable = new PdfPTable(columnWidths);
                    processTable.setWidthPercentage(100);
                    processTable.getDefaultCell().setBorder(Rectangle.BOTTOM);
                    processTable.getDefaultCell().setUseAscender(true);
                    processTable.getDefaultCell().setUseDescender(true);
                }
            }
        }
        return document;
    }
    
    public Document AddDyeingProgramTable(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
    {
        float[] columnWidths = {5, 2, 2, 2};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
            table.addCell(" ");
            PdfPCell gplHeader = new PdfPCell();
            Paragraph p = new Paragraph("GPL",f );
            p.setLeading(8f, 0f);
            p.setAlignment(Element.ALIGN_RIGHT);
            gplHeader.setBorder(Rectangle.BOTTOM);
            gplHeader.addElement(p);
            table.addCell(gplHeader);
            PdfPCell percentHeader = new PdfPCell();
            p = new Paragraph("%",f );
            p.setLeading(8f, 0f);
            p.setAlignment(Element.ALIGN_RIGHT);
            percentHeader.setBorder(Rectangle.BOTTOM);
            percentHeader.addElement(p);
            table.addCell(percentHeader);
            PdfPCell quantityHeader = new PdfPCell();
            p = new Paragraph("Quantity",f );
            p.setLeading(8f, 0f);
            p.setAlignment(Element.ALIGN_RIGHT);
            quantityHeader.setBorder(Rectangle.BOTTOM);
            quantityHeader.addElement(p);
            table.addCell(quantityHeader);
                        
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        
        DyeingChemicalHandler dChemHandler = new DyeingChemicalHandler();
        DyeingProcessHandler dProcessHandler = new DyeingProcessHandler();
        ChemicalHandler chemHandler = new ChemicalHandler();
        JobHandler jHandler = new JobHandler();
        ArrayList<DyeingProcess> dyeingProcessList = dProcessHandler.GetAllDyeingProcessAndSubProcessByDyeingProgramId(dyeingProgramDetails.getID());
        ArrayList<DyeingChemical> dyeingChemicalList = null;
        int rows = 0;
        int rowLimit = 17;
        int rowLimit2 = 32;
        PdfPCell dyeingCell;
        dyeingCell = new PdfPCell(new Phrase("S/N"));
        dyeingCell.setRowspan(4);

        for(int x=0; x<dyeingProcessList.size(); x++)
            {
                    if (dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+")){
                        dyeingCell = new PdfPCell(new Phrase(RomanNumber.toRoman(Integer.parseInt(dyeingProcessList.get(x).getdyeingProcessOrder())) + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                        dyeingCell.setColspan(4);
                        dyeingCell.setBorder(Rectangle.BOTTOM);
                        table.addCell(dyeingCell);               
                        rows++;
                        if(rows >= rowLimit)
                        {
                            rowLimit = rowLimit2;
                            document.add(table);
                            document.newPage();
                            document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                            
                            rows = 1;
                            table = new PdfPTable(columnWidths);
                            table.setWidthPercentage(100);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setUseAscender(true);
                            table.getDefaultCell().setUseDescender(true);
                                table.addCell(" ");
                                table.addCell(gplHeader);
                                table.addCell(percentHeader);
                                table.addCell(quantityHeader);

                            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
                        dyeingChemicalList = dChemHandler.GetAllDyeingChemicalFromDyeingProcessID(dyeingProcessList.get(x).getId());
                        if(dyeingChemicalList.size() > 0)
                        {
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
                                table.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalId()), f));
                                if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                                    PdfPCell gplCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        Float gplFloat = dyeingChemicalList.get(i).getValue();
                                        Double gplDouble = Double.parseDouble(gplFloat.toString());
                                        p = new Paragraph(df.format(gplDouble),f );
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        gplCell.setBorder(Rectangle.BOTTOM);
                                        gplCell.addElement(p);
                                        table.addCell(gplCell);
                                    table.addCell(" ");
                                }
                                else
                                {
                                    table.addCell(" ");
                                    PdfPCell gplCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        Float gplFloat = dyeingChemicalList.get(i).getValue();
                                        Double gplDouble = Double.parseDouble(gplFloat.toString());
                                        p = new Paragraph(df.format(gplDouble),f );
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        gplCell.setBorder(Rectangle.BOTTOM);
                                        gplCell.addElement(p);
                                        table.addCell(gplCell);
                                }

                                
                                if(dyeingChemicalList.get(i).getType()== "%")
                                {
                                    Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                                     Double quantity = Double.parseDouble(fquantity.toString());
                                    //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                                    //Double quantity = Double.parseDouble(fQuantity.toString());
                                   
                                    if(quantity.toString().contains(".0") == true)
                                    {
                                        PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);

                                    }
                                    else
                                    {
                                        PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                    }
                                }
                                else
                                {
                                    //Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                                    //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                                    //Double quantity = Double.parseDouble(fQuantity.toString());
                                    Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                                     Double quantity = Double.parseDouble(fquantity.toString());
                                    if(quantity.toString().contains(".0") == true)
                                    {
                                        PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                    }
                                    else
                                    {
                                        PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                    }
                                }

                                if(i == dyeingChemicalList.size() - 1)
                                {
//                                    rows++;
                                    if(rows >= rowLimit)
                                    {
                                        rowLimit = rowLimit2;
                                        document.add(table);
                                        document.newPage();
                                        document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                                        
                                        
                                        rows = 1;
                                        table = new PdfPTable(columnWidths);
                                        table.setWidthPercentage(100);
                                        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                        table.getDefaultCell().setUseAscender(true);
                                        table.getDefaultCell().setUseDescender(true);
                                        table.addCell(" ");
                                        table.addCell(gplHeader);
                                        table.addCell(percentHeader);
                                        table.addCell(quantityHeader);

                                        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                                        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                                    }
//                                    else
//                                    {
//                                        table.addCell(" ");
//                                        table.addCell(" ");
//                                        table.addCell(" ");
//                                        table.addCell(" ");
//                                        rows++;
//                                        if(rows >= rowLimit)
//                                        {
//                                            rowLimit = rowLimit2;
//                                            document.add(table);
//                                            document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
//                                            rows = 1;
//                                            table = new PdfPTable(columnWidths);
//                                            table.setWidthPercentage(100);
//                                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
//                                            table.getDefaultCell().setUseAscender(true);
//                                            table.getDefaultCell().setUseDescender(true);
//                                            table.addCell(" ");
//                                            table.addCell(gplHeader);
//                                            table.addCell(percentHeader);
//                                            table.addCell(quantityHeader);
//
//                                            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
//                                            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//                                        }
//                                        else
//                                        {
//                                            rows--;
//                                        }
//                                    }
                                    
                                    
                                }

                                rows++;
                                if(rows >= rowLimit)
                                {
                                    rowLimit = rowLimit2;
                                    document.add(table);
                                    document.newPage();
                                    document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                                    
                                    rows = 1;
                                    table = new PdfPTable(columnWidths);
                                    table.setWidthPercentage(100);
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    table.getDefaultCell().setUseAscender(true);
                                    table.getDefaultCell().setUseDescender(true);
                                        table.addCell(" ");
                                        table.addCell(gplHeader);
                                table.addCell(percentHeader);
                                table.addCell(quantityHeader);

                                    table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                                }
                            }
                        }
                    }
                    else if(!(dyeingProcessList.get(x).getdyeingProcessOrder().matches("[0-9]+"))){
                        String dyeingSubProcessLetter = dyeingProcessList.get(x).getdyeingProcessOrder().replaceAll("[^A-Za-z]+", "");

                        dyeingCell = new PdfPCell(new Phrase("    " + dyeingSubProcessLetter.toUpperCase() + ". " + dyeingProcessList.get(x).getDyeingProcessName(), f));
                        dyeingCell.setColspan(4);
                        dyeingCell.setBorder(Rectangle.BOTTOM);
                        table.addCell(dyeingCell);
                        rows++;
                        if(rows >= rowLimit)
                        {
                            rowLimit = rowLimit2;
                            document.add(table);
                            document.newPage();
                            document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                            
                            rows = 1;
                            table = new PdfPTable(columnWidths);
                            table.setWidthPercentage(100);
                            table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                            table.getDefaultCell().setUseAscender(true);
                            table.getDefaultCell().setUseDescender(true);
                                table.addCell(" ");
                                table.addCell(gplHeader);
                                table.addCell(percentHeader);
                                table.addCell(quantityHeader);

                            table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                        }
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
                            table.addCell(new Phrase("        " + dyeingChemicalList.get(i).getOrder() + ". " + chemHandler.GetChemicalNameFromChemicalID(dyeingChemicalList.get(i).getChemicalId()), f));
                            if("GPL".equals(dyeingChemicalList.get(i).getType().toUpperCase())){
                                PdfPCell gplCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        Float gplFloat = dyeingChemicalList.get(i).getValue();
                                        Double gplDouble = Double.parseDouble(gplFloat.toString());
                                        p = new Paragraph(df.format(gplDouble),f );
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        gplCell.setBorder(Rectangle.BOTTOM);
                                        gplCell.addElement(p);
                                        table.addCell(gplCell);
                                table.addCell(" ");
                            }
                            else
                            {
                                table.addCell(" ");
                                PdfPCell gplCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        Float gplFloat = dyeingChemicalList.get(i).getValue();
                                        Double gplDouble = Double.parseDouble(gplFloat.toString());
                                        p = new Paragraph(df.format(gplDouble),f );
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        gplCell.setBorder(Rectangle.BOTTOM);
                                        gplCell.addElement(p);
                                        table.addCell(gplCell);
                            }

                            if(dyeingChemicalList.get(i).getType()== "%")
                            {
                                Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                                     Double quantity = Double.parseDouble(fquantity.toString());
                                //Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                                //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                                //Double quantity = Double.parseDouble(fQuantity.toString());
                                if(quantity.toString().contains(".0") == true)
                                {
                                    PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity)  + " " + dyeingChemicalList.get(i).getState(), f);
                                       p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                    
                                }
                                else
                                {
                                    PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,###.00");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                }
                            }
                            else
                            {
                                Float fquantity = computeHelper.ComputeDyeingQuantity(dyeingChemicalList.get(i), jobOrderDetails);
                                     Double quantity = Double.parseDouble(fquantity.toString());
                                //Float fQuantity = jobOrderDetails.getWeight() * dyeingChemicalList.get(i).getValue();
                                //Double quantity = Double.parseDouble(fQuantity.toString());
                                //Double quantity = Double.parseDouble(volume) * dyeingChemicalList.get(i).getValue();
                                if(quantity.toString().contains(".0") == true)
                                {
                                    PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                    
                                    
                                }
                                else
                                {
                                    PdfPCell dyeingQuantityCell = new PdfPCell();
                                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                                        df.setRoundingMode(RoundingMode.CEILING);
                                        p = new Paragraph(df.format(quantity) + " " + dyeingChemicalList.get(i).getState(), f);
                                        p.setLeading(8f, 0f);
                                        p.setAlignment(Element.ALIGN_RIGHT);
                                        dyeingQuantityCell.setBorder(Rectangle.BOTTOM);
                                        dyeingQuantityCell.addElement(p);
                                        table.addCell(dyeingQuantityCell);
                                }
                            }
                            
                            if(i == dyeingChemicalList.size() - 1)
                            {
                                if(rows >= rowLimit)
                                {
                                    rowLimit = rowLimit2;
                                    document.add(table);
                                    document.newPage();
                                    document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                                    
                                    rows = 1;
                                    table = new PdfPTable(columnWidths);
                                    table.setWidthPercentage(100);
                                    table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                    table.getDefaultCell().setUseAscender(true);
                                    table.getDefaultCell().setUseDescender(true);
                                        table.addCell(" ");
                                        table.addCell(gplHeader);
                                table.addCell(percentHeader);
                                table.addCell(quantityHeader);

                                    table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                                }
                            }
                            
                            rows++;
                            if(rows >= rowLimit)
                            {
                                rowLimit = rowLimit2;
                                document.add(table);
                                document.newPage();
                                document = AddSecondPageHeader(document, machineDetails, designDetails, customerDetails, chemicalDetails, jobOrderDetails, dyeingProgramDetails, volume);
                                
                                rows = 1;
                                table = new PdfPTable(columnWidths);
                                table.setWidthPercentage(100);
                                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                                table.getDefaultCell().setUseAscender(true);
                                table.getDefaultCell().setUseDescender(true);
                                    table.addCell(" ");
                                    table.addCell(gplHeader);
                                table.addCell(percentHeader);
                                table.addCell(quantityHeader);

                                table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
                                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                            }
                        }
                    }
            }
        document.add(table);
        return document;
    }
    
    public Document AddSecondPageHeaderWithSig(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
    {
        DyeingProgramNameHandler dyeingProgramNameHandler = new DyeingProgramNameHandler();
        
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
        PreferenceHandler prefHandler = new PreferenceHandler();
//        Paragraph companyHeader = new Paragraph(prefHandler.getCompanyPreference(), companyHeaderFont);
//        companyHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(companyHeader);
        Font checkedByFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("Dyeing Control Slip", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(controlSlipHeader);
                
        float[] columnWidths = {2, 5, 2};
        PdfPTable tableHeader = new PdfPTable(columnWidths);
        tableHeader.setWidthPercentage(100);
        tableHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tableHeader.getDefaultCell().setUseAscender(true);
        tableHeader.getDefaultCell().setUseDescender(true);
        Phrase fillerPhrase = new Phrase();
        fillerPhrase.add(" ");
        PdfPCell fillerTableHeaderCell = new PdfPCell(fillerPhrase);
        
        Phrase companyHeaderPhrase = new Phrase(prefHandler.getCompanyPreference().toUpperCase(), companyHeaderFont);
        PdfPCell companyHeaderTableHeaderCell = new PdfPCell(companyHeaderPhrase);
            companyHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            companyHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
        Phrase controlSlipHeaderPhrase = new Phrase("DYEING CONTROL SLIP", controlSlipHeaderFont);
        PdfPCell controlSlipHeaderTableHeaderCell = new PdfPCell(controlSlipHeaderPhrase);
        controlSlipHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            controlSlipHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
        Phrase preparedByPhrase = new Phrase("Prepared By", checkedByFont);
        PdfPCell preparedByHeaderTableHeaderCell = new PdfPCell(preparedByPhrase);
        preparedByHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        preparedByHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Phrase checkedByPhrase = new Phrase("Checked By", checkedByFont);
        PdfPCell checkedByHeaderTableHeaderCell = new PdfPCell(checkedByPhrase);
        checkedByHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        checkedByHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(dyeingProcessHeader);
        
        Phrase dyeingProcessHeaderPhrase = new Phrase(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        PdfPCell dyeingProcessHeaderTableHeaderCell = new PdfPCell(dyeingProcessHeaderPhrase);
        dyeingProcessHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dyeingProcessHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Phrase fillerWithBorder = new Phrase();
        fillerWithBorder.add(" ");
        PdfPCell fillerWithBorderTableHeaderCell = new PdfPCell(fillerWithBorder);
        fillerWithBorderTableHeaderCell.setBorder(Rectangle.BOTTOM);
        
        fillerTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        companyHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        controlSlipHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        preparedByHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        dyeingProcessHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        checkedByHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(companyHeaderTableHeaderCell);
        tableHeader.addCell(fillerWithBorderTableHeaderCell);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(controlSlipHeaderTableHeaderCell);
        tableHeader.addCell(preparedByHeaderTableHeaderCell);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(fillerWithBorderTableHeaderCell);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(dyeingProcessHeaderTableHeaderCell);
        tableHeader.addCell(checkedByHeaderTableHeaderCell);
        
        document.add(tableHeader);
        
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase filler = new Phrase();
        filler.add(" ");
//        PdfPCell pCell1 = new PdfPCell(filler);
//        table.addCell(filler);
//        document.add(table);
        
        
        
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
        
        Phrase p4 = new Phrase("Weight: " + jobOrderDetails.getDyeingWeight(), f);
        //p4.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("DesignColor: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase("Vol. of Water: " + jobOrderDetails.getDyeingVolumeH20(), f);
        //p8.add("Vol. of Water: " + volume, f);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_____________ Drugman:_____________ Operator:_____________ Date:_____________", f);
        document.add(paragraph);
        if(jobOrderDetails.getRollLoad().isEmpty() == false)
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls: " + jobOrderDetails.getRollLoad(), f);
            document.add(rollLoad);
        }
        else
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls:_________", f);
            document.add(rollLoad);
        }
        document.add(new Paragraph(" "));
        //document.add(Chunk.NEWLINE);
        
        return document;
    }
    
    public Document AddSecondPageHeader(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrder jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume) throws IOException, DocumentException
    {
        DyeingProgramNameHandler dyeingProgramNameHandler = new DyeingProgramNameHandler();
        
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
        PreferenceHandler prefHandler = new PreferenceHandler();
//        Paragraph companyHeader = new Paragraph(prefHandler.getCompanyPreference(), companyHeaderFont);
//        companyHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(companyHeader);
        Font checkedByFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("Dyeing Control Slip", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(controlSlipHeader);
                
        float[] columnWidths = {2, 5, 2};
        PdfPTable tableHeader = new PdfPTable(columnWidths);
        tableHeader.setWidthPercentage(100);
        tableHeader.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        tableHeader.getDefaultCell().setUseAscender(true);
        tableHeader.getDefaultCell().setUseDescender(true);
        Phrase fillerPhrase = new Phrase();
        fillerPhrase.add(" ");
        PdfPCell fillerTableHeaderCell = new PdfPCell(fillerPhrase);
        
        Phrase companyHeaderPhrase = new Phrase(prefHandler.getCompanyPreference().toUpperCase(), companyHeaderFont);
        PdfPCell companyHeaderTableHeaderCell = new PdfPCell(companyHeaderPhrase);
            companyHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            companyHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
        Phrase controlSlipHeaderPhrase = new Phrase("DYEING CONTROL SLIP", controlSlipHeaderFont);
        PdfPCell controlSlipHeaderTableHeaderCell = new PdfPCell(controlSlipHeaderPhrase);
        controlSlipHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            controlSlipHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
        Phrase preparedByPhrase = new Phrase("Prepared By", checkedByFont);
        PdfPCell preparedByHeaderTableHeaderCell = new PdfPCell(preparedByPhrase);
        preparedByHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        preparedByHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Phrase checkedByPhrase = new Phrase("Checked By", checkedByFont);
        PdfPCell checkedByHeaderTableHeaderCell = new PdfPCell(checkedByPhrase);
        checkedByHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        checkedByHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
        Font dyeingProcessFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        //document.add(dyeingProcessHeader);
        
        Phrase dyeingProcessHeaderPhrase = new Phrase(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        PdfPCell dyeingProcessHeaderTableHeaderCell = new PdfPCell(dyeingProcessHeaderPhrase);
        dyeingProcessHeaderTableHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dyeingProcessHeaderTableHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Phrase fillerWithBorder = new Phrase();
        fillerWithBorder.add(" ");
        PdfPCell fillerWithBorderTableHeaderCell = new PdfPCell(fillerWithBorder);
        fillerWithBorderTableHeaderCell.setBorder(Rectangle.BOTTOM);
        
        fillerTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        companyHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        controlSlipHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        preparedByHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        dyeingProcessHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        checkedByHeaderTableHeaderCell.setBorder(PdfPCell.NO_BORDER);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(companyHeaderTableHeaderCell);
        tableHeader.addCell(fillerTableHeaderCell);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(controlSlipHeaderTableHeaderCell);
        tableHeader.addCell(fillerTableHeaderCell);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(fillerTableHeaderCell);
        
        tableHeader.addCell(fillerTableHeaderCell);
        tableHeader.addCell(dyeingProcessHeaderTableHeaderCell);
        tableHeader.addCell(fillerTableHeaderCell);
        
        document.add(tableHeader);
        
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase filler = new Phrase();
        filler.add(" ");
//        PdfPCell pCell1 = new PdfPCell(filler);
//        table.addCell(filler);
//        document.add(table);
        
        
        
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
        
        Phrase p4 = new Phrase("Weight: " + jobOrderDetails.getDyeingWeight(), f);
        //p4.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("DesignColor: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase("Vol. of Water: " + jobOrderDetails.getDyeingVolumeH20(), f);
        //p8.add("Vol. of Water: " + volume, f);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        document.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_____________ Drugman:_____________ Operator:_____________ Date:_____________", f);
        document.add(paragraph);
        if(jobOrderDetails.getRollLoad().isEmpty() == false)
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls: " + jobOrderDetails.getRollLoad(), f);
            document.add(rollLoad);
        }
        else
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls:_________", f);
            document.add(rollLoad);
        }
        document.add(new Paragraph(" "));
        //document.add(Chunk.NEWLINE);
        
        return document;
    }
    
    public Document AddThirdPage(Document document, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrderExtended jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume, ResinJob resin) throws IOException, DocumentException 
    {
        ResinProgramHandler resinProgramNameHandler = new ResinProgramHandler();
        DyeingProgramNameHandler dyeingProgramNameHandler = new DyeingProgramNameHandler();
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.NORMAL);
        PreferenceHandler prefHandler = new PreferenceHandler();
        Paragraph companyHeader = new Paragraph(prefHandler.getCompanyPreference().toUpperCase(), companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        Chapter chapter = new Chapter(companyHeader, 1);
        chapter.setNumberDepth(0);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("RESIN CONTROL SLIP", controlSlipHeaderFont);
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
        Paragraph dyeingProcessHeader = new Paragraph(dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID()), dyeingProcessFont);
        dyeingProcessHeader.setAlignment(Element.ALIGN_CENTER);
        chapter.add(dyeingProcessHeader);
        
        Paragraph resinProgramHeader = new Paragraph(resinProgramNameHandler.GetResinProgramNameFromResinProgramID(resin.getResinProgramID()), dyeingProcessFont);
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
        
        MachineHandler machineHandler = new MachineHandler();
        Machine resinMachine = machineHandler.GetMachineDetailsById(resin.getResinMachineID());
        
        Phrase p6 = new Phrase("Machine: " + resinMachine.getMachineName(), f);
        //p6.add("Machine: " + machineDetails.getMachineName(), f);
        pCell = new PdfPCell(p6);
        table.addCell(p6);
        
        Phrase p5 = new Phrase("Design: " + designDetails.getDesignName(), f);
        //p5.add("Design: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        table.addCell(p5);
        
        Phrase p4 = new Phrase("Weight: " + resin.getResinWeight(), f);
        //p4.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p4);
        table.addCell(p4);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("DesignColor: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase("Vol. of Water: " + resin.getResinVolH2O(), f);
        //p8.add("Vol. of Water: " + volume, f);
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        table.addCell(filler);
        table.addCell(filler);
        chapter.add(table);
        
        Paragraph paragraph = new Paragraph("Supervisor:_____________ Drugman:_____________ Operator:_____________ Date:_____________", f);
        chapter.add(paragraph);
        
        paragraph = new Paragraph("Loading Time:_______________________", f);
        
        chapter.add(paragraph);
        
        if(jobOrderDetails.getRollLoad().isEmpty() == false)
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls: " + jobOrderDetails.getRollLoad(), f);
            chapter.add(rollLoad);
        }
        else
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls:_________", f);
            chapter.add(rollLoad);
        }
        
        chapter.add(Chunk.NEWLINE);
        chapter.add(Chunk.NEWLINE);
        
        ResinProgramHandler resinProgramHandler = new ResinProgramHandler();
        ResinChemicalHandler resinChemicalHandler = new ResinChemicalHandler();
        ChemicalHandler chemicalHandler = new ChemicalHandler();
        JobHandler jHandler = new JobHandler();
        String resinProgramName = resinProgramHandler.GetResinProgramNameFromResinProgramID(resin.getResinProgramID());
        ArrayList<ResinChemical> resinChemicalList = resinChemicalHandler.GetResinChemicalsByResinProgramId(resin.getResinProgramID());
        
        paragraph = new Paragraph(resinProgramName, dyeingProcessFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        chapter.add(paragraph);
        chapter.add(Chunk.NEWLINE);
        
        float[] columnWidths = {5, 2, 2, 2};
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        PdfPCell cell = new PdfPCell();
        //table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
            table.addCell(new Phrase("Resin Chemical", f));
            PdfPCell gplHeader = new PdfPCell();
            Paragraph parag = new Paragraph("GPL",f );
            parag.setLeading(8f, 0f);
            parag.setAlignment(Element.ALIGN_RIGHT);
            gplHeader.setBorder(Rectangle.BOTTOM);
            gplHeader.addElement(parag);
            table.addCell(gplHeader);
            PdfPCell percentHeader = new PdfPCell();
            parag = new Paragraph("%",f );
            parag.setLeading(8f, 0f);
            parag.setAlignment(Element.ALIGN_RIGHT);
            percentHeader.setBorder(Rectangle.BOTTOM);
            percentHeader.addElement(parag);
            table.addCell(percentHeader);
            PdfPCell quantityHeader = new PdfPCell();
            parag = new Paragraph("Quantity",f );
            parag.setLeading(8f, 0f);
            parag.setAlignment(Element.ALIGN_RIGHT);
            quantityHeader.setBorder(Rectangle.BOTTOM);
            quantityHeader.addElement(parag);
            table.addCell(quantityHeader);

            
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
                    PdfPCell gplCell = new PdfPCell();
                    DecimalFormat df = new DecimalFormat("#,##0.00000");
                    df.setRoundingMode(RoundingMode.CEILING);
                    Float gplFloat = resinChemicalList.get(x).getGPLValue();
                    Double gplDouble = Double.parseDouble(gplFloat.toString());
                    parag = new Paragraph(df.format(gplDouble),f );
                    parag.setLeading(8f, 0f);
                    parag.setAlignment(Element.ALIGN_RIGHT);
                    gplCell.setBorder(Rectangle.BOTTOM);
                    gplCell.addElement(parag);
                    table.addCell(gplCell);
                    table.addCell(" ");
                }
                else
                {
                    table.addCell(" ");
                    PdfPCell gplCell = new PdfPCell();
                    DecimalFormat df = new DecimalFormat("#,##0.00000");
                    df.setRoundingMode(RoundingMode.CEILING);
                    Float gplFloat = resinChemicalList.get(x).getGPLValue();
                    Double gplDouble = Double.parseDouble(gplFloat.toString());
                    parag = new Paragraph(df.format(gplDouble),f );
                    parag.setLeading(8f, 0f);
                    parag.setAlignment(Element.ALIGN_RIGHT);
                    gplCell.setBorder(Rectangle.BOTTOM);
                    gplCell.addElement(parag);
                    table.addCell(gplCell);
                }
                //table.addCell(String.valueOf(jobOrderDetails.getVolumeH20() * resinChemicalList.get(x).getGPLValue()));
                if(resinChemicalList.get(x).getType()== "%")
                {
                    Float fquantity = computeHelper.ComputeResinQuantity(resinChemicalList.get(x), resin);
                    Double quantity = Double.parseDouble(fquantity.toString());
                    //Double quantity = Double.parseDouble(volume) * resinChemicalList.get(x).getGPLValue();
                   if(quantity.toString().contains(".0"))
                    {
                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                        df.setRoundingMode(RoundingMode.CEILING);
                        parag = new Paragraph(df.format(quantity) + " " + resinChemicalList.get(x).getState(), f);
                        parag.setLeading(8f, 0f);
                        parag.setAlignment(Element.ALIGN_RIGHT);
                        PdfPCell resinQuantityCell = new PdfPCell();
                        resinQuantityCell.setBorder(Rectangle.BOTTOM);
                        resinQuantityCell.addElement(parag);
                        table.addCell(resinQuantityCell);

                    }
                    else
                    {
                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                        df.setRoundingMode(RoundingMode.CEILING);
                        parag = new Paragraph(df.format(quantity) + " " + resinChemicalList.get(x).getState(), f);
                        parag.setLeading(8f, 0f);
                        parag.setAlignment(Element.ALIGN_RIGHT);
                        PdfPCell resinQuantityCell = new PdfPCell();
                        resinQuantityCell.setBorder(Rectangle.BOTTOM);
                        resinQuantityCell.addElement(parag);
                        table.addCell(resinQuantityCell);
                    }
                               
                }
                else
                {
                    //Double quantity = Double.parseDouble(volume) * resinChemicalList.get(x).getGPLValue();
                    Float fquantity = computeHelper.ComputeResinQuantity(resinChemicalList.get(x), resin);
                    Double quantity = Double.parseDouble(fquantity.toString());
                    if(quantity.toString().contains(".0"))
                    {
                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                            df.setRoundingMode(RoundingMode.CEILING);
                            
                    parag = new Paragraph(df.format(quantity) + " " + resinChemicalList.get(x).getState(), f);
                                        parag.setLeading(8f, 0f);
                                        parag.setAlignment(Element.ALIGN_RIGHT);
                                        PdfPCell resinQuantityCell = new PdfPCell();
                                        resinQuantityCell.setBorder(Rectangle.BOTTOM);
                                        resinQuantityCell.addElement(parag);
                                        table.addCell(resinQuantityCell);

                    }
                    else
                    {
                        DecimalFormat df = new DecimalFormat("#,##0.00000");
                            df.setRoundingMode(RoundingMode.CEILING);
                            
                    parag = new Paragraph(df.format(quantity) + " " + resinChemicalList.get(x).getState(), f);
                                        parag.setLeading(8f, 0f);
                                        parag.setAlignment(Element.ALIGN_RIGHT);
                                        PdfPCell resinQuantityCell = new PdfPCell();
                                        resinQuantityCell.setBorder(Rectangle.BOTTOM);
                                        resinQuantityCell.addElement(parag);
                                        table.addCell(resinQuantityCell);
                    }
                               
                }
                //table.addCell(" ");
                //table.addCell(" ");
                //table.addCell(" ");
        }
        
        chapter.add(table);
        document.add(chapter);
        return document;
    }
    
    public Document addCheckedByImage(Document document){
        try{
            String filename = "C:\\pdfCheckedByImg.jpg";
            Image image = Image.getInstance(filename);
            image.scaleToFit(83, 100);
            image.setAbsolutePosition(
            (PageSize.LETTER.getWidth() - (image.getScaledWidth() + 10)),
            (PageSize.LETTER.getHeight() - (image.getScaledWidth() + 30)));
            document.add(image);
        }
        catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }
    
    public Document addFirstPageSection(String dest, Machine machineDetails, Design designDetails, Customer customerDetails, DesignColor chemicalDetails, JobOrderExtended jobOrderDetails, DyeingProgram dyeingProgramDetails, String volume, Document document) throws IOException, DocumentException {
        Font companyHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.NORMAL);
        
        PreferenceHandler prefHandler = new PreferenceHandler();
        
        Paragraph companyHeader = new Paragraph(prefHandler.getCompanyPreference().toUpperCase(), companyHeaderFont);
        companyHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(companyHeader);
        
        Font controlSlipHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.NORMAL);
        Paragraph controlSlipHeader = new Paragraph("PUSHCART CONTROL SLIP", controlSlipHeaderFont);
        controlSlipHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(controlSlipHeader);
        
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        //Added Code
        FontSelector selector1 = new FontSelector();
        Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        f1.setColor(BaseColor.BLUE);
        selector1.addFont(f1);
        
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
        
        Phrase p6 = new Phrase("Weight: " + jobOrderDetails.getDyeingWeight(), f);
        //p6.add("Weight: " + jobOrderDetails.getWeight(), f);
        pCell = new PdfPCell(p6);
        
        table.addCell(p6);
        
        Phrase p5 = new Phrase("Design: " + designDetails.getDesignName(), f);
        //p5.add("Design: " + designDetails.getDesignName(), f);
        pCell = new PdfPCell(p5);
        pCell.setColspan(2);
        pCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(pCell);
        
        Phrase p7 = new Phrase("Color: " + chemicalDetails.getColorName(), f);
        //p7.add("DesignColor: " + chemicalDetails.getColorName(), f);
        pCell = new PdfPCell(p7);
        table.addCell(p7);
        
        Phrase p8 = new Phrase();
        p8.add(" ");
        pCell = new PdfPCell(p8);
        table.addCell(p8);
        
        document.add(table);
        
        table = new PdfPTable(2);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100);
        
        Phrase filler1 = new Phrase();
        filler1.add(" ");
        pCell = new PdfPCell(filler1);
        if(jobOrderDetails.getRollLoad().isEmpty() == false)
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls: " + jobOrderDetails.getRollLoad(), f);
            table.addCell(rollLoad);
        }
        else
        {
            Paragraph rollLoad = new Paragraph("Number of Rolls:_________", f);
            table.addCell(rollLoad);
        }
        table.addCell(filler1);
        
        DyeingProgramNameHandler dyeingProgramNameHandler = new DyeingProgramNameHandler();
        String dyeingProgName = dyeingProgramNameHandler.GetDyeingProgramNameFromID(dyeingProgramDetails.getDyeingProgramNameID());
        
        Phrase dyeingType = new Phrase("Dyeing Type: " + dyeingProgName, f);
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
        
        Phrase preparedBy = new Phrase("", f);
        //preparedBy.add("Prepared By:________________ ", f);
        pCell = new PdfPCell(preparedBy);
        table.addCell(preparedBy);
        
        Phrase resin = new Phrase("Resin:______________________", f);
        ArrayList<ResinJob> resinList = jobOrderDetails.getThisResinJob();
        if(resinList.size() > 0)
        {
            ResinProgramHandler resinProgramNameHandler = new ResinProgramHandler();
            String resinProgName = resinProgramNameHandler.GetResinProgramNameFromResinProgramID(resinList.get(0).getResinProgramID());
            
            if(resinList.size() == 2)
            {
                resinProgName = resinProgName + " / " + resinProgramNameHandler.GetResinProgramNameFromResinProgramID(resinList.get(1).getResinProgramID());
            }
            
            resin = new Phrase("Resin: "+resinProgName, f);
        }
        //resin.add("Resin:___________________", f);
        pCell = new PdfPCell(resin);
        pCell.setColspan(2);
        pCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(pCell);
        
        Phrase checkedBy = new Phrase("", f);
        //checkedBy.add("Checked By:________________ ", f);
        pCell = new PdfPCell(checkedBy);
        table.addCell(checkedBy);
        
        document.add(table);
        
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
        
        document.add(table);
        return document;
    }
    
    public void printPDF () throws DocumentException, IOException
    {        
        File myFile = new File("C:\\chapter_title.pdf");
        Desktop.getDesktop().open(myFile);
   }
}
