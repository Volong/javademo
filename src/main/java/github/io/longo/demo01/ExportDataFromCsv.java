package github.io.longo.demo01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportDataFromCsv {

    public static void main(String[] args)
    {
        File csv = new File("C:\\Users\\Volong\\Desktop\\孕管专家数据.csv");  // CSV文件路径
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
                List<String> allString = new ArrayList<>();
                while ((line = br.readLine()) != null)  //读取到的内容给line变量
                {
                    everyLine = line;
                    System.out.println(everyLine);
                    allString.add(everyLine);
                }
                System.out.println("csv表格中所有行数："+allString.size());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
