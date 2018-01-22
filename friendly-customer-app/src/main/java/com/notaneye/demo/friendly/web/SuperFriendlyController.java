package com.notaneye.demo.friendly.web;


import com.notaneye.demo.friendly.service.HarmlessExternalApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@SessionAttributes({ "registration" })
public class SuperFriendlyController {

    private static final Logger LOG = LoggerFactory.getLogger("friendly-customer-app");

    @Autowired
    private HarmlessExternalApi api;


    @ModelAttribute("registration")
    public RegistrationCommand newRegistration() {

        RegistrationCommand cmd = new RegistrationCommand();
        cmd.setRegistrationId(UUID.randomUUID());
        return cmd;
    }


    @GetMapping("/")
    public ModelAndView index(@ModelAttribute("registration") RegistrationCommand registration) {

        LOG.info("Starting registration process {}", registration.getRegistrationId());
        Map<String, RegistrationCommand> map = new LinkedHashMap<>();
        map.put("registration", registration);
        return new ModelAndView("index", map);
    }


    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("registration") RegistrationCommand registration) {

        LOG.info("Registering new user '{}' ({})", registration.getUsername(), registration.getRegistrationId());
        this.api.enrolledEvent(registration);
        return new ModelAndView("pii");
    }


    @PostMapping("/finish")
    public ModelAndView finishRegistration(@ModelAttribute("registration") RegistrationCommand registration,
            SessionStatus status) {

        LOG.info("User registration complete ({})", registration.getRegistrationId());
        this.api.piiEvent(registration);
        status.setComplete();
        return new ModelAndView("complete");
    }
}
