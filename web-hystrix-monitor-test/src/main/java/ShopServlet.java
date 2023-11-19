import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopServlet extends HttpServlet {

    static int num = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        CommandFallback cmdReq = new CommandFallback(num);
        String result = cmdReq.execute();
        resp.getWriter().write(result);
        num++;
    }
}
