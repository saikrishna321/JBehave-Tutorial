package com.mtc.pages;
/**
 * 
 * @author saikrishna_v
 */
import java.util.List;

import org.jbehave.web.selenium.FluentWebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Home extends FluentWebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
		
	}

	public void go(){
		
		get("http://www.MovieTickets.com");
		//get("http://www.movietickets.com");
	}
	
	public void searchZipCode(String zip){
		
		findElement(By.xpath("//*[@id='Szip']")).sendKeys(zip);
		//div(id("zGo")).click();
		findElement(By.id("zGo")).click();
				
	}
	
	public void myAccountTrackingID(){
		
		String a = findElement(By.xpath("//table[2]/tbody/tr/td[6]"))
				.getText();
		// System.out.println(a);

		// Splits the Tracking id alone.

		String splittxt = a.replaceAll("-", "");
		System.out.println(splittxt);
		String[] b = splittxt.split("\n");
		String finalstring = b[1].toString();
		System.out.println("**************");
		System.out.println(finalstring);

		// Check in each row if the same tracking id is present:If present Fail
		// else Pass

		List<WebElement> totalRowCount =findElements(By
				.xpath("//*[@id='masOrders']/tbody/tr"));
		System.out.println("***********");
		int sizeofRow = totalRowCount.size();
		System.out.println(sizeofRow);
		System.out.println("*************");
		for (int i = 2; i < sizeofRow; i++) {

			String rowExpected = findElement(
					By.xpath("//tr[" + i + "]/td[6]")).getText();
			String splittxt1 = rowExpected.replaceAll("-", "");
			String[] b1 = splittxt1.split("\n");
			String finalstring1 = b1[1].toString();
			System.out.println("**************");
			System.out.println(finalstring1);

			if (finalstring.equals(finalstring1)) {

				org.junit.Assert.fail("Tracking-ID" + finalstring1 + "matches"
						+ finalstring);
			} else
				System.out.println("Pass::"+ finalstring+"doesnt match with "+finalstring1);

		}
	}
}
