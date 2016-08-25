package servletPack;

import DB.PoolManager;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "staffStudentStates", urlPatterns = {"/staffStudentStates"})
public class staffStudentStates extends HttpServlet {

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
            String staff = (String) session.getAttribute("username");

            Session s = PoolManager.getSessionFactory().openSession();

            Criteria cc = s.createCriteria(DB.Staff.class);
            cc.add(Restrictions.eq("username", staff));
            cc.setMaxResults(1);

            List<DB.Staff> ll = cc.list();
            for (DB.Staff ll1 : ll) {

                Criteria c = s.createCriteria(DB.Advisee.class);
                c.add(Restrictions.eq("staff", s.load(DB.Staff.class, ll1.getStaffid())));

                List<DB.Advisee> l = c.list();

                int k = 0;

                for (DB.Advisee l1 : l) {

                    a.append("<tr>"
                            + "<td>" + k++ + "</td>"
                            + "<td>" + l1.getStudentPreference().getStudid() + "</td>"
                            + "<td>" + s.load(DB.Company.class, l1.getStudentPreference().getCompanyByPref1()).getName() + "</td>"
                            + "<td>" + s.load(DB.Status.class, l1.getStudentPreference().getStatusByStatus1()).getStatus() + "</td>"
                            + "<td>" + s.load(DB.Company.class, l1.getStudentPreference().getCompanyByPref2()).getName() + "</td>"
                            + "<td>" + s.load(DB.Status.class, l1.getStudentPreference().getStatusByStatus2()).getStatus() + "</td>"
                            + "<td>" + s.load(DB.Company.class, l1.getStudentPreference().getCompanyByPref3()).getName() + "</td>"
                            + "<td>" + s.load(DB.Status.class, l1.getStudentPreference().getStatusByStatus3()).getStatus() + "</td>"
                            + "<td>" + s.load(DB.Company.class, l1.getStudentPreference().getCompanyByPref4()).getName() + "</td>"
                            + "<td>" + s.load(DB.Status.class, l1.getStudentPreference().getStatusByStatus4()).getStatus() + "</td>"
                            + "</tr>");
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
