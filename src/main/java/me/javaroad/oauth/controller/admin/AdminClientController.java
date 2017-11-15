package me.javaroad.oauth.controller.admin;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_PREFIX;

import me.javaroad.oauth.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heyx
 */
@Controller
@RequestMapping(ADMIN_PREFIX + "/client")
public class AdminClientController {

    private final ClientService clientService;

    @Autowired
    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("index")
    public String index(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("page", clientService.getPage(pageable));
        return "admin/client/index";
    }
}
