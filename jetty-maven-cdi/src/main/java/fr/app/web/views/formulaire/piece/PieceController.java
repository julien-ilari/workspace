package fr.app.web.views.formulaire.piece;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FilenameUtils;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.UploadedFile;

import fr.app.commons.base.exceptions.TechniqueException;
import fr.app.web.faces.interceptors.annotations.CatchException;
import fr.app.web.faces.interceptors.annotations.LogInterceptor;
import fr.app.web.views.formulaire.PieceType;

@ViewScoped
public class PieceController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private static final String message = "paramètre null.";
	

	public void testNotNUll(@NotNull final String test) throws TechniqueException{
		
		
		
		
		
		
	}



	/**
	 * Liste des pièces jointes (scan Antivirus OK !)
	 */
	private Map<PieceType, Map<String,PieceModel>> pieces;	
	
	@PostConstruct
	public void pieceControllerPostConstruct(){
		
		pieces = new HashMap<PieceType, Map<String,PieceModel>>();
		pieces.put(PieceType.ATTENDUE, new HashMap<String,PieceModel>());
		pieces.put(PieceType.OPTIONELLE, new HashMap<String,PieceModel>());
		
	}
	
	
	
	public Map<String,PieceModel> getPiecesAttendues(){
		return pieces.get(PieceType.ATTENDUE);
	}
	
	public Map<String,PieceModel> getPiecesOptionelles(){
		return pieces.get(PieceType.OPTIONELLE);
	}
	
	
	/**
	 * Reinitialisation des fichiers envoyés par l'utilisateur
	 */
	public void reset() {
		for (Entry<PieceType, Map<String, PieceModel>> entry : pieces.entrySet()) {
			entry.getValue().clear();
		}
	}
	
	public void ajouterPiece(PieceType typePiece,  UploadedFile  uploadedFile ) throws IOException {
		validerFichier();
		
		
		PieceModel pieceModel = new PieceModel(uploadedFile);
		
		// Enregistrement du fichier dans un dossier temporaire
        Path folder = Paths.get("C:\\Users\\33671\\Documents");
        String filename = FilenameUtils.getBaseName(pieceModel.getFileName()); 
        String extension = FilenameUtils.getExtension(pieceModel.getFileName());
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);
        
        pieces.get(typePiece).put(pieceModel.getFileName(), pieceModel);
        
        try (InputStream input = pieceModel.getUploadedFile().getInputstream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }
        
	}
	
	
	
	
	private void validerFichier() {
		// scan en ligne
		
		// Test extension valide
		
		// Test Taille max du fichier
		
//		FacesMessage msg = new FacesMessage("Pièce validée.",uploadedFile.getFileName());
//        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
        
		
	}

	public Map<PieceType, Map<String,PieceModel>> getPieces() {
		return pieces;
	}
	
}
