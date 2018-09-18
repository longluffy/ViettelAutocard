package com.vt;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vt.dto.LoginDTO;
import com.vt.dto.NapTheDTO;
import com.vt.login.LoginProcessor;
import com.vt.logout.LogoutProcessor;
import com.vt.napthe.NaptheFTTHTraSauProcessor;

public class ViettelAutoProcessor {
	public static String execute( LoginDTO loginDto, NapTheDTO naptheDto ) {
		try {
			final String phantomjs = System.getProperty("user.dir") + "\\tool\\phantomjs\\bin\\";
			System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomjs);

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability("takesScreenshot", true);
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					phantomjs + "\\phantomjs.exe");

			System.out.println("----STARTING-----");
			WebDriver driver = new PhantomJSDriver(caps);

			long startTime = System.nanoTime();
			//test comment to git
			
			boolean isLogged = LoginProcessor.execute(driver, loginDto);
			if (!isLogged) {
				System.out.println("LOGIN FAILED");
				return "ERROR: LOGIN FAILED";
			}
			
			String message = NaptheFTTHTraSauProcessor.execute(driver, naptheDto);
			System.out.println(message);
			
			LogoutProcessor.execute(driver);

			System.out.println("----END-----");
			driver.quit();
			
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			double seconds = (double)duration / 1000000000.0;
			
			System.out.println("TIME SPENT: " + seconds + "s");
			return message; 

		} catch (IOException e) {
			System.out.println("INTERNAL SERVER ERROR");
			return "SERVER ERROR: " + e.getMessage();
		}
	}
}
