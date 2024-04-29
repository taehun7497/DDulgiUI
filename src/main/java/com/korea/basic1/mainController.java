package com.korea.basic1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class mainController {

    @GetMapping("/")
    public String root(){
        return "main";
    }
}
