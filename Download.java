
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File file = new File("b:\\share\\");
        String p = "b:\\share\\";
        String arr[] = new String[100];
        arr = file.list();
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("titleBar.jsp");
        rd.include(request,response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body link=\"silver\" background=images/back.png>");
        out.println("<img src = \"images/Download.png\">");
        for(int i=0;i<arr.length;i++)
        {
            out.println("<br>");
            out.println("<h3><a color:red; href =\"DownloadHandler?name="+p+""+arr[i]+"\">"+arr[i]+"</a></h3>");
            out.println("<br>");
        }
        out.println("<h2><a href = \"userHome.jsp\"> DASHBOARD</a></h2>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        
    }
     

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
