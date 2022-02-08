<%@ page language="java" contentType="text/html; charset=utf-8"  import="dao.*" pageEncoding="utf-8"%>
<%@ page import="metier.Utilisateur" %>
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
        <div class="w3-col s8 w3-bar">>
            <% Utilisateur user = (Utilisateur)request.getSession().getAttribute("Utilisateur");%>
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
    <c:if test="${Utilisateur.typeU.equals('Enseignant')}">
        <a href="cours.do?m=list" class="w3-bar-item w3-button w3-padding"><i class="fa fa-eye fa-fw"></i>Consulter mes cours</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>Consulter les &eacute;tudiants</a>
    </c:if>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bullseye fa-fw"></i>D&eacute;poser un justificatif</a>
        <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-diamond fa-fw"></i>Consulter le r&eacute;cap des pr&eacute;sence</a>
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
    <div>
        <i class="fa fa-arrow-left dateTimetable"></i>
            2022-02-07
        <i class="fa fa-arrow-right"></i>
    </div>

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
                    <tr>
                        <td class="align-middle">09:00am</td>
                        <td>
                            <span class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16 xs-font-size13">Dance</span>
                            <div class="margin-10px-top font-size14">9:00-10:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td>
                            <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                            <div class="margin-10px-top font-size14">9:00-10:00</div>
                            <div class="font-size13 text-light-gray">Marta Healy</div>
                        </td>

                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">9:00-10:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td>
                            <span class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Dance</span>
                            <div class="margin-10px-top font-size14">9:00-10:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td>
                            <span class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Art</span>
                            <div class="margin-10px-top font-size14">9:00-10:00</div>
                            <div class="font-size13 text-light-gray">Kate Alley</div>
                        </td>
                    </tr>

                    <tr>
                        <td class="align-middle">10:00am</td>
                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">10:00-11:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td class="bg-light-gray">

                        </td>
                        <td>
                            <span class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Art</span>
                            <div class="margin-10px-top font-size14">10:00-11:00</div>
                            <div class="font-size13 text-light-gray">Kate Alley</div>
                        </td>
                        <td>
                            <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                            <div class="margin-10px-top font-size14">10:00-11:00</div>
                            <div class="font-size13 text-light-gray">Marta Healy</div>
                        </td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">10:00-11:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                    </tr>

                    <tr>
                        <td class="align-middle">11:00am</td>
                        <td>
                            <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                            <div class="margin-10px-top font-size14">11:00-12:00</div>
                        </td>
                        <td>
                            <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                            <div class="margin-10px-top font-size14">11:00-12:00</div>
                        </td>
                        <td>
                            <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                            <div class="margin-10px-top font-size14">11:00-12:00</div>
                        </td>
                        <td>
                            <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                            <div class="margin-10px-top font-size14">11:00-12:00</div>
                        </td>
                        <td>
                            <span class="bg-lightred padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Break</span>
                            <div class="margin-10px-top font-size14">11:00-12:00</div>
                        </td>
                    </tr>

                    <tr>
                        <td class="align-middle">12:00pm</td>
                        <td class="bg-light-gray">

                        </td>
                        <td>
                            <span class="bg-purple padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Art</span>
                            <div class="margin-10px-top font-size14">12:00-1:00</div>
                            <div class="font-size13 text-light-gray">Kate Alley</div>
                        </td>
                        <td>
                            <span class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Dance</span>
                            <div class="margin-10px-top font-size14">12:00-1:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">12:00-1:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td class="bg-light-gray">

                        </td>
                    </tr>

                    <tr>
                        <td class="align-middle">01:00pm</td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">1:00-2:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">1:00-2:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td class="bg-light-gray">

                        </td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">1:00-2:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                            <div class="margin-10px-top font-size14">1:00-2:00</div>
                            <div class="font-size13 text-light-gray">Marta Healy</div>
                        </td>
                    </tr>

                    <tr>
                        <td class="align-middle">02:00pm</td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">2:00-3:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">2:00-3:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="w3-panel">
        <div class="w3-row-padding" style="margin:0 -16px">
            <div class="w3-third">
                <h5>Regions</h5>
                <img src="/w3images/region.jpg" style="width:100%" alt="Google Regional Map">
            </div>
            <div class="w3-twothird">
                <h5>Feeds</h5>
                <table class="w3-table w3-striped w3-white">
                    <tr>
                        <td><i class="fa fa-user w3-text-blue w3-large"></i></td>
                        <td>New record, over 90 views.</td>
                        <td><i>10 mins</i></td>
                    </tr>
                    <tr>
                        <td><i class="fa fa-bell w3-text-red w3-large"></i></td>
                        <td>Database error.</td>
                        <td><i>15 mins</i></td>
                    </tr>
                    <tr>
                        <td><i class="fa fa-users w3-text-yellow w3-large"></i></td>
                        <td>New record, over 40 users.</td>
                        <td><i>17 mins</i></td>
                    </tr>
                    <tr>
                        <td><i class="fa fa-comment w3-text-red w3-large"></i></td>
                        <td>New comments.</td>
                        <td><i>25 mins</i></td>
                    </tr>
                    <tr>
                        <td><i class="fa fa-bookmark w3-text-blue w3-large"></i></td>
                        <td>Check transactions.</td>
                        <td><i>28 mins</i></td>

                        <td class="bg-light-gray">

                        </td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">2:00-3:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                            <div class="margin-10px-top font-size14">2:00-3:00</div>
                            <div class="font-size13 text-light-gray">Marta Healy</div>
                        </td>

                    </tr>

                    <tr>
                        <td class="align-middle">03:00pm</td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">3:00-4:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">3:00-4:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td class="bg-light-gray">

                        </td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">3:00-4:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                            <div class="margin-10px-top font-size14">3:00-4:00</div>
                            <div class="font-size13 text-light-gray">Marta Healy</div>
                        </td>
                    </tr>

                    <tr>
                        <td class="align-middle">04:00pm</td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">4:00-5:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-yellow padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Music</span>
                            <div class="margin-10px-top font-size14">4:00-5:00</div>
                            <div class="font-size13 text-light-gray">Ivana Wong</div>
                        </td>
                        <td class="bg-light-gray">

                        </td>
                        <td>
                            <span class="bg-pink padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">English</span>
                            <div class="margin-10px-top font-size14">4:00-5:00</div>
                            <div class="font-size13 text-light-gray">James Smith</div>
                        </td>
                        <td>
                            <span class="bg-green padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">Yoga</span>
                            <div class="margin-10px-top font-size14">4:00-5:00</div>
                            <div class="font-size13 text-light-gray">Marta Healy</div>
                        </td>
                    </tr>

                    </tbody>
                </table>
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

</body>
</html>
