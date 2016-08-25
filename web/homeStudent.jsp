<%-- 
    Document   : homeStudent
    Created on : Nov 9, 2015, 12:02:46 PM
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
        <title>JSP Page</title>
    </head>
    <%
        String name = null, type = null;

        if (session.getAttribute("username") == null || session.getAttribute("type") != "Student") {
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
    <script type="text/javascript">
        function studentPrefList() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("pref1").innerHTML = xhttp.responseText;
                    document.getElementById("pref2").innerHTML = xhttp.responseText;
                    document.getElementById("pref3").innerHTML = xhttp.responseText;
                    document.getElementById("pref4").innerHTML = xhttp.responseText;
                }
            }
            xhttp.open("POST", "studentPreferences", true);
            xhttp.send();
        }

        function selectedCmp(id) {

            var pref1 = document.getElementById("pref1").value;
            var pref2 = document.getElementById("pref2").value;
            var pref3 = document.getElementById("pref3").value;
            var pref4 = document.getElementById("pref4").value;

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    if (id !== "pref1" && pref1 === "0")
                        document.getElementById("pref1").innerHTML = xhttp.responseText;
                    if (id !== "pref2" && pref2 === "0")
                        document.getElementById("pref2").innerHTML = xhttp.responseText;
                    if (id !== "pref3" && pref3 === "0")
                        document.getElementById("pref3").innerHTML = xhttp.responseText;
                    if (id !== "pref4" && pref4 === "0")
                        document.getElementById("pref4").innerHTML = xhttp.responseText;
                }
            }
            xhttp.open("POST", "preferenceLimit", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("name1=" + pref1 + "&name2=" + pref2 + "&name3=" + pref3 + "&name4=" + pref4);
        }

        function validate() {
            var bool = true;

            document.getElementById("im1").hidden = true;
            document.getElementById("im2").hidden = true;
            document.getElementById("img1").hidden = false;
            document.getElementById("img2").hidden = false;
            document.getElementById("im3").hidden = true;
            document.getElementById("im4").hidden = true;
            document.getElementById("img3").hidden = false;
            document.getElementById("img4").hidden = false;

            if (document.getElementById("pref1").value === document.getElementById("pref2").value) {
                document.getElementById("im1").hidden = false;
                document.getElementById("im2").hidden = false;
                document.getElementById("img1").hidden = true;
                document.getElementById("img2").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref2").value === document.getElementById("pref3").value) {
                document.getElementById("im2").hidden = false;
                document.getElementById("im3").hidden = false;
                document.getElementById("img3").hidden = true;
                document.getElementById("img2").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref3").value === document.getElementById("pref4").value) {
                document.getElementById("im3").hidden = false;
                document.getElementById("im4").hidden = false;
                document.getElementById("img3").hidden = true;
                document.getElementById("img4").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref1").value === document.getElementById("pref4").value) {
                document.getElementById("im1").hidden = false;
                document.getElementById("im4").hidden = false;
                document.getElementById("img1").hidden = true;
                document.getElementById("img4").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref2").value === document.getElementById("pref3").value) {
                document.getElementById("im2").hidden = false;
                document.getElementById("im3").hidden = false;
                document.getElementById("img2").hidden = true;
                document.getElementById("img3").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref2").value === document.getElementById("pref4").value) {
                document.getElementById("im2").hidden = false;
                document.getElementById("im4").hidden = false;
                document.getElementById("img2").hidden = true;
                document.getElementById("img4").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref1").value === document.getElementById("pref3").value) {
                document.getElementById("im1").hidden = false;
                document.getElementById("im3").hidden = false;
                document.getElementById("img1").hidden = true;
                document.getElementById("img3").hidden = true;
                bool = false;
            }

            if (document.getElementById("pref1").value === "0") {
                document.getElementById("im1").hidden = false;
                document.getElementById("img1").hidden = true;
                bool = false;
            }
            if (document.getElementById("pref2").value === "0") {
                document.getElementById("im2").hidden = false;
                document.getElementById("img2").hidden = true;
                bool = false;
            }
            if (document.getElementById("pref3").value === "0") {
                document.getElementById("im3").hidden = false;
                document.getElementById("img3").hidden = true;
                bool = false;
            }
            if (document.getElementById("pref4").value === "0") {
                document.getElementById("im4").hidden = false;
                document.getElementById("img4").hidden = true;
                bool = false;
            }

            if (bool)
                proceed();
        }

        function proceed() {
            var pref1 = document.getElementById("pref1").value;
            var pref2 = document.getElementById("pref2").value;
            var pref3 = document.getElementById("pref3").value;
            var pref4 = document.getElementById("pref4").value;

            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    if (xhttp.responseText === "1") {
                        document.getElementById("errorDiv").innerHTML = "Preferences are already sent.";
                        document.getElementById("errorDiv").style.color = "red";
                    } else if (xhttp.responseText === "2") {
                        document.getElementById("errorDiv").innerHTML = "Invalid Preference Selection.";
                        document.getElementById("errorDiv").style.color = "red";
                    } else if (xhttp.responseText === "0") {
                        document.getElementById("errorDiv").innerHTML = "Preference added successfully.";
                        document.getElementById("errorDiv").style.color = "green";
                    }
                }
            }
            xhttp.open("POST", "studentPrefAdd", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("name1=" + pref1 + "&name2=" + pref2 + "&name3=" + pref3 + "&name4=" + pref4);
        }

        function loadPreferences() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("demo").innerHTML = xhttp.responseText;
                    studentPrefList();
                }
            }
            xhttp.open("GET", "StudentPreferences.jsp", true);
            xhttp.send();
        }

        function loadProgress() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("demo").innerHTML = xhttp.responseText;
                    loadProgressList();
                }
            }
            xhttp.open("GET", "StudentProgress.jsp", true);
            xhttp.send();
        }

        function loadProgressList() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    if (xhttp.responseText === "") {
                        document.getElementById("progList").innerHTML = "<tr><td colspan=3>No preferences selected yet.</td></tr>"
                        document.getElementById("progList").style.color = "red";
                    } else {
                        document.getElementById("progList").innerHTML = xhttp.responseText;
                    }
                }
            }
            xhttp.open("GET", "studentProgress", true);
            xhttp.send();
        }

        function loadUploads() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    document.getElementById("demo").innerHTML = xhttp.responseText;
                    //loadProgressList();
                }
            }
            xhttp.open("GET", "cvupload.jsp", true);
            xhttp.send();
        }

        function checkAvailability() {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    if (xhttp.responseText === "1") {
                        loadPreferences()
                    } else {
                        document.getElementById("demo").innerHTML = "<br><br><div align='center'>This feature is not available at the moment. Please contact the administrator.</div>";
                    }
                }
            }
            xhttp.open("GET", "studentAvailability", true);
            xhttp.send();
        }
    </script>
    <body onload="loadProgress()">
        <div id='cssmenu'>
            <ul>
                <li class="liclass"><a onclick="checkAvailability()" href='#'><span>Add Preferences</span></a></li>
                <li class="liclass"><a onclick="loadUploads()" href="#"><span>Upload Curriculum Vitae</span></a></li>
                <li class="liclass"><a onclick="loadProgress()" href='#'><span>Current Progress</span></a></li>
                <li class="liclass"><a href="Logout"><label id="labelhover">Logout</label></a></li>
                <li class="liclass2"><label>You are logged in as <%= name%></label></li>
            </ul>
        </div>
        <br>
        <div id="demo">
        </div>
    </body>
</html>
