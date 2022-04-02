import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Yan {

        public static void main(String[] args) {
            System.out.println("Программа бухгалтерия");
            MonthReport monthReport = new MonthReport();
            YearReport yearReport = new YearReport();
            String path = "G:\\java+\\yan\\data\\";






            Scanner scanner =new Scanner(System.in);
            printMenu();
            while (true) {
                try {
                    int number = scanner.nextInt();
                    if (number == 1) {
                        System.out.println("Считываю месячные отчеты...");
                        loadData (monthReport, path);
                        System.out.println("Готово.");
                    } else if (number == 2) {
                        System.out.println("Считываю годовой отчет...");
                        loadDataYear (yearReport, path);
                        System.out.println("Готово.");

                    } else if (number == 3) {
                        System.out.println("Сверяю очеты...");
                        collation(monthReport, yearReport);
                    } else if (number == 4) {
                        System.out.println("Информация по месячным отчетам:");
                        monthReport.infaByMonth();
                    } else if (number == 5) {
                        System.out.println("Информация по годовому отчету:");
                        yearReport.infaByYear();
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
            public static void printMenu () {
                System.out.println("..........................");
                System.out.println("Введите команду:");
                System.out.println("1 - считать все месячные отчеты;");
                System.out.println("2 - считать годовой отчет;");
                System.out.println("3 - сверить отчеты;");
                System.out.println("4 - вывести информацию о месячных отчетах;");
                System.out.println("5 - вывести информацию о годовом отчете;");
                System.out.println("0 - выход из программы;");
                System.out.println("..........................");
            }

        private static String readFileDataOrNull (String path) {
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                System.out.println("Не возможно прочитать файл с отчетом.");
               return null;
            }
        }

        private static ArrayList<MonthData> parseFileContentMonth (String content) {
            String[]  strings = content.split("\\r\\n");
            ArrayList<MonthData> monthData = new ArrayList<>();
            for (int i=1; i<=3; i++) {
                String[] line = strings[i].split(", ");
                 monthData.add(new MonthData(line[0],
                                            Boolean.parseBoolean(line[1]),
                                            Float.parseFloat(line[2]),
                                            Integer.parseInt(line[3])));

            }
            return monthData;
        }

        private static void loadData (MonthReport monthReport, String str) {

            String pach;
            for (int i = 1; i <=3 ; i++) {
                pach = str + "m.20220" + i + ".csv";
                monthReport.addMonth (i, parseFileContentMonth(readFileDataOrNull(pach)));
            }
            MonthReport.is_load_month = true;
        }

    private static ArrayList<YearData> parseFileContentYear (String content) {
        String[]  strings = content.split("\\r\\n");
        ArrayList<YearData> yearData = new ArrayList<>();
        for (int i=1; i<strings.length; i=i+2) {
            String[] line1 = strings[i].split(", ");
            String[] line2 = strings[i+1].split(", ");
            float profit = -1;
            float waste = -1;
            if (Boolean.parseBoolean(line1[2]))
                waste = Float.parseFloat(line1[1]);
            else profit = Float.parseFloat(line1[1]);
            if (Boolean.parseBoolean(line2[2]))
                waste = Float.parseFloat(line2[1]);
            else profit = Float.parseFloat(line2[1]);
            yearData.add(new YearData(
                    Integer.parseInt(line1[0]),
                    waste,
                    profit));

        }
        return yearData;
    }
    private static void loadDataYear (YearReport yearReport, String str) {

        String pach = str + "y.2022.csv";
        yearReport.dataYear =  parseFileContentYear(readFileDataOrNull(pach));
        YearReport.is_load_year = true;
    }

    public static void collation(MonthReport monthReport, YearReport yearReport) {
            if (!YearReport.is_load_year || !MonthReport.is_load_month) {
                System.out.println("Сначало считайте отчеты");
                return;
            }


        for (int i = 0; i<yearReport.dataYear.size() ; i++) {
            if (yearReport.dataYear.get(i).amountProfit!=monthReport.monthProfit(i+1)) {
                System.out.println("Ошибка в сверке отчетов. Ошибка в месяце "+yearReport.dataYear.get(i).numberMonth);
                return;
            }
            if (yearReport.dataYear.get(i).amountWaste!=monthReport.monthWaste(i+1)) {
                System.out.println("Ошибка в сверке отчетов. Ошибка в месяце "+yearReport.dataYear.get(i).numberMonth);
                return;
            }





        }
        System.out.println("Ошибок не обнаружено");
    }


}

