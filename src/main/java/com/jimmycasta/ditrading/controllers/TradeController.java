package com.jimmycasta.ditrading.controllers;

import com.jimmycasta.ditrading.entities.*;
import com.jimmycasta.ditrading.services.*;
import com.jimmycasta.ditrading.utils.operationMath;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/trades")
public class TradeController {

    private final TradeService tradeService;
    private final SymbolService symbolService;
    private final TimeFrameService timeFrameService;
    private final PositionService positionService;
    private final StrategyService strategyService;
    private final StatusService statusService;

    @Autowired
    public TradeController(TradeService tradeService,
                           SymbolService symbolService,
                           TimeFrameService timeFrameService,
                           PositionService positionService,
                           StrategyService strategyService,
                           StatusService statusService) {

        this.tradeService = tradeService;
        this.symbolService = symbolService;
        this.timeFrameService = timeFrameService;
        this.positionService = positionService;
        this.strategyService = strategyService;
        this.statusService = statusService;
    }

    @GetMapping("/list")
    public String trades(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.parseInt(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<TradeEntity> listTrade = tradeService.getAllPage(pageRequest);

        int totalPages = listTrade.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);

        }
        model.addAttribute("listTrade", listTrade);
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPages);
        model.addAttribute("title", "Trades");
        model.addAttribute("titleAction", "Lista de Trades");
        return "trades/listTrade";
    }

    @GetMapping("/new")
    public String newTrade(@ModelAttribute("trade") TradeEntity trade, Model model) {

        trade.setEntryDate(LocalDate.now());

        List<SymbolEntity> listSymbols = symbolService.getAll();
        List<TimeFrameEntity> listFrames = timeFrameService.getAll();
        List<PositionEntity> listPositions = positionService.getAll();
        List<StrategyEntity> listStrategies = strategyService.getAll();
        List<StatusEntity> listStatus = statusService.getAll();

        model.addAttribute("listSymbols", listSymbols);
        model.addAttribute("listFrames", listFrames);
        model.addAttribute("listPositions", listPositions);
        model.addAttribute("listStrategies", listStrategies);
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("title", "Nuevo trade");
        model.addAttribute("titleAction", "Nuevo trade");
        return "trades/newTrade";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("trade") TradeEntity trade,
                       BindingResult result, RedirectAttributes attributes,
                       Model model) {
        if (result.hasErrors()) {

            List<SymbolEntity> listSymbols = symbolService.getAll();
            List<TimeFrameEntity> listFrames = timeFrameService.getAll();
            List<PositionEntity> listPositions = positionService.getAll();
            List<StrategyEntity> listStrategies = strategyService.getAll();
            List<StatusEntity> listStatus = statusService.getAll();

            model.addAttribute("listSymbols", listSymbols);
            model.addAttribute("listFrames", listFrames);
            model.addAttribute("listPositions", listPositions);
            model.addAttribute("listStrategies", listStrategies);
            model.addAttribute("listStatus", listStatus);
            model.addAttribute("title", "Nuevo trade");
            model.addAttribute("titleAction", "Nuevo trade");
            return "trades/newTrade";
        }
        if (trade.getTakeProfit() == null) {
            tradeService.save(trade);
            return "redirect:/trades/list";

        }
        attributes.addFlashAttribute("message", "Trade guardado");
        trade.setReturnInvestment(operationMath.diffPercent(trade.getTakeProfit(), trade.getEntryPrice()));
        trade.setPnlBalance(operationMath.calculatePnl(trade.getTakeProfit(), trade.getEntryPrice(), trade.getAssetsQuantity()));
        trade.setPnlPercentage(operationMath.diffPercent(trade.getTakeProfit(), trade.getEntryPrice()));
        tradeService.save(trade);
        return "redirect:/trades/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model,
                       @ModelAttribute("trade") TradeEntity trade) {
        trade = tradeService.getById(id);
        trade.setExitDate(LocalDate.now());

        List<SymbolEntity> listSymbols = symbolService.getAll();
        List<TimeFrameEntity> listFrames = timeFrameService.getAll();
        List<PositionEntity> listPositions = positionService.getAll();
        List<StrategyEntity> listStrategies = strategyService.getAll();
        List<StatusEntity> listStatus = statusService.getAll();

        model.addAttribute("listSymbols", listSymbols);
        model.addAttribute("listFrames", listFrames);
        model.addAttribute("listPositions", listPositions);
        model.addAttribute("listStrategies", listStrategies);
        model.addAttribute("listStatus", listStatus);

        model.addAttribute("trade", trade);
        model.addAttribute("title", "Editar/Cerrar - trade");
        model.addAttribute("titleAction", "Editar/Cerrar - trade");
        return "trades/editTrade";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        tradeService.delete(id);
        return "redirect:/trades/list";

    }


}


