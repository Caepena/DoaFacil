package br.com.fiap.ong;

import br.com.fiap.config.MessageHelper;
import br.com.fiap.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ong")
@RequiredArgsConstructor
public class ONGController {

    private final ONGService ongService;
    private final MessageSource messageSource;
    private final MessageHelper messageHelper;
    private final UserService userService;

    @GetMapping
    public String index(Model model,@AuthenticationPrincipal OAuth2User user) {
        var ongs = ongService.getAllONGS();
        model.addAttribute("ongs", ongs);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping
    public String ongForm(ONG ong) {
        return "ongForm";
    }

    @PostMapping("/form")
    public String create(@Valid ONG ong, BindingResult result, RedirectAttributes redirect) {

        if (result.hasErrors()) return "form";

        ongService.save(ong);
        redirect.addFlashAttribute("message", messageHelper.get("ong.new.success"));
        return "redirect:/ong";
    }

    @DeleteMapping("id")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        ongService.deleteById(id);
        redirect.addFlashAttribute("message", messageHelper.get("ong.delete.success"));
        return "redirect:/ong";
    }
}
