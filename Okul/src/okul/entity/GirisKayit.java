package okul.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GirisKayit {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "Kullanici_Adi")
	private String kulAdi;
	
	@Column(name = "Giris_Tarihi")
	private Date girisDate;
	
	@Column(name = "Cikis_Tarihi")
	private Date cikisDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKulAdi() {
		return kulAdi;
	}

	public void setKulAdi(String kulAdi) {
		this.kulAdi = kulAdi;
	}

	public Date getGirisDate() {
		return girisDate;
	}

	public void setGirisDate(Date girisDate) {
		this.girisDate = girisDate;
	}

	public Date getCikisDate() {
		return cikisDate;
	}

	public void setCikisDate(Date cikisDate) {
		this.cikisDate = cikisDate;
	}
	
	
}
