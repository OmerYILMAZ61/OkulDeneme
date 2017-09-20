package okul.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import okul.dao.DAO;
import okul.entity.Kullanici;
import okul.entity.Notlar;

@ManagedBean
@SessionScoped
public class NotBean {
	
	private Kullanici kullanici ;
	private List<Notlar> notlar;
	
	@PostConstruct
	public void init(){
		this.kullanici=LoginBean.getInstance().getKullanici();
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
	

}
