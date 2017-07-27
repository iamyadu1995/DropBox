/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ashu
 */
public class UploadHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        ServletInputStream sis;
        sis = request.getInputStream();
        
        String filename = "b:\\upload_"+System.currentTimeMillis()+".txt";
        FileOutputStream fos;
        fos = new FileOutputStream(filename);
        byte arr[] = new byte[1024];
        int n;
        while((n = sis.read(arr))!=-1)
        {
            fos.write(arr,0,n);
        }
        fos.flush();
        fos.close();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body background=images/back.png>");
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("titleBar.jsp");
        rd.include(request,response);
        try
        {
            UploadParser parser = new UploadParser(filename);
            String name = parser.getFieldValue("txtname");
            String targetFile = parser.extractFile("fileUpload");
            parser.close();
            out.println("<h1>UPLOAD SUCCESS</h1>");
            out.println("<h1>USER : "+ name +"</h1>");
            out.println("<h1>FILE SAVED @ Location : "+ targetFile +"</h1>");
        }
             catch(Exception ex)
        {
            out.println("<h1>UPLOAD FAILED</h1>");
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
