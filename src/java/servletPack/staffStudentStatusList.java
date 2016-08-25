/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.PoolManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "staffStudentStatusList", urlPatterns = {"/staffStudentStatusList"})
public class staffStudentStatusList extends HttpServlet {

    public static ArrayList selectedStudents;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String al = request.getParameter("checkedBoxes");
        String[] checked = al.split(",");

        PrintWriter a = response.getWriter();
        try {
            selectedStudents = new ArrayList();

            if (!(checked.length == 0 || (checked[0].equals("null")))) {

                HttpSession session = request.getSession();
                Session s = PoolManager.getSessionFactory().openSession();
                Criteria c = s.createCriteria(DB.StudentPreference.class);
                c.addOrder(Order.asc("studid"));

                List<DB.StudentPreference> l = c.list();

                for (DB.StudentPreference key : l) {
                    for (String check : checked) {
                        if (check.equals(key.getStudid())) {

                            selectedStudents.add(key.getStudid());

                            a.append("<tr>"
                                    + "<td colspan=\"3\">" + key.getStudid() + "</td>"
                                    + "</tr>"
                                    + "<td>" + 1 + "</td>"
                                    + "<td>" + s.load(DB.Company.class, key.getCompanyByPref1().getCompanyid()).getName() + "</td>"
                                    + "<td>" + s.load(DB.Status.class, key.getStatusByStatus1().getIdstatus()).getStatus() + "</td>"
                                    + "</tr><tr>"
                                    + "<td>" + 2 + "</td>"
                                    + "<td>" + s.load(DB.Company.class, key.getCompanyByPref2().getCompanyid()).getName() + "</td>"
                                    + "<td>" + s.load(DB.Status.class, key.getStatusByStatus2().getIdstatus()).getStatus() + "</td>"
                                    + "</tr><tr>"
                                    + "<td>" + 3 + "</td>"
                                    + "<td>" + s.load(DB.Company.class, key.getCompanyByPref3().getCompanyid()).getName() + "</td>"
                                    + "<td>" + s.load(DB.Status.class, key.getStatusByStatus3().getIdstatus()).getStatus() + "</td>"
                                    + "</tr><tr>"
                                    + "<td>" + 4 + "</td>"
                                    + "<td>" + s.load(DB.Company.class, key.getCompanyByPref4().getCompanyid()).getName() + "</td>"
                                    + "<td>" + s.load(DB.Status.class, key.getStatusByStatus4().getIdstatus()).getStatus() + "</td>"
                                    + "</tr>");
                            break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStatus(int maxStatus) {
        String status = "Preferences not selected yet";

        String val = "Preferences not selected";
        switch (maxStatus) {
            case 1:
                status = "Pending";
                break;
            case 2:
                status = "Short Listed";
                break;
            case 3:
                status = "CV sent";
                break;
            case 4:
                status = "Selected";
                break;
            case 5:
                status = "CV Viewed";
                break;
        }
        return status;
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
