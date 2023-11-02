package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.User;

public class Userdao {
	EntityManagerFactory f = Persistence.createEntityManagerFactory("villain");
	EntityManager m = f.createEntityManager();
	EntityTransaction t = m.getTransaction();

	public void addUser(User u) {
		t.begin();
		m.persist(u);
		t.commit();
	}

	public boolean loginUser(String newemail, String newpassword) {
		Query q = m.createQuery("select a from User a");
		List<User> l = q.getResultList();
		for (User u : l) {
			if (u.getEmail().equals(newemail) && u.getPassword().equals(newpassword)) {
				return true;
			}
		}
		return false;
	}
}
