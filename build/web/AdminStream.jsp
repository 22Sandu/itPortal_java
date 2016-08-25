<%-- 
    Document   : AdminStream
    Created on : Dec 20, 2015, 4:49:12 PM
    Author     : Sanduni
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="DB.Stream"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Order"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="DB.PoolManager"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="CSS/menu.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="JS/script.js"></script>
        <title>JSP Page</title>
        <%
            String name = null, type = null;

            if (session.getAttribute("username") == null || session.getAttribute("type") != "Admin") {
                request.setAttribute("error", 2);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                name = (String) session.getAttribute("username");
                type = (String) session.getAttribute("type");
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
        <script>
            function submitTime() {
                var inTime = document.getElementById("id1").value;
                var outTime = document.getElementById("id2").value;
                if (inTime !== "" && outTime !== "") {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (xhttp.readyState == 4 && xhttp.status == 200) {
                            if (xhttp.responseText === "1") {
                                document.getElementById("errorDiv").innerHTML = "Enter valid time.";
                                document.getElementById("errorDiv").style.color = "red";
                            } else if (xhttp.responseText === "2") {
                                document.getElementById("errorDiv").innerHTML = "Function cannot be proceed at the moment.Please try again later.";
                                document.getElementById("errorDiv").style.color = "red";
                            } else if (xhttp.responseText === "3") {
                                document.getElementById("errorDiv").innerHTML = "Saved successfully.";
                                document.getElementById("errorDiv").style.color = "green";
                            } else {
                                document.getElementById("errorDiv").innerHTML = "Function cannot be proceed at the moment.Please try again later.";
                                document.getElementById("errorDiv").style.color = "red";
                            }
                        }
                    }
                    xhttp.open("POST", "adminTime", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("inTime=" + inTime + "&outTime=" + outTime);
                }
            }
        </script>
    </head>
    <body onload="loadCompany()">
        <div id='cssmenu'>
            <ul>
                <li class="liclass"><a href='homeAdmin.jsp'><span>Back</span></a></li>
                <li class="liclass"><a href="Logout"><label id="labelhover">Logout</label></a></li>
                <li class="liclass2"><label>You are logged in as <%= name%></label></li>
            </ul>
        </div>

        <br>
        <div class="error2" id="errorDiv"></div>


        <table align="center" style="text-align: center">
            <tr>
                <td style="padding-right: 20px;">Make Student Preference available</td>
                <td>
                    From <div id="datetimepicker" class="input-append date">
                        <input id="id1" type="text" style="height: 30px;">
                        <span class="add-on">
                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>
                    </div>
                <td style="width: 5%;"></td>
                </td>
                <td>
                    To <div id="datetimepicker2" class="input-append date">
                        <input id="id2" type="text" style="height: 30px;">
                        <span class="add-on">
                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>
                    </div>
                </td>
            </tr>
            <br>
            <tr>
                <td colspan="4" style="text-align: right;padding-top: 20px;"><input onclick="submitTime()" type="submit" value="Save" name="save" /></td>
            </tr>
        </table>
        <br><br><br>
        <%
            try {
                PrintWriter a = response.getWriter();

                Session s = PoolManager.getSessionFactory().openSession();
                Criteria c = s.createCriteria(DB.Stream.class);
                c.addOrder(Order.desc("id"));
                c.setMaxResults(1);

                List<DB.Stream> l = c.list();

                for (Stream st : l) {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String inTime = simpleDateFormat.format(st.getInTime());
                    String outTime = simpleDateFormat.format(st.getOutTime());

        %>
        <table align="center" style="text-align: center;width: 60%;">
            <tr>
                <td colspan="2" style="text-align: left">Last Available</td></tr>
            <tr>
                <td>From: <%=inTime %></td>
                <td>To: <%=outTime %></td>
            </tr>
        </table>
        <%        }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js"></script>
        <script type="text/javascript">
                    $('#datetimepicker').datetimepicker({
                        format: 'dd/MM/yyyy hh:mm:ss',
                        language: 'en-IN'
                    });
                    $('#datetimepicker2').datetimepicker({
                        format: 'dd/MM/yyyy hh:mm:ss',
                        language: 'en-IN'
                    });
        </script>
    </body>
</html>