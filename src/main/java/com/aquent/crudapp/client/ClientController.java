package com.aquent.crudapp.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aquent.crudapp.person.Person;
import com.aquent.crudapp.person.PersonService;

/**
 * Controller for handling basic person management operations.
 */
@Controller
@RequestMapping("client")
public class ClientController {
    public static final String COMMAND_DELETE = "Delete";

    private final PersonService personService;
    private final ClientService clientService;

    public ClientController(ClientService clientService, PersonService personService) {
        this.clientService = clientService;
        this.personService = personService;
    }

    /**
     * Renders the listing page.
     *
     * @return list view populated with the current list of clients
     */
    @GetMapping(value = "list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("client/list");
        mav.addObject("clients", clientService.listClients());
        return mav;
    }

    /**
     * Renders an empty form used to create a new client record.
     *
     * @return create view populated with an empty client
     */
    @GetMapping(value = "create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("client/create");
        mav.addObject("client", new Client());
        mav.addObject("errors", new ArrayList<String>());
        mav.addObject("persons", personService.listPeople());
        return mav;
    }

    /**
     * Validates and saves a new client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @return redirect, or create view with errors
     */
    @PostMapping(value = "create")
    public ModelAndView create(Client client, Person person) {
        List<String> errors = clientService.validateClient(client);
        if (errors.isEmpty()) {
            clientService.createClient(client);
            personService.updatePerson(person);
            return new ModelAndView("redirect:/client/list");
        } else {
            ModelAndView mav = new ModelAndView("client/create");
            mav.addObject("client", client);
            mav.addObject("errors", errors);
            mav.addObject("persons", personService.listPeople());
            return mav;
        }
    }

    /**
     * Renders an edit form for an existing client record.
     *
     * @param clientId the ID of the client to edit
     * @return edit view populated from the client record
     */
    @GetMapping(value = "edit/{clientId}")
    public ModelAndView edit(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/edit");
        mav.addObject("client", clientService.readClient(clientId));
        mav.addObject("errors", new ArrayList<String>());
        mav.addObject("persons", personService.listPeople());
        return mav;
    }

    /**
     * Validates and saves an edited client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @return redirect, or edit view with errors
     */
    @PostMapping(value = "edit")
    public ModelAndView edit(Client client, Person person) {
        List<String> errors = clientService.validateClient(client);
        if (errors.isEmpty()) {
            clientService.updateClient(client);
            personService.updatePerson(person);
            return new ModelAndView("redirect:/client/list");
        } else {
            ModelAndView mav = new ModelAndView("client/edit");
            mav.addObject("client", client);
            mav.addObject("errors", errors);
            mav.addObject("persons", personService.listPeople());
            return mav;
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param clientId the ID of the client to be deleted
     * @return delete view populated from the client record
     */
    @GetMapping(value = "delete/{clientId}")
    public ModelAndView delete(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("client/delete");
        mav.addObject("client", clientService.readClient(clientId));
        return mav;
    }

    /**
     * Handles client deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param clientId the ID of the client to be deleted
     * @return redirect to the listing page
     */
    @PostMapping(value = "delete")
    public String delete(@RequestParam String command, @RequestParam Integer clientId) {
        if (COMMAND_DELETE.equals(command)) {
            clientService.deleteClient(clientId);
        }
        return "redirect:/client/list";
    }
    
}
