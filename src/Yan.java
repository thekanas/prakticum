import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Yan {

        public static void main(String[] args) {
            System.out.println("��������� �����������");
            MonthReport monthReport = new MonthReport();
            YearReport yearReport = new YearReport();
            String path = "G:\\java+\\yan\\data\\";






            Scanner scanner =new Scanner(System.in);
            printMenu();
            while (true) {
                try {
                    int number = scanner.nextInt();
                    if (number == 1) {
                        System.out.println("�������� �������� ������...");
                        loadData (monthReport, path);
                        System.out.println("������.");
                    } else if (number == 2) {
                        System.out.println("�������� ������� �����...");
                        loadDataYear (yearReport, path);
                        System.out.println("������.");

                    } else if (number == 3) {
                        System.out.println("������ �����...");
                        collation(monthReport, yearReport);
                    } else if (number == 4) {
                        System.out.println("���������� �� �������� �������:");
                        monthReport.infaByMonth();
                    } else if (number == 5) {
                        System.out.println("���������� �� �������� ������:");
                        yearReport.infaByYear();
                    } else if (number == 0) {
                        System.out.println("����� �� ���������");
                        break;
                    } else {
                        System.out.println("����� ������� ����");
                    }

                    printMenu();

                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("������� ������������ �������.");

                }

            }

        }
            public static void printMenu () {
                System.out.println("..........................");
                System.out.println("������� �������:");
                System.out.println("1 - ������� ��� �������� ������;");
                System.out.println("2 - ������� ������� �����;");
                System.out.println("3 - ������� ������;");
                System.out.println("4 - ������� ���������� � �������� �������;");
                System.out.println("5 - ������� ���������� � ������� ������;");
                System.out.println("0 - ����� �� ���������;");
                System.out.println("..........................");
            }

        private static String readFileDataOrNull (String path) {
            try {
                return Files.readString(Path.of(path));
            } catch (IOException e) {
                System.out.println("�� �������� ��������� ���� � �������.");
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
                System.out.println("������� �������� ������");
                return;
            }


        for (int i = 0; i<yearReport.dataYear.size() ; i++) {
            if (yearReport.dataYear.get(i).amountProfit!=monthReport.monthProfit(i+1)) {
                System.out.println("������ � ������ �������. ������ � ������ "+yearReport.dataYear.get(i).numberMonth);
                return;
            }
            if (yearReport.dataYear.get(i).amountWaste!=monthReport.monthWaste(i+1)) {
                System.out.println("������ � ������ �������. ������ � ������ "+yearReport.dataYear.get(i).numberMonth);
                return;
            }





        }
        System.out.println("������ �� ����������");
    }


}

