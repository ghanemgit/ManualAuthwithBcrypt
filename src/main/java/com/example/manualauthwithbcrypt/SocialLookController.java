package com.example.manualauthwithbcrypt;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller

public class SocialLookController {


    private final SocialLookerRepository socialLookerRepository;
    private final SocialLookPostRepository socialLookPostRepository;


    public SocialLookController(SocialLookerRepository socialLookerRepository, SocialLookPostRepository socialLookPostRepository) {
        this.socialLookerRepository = socialLookerRepository;
        this.socialLookPostRepository = socialLookPostRepository;
    }

    @GetMapping("/")
    RedirectView getIndex(){
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/login.html";
    }


    @PostMapping("/login")
    public RedirectView logInUser(HttpServletRequest request, String username, String password)
    {
        SocialLooker socialLooker = socialLookerRepository.findSocialLookerByUsername(username);
        if ((socialLooker == null) || (!BCrypt.checkpw(password, socialLooker.getPassword())))
        {
            return new RedirectView("/login");
        }

        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        return new RedirectView("/posts");

    }

    @GetMapping("/signup")
    public String getSignupPage()
    {
        return "/signup.html";
    }


    @PostMapping("/signup")
    public RedirectView signUpUser(String username,String password){

        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(12));

        SocialLooker socialLooker = new SocialLooker(username,hashedPassword);

        socialLookerRepository.save(socialLooker);
        return new RedirectView("/login");
    }

    @GetMapping("/post")
    public String getPostPage(){
        return "/post.html";
    }
    

    @PostMapping("/post")
    RedirectView addNewPost(@ModelAttribute SocialLookPost socialLookPost){

        socialLookPostRepository.save(socialLookPost);
        return new RedirectView("posts");


    }

    @ResponseBody
    @GetMapping("/posts")
    Iterable<SocialLookPost> getAllPosts(){

        return socialLookPostRepository.findAll();
    }

}


