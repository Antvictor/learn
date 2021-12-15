package yangchao.study.excel;

import com.alibaba.fastjson.JSON;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import yangchao.study.entity.Test1;
import yangchao.study.entity.Test2;
import yangchao.study.entity.Test3;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelUtils {

    public static List<Test1> readExcel(File file) {
        String fileName = file.getName();
        Workbook workbook = null;
        List<Test1> test1s = new ArrayList<>();
        // 判断格式
        try {
            if (fileName.endsWith("xlsx")) {
                // 2003版
                workbook = new HSSFWorkbook(new FileInputStream(file));
            } else if (fileName.endsWith("xls")) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
            }else {
                System.out.println("文件格式错误");
            }

            Sheet sheet = workbook.getSheet("sheet1");
            for (Row r : sheet) {
                // 第一行是示例
                if (r.getRowNum() == 0) {
                    continue;
                }
                // 从第二行开始读
                Test1 test1 = new Test1();
                Cell cell0 = r.getCell(0);
                Cell cell1 = r.getCell(1);
                test1.setId(Integer.parseInt(getFromCell(cell0)));
                test1.setName(getFromCell(cell1));
                test1s.add(test1);
            }
            return test1s;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("解析错误");
        }
        return test1s;
    }

    public static String getFromCell(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            DecimalFormat decimalFormat = new DecimalFormat("0");
            return decimalFormat.format(cell.getNumericCellValue());
        }
        if (cell.getCellType() == CellType.STRING) {
            return String.valueOf(cell.getStringCellValue());
        }
        if (cell.getCellType() == CellType.FORMULA) {
            return String.valueOf(cell.getCellFormula());
        }
        if (cell.getCellType() == CellType.BLANK) {
            return "";
        }
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        return "";
    }
    @Test
    public void byteTest() {
        File file = new File("test2.xls");
        BufferedOutputStream bufferedOutputStream = null;
        try {

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = IOUtils.toByteArray(new FileInputStream("/Users/exccedy/job/20200108/test.txt"));
//            System.out.println(bytes);
//            System.out.println(JSON.toJSONString(bytes));
//            System.out.println(new String(bytes));
            bufferedOutputStream.write(bytes);
            BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/exccedy/job/20200108/test.txt")));
//            reader.readLine();
            String test = null;
            while ((test = reader.readLine()) != null){
                System.out.println(test);
                Test1 test1 = JSON.parseObject(JSON.toJSONString(test),Test1.class);
                System.out.println(JSON.toJSONString(test1));
            }
            bufferedOutputStream.close();

            System.out.println(JSON.toJSONString(readExcel(file)));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testT () {
        /*Test1 test1 = new Test1();
        test1.setName("hh");
        test1.setId(1);

        Test2 test2 = JSON.parseObject(JSON.toJSONString(test1),Test2.class);
        test2.setFff("kkk");

        System.out.println(JSON.toJSONString(test2));*/

        List<Test3> test3s = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Test3 test3 = new Test3();
            List<Long> longList = new ArrayList<>();
            longList.add(1l);
            longList.add(2l);
            longList.add(3l);

            test3.setLongList(longList);
            test3s.add(test3);
        }
        test3s.forEach( t -> {
            t.getLongList().size();
        });

        System.out.println("数量：t = ");
    }

    @Test
    public void testStream() {
        Test1 test = new Test1() ,test1 = new Test1(),test2 = new Test1();
        test.setName("hh");
        test.setId(1);
        test1.setName("zz");
        test1.setId(1);
        test2.setName("hh");
        test2.setId(2);

        List<Test1> test1s = new ArrayList<>();
        test1s.add(test);
        test1s.add(test1);
        test1s.add(test2);

        List<Test1> dis = test1s.stream().distinct().collect(Collectors.toList());

        System.out.println(JSON.toJSONString(dis));

//        List<Test1> peek = dis.stream().peek(x -> x.getId()).collect(Collectors.toList());
//        System.out.println(JSON.toJSONString(peek));
        List<Test1> filter = dis.stream().filter(x -> x.getId() == 2).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(filter));
        System.out.println(JSON.toJSONString(dis));
        System.out.println(test.equals(test1));

        Map<Integer,String> tt1 = dis.stream().collect(Collectors.toMap(Test1::getId, Test1::getName,(key1, key2) -> key1));
        Map<Integer,String> tt2 =  dis.stream().collect(Collectors.toMap(Test1::getId, Test1::getName,(key1, key2) -> key2));
        Map<Integer,String> tt3 =  dis.stream().filter(x -> x.getId() == 1).collect(Collectors.toMap(Test1::getId, Test1::getName,(key1, key2) -> key2));

        System.out.println(JSON.toJSONString(tt1));
        System.out.println(JSON.toJSONString(tt2));
        System.out.println(JSON.toJSONString(tt3));
        System.out.println(filter.contains(test));
        System.out.println(filter.contains(test2.getId()));
        System.out.println(filter.contains(test2));
        //[{"id":1,"name":"hh"},{"id":1,"name":"zz"},{"id":2,"name":"hh"}]
        //[{"id":2,"name":"hh"}]
        //[{"id":1,"name":"hh"},{"id":1,"name":"zz"},{"id":2,"name":"hh"}]
        //false
        //{1:"hh",2:"hh"}
        //{1:"zz",2:"hh"}
        //{1:"zz"}
        //false
        //false
        //true

        List<Integer> su = test1s.stream().filter(x-> x.getName().equals("zz"))
                .map(Test1::getId).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(su));

        List<Integer> fa = test1s.stream().filter(x -> !su.contains(x.getId()))
                .map(Test1::getId)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(fa));
    }
}
