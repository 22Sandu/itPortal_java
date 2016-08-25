<%-- 
    Document   : homeStaff
    Created on : Nov 9, 2015, 2:31:16 PM
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
        <link rel="stylesheet" href="CSS/thumbnail.css" >
        
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="JS/script.js"></script>
        <title>Staff Page</title>
        <%
            String name = null, type = null;

            if (session.getAttribute("username") == null || session.getAttribute("type") != "Staff") {
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
            function loadStudents() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("loadingArea").innerHTML = xhttp.responseText;
                        loadStudentList();
                        loadSelectedStudentList();
                        checkCheckBoxes();
                    }
                }
                xhttp.open("GET", "StaffStudents.jsp", true);
                xhttp.send();
            }
            
            function checkCheckBoxes(){
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText !== "") {
                            var str = xhttp.responseText.split(",");
                            var i;
                            for(i=0;i<str.length;i++){
                                 document.getElementById(str[i]).checked = true;
                            }
                           
                        } 
                    }
                }

                xhttp.open("POST", "StaffCheckBoxes", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();
            }

            function loadStudentList() {
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("stdList").innerHTML = "<tr><td colspan=\"3\">No students in the database.</td></tr>"
                        } else {
                            document.getElementById("stdList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "StaffStudentList", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();
            }

            function loadSelectedStudentList() {
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("stdStatusList").innerHTML = "<tr><td colspan=\"3\">No students in the database.</td></tr>"
                        } else {
                            document.getElementById("stdStatusList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "StaffStudentSelectedList", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();
            }

            function loadStudentStatus(key) {
                var val = '<%=name%>';
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("stdList").innerHTML = "<tr><td>No students assigned.</td></tr>"
                        } else {
                            document.getElementById("stdList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "staffStudentStates", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val);
            }

            function selectStudents() {
                var checkedBoxes = getCheckedBoxes("studentCheckBox");

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("stdStatusList").innerHTML = "<tr><td colspan=\"3\">No students assigned.</td></tr>"
                        } else {
                            document.getElementById("stdStatusList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "staffStudentStatusList", true);
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

            function saveSelectedList() {
                var checkedBoxes = getCheckedBoxes("studentCheckBox");

                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("ssl").src = "Icons/tick2.png";
                        } else {
                            document.getElementById("ssl").src = "Icons/crs2.png";
                        }
                    }
                }

                xhttp.open("POST", "saveSelectedList", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("checkedBoxes=" + checkedBoxes);
            }
            
            function loadStudListManage(){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("loadingArea").innerHTML = "<table><tr><td>Please try again later</td></tr><table>";
                        } else {
                            document.getElementById("loadingArea").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "StaffloadStudList", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();
            }
            
            function loadImagesCV(file){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("loadHere").innerHTML = "<table><tr><td>Please try again later</td></tr><table>";
                        } else {
                            document.getElementById("loadHere").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("GET", "StaffManageStudent.jsp?fileName="+file, true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send();               
            }

        </script>
    </head>
    <body onload="loadStudents()">
        <div id='cssmenu'>
            <ul>
                <li class="liclass"><a onclick="loadStudents()" href='#'><span>Student Status</span></a></li>
                <li class="liclass"><a onclick="loadStudListManage()" href='#'><span>Manage Curriculum Vitae</span></a></li>
                <li class="liclass"><a href="Logout"><label id="labelhover">Logout</label></a></li>
                <li class="liclass2"><label>You are logged in as <%= name%></label></li>
            </ul>
        </div>
        <div id="loadingArea">
            
        </div>
            <br><br>
            <div id="loadHere"></div>
    </body>
</html>
