import java.util.ArrayList;


public class YearReport {
    public static boolean is_load_year = false;

    ArrayList<YearData> dataYear = new ArrayList<>();

    public YearReport() {

    }


    public void infaByYear () {
        if (!YearReport.is_load_year) {
            System.out.println("������� �������� ������");
            return;
        }
        float avgProfit = 0;
        float avgWaste = 0;

        for (int i = 0; i <dataYear.size() ; i++) {
            YearData dataYearTemp =  dataYear.get(i);
            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println("������� �� ������ ����� "+dataYearTemp.numberMonth);
            System.out.println(dataYearTemp.amountProfit-dataYearTemp.amountWaste);
            avgProfit += dataYearTemp.amountProfit;
            avgWaste += dataYearTemp.amountWaste;

        }
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("������� ������� �� ��� ������ "+(avgProfit/dataYear.size()));
        System.out.println("������� ������ �� ��� ������ "+(avgWaste/dataYear.size()));
        System.out.println("++++++++++++++++++++++++++++++");
    }


}
