package com.vt.logout;

import org.openqa.selenium.WebDriver;

import com.vt.webelement.PageUtils;

public class LogoutProcessor {
	private final static String BASE_URL = "https://viettel.vn/dang-xuat";
	private WebDriver driver;

	public LogoutProcessor(WebDriver driver) {
		this.driver = driver;
	}

	public void execute() {
		PageUtils.offlogging();

		driver.get(BASE_URL);

		PageUtils.waitForLoad(driver);

		System.out.println("----LOGOUT SUCCESS-----");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
