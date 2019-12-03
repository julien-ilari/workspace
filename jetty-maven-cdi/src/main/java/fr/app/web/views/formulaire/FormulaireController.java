package fr.app.web.views.formulaire;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import fr.app.web.views.formulaire.piece.PieceController;
import fr.app.web.views.formulaire.piece.PieceModel;


@SessionScoped
@Named("formulaireController")
public class FormulaireController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PieceController pieceService;
	
	@Inject
	private FormulaireModel model;
	
	/**
	 * Permet de rattacher le composant html "selectOneMenu" pour les sujets
	 */
	private SelectOneMenu uiSelectOneSujet;
	
	/**
	 * Permet de rattacher le composant html "selectOneMenu" pour les objets
	 */
	private SelectOneMenu uiSelectOneObjet;
	
	/**
	 * Liste de selectItem des sujets disponibles
	 */
	private List<SelectItem> sujets = new ArrayList<>();
	
	/**
	 * Liste des objets disponibles pour un sujet 
	 * <ul>
	 * 	<li>clé : un sujet.</li>
	 *  <li>valeur : liste de selectItem des objets disponibles).</li>
	 * </ul>
	 */
	private Map<String, List<SelectItem>> objets = new HashMap<String, List<SelectItem>>();
	
	/**
	 * Fichier upoaded (scan à effectué sur le fichier)
	 */
	 private UploadedFile uploadedFile;
	 
	 /**
	  * preview Image
	  */	 
	 public String getImageContentsAsBase64() {
	    return Base64.getEncoder().encodeToString(uploadedFile.getContents());
	 }

	

	/**
	 * <h1>Event : Selection d'un item sur la liste</h1>
	 * <p>La méthode doit faire un reset pour charger la nouvelle configuration selectionnée</p> 
	 * @param event
	 */
	public void itemSelectEvent(AjaxBehaviorEvent event){
		// objet prendra la valeur null lors de l'appel de l'evenement sur la liste des sujets
		if(uiSelectOneSujet == event.getComponent()) {
			model.setObjet(null);
		}
		
		pieceService.reset();
		
	}
	
	public void handleFileUpload(FileUploadEvent event) throws IOException {
		uploadedFile =  event.getFile();
		
		// Ajout de la nouvelle pièce
		pieceService.ajouterPiece(PieceType.ATTENDUE, uploadedFile);
        
    }
	
	public StreamedContent getMedia() throws FileNotFoundException { 
		 final File initialFile = new File("C:\\Users\\33671\\Documents\\test.pdf");
	      final InputStream stream =  new DataInputStream(new FileInputStream(initialFile));
        return new DefaultStreamedContent(stream, "application/pdf");
    }
	
	public List<String> getImages(){
		List<String> images = new ArrayList<String>();
		for (Entry<String, PieceModel> entry : pieceService.getPieces().get(PieceType.ATTENDUE).entrySet()) {
			images.add(Base64.getEncoder().encodeToString(entry.getValue().getUploadedFile().getContents()));
		}
		return images;
	}

	
	
	/**
	 * Liste des objets en fonction du sujet.
	 * @param sujet
	 * @return
	 */
	public List<SelectItem> getObjets() {
		return objets.get(uiSelectOneSujet.getValue());
	}
	
	

	@PostConstruct
	public void postConstruct() {
		
		
		SelectItem sujet2 = new SelectItem("FAMILLE_02", "famille 02");
		SelectItem sujet3 = new SelectItem("FAMILLE_03", "famille 03");
		SelectItem sujet1 = new SelectItem("FAMILLE_01", "famille 01");
		
		sujets.add(sujet1);
		sujets.add(sujet2);
		sujets.add(sujet3);
		
		// Trier avec Lambda
		sujets.sort(new Comparator<SelectItem>() {

			@Override
			public int compare(SelectItem o1, SelectItem o2) {
				// TODO Auto-generated method stub
				return o1.getLabel().compareTo(o2.getLabel());
			}
				
		});
		
		
		List<SelectItem> objets1 = new ArrayList<>();
		objets1.add(new SelectItem("OBJET_01", "Objet 01"));
		objets1.add(new SelectItem("OBJET_02", "Objet 02"));
		objets1.add(new SelectItem("OBJET_03", "Objet 03"));
		objets.put((String) sujet1.getValue(), objets1);
		
		
		List<SelectItem> objets2 = new ArrayList<>();
		objets2.add(new SelectItem("OBJET_11", "Objet 11"));
		objets2.add(new SelectItem("OBJET_12", "Objet 12"));
		objets2.add(new SelectItem("OBJET_13", "Objet 13"));
		objets.put((String) sujet2.getValue(), objets2);
		
		
		List<SelectItem> objets3 = new ArrayList<>();
		objets3.add(new SelectItem("OBJET_01", "Objet 01"));
		objets3.add(new SelectItem("OBJET_02", "Objet 02"));
		objets3.add(new SelectItem("OBJET_03", "Objet 03"));
		objets.put((String) sujet3.getValue(), objets3);
		
		
	}
	
	public void loadBean() throws IOException {
		 if(!FacesContext.getCurrentInstance().isPostback()) {
	           // Chargement 
	     }
	}

	public FormulaireModel getModel() {
		return model;
	}

	public void setModel(FormulaireModel model) {
		this.model = model;
	}


	public List<SelectItem> getSujets() {
		return sujets;
	}


	public SelectOneMenu getUiSelectOneSujet() {
		return uiSelectOneSujet;
	}


	public void setUiSelectOneSujet(SelectOneMenu uiSelectOneSujet) {
		this.uiSelectOneSujet = uiSelectOneSujet;
	}


	public SelectOneMenu getUiSelectOneObjet() {
		return uiSelectOneObjet;
	}


	public void setUiSelectOneObjet(SelectOneMenu uiSelectOneObjet) {
		this.uiSelectOneObjet = uiSelectOneObjet;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}



	public PieceController getPieceService() {
		return pieceService;
	}

	




	

}
