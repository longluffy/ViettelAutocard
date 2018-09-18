package com.vt.login;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vt.dto.LoginDTO;
import com.vt.webelement.PageUtils;

public class LoginProcessor {

	private static final String CSRF_TOKEN = "vt_signin__csrf_token";
	private static final String USER_NAME_ID = "usernameForm";
	private static final String PASSWORD_ID = "passwordForm";
	private static final String SIGN_IN_CATEGORY_ID = "vt_signin_category";

	private final static String BASE_URL = "https://viettel.vn/dang-nhap";

	public static boolean execute(WebDriver driver, LoginDTO loginDto) {
		if (loginDto == null || StringUtils.isEmpty(loginDto.getUsername())
				|| StringUtils.isEmpty(loginDto.getPassword()) || loginDto.getCategory() == null) {
			return false;
		}

		System.out.println(">> START LOGIN <<");
		driver.get(BASE_URL);

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isLoadedForm(d);
			}
		});

		// Find the text input element by its vt_signin__csrf_token
		WebElement csrfTokenEl = driver.findElement(By.id(CSRF_TOKEN));
		WebElement userNameEl = driver.findElement(By.id(USER_NAME_ID));
		WebElement passwordEl = driver.findElement(By.id(PASSWORD_ID));
		WebElement categorySignInEl = driver.findElement(By.id(SIGN_IN_CATEGORY_ID));
		WebElement buttonLoginEl = driver.findElement(By.cssSelector("button[class='myviettel_button']"));

		// get value
		String csrfString = csrfTokenEl.getAttribute("value");
		System.out.println("form csrftoken: " + csrfString);

		// sendkey to form
		userNameEl.sendKeys(loginDto.getUsername());
		passwordEl.sendKeys(loginDto.getPassword());
		categorySignInEl.sendKeys(loginDto.getCategory().name());

		
		buttonLoginEl.click();
		PageUtils.waitForLoad(driver);

		WebElement logoutEl = PageUtils.getLogoutLinkElement(driver);
		if (logoutEl != null) {
			System.out.println(">> LOGIN SUCCESS <<");
			return true;
		}
		System.out.println(">> LOGIN FAILED <<");
		return false;
	}

	private static boolean isLoadedForm(WebDriver d) {
		return d.findElement(By.id(CSRF_TOKEN)) != null && d.findElement(By.id(USER_NAME_ID)) != null
				&& d.findElement(By.id(PASSWORD_ID)) != null && d.findElement(By.id(SIGN_IN_CATEGORY_ID)) != null;
	}
}
