package com.example.dnlab.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/application")
public class ApplicationViewController {

    @GetMapping("insertApplication")
    public String insertApplication(){
        return "insertApplication";
    }
    @GetMapping("AllApplication")
    public String getAllApplication(){
        return "applications";
    }

    @GetMapping("details/{num}")
    public String getApplicationDetail(){
        return "applicationDetail";
    }
}
