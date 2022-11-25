package Class;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rechnung {
	private IntegerProperty rechnungid;
	private LocalDate datum;
	private IntegerProperty fiid;
	private IntegerProperty kdid;
	private IntegerProperty adrid;
	private IntegerProperty stmmid;

	
	public Rechnung() {
		
		this.rechnungid = new SimpleIntegerProperty();
		//this.datum = new LocalDate();
		this.fiid = new SimpleIntegerProperty();
		this.kdid = new SimpleIntegerProperty();
		this.adrid = new SimpleIntegerProperty();
		this.stmmid = new SimpleIntegerProperty();
	}
	
	public int getrechnungsid(){
		return rechnungid.get();
	}
	
	public void setrechnungid(int RechnungID) {
		this.rechnungid.set(RechnungID);

	}
	
	public IntegerProperty rechnungidproperty() {
		return rechnungid;
		
	}
	
	public int getfiid(){
		return fiid.get();
	}
	
	public void setfiid(int FiID) {
		this.fiid.set(FiID);

	}
	
	public IntegerProperty fiidproperty() {
		return fiid;
		
	}
	
	public int getkdid(){
		return kdid.get();
	}
	
	public void setkdid(int KundeID) {
		this.kdid.set(KundeID);

	}
	
	public IntegerProperty kdidproperty() {
		return kdid;
		
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
	
	public int getstmmid(){
		return stmmid.get();
	}
	
	public void setstmmid(int StmmID) {
		this.stmmid.set(StmmID);

	}
	
	public IntegerProperty stmmidproperty() {
		return stmmid;
		
	}
	
}
