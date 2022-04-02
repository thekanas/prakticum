import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport {
    public static boolean is_load_month = false;
    HashMap<Integer, ArrayList<MonthData>> data = new HashMap<>();

    public MonthReport() {
        for (int i = 1; i <= 3; i++) {
            data.put(i, new ArrayList<>());
        }
    }

    public void addMonth (int month, ArrayList<MonthData> monthData) {
        data.put(month, monthData);
    }

    public MonthData monthProfitMax (ArrayList<MonthData> arrMonthData) {
        MonthData maxMonthData = new MonthData("���������� ����� �� ������", false, 0, 0);
        for (MonthData monthData : arrMonthData ) {
                if (monthData.is_expense==false) {
                    if (monthData.quantity*monthData.sum_of_one > maxMonthData.quantity*maxMonthData.sum_of_one)
                    maxMonthData = monthData;
                }

        }
        return maxMonthData;
    }

    public MonthData monthWasteMax (ArrayList<MonthData> arrMonthData) {
        MonthData maxMonthData = new MonthData("����� �� �������", true, 0, 0);
        for (MonthData monthData : arrMonthData ) {
            if (monthData.is_expense==true) {
                if (monthData.quantity*monthData.sum_of_one > maxMonthData.quantity*maxMonthData.sum_of_one)
                    maxMonthData = monthData;
            }

        }
        return maxMonthData;
    }

    public float monthProfit (int month) {
        float monthProfit = 0;

            for (MonthData monthData : data.get(month) ) {
                if (monthData.is_expense==false) {
                    monthProfit += monthData.quantity*monthData.sum_of_one;
                }
            }

        return monthProfit;
    }

    public float monthWaste (int month) {
        float monthWaste = 0;

            for (MonthData monthData : data.get(month) ) {
                if (monthData.is_expense==true) {
                    monthWaste += monthData.quantity*monthData.sum_of_one;
                }
            }

        return monthWaste;
    }



    public void infaByMonth () {
        if (!MonthReport.is_load_month) {
            System.out.println("������� �������� ������");
            return;
        }
        for (int i = 1; i <=3 ; i++) {
            MonthData profit =  monthProfitMax(data.get(i));
            MonthData waste =  monthWasteMax(data.get(i));
            System.out.println("////////////////////////////////");
            System.out.println("����� ������ "+i);
            System.out.println("����� ���������� �����: "+profit.nameItem);
            System.out.println("�������: "+(profit.quantity*profit.sum_of_one));
            System.out.println("����� ������� �����: "+waste.nameItem);
            System.out.println("�����: "+(waste.quantity*waste.sum_of_one));
            System.out.println("////////////////////////////////");


        }
    }
}
