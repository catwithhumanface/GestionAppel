package dao;

public class FicheAppelUtile {

    public FicheAppelUtile() {
    }

    /**
     * Génération de la liste déroulante à partir de l'état de présence
     *
     * @param etatPres     L'etat de présence
     * @param activateFlag boolean pour indiqué si la liste déroulante est désactivé ou non
     * @return String contient code HTML à afficher
     */
    public String outputSelector(String etatPres, boolean activateFlag) {

        String tdSelect = "";


        if (activateFlag) {
            tdSelect += "<td>" +
                    "<select class='w3-select'>";
        } else {
            tdSelect += "<td>" +
                    "<select class='w3-select' disabled>";
        }

        if (etatPres.equals("present")) {
            tdSelect = "<option value='default'>------</option>" +
                    "<option value='Present' selected>Present</option>" +
                    "<option value='Retard'>Retard</option>" +
                    "<option value='Absent'>Absent</option>" +
                    "</select></td>" +
                    "</tr>";
        } else if (etatPres.equals("retard")) {
            tdSelect = "<option value='default'>------</option>" +
                    "<option value='Present'>Present</option>" +
                    "<option value='Retard' selected>Retard</option>" +
                    "<option value='Absent'>Absent</option>" +
                    "</select></td>" +
                    "</tr>";
        } else if (etatPres.equals("absent")) {
            tdSelect = "<option value='default'>------</option>" +
                    "<option value='Present'>Present</option>" +
                    "<option value='Retard'>Retard</option>" +
                    "<option value='Absent' selected>Absent</option>" +
                    "</select></td>" +
                    "</tr>";
        } else {
            tdSelect = "<option value='default' selected>------</option>" +
                    "<option value='Present'>Present</option>" +
                    "<option value='Retard'>Retard</option>" +
                    "<option value='Absent'>Absent</option>" +
                    "</select></td>" +
                    "</tr>";
        }

        return tdSelect;
    }
}
