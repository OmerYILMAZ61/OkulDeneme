package okul.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import okul.dao.DAO;
import okul.entity.GirisEntity;
import okul.entity.GirisKayit;
import okul.entity.Kullanici;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GirisEntity girisEntity;
	private boolean kullaniciIcerde;
	private Kullanici kullanici;
	private static LoginBean uniqueInstance;
	private GirisKayit girisKayit;
	
	
	public static LoginBean getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new LoginBean();
		}
		return uniqueInstance;
	}

	@PostConstruct
	public void init() {
		this.girisKayit = new GirisKayit();
		this.girisEntity = new GirisEntity();
		this.kullanici = new Kullanici();
	}

	public String login() {
		
		this.kullanici = DAO.getInstance().checkUser(this.girisEntity);
		LoginBean.getInstance().setKullanici(kullanici);

		if (this.kullanici!=null) {
			this.girisKayit.setKulAdi(kullanici.getKullaniciAdi());
			this.girisKayit.setGirisDate(Calendar.getInstance().getTime());
			DAO.getInstance().ekle(girisKayit);
			this.kullaniciIcerde = true;
			return "iceri/anasayfa.xhtml?faces-redirect=true";
		}

		return "fail.xhtml";

	}
	
	public String cikisYap(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		this.kullaniciIcerde = false;
		this.girisKayit.setCikisDate(Calendar.getInstance().getTime());
		DAO.getInstance().update(girisKayit);
		return "/index.xhtml?faces-redirect=true";
	}
	

	public void setGirisEntity(GirisEntity girisEntity) {
		this.girisEntity = girisEntity;
	}

	public GirisEntity getGirisEntity() {
		return girisEntity;
	}

	public boolean isKullaniciIcerde() {
		return kullaniciIcerde;
	}

	public void setKullaniciIcerde(boolean kullaniciIcerde) {
		this.kullaniciIcerde = kullaniciIcerde;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setGirisKayit(GirisKayit girisKayit) {
		this.girisKayit = girisKayit;
	}
	public GirisKayit getGirisKayit() {
		return girisKayit;
	}
}
