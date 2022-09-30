package application.blueprint;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Kunde {
	
	private IntegerProperty kundeid;
	private StringProperty nachname;
	private StringProperty vorname;
	private StringProperty email;
	private StringProperty tel;

	
	public Kunde() {
		
		this.kundeid = new SimpleIntegerProperty();
		this.nachname = new SimpleStringProperty();
		this.vorname = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.tel = new SimpleStringProperty();
	}
	
	public int getKundeid(){
		return kundeid.get();
	}
	
	public void setkundeid(int KundeID) {
		this.kundeid.set(KundeID);

	}
	
	public IntegerProperty kundeidproperty() {
		return kundeid;
		
	}
	
	public String getnachname() {
		return nachname.get();
	}

	public void setnachname(String nachname) {
		this.nachname.set(nachname);

		
	}
	
	public StringProperty nachnameproperty() {
		return nachname;
	}
	
	
	public String getvorname() {
		return vorname.get();
	}
	
	public void setvorname(String vorname) {
		this.vorname.set(vorname);
	}
	
	public StringProperty vornameproperty() {
		return vorname;
	}
	
	public String getemail() {
		return email.get();
	}
	
	
	public void setemail(String email) {
		
		this.email.set(email);

	}
	
	public StringProperty emailproperty() {
		return email;
	}
	
	public String gettel() {
		return tel.get();
	}
	
	public void settel(String tel) {
		this.tel.set(tel);

	}
	
	public StringProperty telproperty() {
		return tel;
	}
	
}
	
	


