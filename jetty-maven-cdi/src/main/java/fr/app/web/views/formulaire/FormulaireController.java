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
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Utils;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import fr.app.commons.base.exceptions.TechniqueException;
import fr.app.web.faces.exceptions.MetierException;
import fr.app.web.faces.interceptors.annotations.CatchException;
import fr.app.web.faces.interceptors.annotations.LogInterceptor;
import fr.app.web.views.formulaire.piece.PieceController;
import fr.app.web.views.formulaire.piece.PieceModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@CatchException
@ViewScoped
@Named("formulaireController")
public class FormulaireController implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject @Param
	private String initCode;
	
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
	 * Liste des objets disponibles pour un sujet 
	 * <ul>
	 * 	<li>clé : un sujet.</li>
	 *  <li>valeur : liste de selectItem des objets disponibles).</li>
	 * </ul>
	 */
	private Map<SujetModel, List<ObjetModel>> objets = new HashMap<SujetModel, List<ObjetModel>>();
	
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
	 * @throws TechniqueException 
	 */

	public void itemSelectEvent(AjaxBehaviorEvent event) throws TechniqueException{
		// objet prendra la valeur null lors de l'appel de l'evenement sur la liste des sujets
		if(uiSelectOneSujet == event.getComponent()) {
			model.setObjet(null);
		}
		
		pieceService.reset();
		
		log.debug("Message test");
		
		pieceService.testNotNUll(null);
		
		
	}
	
	private static final String message = "paramètre null.";
	
	

	@LogInterceptor
	@CatchException
	public void testNotNUll(@NotNull(message=message) final String test) throws TechniqueException{
		
		
		
		//ValidationInterceptor
		
	}
	
	@CatchException
	public void handleFileUpload(FileUploadEvent event) throws MetierException  {
		
		
		throw new MetierException("Grosse pute application", "piece" + event.getComponent().getAttributes().get("rowId"));
//		uploadedFile =  event.getFile();
//		
//		// Ajout de la nouvelle pièce
//		pieceService.ajouterPiece(PieceType.ATTENDUE, uploadedFile);
	
        
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
	public List<ObjetModel> getObjets() {
		return objets.get(uiSelectOneSujet.getValue());
	}
	
	

	public List<SujetModel> getSujets() {
		 List<SujetModel> sujets = new ArrayList<>(objets.keySet());
		// Trier avec Lambda
		sujets.sort(new Comparator<SujetModel>() {
			@Override
			public int compare(SujetModel o1, SujetModel o2) {
				return o1.getNom().compareTo(o2.getNom());
			}
				
		});

		
		return sujets;
	}



	@PostConstruct
	public void postConstruct() {
		
		
		SujetModel sujet2 = new SujetModel("FAMILLE_02", "famille 02");
		SujetModel sujet3 = new SujetModel("FAMILLE_03", "famille 03");
		SujetModel sujet1 = new SujetModel("FAMILLE_01", "famille 01");
		
		
		
		
		
		
		List<ObjetModel> objets1 = new ArrayList<>();
		objets1.add(new ObjetModel("OBJET_01", "Objet 01"));
		objets1.add(new ObjetModel("OBJET_02", "Objet 02"));
		objets1.add(new ObjetModel("OBJET_03", "Objet 03"));
		objets.put(sujet1, objets1);
		
		
		List<ObjetModel> objets2 = new ArrayList<>();
		objets2.add(new ObjetModel("OBJET_11", "Objet 11"));
		objets2.add(new ObjetModel("OBJET_12", "Objet 12"));
		objets2.add(new ObjetModel("OBJET_13", "Objet 13"));
		objets.put(sujet2, objets2);
		
		
		List<ObjetModel> objets3 = new ArrayList<>();
		objets3.add(new ObjetModel("OBJET_01", "Objet 01"));
		objets3.add(new ObjetModel("OBJET_02", "Objet 02"));
		objets3.add(new ObjetModel("OBJET_03", "Objet 03"));
		objets.put(sujet3, objets3);
		
		
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



	public String getInitCode() {
		return initCode;
	}

	
	private Part file;
    private byte[] content;

    public void read() throws IOException {
        content = Utils.toByteArray(file.getInputStream());
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public byte[] getContent() {
        return content;
    }

	

}
