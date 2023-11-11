package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.PositionEntity;
import com.jimmycasta.ditrading.services.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/list")
    public String positions(Model model) {
        List<PositionEntity> listPosition = positionService.getAll();
        model.addAttribute("listPosition", listPosition);
        model.addAttribute("title", "Posiciones");
        model.addAttribute("titleAction", "Posiciones Largo/Corto");
        return "/positions/listPosition";

    }

    @GetMapping("/new")
    public String newPosition(@ModelAttribute("position") PositionEntity position, Model model) {
        model.addAttribute("title", "Nueva posición");
        model.addAttribute("titleAction", "Nueva posición");
        return "/positions/newPosition";

    }

    @PostMapping("/save")
    public String save(@Valid PositionEntity position,
                       BindingResult result, RedirectAttributes attributes,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("position", new PositionEntity());
            model.addAttribute("title", "Nueva posición");
            model.addAttribute("titleAction", "Nueva posición");
            return "positions/newPosition";
        }
        positionService.save(position);
        attributes.addFlashAttribute("message", "Posición guardada");
        return "redirect:/positions/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("position") PositionEntity position) {
        position = positionService.getById(id);
        model.addAttribute("position", position);
        model.addAttribute("title", "Editar posición");
        model.addAttribute("titleAction", "Editar posición");
        return "positions/newPosition";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        positionService.delete(id);
        return "redirect:/positions/list";

    }


}
