package ctrl;

import dao.FicheAppelDao;
import dao.FicheAppelService;
import dao.PdfService;
import metier.Presence;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PdfController", value = "/PdfController")
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
