<%@ page language="java" contentType="text/html; charset=utf-8" import="dao.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V6</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="resources/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="resources/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-t-85 p-b-20">
            <form class="login100-form validate-form" action="member.do?m=login" method="post">
					<span class="login100-form-title p-b-70">
						Bienvenue !
					</span>
                <span class="login100-form-avatar">
						<img src="resources/images/logoUt1.png" alt="AVATAR">
					</span>
                <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="Entrez le type de connexion">
                    <label class="margin-l30">Type de connexion</label>
                    <select class="w3-select" name="typeUser">
                        <option value="Etudiant">Etudiant</option>
                        <option value="Enseignant">Enseignant</option>
                        <option value="Scolarite">Scolarité</option>
                    </select>
                </div>
                <div class="wrap-input100 validate-input m-b-35" data-validate="Entrez votre identifiant">
                    <label></label>
                    <input class="input100" type="text" name="username">
                    <span class="focus-input100" data-placeholder="Identifiant"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-50" data-validate="Entrez votre mot de passe">
                    <label></label>
                    <input class="input100" type="password" name="pass">

                    <span class="focus-input100" data-placeholder="Mot de passe"></span>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" onclick="login()">
                        Se connecter
                    </button>
                </div>

                <ul class="login-more p-t-190">

                </ul>
            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="resources/vendor/bootstrap/js/popper.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="resources/vendor/daterangepicker/moment.min.js"></script>
<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="resources/js/main.js"></script>
<script>
    function login() {
        var $username = $("input[name='username']");
        var $password = $("input[name='pass']");
        if (!$username.val().trim()) {
            alert('Mettez votre identifiant.');
            $username.focus();
            return false;
        }
        if (!$password.val().trim()) {
            alert('Mettez votre mot de passe.');
            $password.focus();
            return false;
        }
    }
</script>
<script type="text/javascript">
    if (${rCode} == <%=UtilisateurSet.NO_ID%>) {
        alert('Cet identifiant nexiste pas');
    } else if (${rCode} == <%=UtilisateurSet.NO_PWD%>) {
        alert('Le mot de passe ou le type de connexion est incorrect');
    } else if (${rCode} == <%=UtilisateurSet.NO_CONNEXION%>) {
        alert('Votre session est échouée.');
    }
</script>
</body>
</html>