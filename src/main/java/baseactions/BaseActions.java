package baseactions;

import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;

import static base.BaseTest.getDriver;
import static java.time.temporal.ChronoUnit.MILLIS;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseActions {
    public WebDriver driver;
    public WebDriverWait webDriverWait;
    protected static final Logger logger = LogManager.getLogger(BaseActions.class);
    public static final int DEFAULT_WAIT = 30;
    private static String cachedFileName = null;
    public BaseActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, DEFAULT_WAIT), this);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT));
    }
    public void clickWebElement(WebElement element) {
        logger.info("webelement clickleniyor...");
        element.click();
        logger.info("webelement clicklendi...");
    }
    public void clickSeleniumElement(By by) {
        logger.info("selenium element clickleniyor...");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
        logger.info("selenium element clicklendi...");
    }
    public void assertVisible(By by, String message, int... timeOut) {
        logger.info("element görüntülendimi kontrolu yapılıyor...");
        int timeoutFinal = timeOut.length == 0 ? DEFAULT_WAIT : timeOut[0];
        assertTrue(isElementVisible(by, timeoutFinal), message);
        logger.info("element görüntülendimi kontrolu tamamlandı...");
    }
    protected boolean isElementVisible(By by, int timeOutInSeconds) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                    .pollingEvery(Duration.of(300, MILLIS))
                    .ignoring(NoSuchElementException.class);

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickVisibleElement(By by, String errMsg, int... time) {
        int timeoutFinal = time.length == 0 ? DEFAULT_WAIT : time[0];
        assertVisible(by, errMsg, timeoutFinal);
        driver.findElement(by).click();
    }
    public void fillInVisibleElement(By by, String errMsg, String input, int... time) {
        int timeoutFinal = time.length == 0 ? DEFAULT_WAIT : time[0];
        assertVisible(by, errMsg, timeoutFinal);
        driver.findElement(by).sendKeys(input);
    }
    public void clearVisibleElement(By by, String errMsg, int... time) {
        int timeoutFinal = time.length == 0 ? DEFAULT_WAIT : time[0];
        assertVisible(by, errMsg, timeoutFinal);
        driver.findElement(by).clear();
    }
    public void pressEnterVisibleElement(By by, String errMsg, int... time) {
        int timeoutFinal = time.length == 0 ? DEFAULT_WAIT : time[0];
        assertVisible(by, errMsg, timeoutFinal);
        driver.findElement(by).sendKeys(Keys.ENTER);
    }
    public void ifElementIsVisibleClick(By by) {
        logger.info("Element Görünür İse Tıklama İşlemi Yapılacak...");
        if (isElementVisible(by,10)) {
            logger.info("Elemente Tıklandı...");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
        } else {
            logger.info("Element Görüntülenmediği İçin Tıklama İşlemi Yapılmadan Devam Edilid...");
        }
    }
    public String readCellData(int sheetIndex, int rowIndex, int columnIndex) {
        String cellData = null;
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(System.getProperty("user.dir")+"/data/testData.xlsx"))) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    FormulaEvaluator formulaEval = workbook.getCreationHelper().createFormulaEvaluator();
                    CellType cellType = formulaEval.evaluateInCell(cell).getCellType();
                    switch (cellType) {
                        case NUMERIC:
                            cellData = String.valueOf(cell.getNumericCellValue());
                            break;
                        case STRING:
                            cellData = cell.getStringCellValue();
                            break;
                        case BOOLEAN:
                            cellData = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            cellData = "Invalid value";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellData;
    }

    public String getExcelDataWithRowAndColumn(Integer row, Integer column) {
        return readCellData(0,row,column);
    }
    public void clickElementByIndex(By locator, int index) {
        WebElement element = driver.findElements(locator).get(index);
        element.click();
    }
    public static int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }
    public WebElement findElement(By by, int timeout) {
        FluentWait<WebDriver> webDriverWaitForElement = new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .pollingEvery(Duration.of(300, MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return webDriverWaitForElement.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public String getText(By by) {
        return driver.findElement(by).getText();
    }
    public void writeToFile(String content) {
        String fileName = getFileName();
        String filePath = System.getProperty("user.dir") + "/testresultdata/" + fileName;
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
        } catch (IOException e) {
            System.err.println("Dizin oluşturma işlemi sırasında bir hata oluştu: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(content);
            writer.newLine();
            writer.close();
            System.out.println("Metin başarıyla dosyaya yazıldı: " + filePath);
        } catch (IOException e) {
            System.err.println("Dosyaya yazma işlemi sırasında bir hata oluştu: " + e.getMessage());
        }
    }
    private static String getEpochDate() {
        return String.valueOf(Instant.now().getEpochSecond());
    }
    private static String getFileName() {
        if (cachedFileName == null) {
            cachedFileName = getEpochDate() + "_DATA.txt";
        }
        return cachedFileName;
    }
    public <T> boolean compareValues(T expected, T actual, String msg) {
        if (expected.equals(actual)) {
            return true;
        } else {
            String errorMessage = String.format(msg+" Beklenen: %s, Gelen: %s", expected, actual);
            fail(errorMessage);
            return false;
        }
    }
    public String selectOption(WebElement selectionElement, String option) {
        Select select = new Select(selectionElement);
        select.selectByValue(option);
        List<WebElement> options = select.getOptions();
        Optional<String> selectedText = options.stream().filter(WebElement::isSelected).map(WebElement::getText).findFirst();
        return selectedText.orElse(null);
    }
}
