package com.notaneye.demo.friendly.web


import com.notaneye.demo.friendly.service.HarmlessExternalApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.ModelAndView


@Controller
@SessionAttributes(['registration'])
class SuperFriendlyController {

    private static final Logger LOG = LoggerFactory.getLogger('friendly-customer-app')


    @Autowired
    HarmlessExternalApi api


    @ModelAttribute('registration')
    RegistrationCommand newRegistration() {

        RegistrationCommand cmd = new RegistrationCommand()
        cmd.registrationId = UUID.randomUUID()
        return cmd
    }


    @GetMapping('/')
    ModelAndView index(@ModelAttribute('registration') RegistrationCommand registration) {

        LOG.info("Starting registration process {}", registration.registrationId)
        Map<String, Object> model = ['registration': registration]
        return new ModelAndView('index', model)
    }


    @PostMapping('/register')
    ModelAndView register(@ModelAttribute('registration') RegistrationCommand registration) {

        LOG.info("Registering new user '{}' ({})", registration.username, registration.registrationId)
        this.api.enrolledEvent(registration)
        return new ModelAndView('pii')
    }


    @PostMapping('/finish')
    ModelAndView finishRegistration(
            @ModelAttribute('registration') RegistrationCommand registration,
            SessionStatus status) {

        LOG.info("User registration complete ({})", registration.registrationId)
        this.api.piiEvent(registration)
        status.setComplete()
        return new ModelAndView('complete')
    }
}
