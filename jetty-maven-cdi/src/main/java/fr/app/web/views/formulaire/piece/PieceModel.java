package fr.app.web.views.formulaire.piece;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import org.primefaces.model.UploadedFile;

/**
 * Représentation des informations de la pièce du formulaire à voir.
 * @Dependent Une nouvelle instance pour l'injection en cours.
 * 
 * @author Julien ILARI
 *
 */
@Dependent
public class PieceModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private UploadedFile uploadedFile;

	private PiecePlayer player;
	
	private String mimeType;

	public PieceModel(UploadedFile fichier) {
		this.uploadedFile = fichier;
	
		
	    if (fichier.getFileName().endsWith("png")) {
	        mimeType = "image/png";
	        player = PiecePlayer.IMAGE;
	    } else if (fichier.getFileName().endsWith("jpg") || fichier.getFileName().endsWith("jpeg")) {
	        mimeType = "image/jpeg";
	        player = PiecePlayer.IMAGE;
	    } else if (fichier.getFileName().endsWith("gif")) {
	        mimeType = "image/gif";
	        player = PiecePlayer.IMAGE;
	    } else if (fichier.getFileName().endsWith("pdf")) {
	        mimeType = "application/pdf";
	        player = PiecePlayer.PDF;
	    } else {
	        mimeType = "application/octet-stream";
	    }
	
	}
	
	
	
	public String getFileName() {
		return uploadedFile.getFileName();
	}



	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}



	public PiecePlayer getPlayer() {
		return player;
	}



	public void setPlayer(PiecePlayer player) {
		this.player = player;
	}



	public String getMimeType() {
		return mimeType;
	}



	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}



	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
	
	


}
