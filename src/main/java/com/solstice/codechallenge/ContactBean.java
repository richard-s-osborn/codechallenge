package com.solstice.codechallenge;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.List;

@Repository
public class ContactBean {

    @PersistenceContext
    private EntityManager entityManager;

    public Contact find(Long id) {
        return entityManager.find(Contact.class, id);
    }

    @Transactional
    public void addContact(Contact contact) {

        entityManager.persist(contact);
    }

    @Transactional
    public void editContact(Contact contact) {

        entityManager.merge(contact);
    }

    @Transactional
    public void deleteContact(Contact contact) {
        entityManager.remove(contact);
    }

    @Transactional
    public void deleteContactId(long id) {
        Contact contact = entityManager.find(Contact.class, id);
        deleteContact(contact);
    }

    public List<Contact> getContacts() {
        CriteriaQuery<Contact> cq = entityManager.getCriteriaBuilder().createQuery(Contact.class);
        cq.select(cq.from(Contact.class));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<Contact> findAll(int firstResult, int maxResults) {
        CriteriaQuery<Contact> cq = entityManager.getCriteriaBuilder().createQuery(Contact.class);
        cq.select(cq.from(Contact.class));
        TypedQuery<Contact> q = entityManager.createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }

    public int countAll() {
        CriteriaQuery<Long> cq = entityManager.getCriteriaBuilder().createQuery(Long.class);
        Root<Contact> rt = cq.from(Contact.class);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        TypedQuery<Long> q = entityManager.createQuery(cq);
        return (q.getSingleResult()).intValue();
    }

    public int count(String field, String searchTerm) {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        Root<Contact> root = cq.from(Contact.class);
        EntityType<Contact> type = entityManager.getMetamodel().entity(Contact.class);

        Path<String> path = root.get(type.getDeclaredSingularAttribute(field, String.class));
        Predicate condition = qb.like(path, "%" + searchTerm + "%");

        cq.select(qb.count(root));
        cq.where(condition);

        return entityManager.createQuery(cq).getSingleResult().intValue();
    }

    public List<Contact> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = qb.createQuery(Contact.class);
        Root<Contact> root = cq.from(Contact.class);
        EntityType<Contact> type = entityManager.getMetamodel().entity(Contact.class);

        Path<String> path = root.get(type.getDeclaredSingularAttribute(field, String.class));
        Predicate condition = qb.like(path, "%" + searchTerm + "%");

        cq.where(condition);
        TypedQuery<Contact> q = entityManager.createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        return q.getResultList();
    }

    public void clean() {

        entityManager.createQuery("delete from Contact").executeUpdate();
    }
}
