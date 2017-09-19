package okul.dao;


import okul.entity.Kullanici;
import okul.entity.Rol;


public class Islem {

	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		Rol rol = new Rol();
		rol.setId(1);
		rol.setName("admin");
		dao.ekle(rol);
		
		Kullanici kullanici = new Kullanici();
		kullanici.setId(1);
		kullanici.setKullaniciAdi("aqu");
		kullanici.setSifre("123");
		kullanici.setRol(rol);
		dao.ekle(kullanici);
		
	}
	
	
	
}
