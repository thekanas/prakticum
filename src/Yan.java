import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Yan {

        public static void main(String[] args) {
            System.out.println("Программа бухгалтерия");
            System.out.println(readFileMonthdataOrNull("G:\\java+\\yan\\data\\m.202201.csv"));


            /*while (true) {
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

            }*/

        }

        public static void printMenu() {
            System.out.println("..........................");
            System.out.println("Введите команду:");
            System.out.println("1 - считать все месячные отчеты;");
            System.out.println("2 - считать годовой отчет;");
            System.out.println("3 - сверить отчеты: шагов в день;");
            System.out.println("4 - вывести информацию о месячных отчетах;");
            System.out.println("5 - вывести информацию о годовом отчете;");
            System.out.println("0 - выход из программы;");
            System.out.println("..........................");
        }

        private static String readFileMonthdataOrNull (String path) {
            try {
                Path filePath= Path.of(path);

                return Files.readString(filePath);
            } catch (IOException e) {
                System.out.println("Не возможно прочитать файл с месячным отчетом.");
               return null;
            }
        }


    }

