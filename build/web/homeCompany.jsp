<%-- 
    Document   : homeCompany
    Created on : Nov 9, 2015, 2:30:59 PM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="CSS/css/homes.css">
        <link rel="stylesheet" href="CSS/css/pageformat.css" /> 
        <link rel="stylesheet" href="CSS/css/fontformat.css" />
        
        <link rel="stylesheet" href="CSS/menu.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="JS/script.js"></script>
        <title>Company Page</title>
    </head>
    <script type="text/javascript">
        function loadStudentPreferences() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("loadingArea").innerHTML = xhttp.responseText;
                    loadStudentPreferencesList();
                }
            }
            xhttp.open("GET", "CompanyStudentPreferences.jsp", true);
            xhttp.send();
        }

        function loadStudentPreferencesList() {
            var limit = document.getElementById("limit").value;
            var val = document.getElementById("test").innerHTML;

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                    if (xhttp.responseText === "") {
                        document.getElementById("idx").disabled = true;
                        document.getElementById("prev").disabled = false;
                    } else {
                        document.getElementById("cmpnyList").innerHTML = xhttp.responseText;
                    }
                }
            }
            xhttp.open("POST", "companyPreferences", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("limit=" + limit + "&page=" + val);
        }

        function dropChange() {

            if (document.getElementById("limit").value === "0") {
                document.getElementById("idx").disabled = true;
                document.getElementById("prev").disabled = true;
                document.getElementById("test").innerHTML = "0";
            } else {
                document.getElementById("idx").disabled = false;
                document.getElementById("prev").disabled = true;
                document.getElementById("test").innerHTML = "0";
            }
            loadStudentPreferencesList();
        }

        function next() {
            if (document.getElementById("test").innerHTML === 0) {
                document.getElementById("prev").disabled = true;
                document.getElementById("idx").disabled = false;
            } else {
                document.getElementById("prev").disabled = false;
                document.getElementById("idx").disabled = false;
            }
            document.getElementById("test").innerHTML = parseInt(document.getElementById("test").innerHTML) + 1;

            loadStudentPreferencesList();
        }

        function prev() {
            document.getElementById("test").innerHTML = parseInt(document.getElementById("test").innerHTML) - 1;
            if (document.getElementById("test").innerHTML === "0") {
                document.getElementById("prev").disabled = true;
                document.getElementById("idx").disabled = false;
            }
            loadStudentPreferencesList();
        }

        function changeState(stud, state, status) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                    loadStudentPreferencesList();
                }
            }
            xhttp.open("POST", "updateCompanyPreferences", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("stud=" + stud + "&state=" + state + "&status=" + status);
        }

        function downloadZip() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                    if (xhttp.responseText == "1") {
                        document.getElementById("msg").style.color = "green";
                        document.getElementById("msg").innerHTML = "Downloaded Successfully";
                    } else {
                        document.getElementById("msg").style.color = "red";
                        document.getElementById("msg").innerHTML = "Something went wrong. Try again later.";
                    }
                }
            }
           
            document.location.href='${pageContext.request.contextPath}/DownloadZip';
          
        }
    </script>
    <%
        String name = null, type = null;

        if (session.getAttribute("username") == null || session.getAttribute("type") != "Company") {
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
    <body onload="loadStudentPreferences()">
        <div id='cssmenu'>
            <ul>
                <li class="liclass"><a onclick="loadStudentPreferences()" href='#'><span>Student Preferences</span></a></li>
                <li class="liclass"><a href="Logout"><label id="labelhover">Logout</label></a></li>
                <li class="liclass2"><label>You are logged in as <%= name%></label></li>
            </ul>
        </div>

        <div id="loadingArea">
        </div>
    </body>
</html>
