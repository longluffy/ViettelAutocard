package com.vt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ProtocolHandshake;

import com.vt.dto.IpProxyDTO;
import com.vt.dto.LoginDTO;
import com.vt.dto.NapTheDTO;
import com.vt.login.LoginProcessor;
import com.vt.logout.LogoutProcessor;
import com.vt.napthe.NaptheFTTHTraSauProcessor;

public class ViettelAutoProcessor {

	private LoginDTO loginDto;
	private NapTheDTO naptheDto;
	private String pathExe;

	public ViettelAutoProcessor(LoginDTO loginDto, NapTheDTO naptheDto, String pathExe) {
		this.loginDto = loginDto;
		this.naptheDto = naptheDto;
		this.pathExe = pathExe;
	}

	public synchronized String execute() {
		WebDriver driver = null;
		Logger.getLogger(PhantomJSDriverService.class.getName()).setLevel(Level.OFF);
		Logger.getLogger(ProtocolHandshake.class.getName()).setLevel(Level.OFF);
		
		try {
			IpProxyDTO ipProxyDTO = fakeProxyVN();

			// options
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// fake ip
			ArrayList<String> cliArgsCap = new ArrayList<String>();
			cliArgsCap.add("--proxy=" + ipProxyDTO.getHost() + ":" + ipProxyDTO.getPort());
			cliArgsCap.add("--webdriver-loglevel=NONE");
 
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, pathExe);
			capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);

			System.out.println("----STARTING-----");

			driver = new PhantomJSDriver(capabilities);
			long startTime = System.nanoTime();

			if (loginDto.isHasLoginLogout()) {
				LoginProcessor loginProcessor = new LoginProcessor(driver, loginDto);
				boolean isLogged = loginProcessor.execute();
				if (!isLogged) {
					System.out.println("LOGIN FAILED");
					return "ERROR: LOGIN FAILED";
				}
			}
			NaptheFTTHTraSauProcessor naptheProcessor = new NaptheFTTHTraSauProcessor(driver, naptheDto);
			String message = naptheProcessor.execute();
			System.out.println(message);

			if (loginDto.isHasLoginLogout()) {
				LogoutProcessor logoutProcessor = new LogoutProcessor(driver);
				logoutProcessor.execute();
			}

			System.out.println("----END-----");

			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			double seconds = (double) duration / 1000000000.0;

			System.out.println("TIME SPENT: " + seconds + "s");
			driver.quit();
			return message;

		} catch (Exception e) {
			System.out.println("INTERNAL SERVER ERROR");
			if (driver != null) {
				driver.quit();
			}
			return "SERVER ERROR: " + e.getMessage();
		}
	}

	private IpProxyDTO fakeProxyVN() throws IOException, JSONException {
		String url = "https://api.getproxylist.com/proxy?country[]=VN";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print in String
		System.out.println(response.toString());

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(response.toString());
		String host = myResponse.getString("ip");
		int port = myResponse.getInt("port");
		System.out.println("host: " + host + "--" + "port:" + port);
		IpProxyDTO ipProxyDto = new IpProxyDTO(host, port);

		return ipProxyDto;
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
