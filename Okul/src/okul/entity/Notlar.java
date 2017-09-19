package okul.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notlar {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "Ders_Adi")
	private String dersAdi;
	
	@Column(name = "Notlar")
	private float not;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDersAdi() {
		return dersAdi;
	}

	public void setDersAdi(String dersAdi) {
		this.dersAdi = dersAdi;
	}
	
	public void setNot(float not) {
		this.not = not;
	}
	public float getNot() {
		return not;
	}
}
