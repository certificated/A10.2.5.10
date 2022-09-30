package application.blueprint;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stammdaten {

	private IntegerProperty stammid;
	private IntegerProperty stammblz;
	private StringProperty stammab;
	private StringProperty stammzb;
	

	
	public Stammdaten() {
		
		this.stammid = new SimpleIntegerProperty();
		this.stammblz = new SimpleIntegerProperty();
		this.stammab = new SimpleStringProperty();
		this.stammzb = new SimpleStringProperty();
		
	}
	
	public int getstammid(){
		return stammid.get();
	}
	
	public void setstammid(int stammid) {
		this.stammid.set(stammid);
	}
	
	public IntegerProperty setammidproperty() {
		return stammid;
	}
	
	public Integer getstammblz() {
		return stammblz.get();
	}
	
	public void setstammblz(Integer stammblz) {
		this.stammblz.set(stammblz);	
	}
	
	public IntegerProperty setammblzproperty() {
		return stammblz;
	}
	
	
	public String getstammab() {
		return stammab.get();
	}
	
	public void setstammab(String stammab) {
		this.stammab.set(stammab);
	}
	
	public StringProperty setammabproperty() {
		
		return stammab;
	}
	
	public String getstammzb() {
		return stammzb.get();
	}
	
	
	public void setstammzb(String stammzb) {
		this.stammzb.set(stammzb);
	}
	
	public StringProperty stammzbproperty() {
		return stammzb;
	}
	
	
}
