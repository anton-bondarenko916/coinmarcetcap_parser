import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParserCoinMap parser = new ParserCoinMap();
        parser.saveTableToList(parser.getTableFromPage());
        try {
            Main.mainMenu(parser);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            parser.closeBrowser();
        }

    }

    public static void mainMenu(ParserCoinMap parserCoinMap) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        int x = 0;
        String s = "";

        while (!"3".equals(s)){
            System.out.println("1. Отобразить все 25 валют 1");
            System.out.println("2. Поиск по названию валюты 2");
            System.out.println("3. Для выхода из приложения введите 3");
            s = scanner.next();

            try {
                x = Integer.parseInt(s);
            } catch (NumberFormatException e){
                System.out.println("Неверный ввод");
            }

            switch (x){
                case 1:
                    // вызов метода 1
                    parserCoinMap.printTable();
                    System.out.println("Нажмите Enter для продолжения");
                    s = scanner.next();
                    //Runtime.getRuntime().exec("cls");
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
                case 2:
                    // вызов метода 2
                    System.out.println("Введите название:");
                    s = scanner.next();
                    parserCoinMap.binarySearch(s);
                    System.out.println("Нажмите Enter для продолжения");
                    s = scanner.next();
                    Runtime.getRuntime().exec("cls");
                    break;
                case 3:
                    // вызов метода 3
                    return;
            }
        }
    }
}
