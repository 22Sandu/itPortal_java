/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.Advisee;
import DB.PoolManager;
import DB.Staff;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "saveSelectedList", urlPatterns = {"/saveSelectedList"})
public class saveSelectedList extends HttpServlet {

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
            ArrayList students = staffStudentStatusList.selectedStudents;

            if (students.size() > 0) {
                String[] stud = new String[students.size()];
                students.toArray(stud);

                HttpSession session = request.getSession();
                String staff = (String) session.getAttribute("username");

                Session s = PoolManager.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();

                Criteria cc = s.createCriteria(DB.Staff.class);
                cc.add(Restrictions.eq("username", staff));
                cc.setMaxResults(1);

                List<DB.Staff> ll = cc.list();
                for (DB.Staff ll1 : ll) {

                    Criteria c = s.createCriteria(DB.Advisee.class);
                    c.add(Restrictions.eq("staff", s.load(DB.Staff.class, ll1.getStaffid())));

                    List<DB.Advisee> l = c.list();

                    for (DB.Advisee ad2 : l) {
                        s.delete(s.load(DB.Advisee.class, ad2.getId()));
                    }

                    for (String st : stud) {
                        DB.Advisee ad = new Advisee();
                        ad.setStaff(ll1);
                        ad.setStudentPreference(s.load(DB.StudentPreference.class, st));
                        s.save(ad);
                    }
                }

                t.commit();
                response.getWriter().write("1");
            }

        } catch (Exception ex) {
            System.out.println(ex);
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
