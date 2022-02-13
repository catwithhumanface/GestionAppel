package dao;

import metier.Presence;
import metier.SeanceCours;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PdfService {

    private List<Presence> presences;
    private PDDocument document;
    private PDPage page;
    private PDFont font;
    private PDPageContentStream cont;

    public PdfService() {
    }

    public PdfService(List<Presence> presences) throws IOException {
        this.presences = presences;
        this.document = new PDDocument();
        this.page = new PDPage();
        this.document.addPage(page);

        this.font = PDType1Font.HELVETICA_BOLD;
    }

    public PDPageContentStream composeHeader(PDPageContentStream cont) throws IOException {
        cont.setFont(this.font, 12);
        cont.showText(String.format("%20s %10s %25s %10s %25s %10s %25s", "ID", "|", "Nom", "|", "Etat Present", "|", "Jutificatif"));
        cont.newLine();
        cont.showText(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        return cont;
    }

    public PDPageContentStream composeLine(PDPageContentStream cont, Presence p) throws IOException {
        cont.showText(String.format("%20s %10s %25s %10s %25s %10s %25s", p.getEtudiant().getIdE(), "|",
                p.getEtudiant().getNom() + " " + p.getEtudiant().getPrenom(), "|",
                p.getEtatP(), "|", p.getUrl()));
        return cont;
    }

    public void composePDF() throws IOException {
        this.cont = new PDPageContentStream(this.document, this.page);

        cont.beginText();

        cont.setFont(PDType1Font.TIMES_ROMAN, 12);
        cont.setLeading(14.5f);


        cont.newLineAtOffset(25, 700);
        String line0 = "                                                               Fiche d'Appel de " + presences.get(0).getSeanceCours().getCours().getLibelles();
        cont.showText(line0);
        cont.newLine();

//        cont.newLineAtOffset(25, 600);
        String line1 = String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------");
        cont.showText(line1);
        cont.newLine();

        String line2 = String.format("%20s %10s %25s %10s %25s %10s %25s", "ID", "|", "Nom", "|", "Etat Present", "|", "Jutificatif");
        cont.showText(line2);
        cont.newLine();


        String line3 = String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------");
        cont.showText(line3);
        cont.newLine();

        for (Presence p : this.presences) {
            String line = String.format("%20s %10s %25s %10s %25s %10s %25s", p.getEtudiant().getIdE(), " ",
                    p.getEtudiant().getNom() + " " + p.getEtudiant().getPrenom(), " ",
                    p.getEtatP(), " ", p.getUrl() == null ? "" : p.getUrl());
            cont.showText(line);
            cont.newLine();
        }

        cont.endText();
        cont.close();
        System.out.println(JustificatifConstant.PDF_DIRECTORY + "temp"+presences.get(0).getSeanceCours().getIdSC()+".pdf");
        document.save(JustificatifConstant.PDF_DIRECTORY + "temp"+presences.get(0).getSeanceCours().getIdSC()+".pdf");
        System.out.println("!!!!!!!");

        document.close();

    }
}
