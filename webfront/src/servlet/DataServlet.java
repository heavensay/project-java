package servlet;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.contains("queryResourceTree")) {
            getJsonTree(request, response);
        }
    }

    public void getJsonTree(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        System.out.println("333");
        //{"id":null,"text":"用户权限","state":"open","checked":false,"attributes":null,
        //"children":[{"id":null,"text":"用户插入","state":"open","checked":false,"attributes":null,
        //"children":[{"id":null,"text":"用户插入批量","state":"open","checked":false,"attributes":null,
        //	"children":[]}]},{"id":null,"text":"用户查询","state":"open","checked":false,
        //	"attributes":null,"children":[]}]}
        String jsonTree = "[{\"id\":null,\"text\":\"用户权限\",\"state\":\"open\",\"checked\":false,\"attributes\":null," +
                "\"children\":[{\"id\":null,\"text\":\"用户插入\",\"state\":\"open\",\"checked\":false,\"attributes\":null," +
                "\"children\":[{\"id\":null,\"text\":\"用户插入批量\",\"state\":\"open\",\"checked\":false," +
                "\"attributes\":null,\"children\":[]}]},{\"id\":null,\"text\":\"用户查询\"," +
                "\"state\":\"open\",\"checked\":false,\"attributes\":null,\"children\":[]}]}]";
        response.setCharacterEncoding("GBK");
        response.setContentType("text/json");
        response.getWriter().write(jsonTree);
    }

}
