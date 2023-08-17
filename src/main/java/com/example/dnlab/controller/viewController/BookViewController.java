package com.example.dnlab.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/book")
@Controller
public class BookViewController {

    @GetMapping("/insertBook")
    public String insertBook(){
        return "insertBook";
    }

    @GetMapping("/bookLists")
    public String bookLists(){
        return "bookLists";
    }
}
