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
@RequestMapping(ADMIN_PREFIX + "/user")
public class AdminUserController extends BaseController {

    @GetMapping("index")
    public String index() {
        return view("user/index");
    }

    @GetMapping("create")
    public String create() {
        return view("user/create");
    }

    @GetMapping("group")
    public String group() {
        return view("user/group");
    }
}
