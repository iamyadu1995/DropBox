
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

public class DownloadHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String filename;
        filename = request.getParameter("name");
       File f = new File(filename);
        if(f.exists())
        {
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition","attachement;filename=a.zip");
            ServletOutputStream sos;
            sos = response.getOutputStream();
            ZipOutputStream zos;
            zos = new ZipOutputStream(sos);
            ZipEntry entry = new ZipEntry(f.getName());
            zos.putNextEntry(entry);
            FileInputStream fin = new FileInputStream(f);
            byte data[] = new byte[1024];
            int n;
            while((n = fin.read(data))!=-1)
            {
                zos.write(data,0,n);
            }
            fin.close();
            zos.closeEntry();
            zos.flush();
            zos.close();
        }
        
        else
        {
           response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<body background=images/back.png>");
            out.println("<h1>FILE NOT FOUND</h1>");
            out.println("<h2>Ensure that " + f.getAbsolutePath() + " exists for the download to succeed </h2>" );
            out.println("<h2><a href = \"userHome.jsp\"> DASHBOARD </a></h2>");
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close(); 
        }
    }
     
  
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
