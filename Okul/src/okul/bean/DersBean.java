package okul.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import okul.dao.DAO;
import okul.entity.Dersler;
import okul.entity.GirisEntity;
import okul.entity.Kullanici;
import okul.entity.Notlar;

@ManagedBean
@SessionScoped
public class DersBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Kullanici kullanici;
	private List<Dersler> secilenDersler;
	private List<Dersler> secilebilecekDersler;
	private Dersler dersSil;
	private List<Dersler> dersList;
	private List<Dersler> ders;
	private List<Dersler> secilmisDersler;
	private Dersler dersAdd;
	private List<Dersler> dersleriSil;
	private List<Dersler> dersSonuclari;
	private List<Kullanici> ogrenciList;
	private List<Dersler> cikicakDersler;


	private static DersBean uniqueInstance;

	public static DersBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new DersBean();
		}
		return uniqueInstance;
	}

	@PostConstruct
	public void init() {
		this.dersSil = new Dersler();
		this.cikicakDersler = new ArrayList<>();
		this.dersleriSil = new ArrayList<>();
		ders = DAO.getInstance().getDersList();
		this.dersList = DAO.getInstance().getDersList(); 
		this.secilenDersler = new ArrayList<Dersler>();
		this.kullanici=LoginBean.getInstance().getKullanici();
		this.secilmisDersler = LoginBean.getInstance().getKullanici().getSecilenDersler();
		this.dersAdd = new Dersler();
		this.dersSonuclari = new ArrayList<>();
		this.ogrenciList = KullaniciBean.getInstance().getOgrenciList();
		this.secilebilecekDersler = secilebilcekleriGoster();
		
	}

	public List<Dersler> secilebilcekleriGoster() {

		List<Dersler> dersList = DAO.getInstance().getDersList();
		
		for (int i = 0; i < dersList.size(); i++) {
			for (int j = 0; j < secilmisDersler.size(); j++) {
				if(dersList.get(i).getId()==secilmisDersler.get(j).getId()){
					dersList.remove(i);
				}
			}
		}
		System.out.println(dersList.size());
		return dersList;
	}
	
	public List<Dersler> dersSonucla(){
		return this.kullanici.getSecilenDersler();
	}
	
	
	public void dersEkle() {

		DAO.getInstance().dersEkle(dersAdd);
		this.dersList = DAO.getInstance().getDersList();
	}
	
	public void dersSil() {
		DAO.getInstance().dersSil(dersSil);
		this.dersList = DAO.getInstance().getDersList();

	}

	public void dersleriAl(){
		
		for (Dersler dersler : secilmisDersler) {
			secilenDersler.add(dersler);
		}
		this.kullanici.setSecilenDersler(secilenDersler);
		DAO.getInstance().dersleriAl(kullanici);
		
		GirisEntity girisEntity = new GirisEntity();
		girisEntity.setUserName(kullanici.getKullaniciAdi());
		girisEntity.setPassWord(kullanici.getSifre());
		this.kullanici = DAO.getInstance().checkUser(girisEntity);
		this.secilmisDersler=kullanici.getSecilenDersler();
		this.secilebilecekDersler= secilebilcekleriGoster();
		this.secilenDersler.clear();
		dersvenotal(secilmisDersler,kullanici);
	}
	
	private void dersvenotal(List<Dersler> secilmisDersler2, Kullanici kullanici2) {
		Notlar not = new Notlar();
		for (int i = 0; i < secilmisDersler2.size() ; i++) {
			not.setKul(kullanici2);
			not.setDers(secilmisDersler2.get(i));
			DAO.getInstance().ekle(not);
			System.out.println("aq");
		}
		
	}

	public void dersleriSil(){
		
		List<Dersler> dersList = LoginBean.getInstance().getKullanici().getSecilenDersler();

		for (int i = 0; i < dersList.size(); i++) {
			for (int j = 0; j < cikicakDersler.size(); j++) {
				if(dersList.get(i).getId()==cikicakDersler.get(j).getId()){
					dersList.remove(i);
				}
			}
		}
		
		this.kullanici.setSecilenDersler(dersList);
		DAO.getInstance().dersleriAl(kullanici);
		this.secilmisDersler=kullanici.getSecilenDersler();
		this.secilebilecekDersler = secilebilcekleriGoster();
		this.cikicakDersler.clear();
		dersvenotSil(cikicakDersler,kullanici);
	}
	
	private void dersvenotSil(List<Dersler> cikicakDersler2, Kullanici kullanici2) {

		Notlar not = new Notlar();
		for (int i = 0; i < cikicakDersler2.size() ; i++) {
			not.setKul(kullanici2);
			not.setDers(cikicakDersler2.get(i));
			DAO.getInstance().cikar(not);
			System.out.println("aq");
		}
		
	}

	public void setCikicakDersler(List<Dersler> cikicakDersler) {
		this.cikicakDersler = cikicakDersler;
	}
	public List<Dersler> getCikicakDersler() {
		return cikicakDersler;
	}
	
	public void setDersSonuclari(List<Dersler> dersSonuclari) {
		this.dersSonuclari = dersSonuclari;
	}
	
	public List<Dersler> getDersSonuclari() {
		return dersSonuclari;
	}
	
	public void setDersleriSil(List<Dersler> dersleriSil) {
		this.dersleriSil = dersleriSil;
	}
	public List<Dersler> getDersleriSil() {
		return dersleriSil;
	}
	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public List<Dersler> getSecilenDersler() {
		return secilenDersler;
	}

	public void setSecilenDersler(List<Dersler> secilenDersler) {
		this.secilenDersler = secilenDersler;
	}

	public List<Dersler> getDersList() {
		return dersList;
	}

	public void setDersList(List<Dersler> dersList) {
		this.dersList = dersList;
	}

	
	public void setSecilebilecekDersler(List<Dersler> secilebilecekDersler) {
		this.secilebilecekDersler = secilebilecekDersler;
	}
	
	public List<Dersler> getSecilebilecekDersler() {
		return secilebilecekDersler;
	}
	
	public void setDersAdd(Dersler dersAdd) {
		this.dersAdd = dersAdd;
	}
	
	public Dersler getDersAdd() {
		return dersAdd;
	}

	public List<Dersler> getSecilmisDersler() {
		return secilmisDersler;
	}

	public void setSecilmisDersler(List<Dersler> secilmisDersler) {
		this.secilmisDersler = secilmisDersler;
	}

	public Dersler getDersSil() {
		return dersSil;
	}

	public void setDersSil(Dersler dersSil) {
		this.dersSil = dersSil;
	}

	public void setDers(List<Dersler> ders) {
		this.ders = ders;
	}
	
	public List<Dersler> getDers() {
		return ders;
	}
	
	public void setOgrenciList(List<Kullanici> ogrenciList) {
		this.ogrenciList = ogrenciList;
	}
	
	public List<Kullanici> getOgrenciList() {
		return ogrenciList;
	}
	
}
