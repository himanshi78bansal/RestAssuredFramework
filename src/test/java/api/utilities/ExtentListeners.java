package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentListeners implements ITestListener {

    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    public void configureReport() {

        String timeStamp =
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        String reportName = "RestAssuredFramework-" + timeStamp + ".html";

        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + reportName);

        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        reports.setSystemInfo("Machine", "Tester");
        reports.setSystemInfo("OS", "Ubuntu");
        reports.setSystemInfo("User Name", "Himanshi Bansal");

        htmlReporter.config().setDocumentTitle("Extent Report");
        htmlReporter.config().setReportName("Rest Assured Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onStart(ITestContext context) {
        configureReport();
        System.out.println("Execution Started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Execution Finished");
        reports.flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test Started : " + result.getName());
        test = reports.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test Passed : " + result.getName());

        test.log(Status.PASS, MarkupHelper.createLabel("Test Passed : " + result.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test Failed : " + result.getName());

        test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed : " + result.getName(), ExtentColor.RED));

        test.fail(result.getThrowable());

        String screenShotPath = System.getProperty("user.dir") + "/ScreenShots/" + result.getName() + ".png";
        File screenShotFile = new File(screenShotPath);
        if (screenShotFile.exists()) {
            try {
                test.addScreenCaptureFromPath(screenShotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("Test Skipped : " + result.getName());

        test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped : " + result.getName(), ExtentColor.YELLOW));
    }
}