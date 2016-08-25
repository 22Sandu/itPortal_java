/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chalaka
 */

public class EmailSendingServlet extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
    
    @Override
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        String recipient = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
 
      
    PrintWriter out = response.getWriter();
    
        try {
            EmailUtilaty.sendEmail(host, port, user, pass, recipient, subject,
                    content);
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Emiail send successfully!');");
                                out.println("location='homeAdmin.jsp';");
                                out.println("</script>");
            
        } catch (Exception ex) {
            ex.printStackTrace();
                     out.println("<script type=\"text/javascript\">");
                     out.println("alert('Unable to send the email !!!');");
                     out.println("location='homeAdmin.jsp';");
                     out.println("</script>");
        } 
    }
}
