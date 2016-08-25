/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.PoolManager;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "studentProgress", urlPatterns = {"/studentProgress"})
public class studentProgress extends HttpServlet {

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
            
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            
            Session s = PoolManager.getSessionFactory().openSession();
            Criteria c = s.createCriteria(DB.StudentPreference.class);
            c.add(Restrictions.eq("studid", username));
            
            List<DB.StudentPreference> l = c.list();
            
            if (l.size() == 1) {
                
                int pref = 0, state = -1;
                for (DB.StudentPreference l1 : l) {
                    for (int i = 1; i < 5; i++) {
                        switch (i) {
                            case 1:
                                pref = l1.getCompanyByPref1().getCompanyid();
                                state = l1.getStatusByStatus1().getIdstatus();
                                break;
                            case 2:
                                pref = l1.getCompanyByPref2().getCompanyid();
                                state = l1.getStatusByStatus2().getIdstatus();
                                break;
                            case 3:
                                pref = l1.getCompanyByPref3().getCompanyid();
                                state = l1.getStatusByStatus3().getIdstatus();
                                break;
                            case 4:
                                pref = l1.getCompanyByPref4().getCompanyid();
                                state = l1.getStatusByStatus4().getIdstatus();
                                break;
                        }
                        a.write("<tr>"
                                + "<td>" + i + "</td>"
                                + "<td>" + s.load(DB.Company.class, pref).getName() + "</td>"
                                + "<td>" + s.load(DB.Status.class, state).getStatus() + "</td>"
                                + "</tr>");
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
