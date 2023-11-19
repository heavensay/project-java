package testexcel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

/**
 * apache poi 操作excel
 *
 * @author banana
 */
public class TestExcel {

    @Test
    public void test1() throws Exception {
        //创建excel工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建第一个sheet（页），命名为 new sheet
        Sheet sheet = workbook.createSheet("new sheet");
        //Row 行
        //Cell 方格
        // Row 和 Cell 都是从0开始计数的

        // 创建一行，在页sheet上
        Row row = sheet.createRow((short) 0);
        // 在row行上创建一个方格
        Cell cell = row.createCell(0);
        //设置方格的显示
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("This is a string 速度反馈链接");
        row.createCell(3).setCellValue(true);

        //创建一个文件 命名为workbook.xls
        FileOutputStream fileOut = new FileOutputStream("bin/testexcel/test1.xls");
        // 把上面创建的工作簿输出到文件中
        workbook.write(fileOut);
        //关闭输出流
        fileOut.close();
    }

    @Test
    public void test2() throws Exception {
        byte[] bb = new byte[]{48, 49, 50};
        Book book = new Book(1, "jsp", "leno", 300.33f, "1234567",
                "清华出版社", bb);
        //创建excel工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建第一个sheet（页），命名为 new sheet
        Sheet sheet = workbook.createSheet("new sheet");
        //Row 行
        //Cell 方格
        // Row 和 Cell 都是从0开始计数的

        // 创建一行，在页sheet上
        Row row = sheet.createRow((short) 0);
        Cell titlecell = row.createCell(0);
        titlecell.setCellValue("主键");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("价格");
        row.createCell(3).setCellValue("前言");

        Row row2 = sheet.createRow((short) 2);
        // 在row行上创建一个方格
        Cell cell = row2.createCell(0);
        //设置方格的显示
        cell.setCellValue(book.getBookId());
        // Or do it on one line.
        row2.createCell(1).setCellValue(book.getName());
        row2.createCell(2).setCellValue(book.getPrice());

        //创建一个文件 命名为workbook.xls
        FileOutputStream fileOut = new FileOutputStream("bin/testexcel/test2.xls");
        // 把上面创建的工作簿输出到文件中
        workbook.write(fileOut);
        //关闭输出流
        fileOut.close();
    }
}
