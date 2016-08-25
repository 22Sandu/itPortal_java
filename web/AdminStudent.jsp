
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = null, type = null;
    if (session.getAttribute("username") == null || session.getAttribute("type") != "Admin") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
    }
%>

<h3 style="text-align: center;">Student Preference List</h3>
<br>
<table align="center" style="text-align: center;width: 500px;">
    <tr>
        <td style="text-align: left">Number of Students per page :</td>
        <td>
            <select id="limit" style="width: 150px;" name="limit" onchange="dropChange('2')">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option selected="true" value="0">All</option>
            </select>
        </td>
    </tr>
</table>
<br>
<table align="center" style="text-align: center;width: 1000px;line-height: 28px;" class="tablea">
    <thead>
    <th>Index</th>
    <th>Registration Number</th>
    <th>Preference 1</th>
    <th>Preference 2</th>
    <th>Preference 3</th>
    <th>Preference 4</th>
</thead>
<tbody id="stdList">
</tbody>
</table>
<br>
<table align="center" style="width: 500px;text-align: center">
    <tr>
        <td>
            <input class="btn btn-default btn-sm" id="prev" type="submit" value="Previous" disabled="true" onclick="prev('2')" />
            <input class="btn btn-default btn-sm" id="idx" type="submit" value="Next" disabled="true" onclick="next('2')" />
        </td>
    </tr>
</table>
<br><br>
<div id="test" hidden="true">0</div>
