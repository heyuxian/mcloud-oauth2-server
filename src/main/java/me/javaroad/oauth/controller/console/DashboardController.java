package me.javaroad.oauth.controller.console;

import static me.javaroad.oauth.controller.OAuthConstants.CONSOLE_PREFIX;

import me.javaroad.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heyx
 */
@Controller
@RequestMapping(CONSOLE_PREFIX)
public class DashboardController extends BaseController {

    @GetMapping(value = {"/dashboard", "/", ""})
    public String index() {
        return view("index");
    }

}
