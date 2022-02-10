package ctrl;

import dao.*;
import metier.Presence;
import metier.SeanceCours;
import metier.Utilisateur;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class localTest {
    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    private int idE;
    private String nom;
    private String etatP;
    private String urlJ;


    public static void main(String[] args) throws IOException {


    }
}
