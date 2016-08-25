<%-- 
    Document   : homeAdmin
    Created on : Nov 9, 2015, 2:30:36 PM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/menu.css">

        <link rel="stylesheet" href="CSS/css/homes.css">
        <link rel="stylesheet" href="CSS/css/pageformat.css" /> 
        <link rel="stylesheet" href="CSS/css/fontformat.css" />

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
            function loadCompany() {
                document.getElementById("errorDiv").innerHTML = "";
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                        loadCompanyList();
                    }
                }
                xhttp.open("GET", "AdminCompany.jsp", true);
                xhttp.send();
            }

            function loadCompanyList() {
                document.getElementById("errorDiv").innerHTML = "";
                var order;
                var limit = document.getElementById("limit").value;
                var val = document.getElementById("test").innerHTML;

                if (document.getElementById("date").checked === true) {
                    order = "1";
                } else if (document.getElementById("alph").checked === true) {
                    order = "2";
                }

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
                xhttp.open("POST", "loadCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("order=" + order + "&limit=" + limit + "&page=" + val);
            }

            function dropChange(type) {

                if (document.getElementById("limit").value === "0") {
                    document.getElementById("idx").disabled = true;
                    document.getElementById("prev").disabled = true;
                    document.getElementById("test").innerHTML = 0;
                } else {
                    document.getElementById("idx").disabled = false;
                    document.getElementById("prev").disabled = true;
                    document.getElementById("test").innerHTML = 0;
                }
                if (type === '1')
                    loadCompanyList();
                else if (type === '2')
                    loadStudentList();
            }

            function next(type) {
                if (document.getElementById("test").innerHTML === "0") {
                    document.getElementById("prev").disabled = true;
                    document.getElementById("idx").disabled = false;
                } else {
                    document.getElementById("prev").disabled = false;
                    document.getElementById("idx").disabled = false;
                }
                document.getElementById("test").innerHTML = parseInt(document.getElementById("test").innerHTML) + 1;
                if (type === '1')
                    loadCompanyList();
                else if (type === '2')
                    loadStudentList();
            }

            function prev(type) {
                document.getElementById("test").innerHTML = parseInt(document.getElementById("test").innerHTML) - 1;
                if (document.getElementById("test").innerHTML === "0") {
                    document.getElementById("prev").disabled = true;
                    document.getElementById("idx").disabled = false;
                }
                if (type === '1')
                    loadCompanyList();
                else if (type === '2')
                    loadStudentList();
            }

            function addCompany() {
                var val = document.getElementById("inputVal").value;
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("errorDiv").innerHTML = "";
                            loadCompany();
                        } else if (xhttp.responseText === "2") {
                            document.getElementById("errorDiv").innerHTML = "Please try again later";
                        } else if (xhttp.responseText === "3") {
                            document.getElementById("errorDiv").innerHTML = "Company already exist";
                        } else if (xhttp.responseText === "4") {
                            document.getElementById("errorDiv").innerHTML = "Enter a valid name";
                        }
                    }
                }
                xhttp.open("POST", "addCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val);
            }

            var glob = {};

            function updateC(val, ind) {
                glob.name = ind;
                document.getElementById("updt").disabled = "";
                document.getElementById("click").disabled = "true";
                document.getElementById("inputVal").value = val;
            }

            function updateCompany() {
                var ind = glob.name;
                var val = document.getElementById("inputVal").value;
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("click").disabled = "";
                            document.getElementById("updt").disabled = "true";
                            document.getElementById("inputVal").value = "";
                            document.getElementById("errorDiv").innerHTML = "";
                            loadCompany();
                        } else if (xhttp.responseText === "2") {
                            document.getElementById("errorDiv").innerHTML = "Please try again later";
                        }
                    }
                }
                xhttp.open("POST", "updateCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val + "&index=" + ind);
            }

            function deleteCompany(val) {

                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("errorDiv").innerHTML = "";
                            loadCompany();
                        }
                    }
                }
                xhttp.open("POST", "deleteCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val);
            }


            function loadStudent() {
                document.getElementById("errorDiv").innerHTML = "";
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("progList").innerHTML = "<tr><td colspan=3>No preferences selected yet.</td></tr>"
                            document.getElementById("progList").style.color = "red";
                        } else {
                            document.getElementById("demo").innerHTML = xhttp.responseText;
                            loadStudentList();
                        }
                    }
                }
                xhttp.open("GET", "AdminStudent.jsp", true);
                xhttp.send();
            }

            function loadStudentList() {
                document.getElementById("errorDiv").innerHTML = "";
                var limit = document.getElementById("limit").value;
                var val = document.getElementById("test").innerHTML;

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("idx").disabled = true;
                        } else {
                            //document.getElementById("idx").disabled = false;
                            document.getElementById("stdList").innerHTML = xhttp.responseText;
                        }
                    }
                }
                xhttp.open("POST", "loadStudent", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("limit=" + limit + "&page=" + val);
            }

            function loadEmails() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                        //loadProgressList();
                    }
                }
                xhttp.open("GET", "emailform.jsp", true);
                xhttp.send();
            }


            function manageStudent() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                        manageCompanyList();
                    }
                }
                xhttp.open("GET", "AdminManageStudent.jsp", true);
                xhttp.send();
            }

            function manageCompanyList() {
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("companyList").innerHTML = "<tr><td colspan=\"6\">No students in the database</td></tr>"
                        } else {
                            document.getElementById("companyList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "AdminCompanyList", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();
            }

            function selectCompanies() {
                var checkedBoxes = getCheckedBoxes("companyCheckBox");

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("studList").innerHTML = "<tr><td colspan=\"6\">No students assigned</td></tr>"
                        } else {
                            document.getElementById("studList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "AdminStudentList", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("checkedBoxes=" + checkedBoxes);
            }

            function getCheckedBoxes(chkboxName) {
                var checkboxes = document.getElementsByName(chkboxName);
                var checkboxesChecked = [];
                // loop over them all
                for (var i = 0; i < checkboxes.length; i++) {
                    // And stick the checked ones onto an array...
                    if (checkboxes[i].checked) {
                        checkboxesChecked.push(checkboxes[i].id);
                    }
                }
                // Return the array if it is non-empty, or null
                return checkboxesChecked.length > 0 ? checkboxesChecked : null;
            }

            function removeStudentCompany(stud, pref) {
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("errorDiv").innerHTML = "";
                            selectCompanies();
                            document.getElementById(stud).hidden = false;
                        } else if (xhttp.responseText === "2") {
                            document.getElementById("errorDiv").innerHTML = "Student already removed";
                        } else {
                            document.getElementById("errorDiv").innerHTML = "Please try again later";
                        }
                    }
                }
                xhttp.open("POST", "AdminRemoveStudent", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("stud=" + stud + "&pref=" + pref);
            }

            function addStudentCompany(stud, pref) {
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("errorDiv").innerHTML = "";
                            selectCompanies();
                            document.getElementById(stud + "2").hidden = false;
                        } else if (xhttp.responseText === "2") {
                            document.getElementById("errorDiv").innerHTML = "Student already added";
                        } else {
                            document.getElementById("errorDiv").innerHTML = "Please try again later";
                        }
                    }
                }
                xhttp.open("POST", "AdminAddStudent", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("stud=" + stud + "&pref=" + pref);
            }

        </script>
    </head>
    <body onload="loadCompany()">
        <div id='cssmenu'>
            <ul>
                <li class="liclass"><a onclick="loadCompany()" href='#'><span>Manage Companies</span></a></li>
                <li class="liclass"><a onclick="loadStudent()" href='#'><span>Student Preferences</span></a></li>
                <li class="liclass"><a onclick="manageStudent()" href='#'><span>Manage Students</span></a></li>
                <li class="liclass"><a href='AdminStream.jsp'><span>Manage Preferences</span></a></li>
                <li class="liclass"><a href="Logout"><label id="labelhover">Logout</label></a></li>
                <li class="liclass2"><label>You are logged in as <%= name%></label></li>
            </ul>
        </div>

        <br>
        <div class="error2" id="errorDiv"></div>
        <div id="demo">

        </div>
    </body>
</html>
