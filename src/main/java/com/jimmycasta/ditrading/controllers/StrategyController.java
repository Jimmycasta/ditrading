package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.StrategyEntity;
import com.jimmycasta.ditrading.services.StrategyService;
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
@RequestMapping("/strategies")
public class StrategyController {

    private final StrategyService strategyService;

    @Autowired
    public StrategyController(StrategyService strategyService) {
        this.strategyService = strategyService;
    }

    @GetMapping("/list")
    public String strategy(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.parseInt(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<StrategyEntity> listStrategy = strategyService.getAllPage(pageRequest);

        int totalPages = listStrategy.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);

        }
        model.addAttribute("listStrategy", listStrategy);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPages);
        model.addAttribute("title", "Estrategias");
        model.addAttribute("titleAction", "Estrategias");
        return "strategies/listStrategy";
    }

    @GetMapping("/new")
    public String newStrategy(@ModelAttribute("strategy") StrategyEntity strategy, Model model) {
        model.addAttribute("title", "Nueva estrategia");
        model.addAttribute("titleAction", "Nueva estrategia");
        return "strategies/newStrategy";
    }

    @PostMapping("/save")
    public String save(@Valid StrategyEntity strategy, BindingResult result,
                       RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("strategy", new StrategyEntity());
            model.addAttribute("title", "Nueva estrategia");
            model.addAttribute("titleAction", "Nueva estrategia");
            return "strategies/newStrategy";
        }
        strategyService.save(strategy);
        attributes.addFlashAttribute("message", "Estrategia guardada");
        return "redirect:/strategies/list";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("strategy") StrategyEntity strategy) {
        strategy = strategyService.getById(id);
        model.addAttribute("strategy", strategy);
        model.addAttribute("title", "Editar estrategia");
        model.addAttribute("titleAction", "Editar estrategia");
        return "strategies/newStrategy";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        strategyService.delete(id);
        return "redirect:/strategies/list";

    }
}
