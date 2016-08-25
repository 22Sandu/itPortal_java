/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.PoolManager;
import DB.StudentPreference;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "AdminAddStudent", urlPatterns = {"/AdminAddStudent"})
public class AdminAddStudent extends HttpServlet {

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

            String student = request.getParameter("stud");
            int pref = Integer.parseInt(request.getParameter("pref"));

            Session s = PoolManager.getSessionFactory().openSession();
            Criteria c = s.createCriteria(DB.StudentPreference.class);
            c.add(Restrictions.eq("studid", student));
            c.setMaxResults(1);

            List<DB.StudentPreference> l = c.list();

            for (StudentPreference l1 : l) {
                int prefid = 0;
                switch (pref) {
                    case 1:
                        prefid = l1.getStatusByStatus1().getIdstatus();
                        break;
                    case 2:
                        prefid = l1.getStatusByStatus2().getIdstatus();
                        break;
                    case 3:
                        prefid = l1.getStatusByStatus3().getIdstatus();
                        break;
                    case 4:
                        prefid = l1.getStatusByStatus4().getIdstatus();
                        break;
                }

                if (prefid == 1) {
                    a.write("2");
                } else {
                    Transaction t = s.beginTransaction();

                    StudentPreference sp = s.load(DB.StudentPreference.class, student);

                    switch (pref) {
                        case 1:
                            sp.setStatusByStatus1(s.load(DB.Status.class, 1));
                            break;
                        case 2:
                            sp.setStatusByStatus2(s.load(DB.Status.class, 1));
                            break;
                        case 3:
                            sp.setStatusByStatus3(s.load(DB.Status.class, 1));
                            break;
                        case 4:
                            sp.setStatusByStatus4(s.load(DB.Status.class, 1));
                            break;
                    }

                    s.update(sp);
                    t.commit();
                    a.write("1");
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
