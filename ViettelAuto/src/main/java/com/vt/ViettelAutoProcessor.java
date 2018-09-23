package com.vt;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vt.dto.LoginDTO;
import com.vt.dto.NapTheDTO;
import com.vt.login.LoginProcessor;
import com.vt.napthe.NaptheFTTHTraSauProcessor;
import com.vt.webelement.PageUtils;

public class ViettelAutoProcessor {

	private LoginDTO loginDto;
	private NapTheDTO naptheDto;
	private String pathExe;

	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1";

	public ViettelAutoProcessor(LoginDTO loginDto, NapTheDTO naptheDto, String pathExe) {
		this.loginDto = loginDto;
		this.naptheDto = naptheDto;
		this.pathExe = pathExe;
	}

	public synchronized String execute() {
		System.out.println("Doing heavy processing - START " + Thread.currentThread().getName());

		//
		// String geckoDriverPath = System.getProperty(DRIVER_PATH);
		// if (StringUtils.isEmpty(geckoDriverPath)) {
		// // user path
		// final String geckoDriver = System.getProperty("user.dir") +
		// "\\tool\\driver\\window_64\\geckodriver.exe";
		// System.setProperty("webdriver.gecko.driver", geckoDriver);
		// }
		//
		// FirefoxBinary firefoxBinary = new FirefoxBinary();
		// firefoxBinary.addCommandLineOptions("--headless");
		// FirefoxOptions firefoxOptions = new FirefoxOptions();
		// firefoxOptions.setBinary(firefoxBinary);
		// FirefoxDriver driver = new FirefoxDriver(firefoxOptions);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "FIREFOX");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent", USER_AGENT);
		capabilities.setCapability(FirefoxDriver.PROFILE, true);
		System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, pathExe);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, pathExe);

		System.out.println("----STARTING-----");
		WebDriver driver = new PhantomJSDriver(capabilities);

		driver.manage().window().setSize(new Dimension(320, 480));
		 
		long startTime = System.nanoTime();

		try {
			driver.get("https://viettel.vn/m/");
			PageUtils.waitForLoad(driver);

			// checklogin
			WebElement loginLink = PageUtils.getLoginLinkElement(driver);
			System.out.println("loginLink == null");
			
			if (loginLink != null) {
				System.out.println("loginLink != null");
				LoginProcessor loginProcessor = new LoginProcessor(driver, loginDto);
				boolean isLogged = loginProcessor.execute();
				if (!isLogged) {
					System.out.println("LOGIN FAILED");
					return "ERROR: LOGIN FAILED";
				}
			}

			// goto trang chu
			driver.get("https://viettel.vn/myvt/trang-chu");
			PageUtils.waitForLoad(driver);
  
			NaptheFTTHTraSauProcessor naptheProcessor = new NaptheFTTHTraSauProcessor(driver, naptheDto);
			String message = naptheProcessor.execute();

			System.out.println(message);

			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			double seconds = (double) duration / 1000000000.0;

			System.out.println("TIME SPENT: " + seconds + "s");
			System.out.println("Doing heavy processing - END " + Thread.currentThread().getName());
			driver.quit();
			return message;

		} catch (Exception e) {
			System.out.println("INTERNAL SERVER ERROR");
			driver.quit();
			return "SERVER ERROR: " + e.getMessage();
		}

	}

	public LoginDTO getLoginDto() {
		return loginDto;
	}

	public void setLoginDto(LoginDTO loginDto) {
		this.loginDto = loginDto;
	}

	public NapTheDTO getNaptheDto() {
		return naptheDto;
	}

	public void setNaptheDto(NapTheDTO naptheDto) {
		this.naptheDto = naptheDto;
	}

	public String getPathExe() {
		return pathExe;
	}

	public void setPathExe(String pathExe) {
		this.pathExe = pathExe;
	}

}
