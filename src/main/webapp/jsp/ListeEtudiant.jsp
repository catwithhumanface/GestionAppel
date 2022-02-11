
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="metier.Utilisateur" %>
<%@ page import="metier.SeanceCours" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="metier.Etudiant" %>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

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
    <span class="w3-bar-item w3-right">Gestion d'appel</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
    <div class="w3-container w3-row">
        <div class="w3-col s4">
            <img src="resources/images/avatar-01.jpg" class="w3-circle w3-margin-right" style="width:46px">
        </div>

        <div class="w3-col s8 w3-bar">>
            <%
                Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");
                pageContext.setAttribute("user", user);
                pageContext.setAttribute("typeU", user.getTypeU());
            %>
            <c:if test="${!empty user}">
                <span>Bienvenue, <strong><%=user.getPrenom()%></strong></span><br>
                <a id="typeU" style="display: none">${typeU}</a>
                <a href="member.do?m=profil" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
            </c:if>
            <c:if test="${empty user}">

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
            <a href="ctrlJustificatif" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>&nbsp
                D&eacute;poserun justificatif</a>
        </c:if>
        <c:if test="${Utilisateur.typeU.equals('Enseignant')}">
            <a href="cours.do?m=list" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i>&nbsp
                Consulter mes cours</a>
            <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>&nbsp Consulter les
                &eacute;tudiants</a>
        </c:if>
        <c:if test="${Utilisateur.typeU.equals('Scolarite')}">
            <a href="ctrlValiderJ" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>&nbsp
                Consulter
                les justificatif</a>
        </c:if>
        <a
                <c:if test="${Utilisateur.typeU.equals('Etudiant')}">href="<%out.println("recapHebdoController?ide="+user.getIdE());%>"
        </c:if> <c:if test="${Utilisateur.typeU.equals('Enseignant')}">href="listeEtudiantController"
                </c:if>class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>&nbsp Consulter le r&eacute;cap
            hebdomadaire</a>
        <c:if test="${Utilisateur.typeU.equals('Etudiant')}">
            <a href="etudiant.do?m=showab" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>&nbsp
                Consulter mes absences</a>
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
        <%
            SeanceCours sc = (SeanceCours) request.getSession().getAttribute("sc");
            pageContext.setAttribute("sc", sc);
        %>
        <h5><b><i class="fa fa-table"></i> Recpulatif Trismestre</b>
        </h5>
        <a id="idsc" style="display: none">${sc.idSC}</a>
    </header>

    <div class="w3-container">
        <table class="table middle">
            <thead class="thead-dark">
            <tr>
                <th scope="col">&#8470 Etudiant</th>
                <th scope="col">Nom Pr&eacute;nom</th>
                <th scope="col">Present</th>
                <th scope="col">Retard</th>
                <th scope="col">Absent</th>
            </tr>
            </thead>
            <tbody>
            <%
                String msg_e = (String) request.getAttribute("msg_e");
                if (msg_e != null) {
                    out.println("<div>" + msg_e + "</div>");
                }
                Map<Etudiant, List<Integer>> map = (Map<Etudiant, List<Integer>>) request.getAttribute("rapport");
                pageContext.setAttribute("map", map);

                if (user.getTypeU().equals("Enseignant")) {
                    for (Etudiant etudiant : map.keySet()) {
            %>
            <tr>
                <th><% out.println(etudiant.getIdE()); %></th>
                <td><a href="<%out.println("recapHebdoController?ide="+etudiant.getIdE());%>"><% out.println(etudiant.getNom()+" "+etudiant.getPrenom()); %></a></td>
                <td class="w3-text-green"><% out.println(map.get(etudiant).get(0)); %></td>
                <td class="w3-text-yellow"><% out.println(map.get(etudiant).get(1)); %></td>
                <td class="w3-text-red"><% out.println(map.get(etudiant).get(2)); %></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>


    <br>

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
