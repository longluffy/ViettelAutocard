import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainRunnableChrome extends Thread {
	public static void main(String[] args) {
		Thread threadKeepSession = new Thread() {
			public void run() {
				System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		        // Add options to Google Chrome. The window-size is important for responsive sites
		        ChromeOptions options = new ChromeOptions();
		        options.addArguments("headless");
		        options.addArguments("window-size=1200x600");
		        WebDriver driver = new ChromeDriver(options);
		        driver.get("http://seleniumhq.org"); 
		        
		        boolean a = driver.findElement(By.id("q")).isDisplayed();
		        System.out.println(" a : " + a);
		        driver.quit();
			}
		};
		threadKeepSession.start();
	}

}
