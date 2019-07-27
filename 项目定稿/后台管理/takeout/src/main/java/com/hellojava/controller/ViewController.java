package com.hellojava.controller;

import com.hellojava.dao.AdminDao.AdminRepository;
import com.hellojava.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping("/add_commodityTypeView")
    public String add_commodityTypeView(Integer busId, Model model) {
        model.addAttribute ("busId",busId);
        return "add_commodityType";
    }

    @RequestMapping("/loginview")
    public String loginView() {
        return "login";
    }

    @RequestMapping("/errorview")
    public String errorView() {
        return "errror";
    }

    @RequestMapping("/homeview")
    public String mainView() {
        return "home";
    }

    @RequestMapping("/backlogin")
    public String backLogin(String name , String pass) {
        Admin admin = adminRepository.findAdminByAdminNameAndAdminPassword (name , pass);
        if (admin != null) {
            return "home";
        }
        return "error";
    }
}
