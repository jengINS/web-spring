package com.tedinno.ace.WebAppController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tedinno.ace.Service.AuthenService;
import com.tedinno.ace.bind.tbl_users;

@Controller
public class AuthenController {
    @Autowired
    private AuthenService authenService;

    @GetMapping("/authen_list1")
    public String authenList(Model model) {
        // model.addAttribute("authen_list1", authenService.getAllEmployee1());
        return "admin/authen_list1";
    }

}
