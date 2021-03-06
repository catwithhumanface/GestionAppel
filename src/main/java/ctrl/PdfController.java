package ctrl;

import model.FicheAppelService;
import model.PdfService;
import metier.Presence;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PdfController", value = "/PdfController")

/**
 * Controller fait pour Ajax qui
 * Obtenir les requettes HTTP contient l'id de la séance de cours afin de générer la fichier PDF
 * @author Zijing Zhou
 * @param request paramètres entrants avec la demande qui contient l'identifiant de la séance de cours
 * @param response etat HTML 200, 500, 404, 400
 */
public class PdfController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idsc = request.getParameter("idsc");

        FicheAppelService service = FicheAppelService.getInstance();

        List<Presence> presences = service.getAppel(Integer.parseInt(idsc));


        PdfService pdf = new PdfService(presences);

        pdf.composePDF();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
