/**
 * 
 */
package blog163.visitor;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.xalan.xsltc.cmdline.Compile;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author chenguangjian 2015年3月15日 上午2:36:25
 */
public class WebTools {

	static boolean called = false;
	static Set<Cookie> cookieSet;
	private static String filePath = Messages.getString("WebTools.UrlFilePath"); //$NON-NLS-1$
	static int count = 0;

	//	static String url = Messages.getString("WebTools.RV0"); //$NON-NLS-1$

	public static void main(String[] args) throws InterruptedException,
			IOException {
		// WebTools webTools = new WebTools();
		//
		// webTools.driverGet(url);
		// // 打开指定路径的
		System.setProperty(Messages.getString("WebTools.RV1"), //$NON-NLS-1$
				Messages.getString("WebTools.RV2")); //$NON-NLS-1$
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxProfile firefoxprofile = new FirefoxProfile(new File(
				Messages.getString("WebTools.RV3"))); //$NON-NLS-1$
		capabilities.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
		WebDriver driver = new FirefoxDriver(capabilities);
		login(driver);

		for (String url : Utils.getUrls(filePath)) {
			driverGet(url, driver);
		}

		driver.quit();
	}

	public void driverGet(String url) throws InterruptedException {

		// 打开指定路径的
		System.setProperty(Messages.getString("WebTools.RV1"), //$NON-NLS-1$
				Messages.getString("WebTools.RV2")); //$NON-NLS-1$
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxProfile firefoxprofile = new FirefoxProfile(new File(
				Messages.getString("WebTools.RV3"))); //$NON-NLS-1$
		capabilities.setCapability(FirefoxDriver.PROFILE, firefoxprofile);
		WebDriver driver = new FirefoxDriver(capabilities);
		// if (!called) {
		// login(driver);
		// called = true;
		// }
		login(driver);
		Thread.sleep(3000);

		try {
			driver.navigate().to(url);
			System.out.println("Count:" + (count++));
			// cookieSet = driver.manage().getCookies();
			// addCookies(driver);
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript(Messages
					.getString("WebTools.RV5")); //$NON-NLS-1$

			String html = driver.getPageSource();
			// System.out.println(html);

			String regex = Messages.getString("WebTools.RV6"); //$NON-NLS-1$
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(html);

			List<String> hrefs = new ArrayList<String>();
			while (m.find()) {
				String ms = m.group();
				int beginIndex = ms.indexOf(Messages.getString("WebTools.0")); //$NON-NLS-1$
				int endIndex = ms.indexOf(Messages.getString("WebTools.1")); //$NON-NLS-1$
				// System.out.println(ms);
				if (beginIndex < endIndex) {
					String href = ms.substring(beginIndex, endIndex);
					// System.out.println(href);
					hrefs.add(href);
				}

			}

			for (String e : hrefs) {
				driver.navigate().to(e);
				driverGet(e);
			}
			// driver.close();
			// driver.quit();

			// for (String e : hrefs) {
			// driverGet(e);
			// }

		} catch (Exception e) {// 打印堆栈信息
			e.printStackTrace();
		} finally {
			try {// 关闭并退出
				driver.close();
				driver.quit();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * 
	 * @param url
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void driverGet(String url, WebDriver driver)
			throws InterruptedException {

		// Thread.sleep(3000);

		try {

			// addCookies(driver);

			driver.navigate().to(url);
			// Thread.sleep(3000);

			((JavascriptExecutor) driver).executeScript(Messages
					.getString("WebTools.RV5")); //$NON-NLS-1$

			String html = driver.getPageSource();
			// System.out.println(html);

			String regex = Messages.getString("WebTools.RV6"); //$NON-NLS-1$
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(html);

			List<String> hrefs = new ArrayList<String>();
			while (m.find()) {
				String ms = m.group();
				int beginIndex = ms.indexOf(Messages.getString("WebTools.0")); //$NON-NLS-1$
				int endIndex = ms.indexOf(Messages.getString("WebTools.1")); //$NON-NLS-1$
				// System.out.println(ms);
				if (beginIndex < endIndex) {
					String href = ms.substring(beginIndex, endIndex);
					// System.out.println(href);
					hrefs.add(href);
				}

			}

			for (String e : hrefs) {
				driver.navigate().to(e);
				System.out.println("Count:" + (count++));
				// driverGet(e, driver);// here recursive call , out of memory
			}

		} catch (Exception e) {// 打印堆栈信息
			e.printStackTrace();
		} finally {
			try {// 关闭并退出
					// driver.close();
					// driver.quit();
			} catch (Exception e) {
			}
		}

	}

	private static void login(WebDriver driver) throws InterruptedException {
		driver.navigate().to(Messages.getString("WebTools.login_url")); //$NON-NLS-1$
		Thread.sleep(1000);
		driver.findElement(
				By.xpath(Messages.getString("WebTools.usernameXpath"))).sendKeys( //$NON-NLS-1$
						Messages.getString("WebTools.user")); //$NON-NLS-1$
		driver.findElement(
				By.xpath(Messages.getString("WebTools.passwordXpath"))).sendKeys( //$NON-NLS-1$
						Messages.getString("WebTools.passwd")); //$NON-NLS-1$
		driver.findElement(
				By.xpath(Messages.getString("WebTools.loginBtnXpath"))).click();; //$NON-NLS-1$
		Thread.sleep(1000);

	}

	private void addCookies(WebDriver driver) {

		for (Cookie c : cookieSet) {
			driver.manage().addCookie(c);
		}

		// // blogAppAd_blog7index=1; blogAppAd_blog7indexM=1;
		// // UOR=blog.sina.com.cn,blog.sina.com.cn,;
		//		String cookieStr = Messages.getString("WebTools.RV4"); //$NON-NLS-1$
		//		String[] KVs = cookieStr.split(";"); //$NON-NLS-1$
		// for (String e : KVs) {
		//			String[] kv = e.split("="); //$NON-NLS-1$
		// String key = kv[0];
		// String value = kv[1];
		// Cookie cookie = new Cookie(key, value);
		//
		// driver.manage().addCookie(cookie);
		// }

	}
}
