<%@ page language="java" contentType="text/html; charset=utf-8" import="dao.*" pageEncoding="utf-8" %>
<%@ page import="metier.Utilisateur" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.SeanceCours" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>Home</title>
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
        <div class="w3-col s8 w3-bar">>
            <% Utilisateur user = (Utilisateur) request.getSession().getAttribute("Utilisateur");%>
            <c:if test="${!empty Utilisateur}">
                <span>Bienvenue, <strong><%=user.getPrenom()%></strong></span><br>
                <a href="monProfil" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
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

        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>Consulter les &eacute;tudiants</a>
        <c:if test="${Utilisateur.typeU.equals('Etudiant')}">
        <a href="ctrlJustificatif" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>D&eacute;poser un justificatif</a>
        </c:if>

        <a href=# class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>Consulter le r&eacute;cap des pr&eacute;sense</a>

        <a href="cours.do?m=list&source=static" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>Consulter le statisitque des cours </a>

        <c:if test="${Utilisateur.typeU.equals('Enseignant')}">
            <a href="cours.do?m=list&source=cours" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i>Consulter
                mes cours</a>
            <a href="cours.do?m=list" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>Consulter les &eacute;tudiants</a>
        </c:if>
        <c:if test="${Utilisateur.typeU.equals('Scolarite')}">
        <a href="ctrlValiderJ" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>Consulter les justificatif</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>Consulter le r&eacute;cap
            des pr&eacute;sence</a>
        </c:if>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bell fa-fw"></i>Consulter mes alertes</a>
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
    <div style="margin-left: 38%">
        <a id="arrLeft" class="w3-button w3-circle w3-ripple w3-black fa fa-arrow-left"></a>
        <%
            String date = (String)request.getAttribute("lundi");
            pageContext.setAttribute("date", date);
        %>
        <span id="semaine" >Semaine&nbsp${date}</span>
        <a id="arrRight" class="w3-button w3-circle w3-ripple w3-black fa fa-arrow-right"></a>
    </div>

    <div id="planning">
        <div class="w3-row-padding w3-margin-bottom">
            <div class="container">
                <div class="timetable-img text-center">
                </div>
                <div class="table-responsive">
                    <table class="table table-bordered text-center timetable">
                        <thead>
                        <tr class="bg-light-gray">
                            <th class="text-uppercase">Time
                            </th>
                            <th class="text-uppercase">Monday</th>
                            <th class="text-uppercase">Tuesday</th>
                            <th class="text-uppercase">Wednesday</th>
                            <th class="text-uppercase">Thursday</th>
                            <th class="text-uppercase">Friday</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<SeanceCours> edt = (List<SeanceCours>) request.getAttribute("edt");
                            pageContext.setAttribute("edt", edt);
                            if (edt == null) {
                                boolean ifCours = false;
                            }
                        %>
                        <tr>
                            <td class="align-middle">09:00am</td>
                            <td id="lundi9" rowspan="3">
                                <c:if test="${edt.get(0)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(0).idSC}">${edt.get(0).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                            <td id="mardi9" rowspan="3">
                                <c:if test="${edt.get(2)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(2).idSC}">${edt.get(2).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>

                            <td id="mercredi9" rowspan="3">
                                <c:if test="${edt.get(4)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(4).idSC}">${edt.get(4).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                            <td id="jeudi9" rowspan="3">
                                <c:if test="${edt.get(6)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(6).idSC}">${edt.get(6).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>

                            </td>
                            <td id="vendredi9" rowspan="3">
                                <c:if test="${edt.get(8)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(8).idSC}">${edt.get(8).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>

                            </td>
                        </tr>

                        <tr>
                            <td class="align-middle">10:00am

                        </tr>

                        <tr>
                            <td class="align-middle">11:00am</td>
                            <%--                            <td>--%>
                            <%--                                <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Management</span>--%>
                            <%--                                <div class="margin-10px-top font-size14">11:00-12:00</div>--%>
                            <%--                            </td>--%>

                        </tr>

                        <tr>
                            <td class="align-middle">12:00pm</td>
                            <td class="bg-light-gray">

                            </td>
                            <td>

                            </td>
                            <td>

                            </td>
                            <td>

                            </td>
                            <td class="bg-light-gray">

                            </td>
                        </tr>

                        <tr>
                            <td class="align-middle">01:00pm</td>
                            <td id="lundi13" rowspan="2">
                                <c:if test="${edt.get(1)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(1).idSC}">${edt.get(1).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                            <td id="mardi13" rowspan="2">
                                <c:if test="${edt.get(3)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(3).idSC}">${edt.get(3).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                            <td id="mercredi13" class="bg-light-gray" rowspan="2">
                                <c:if test="${edt.get(5)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(5).idSC}">${edt.get(5).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                            <td id="jeudi13" rowspan="2">
                                <c:if test="${edt.get(7)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(7).idSC}">${edt.get(7).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                            <td id="vendredi13" rowspan="2">
                                <c:if test="${edt.get(9)!=null}">
                                    <span class="w3-button w3-aqua cours"
                                          id="${edt.get(9).idSC}">${edt.get(9).getCours().getLibelles()}</span>

                                    <div class="font-size13"><%=user.getPrenom()%>
                                    </div>
                                </c:if>
                            </td>
                        </tr>

                        <tr>
                            <td class="align-middle">02:00pm</td>
                            <td>

                            </td>
                            <td>

                            </td>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <footer class="w3-container w3-padding-16 w3-light-grey">

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
<script type="text/javascript" src="resources/js/fctHome.js"></script>


</body>
</html>
