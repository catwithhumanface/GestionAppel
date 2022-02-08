/**
 *
 * @global
 */
var semaine = getLastMonday();
var lundi_matin = "7";
var lundi_aprem = "8";

function getLastMonday() {
    const date = new Date();
    const today = date.getDate();
    const dayOfTheWeek = date.getDay() - 1;
    const newDate = date.setDate(today - (dayOfTheWeek || 7));
    return new Date(newDate);
}

function backOneWeek() {
    var planningCanvas = document.getElementById("planning");
    var semaineSpan = document.getElementById("semaine");

    // planningCanvas.innerHTML = '';
    semaineSpan.innerHTML = '';

    var thisMonday = semaine;
    var lastMonday = new Date(thisMonday.setDate(thisMonday.getDate() - 7));

    semaine = lastMonday;

    semaineSpan.appendChild(document.createTextNode("semaine" + " " + lastMonday.getDate()
        + "-" + (lastMonday.getMonth() + 1)
        + "-" + lastMonday.getFullYear()))

    var lien7 = document.getElementById(lundi_matin);
    var lien8 = document.getElementById(lundi_aprem);
    lundi_matin = (parseInt(lien7.id)-2).toString();
    lundi_aprem = (parseInt(lien8.id)-2).toString();
    lien7.id = lundi_matin;
    lien8.id = lundi_aprem;

}


/**
 * Cette méthode "Ajax" affiche la liste des auteurs.
 *
 * Utilise la propriété 'responseXML' de l'objet XMLHttpRequest afin
 * de récupérer sous forme d'arbre DOM le document XML envoyé par le serveur.
 */
function fowardOneWeek() {

    var planningCanvas = document.getElementById("planning");
    var semaineSpan = document.getElementById("semaine");
    // planningCanvas.innerHTML = '';
    semaineSpan.innerHTML = '';

    var thisMonday = semaine;
    var lastMonday = new Date(thisMonday.setDate(thisMonday.getDate() + 7));

    semaine = lastMonday;

    semaineSpan.appendChild(document.createTextNode("semaine" + " " + lastMonday.getDate()
        + "-" + (lastMonday.getMonth() + 1)
        + "-" + lastMonday.getFullYear()))

    var lien7 = document.getElementById(lundi_matin);
    var lien8 = document.getElementById(lundi_aprem);
    lundi_matin = (parseInt(lien7.id)+2).toString();
    lundi_aprem = (parseInt(lien8.id)+2).toString();
    lien7.id = lundi_matin;
    lien8.id = lundi_aprem;
}

function jumpToFiche(idsc) {
    window.location.replace("ficheAppelController?seance=" + idsc);
}


/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("arrLeft").addEventListener("click", backOneWeek);
    document.getElementById("arrRight").addEventListener("click", fowardOneWeek);
    document.querySelectorAll('.cours').forEach(item => {
        item.addEventListener('click', event => {
            const idsc = item.id;
            jumpToFiche(idsc);
        })
    })

});