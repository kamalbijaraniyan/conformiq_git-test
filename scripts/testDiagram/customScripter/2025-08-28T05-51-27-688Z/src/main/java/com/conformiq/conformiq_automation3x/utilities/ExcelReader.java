
package com.conformiq.conformiq_automation3x.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader {

    /** Path to the spreadsheet. */ 
    private String path;
    /** Stream to read the spreadsheet. */ 
    private FileInputStream fis = null;
    /** Access to the spreadsheet. */ 
    private XSSFWorkbook workbook = null;
    /** Work sheet in the current file. */ 
    private XSSFSheet sheet = null;
    /** Row within the current sheet. */ 
    private XSSFRow row = null;
    /** Cell within the current sheet. */ 
    private XSSFCell cell = null;
    /** Stream to write the spreadsheet. */ 
    private FileOutputStream fileOut = null;

  
    public ExcelReader(final String path, final String sheetName) {
        if (path == null && sheetName == null) {
            return;
        }

        this.setPath(path);

        if (this.getPath() != null) {
            try {
                setFis(new FileInputStream(path));

                if (this.getFis() != null) {
                    XSSFWorkbook xssfWB = new XSSFWorkbook(getFis());

                    if (xssfWB != null) {
                        setWorkbook(xssfWB);

                        if (this.getWorkbook() != null) {
                            this.setSheet(getWorkbook().getSheet(sheetName));
                           
                        }
                    }
                }
            } catch (FileNotFoundException fe) {
                System.out.println("Exception: "+ fe.getMessage());
            } catch (IOException ie) {
            	System.out.println("Exception: "+ ie.getMessage());
            } catch (Exception e) {
            	System.out.println("Exception: "+ e.getMessage());
            } finally {
                if (getFis() != null) {
                    try {
                        getFis().close();
                    } catch (IOException e) {
                    	System.out.println("Exception: "+ e.getMessage());
                    }
                }
            }

        } else {
        	System.out.println("Unable to open spreadsheet as path is null");
        }
    }

    public String getCellValue(final int row, final int col) {
        Row rows = getSheet().getRow(row - 1);
        if (rows == null){
        	System.out.println("Attempt to read data from cell in undefined row " 
                    + "[" + row + "," + col + "] on sheet \""
                    + getSheet().getSheetName() + "\"");
            return "";
        } else {
            Cell cell;
            try {
                cell = rows.getCell(col);
            } catch (IllegalArgumentException ex) {
            	System.out.println("Attempt to read data from undefined cell "
                        + "[" + row + "," + col + "] on sheet \""
                        + getSheet().getSheetName() + "\"");
                throw ex;
            }
            if (cell == null) {
                // Treat cells that don't exist as empty because Excel generally only creates the
                // cells that have values or styles
                return "";
            } else if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                // return String.valueOf(cell.getNumericCellValue());
                return new java.text.DecimalFormat("0").format(cell.getNumericCellValue());
            } else {
                return cell.getStringCellValue();
            }
        }
    }

    

    public int getRowCount() {
        if (getSheet() != null){
            int rowCount = getSheet().getLastRowNum();
            return rowCount + 1;
        } else {
        	System.out.println("Sheet not found");
            return 0;
        }
    }

   
    public int getColumnCount() {
        if (getSheet() != null){
            Row rowNum = getSheet().getRow(0);
            int columnCount = rowNum.getLastCellNum();
            return columnCount;
        } else {
        	System.out.println("Sheet not found");
            return 0;
        }
    }

    
    
    /**
     * Getter.
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Setter.
     * @param path the path to set
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * Getter.
     * @return the fis
     */
    public FileInputStream getFis() {
        return fis;
    }

    /**
     * Setter.
     * @param fis the fis to set
     */
    public void setFis(final FileInputStream fis) {
        this.fis = fis;
    }

    /**
     * Getter.
     * @return the workbook
     */
    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * Setter.
     * @param workbook the workbook to set
     */
    public void setWorkbook(final XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    /**
     * Getter.
     * @return the sheet
     */
    public XSSFSheet getSheet() {
        return sheet;
    }

    /**
     * Setter.
     * @param sheet the sheet to set
     */
    public void setSheet(final XSSFSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * Getter.
     * @return the row
     */
    public XSSFRow getRow() {
        return row;
    }

    /**
     * Setter.
     * @param row the row to set
     */
    public void setRow(final XSSFRow row) {
        this.row = row;
    }

    /**
     * Getter.
     * @return the cell
     */
    public XSSFCell getCell() {
        return cell;
    }

    /**
     * Setter.
     * @param cell the cell to set
     */
    public void setCell(final XSSFCell cell) {
        this.cell = cell;
    }

    /**
     * Getter.
     * @return the fileOut
     */
    public FileOutputStream getFileOut() {
        return fileOut;
    }

    /**
     * Setter.
     * @param fileOut the fileOut to set
     */
    public void setFileOut(final FileOutputStream fileOut) {
        this.fileOut = fileOut;
    }

    
    
    public Map<String, String> getRowData(final int rownumber){
        String columnheader;
        String columnvalue;
        int colCount = this.getColumnCount();

        HashMap<String, String> map = new HashMap<String, String>();

        for (int colNum = 0; colNum < colCount; colNum++) {
            columnheader = this.getCellValue(1, colNum);
            columnvalue = this.getCellValue(rownumber, colNum);
            map.put(columnheader, columnvalue);
        }

        return map;
    }
}
