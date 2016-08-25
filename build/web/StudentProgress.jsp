
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = null, type = null;
    if (session.getAttribute("username") == null || session.getAttribute("type") != "Student") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
    }
%>

<h3 style="text-align: center;">Company Preference List</h3>
<br>
<br>
<table align="center" style="text-align: center;width: 1000px;line-height: 25px;" class="tablea">
    <thead style="background-color: #8d9499;">
    <th>Preference</th>
    <th>Company</th>
    <th>Status</th>
</thead>
<tbody id="progList">
</tbody>
</table>
<br>
<br>
