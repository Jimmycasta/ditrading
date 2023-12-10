package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.ChartPatternEntity;
import com.jimmycasta.ditrading.services.ChartPatternService;
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
@RequestMapping("/patterns")
public class ChartPatternController {

    private final ChartPatternService chartPatternService;

    @Autowired
    public ChartPatternController(ChartPatternService chartPatternService) {
        this.chartPatternService = chartPatternService;

    }

    @GetMapping("/list")
    public String pattern(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.parseInt(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<ChartPatternEntity> listPattern = chartPatternService.getAllPage(pageRequest);

        int totalPages = listPattern.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);

        }
        model.addAttribute("listPattern", listPattern);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPages);
        model.addAttribute("title", "Patrones chartistas");
        model.addAttribute("titleAction", "Patrones chartistas");
        return "patterns/listPattern";
    }

    @GetMapping("/new")
    public String newPattern(@ModelAttribute("pattern") ChartPatternEntity pattern, Model model) {
        model.addAttribute("title", "Nuevo patron");
        model.addAttribute("titleAction", "Nuevo patron chartista");
        return "patterns/newPattern";
    }

    @PostMapping("/save")
    public String save(@Valid ChartPatternEntity pattern, BindingResult result,
                       RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pattern", new ChartPatternEntity());
            model.addAttribute("title", "Nuevo patron chartista");
            model.addAttribute("titleAction", "Nuevo patron chartista");
            return "patterns/newPattern";
        }
        chartPatternService.save(pattern);
        attributes.addFlashAttribute("message", "Patron guardado");
        return "redirect:/patterns/list";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("pattern") ChartPatternEntity pattern) {
        pattern = chartPatternService.getById(id);
        model.addAttribute("pattern", pattern);
        model.addAttribute("title", "Editar patron");
        model.addAttribute("titleAction", "Editar patron");
        return "patterns/newPattern";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        chartPatternService.delete(id);
        return "redirect:/patterns/list";

    }
}
