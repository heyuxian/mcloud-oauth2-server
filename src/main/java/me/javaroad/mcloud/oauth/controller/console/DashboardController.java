package me.javaroad.mcloud.oauth.controller.console;

import me.javaroad.mcloud.oauth.controller.OAuthConstants;
import me.javaroad.mcloud.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heyx
 */
@Controller
@RequestMapping(OAuthConstants.CONSOLE_PREFIX)
public class DashboardController extends BaseController {

    @GetMapping(value = {"/dashboard", "/", ""})
    public String index() {
        return view("index");
    }

}
