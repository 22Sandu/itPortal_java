<%-- 
    Document   : AdminManageStudent
    Created on : Dec 20, 2015, 8:07:31 PM
    Author     : Sanduni
--%>
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

<h3 style="text-align: center;">Manage Students</h3>
<br>
<table align="center" style="width: 96%;text-align: center;line-height: 30px;">
    <tr>
        <td style="width: 23%;vertical-align: top;padding-left: 3%;;">
            <table style="width: 100%">
                <thead>
                    <tr><td>&nbsp;</td></tr>
                    <tr style="text-align: left;"><td colspan="2" style="font-size: 18px;">Company List</td><td colspan="2"><input class="btn btn-default btn-sm" onclick="selectCompanies()" type="submit" value="Select Companies" /></td></tr>
                    <tr><td>&nbsp;</td></tr>
                </thead>
                <tbody id="companyList" style="padding-left: 90px;margin-left: 90px;text-align: left;"></tbody>
            </table>
        </td>
        <td style="vertical-align: top;">
            <table style="width: 100%;" class="tablea">
                <thead>
                    <tr>
                        <td colspan="6" style="font-size: 18px;">Student List</td>
                    </tr>
                    <tr style="background-color: #999999;">
                        <td>Index</td>
                        <td>Student</td>
                        <td>Preference</td>
                        <td>Status</td>
                        <td style="width: 15%;">Remove</td>
                        <td style="width: 15%;">Add</td>
                    </tr>
                </thead>
                <tbody id="studList"></tbody>
            </table>
        </td>

    </tr>

</table>
<br><br>

<div id="test"></div>

