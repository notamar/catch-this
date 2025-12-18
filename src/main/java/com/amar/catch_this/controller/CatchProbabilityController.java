package com.amar.catch_this.controller;

import com.amar.catch_this.model.Player;
import com.amar.catch_this.model.DefensivePlay;
import com.amar.catch_this.service.SavantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CatchProbabilityController {

    private final SavantService savantService;

    public CatchProbabilityController(SavantService savantService) {
        this.savantService = savantService;
    }

    /**
     * Dynamic endpoint for MLB catch probability
     * Defaults to Fernando Tatis Jr. (playerId=665487, season=2025)
     *
     * Example:
     * /api/v1/catch-probability?playerId=665487&season=2025
     * 
     * Use this endpoint to get playerId from name.
     * https://statsapi.mlb.com/api/v1/people/search?names=Julio%20Rodriguez
     */
    @GetMapping("/api/v1/people")
    public Player getPlayer(
        @RequestParam(defaultValue = "Fernando Tatis Jr") String playerName
    ) throws Exception {
        return savantService.getPlayer(playerName);
    }

    @GetMapping("/api/v1/catch-probability")
    public List<DefensivePlay> getDefensivePlays(
            @RequestParam(defaultValue = "665487") int playerId, // default: Tatis Jr.
            @RequestParam(defaultValue = "2025") int season
    ) throws Exception {
        return savantService.getDefensivePlays(playerId, season);
    }
}