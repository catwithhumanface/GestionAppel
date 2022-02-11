<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="dao.FicheAppelService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="metier.Utilisateur" %>
<%@ page import="metier.SeanceCours" %>
<!DOCTYPE html>
<html>
<title>Fiche d'Appels</title>
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
        <%
            SeanceCours sc = (SeanceCours) request.getSession().getAttribute("sc");
            pageContext.setAttribute("sc",sc);
        %>
        <h5><b><i class="fa fa-table"></i> Fiche d'Appel ${sc.cours.libelles} de ${sc.dateSeance} &#224 ${sc.heureDeb}h</b> <b><a id="down"
                                                                       class="w3-button w3-ripple w3-blue fa fa-file-pdf-o">Generate</a></b>
        </h5>
        <a id="idsc" style="display: none">${sc.idSC}</a>
    </header>

    <div class="w3-container">
        <table class="table middle">
            <thead class="thead-dark">
            <tr>
                <th scope="col">&#8470 Etudiant</th>
                <th scope="col">Photo</th>
                <th scope="col">Nom Pr&eacute;nom</th>
                <th scope="col">Etat Pr&eacute;sence</th>
                <th scope="col">Justificatif</th>
            </tr>
            </thead>
            <tbody>
            <%
                String msg_e = (String) request.getAttribute("msg_e");
                if (msg_e != null) {
                    out.println("<div>" + msg_e + "</div>");
                }
                /**
                 * Affichage de la liste d'appel
                 */
                //FicheAppelService utile = FicheAppelService.getInstance()   ;
                List<Object[]> l = (List<Object[]>) request.getAttribute("listeAppel");
//                String l1[] = {"21912808", "ZHOU", "Zijing", "present", "File"};
//                l.add(l1);
                pageContext.setAttribute("activateFlag",
                        Boolean.parseBoolean(request.getParameter("activateFlag")));
                pageContext.setAttribute("liste", l);
            %>
            <c:forEach items="${listeAppel}" var="row">
                <tr>
                    <th>${row[0]}</th>
                    <td> <img src=${row[5]} class="w3-circle" style="width:150px; margin-left:15%;"></td>
                    <td>${row[1]}&nbsp${row[2]}</td>
                    <td>
                        <select id="${row[0]}" class='w3-select' name="etatP[]"
                                <c:if test="${!activateFlag}">disabled</c:if> >
                            <option value="Present"
                                    <c:if test="${row[3]=='Present'}">selected</c:if> >Present
                            </option>
                            <option value="Retard"
                                    <c:if test="${row[3]=='Retard'}">selected</c:if> >Retard
                            </option>
                            <option value="Absent"
                                    <c:if test="${row[3]=='Absent'}">selected</c:if> >Absent
                            </option>
                        </select>
                    </td>
                    <td><a class="fa fa-file" aria-hidden="true" href="resources/images/avatar-01.jpg"></a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="w3-container">
        <div class="w3-col w3-container s6 m6 l6">
            <button class="w3-btn w3-white w3-border w3-border-green w3-round-xlarge" onclick="saveFiche()"
                    style="margin-left: 35%" <c:if test="${!activateFlag}">disabled</c:if>> Enregistrer
            </button>
        </div>
        <div class="w3-col w3-container s6 m6 l6">
            <button class="w3-btn w3-white w3-border w3-border-green w3-round-xlarge" onclick="validateFiche()"
                    style="margin-left: 40%"
                    <c:if test="${!activateFlag}">disabled</c:if>>&nbsp&nbspValider&nbsp&nbsp
            </button>
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
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    function saveFiche() {
        console.log("save execute");
        var result = "";
        var idSc = ${idSc};

        $('select[name="etatP[]"]').each(function () {
            let id = this.id;
            let value = this.value;
            result = result + "/" + id + "!" + value;
            console.log(result);
        });
        window.location.href = "ficheAppelController?m=save&idSc=" + idSc + "&result=" + result;
    }

    function validateFiche() {
        console.log("validate execute");
        var result = "";
        var idSc = ${idSc};

        $('select[name="etatP[]"]').each(function () {
            let id = this.id;
            let value = this.value;
            result = result + "/" + id + "!" + value;
            console.log(result);
        });
        window.location.href = "ficheAppelController?m=validate&idSc=" + idSc + "&result=" + result;
    }
</script>
<script type="text/javascript" src="resources/js/fctFicheAppel.js"></script>
</body>
</html>

