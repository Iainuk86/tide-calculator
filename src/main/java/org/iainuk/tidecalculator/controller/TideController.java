package org.iainuk.tidecalculator.controller;

import org.iainuk.tidecalculator.service.TideServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class TideController {

    @Autowired
    TideServiceImpl tideService;

    @PostMapping("/home")
    public String processAddress(@ModelAttribute("address") String address, Model model) throws IOException, InterruptedException
    {
        String latLong = tideService.getLatLong(address);
        String tideLevel = tideService.getTideLevel(latLong);

        model.addAttribute("tideLevel", tideLevel);

        return "tide";
    }
}
