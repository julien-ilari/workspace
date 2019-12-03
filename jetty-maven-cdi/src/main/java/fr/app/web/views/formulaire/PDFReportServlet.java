package fr.app.web.views.formulaire;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.app.web.views.formulaire.piece.PieceController;
import fr.app.web.views.formulaire.piece.PieceModel;


/**
 * Servlet pour la construction d'un fichier pdf en réponse
 * @author 33671
 *
 */
@WebServlet(name = "pdf", urlPatterns = {"/pdf"})
public class PDFReportServlet extends HttpServlet {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private PieceController pieceService;

	protected void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {
		
		String reqFileName = (String) req.getParameter("fileName");
		
//		FileInputStream fis = null;
		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		
		
		// Recherche du document dans la liste des fichiers envoyés par l'utilisateur
		for (Entry<PieceType, Map<String, PieceModel>> entry : pieceService.getPieces().entrySet()) {
			for (Entry<String, PieceModel> piece : entry.getValue().entrySet()) {
				String fileName = piece.getKey();
				PieceModel pieceModel = piece.getValue();
				
				if(reqFileName.equals(fileName)){
					input = new BufferedInputStream(pieceModel.getUploadedFile().getInputstream());
					response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
				}
				
			}
			
		}
		
		if(null == input) return;
//		String pathname = "C:\\Users\\33671\\Documents\\test.pdf";
//		File file = new File(pathname);

		try {
		    output = new BufferedOutputStream(response.getOutputStream());
		    byte[] buffer = new byte[8192];
		    int length;
		    while ((length = input.read(buffer)) > 0) {
		        output.write(buffer, 0, length);
		    }
		} finally {
		    if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
		    if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
		}
    
       
    }
}