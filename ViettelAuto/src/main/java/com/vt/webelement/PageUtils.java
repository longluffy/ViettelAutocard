package com.vt.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtils {

	public static WebElement getLogoutLinkElement(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//a[@href='/dang-xuat']"));
		} catch (Exception e) {
			return null;
		}
	}

	public static WebElement getLoginLinkElement(WebDriver driver) {
		try {
			return driver.findElement(By.xpath("//a[@href='/dang-nhap']"));
		} catch (Exception e) {
			return null;
		}
	}

	public static void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
}
