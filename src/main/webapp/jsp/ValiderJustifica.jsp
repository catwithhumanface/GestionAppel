<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" import="dao.*" pageEncoding="utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8" import="static dao.JustificatifConstant.DownloadPath"%>
<%@ page import="metier.Utilisateur" %>

<!DOCTYPE html>
<html>
<title>Déposer un jusitificatif</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/ficheAppel.css">

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
        <h5>Dashboard</h5>
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
        <h5><b><i class="fa fa-table"></i> Déposer un justificatif</b></h5>
    </header>

    <div class="w3-container">
        <table class="table middle">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Justificatif</th>
                <th scope="col">Valider</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${setJustifi}" var="row">
            <c:set var="pathparts" value="${fn:split(row.url, '\\\\')}" />                <!-- String[] with values "dir1", "dir2", "dir3" and "filename.xml" -->
            <c:set var="filename" value="${pathparts[fn:length(pathparts) - 1]}" /> <!-- Last item of String[]: "filename.xml" -->
            <c:set var="basename" value="${fn:split(filename, '.')[0]}" />          <!-- Result: "filename" -->
                 <form method="post" action = "ctrlValiderJ" >
                <tr>
                    <td><a href =<%=DownloadPath%>${filename}>${filename}</a></td>
                    <td> <button type = "submit">Valider</button></td>
                    <td><input type="hidden" name="idE" value =${row.idP.getIdE()} ></td>
                    <td><input type="hidden" name="idSC" value =${row.idP.getIdSC()} ></td>
                </tr>
                <form/>

            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="w3-container">
        <div class="w3-col w3-container s4 m4 l4"></div>
        <div class="w3-col w3-container s2 m4 l4">
        </div>
    </div>
    <br>
    <div class="w3-container w3-dark-grey w3-padding-32">
        <div class="w3-row">
            <div class="w3-container w3-third">
                <h5 class="w3-bottombar w3-border-green">Demographic</h5>
                <p>Language</p>
                <p>Country</p>
                <p>City</p>
            </div>
            <div class="w3-container w3-third">
                <h5 class="w3-bottombar w3-border-red">System</h5>
                <p>Browser</p>
                <p>OS</p>
                <p>More</p>
            </div>
            <div class="w3-container w3-third">
                <h5 class="w3-bottombar w3-border-orange">Target</h5>
                <p>Users</p>
                <p>Active</p>
                <p>Geo</p>
                <p>Interests</p>
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

</script>

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
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>

