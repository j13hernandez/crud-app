package com.aquent.crudapp.client;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Component
public class JdbcClientDao implements ClientDao {

    private static final String SQL_LIST_CLIENTS = "SELECT * FROM client";
    private static final String SQL_READ_CLIENT = "SELECT * FROM client WHERE client_id = :clientId";
    private static final String SQL_DELETE_CLIENT = "DELETE FROM client WHERE client_id = :clientId";
    private static final String SQL_UPDATE_CLIENT = "UPDATE client SET (company_name, website_url, phone_number, street_address, city, state, zip_code)"
            + " = (:companyName, :websiteUrl, :phoneNumber, :streetAddress, :city, :state, :zipCode)"
            + "WHERE client_id = :clientId";
    private static final String SQL_CREATE_CLIENT = "INSERT INTO client (company_name, website_url, phone_number, street_address, city, state, zip_code)"
            + " VALUES (:companyName, :websiteUrl, :phoneNumber, :streetAddress, :city, :state, :zipCode)";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcClientDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Client> listClients() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_CLIENTS, new ClientRowMapper());
    }

    @Override
    public Integer createClient(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_CLIENT, new BeanPropertySqlParameterSource(client), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Client readClient(Integer clientId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_CLIENT, Collections.singletonMap("clientId", clientId), new ClientRowMapper());
    }

    @Override
    public void updateClient(Client client) {
        namedParameterJdbcTemplate.update(SQL_UPDATE_CLIENT, new BeanPropertySqlParameterSource(client));
    }

    @Override
    public void deleteClient(Integer clientId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_CLIENT, Collections.singletonMap("clientId", clientId));
    }

    private static final class ClientRowMapper implements RowMapper<Client> {

        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setStreetAddress(rs.getString("street_address"));
            client.setCity(rs.getString("city"));
            client.setState(rs.getString("state"));
            client.setZipCode(rs.getString("zip_code"));
            client.setClientId(rs.getInt("client_id"));
            client.setCompanyName(rs.getString("company_name"));
            client.setWebsiteUrl(rs.getString("website_url"));
            client.setPhoneNumber(rs.getString("phone_number"));
            return client;
        }
    }
}
