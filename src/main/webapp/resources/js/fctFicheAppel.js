/**
 *
 * @global
 */

function getPdf() {
    var idsc = document.getElementById("idsc");
    var btn = document.getElementById("down");

    if (btn.innerHTML === "Generate") {
        var xhr = new XMLHttpRequest();

        // Requête au serveur avec les paramètres éventuels.
        xhr.open("GET", "pdfController?idsc="+idsc.innerHTML);

        // On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
        xhr.onload = function () {
            // Si la requête http s'est bien passée.
            if (xhr.status === 200) {
                // Elément html que l'on va mettre à jour.
                btn.innerHTML="download";
                btn.href="resources/outPutFiles/temp"+idsc+".pdf"
            }
        };

        // Envoie de la requête.
        xhr.send();
    }
}


/**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("down").addEventListener("click", getPdf);

});