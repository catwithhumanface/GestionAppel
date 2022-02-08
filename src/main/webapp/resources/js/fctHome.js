/**
 *
 * @global
 */
var semaine = getLastMonday();

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
}

function jumpToFiche(idsc){
    window.location.replace("ficheAppelController?seance="+idsc);
}


/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("arrLeft").addEventListener("click", backOneWeek);
    document.getElementById("arrRight").addEventListener("click", fowardOneWeek);
    document.querySelectorAll('.w3-button').forEach(item => {
        item.addEventListener('click', event => {
            const idsc = item.id;
            jumpToFiche(idsc);
        })
    })

});