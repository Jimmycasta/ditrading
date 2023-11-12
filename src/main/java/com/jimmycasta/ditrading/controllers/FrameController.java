package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.TimeFrameEntity;
import com.jimmycasta.ditrading.services.TimeFrameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/frames")
public class FrameController {

    private final TimeFrameService timeFrameService;

    @Autowired
    public FrameController(TimeFrameService timeFrameService) {
        this.timeFrameService = timeFrameService;
    }

    @GetMapping("/list")
    public String frames(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.parseInt(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<TimeFrameEntity> listFrame = timeFrameService.getAllPage(pageRequest);

        int totalPages = listFrame.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("listFrame", listFrame);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPages);
        model.addAttribute("title", "Intervalos de tiempo");
        model.addAttribute("titleAction", "Intervalos de tiempo");
        return "frames/listFrame";

    }

    @GetMapping("/new")
    public String newFrame(@ModelAttribute("frame") TimeFrameEntity frame, Model model) {
        model.addAttribute("title", "Nuevo Intervalo/Tiempo");
        model.addAttribute("titleAction", "Nuevo intervalo de tiempo");
        return "frames/newFrame";

    }

    @PostMapping("/save")
    public String saveFrame(@Valid TimeFrameEntity frame, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("frame", new TimeFrameEntity());
            model.addAttribute("title", "\"Nuevo Intervalo/Tiempo");
            return "frames/newFrame";

        }
        timeFrameService.save(frame);
        attributes.addFlashAttribute("message", "Intervalo de tiempo guardado ");
        return "redirect:/frames/list";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        timeFrameService.delete(id);
        return "redirect:/frames/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model, @ModelAttribute("frame") TimeFrameEntity frame) {
        frame = timeFrameService.getById(id);
        model.addAttribute("frame", frame);
        model.addAttribute("title", "Editar Intervalo de tiempo");
        model.addAttribute("titleAction", "Editar intervalos de tiempo");
        return "frames/newFrame";

    }
}
