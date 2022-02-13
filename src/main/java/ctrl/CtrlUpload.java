package ctrl;

import dao.JustificatifService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static dao.JustificatifConstant.DownloadPath;
import static dao.JustificatifConstant.UPLOAD_DIRECTORY;

@WebServlet(name = "/ctrlUpload", value = "/ctrlUpload")
public class CtrlUpload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // String m = req.getParameter("m");
        //System.out.println("m :::: "+ m);
            Upload(req,resp);
    }

    protected void Upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JustificatifService serviceJ = JustificatifService.getInstance();
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
                        for (int i = 0; i < list.size(); i++) {
                            if(!list.get(i).isFormField()&&list.get(i).getSize()>0){
                                idUrl.put(list.get(i+1).getFieldName(),list.get(i+1).getString("UTF-8"));
                                idUrl.put(list.get(i+2).getFieldName(),list.get(i+2).getString("UTF-8"));
                                // input file
                                String fileName = list.get(i).getName();   // le nom de fichier
                                // obtenir le contenu de fichier
                                //path = DownloadPath+fileName;
                                int idE = Integer.parseInt(idUrl.get("idE"));
                                int idSC = Integer.parseInt(idUrl.get("idSC"));
                                serviceJ.Insert(idE,idSC,fileName);
                                InputStream is = list.get(i).getInputStream();

                                URL url = getClass().getClassLoader().getResource("test.txt");
                                File file = Paths.get(url.toURI()).toFile();
                                String absolutePath = file.getAbsolutePath();
                                absolutePath = absolutePath.substring(0,absolutePath.length()-8);
                                System.out.println("Le chemin trop bien: " +  absolutePath);

                                FileOutputStream fos = new FileOutputStream(absolutePath + fileName);
                                IOUtils.copy(is, fos);

                                //

                                //

                                // fermer le resource
                                fos.close();
                                is.close();

                            }
                        }
                    }
            }
        } catch (FileUploadException | URISyntaxException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("ctrlJustificatif");
    }
}

