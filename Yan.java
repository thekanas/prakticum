import java.io.IOException;
import java.util.ArrayList;

import java.util.InputMismatchException;

import java.util.Scanner;

import java.util.Set;


public class Yan {
    public static void main(String[] args) {

        System.out.println("Программа StepTracker");
        StepTracker stepTracker = new StepTracker();

        MonthData mon1 = stepTracker.monthData.get(1);
        Set<Integer> keys1 = mon1.monthDay.keySet();
        System.out.println("Ключи: " + keys1);
        ArrayList<Integer> values1 = new ArrayList<Integer>(mon1.monthDay.values());
        System.out.println("Значения: " + values1);

        Scanner scanner = new Scanner(System.in);
        printMenu();


            while (true) {
                try {
                int number = scanner.nextInt();
                if (number == 1) {
                    System.out.println("Ввод колличества шагов");
                    System.out.println("Введите номер месяца");
                    int monht = scanner.nextInt();
                    System.out.println("Введите номер дня");
                    int day = scanner.nextInt();
                    System.out.println("Введите колличество шагов");
                    int step = scanner.nextInt();
                    stepTracker.stepTrackerSet(monht, day, step);

                    /*MonthData mon = stepTracker.monthData.get(monht);
                    Set<Integer> keys = mon.monthDay.keySet();
                    System.out.println("Ключи: " + keys);

                    ArrayList<Integer> values = new ArrayList<Integer>(mon.monthDay.values());
                    System.out.println("Значения: " + values);*/

                    System.out.println("Сохранено");
                } else if (number == 2) {
                    System.out.println("Просмотр статистики.");
                    System.out.println("Введите номер месяца за который нужна статистика:");
                    int monht = scanner.nextInt();
                    stepTracker.stepTrackerStatForMonth(monht);
                } else if (number == 3) {
                    System.out.println("Ввод цели шагов в день");
                    StepTracker.targetSet(scanner.nextInt());
                } else if (number == 0) {
                    System.out.println("Выход из программы");
                    break;
                } else {
                    System.out.println("Такой команды нету");
                }

                printMenu();

            } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Введена некорректная команда.");

            }

            }

    }

        public static void printMenu() {
            System.out.println("..........................");
            System.out.println("Введите команду:");
            System.out.println("1 - ввести колличество пройденных шагов;");
            System.out.println("2 - посмотреть статистику;");
            System.out.println("3 - изменить цель по колличеству шагов в день, текущая цель: "+ StepTracker.target + " шагов в день;");
            System.out.println("0 - выход из программы;");
		System.out.println("0 - выход из программы;");
            System.out.println("..........................");
        }


}
