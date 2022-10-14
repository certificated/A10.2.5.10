package application.blueprint;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Posten {

	private IntegerProperty artid;
	private StringProperty artname;
	private StringProperty artbeschreibung;
	private  Date artab;
	public Date getArtab() {
		return artab;
	}

	public void setArtab(Date artab) {
		this.artab = artab;
	}

	private IntegerProperty artpreis;

	
	public Posten() {
		
		this.artid = new SimpleIntegerProperty();
		this.artname = new SimpleStringProperty();
		this.artbeschreibung = new SimpleStringProperty();
		this.artab = new Date();
		this.artpreis = new SimpleIntegerProperty();
	}
	
	public int getartid(){
		return artid.get();
	}
	
	public void setartid(int artID) {
		this.artid.set(artID);

	}
	
	public IntegerProperty artidproperty() {
		return artid;
		
	}
	
	public String getartname() {
		return artname.get();
	}

	public void setartname(String artName) {
		this.artname.set(artName);

		
	}
	
	public StringProperty artnameproperty() {
		return artname;
	}
	
	
	public String getartbeschreibung() {
		return artbeschreibung.get();
	}
	
	public void setartbeschreibung(String artBeschreibung) {
		this.artbeschreibung.set(artBeschreibung);
	}
	
	public StringProperty artbeschreibungproperty() {
		return artbeschreibung;
	}
	
	public Date getartab() {
		return artab;
	}

	public void setartab(Date artab) {
		this.artab = artab;
	}
	
	/*public StringProperty artabproperty() {
		return artab;
	}*/
	
	public Integer getartpreis() {
		return artpreis.get();
	}
	
	public void setartpreis(Integer artPreis) {
		this.artpreis.set(artPreis);

	}
	
	public IntegerProperty artpreisproperty() {
		return artpreis;
	}
}
