package okul.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Notlar {

	@Id
	@GeneratedValue
	private int id;

	private Float sonuc;

	@OneToOne
	private Kullanici kul;
	
	@OneToOne
	private Dersler ders;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Float getSonuc() {
		return sonuc;
	}

	public void setSonuc(Float sonuc) {
		this.sonuc = sonuc;
	}

	public Kullanici getKul() {
		return kul;
	}

	public void setKul(Kullanici kul) {
		this.kul = kul;
	}

	public Dersler getDers() {
		return ders;
	}

	public void setDers(Dersler ders) {
		this.ders = ders;
	}

}
