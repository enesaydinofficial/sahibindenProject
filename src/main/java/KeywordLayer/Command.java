package KeywordLayer;

import BasePackage.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Command extends BaseClass {

    public static void CreateFile(String FileName, String FileExtension) throws Exception {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Files\\" + FileName + FileExtension);
        file.createNewFile();
    }

    public static void CreateFolder(String folderPathName) {
        File theDir = new File(folderPathName);

        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }

    public static void DeleteFile(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String GetCurrentURL() {
        return driver.getCurrentUrl();
    }

    public static void GetURL(String URL) {
        Long PageLoadTimeOut = Long.parseLong(config.getProperty("PageLoadTimeOut"));
        driver.manage().timeouts().pageLoadTimeout(PageLoadTimeOut, TimeUnit.MINUTES);
        driver.get(URL);
    }

    public static void OpenNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    public static void Click(By Element) {
        driver.findElement(Element).click();
    }

    public static void RightClick(By Element) {
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(Element)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
    }

    public static void DoubleClick(By Element) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(Element);
        action.doubleClick(element).perform();
    }

    public static void MouseOver(By Element) {
        WebElement element = driver.findElement(Element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void SelectOptionIndex(By Element, int index) {
        new Select(driver.findElement(Element)).selectByIndex(index);
    }

    public static void SelectOptionValue(By Element, String ItemValue) {
        new Select(driver.findElement(Element)).selectByValue(ItemValue);
    }

    public static void SelectOptionVisibleText(By Element, String VisibleText) {
        new Select(driver.findElement(Element)).selectByVisibleText(VisibleText);
    }

    public static void SendKeys(By Element, CharSequence Text) {
        driver.findElement(Element).sendKeys(Text);
    }

    public static void SendKeys(By Element, int Text) {
        driver.findElement(Element).sendKeys(String.valueOf(Text));
    }

    public static void CodeMirrorSetValue(By Element, String setValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].CodeMirror.setValue('" + setValue + "');", driver.findElement(Element));
    }

    public static boolean isDisplayed(By Element) {
        return driver.findElement(Element).isDisplayed();
    }

    public static boolean isElementPresent(By Element) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(Element);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void WaitElementToBeClickable(By Element) throws Exception {
        Long WaitTimeOutSeconds = Long.parseLong(config.getProperty("WaitTimeOutSeconds"));
        ThreadSleep1Seconds();
        WebDriverWait wait = new WebDriverWait(driver, WaitTimeOutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(Element)));
    }

    public static void WaitElementVisible(By Element) {
        Long WaitTimeOutSeconds = Long.parseLong(config.getProperty("WaitTimeOutSeconds"));
        WebDriverWait wait = new WebDriverWait(driver, WaitTimeOutSeconds);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(Element)));
    }

    public static void WaitElementNotVisible(By Element) {
        Long WaitTimeOutSeconds = Long.parseLong(config.getProperty("WaitTimeOutSeconds"));
        WebDriverWait wait = new WebDriverWait(driver, WaitTimeOutSeconds);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(Element)));
    }

    public static void WaitForElementNotVisible(By Element) {

        while (true) {

            try {
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                List WebElement = driver.findElements(Element);

                if (WebElement.size() >= 1) {
                    ThreadSleep1Seconds();
                } else {
                    ImplicitlyWait();
                    break;
                }

            } catch (Exception ex) {
            }

        }
    }

    public static void ClearInput(By Element) {
        driver.findElement(Element).clear();
    }

    public static void ClearMultipleSelectedOption(By Element) {
        new Select(driver.findElement(Element)).deselectAll();
    }

    public static String GetText(By Element) {
        WaitElementVisible(Element);
        return driver.findElement(Element).getText();
    }

    public static String GetSelectedOptionText(By Element) {
        Select dropdown = new Select(driver.findElement(Element));
        return dropdown.getFirstSelectedOption().getText();
    }

    public static String GetAttribute(By Element, String AttributeName) {
        return driver.findElement(Element).getAttribute(AttributeName);
    }

    public static String SelectedOptionGetText(By Element) {
        return new Select(driver.findElement(Element)).getFirstSelectedOption().getText();
    }

    public static String SelectedOptionGetValue(By Element) {
        return new Select(driver.findElement(Element)).getFirstSelectedOption().getAttribute("value");
    }

    public static void ThreadSleep1Seconds() throws Exception {
        Thread.sleep(1000);
    }

    public static void ThreadSleep3Seconds() throws Exception {
        Thread.sleep(3 * 1000);
    }

    public static void ThreadSleep5Seconds() throws Exception {
        Thread.sleep(5 * 1000);
    }

    public static void ThreadSleep10Seconds() throws Exception {
        Thread.sleep(10 * 1000);
    }

    public static void PageLoad() {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public static void ImplicitlyWait() {
        long ImplicitlyWait = Long.parseLong(config.getProperty("ImplicitlyWait"));
        driver.manage().timeouts().implicitlyWait(ImplicitlyWait, TimeUnit.SECONDS);
    }

    public static void AssertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void AssertEqualsInt(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void VerifyElementNotVisible(By Element) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElements(Element).isEmpty());
        ImplicitlyWait();
    }

    public static void CheckBoxChecked(By Element) {
        driver.findElement(Element).isSelected();
    }

    public static void PageRefresh() {
        driver.navigate().refresh();
    }

    public static void KeysENTER(By Element) {
        driver.findElement(Element).sendKeys(Keys.ENTER);
    }

    public static void SwitchWindowTab(int tab) {
        ArrayList<String> TabSwitch = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(TabSwitch.get(tab));
    }

    public static void SwitchFrame(WebElement Frame) {
        driver.switchTo().frame(Frame);
    }

    public static void SwitchParentFrame() {
        driver.switchTo().parentFrame();
    }

    public static String GetWindowHandle() {
        return driver.getWindowHandle();
    }

    public static void DeleteCookie() {
        driver.manage().deleteAllCookies();
    }

    public static String GetCurrentDate() {
        Calendar cal = Calendar.getInstance();
        int CurrentDay = cal.get(Calendar.DAY_OF_MONTH);
        int CurrentMonth = cal.get(Calendar.MONTH);
        int CurrentYear = cal.get(Calendar.YEAR);
        return CurrentDay + "." + CurrentMonth + "." + CurrentYear;
    }

    public static String GetCurrentMonthYear() {
        Calendar cal = Calendar.getInstance();
        int CurrentMonth = cal.get(Calendar.MONTH);
        int CurrentYear = cal.get(Calendar.YEAR);
        return CurrentMonth + "." + CurrentYear;
    }

    public static String GetCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        int CurrentDay = cal.get(Calendar.DAY_OF_MONTH);
        int CurrentMonth = cal.get(Calendar.MONTH);
        int CurrentYear = cal.get(Calendar.YEAR);
        int CurrentHour = cal.get(Calendar.HOUR);
        int CurrentMinute = cal.get(Calendar.MINUTE);
        String GetCurrentDateTime = CurrentDay + "." + CurrentMonth + "." + CurrentYear;
        return GetCurrentDateTime + " " + CurrentHour + ":" + CurrentMinute;
    }

    public static void DragAndDrop(WebElement From, WebElement To) throws Exception {
        Actions act = new Actions(driver);

        ScrollToElement(From);
        ThreadSleep1Seconds();
        act.clickAndHold(From).build().perform();
        ScrollToElement(To);
        ThreadSleep1Seconds();
        act.moveToElement(To).build().perform();


        act.release(To).build().perform();
    }

    public static void PageZoom(String ZoomValue) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + ZoomValue + "%'");
        ThreadSleep1Seconds();
    }

    public static void PageDownScroll() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("scroll(0,10000)");
    }

    public static void PageScroll(int x, int y) {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("scroll(" + x + "," + y + ")");
    }

    public static void PageUpScroll() {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("scroll(0,0)");
    }

    public static void ScrollToElement(WebElement element) {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void ScrollToElement(By element) {
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
    }


}

