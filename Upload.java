
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Upload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("titleBar.jsp");
       // rd = request.getRequestDispatcher("navBarMember.jsp");
        rd.include(request,response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body background=images/back.png>");
        out.println("<h1>UPLOAD</h1>");
        out.println("<img src = \"images/Upload.png\">");
        out.println("<form name = \"f\" method = \"post\" action = \"UploadHandler\" enctype = \"multipart/form-data\" >");
        out.println("<b>Name :</b> ");
        out.println("<input type = \"text\" name = \"txtname\"/> ");
        out.println("<br>");
        out.println("<br>");
        out.println("<b>Upload : </b>");
        out.println("<input type = \"file\" name = \"fileUpload\" />");
        out.println("<br>");
        out.println("<br>");
        out.println("<input type = \"submit\" name = \"bttnSubmit\" value = \"UPLOAD\" /> ");
        out.println("</form>");
         out.println("<h2><a href = \"userHome.jsp\"> DASHBOARD</a></h2>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
