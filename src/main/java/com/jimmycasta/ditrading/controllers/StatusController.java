package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.StatusEntity;
import com.jimmycasta.ditrading.services.StatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;

    }

    @GetMapping("/list")
    public String status(Model model) {
        List<StatusEntity> listStatus = statusService.getAll();
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("title", "Estado de trade");
        model.addAttribute("titleAction", "Estados del trade");
        return "/status/listStatus";

    }

    @GetMapping("/new")
    public String newStatus(@ModelAttribute("status") StatusEntity status, Model model) {
        model.addAttribute("title", "Nuevo estado");
        model.addAttribute("titleAction", "Nuevo estado");
        return "/status/newStatus";

    }

    @PostMapping("/save")
    public String save(@Valid StatusEntity status,
                       BindingResult result, RedirectAttributes attributes,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("status", new StatusEntity());
            model.addAttribute("title", "Nuevo estado");
            model.addAttribute("titleAction", "Nuevo estado");
            return "status/newStatus";
        }
        statusService.save(status);
        attributes.addFlashAttribute("message", "Estado guardado");
        return "redirect:/status/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("status") StatusEntity status) {
        status = statusService.getById(id);
        model.addAttribute("status", status);
        model.addAttribute("title", "Editar estado");
        model.addAttribute("titleAction", "Editar estado");
        return "status/newStatus";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        statusService.delete(id);
        return "redirect:/status/list";

    }


}
