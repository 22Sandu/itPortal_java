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
import org.hibernate.criterion.Restrictions;
import static servletPack.staffStudentStatusList.selectedStudents;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "AdminStudentList", urlPatterns = {"/AdminStudentList"})
public class AdminStudentList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String al = request.getParameter("checkedBoxes");
        String[] checked = al.split(",");

        PrintWriter a = response.getWriter();
        try {

            if (!(checked.length == 0 || (checked[0].equals("null")))) {

                Session s = PoolManager.getSessionFactory().openSession();
                Criteria c = s.createCriteria(DB.StudentPreference.class);

                List<DB.StudentPreference> l = c.list();
                

                for (String check : checked) {
                    int checkbox = Integer.parseInt(check);
                    a.append("<tr>"
                            + "<td colspan=\"6\">" + s.load(DB.Company.class, checkbox).getName() + "</td>"
                            + "</tr>");
                    int k = 1;
                    for (DB.StudentPreference key : l) {

                        int pref = 0;
                        String status = "";

                        if (checkbox == key.getCompanyByPref1().getCompanyid()) {
                            pref = 1;
                            status = key.getStatusByStatus1().getStatus();
                        } else if (checkbox == key.getCompanyByPref2().getCompanyid()) {
                            pref = 2;
                            status = key.getStatusByStatus2().getStatus();
                        } else if (checkbox == key.getCompanyByPref3().getCompanyid()) {
                            pref = 3;
                            status = key.getStatusByStatus3().getStatus();
                        } else if (checkbox == key.getCompanyByPref4().getCompanyid()) {
                            pref = 4;
                            status = key.getStatusByStatus4().getStatus();
                        }

                        if (pref != 0 && !status.equals("")) {
                            String id2 = key.getStudid()+"2";
                            a.append("<tr>"
                                    + "<td>" + k++ + "</td>"
                                    + "<td>" + key.getStudid() + "</td>"
                                    + "<td>" + pref + "</td>"
                                    + "<td>" + status + "</td>"
                                    + "<td><input type=\"radio\" onclick=\"removeStudentCompany('" + key.getStudid() + "','" + pref + "')\" type=\"submit\" name=\""+ key.getStudid() +"\" />"
                                    + "<img hidden=\"true\" id=\""+key.getStudid()+"\" src=\"Icons/tick2.png\" style=\"\" width=\"15\" height=\"15\" alt=\"tick2\"/></td>"
                                    + "<td><input type=\"radio\" onclick=\"addStudentCompany('" + key.getStudid() + "','" + pref + "')\" type=\"submit\" name=\""+ key.getStudid() +"\" />"
                                    + "<img hidden=\"true\" id=\""+ id2 +"\" src=\"Icons/tick2.png\" style=\"\" width=\"15\" height=\"15\" alt=\"tick2\"/></td>"
                                    + "</tr>"
                            );
                        }
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
