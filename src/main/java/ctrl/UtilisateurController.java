package ctrl;

import dao.FicheAppelService;
import dao.JustificatifService;
import dao.UtilisateurService;
import dao.UtilisateurSet;
import metier.Presence;
import metier.SeanceCours;
import metier.Utilisateur;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dao.JustificatifConstant.UPLOAD_DIRECTORY;
import static dao.JustificatifConstant.UPLOAD_DIRECTORY_PHOTO;

@WebServlet("/member.do")
public class UtilisateurController extends HttpServlet {
    UtilisateurService service = UtilisateurService.getInstance();

    /**
     * Obtenir les paramètres afin d'exécuter les méthodes correspendantes
     * @author Joohyun Ann
     * @param request paramètres entrants avec la demande
     * @param response page à rediriger
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if (m != null) {
            System.out.println(m);
            m = m.trim();
            if (m.equals("form")) {
                form(request, response);
            } else if (m.equals("login")) {
                login(request, response);
            }else if(m.equals("profil")){
                profil(request, response);
            }else if(m.equals("changerP")){
                modifP(request, response);
            }
        }
    }

    /**
     * Générer la page de la connexion
     * @author Joohyun Ann
     * @param request
     * @param response
     * @return page index ou page seanceCoursList
     */
    private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "index";
        response.sendRedirect(view);
    }

    /**
     * Générer la page d'accueil
     * @author Joohyun Ann
     * @param request username, pass, typeUser
     * @param response rCode
     * @return page index ou vers homeController
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String type = request.getParameter("typeUser");
        System.out.println(username + " " + pass + " " + type);
        if (username != null) username = username.trim();
        if (pass != null) pass = pass.trim();

        int rCode = service.checkLogin(username, pass, type);
        request.setAttribute("rCode", rCode);
        String view = "index";

        if (rCode == UtilisateurSet.PASS) {
            Utilisateur utilisateur = service.getUtilisateur(username, pass, type);
            HttpSession session = request.getSession();
            session.setAttribute("Utilisateur", utilisateur);
            response.sendRedirect("homeController");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        }
    }

    /**
     * Générer la page de mon profil
     * @author Joohyun Ann
     * @param request utilisateur
     * @param response rCode
     * @return page index ou monProfil
     */
    private void profil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        String view = "monProfil";
        if (utilisateur == null) {
            request.setAttribute("rCode", UtilisateurSet.NO_CONNEXION);
            view = "index";
        }
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    /**
     * Modifier la photo de profil
     * @author Joohyun Ann
     * @param req utilisateur
     * @param resp générer mon profil
     * @return page index ou monProfil
     */
    protected void modifP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("Utilisateur");
        Map<String,String> idUrl = new HashMap<String,String>();
        String path = "";
        try {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");

            boolean multipartContent = ServletFileUpload.isMultipartContent(req);

            if (multipartContent) {

                List<FileItem> list = upload.parseRequest(req);
                if (null != list) {
                    for (int i = 0; i < 2; i++) {
                        if(!list.get(i).isFormField()&&list.get(i).getSize()>0){
                            System.out.println("@@1");
                            idUrl.put(list.get(i+1).getFieldName(),list.get(i+1).getString("UTF-8"));
                            System.out.println(list.get(i+1).getFieldName());
                            // input file
                            String fileName = list.get(i).getName();   // le nom de fichier
                            // obtenir le contenu de fichier
                            path = UPLOAD_DIRECTORY_PHOTO+fileName;
                            String mail = idUrl.get("mailChange");
                            String pathPhoto = "resources/photoProfil/"+fileName;
                            service.update(mail, utilisateur,pathPhoto);

                            InputStream is = list.get(i).getInputStream();
                            FileOutputStream fos = new FileOutputStream(UPLOAD_DIRECTORY_PHOTO + fileName);
                            IOUtils.copy(is, fos);

                            // fermer le resource
                            fos.close();
                            is.close();

                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = req.getRequestDispatcher("member.do?m=profil");
        rd.forward(req, resp);
    }
}
