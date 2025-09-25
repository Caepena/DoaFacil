package br.com.fiap.campanha;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;

@Controller
@RequestMapping("/campanha")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;
    private final MessageHelper messageHelper;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("campanhas", campanhaService.getAllCampanhas());
        return "campanha/index";
    }

    @GetMapping("/form")
    public String form(Campanha campanha) {
        return "campanha/form";
    }

    @PostMapping
    public String create(@Valid Campanha campanha, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) return "campanha/form";

        campanhaService.save(campanha);
        redirect.addFlashAttribute("message", messageHelper.get("campaign.new.success"));
        return "redirect:/campanha";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        campanhaService.deleteById(id);
        redirect.addFlashAttribute("message", messageHelper.get("campaign.delete.success"));
        return "redirect:/campanha";
    }
}