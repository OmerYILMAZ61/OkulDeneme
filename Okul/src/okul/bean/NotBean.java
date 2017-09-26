package okul.bean;

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
import okul.entity.Notlar;

@ManagedBean
@SessionScoped
public class NotBean {
	
	
	private static NotBean uniqueInstance;

	public static NotBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new NotBean();
		}
		return uniqueInstance;
	}
	
	private Kullanici kullanici ;
	private List<Notlar> notlar;
	private List<Notlar> OgrenciNotlari;
	private Kullanici notuGirilcekOgr;
	private List<Notlar> OgrenciGirilecekNotlari;
	private List<Notlar> deneme;
	
	@PostConstruct
	public void init(){
		this.kullanici=LoginBean.getInstance().getKullanici();
		this.notlar = DAO.getInstance().getNotlar();
		this.OgrenciNotlari = notGetir(kullanici);
		this.notuGirilcekOgr = new Kullanici();
		this.OgrenciGirilecekNotlari = new ArrayList<>();
		this.deneme = DAO.getInstance().getNotlar();
	}
	

	public List<Notlar> notGetir(Kullanici kullanici2) {
	
		List<Notlar> not = DAO.getInstance().getOgrNot(kullanici2);
		return not;
	}

	public List<Notlar> notGetir() {
		List<Notlar> not = DAO.getInstance().getOgrNot(kullanici);
		this.OgrenciNotlari = not;
		return not;
	}
	
	public List<Notlar> ogrencininNotlariGetir(){
		
		
		List<Notlar> not = DAO.getInstance().getOgrNot(notuGirilcekOgr);
		this.OgrenciGirilecekNotlari = not;
		return not;
	}

	public void onRowEdit(RowEditEvent event) {

		Notlar not = (Notlar) event.getObject();
		DAO.getInstance().guncelle(not);
		this.notlar = DAO.getInstance().getNotlar();
		this.OgrenciNotlari = notGetir(kullanici);
		
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Notlar) event.getObject()).getDers().getDersAdi());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void ogrenciNotuGetir(){
		
	}
	
	
	public void notlariGuncelle(){
		this.notlar = DAO.getInstance().getNotlar();
	}
	
	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public List<Notlar> getNotlar() {
		return notlar;
	}

	public void setNotlar(List<Notlar> notlar) {
		this.notlar = notlar;
	}
	public void setOgrenciNotlari(List<Notlar> ogrenciNotlari) {
		OgrenciNotlari = ogrenciNotlari;
	}
	public List<Notlar> getOgrenciNotlari() {
		return OgrenciNotlari;
	}

	public void setNotuGirilcekOgr(Kullanici notuGirilcekOgr) {
		this.notuGirilcekOgr = notuGirilcekOgr;
	}
	public Kullanici getNotuGirilcekOgr() {
		return notuGirilcekOgr;
	}
	public void setOgrenciGirilecekNotlari(List<Notlar> ogrenciGirilecekNotlari) {
		OgrenciGirilecekNotlari = ogrenciGirilecekNotlari;
	}
	public List<Notlar> getOgrenciGirilecekNotlari() {
		return OgrenciGirilecekNotlari;
	}
	public void setDeneme(List<Notlar> deneme) {
		this.deneme = deneme;
	}
	public List<Notlar> getDeneme() {
		return deneme;
	}
	
}
