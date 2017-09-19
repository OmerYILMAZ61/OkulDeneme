package okul.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Kullanici {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "Kullanici_Adi")
	private String kullaniciAdi;
	
	@Column(name = "sifre")
	private String sifre;
	
	@OneToOne
	private  Rol  rol;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(unique = true)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="ogrenci_ders",joinColumns= @JoinColumn(name = "ogrenci_id"),
						inverseJoinColumns = @JoinColumn(name = "ders_id"))
	private  List<Dersler>  secilenDersler;

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public void setSecilenDersler(List<Dersler> secilenDersler) {
		this.secilenDersler = secilenDersler;
	}
	
	public List<Dersler> getSecilenDersler() {
		return secilenDersler;
	}
	
}
