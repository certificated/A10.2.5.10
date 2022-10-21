package Class;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adresse {

	private IntegerProperty adrid;
	private StringProperty adrort;
	private IntegerProperty adrplz;
	private StringProperty adrstrasse;
	private IntegerProperty adrhanr;

	
	public Adresse() {
		
		this.adrid = new SimpleIntegerProperty();
		this.adrort = new SimpleStringProperty();
		this.adrplz = new SimpleIntegerProperty();
		this.adrstrasse = new SimpleStringProperty();
		this.adrhanr = new SimpleIntegerProperty();
	}
	
	public int getadrid(){
		return adrid.get();
	}
	
	public void setadrid(int AdrID) {
		this.adrid.set(AdrID);
	}
	
	public IntegerProperty adridproperty() {
		return adrid;
	}
	
	public String getadrort() {
		return adrort.get();
	}
	
	public void setadrort(String adrort) {
		this.adrort.set(adrort);	
	}
	
	public StringProperty adrortproperty() {
		return adrort;
	}
	
	
	public Integer getadrplz() {
		return adrplz.get();
	}
	
	public void setadrplz(Integer adrplz) {
		this.adrplz.set(adrplz);
	}
	
	public IntegerProperty adrplzproperty() {
		
		return adrplz;
	}
	
	public String getadrstrasse() {
		return adrstrasse.get();
	}
	
	
	public void setadrstrasse(String adrstrasse) {
		this.adrstrasse.set(adrstrasse);
	}
	
	public StringProperty adrstrasseproperty() {
		return adrstrasse;
	}
	
	public Integer getadrhanr() {
		return adrhanr.get();
	}
	
	public void setadrhanr(Integer adrhanr) {
		this.adrhanr.set(adrhanr);
	}
	
	public IntegerProperty adrhanrproperty() {
		return adrhanr;
	}
	
}
