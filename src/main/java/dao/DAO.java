package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Student;
import utils.JPAutil;

public class DAO {

	public static final EntityManager em = JPAutil.getEntityManager();
	
	public List<Student> findAll(){
		String jpql = "SELECT o FROM Student o";
		TypedQuery<Student> query = em.createQuery(jpql, Student.class);
		return query.getResultList();
	}

	public List<Student> findByMajor(String input) {
		String jpql = "SELECT o FROM Student o WHERE o.major LIKE :keyword";
		TypedQuery<Student> query = em.createQuery(jpql, Student.class);
		query.setParameter("keyword", "%" + input + "%");
		return query.getResultList();
	}
	
	public Student findById(Integer input) {
		String jpql = "SELECT o FROM Student o WHERE o.id = ?0";
		TypedQuery<Student> query = em.createQuery(jpql, Student.class);
		query.setParameter(0, input);
		return query.getSingleResult();
	}

	public Student create(Student student) {
		try {
			em.getTransaction().begin();
			em.persist(student);
			System.out.println("Create success");
			em.getTransaction().commit();
			return student;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public Student edit(Student input) {
		try {
			Student newStudent = em.find(Student.class, input.getId());
			newStudent.setName(input.getName());
			newStudent.setMark(input.getMark());
			newStudent.setMajor(input.getMajor());
			
			em.getTransaction().begin();
			em.merge(newStudent);
			System.out.println("Edit success");
			em.getTransaction().commit();
			return newStudent;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	public Student remove(Student input) {
		try {
			em.getTransaction().begin();
			em.remove(input);
			System.out.println("Delete success");
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
}
