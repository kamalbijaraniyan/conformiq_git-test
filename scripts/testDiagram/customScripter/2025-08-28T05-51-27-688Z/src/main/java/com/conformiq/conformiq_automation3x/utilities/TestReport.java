
package com.conformiq.conformiq_automation3x.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestReport {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	private StringBuilder  testData = new StringBuilder();

	public static void createTesthtmlHeader(StringBuilder overallTestData){
		overallTestData.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n");
		overallTestData.append("<HTML>\n");

	}

	public void appendtestData(StringBuilder overallTestData){
		overallTestData.append(testData.toString());
	}

	public void closeTestHTML(StringBuilder overallTestData){
		overallTestData.append("</html>\n");
	}

	public void generateGeneralInfo(StringBuilder overallTestData, String sTestCaseName, String sTestCaseID, String result, float exeTime) {
		overallTestData.append("<DIV ID=\"projectName_conformiqversion\">\n");
		overallTestData.append("<TABLE class='projectname' WIDTH='100%'>\n");
		overallTestData.append("<TR>\n");
		overallTestData.append("<TD COLSPAN=6 CLASS=projectheader>General Information</TD>\n");
		overallTestData.append("<TR>\n");
		overallTestData.append("<TD CLASS=projectfieldheader>CTA Project Name : </TD>\n");
		overallTestData.append("<TD COLSPAN=3 CLASS=fieldvalue>"+Configurations.projectName+"</TD>\n");
		overallTestData.append("   <TD CLASS=projectfieldheader>Execution Date :</TD>\n");
		overallTestData.append(" <TD CLASS=fieldvalue>"+dateFormat.format(cal.getTime())+"</TD>\n");
		overallTestData.append("</TR> <TR>\n");
		overallTestData.append("<TD CLASS=projectfieldheader>Test Name</TD>\n");
		overallTestData.append("<TD COLSPAN=3 CLASS=fieldvalue>"+sTestCaseName+"</TD>\n");
		overallTestData.append(" <TD CLASS=projectfieldheader>Test ID</TD>\n");
		overallTestData.append("   <TD COLSPAN=3 CLASS=fieldvalue>"+sTestCaseID+"</TD>\n");
		overallTestData.append("</TR>");
		overallTestData.append("<TR>");		 
		overallTestData.append("<TD CLASS=projectfieldheader>Test Verdict :</TD>");
		if(result.equals("PASS")){
			overallTestData.append("<TD COLSPAN=3 CLASS=fieldvalue><B><FONT COLOR=\"GREEN\">"+result+"</FONT></B></TD>");
		}else if(result.equals("FAIL")){
			overallTestData.append("<TD COLSPAN=3 CLASS=fieldvalue><B><FONT COLOR=\"RED\">"+result+"</FONT></B></TD>");

		}else{
			overallTestData.append("<TD COLSPAN=3 CLASS=fieldvalue><B><FONT COLOR=\"BLACK\">"+result+"</FONT></B></TD>");

		}
		overallTestData.append("<TD CLASS=projectfieldheader>Execution time :</TD>");
		overallTestData.append("<TD COLSPAN=3 CLASS=fieldvalue>"+ exeTime+" Seconds"+"</TD>");
		overallTestData.append("</TR>");
		overallTestData.append("</TABLE></DIV>\n");
		overallTestData.append("<BR>");

	}

	public void writeTestReporthtml(StringBuilder overallTestData, String testCaseid) throws IOException {
	    File outFile = new File(Configurations.htmlReportLocation+testCaseid+"//"); 

		if(!outFile.exists()){
			outFile.mkdirs();
			Writer testFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Configurations.htmlReportLocation+testCaseid+"//" +testCaseid+".html"), "UTF-8"));
			try {
				testFile.write(overallTestData.toString());
			} finally {
				testFile.close();
			}
			overallTestData.setLength(0);
			testData.setLength(0);

		}else{
			Writer testFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Configurations.htmlReportLocation+ testCaseid+".html"), "UTF-8"));
			try {
				testFile.write(overallTestData.toString());
			} finally {
				testFile.close();
			}
			overallTestData.setLength(0);
			testData.setLength(0);

		}

	}


	public void createHead(StringBuilder overallTestData){
		overallTestData.append("<HEAD>\n");
		overallTestData.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">\n");
		overallTestData.append("<title>Conformiq Test Automation Execution Report</title>\n");
		overallTestData.append("<script language=\"javascript\" type=\"text/javascript\" src=\"TestHtmlFiles/conformiq.js\"></script>\n");
		overallTestData.append("<LINK href=\"TestHtmlFiles/conformiq.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		overallTestData.append("<HEAD>\n");
		overallTestData.append("<body onload=\"javascript:OnLoad();\">\n");

	}

	public void putLogo(StringBuilder overallTestData){
		overallTestData.append("<TABLE border=\"3\" BORDERCOLOR=\"BLACK\" WIDTH='100%'>");
		overallTestData.append("<TR>");
		overallTestData.append("<TD CLASS='noborder' align=\"left\"><IMG SRC='TestHtmlFiles/conformiqlogo.png' alt= 'Conformiq Logo' /></TD>");
		overallTestData.append("</TABLE>");
		overallTestData.append("</TR>");

	}

	public void createStepHeader(){
		testData.append("<TABLE class='teststeps' WIDTH='100%'>\n");
		testData.append("<TR>\n");
		testData.append("<TD COLSPAN=4 CLASS=projectheader>Test Step Execution Summary</TD></TR>\n");

	}

	public void closeStepTable() {
		testData.append("</TABLE>\n");

	}
	
	public void fillTableStep(String stepNumber, String stepName){
		testData.append("<TR>\n");
		testData.append("<TD CLASS=fieldheader>"+stepNumber+"</TD>");
		testData.append("<TD CLASS=fieldvalue>"+stepName+"</TD>");
		testData.append("</TR>\n");
	}
	public void beginStep(){
		testData.append("<TR>\n");
	}
	public void endStep(){
		testData.append("</TR>\n");
	}

	public void fillStepHeader(String stepInfo){
		testData.append("<TD CLASS=fieldheader>"+stepInfo+"</TD>");
	}

	public void fillStepValue(String stepInfo){
		testData.append("<TD CLASS=fieldvalue>"+stepInfo+"</TD>");
	}

	public void fillStatus(String strin, String sScreenShot, String sParamName, String sTestCaseID){
		if(sScreenShot.equals("Y")){
			testData.append("<td>"+"<a href=\"Screenshots/"+sTestCaseID+"_"+sParamName+".png\" style=\"color: GREEN\">"+strin+"</a>"+"</td>\n");
		}else{
			testData.append(" <TD CLASS=fieldvalue> <B><FONT COLOR=\"GREEN\">"+strin+"</TD>\n");


		}
	}
	
	public void fillFailStatus(String strin, String sScreenShot, String sParamName, String sTestCaseID){
		if(sScreenShot.equals("Y")){
			testData.append("<td>"+"<a href=\"Screenshots/"+sTestCaseID+"_"+sParamName+".png\" style=\"color: RED\">"+strin+"</a>"+"</td>\n");
		}else{
			testData.append(" <TD CLASS=fieldvalue> <B><FONT COLOR=\"RED\">"+strin+"</TD>\n");


		}
	}


	public void fillskippedStatus(String strin, String sTestCaseID, String sParamName, String sScreenShot){
		if(sScreenShot.equals("Y")){
			testData.append("<td>"+"<a href=\"Screenshots/"+sTestCaseID+"_"+sParamName+".png\" style=\"color: BLACK\">"+""+"</a>"+"</td>\n");
			testData.append("<td>"+"<a href=\"Screenshots/"+sTestCaseID+"_"+sParamName+".png\" style=\"color: BLACK\">"+strin+"</a>"+"</td>\n");
		}else{
			testData.append("<td>"+"<a href=\"Screenshots/"+sTestCaseID+"_"+sParamName+".png\" style=\"color: BLACK\">"+""+"</a>"+"</td>\n");
			testData.append(" <TD CLASS=fieldvalue> <B><FONT COLOR=\"BLACK\">"+strin+"</TD>\n");

		}
	}
	public void filltableDetails(StringBuilder overallTestData){
		/*int iTotalTestCases = ExcelUtils.getTestRowCount(Constants.Sheet_TestCases);
		overallTestData.append("<TABLE class='teststeps' WIDTH='100%'>\n");
		overallTestData.append("<TR><TD>\n");
		overallTestData.append("[<A HREF=\"Conformiq Test Automation Execution Report.html\" onMouseOver=\"window.status='Summary page';return true;\">summary</A>] Test cases:  \n");
		for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
			try {
				String sTestCaseID1 = ExcelUtils.getTestCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
				fillTableDetails(overallTestData,sTestCaseID1);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
*/		testData .append("</TD></TR></TABLE>\n");
		testData.append("<DIV align=\"right\">CopyRight &copy; 2015 Conformiq Inc<DIV>\n");
	}


	public void fillTableDetails(StringBuilder overallTestData, String sTestCaseID1){
		overallTestData.append("[<A HREF="+sTestCaseID1+".html>"+sTestCaseID1+"</A>]\n");

	}

}
