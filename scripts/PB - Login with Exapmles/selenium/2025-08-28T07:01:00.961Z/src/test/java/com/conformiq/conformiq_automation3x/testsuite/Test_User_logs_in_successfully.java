package com.conformiq.conformiq_automation3x.testsuite;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.conformiq.conformiq_automation3x.utilities.Configurations;
import com.conformiq.conformiq_automation3x.utilities.TestReport;
import com.conformiq.conformiq_automation3x.utilities.PageObjectBase;
import com.conformiq.conformiq_automation3x.utilities.DataUtil;
import com.conformiq.conformiq_automation3x.pages.Action_1_Page;

public class Test_User_logs_in_successfully extends PageObjectBase {

private TestReport testReport= new TestReport();
private StringBuilder overallTestData= new StringBuilder();

	@DataProvider(name="TestData")
	public Object[][] getData(){
		return DataUtil.getDataFromSpreadSheet("TC_8");
	}
	@Test(dataProvider="TestData")
	public void test(String UserName, String Password) throws Exception
	{
		Action_1_Page action_1 = PageFactory.initElements(getDriver(), Action_1_Page.class);

		testReport.createTesthtmlHeader(overallTestData);
		testReport.createHead(overallTestData);
		testReport.putLogo(overallTestData);
		float ne = (float) 0.0;
		testReport.generateGeneralInfo(overallTestData, "Test_User_logs_in_successfully", "TC_Test_User_logs_in_successfully", "",ne);
		testReport.createStepHeader();

		//External Circumstances

		// Navigate to new URL: NO_URL
		action_1.navigateToPage("NO_URL");

		Reporter.log("Step - 1 - input");
		testReport.fillTableStep("Step 1", "input");

		action_1.enter___fill_text_area__associated_data_username_1(UserName);
		this.getScreenshot(Configurations.screenshotLocation, "Test_User_logs_in_successfully", "Step_1");

		Reporter.log("Step - 2 - input");
		testReport.fillTableStep("Step 2", "input");

		action_1.enter___fill_text_area__associated_data_password_2(Password);
		this.getScreenshot(Configurations.screenshotLocation, "Test_User_logs_in_successfully", "Step_2");

	}
}
