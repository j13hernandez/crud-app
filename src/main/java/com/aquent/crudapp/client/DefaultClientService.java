package com.aquent.crudapp.client;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of {@link ClientService}.
 */
@Component
public class DefaultClientService implements ClientService {

    private final ClientDao clientDao;
    private final Validator validator;

    public DefaultClientService(ClientDao clientDao, Validator validator) {
        this.clientDao = clientDao;
        this.validator = validator;
    }

    @Override
    public List<Client> listClients() {
        List<Client> clients = clientDao.listClients();
        return clients;
    }

    @Override
    public Integer createClient(Client client) {
        return clientDao.createClient(client);
    }

    @Override
    public Client readClient(Integer clientId) {
        Client client = clientDao.readClient(clientId);
        return client;
    }

    @Override
    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientDao.deleteClient(clientId);
    }

    @Override
    public List<String> validateClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        List<String> errors = new ArrayList(violations.size());
        for (ConstraintViolation<Client> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
