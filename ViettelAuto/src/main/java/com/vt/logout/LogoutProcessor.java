package com.vt.logout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vt.webelement.PageUtils;

public class LogoutProcessor {
	private final static String BASE_URL = "https://viettel.vn/dang-xuat";
	
	public static void execute(WebDriver driver) {
		driver.get(BASE_URL);

		PageUtils.waitForLoad(driver);
		
		System.out.println("----LOGOUT SUCCESS-----");
	}
}
