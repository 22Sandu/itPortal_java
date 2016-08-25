/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.Advisee;
import DB.PoolManager;
import java.io.IOException;
import java.io.PrintWriter;
import static java.time.temporal.TemporalAdjusters.next;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "StaffCheckBoxes", urlPatterns = {"/StaffCheckBoxes"})
public class StaffCheckBoxes extends HttpServlet {

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

        try {
            PrintWriter a = response.getWriter();
            String staff = request.getSession().getAttribute("username").toString();

            Session s = PoolManager.getSessionFactory().openSession();

            Criteria cc = s.createCriteria(DB.Staff.class);
            cc.add(Restrictions.eq("username", staff));
            cc.setMaxResults(1);

            List<DB.Staff> ll = cc.list();
            
            for (DB.Staff ll1 : ll) {
                Criteria c = s.createCriteria(DB.Advisee.class);
                c.add(Restrictions.eq("staff", s.load(DB.Staff.class, ll1.getStaffid())));

                List<DB.Advisee> l = c.list();
                if (l.size() > 0) {
                    for (Advisee l1 : l) {
                        a.append(l1.getStudentPreference().getStudid() + ",");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
