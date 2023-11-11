package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.TraderEntity;
import com.jimmycasta.ditrading.services.TraderService;
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
@RequestMapping("/traders")
public class TraderController {

    private final TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
        this.traderService = traderService;

    }

    @GetMapping("/list")
    public String trader(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.parseInt(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<TraderEntity> listTrader = traderService.getAll(pageRequest);

        int totalPages = listTrader.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);

        }
        model.addAttribute("listTrader", listTrader);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPages);
        model.addAttribute("title", "Traders");
        model.addAttribute("titleAction", "Traders");
        return "traders/listTrader";
    }

    @GetMapping("/new")
    public String newSymbol(@ModelAttribute("trader") TraderEntity trader, Model model) {
        model.addAttribute("title", "Nuevo trader");
        model.addAttribute("titleAction", "Nuevo trader");
        return "traders/newTrader";
    }

    @PostMapping("/save")
    public String saveSymbol(@Valid TraderEntity trader,
                             BindingResult result, RedirectAttributes attributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("trader", new TraderEntity());
            model.addAttribute("title", "Nuevo trader");
            model.addAttribute("titleAction", "Nuevo trader");
            return "traders/newTrader";
        }
        traderService.save(trader);
        attributes.addFlashAttribute("message", "Trader guardado");
        return "redirect:/traders/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("trader") TraderEntity trader) {
        trader = traderService.getById(id);
        model.addAttribute("trader", trader);
        model.addAttribute("title", "Editar trader");
        model.addAttribute("titleAction", "Editar trader");
        return "traders/newTrader";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        traderService.delete(id);
        return "redirect:/traders/list";

    }

}
