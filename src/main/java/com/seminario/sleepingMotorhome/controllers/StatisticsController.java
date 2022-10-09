package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.services.StatisticsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsServices statisticsServices;

    @GetMapping(path = "/chartList")
    public String chartMethod (Model model){
        model.addAttribute("chartDataMotorhomes", statisticsServices.chartMotorhomeStayPerMonth());
        model.addAttribute("chartDataMotorhomesSmall", statisticsServices.chartMotorhomeStayPerMonthAndMotorhomeType(1));
        model.addAttribute("chartDataMotorhomesMedium", statisticsServices.chartMotorhomeStayPerMonthAndMotorhomeType(2));
        model.addAttribute("chartDataMotorhomesLarge", statisticsServices.chartMotorhomeStayPerMonthAndMotorhomeType(3));
        return "reports/charts";
    }

}

