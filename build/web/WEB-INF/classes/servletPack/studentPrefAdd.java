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
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "studentPrefAdd", urlPatterns = {"/studentPrefAdd"})
public class studentPrefAdd extends HttpServlet {

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

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        try {
            int name1 = Integer.parseInt(request.getParameter("name1"));
            int name2 = Integer.parseInt(request.getParameter("name2"));
            int name3 = Integer.parseInt(request.getParameter("name3"));
            int name4 = Integer.parseInt(request.getParameter("name4"));

            PrintWriter a = response.getWriter();

            Session s = PoolManager.getSessionFactory().openSession();
            Criteria c = s.createCriteria(DB.StudentPreference.class);
            c.add(Restrictions.eq("studid", username));
            c.setMaxResults(1);
            
            List<DB.StudentPreference> l = c.list();

            if (l.size() > 0) {
                for (StudentPreference l1 : l) {
                    Transaction t = s.beginTransaction();
                    DB.StudentPreference sp = s.load(DB.StudentPreference.class, l1.getStudid());

                    sp.setStudid(username);
                    sp.setCompanyByPref1(s.load(DB.Company.class, name1));
                    sp.setCompanyByPref2(s.load(DB.Company.class, name2));
                    sp.setCompanyByPref3(s.load(DB.Company.class, name3));
                    sp.setCompanyByPref4(s.load(DB.Company.class, name4));
                    sp.setStatusByStatus1(s.load(DB.Status.class, 1));
                    sp.setStatusByStatus2(s.load(DB.Status.class, 1));
                    sp.setStatusByStatus3(s.load(DB.Status.class, 1));
                    sp.setStatusByStatus4(s.load(DB.Status.class, 1));

                    s.update(sp);
                    t.commit();
                    a.write("0");
                }
            } else if (name1 == name2 || name1 == name3 || name1 == name4 || name3 == name2 || name4 == name2 || name3 == name4 || name1 == name4) {
                a.write("2");
            } else {
                Transaction t = s.beginTransaction();
                DB.StudentPreference sp = new StudentPreference();

                sp.setStudid(username);
                sp.setCompanyByPref1(s.load(DB.Company.class, name1));
                sp.setCompanyByPref2(s.load(DB.Company.class, name2));
                sp.setCompanyByPref3(s.load(DB.Company.class, name3));
                sp.setCompanyByPref4(s.load(DB.Company.class, name4));
                sp.setStatusByStatus1(s.load(DB.Status.class, 1));
                sp.setStatusByStatus2(s.load(DB.Status.class, 1));
                sp.setStatusByStatus3(s.load(DB.Status.class, 1));
                sp.setStatusByStatus4(s.load(DB.Status.class, 1));

                s.save(sp);
                t.commit();
                a.write("0");
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
