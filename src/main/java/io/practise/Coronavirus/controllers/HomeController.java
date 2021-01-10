package io.practise.Coronavirus.controllers;


import io.practise.Coronavirus.models.LocationStats;
import io.practise.Coronavirus.services.Coronavirusdataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    @Autowired
    Coronavirusdataservice coronavirusdataservice;

    @GetMapping("/")
    public String home(Model model)
    {

        List<LocationStats> allStats = coronavirusdataservice.getAllstats();

        System.out.println("Size is " + allStats.size());
        model.addAttribute("localtionStats",allStats);

        int totalreportedCases= allStats.stream().mapToInt(stat -> stat.getLatestTotalScore()).sum();
        model.addAttribute("totalreportedCases",totalreportedCases);

        return "home";
    }

}
