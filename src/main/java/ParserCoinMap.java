import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ParserCoinMap {

    private final String URL = "https://coinmarketcap.com/";
    private boolean isSorted = false;
    private String[][] list = new String[25][3];
    private String[][] sortedList;
    private WebDriver driver;

    ParserCoinMap(){
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/chromedriver.exe");
    }

    public WebElement getTableFromPage(){
        driver = new ChromeDriver();
        driver.get(URL);
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, 1600);");
        WebElement table = driver.findElement(By.tagName("tbody"));
        return table;
    }

    public void saveTableToList(WebElement table){
        String[] data = table.getText().split("\n");
        int iterator = 1;
        for (int i = 0; i < 25; i++) {
            list[i][0] = data[iterator];
            list[i][1] = data[iterator + 2];
            list[i][2] = data[iterator + 4];
            iterator += 9;
        }
        sortedList = list.clone();
    }

    private void sortList(){
        if (!isSorted){
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 24 - i; j++) {
                    if (sortedList[j][0].compareTo(sortedList[j+1][0]) > 0){
                        String[] temp = sortedList[j];
                        sortedList[j] = sortedList[j + 1];
                        sortedList[j + 1] = temp;
                    }
                }
            }
        }
    }

    public void printTable(){
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(list[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void binarySearch(String key){
        sortList();
        int i = 0;
        int j = sortedList.length - 1;
        while (i < j){
            int m = (int)((i + j) / 2);
            if (key.compareTo(sortedList[m][0]) > 0)
                i = m + 1;
            else
                j = m;
        }
        if (sortedList[j][0].equals(key)){
            System.out.print(sortedList[j][0] + "\t");
            System.out.print(sortedList[j][1] + "\t");
            System.out.print(sortedList[j][2] + "\t");
            System.out.println();
        }
        else
            System.out.println("Not found");
    }

    public void closeBrowser(){
        driver.close();
    }

}
