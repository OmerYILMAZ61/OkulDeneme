package okul.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import okul.entity.Dersler;
import okul.entity.GirisEntity;
import okul.entity.Kullanici;
import okul.entity.Notlar;
import okul.entity.Rol;

public class DAO {

	private static DAO uniqueInstance;

	public static DAO getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new DAO();
		}
		return uniqueInstance;
	}

	SessionFactory sessionFactory;

	public DAO() {
		sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
	}

	public Kullanici checkUser(GirisEntity girisEntity) {
		Kullanici kullanici = null;

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Kullanici.class);

		criteria.add(Restrictions.eq("kullaniciAdi", girisEntity.getUserName()));

		criteria.add(Restrictions.eq("sifre", girisEntity.getPassWord()));

		List<Kullanici> list = criteria.list();

		if (list.size() > 0)
			kullanici = list.get(0);
		session.close();

		return kullanici;
	}

	public void rolEkle(Rol rol) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(rol);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	public void rolSil(Rol secilenRol) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.delete(secilenRol);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void ekle(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	
	public void dersEkle(Dersler obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void update(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.merge(obj); 
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	public List<Rol> rolListele() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Rol.class);

		List<Rol> list = criteria.list();

		session.close();
		return list;

	}

	public List<Kullanici> getKullaniciList() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Kullanici.class);

		List<Kullanici> list = criteria.list();

		return list;
	}

	public void kullanicilariSil(List<Kullanici> silinecekKullanicilarList) {
		Kullanici kullanici = new Kullanici();

		for (int i = 0; i < silinecekKullanicilarList.size(); i++) {
			kullanici.setId(silinecekKullanicilarList.get(i).getId());

			Session session = sessionFactory.openSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();
				session.delete(kullanici);
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	}

	public void updateKullanici(Kullanici object) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.merge(object);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Dersler> getDersList() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Dersler.class);

		List<Dersler> list = criteria.list();

		session.close();
		return list;
	}

	public void dersleriAl(Kullanici kullanici) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.merge(kullanici);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	


	public void Sil(Object obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	
	public void dersSil(Dersler obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public List<Notlar> getNotlar() {
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Notlar.class);

		List<Notlar> list = criteria.list();

		return list;
	}

	public void cikar(Notlar not) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(not);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public List<Notlar> getOgrNot(Kullanici kullanici) {

		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Notlar.class);

		criteria.add(Restrictions.eq("kul", kullanici));
		
		List<Notlar> list = criteria.list();
		
		return list;
	}

	public Notlar notDersCikar(Notlar not) {
		
		Notlar notlar = new Notlar();
		
		Session session = sessionFactory.openSession();

		Criteria criteria = session.createCriteria(Notlar.class);

		criteria.add(Restrictions.eq("kul", not.getKul()));
		
		criteria.add(Restrictions.eq("ders", not.getDers()));

		List<Notlar> list = criteria.list();

		if (list.size() > 0)
			notlar = list.get(0);
		session.close();

		return notlar;
		
	}

	public void guncelle(Notlar not) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(not);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}


}
