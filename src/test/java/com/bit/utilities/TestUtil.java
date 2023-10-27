package com.bit.utilities;

import com.bit.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class TestUtil extends BaseTest {

    public static String screenshotPath, screenshotName;
    public static String userDir = System.getProperty("user.dir");

    public static void captureScreenshot()
    {
        Date d = new Date();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshotName = "Img_"+ new SimpleDateFormat("yyyyMMddHHmmss'.jpg'", Locale.getDefault()).format(new Date());
        screenshotName = d.toString().replace(":","_").replace(" ","_")+".jpg";
        System.out.println("screenshotName: "+screenshotName);  //For debugging
        try {
            FileUtils.copyFile(scrFile, new File( userDir + "/target/screenshots/"+screenshotName ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void captureScreenshot(String path)
    {
//        Date d = new Date();
//        screenshotName = d.toString().replace(":","_").replace(" ","_")+".jpg";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshotName = "Img_"+new SimpleDateFormat("yyyyMMddHHmm'.jpg'").format(new Date());
//        screenshotName = "Img_"+ new SimpleDateFormat("yyyyMMddHHmmss'.jpg'", Locale.getDefault()).format(new Date());

//        path =
        //log.info("screenshotName: "+screenshotName);  //For debugging
        try {
            FileUtils.copyFile(scrFile, new File( path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isTestRunnable(String testName, ExcelReader excel) {
        String sheetName = "TestSuite";
        String testCase = null, runMode = null;
        int rows = excel.getRowCount(sheetName);
        for (int rNum = 2; rNum <= rows; rNum++) {

            testCase = excel.getCellData(sheetName, "TC_ID", rNum);
            if (testCase.equalsIgnoreCase(testName)) {
                runMode = excel.getCellData(sheetName, "RunMode", rNum);

                if (runMode.equalsIgnoreCase("Y"))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

//
//    public static Platform getPlatformDetails()
//    {
//        Platform platform = null;
//
//        if (platform == null) {
//            String operSys = System.getProperty("os.name").toLowerCase();
//            if (operSys.contains("win")) {
//                platform = Platform.WINDOWS;
//            } else if (operSys.contains("nix") || operSys.contains("nux")
//                    || operSys.contains("aix")) {
//                platform = Platform.LINUX;
//            } else if (operSys.contains("mac")) {
//                platform = Platform.MAC;
//            }
//        }
//        return platform;
//    }


}
