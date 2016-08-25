

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<title>JSP Page</title>
<%
    String name = null, type = null, fileName = "e12001";

    if (session.getAttribute("username") == null || session.getAttribute("type") != "Staff") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
        fileName = (String) request.getParameter("fileName");
    }

    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                userName = cookie.getValue();
            }
            if (cookie.getName().equals("JSESSIONID")) {
                sessionID = cookie.getValue();
            }
        }
    }
%>

<div class="gallery" align="center">
    <table>
        <tr>
            <td>
                <div class="thumbnails">
                    <%
                        String path = "C:\\Users\\Sanduni\\Documents\\NetBeansProjects\\Final\\web\\CVs";

                        File directory = new File(path);
                        String[] dir = directory.list();
                        int k = 0;
                        for (String sl : dir) {
                            String[] sp = sl.split("-");
                            if (sp[0].equals(fileName)) {

                    %>
                    <img onmouseover="preview.src = image<%=k%>.src" name="image<%=k%>" src="CVs/<%=sl%>" alt=""/>
                    <%
                                k++;
                            }
                        }

                    %>
                </div>
            </td>
        </tr>
        <tr>
            <td><br/></td>
        </tr>
        <tr>
            <td>
                <div class="preview" align="center">
                    <img name="preview" src="image0.jpg" alt=""/>
                </div>
            </td>
        </tr>
    </table>
</div>

