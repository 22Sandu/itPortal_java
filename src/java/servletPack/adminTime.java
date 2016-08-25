/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.PoolManager;
import DB.Stream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static javassist.CtMethod.ConstParameter.string;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "adminTime", urlPatterns = {"/adminTime"})
public class adminTime extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter a = response.getWriter();
        try {
            if (request.getParameter("inTime").equals("") || request.getParameter("outTime").equals("")) {
                a.write("1");
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date inTime = simpleDateFormat.parse(request.getParameter("inTime"));
                Date outTime = simpleDateFormat.parse(request.getParameter("outTime"));
                Date current = simpleDateFormat.parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                
                Session s = PoolManager.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                if (!(inTime.before(current) || outTime.before(current) || inTime.after(outTime))) {
                    DB.Stream st = new Stream();
                    
                    st.setInTime(inTime);
                    st.setOutTime(outTime);
                    
                    s.save(st);
                    t.commit();
                    
                    a.write("3");
                } else {
                    a.write("1");
                }
            }
            
        } catch (Exception e) {
            a.write("2");
        }
    }
    
    public Date convertDate(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        System.out.println("date : " + simpleDateFormat.format(date));
        return date;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
