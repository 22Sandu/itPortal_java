package servletPack;

import DB.PoolManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int type = -1;
            JSONArray names;
            ListIterator i;
            PrintWriter a = response.getWriter();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Session s = PoolManager.getSessionFactory().openSession();
            Criteria c = s.createCriteria(DB.Login.class);
            Criterion r1 = Restrictions.eq("username", username);
            Criterion r2 = Restrictions.eq("password", password);
            c.add(Restrictions.and(r1, r2));

            List<DB.Login> l = c.list();

            if (c == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
                request.setAttribute("error", 1);
                rd.include(request, response);
            } else {
                if (l.size() == 1) {
                    for (DB.Login l1 : l) {
                        type = l1.getType().getTypeid();
                    }
                }
            }

            if (type != -1) {
                String page = "Login.jsp", typeName = "";

                switch (type) {
                    case 1:
                        page = "homeAdmin.jsp";
                        typeName = "Admin";
                        break;
                    case 2:
                        page = "homeStudent.jsp";
                        typeName = "Student";
                        break;
                    case 3:
                        page = "homeCompany.jsp";
                        typeName = "Company";
                        break;
                    case 4:
                        page = "homeStaff.jsp";
                        typeName = "Staff";
                        break;
                }

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("type", typeName);
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("username", username);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                response.sendRedirect(page);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            request.setAttribute("error", 1);
            rd.include(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
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
