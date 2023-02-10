package com.f1soft.campaign.web.fileutil;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <krishna.pandey@f1soft.com>
 */

@Slf4j
public class ExcelFileReader {

    private static final Logger logger = LoggerFactory.getLogger(ExcelFileReader.class);
    private int rowNum = 0;
    private OPCPackage opcPkg;
    private ReadOnlySharedStringsTable stringsTable;
    private XMLStreamReader xmlReader;
    private XSSFReader xssfReader;
    private XMLInputFactory factory;
    private InputStream inputStream;

    public ExcelFileReader(String excelPath) throws Exception {
        opcPkg = OPCPackage.open(excelPath, PackageAccess.READ);
        this.stringsTable = new ReadOnlySharedStringsTable(opcPkg);

        xssfReader = new XSSFReader(opcPkg);
        factory = XMLInputFactory.newInstance();
        inputStream = xssfReader.getSheetsData().next();
        xmlReader = factory.createXMLStreamReader(inputStream);

        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement() && xmlReader.getLocalName().equals("sheetData")) {
                break;
            }
        }
    }

    public int rowNum() {
        return rowNum;
    }

    public List<String[]> readRows(int batchSize) throws Exception {
        String elementName = "row";
        List<String[]> dataRows = new ArrayList<>();
        if (batchSize > 0) {
            while (xmlReader.hasNext()) {
                xmlReader.next();
                if (xmlReader.isStartElement() && xmlReader.getLocalName().equals(elementName)) {
                    rowNum++;
                    dataRows.add(getDataRow());
                    if (dataRows.size() == batchSize) {
                        break;
                    }
                }
            }
        }
        return dataRows;
    }

    private String[] getDataRow() throws XMLStreamException {
        List<String> rowValues = new ArrayList<>();
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals("c")) {
                    CellReference cellReference = new CellReference(xmlReader.getAttributeValue(null, "r"));
                    // Fill in the possible blank cells!
                    while (rowValues.size() < cellReference.getCol()) {
                        rowValues.add("");
                    }
                    String cellType = xmlReader.getAttributeValue(null, "t");
                    rowValues.add(getCellValue(cellType));
                }
            } else if (xmlReader.isEndElement() && xmlReader.getLocalName().equals("row")) {
                break;
            }
        }
        return rowValues.toArray(new String[rowValues.size()]);
    }

    private String getCellValue(String cellType) throws XMLStreamException {
        String value = ""; // by default
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals("v")) {
                    if (cellType != null && cellType.equals("s")) {
                        int idx = Integer.parseInt(xmlReader.getElementText());
                        return new XSSFRichTextString(stringsTable.getEntryAt(idx)).toString();
                    } else {
                        return xmlReader.getElementText();
                    }
                }
            } else if (xmlReader.isEndElement() && xmlReader.getLocalName().equals("c")) {
                break;
            }
        }
        return value;
    }

    public List<String> getDataList(int rowSize, ExcelFileReader excelFileReader) {
        List<String> mobileNumbersList = new ArrayList<>();
        try {
            List<String[]> excelFileRows = excelFileReader.readRows(rowSize);
            for (String[] row : excelFileRows) {
                mobileNumbersList.addAll(Arrays.asList(row));
            }
        } catch (Exception ex) {
            logger.error("Exception : ", ex);
        }
        return mobileNumbersList;

    }

    public void finlize() throws Throwable {
        if (opcPkg != null) {
            opcPkg.close();
            xmlReader.close();
            inputStream.close();
        }
        super.finalize();
    }

    public static void main(String[] args) throws Throwable {
        try {
            List<String> numberList = new ArrayList<>();
            ExcelFileReader excelFileReader = new ExcelFileReader("E:/sample_10000_rows.xlsx");

            List<String[]> rows = excelFileReader.readRows(10001);
            int i = 0;
            for (String[] row : rows) {
                for (String innerRow : row) {
                    if (i < 10000) {
                        numberList.add(innerRow);
                        i++;
                    } else {
                        log.info("Count is greater than 10,000.");
                        log.info("size =   " + numberList.size());
                        return;
                    }
                }
            }
            excelFileReader.finlize();
        } catch (Exception ex) {
            logger.error("Exception : ", ex);
        }
    }
}
