package com.solstice.codechallenge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    private final ContactBean contactBean;

    public HomeController(ContactBean contactBean) {
        this.contactBean = contactBean;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/codingchallenge")
    public String home(Map<String, Object> model) {

        contactBean.addContact(new Contact("Mr", "David Dobkin", "555 Main St, Chicago, IL", 123456, 205));
        contactBean.addContact(new Contact("Mr", "Todd Phillips", "123 Wacker Dr, Chicago, IL,", 123456, 204));
        contactBean.addContact(new Contact("Mr", "David Dobkin", "666 Wells Dr, Chicago, IL", 123456, 203));
        contactBean.addContact(new Contact("Ms", "Betty Thomas", "444 Ohio St, Chicago, IL", 123456, 202));
        contactBean.addContact(new Contact("Mr", "Wes Anderson", "111 Michigan Ave, Chicago, IL", 123456, 201));
        contactBean.addContact(new Contact("Mr", "Ben Stiller", "777 Milwaukee Ave, Chicago, IL", 123456, 201));
        contactBean.addContact(new Contact("Mr", "Tom Dey", "888 Grand Ave, Chicago, IL", 123456, 200));

        model.put("contacts", contactBean.getContacts());

        return "codingchallenge";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        contactBean.addContact(new Contact("Mr", "David Dobkin", "555 Main St, Chicago, IL", 123456, 205));
        contactBean.addContact(new Contact("Mr", "Todd Phillips", "123 Wacker Dr, Chicago, IL,", 123456, 204));
        contactBean.addContact(new Contact("Mr", "David Dobkin", "666 Wells Dr, Chicago, IL", 123456, 203));
        contactBean.addContact(new Contact("Ms", "Betty Thomas", "444 Ohio St, Chicago, IL", 123456, 202));
        contactBean.addContact(new Contact("Mr", "Wes Anderson", "111 Michigan Ave, Chicago, IL", 123456, 201));
        contactBean.addContact(new Contact("Mr", "Ben Stiller", "777 Milwaukee Ave, Chicago, IL", 123456, 201));
        contactBean.addContact(new Contact("Mr", "Tom Dey", "888 Grand Ave, Chicago, IL", 123456, 200));

        model.put("contacts", contactBean.getContacts());

        return "setup";
    }
}
