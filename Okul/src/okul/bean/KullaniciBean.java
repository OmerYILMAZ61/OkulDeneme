package okul.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import okul.dao.DAO;
import okul.entity.Kullanici;
import okul.entity.Rol;

@ManagedBean
@SessionScoped
public class KullaniciBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Rol> rolList;
	private Rol secilenRol;
	private List<Kullanici> kullaniciList;
	private Rol degistirilcekRol;
	private Kullanici kullaniciAdd;
	private List<Kullanici> silinecekKullanicilarList;
	private List<Kullanici> ogrenciList;
	private Rol rolAdd;
	private Rol silinecekRol;
	
	
	private static KullaniciBean uniqueInstance;

	public static KullaniciBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new KullaniciBean();
		}
		return uniqueInstance;
	}

	@PostConstruct
	public void init() {
		
		this.silinecekRol = new Rol();
		this.rolList = DAO.getInstance().rolListele();
		this.secilenRol = new Rol();
		this.kullaniciAdd = new Kullanici();
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		this.silinecekKullanicilarList = new ArrayList<>();
		this.degistirilcekRol = new Rol();
		KullaniciBean.getInstance().setOgrenciList(ogrenciListele(kullaniciList));
		this.ogrenciList =	ogrenciListele(kullaniciList);
		this.rolAdd = new Rol();
				
	}

	private List<Kullanici> ogrenciListele(List<Kullanici> kullaniciList) {
		List<Kullanici> ogrList = new ArrayList<>();
		for (Kullanici kullanici : kullaniciList) {
			if(kullanici.getRol().getName().equals("öðrenci")){
				ogrList.add(kullanici);
			}
		}
		return ogrList;
	}
	

	public void rolEkle() {

		DAO.getInstance().rolEkle(rolAdd);

		this.rolList = DAO.getInstance().rolListele();
	}
	
	public void rolSil() {

		DAO.getInstance().rolSil(silinecekRol);
		this.rolList = DAO.getInstance().rolListele();
	}

	public String kullaniciEkle() {
		
		kullaniciAdd.setRol(secilenRol);
		DAO.getInstance().ekle(kullaniciAdd);
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		this.ogrenciList = ogrenciListele(kullaniciList);
		return "kullaniciGuncelle.xhtml";

	}

	public void kullaniciSil() {
		DAO.getInstance().kullanicilariSil(silinecekKullanicilarList);
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		this.ogrenciList = ogrenciListele(kullaniciList);
	}


	public Rol getSecilenRol() {
		return secilenRol;
	}

	public void setSecilenRol(Rol secilenRol) {
		this.secilenRol = secilenRol;
	}

	public void setRolList(List<Rol> rolList) {
		this.rolList = rolList;
	}

	public List<Rol> getRolList() {
		return rolList;
	}

	public Kullanici getKulllaniciAdd() {
		return kullaniciAdd;
	}

	public void setKulllaniciAdd(Kullanici kulllaniciAdd) {
		this.kullaniciAdd = kulllaniciAdd;
	}

	public List<Kullanici> getKullaniciList() {
		return kullaniciList;
	}

	public void setKullaniciList(List<Kullanici> kullaniciList) {
		this.kullaniciList = kullaniciList;
	}

	public Kullanici getKullaniciAdd() {
		return kullaniciAdd;
	}

	public void setKullaniciAdd(Kullanici kullaniciAdd) {
		this.kullaniciAdd = kullaniciAdd;
	}

	public Rol getDegistirilcekRol() {
		return degistirilcekRol;
	}

	public void setDegistirilcekRol(Rol degistirilcekRol) {
		this.degistirilcekRol = degistirilcekRol;
	}

	public void onRowEdit(RowEditEvent event) {

		Kullanici kul = (Kullanici) event.getObject();
		kul.setRol(degistirilcekRol);
		DAO.getInstance().updateKullanici(kul);
		this.kullaniciList = DAO.getInstance().getKullaniciList();
		this.ogrenciList = ogrenciListele(kullaniciList);
		
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Kullanici) event.getObject()).getKullaniciAdi());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void setSilinecekKullanicilarList(List<Kullanici> silinecekKullanicilarList) {
		this.silinecekKullanicilarList = silinecekKullanicilarList;
	}

	public List<Kullanici> getSilinecekKullanicilarList() {
		return silinecekKullanicilarList;
	}
	
	public void setOgrenciList(List<Kullanici> ogrenciList) {
		this.ogrenciList = ogrenciList;
	}
	
	public List<Kullanici> getOgrenciList() {
		return ogrenciList;
	}
	
	public void setRolAdd(Rol rolAdd) {
		this.rolAdd = rolAdd;
	}
	
	public Rol getRolAdd() {
		return rolAdd;
	}
	
	
	public void setSilinecekRol(Rol silinecekRol) {
		this.silinecekRol = silinecekRol;
	}
	
	public Rol getSilinecekRol() {
		return silinecekRol;
	}
	
}
