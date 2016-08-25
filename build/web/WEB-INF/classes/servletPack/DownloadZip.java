/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import DB.PoolManager;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

public class DownloadZip extends HttpServlet {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

        Session s = PoolManager.getSessionFactory().openSession();
        Criteria c = s.createCriteria(DB.StudentPreference.class);

        HttpSession session = request.getSession();
        String company = (String) session.getAttribute("username");
        
        List<String> iteams=new ArrayList<>();

        try {

            List<DB.StudentPreference> l = c.list();
            
            Criteria cc = s.createCriteria(DB.Company.class);
            cc.add(Restrictions.eq("name", company));
            cc.setMaxResults(1);

            List<DB.Company> ll = cc.list();
            int index = 0;
            for (DB.Company ll1 : ll) {
                index = ll1.getCompanyid();
            }

            int pref1, pref2, pref3, pref4, status = 0, pref = 0;

            int k = 1;
            boolean val = false;

            for (int j = 0; j < l.size(); j++) {
                val = false;

                pref1 = l.get(j).getCompanyByPref1().getCompanyid();
                pref2 = l.get(j).getCompanyByPref2().getCompanyid();
                pref3 = l.get(j).getCompanyByPref3().getCompanyid();
                pref4 = l.get(j).getCompanyByPref4().getCompanyid();

                if (index == pref1) {
                    status = l.get(j).getStatusByStatus1().getIdstatus();
                    pref = 1;
                } else if (index == pref2) {
                    status = l.get(j).getStatusByStatus2().getIdstatus();
                    pref = 2;
                } else if (index == pref3) {
                    status = l.get(j).getStatusByStatus3().getIdstatus();
                    pref = 3;
                } else if (index == pref4) {
                    status = l.get(j).getStatusByStatus4().getIdstatus();
                    pref = 4;
                }

                String path="C:\\Users\\Sanduni\\Documents\\upload";
 
                File directory = new File(path);
                String[] dir = directory.list();
                String cv = null;
                
                for (String sl : dir) {
                    if(sl.equals(l.get(j).getStudid()+".pdf")){
                        val = true;
                        cv = sl;
                        break;
                    }
                }
                

                if ((index == pref1 || index == pref2 || index == pref3 || index == pref4) && val) {
                    iteams.add(l.get(j).getStudid());
                    new dbHandlingMethods().updateStudentPreferences(l.get(j).getStudid(), pref, 3);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //
            // The path below is the root directory of data to be
            // compressed.
            //
            // String path = getServletContext().getRealPath("data");
            String path = "C:\\Users\\Sanduni\\Documents\\upload";

            File directory = new File(path);

            String[] files = directory.list();
            String[] needFiles = new String[iteams.size()];

            for (int i = 0; i < iteams.size(); i++) {
                needFiles[i] = iteams.get(i) + ".pdf";
                System.out.println(needFiles[i]);
            }

            //
            // Checks to see if the directory contains some files.
            //
            if (needFiles != null && needFiles.length > 0) {

                //
                // Call the zipFiles method for creating a zip stream.
                //
                byte[] zip = zipFiles(directory, needFiles);

                //
                // Sends the response back to the user / browser. The
                // content for zip file type is "application/zip". We
                // also set the content disposition as attachment for
                // the browser to show a dialog that will let user 
                // choose what action will he do to the sent content.
                //
                ServletOutputStream sos = response.getOutputStream();
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"CV.zip\"");

                sos.write(zip);
                sos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];

        for (String fileName : files) {
            FileInputStream fis = new FileInputStream(directory.getPath()
                    + DownloadZip.FILE_SEPARATOR + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            zos.putNextEntry(new ZipEntry(fileName));

            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, bytesRead);
            }

            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();

        return baos.toByteArray();
    }

}
