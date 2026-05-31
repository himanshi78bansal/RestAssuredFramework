package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider (name = "UserData")
    public String [][] userDataProvider(){
        String fName = System.getProperty("user.dir") + "/TestData/userData.xlsx";

        System.out.println("filename:" + fName);
        int ttlRowCount = XLUtilities.getRowCount(fName, "userData");
        int ttlColCount = XLUtilities.getColCount(fName, "userData");

        String[][] userData = new String[ttlRowCount-1][ttlColCount];

        for (int row = 1; row<ttlRowCount; row++){
            for (int col = 0; col<ttlColCount; col++){
                userData[row-1][col] = XLUtilities.getCellValue(fName, "userData", row, col);
            }
        }

        return  userData;
    }
    @DataProvider (name = "UserName")
    public String [] userNamesDataProvider(){
        String fName = System.getProperty("user.dir") + "/TestData/userData.xlsx";

        int ttlRowCount = XLUtilities.getRowCount(fName, "userData");

        String[] userNames = new String[ttlRowCount-1];

        for (int row = 1; row<ttlRowCount; row++){
                userNames[row-1] = XLUtilities.getCellValue(fName, "userData", row, 1);
        }

        return  userNames;
    }
}
