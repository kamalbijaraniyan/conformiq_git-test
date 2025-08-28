
package com.conformiq.conformiq_automation3x.utilities;


public final class DataUtil {
    private DataUtil(){
        
    }
    public static Object[][] getDataFromSpreadSheet(final String workSheetName) {
        ExcelReader datatable = new ExcelReader(
                System.getProperty("user.dir") + "//src//test//resources//data.xlsx",
                workSheetName);

        int rowCount = datatable.getRowCount();
        int colCount = datatable.getColumnCount();
        Object[][] data = new Object[rowCount - 1][colCount];
        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            // loop all the available row values
            for (int colNum = 0; colNum < colCount; colNum++) {
                // while returning the data[][] you should not send the header
                // values
                data[rowNum - 2][colNum] = datatable.getCellValue(rowNum, colNum);
            }
        }

        return data;
    }

    

}
