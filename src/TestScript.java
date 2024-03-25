import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

// Main class
public class TestScript {
    // Main method
    public static void main(String[] args) throws Exception {
        // Setting up Chrome Webdriver
        System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Navigating to the website
        driver.get("https://carepro-training.ihmafrica.com");

        // Adding a DriverWait   
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Locating and filling the username field
        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        usernameField.sendKeys("tester");

        // Locating and filling the password field
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        passwordField.sendKeys("tester2023!");

        // Locating and clicking the sign-in button
        WebElement signinButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(), 'Sign In')]")));
        signinButton.click();

        // Locating and selecting a province from dropdown
        WebElement provinceDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[contains(@class, 'custom-input') and @placeholder='Enter Province']")));
        Select provinceSelect = new Select(provinceDropdown);
        provinceSelect.selectByVisibleText("Lusaka");

        // Locating and selecting a district from dynamic dropdown
        WebElement districtDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[contains(@class, 'custom-input') and @placeholder='Enter District']")));
        Select districtSelect = new Select(districtDropdown);
        wait.until(dropdown -> {
            List<WebElement> districtOptions = districtSelect.getOptions();
            return districtOptions.size() > 1;
            });
        districtSelect.selectByVisibleText("Lusaka");

        // Locating and entering facility name
        WebElement facilityInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='custom-input rounded-full' and @placeholder='Search facility']")));
        facilityInput.sendKeys("Dr. Watson Dental Clinic");

        // Locating and clicking the enter button
        WebElement enterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Enter')]")));
        enterButton.click();

        // Locating and entering NRIC no. in search input
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search by NRC']")));
        searchInput.sendKeys("111111111");

        // Locating and clicking the submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitButton.click();

        // Locating and clicking the attend to patient button
        WebElement attendToPatientButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Attend to Patient')]")));
        attendToPatientButton.click();

        // Locating and clicking the vital button
        WebElement vitalButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div[2]/div[3]/div/div/div[2]/div/div[2]/a/div/p")));
        vitalButton.click();

        // Locating and clicking the new record button
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[1]/div/div/div/div/div[1]/div/button")));
        addButton.click();

        // Reading data from CSV file
        String csvFile = "src\\dataset\\Sample Dataset.csv";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Iterating through each line in the CSV file
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);

                // Locating and retrieving weight
                WebElement weightInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("weight")));
                weightInput.clear();
                weightInput.sendKeys(data[3]);

                // Locating and retrieving height
                WebElement heightInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("height")));
                heightInput.clear();
                heightInput.sendKeys(data[4]);

                // Locating and retrieving temparature
                WebElement temperatureInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("temperature")));
                temperatureInput.clear();
                temperatureInput.sendKeys(data[5]);

                // Locating and retrieving systolic
                WebElement systolicInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("systolic")));
                systolicInput.clear();
                systolicInput.sendKeys(data[6]);

                // Locating and retrieving diastolic
                WebElement diastolicInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("diastolic")));
                diastolicInput.clear();
                diastolicInput.sendKeys(data[7]);

                // Locating and retrieving pulse rate
                WebElement pulseRateInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("pulseRate")));
                pulseRateInput.clear();
                pulseRateInput.sendKeys(data[8]);

                // Locating and retrieving respiratory rate
                WebElement respiratoryRateInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("respiratoryRate")));
                respiratoryRateInput.clear();
                respiratoryRateInput.sendKeys(data[9]);

                // Locating and retrieving oxygen saturation
                WebElement oxygenSaturationInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("oxygenSaturation")));
                oxygenSaturationInput.clear();
                oxygenSaturationInput.sendKeys(data[10]);

                // Locating and clicking the save button 
                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[1]/div/div/div[2]/div/div/div[2]/div/form/div[2]/div/button[2]")));
                saveButton.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Adding a delay before quitting the driver
        Thread.sleep(10000);

        // Quitting the driver
        driver.quit();
    }
}
