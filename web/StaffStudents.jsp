
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = null, type = null;
    if (session.getAttribute("username") == null || session.getAttribute("type") != "Staff") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
    }
%>

<h3 style="text-align: center;">Students List</h3>
<br>
<table align="center" style="width: 96%;text-align: center;margin-right: 80px;">
    <tr>
        <td style="width: 30%;vertical-align: top;">
            <table style="width: 100%;line-height: 25px;">
                <thead style="font-weight: bold">
                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr><td style="text-align: right">Student List</td><td><input class="btn btn-default btn-sm" onclick="selectStudents()" type="submit" value="Select Students" /></td></tr>
                    <tr><td>&nbsp;</td></tr>
                </thead>
                <tbody id="stdList"></tbody>
            </table>
        </td>
        <td style="vertical-align: top;">
            <table style="width: 100%;line-height: 28px;" class="tablea">
                <thead style="font-weight: bold">
                    <tr style="">
                        <td colspan="2">Selected Student List</td><td colspan="1">
                            <input class="btn btn-default btn-sm" type="submit" value="Save Selected List" onclick="saveSelectedList()" />
                            <img id="ssl" src="Icons/crs2.png" style="position: relative;top: 0px;" width="20" height="20" alt="crs2"/>

                        </td>
                    </tr>
                    <tr><td colspan="3" style="background-color: white">&nbsp;</td></tr>
                    <tr  style="background-color: #999999;font-weight: normal;">
                        <td>Preference</td>
                        <td>Company</td>
                        <td>Status</td>
                    </tr>
                </thead>
                <tbody id="stdStatusList"></tbody>
            </table>
        </td>

    </tr>

</table>
<br><br>

<div id="test"></div>

