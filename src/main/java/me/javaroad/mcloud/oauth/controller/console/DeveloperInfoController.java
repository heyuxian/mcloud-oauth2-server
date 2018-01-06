package me.javaroad.mcloud.oauth.controller.console;

import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.oauth.dto.response.ClientResponse;
import me.javaroad.mcloud.oauth.dto.response.DeveloperInfoResponse;
import me.javaroad.mcloud.oauth.service.DeveloperInfoService;
import me.javaroad.mcloud.oauth.service.ClientService;
import me.javaroad.mcloud.oauth.service.ScopeService;
import me.javaroad.mcloud.web.bind.annotation.CurrentUser;
import me.javaroad.mcloud.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heyx
 */
@Controller
@RequestMapping(OAuthConstants.CONSOLE_PREFIX + "/dev")
public class DeveloperInfoController extends BaseController {

    private final DeveloperInfoService developerInfoService;
    private final ClientService clientService;
    private final ScopeService scopeService;

    @Autowired
    public DeveloperInfoController(DeveloperInfoService developerInfoService, ClientService clientService,
        ScopeService scopeService) {
        this.developerInfoService = developerInfoService;
        this.clientService = clientService;
        this.scopeService = scopeService;
    }

    @GetMapping("info")
    public String info(@CurrentUser String username, Model model) {
        DeveloperInfoResponse developerInfo = developerInfoService.getResponse(username);
        ClientResponse clientResponse = clientService.getResponseByUsername(username);
        model.addAttribute("info", developerInfo);
        model.addAttribute("clientInfo", clientResponse);
        model.addAttribute("scopes", scopeService.getAll());
        return view("info");
    }

    @GetMapping("apply")
    public String apply(@CurrentUser String username, Model model) {
        DeveloperInfoResponse developerInfo = developerInfoService.getResponse(username);
        model.addAttribute("info", developerInfo);
        return view("apply");
    }
}
