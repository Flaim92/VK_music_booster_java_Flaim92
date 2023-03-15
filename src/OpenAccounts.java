import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class OpenAccounts {
    public static WebDriver driver;
    public static void closeDriver() {
        while (1 > 0) {
        driver.quit();
        driver.close();
        }

    }
    private static boolean isStopRequested = false;
    public static void setIsStopRequested(boolean value) {
        isStopRequested = value;
    }
    public static boolean getIsStopRequested() {
        return isStopRequested;
    }
    public static void OpenAccounts_run() {

        String fileName = "Settings.txt";
        ArrayList<String[]> loginData = importDataFromFile(fileName);
        openAccountsInBrowser(loginData);
    }



    public static ArrayList<String[]> importDataFromFile(String fileName) {
        ArrayList<String[]> loginData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null && !line.equals("///")) {
                String[] data = line.split("/");
                loginData.add(data);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginData;
    }

    public static void openAccountsInBrowser(ArrayList<String[]> loginData) {
        Desktop desktop = Desktop.getDesktop();
        for (String[] data : loginData) {
            try {
                if (isStopRequested) {
                    break;
                }
                // Проверяем флаг stopFlag после каждой итерации цикла


                // Создание экземпляра FirefoxDriver

                driver = new FirefoxDriver();

                // Открытие страницы
                driver.get("https://vk.com/");
                TimeUnit.MILLISECONDS.sleep(3000);
                WebElement email_input = driver.findElement(By.id("index_email"));
                TimeUnit.MILLISECONDS.sleep(3000);
                email_input.sendKeys(data[0]);
                // ждать, пока страница полностью загрузится
                TimeUnit.MILLISECONDS.sleep(3000);
                email_input.sendKeys(Keys.ENTER);
                TimeUnit.MILLISECONDS.sleep(3000);
                WebElement password_input = driver.findElement(By.name("password"));
                password_input.sendKeys(data[1]);
                password_input.sendKeys(Keys.ENTER);
                TimeUnit.MILLISECONDS.sleep(3000);
                driver.get(LINK_READER.line);
                WebElement audio_start = driver.findElement(By.className("audio_row__counter"));
                audio_start.click();
                TimeUnit.MILLISECONDS.sleep(3000);
                WebElement audio_start2 = driver.findElement(By.className("top_audio_player_title"));
                audio_start2.click();
                WebElement fix = driver.findElement(By.className("TopNavBtn__icon"));
                fix.click();
                TimeUnit.MILLISECONDS.sleep(3000);;
                fix.click();
                TimeUnit.MILLISECONDS.sleep(3000);
                WebElement rep = driver.findElement(By.className("top_audio_player_title"));
                rep.click();
                TimeUnit.MILLISECONDS.sleep(3000);
                WebElement rep2 = driver.findElement(By.className("audio_page_player_icon--repeat_outline_24"));
                rep2.click();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
