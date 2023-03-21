package org.generation.italy.legion.model.services.implementations;

import org.generation.italy.legion.model.data.abstractions.AddressRepository;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.data.exceptions.EntityNotFoundException;
import org.generation.italy.legion.model.entities.Address;
import org.generation.italy.legion.model.services.abstractions.AbstractAddressDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StandardAddressDidacticService implements AbstractAddressDidacticService {
    private AddressRepository repo;

    @Autowired
    public StandardAddressDidacticService(AddressRepository repo){
        this.repo = repo;
        System.out.println(this.repo.getClass().getName());

    }
    public void setRepo(AddressRepository repo) { // setter injection
        this.repo = repo;
    }


    @Override
    public List<Address> findAllEntity() throws DataException {
        return null;
    }

    @Override
    public Optional<Address> findEntityById(long id) throws DataException {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Address createEntity(Address entity) throws DataException {
        return repo.create(entity);
    }

    @Override
    public void updateEntity(Address entity) throws EntityNotFoundException, DataException {

    }

    @Override
    public void deleteEntityById(long id) throws EntityNotFoundException, DataException {

    }
}
