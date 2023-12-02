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
import java.time.LocalDateTime;
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


    @Autowired
    public TradeController(TradeService tradeService,
                           SymbolService symbolService,
                           TimeFrameService timeFrameService,
                           PositionService positionService,
                           StrategyService strategyService) {

        this.tradeService = tradeService;
        this.symbolService = symbolService;
        this.timeFrameService = timeFrameService;
        this.positionService = positionService;
        this.strategyService = strategyService;
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

        model.addAttribute("listSymbols", listSymbols);
        model.addAttribute("listFrames", listFrames);
        model.addAttribute("listPositions", listPositions);
        model.addAttribute("listStrategies", listStrategies);
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

            model.addAttribute("listSymbols", listSymbols);
            model.addAttribute("listFrames", listFrames);
            model.addAttribute("listPositions", listPositions);
            model.addAttribute("listStrategies", listStrategies);

            model.addAttribute("title", "Nuevo trade");
            model.addAttribute("titleAction", "Nuevo trade");
            return "trades/newTrade";
        }
        if (trade.getTakeProfit() == null) {
            attributes.addFlashAttribute("message", "Trade guardado");
            trade.setEntryLength(operationMath.getDecimalQuantity(trade.getEntryPrice()));
            trade.setStopLength(operationMath.getDecimalQuantity(trade.getStopLoss()));
            tradeService.save(trade);
            return "redirect:/trades/list";

        }
        attributes.addFlashAttribute("message", "Trade guardado");
        trade.setReturnInvestment(operationMath.diffPercent(trade.getTakeProfit(), trade.getEntryPrice()));
        trade.setPnlBalance(operationMath.calculatePnl(trade.getTakeProfit(), trade.getEntryPrice(), trade.getAssetsQuantity()));
        trade.setPnlPercentage(operationMath.diffPercent(trade.getTakeProfit(), trade.getEntryPrice()));
        trade.setLastBalance(operationMath.getLastBalance(trade.getTakeProfit(), trade.getEntryPrice(), trade.getAssetsQuantity()));
        trade.setRiskReward(operationMath.getRiskReward(trade.getEntryPrice(), trade.getTakeProfit(), trade.getStopLoss()));

        trade.setProfitLength(operationMath.getDecimalQuantity(trade.getTakeProfit()));
        trade.setEntryLength(operationMath.getDecimalQuantity(trade.getEntryPrice()));
        trade.setStopLength(operationMath.getDecimalQuantity(trade.getStopLoss()));
        trade.setPnlLength(operationMath.getDecimalQuantity(trade.getPnlBalance()));
        trade.setDateTime(LocalDateTime.now());
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

        model.addAttribute("listSymbols", listSymbols);
        model.addAttribute("listFrames", listFrames);
        model.addAttribute("listPositions", listPositions);
        model.addAttribute("listStrategies", listStrategies);

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


