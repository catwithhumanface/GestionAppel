<%@ page import="metier.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  import="dao.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/css/timetable.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
    html, body, h1, h2, h3, h4, h5 {
        font-family: "Raleway", sans-serif
    }
</style>
<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
    <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i
            class="fa fa-bars"></i> Menu
    </button>
    <span class="w3-bar-item w3-right">Logo</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
    <div class="w3-container w3-row">
        <div class="w3-col s4">
            <img src="resources/images/avatar-01.jpg" class="w3-circle w3-margin-right" style="width:46px">
        </div>
        <div class="w3-col s8 w3-bar">
            <%
                Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");
                pageContext.setAttribute("typeU", user.getTypeU());
            %>
            <c:if test="${!empty Utilisateur}">
                <span>Bienvenue, <strong><%=user.getPrenom()%></strong></span><br>
                <a id="typeU" style="display: none">${typeU}</a>
                <a href="member.do?m=profil" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
            </c:if>
            <c:if test="${empty Utilisateur}">
                <a href="member.do?m=form"><span><strong>Se connecter</strong></span></a><br>
            </c:if>
        </div>
    </div>
    <hr>
    <div class="w3-container">
        <h5>Mon emploi du temps</h5>
    </div>
    <div class="w3-bar-block">
        <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
           onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>Close Menu</a>
        <a href="homeController" class="w3-bar-item w3-button w3-padding"><i class="fa fa-calendar"></i>&nbsp Emploi du
            temps</a>
        <c:if test="${Utilisateur.typeU.equals('Etudiant')}">
            <a href="ctrlJustificatif" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>D&eacute;poser
                un justificatif</a>
        </c:if>
        <c:if test="${Utilisateur.typeU.equals('Enseignant')}">
            <a href="cours.do?m=list" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i>&nbsp
                Consulter mes cours</a>
            <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>&nbsp Consulter les
                &eacute;tudiants</a>
        </c:if>
        <c:if test="${Utilisateur.typeU.equals('Scolarite')}">
            <a href="ctrlValiderJ" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>Consulter
                les justificatif</a>
            <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>Consulter le r&eacute;cap
                des pr&eacute;sence</a>
        </c:if>
        <c:if test="${Utilisateur.typeU.equals('Etudiant')}">
            <a href="etudiant.do?m=showab" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>Consulter mes absences</a>
        </c:if>
    </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

    <!-- Header -->
    <header class="w3-container" style="padding-top:22px">
        <h5><b><i class="fa fa-dashboard"></i> Mon emploi du temps</b></h5>
    </header>


    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2">
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#contains">Contains</a></li>
                            <li><a href="#its_equal">It's equal</a></li>
                            <li><a href="#greather_than">Greather than ></a></li>
                            <li><a href="#less_than">Less than < </a></li>
                            <li class="divider"></li>
                            <li><a href="#all">Anything</a></li>
                        </ul>
                    </div>
                    <input type="hidden" name="search_param" value="all" id="search_param">
                    <input type="text" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
                </div>
            </div>
        </div>
    </div>


    <!-- Footer -->
    <footer class="w3-container w3-padding-16 w3-light-grey">
        <h4>FOOTER</h4>
        <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
    </footer>

    <!-- End page content -->
</div>

<script>
    // Get the Sidebar
    var mySidebar = document.getElementById("mySidebar");

    // Get the DIV with overlay effect
    var overlayBg = document.getElementById("myOverlay");

    // Toggle between showing and hiding the sidebar, and add overlay effect
    function w3_open() {
        if (mySidebar.style.display === 'block') {
            mySidebar.style.display = 'none';
            overlayBg.style.display = "none";
        } else {
            mySidebar.style.display = 'block';
            overlayBg.style.display = "block";
        }
    }

    // Close the sidebar with the close button
    function w3_close() {
        mySidebar.style.display = "none";
        overlayBg.style.display = "none";
    }
</script>

</body>
</html>
