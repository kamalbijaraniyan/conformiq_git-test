

package com.conformiq.conformiq_automation3x.utilities;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class OverviewReport {
	public static StringBuilder indexData = new StringBuilder();
	public static String sTestCaseID1,sTestCaseName1,sRunMode1,sTestResult;
	public static FileWriter indexFile;
	public static  BufferedWriter indexOut;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Calendar cal = Calendar.getInstance();

	public static void  createIndexFile(int iTotalTestCases) throws IOException {
		createindexhtmlHeader();
		createHead();
		putLogo();
		generateGeneralInfo(iTotalTestCases);
		makePieChart(iTotalTestCases);
		testExecSummaryHeader();
		fillTestDetails();
		closetestExecSummaryHeader();
		createProjectInfoDetails();
		closeHTML();
		writeindexhtml();
	}
	public static void createindexhtmlHeader(){
		indexData.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n");
		indexData.append("<HTML>\n");

	}

	public static void closeHTML(){
		indexData.append("</body>\n");
		indexData.append("</html>\n");
	}

	public static void closetestExecSummaryHeader(){
		indexData.append("</TABLE>\n");
		indexData.append("</DIV>\n");
	}
	public static void putLogo(){
		indexData.append("<TABLE border=\"3\" BORDERCOLOR=\"BLACK\" WIDTH='100%'>");
		indexData.append("<TR>");
		indexData.append("<TD CLASS='noborder' align=\"left\"><IMG SRC='TestHtmlFiles/conformiqlogo.png' alt= 'Conformiq Logo' /></TD>");
		indexData.append("</TABLE>");
		indexData.append("</TR>");

	}
	public static void makePieChart(int iTotalTestCases){
		indexData.append("<BR>");
		indexData.append("<DIV>");
		indexData.append("<head>");
		indexData.append("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\">\n");
		indexData.append("</script>\n");
		indexData.append("<script type=\"text/javascript\">\n");
		indexData.append("google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});\n");
		indexData.append("google.setOnLoadCallback(drawChart);\n");
		indexData.append("function drawChart() {\n");
		indexData.append("var data = google.visualization.arrayToDataTable([\n");
		indexData.append("['Status', 'Percentage'],");
		/*int totaltest = ((iTotalTestCases)-1);
		int ntPass = totaltest -(DriverScript_1.countTestNotExecuted +DriverScript_1.countTestFailed);
		indexData.append("['Passed',     "+ntPass+"],");
		indexData.append("['Failed',     "+DriverScript_1.countTestFailed+"],");
		indexData.append("['Not Executed',"+DriverScript_1.countTestNotExecuted+"],");
		*/indexData.append("]);\n");
		indexData.append("var options = {");
		indexData.append("title: 'Test Report',");
		indexData.append("is3D: true,");
		indexData.append("};");
		indexData.append("var chart = new \n");
		indexData.append("google.visualization.PieChart(document.getElementById('piechart_3d'));\n");
		//indexData.append("chart.draw(data, options);\n");
		indexData.append("chart.draw(data, {");
		//indexData.append("	  width: 400,");
		indexData.append("height: 240,");
		indexData.append("	  title: 'Test Report',");
		indexData.append("	  colors: ['GREEN', 'RED', 'BLACK'],");
		indexData.append("	  is3D: true");
		indexData.append("	});");
		indexData.append("}");
		indexData.append("</script>");
		indexData.append("</head>");
		indexData.append("<body>");
		indexData.append("<div id=\"piechart_3d\" style=\"background-color:green; color:black; padding:2px;\">");
		//indexData.append("<div id=\"piechart_3d\" style=\"width: 900px; height: 500px;\"></div>");
		indexData.append("</body>");
		indexData.append("</DIV>");
	}

	public static void createHead(){
		indexData.append("<HEAD>\n");
		indexData.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">\n");
		indexData.append("<title>Conformiq Test Automation Execution Report</title>\n");
		indexData.append("<script language=\"javascript\" type=\"text/javascript\" src=\"TestHtmlFiles/conformiq.js\"></script>\n");
		indexData.append("<LINK href=\"TestHtmlFiles/conformiq.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		indexData.append("<HEAD>\n");
		indexData.append("<body onload=\"javascript:OnLoad();\">\n");

	}
	private static void writeindexhtml() throws IOException {
		File outFile = new File(Configurations.htmlReportLocation);
		if(!outFile.exists()){
			outFile.mkdirs();
		}
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Configurations.htmlReportLocation+ "Conformiq Test Automation Execution Report.html"), "UTF-8"));
		try {
			out.write(indexData.toString());
		} finally {
			out.close();
		}

	}

	public static void generateGeneralInfo(int iTotalTestCases) {
		indexData.append("<DIV ID=\"projectName_conformiqversion\">\n");
		indexData.append("<TABLE class='projectname' WIDTH='100%'>\n");
		indexData.append("<TR>\n");
		indexData.append("<TD COLSPAN=6 CLASS=projectheader>General Information</TD>\n");
		indexData.append("<TR>\n");
		indexData.append("<TD CLASS=projectfieldheader>CTA Project Name : </TD>\n");
		indexData.append("<TD COLSPAN=3 CLASS=fieldvalue>"+"ProjectName"+"</TD>\n");
		indexData.append("<TD CLASS=projectfieldheader>Executed by :</TD>\n");
		indexData.append("<TD CLASS=fieldvalue>Conformiq Test Automation</TD>\n");
		indexData.append("</TR> <TR>\n");
		indexData.append("<TD CLASS=projectfieldheader>Total Tests Executed</TD>\n");
		int totaltest = ((iTotalTestCases)-1);
		indexData.append("<TD COLSPAN=3 CLASS=fieldvalue>"+totaltest+"</TD>\n");
		indexData.append("<TD CLASS=projectfieldheader>Test Execution Date :</TD>\n");
		indexData.append(" <TD CLASS=fieldvalue>"+dateFormat.format(cal.getTime())+"</TD>\n");
		indexData.append("  </TR> <TR>\n");
		//int ntPass = totaltest -(DriverScript_1.countTestNotExecuted +DriverScript_1.countTestFailed);
		indexData.append(" <TD CLASS=projectfieldheader>Total Tests Passed</TD>\n");
		//indexData.append("   <TD COLSPAN=3 CLASS=fieldvalue> <B><FONT COLOR=\"GREEN\">"+ntPass+"/"+totaltest+" ["+((ntPass * 100)/totaltest)+"%]"+"</TD>\n");
		indexData.append("   <TD CLASS=projectfieldheader>Total Tests Skipped</TD>\n");
		//indexData.append(" <TD CLASS=fieldvalue> <B><FONT COLOR=\"BLACK\">"+DriverScript_1.countTestNotExecuted+"/"+totaltest+" ["+((DriverScript_1.countTestNotExecuted * 100)/totaltest)+"%]"+"</TD>\n");
		indexData.append("  </TR> <TR>\n");
		indexData.append(" <TD CLASS=projectfieldheader>Total Tests Failed</TD>\n");
		//indexData.append("   <TD COLSPAN=3 CLASS=fieldvalue> <B><FONT COLOR=\"RED\">"+DriverScript_1.countTestFailed+"/"+totaltest+" ["+((DriverScript_1.countTestFailed * 100)/totaltest)+"%]"+"</TD>\n");
		indexData.append("   <TD CLASS=projectfieldheader>Total Requirement Coverage</TD>\n");
		indexData.append(" <TD CLASS=fieldvalue>"+" "+" </TD>\n");
		indexData.append("  </TR>  </TABLE></DIV>\n");
		indexData.append("<BR>");

	}

	public static void testExecSummaryHeader(){
		indexData.append("<DIV id='show_coverage_matrix'><H1><FONT style=\"BACKGROUND-COLOR:\" color=\"BLACK\">Test Execution Summary[<a href=\"javascript:ShowMatrix('coverage');\"><FONT color =\"BLACK\">show</FONT></a>]</FONT></H1></DIV>\n");
		indexData.append("<DIV id='hide_coverage_matrix'><H1><FONT style=\"BACKGROUND-COLOR:\" color=\"BLACK\">Test Execution Summary[<a href=\"javascript:HideMatrix('coverage');\"><FONT color =\"BLACK\">hide</FONT></a>]</FONT></H1></DIV>\n");
		indexData.append("<DIV ID=\"coverage_matrix\">\n");
		indexData.append("<TABLE class='teststeps' WIDTH='100%'>\n");
		indexData.append("<TR>\n");
		indexData.append(" <TD COLSPAN=3 CLASS=projectheader>Test Execution Summary</TD></TR>\n");

	}



	public static void fillTestDetails(){/*

		//int iTotalTestCases = ExcelUtils.getTestRowCount(Constants.Sheet_TestCases);
		/for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
			try {
				sTestCaseID1 = ExcelUtils.getTestCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
				sTestCaseName1 =ExcelUtils.getTestCellData(iTestcase, Constants.Col_TestCaseName, Constants.Sheet_TestCases);
				sRunMode1 = ExcelUtils.getTestCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
				sTestResult = ExcelUtils.getTestCellData(iTestcase, 5,Constants.Sheet_TestCases);
				indexData.append("<TR>\n");
				indexData.append("<TD CLASS=fieldheader>Test #"+iTestcase+": "+sTestCaseName1+"</TD>\n");
				indexData.append("<td>"+"<a href=\""+sTestCaseID1+".html\">"+sTestCaseID1+"</a>"+"</td>\n");
				if(sTestResult.equals("PASS")){
					indexData.append("<TD CLASS=fieldvalue><B><FONT COLOR=\"GREEN\">"+sTestResult+"</FONT></B></TD>\n");
				}else if(sTestResult.equals("FAIL")){
					indexData.append("<TD CLASS=fieldvalue><B><FONT COLOR=\"RED\">"+sTestResult+"</FONT></B></TD>\n");
				}else{
					indexData.append("<TD CLASS=fieldvalue><B><FONT COLOR=\"BLACK\">"+sTestResult+"</FONT></B></TD>\n");
				}
				indexData.append("</TR>\n");


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	*/}
	public static void createProjectInfoDetails(){
		indexData.append("<BR><DIV id='show_configuration_matrix'><H1><FONT style=\"BACKGROUND-COLOR:\" color=\"BLACK\">Conformiq Test Automation Project Settings [<a href=\"javascript:ShowMatrix('configuration');\"><FONT color=\"BLACK\">show</FONT></a>]</FONT></H1></DIV>\n");
		indexData.append("<DIV id='hide_configuration_matrix'><H1><FONT style=\"BACKGROUND-COLOR:\" color=\"BLACK\">Conformiq Test Automation Project Settings [<a href=\"javascript:HideMatrix('configuration');\"><FONT color=\"BLACK\">hide</FONT></a>]</FONT></H1></DIV>\n");
		indexData.append("<DIV ID=\"configuration_matrix\">\n");
		indexData.append("<TABLE class='teststeps' WIDTH='100%'>\n");
		indexData.append("<TR>\n");
		indexData.append(" <TD COLSPAN=2 CLASS=projectheader>Conformiq Test Automation Project Settings</TD>\n");
		indexData.append("<TR>\n");
		indexData.append("<TD CLASS=fieldheader>Execution Type</TD>\n");
		indexData.append("<TD CLASS=fieldvalue>Selenium</TD>\n");
		indexData.append("</TR>\n");
		indexData.append("</TABLE>\n");
		indexData.append("</DIV>\n");
		indexData.append("<DIV align=\"right\">CopyRight &copy; 2015 Conformiq Inc</DIV>\n");

	}

}
