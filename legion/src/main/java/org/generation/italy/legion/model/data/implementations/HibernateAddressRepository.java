package org.generation.italy.legion.model.data.implementations;

import jakarta.persistence.EntityManager;
import org.generation.italy.legion.model.data.abstractions.AddressRepository;
import org.generation.italy.legion.model.entities.Address;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("hibernate")
public class HibernateAddressRepository extends GenericCrudRepository<Address> implements AddressRepository {
    @Autowired
    public HibernateAddressRepository(EntityManager em) {
        super(em, Address.class);
    }

}
