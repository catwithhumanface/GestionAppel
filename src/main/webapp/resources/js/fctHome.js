/**
 *
 * @global
 */
var semaine = Date.parse(document.getElementById("semaine").innerHTML);

function getLastMonday() {
    const date = new Date();
    const today = date.getDate();
    const dayOfTheWeek = date.getDay() - 1;
    const newDate = date.setDate(today - (dayOfTheWeek || 7));
    return new Date(newDate);
}

function jumpToFiche(idsc) {
    window.location.href = "ficheAppelController?m=show&seance=" + idsc;
}

function jumpBack() {
    var semaineSpan = document.getElementById("semaine");
    console.log(semaineSpan);
    var thisMonday = new Date(semaineSpan.innerHTML);
    var lastMonday = new Date(thisMonday.setDate(thisMonday.getDate() - 7));
    semaine = lastMonday;
    console.log(semaine.getFullYear() + "-" + semaine.getMonth() + 1 + "-" + semaine.getDate());
    window.location.replace("homeController?semaine=" + lastMonday.getFullYear() + "-" + (lastMonday.getMonth() + 1) + "-" + lastMonday.getDate());
}

function jumpFoward() {
    var semaineSpan = document.getElementById("semaine");
    var thisMonday = new Date(semaineSpan.innerHTML);
    var nextMonday = new Date(thisMonday.setDate(thisMonday.getDate() + 7));

    semaine = nextMonday;
    console.log(semaine.getFullYear() + "-" + semaine.getMonth() + 1 + "-" + semaine.getDate());
    window.location.replace("homeController?semaine=" + nextMonday.getFullYear() + "-" + (nextMonday.getMonth() + 1) + "-" + nextMonday.getDate());
}




/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("arrLeft").addEventListener("click", jumpBack);
    document.getElementById("arrRight").addEventListener("click", jumpFoward);
    document.querySelectorAll('.cours').forEach(item => {
        item.addEventListener('click', event => {
            const idsc = item.id;
            console.log("123123123")
            jumpToFiche(idsc);
        })
    })

});