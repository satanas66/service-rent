package es.java.challenge.service.customerloyalty.infraestructure;

import es.java.challenge.service.customerloyalty.domain.CustomerLoyalty;
import es.java.challenge.service.customerloyalty.domain.CustomerLoyaltyRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public abstract class CustomerLoyaltyRepositoryImpl implements CustomerLoyaltyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CustomerLoyalty findCustomerLoyaltyByDni(String dni) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CustomerLoyalty> criteriaQuery = criteriaBuilder.createQuery(CustomerLoyalty.class);
        Root<CustomerLoyalty> root = criteriaQuery.from(CustomerLoyalty.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.equal(root.get("dni"), dni)
        );
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
