package com.gurubank.project;

import org.testng.annotations.Test;
import com.gurubank.util.ExcelReader;
import com.gurubank.util.utill;
import org.testng.annotations.DataProvider;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.*;

public class TestNGLogin {
	public WebDriver driver;
	
	 @BeforeMethod
	  public void beforeTest() {
		  System.setProperty("webdriver.gecko.driver", utill.firefoxpath);
			driver = new FirefoxDriver();
			driver.get(utill.baseurl);
			
	  }
	 
	 
	 public void  screenshot() {
		 Date d = new Date();
		 String screenshotfile = d.toString().replace(":", "_").replace(" ", "_")+".png";
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"//target//"+screenshotfile));
			try {
				FileHandler.copy(srcFile, new File(System.getProperty("user.dir")+"//target//"+screenshotfile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

	
  @Test(dataProvider = "dp")
  public void Login(String uname, String pass, String Eresult) {
	  
	  String Aresult;
	  
	  driver.findElement(By.name("uid")).sendKeys(uname);
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.findElement(By.name("password")).sendKeys(pass);
	    driver.findElement(By.name("btnLogin")).click();
	    
	    try {
	    	Alert alert = driver.switchTo().alert();
	    	Aresult = alert.getText();
	    	//System.out.println(Aresult);
	    	alert.accept();
	    	if (Aresult.equalsIgnoreCase(Eresult)) {
	    		System.out.println("Test Case Pass");
	    	}
	    	else {
	    		System.out.println("Test Case fail");
	    	}
	    }
	    catch (NoAlertPresentException e) 
	    {
	    Aresult = driver.getTitle();
	    screenshot();
	    String Message = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
	    //System.out.println(Message.contains(uname));
	    if (Aresult.equalsIgnoreCase(Eresult) && Message.contains(uname)) {
    		System.out.println("Test Case Pass");
    	}
    	else {
    		System.out.println("Test Case fail");
    	}
	    	
	    }
	    
	    }
	  

  @DataProvider
  public Object[][] dp() {
	  ExcelReader xls = new ExcelReader();
	  String sname = "TestCases";
	  int rows;
	  int cols;
	  rows = xls.getRowCount(sname);
	  cols = xls.getColCount(sname);
	  
	  System.out.println("Total rows and Columns "+ rows+" "+cols);
	 
   Object[][] data = new Object[rows-1][cols];;
	  int drow = 0;
	  
  for (int i=1;i<rows;i++) {
	  for (int j=0;j<cols;j++) {
		  data[drow][j]= xls.getCellData(sname, j, i);
		  System.out.println(data[drow][j]);
		  
	  }
	  drow++;
  }
  
    return data;
  }
 
  @AfterMethod
  public void afterTest() {
	  driver.close();
  }

}
