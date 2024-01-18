package utils;

import javax.persistence.*;

public class JPAutil {
	private static EntityManagerFactory factory;

	public static EntityManager getEntityManager() {
		if (factory == null || factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("STUDENT");
		}
		return factory.createEntityManager();
	}

	public static void shutDown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
