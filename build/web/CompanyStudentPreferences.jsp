
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = null, type = null;
    if (session.getAttribute("username") == null || session.getAttribute("type") != "Company") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
    }
%>

<h3 style="text-align: center;">Students List</h3>
<br>
<table align="center" style="text-align: center;width: 700px;">
    <tr>
        <td colspan="2">
            <div id="msg"></div>
        </td>
    </tr>

    <tr>
        <td style="text-align: left">Number of Students per page :</td>
        <td style="text-align: right">
            <select id="limit" name="limit" onchange="dropChange()" style="width: 150px;margin-bottom: 20px;" >
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option selected="true" value="0">All</option>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: right;">
            <a href="#" style="text-decoration: none;" onclick="downloadZip()">Download all as Zip</a>
        </td>
    </tr>

</table>
<br>
<table align="center" style="text-align: center;width: 700px;line-height: 29px;" class="tablea">
    <thead style="background-color: #8d9499;">
    <th>Index</th>
    <th>Student Name</th>
    <th>Curriculum Vitae</th>
    <th>Status</th>
</thead>
<tbody id="cmpnyList">
</tbody>
</table>
<br><br>
<table align="center" style="width: 500px;text-align: center">
    <tr>
        <td>
            <input class="btn btn-default btn-sm" id="prev" disabled="true" type="submit" value="Previous" onclick="prev()" />
            <input class="btn btn-default btn-sm" id="idx" disabled="true" type="submit" value="Next" onclick="next()" />
        </td>
    </tr>
</table>
<div id="test" hidden="true">0</div>
