package ibf.ssf.day14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/home")
public class HomeController {
    
    @GetMapping("/pagea")
    public String pageA(HttpSession session, Model model) {
        
        return "pagea";
    }

    // Sends fullName to page B with session object
    // When should multivaluemap vs requestparam vs modelattribute be used?
    @PostMapping("/pagea")
    public String postPageA(@RequestBody MultiValueMap<String, String> form, HttpSession session, Model model) {
        
        // see pagea.html fullname
        String fullName = form.getFirst("fullName"); // difference between get and getfirst?
        session.setAttribute("fullName", fullName);
        model.addAttribute("fullName", session.getAttribute("fullName"));

        return "pageb";
    }

    

    @GetMapping("/pagec")
    public String pageC(HttpSession session, Model model) {
        String fullName = (String)session.getAttribute("fullName");
        model.addAttribute("fullName", fullName);
        
        return "pagec";
    }

    @GetMapping("/page0") 
    public String postPageC(HttpSession session) {
        
        session.invalidate();
    
        // Don't return pagea as there is no model involved
        return "redirect:/home/pagea";
    }
}
