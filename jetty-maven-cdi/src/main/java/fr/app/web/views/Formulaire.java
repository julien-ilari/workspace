package fr.app.web.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;





@XmlRootElement(name="formulaire")
@XmlAccessorType (XmlAccessType.FIELD)
public class Formulaire implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	

	@XmlElementWrapper(name="items")
    @XmlElement(name="item", type= ItemModel.class)
	private List<ItemModel> items = new ArrayList<>();



	public List<ItemModel> getItems() {
		return items;
	}



	public void setItems(List<ItemModel> items) {
		this.items = items;
	}
	
	



}
