package servletPack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DB.Company;
import DB.PoolManager;
import DB.StudentPreference;
import java.io.File;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sanduni
 */
@WebServlet(urlPatterns = {"/companyPreferences"})
public class companyPreferences extends HttpServlet {

    static List<DB.StudentPreference> l;
    static int index = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));

        HttpSession session = request.getSession();
        String company = (String) session.getAttribute("username");

        try {
            PrintWriter a = response.getWriter();

            org.hibernate.Session s = PoolManager.getSessionFactory().openSession();
            Criteria c = s.createCriteria(DB.StudentPreference.class);

            l = c.list();
            List<DB.StudentPreference> l2 = new ArrayList<>();
            List<DB.StudentPreference> l3 = new ArrayList<>();
            List<DB.StudentPreference> l4 = new ArrayList<>();

            Criteria cc = s.createCriteria(DB.Company.class);
            cc.add(Restrictions.eq("name", company));
            cc.setMaxResults(1);

            List<DB.Company> ll = cc.list();

            for (Company ll1 : ll) {
                index = ll1.getCompanyid();
            }

            int status = 0, pref = 0;
            int start = 0, end = l.size();

            if (limit != 0) {
                start = page * limit;
                end = start + limit;

                if (end > l.size()) {
                    end = l.size();
                }
            }

            int k = 1;
            boolean val = false;
            String cv = null;

            for (int j = start; j < end; j++) {

                pref = statPref(j)[0];
                status = statPref(j)[1];

                new dbHandlingMethods().updateStudentPreferences(l.get(j).getStudid(), pref, 3);
                if (pref != 0 && status != 0) {
                    if (status == 2) {
                        l3.add(l.get(j));
                    } else if (status == 4) {
                        l2.add(l.get(j));
                    } else if (status == 1 || status == 3 || status == 5) {
                        l4.add(l.get(j));
                    }
                }
            }
            int n = 1;
            for (StudentPreference l21 : l2) {
                val = false;
                
                int preff = getPref(l21.getStudid(), index);

                String path = "C:\\Users\\Sanduni\\Documents\\upload";

                File directory = new File(path);
                String[] dir = directory.list();

                for (String sl : dir) {
                    if (sl.equals(l21.getStudid() + ".pdf")) {
                        val = true;
                        cv = sl;
                        break;
                    }
                }
                if (val && preff!=0) {
                    a.append("<tr>"
                            + "<td>" + n + "</td>"
                            + "<td>" + l21.getStudid() + "</td>"
                            + "<td><a style=\"text-decoration: none;\" href=\"DownloadFileServlet?cv=" + cv + "&stud=" + l21.getStudid() + "&status=" + preff + "\" >" + cv + "</a></td>");
                    a.append("<td>"
                            + "<input id='status1' onclick=\"changeState('" + l21.getStudid() + "', '1', '" + preff + "')\"  type='radio' name='" + l21.getStudid() + "' value='' />Pending&nbsp&nbsp"
                            + "<input id='status1' onclick=\"changeState('" + l21.getStudid() + "', '2', '" + preff + "')\" type='radio' name='" + l21.getStudid() + "' value='' />Short Listed&nbsp&nbsp"
                            + "<input id='status1' onclick=\"changeState('" + l21.getStudid() + "', '4', '" + preff + "')\" type='radio' name='" + l21.getStudid() + "' value='' checked='true' />Selected&nbsp&nbsp"
                            + "</tr>");
                    n++;
                }
            }

            for (StudentPreference l31 : l3) {
                val = false;

                int preff = getPref(l31.getStudid(), index);

                String path = "C:\\Users\\Sanduni\\Documents\\upload";

                File directory = new File(path);
                String[] dir = directory.list();

                for (String sl : dir) {
                    if (sl.equals(l31.getStudid() + ".pdf")) {
                        val = true;
                        cv = sl;
                        break;
                    }
                }
                if (val && preff!=0) {
                    a.append("<tr>"
                            + "<td>" + n + "</td>"
                            + "<td>" + l31.getStudid() + "</td>"
                            + "<td><a style=\"text-decoration: none;\" href=\"DownloadFileServlet?cv=" + cv + "&stud=" + l31.getStudid() + "&status=" + preff + "\" >" + cv + "</a></td>");
                    a.append("<td>"
                            + "<input id='status1' onclick=\"changeState('" + l31.getStudid() + "', '1', '" + preff + "')\" type='radio' name='" + l31.getStudid() + "' value='' />Pending&nbsp&nbsp"
                            + "<input id='status1' onclick=\"changeState('" + l31.getStudid() + "', '2', '" + preff + "')\" type='radio' name='" + l31.getStudid() + "' value='' checked='true' />Short Listed&nbsp&nbsp"
                            + "<input id='status1' onclick=\"changeState('" + l31.getStudid() + "', '4', '" + preff + "')\" type='radio' name='" + l31.getStudid() + "' value='' />Selected&nbsp&nbsp"
                            + "</tr>");
                    n++;
                }
            }

            for (StudentPreference l41 : l4) {
                val = false;

                String path = "C:\\Users\\Sanduni\\Documents\\upload";
                
                int preff = getPref(l41.getStudid(), index);

                File directory = new File(path);
                String[] dir = directory.list();

                for (String sl : dir) {
                    if (sl.equals(l41.getStudid() + ".pdf")) {
                        val = true;
                        cv = sl;
                        break;
                    }
                }
                if (val && preff!=0) {
                    a.append("<tr>"
                            + "<td>" + n + "</td>"
                            + "<td>" + l41.getStudid() + "</td>"
                            + "<td><a style=\"text-decoration: none;\" href=\"DownloadFileServlet?cv=" + cv + "&stud=" + l41.getStudid() + "&status=" + preff + "\" >" + cv + "</a></td>");
                    a.append("<td>"
                            + "<input id='status1' onclick=\"changeState('" + l41.getStudid() + "', '1', '" + preff + "')\" type='radio' name='" + l41.getStudid() + "' value='' checked='true' />Pending&nbsp&nbsp"
                            + "<input id='status1' onclick=\"changeState('" + l41.getStudid() + "', '2', '" + preff + "')\" type='radio' name='" + l41.getStudid() + "' value='' />Short Listed&nbsp&nbsp"
                            + "<input id='status1' onclick=\"changeState('" + l41.getStudid() + "', '4', '" + preff + "')\" type='radio' name='" + l41.getStudid() + "' value='' />Selected&nbsp&nbsp"
                            + "</tr>");
                    n++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] statPref(int j) {
        int pref1, pref2, pref3, pref4, stat1, stat2, stat3, stat4, status = 0, pref = 0;
        pref1 = l.get(j).getCompanyByPref1().getCompanyid();
        pref2 = l.get(j).getCompanyByPref2().getCompanyid();
        pref3 = l.get(j).getCompanyByPref3().getCompanyid();
        pref4 = l.get(j).getCompanyByPref4().getCompanyid();

        stat1 = l.get(j).getStatusByStatus1().getIdstatus();
        stat2 = l.get(j).getStatusByStatus2().getIdstatus();
        stat3 = l.get(j).getStatusByStatus3().getIdstatus();
        stat4 = l.get(j).getStatusByStatus4().getIdstatus();

        if (index == pref1 && stat2 != 4 && stat3 != 4 && stat4 != 4) {
            status = l.get(j).getStatusByStatus1().getIdstatus();
            pref = 1;
        } else if (index == pref2 && stat1 != 4 && stat3 != 4 && stat4 != 4) {
            status = l.get(j).getStatusByStatus2().getIdstatus();
            pref = 2;
        } else if (index == pref3 && stat1 != 4 && stat2 != 4 && stat4 != 4) {
            status = l.get(j).getStatusByStatus3().getIdstatus();
            pref = 3;
        } else if (index == pref4 && stat1 != 4 && stat2 != 4 && stat3 != 4) {
            status = l.get(j).getStatusByStatus4().getIdstatus();
            pref = 4;
        }
        if (!(index == pref1 || index == pref2 || index == pref3 || index == pref4)) {
            pref = 0;
            status = 0;
        }
        return new int[]{pref, status};
    }

    public int getPref(String id, int company) {
        int pref = 0;
        org.hibernate.Session s = PoolManager.getSessionFactory().openSession();
        Criteria c = s.createCriteria(DB.StudentPreference.class);
        c.add(Restrictions.eq("studid", id));
        c.setMaxResults(1);
        
        List<DB.StudentPreference> l = c.list();
        
        for (StudentPreference l1 : l) {
            if(l1.getCompanyByPref1().getCompanyid()==company) pref=1;
            else if(l1.getCompanyByPref2().getCompanyid()==company) pref=2;
            else if(l1.getCompanyByPref3().getCompanyid()==company) pref=3;
            else if(l1.getCompanyByPref4().getCompanyid()==company) pref=4;
        }
        

        return pref;
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
