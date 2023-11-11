package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.SymbolEntity;
import com.jimmycasta.ditrading.services.SymbolService;
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
@RequestMapping("/symbols")
public class SymbolController {

    private final SymbolService symbolService;

    @Autowired
    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @GetMapping("/list")
    public String symbols(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.parseInt(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<SymbolEntity> listSymbol = symbolService.getAllPage(pageRequest);

        int totalPages = listSymbol.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);

        }
        model.addAttribute("listSymbol", listSymbol);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPages);
        model.addAttribute("title","Símbolos");
        model.addAttribute("titleAction","Símbolos/Instrumentos");
        return "symbols/listSymbol";
    }

    @GetMapping("/new")
    public String newSymbol(@ModelAttribute("symbol") SymbolEntity symbol, Model model) {
        model.addAttribute("title", "Nuevo símbolo");
        model.addAttribute("titleAction", "Nuevo símbolo");
        return "symbols/newSymbol";
    }

    @PostMapping("/save")
    public String saveSymbol(@Valid SymbolEntity symbol,
                             BindingResult result, RedirectAttributes attributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("symbol", new SymbolEntity());
            model.addAttribute("title", "Nuevo símbolo");
            model.addAttribute("titleAction", "Nuevo símbolo");
            return "symbols/newSymbol";
        }
        symbolService.save(symbol);
        attributes.addFlashAttribute("message", "Símbolo guardado");
        return "redirect:/symbols/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("symbol") SymbolEntity symbol) {
        symbol = symbolService.getById(id);
        model.addAttribute("symbol", symbol);
        model.addAttribute("title", "Editar símbolo");
        model.addAttribute("titleAction", "Editar símbolo");
        return "symbols/newSymbol";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        symbolService.delete(id);
        return "redirect:/symbols/list";

    }


}
