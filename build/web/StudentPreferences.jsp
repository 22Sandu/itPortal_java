<%-- 
    Document   : StudentPreferences
    Created on : Nov 11, 2015, 10:21:40 PM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            /*String name = null, type = null;
            if (session.getAttribute("username") == null || session.getAttribute("type") != "Student") {
                request.setAttribute("error", 2);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                name = (String) session.getAttribute("username");
                type = (String) session.getAttribute("type");
            }*/
        %>
     
        <h3 style="text-align: center;">Student Preferences</h3>
        <table align="center" style="text-align: center;width: 500px;line-height: 40px;">
            <tr>
                <td colspan="3">
                    <div align="center" id="errorDiv" style="color: red;text-align: center;"></div>
                </td>
            </tr>
            <tr>
                <td>Preference 1</td>
                <td style="width: 10%">
                    <select class="droplist" id="pref1" name="Preference 1" onchange="selectedCmp(this.id)"></select>
                </td>
                <td style="width: 10%">
                    <img id="im1" hidden="true" src="Icons/crs2.png" width="17" height="17" alt="crs2" />
                    <img id="img1" hidden="true" src="Icons/tick2.png" width="17" height="17" alt="crs2" />
                </td>
            </tr>
            <tr>
                <td>Preference 2</td>
                <td>
                    <select class="droplist" id="pref2" name="Preference 2" onchange="selectedCmp(this.id)"></select>
                </td>
                <td>
                    <img id="im2" hidden="true" src="Icons/crs2.png" width="20" height="20" alt="crs2"/>
                    <img id="img2" hidden="true" src="Icons/tick2.png" width="17" height="17" alt="crs2" />
                </td>
            </tr>
            <tr>
                <td>Preference 3</td>
                <td>
                    <select class="droplist" id="pref3" name="Preference 3" onchange="selectedCmp(this.id)"></select>
                </td>
                <td>
                    <img id="im3" hidden="true" src="Icons/crs2.png" width="20" height="20" alt="crs2"/>
                    <img id="img3" hidden="true" src="Icons/tick2.png" width="17" height="17" alt="crs2" />
                </td>
            </tr>
            <tr>
                <td>Preference 4</td>
                <td>
                    <select class="droplist" id="pref4" name="Preference 4" onchange="selectedCmp(this.id)"></select>
                </td>
                <td>
                    <img id="im4" hidden="true" src="Icons/crs2.png" width="20" height="20" alt="crs2"/>
                    <img id="img4" hidden="true" src="Icons/tick2.png" width="17" height="17" alt="crs2" />
                </td>
            </tr>
            <tr>
                <td colspan="3" style="padding-top: 20px;">
                    <input class="btn btn-default btn-sm" type="submit" value="Proceed" onclick="validate()"/>
                </td>
            </tr>
        </table>

