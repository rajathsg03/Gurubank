package com.gurubank.project;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.gurubank.util.ExcelReader;
import com.gurubank.util.utill;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;

public class gurubanklogin {
	
	public static WebDriver driver;
	
	public static void launch () {
		System.setProperty("webdriver.gecko.driver", utill.firefoxpath);
		driver = new FirefoxDriver();
		driver.get(utill.baseurl);
		
		
	}
	
	public static void main(String[] args) throws IOException {
		//launch();
				
	    	//System.out.println(e.getMessage());
	    
	    ExcelReader xls = new ExcelReader();
			String sname = "TestCases";
			String Usercol = xls.getCellData(sname, 0, 0);
			//System.out.println(Usercol);
			String Passcol = xls.getCellData(sname, 1, 0);
			//System.out.println(Passcol);
			//String Erelcol = xls.getCellData(sname, 3, 0);
			//String Expectedresult = xls.getCellData(sname, 4, 0); ;
			String Actualresult;
	    
	    
	    for (int i = 1; i < xls.getRowCount(sname);i++ ) {
	    	launch();
	    	
	    driver.findElement(By.name("uid")).sendKeys(xls.getCellData(sname, Usercol, i));
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.findElement(By.name("password")).sendKeys(xls.getCellData(sname, Passcol, i));
	    driver.findElement(By.name("btnLogin")).click();
	    //Expectedresult = xls.getCellData(sname, Erelcol, i);
	    try {
	    	Alert alert = driver.switchTo().alert();
	    	Actualresult = alert.getText();
	    	System.out.println(Actualresult);
	    	alert.accept();
	    }
	    catch (NoAlertPresentException e) 
	    {
	    WebElement link = driver.findElement(By.linkText("New Customer"));
	    String Message = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
	    System.out.println(Message);
	    if (link.isDisplayed()) {
	    	System.out.println("Login Successful");
	    	driver.close();
	    }
	    }
	    catch (Exception e) {
	    	//System.out.println(e.getMessage());
	    	System.out.println("Login Unsuccessful");
	    	driver.close();
	    }
	    }
	    
	}
	
}
