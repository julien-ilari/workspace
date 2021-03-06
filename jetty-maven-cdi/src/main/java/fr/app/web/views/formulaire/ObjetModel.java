package fr.app.web.views.formulaire;

import java.io.Serializable;
import java.util.Locale;

public class ObjetModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String nom;

	public ObjetModel(String id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	@Override
    public boolean equals(Object other) {
        return (other instanceof ObjetModel) && (id != null)
            ? id.equals(((ObjetModel) other).id)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
            ? (this.getClass().hashCode() + id.hashCode())
            : super.hashCode();
    }

    @Override
    public String toString() {
    	return String.format(Locale.FRANCE,"SujetModel['%1$s', '%2$s']", id, nom);
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
