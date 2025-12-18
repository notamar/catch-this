package com.amar.catch_this.service;

import com.amar.catch_this.model.Player;
import com.amar.catch_this.model.DefensivePlay;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class SavantService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SavantService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Fetches player object for a given playerId.
     * Returns an empty Player object if no data is found.
     */
    public Player getPlayer(String playerName) throws Exception {
        String encodedPlayerName = URLEncoder.encode(playerName, StandardCharsets.UTF_8);

        String url = String.format(
            "https://statsapi.mlb.com/api/v1/people/search?names=%s",
            encodedPlayerName
        );

        String jsonResponse = restTemplate.getForObject(url, String.class);
        Player player = new Player();

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            // return empty object instead of throwing
            return player;
        }

        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        JsonNode peopleNode = rootNode.path("people");

        if (!peopleNode.isArray() || peopleNode.size() == 0) {
            // API returned no data
            return player;
        }

        JsonNode playerNode = peopleNode.get(0);
        {
            player.setId(playerNode.path("id").asText());
            player.setFullName(playerNode.path("fullName").asText());
        }

        return player;
    }

    /**
     * Fetches catch probability for a given playerId and season.
     * Returns an empty CatchProbability object if no data is found.
     */
    public List<DefensivePlay> getDefensivePlays(int playerId, int season) throws Exception {
        String url = String.format(
                "https://baseballsavant.mlb.com/player-services/range?playerId=%d&season=%d&playerType=fielder",
                playerId, season
        );

        String jsonResponse = restTemplate.getForObject(url, String.class);
        ArrayList<DefensivePlay> plays = new ArrayList<>();

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            // return empty object instead of throwing
            return plays;
        }

        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        if (!rootNode.isArray() || rootNode.size() == 0) {
            // API returned no data
            return plays;
        }

        for (JsonNode playNode : rootNode)
            {
                DefensivePlay dp = new DefensivePlay();

                dp.setGamePk(playNode.path("game_pk").asLong());
                dp.setPlayId(playNode.path("play_id").asText(null));
                dp.setPlayerId(playNode.path("player_id").asInt());
                dp.setPlayerName(playNode.path("name_display_first_last").asText(null));
                dp.setStars(playNode.path("stars").asInt());
                dp.setDistance(playNode.path("distance").asDouble());
                dp.setPosition(playNode.path("pos").asInt());
                dp.setHangTime(playNode.path("hang_time").asDouble());
                dp.setOut(playNode.path("out").asInt());
                dp.setWall(playNode.path("wall").asInt());
                dp.setBack(playNode.path("back").asInt());
                dp.setLandingPosX(playNode.path("landing_pos_x").asDouble());
                dp.setLandingPosY(playNode.path("landing_pos_y").asDouble());
                dp.setStartPosX(playNode.path("start_pos_x").asDouble());
                dp.setStartPosY(playNode.path("start_pos_y").asDouble());
                dp.setNormStartPosX(playNode.path("norm_start_pos_x").asDouble());
                dp.setNormStartPosY(playNode.path("norm_start_pos_y").asDouble());
                dp.setPlayerAvgNormStartPosX(playNode.path("player_avg_norm_start_pos_x").asDouble());
                dp.setPlayerAvgNormStartPosY(playNode.path("player_avg_norm_start_pos_y").asDouble());
                dp.setCatchRate(playNode.path("catch_rate").asDouble());
                dp.setSprintSpeed(playNode.path("sprint_speed").asDouble());
        
                plays.add(dp);
            }
        return plays;
    }
}