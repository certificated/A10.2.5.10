package Class;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KFZ {

	private IntegerProperty kfzid;
	private StringProperty kfzmarke;
	private StringProperty kfzmodell;
	private StringProperty kfzhu;
	private StringProperty kfzkennz;
	private IntegerProperty kfzkmstand;
	private StringProperty kfzerstzul;

	
	public KFZ() {
		
		this.kfzid = new SimpleIntegerProperty();
		this.kfzmarke = new SimpleStringProperty();
		this.kfzmodell = new SimpleStringProperty();
		this.kfzhu = new SimpleStringProperty();
		this.kfzkennz = new SimpleStringProperty();
		this.kfzkmstand = new SimpleIntegerProperty();
		this.kfzerstzul = new SimpleStringProperty();
	}
	
	public int getkfzid(){
		return kfzid.get();
	}

	public void setkfzid(int KfzID) {
		this.kfzid.set(KfzID);
	}
	
	public IntegerProperty kfzidproperty() {
		return kfzid;
	}
	
	public String getkfzmarke() {
		return kfzmarke.get();
	}
	
	public void setkfzmarke(String kfzmarke) {
		this.kfzmarke.set(kfzmarke);
	}
	
	public StringProperty kfzmarkeproperty() {
		return kfzmarke;
	}
	
	public String getkfzmodell() {
		return kfzmodell.get();
	}
	
	public void setkfzmodell(String kfzmodell) {
		this.kfzmodell.set(kfzmodell);
	}
	
	public StringProperty kfzmodellproperty() {
		return kfzmodell;
	}
	
	public String getkfzhu() {
		return kfzhu.get();
	}
	
	public void setkfzhu(String kfzhu) {
		this.kfzhu.set(kfzhu);
	}
	
	public StringProperty kfzhuproperty() {
		return kfzhu;
	}
	
	public String getkfzkennz() {
		return kfzkennz.get();
	}
	
	public void setkfzkennz(String kfzkennz) {
		this.kfzkennz.set(kfzkennz);
	}
	
	public StringProperty kfzkennzproperty() {
		return kfzkennz;
	}
	
	public Integer getkfzkmstand() {
		return kfzkmstand.get();
	}
	
	public void setkfzkmstand(Integer kfzkmstand) {
		this.kfzkmstand.set(kfzkmstand);
	}
	
	public IntegerProperty kfzkmstandproperty() {
		return kfzkmstand;
	}
	
	public String getkfzerstzul() {
		return kfzerstzul.get();
	}
	
	public void setkfzerstzul(String kfzerstzul) {
		this.kfzerstzul.set(kfzerstzul);
	}
	
	public StringProperty kfzerstzulproperty() {
		return kfzerstzul;
	}
	
	
}
