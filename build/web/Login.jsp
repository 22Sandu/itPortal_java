<%-- 
    Document   : Login
    Created on : Nov 9, 2015, 11:07:20 AM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/Login.css" />
        <title>Login</title>
    </head>
    <%
        String error = "";

        if (request.getAttribute("error") != null) {
            if (Integer.parseInt(request.getAttribute("error").toString()) == 1) {
                error = "Username or password incorrect.";
            } else if (Integer.parseInt(request.getAttribute("error").toString()) == 2) {
                error = "Please login to proceed.";
            }
        }
    %>
    <script>
        function loadFunc() {
            var error = "<%=error%>";
            document.getElementById("div1").innerHTML = error;
        }
    </script>
    <body onload="loadFunc()">
        <div align="center" style="padding-top: 40px;">
            <img src="Images/banner.png" width="450" height="100" alt="banner"/>

            <div class="shadowDiv">
            <form action="Login" method="POST">
                <h2 class="enjoy-css" >Login to Industry Portal</h2>
                <div class="error" id="div1"></div>
                <table align="center" style="width: 55%;text-align: right;padding-top: 5px;">
                    <tr>
                        <td style="height: 65px;"><label for="name" class="labels">Username</label></td>
                        <td style="height: 60px;padding-left: 15px;">
                            <input id="username" tabindex="1" type="text" name="username" value="" placeholder="Enter your Username" />
                        </td>
                    </tr>
                    <tr>
                        <td><label for="password" class="labels">Password</label></td>
                        <td>
                            <input id="password" type="password" name="password" value="" tabindex="1" placeholder="Enter your Password"  />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: left;padding-top: 10px;">
                            <a href="#" class="forpass">Forgot Password ?</a>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding-top: 15px;">
                            <input type="submit" value="Login" class="loginButton" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;padding-top: 20px;">
                            <a href="#" class="accountcreation">Create Account</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        </div>
    </body>
</html>
