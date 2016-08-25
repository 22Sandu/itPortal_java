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
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "loadStudent", urlPatterns = {"/loadStudent"})
public class loadStudent extends HttpServlet {

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

        String order = request.getParameter("order");
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));

        try {
            PrintWriter a = response.getWriter();

            Session s = PoolManager.getSessionFactory().openSession();
            Criteria c = s.createCriteria(DB.StudentPreference.class);

            List<DB.StudentPreference> l = c.list();

            String pref1, pref2, pref3, pref4;
            int k = 1;

            int start = 0, end = l.size();

            if (limit != 0) {
                start = page * limit;
                end = start + limit;

                if (end > l.size()) {
                    end = l.size();
                }
            }

            for (int j = start; j < end; j++) {

                try {
                    pref1 = l.get(j).getCompanyByPref1().getName();
                    pref2 = l.get(j).getCompanyByPref2().getName();
                    pref3 = l.get(j).getCompanyByPref3().getName();
                    pref4 = l.get(j).getCompanyByPref4().getName();

                    a.append("<tr>"
                            + "<td>" + (j + 1) + "</td>"
                            + "<td>" + l.get(j).getStudid() + "</td>"
                            + "<td>" + pref1 + "</td>"
                            + "<td>" + pref2 + "</td>"
                            + "<td>" + pref3 + "</td>"
                            + "<td>" + pref4 + "</td>"
                            + "</tr>");

                } catch (IndexOutOfBoundsException e) {
                    a.append("<tr>"
                            + "<td>" + (j + 1) + "</td>"
                            + "<td>" + l.get(j).getStudid() + "</td>"
                            + "<td colspan='4'>A referenced company is deleted from the database</td></tr>");
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
