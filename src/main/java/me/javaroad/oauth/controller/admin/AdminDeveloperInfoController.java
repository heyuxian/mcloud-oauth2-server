package me.javaroad.oauth.controller.admin;

import static me.javaroad.oauth.controller.OAuthConstants.ADMIN_PREFIX;

import me.javaroad.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author heyx
 */
@Controller
@RequestMapping(ADMIN_PREFIX + "/dev")
public class AdminDeveloperInfoController extends BaseController {

    @GetMapping("index")
    public String index() {
        return view("index");
    }

    @GetMapping("pending")
    public String pending() {
        return view("pending");
    }
}
