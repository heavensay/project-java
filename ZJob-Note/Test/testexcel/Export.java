package testexcel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leno
 * 使用servlet导出动态生成的excel文件，数据可以来源于数据库
 * 这样，浏览器客户端就可以访问该servlet得到一份用java代码动态生成的excel文件
 */
public class Export extends javax.servlet.http.HttpServlet {
    static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File(getServletContext().getRealPath("WEB-INF/book.jpg"));
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=test.xls");
        //测试图书
        ExportExcel<Book> ex = new ExportExcel<Book>();
        String[] headers = {"图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",
                "图书出版社", "封面图片"};
        List<Book> dataset = new ArrayList<Book>();
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            byte[] buf = new byte[bis.available()];
            while ((bis.read(buf)) != -1) {
                //将图片数据存放到缓冲数组中
            }
            dataset.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
                    "清华出版社", buf));
            dataset.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
                    "阳光出版社", buf));
            dataset.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
                    "清华出版社", buf));
            dataset.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
                    "清华出版社", buf));
            dataset.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
                    "汤春秀出版社", buf));
            OutputStream out = response.getOutputStream();
            ex.exportExcel(headers, dataset, out);
            out.close();
            System.out.println("excel导出成功！");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}