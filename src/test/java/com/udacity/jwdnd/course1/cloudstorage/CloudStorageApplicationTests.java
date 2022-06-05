package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.time.Duration;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}
	private void doLogout(){
		WebElement logoutButton = driver.findElement(By.id("logout"));
		logoutButton.click();

	}
	private void addNotes(String noteTitle,String noteDescription) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		jse.executeScript("arguments[0].click()", notesTab);
		webDriverWait.withTimeout(Duration.ofSeconds(30));
		WebElement newNote = driver.findElement(By.id("newnote"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(newNote)).click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement notetite = driver.findElement(By.id("note-title"));
		notetite.sendKeys(noteTitle);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement notedescription = driver.findElement(By.id("note-description"));
		notedescription.sendKeys(noteDescription);
		WebElement savechanges = driver.findElement(By.id("save-changes"));
		savechanges.click();
		Assertions.assertEquals("http://localhost:" + this.port + "/result?success", driver.getCurrentUrl());
	}
	private void updateNotes(String newNoteTitle,String newNoteDescription){
		driver.get("http://localhost:" + this.port + "/home");
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		jse.executeScript("arguments[0].click()", notesTab);
		WebElement notesTable = driver.findElement(By.id("userTable"));
		List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
		WebElement editElement = null;
		for (int i = 0; i < notesList.size(); i++) {
			WebElement element = notesList.get(i);
			editElement = element.findElement(By.name("edit"));
			if (editElement != null){
				break;
			}
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(editElement)).click();
		WebElement notetitle = driver.findElement(By.id("note-title"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(notetitle));
		notetitle.clear();
		notetitle.sendKeys(newNoteTitle);
		WebElement notedescription = driver.findElement(By.id("note-description"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(notedescription));
		notedescription.clear();
		notedescription.sendKeys(newNoteDescription);
		WebElement savechanges = driver.findElement(By.id("save-changes"));
		savechanges.click();
		Assertions.assertEquals("Result", driver.getTitle());


		driver.get("http://localhost:" + this.port + "/home");
		notesTab = driver.findElement(By.id("nav-notes-tab"));
		jse.executeScript("arguments[0].click()", notesTab);
		notesTable = driver.findElement(By.id("userTable"));
		notesList = notesTable.findElements(By.tagName("th"));
		Boolean edited = false;
		for (int i = 0; i < notesList.size(); i++) {
			WebElement element = notesList.get(i);
			if (element.getAttribute("innerHTML").equals(newNoteTitle)) {
				edited = true;
				break;
			}
		}
		Assertions.assertTrue(edited);
	}
	private void deleteNote(){
		driver.get("http://localhost:" + this.port + "/home");
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		jse.executeScript("arguments[0].click()", notesTab);
		WebElement notesTable = driver.findElement(By.id("userTable"));
		List<WebElement> notesList = notesTable.findElements(By.tagName("td"));
		WebElement deleteElement = null;
		for (int i = 0; i < notesList.size(); i++) {
			WebElement element = notesList.get(i);
			deleteElement = element.findElement(By.name("delete"));
			if (deleteElement != null){
				break;
			}
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
		Assertions.assertEquals("http://localhost:" +this.port+"/result?success",driver.getCurrentUrl());
	}
	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/signup?success", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	@Test
	public void testUnauthorized(){
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
		driver.get("http://localhost:" + this.port + "/notes");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
		driver.get("http://localhost:" + this.port + "/credentials");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("http://localhost:" + this.port + "/signup", driver.getCurrentUrl());
	}


	@Test
	public void testLogoutNotAccessHomePage(){
		doMockSignUp("Quoc","LeVan","admin","123");
		doLogIn("admin","123");
		Assertions.assertEquals("Home",driver.getTitle());
		doLogout();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	@Test
	public void testDoNotes(){
		doMockSignUp("Quoc","LeVan","admin","123");
		doLogIn("admin","123");
		addNotes("noteTitle","noteDescription");
		updateNotes("newNoteTitle","newNoteDescription");
		deleteNote();
	}

	@Test
	public void testDoCredentials(){
		doMockSignUp("Quoc","LeVan","admin","123");
		doLogIn("admin","123");
		addCredential("URL","username","password");
		updateCredential("newURL");
		deleteCredential();
	}

	private void deleteCredential() {
		driver.get("http://localhost:" + this.port + "/home");
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
		jse.executeScript("arguments[0].click()", credentialsTab);
		WebElement credentialsTable = driver.findElement(By.id("credentialTable"));
		List<WebElement> credentialsList = credentialsTable.findElements(By.tagName("td"));
		WebElement deleteElement = null;
		for (int i = 0; i < credentialsList.size(); i++) {
			WebElement element = credentialsList.get(i);
			deleteElement = element.findElement(By.name("deleteCredentials"));
			if (deleteElement != null){
				break;
			}
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteElement)).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Assertions.assertEquals("http://localhost:" +this.port+"/result?success",driver.getCurrentUrl());
	}

	private void updateCredential(String newUrl) {
		driver.get("http://localhost:" + this.port + "/home");
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		jse.executeScript("arguments[0].click()", credentialTab);
		WebElement credentialsTable = driver.findElement(By.id("credentialTable"));
		List<WebElement> credentialsList = credentialsTable.findElements(By.tagName("td"));
		WebElement editElement = null;
		for (int i = 0; i < credentialsList.size(); i++) {
			WebElement element = credentialsList.get(i);
			editElement = element.findElement(By.name("editCredential"));
			if (editElement != null){
				break;
			}
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(editElement)).click();
		WebElement url = driver.findElement(By.id("credential-url"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(url));
		url.clear();
		url.sendKeys(newUrl);
		WebElement savechanges = driver.findElement(By.id("save-changes-credential"));
		savechanges.click();
		Assertions.assertEquals("http://localhost:"+this.port +"/result?success", driver.getCurrentUrl());

		driver.get("http://localhost:" + this.port + "/home");
		credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		jse.executeScript("arguments[0].click()", credentialTab);
		credentialsTable = driver.findElement(By.id("credentialTable"));
		credentialsList = credentialsTable.findElements(By.tagName("th"));
		Boolean edited = false;
		for (int i = 0; i < credentialsList.size(); i++) {
			WebElement element = credentialsList.get(i);
			if (element.getAttribute("innerHTML").equals(newUrl)) {
				edited = true;
				break;
			}
		}
		Assertions.assertTrue(edited);
	}

	private void addCredential(String URL, String userName,String passWord) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		jse.executeScript("arguments[0].click()", credentialTab);
		webDriverWait.withTimeout(Duration.ofSeconds(30));
		WebElement newCredential = driver.findElement(By.id("newcredential"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(newCredential)).click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement url = driver.findElement(By.id("credential-url"));
		url.sendKeys(URL);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement username = driver.findElement(By.id("credential-username"));
		username.sendKeys(userName);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
		WebElement password = driver.findElement(By.id("credential-password"));
		password.sendKeys(passWord);
		WebElement savechanges = driver.findElement(By.id("save-changes-credential"));
		savechanges.click();
		Assertions.assertEquals("http://localhost:" + this.port + "/result?success", driver.getCurrentUrl());
	}
}
