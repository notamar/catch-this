package com.amar.catch_this.model;

public class DefensivePlay {

    private long gamePk;
    private String playId;
    private int playerId;
    private String playerName;
    private int stars;
    private double distance;
    private int position;
    private double hangTime;
    private int out;
    private int wall;
    private int back;
    private double landingPosX;
    private double landingPosY;
    private double startPosX;
    private double startPosY;
    private double normStartPosX;
    private double normStartPosY;
    private double playerAvgNormStartPosX;
    private double playerAvgNormStartPosY;
    private double catchRate;
    private double sprintSpeed;

    public DefensivePlay() {}

    public long getGamePk() {
        return gamePk;
    }

    public void setGamePk(long gamePk) {
        this.gamePk = gamePk;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getHangTime() {
        return hangTime;
    }

    public void setHangTime(double hangTime) {
        this.hangTime = hangTime;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public double getLandingPosX() {
        return landingPosX;
    }

    public void setLandingPosX(double landingPosX) {
        this.landingPosX = landingPosX;
    }

    public double getLandingPosY() {
        return landingPosY;
    }

    public void setLandingPosY(double landingPosY) {
        this.landingPosY = landingPosY;
    }

    public double getStartPosX() {
        return startPosX;
    }

    public void setStartPosX(double startPosX) {
        this.startPosX = startPosX;
    }

    public double getStartPosY() {
        return startPosY;
    }

    public void setStartPosY(double startPosY) {
        this.startPosY = startPosY;
    }

    public double getNormStartPosX() {
        return normStartPosX;
    }

    public void setNormStartPosX(double normStartPosX) {
        this.normStartPosX = normStartPosX;
    }

    public double getNormStartPosY() {
        return normStartPosY;
    }

    public void setNormStartPosY(double normStartPosY) {
        this.normStartPosY = normStartPosY;
    }

    public double getPlayerAvgNormStartPosX() {
        return playerAvgNormStartPosX;
    }

    public void setPlayerAvgNormStartPosX(double playerAvgNormStartPosX) {
        this.playerAvgNormStartPosX = playerAvgNormStartPosX;
    }

    public double getPlayerAvgNormStartPosY() {
        return playerAvgNormStartPosY;
    }

    public void setPlayerAvgNormStartPosY(double playerAvgNormStartPosY) {
        this.playerAvgNormStartPosY = playerAvgNormStartPosY;
    }

    public double getCatchRate() {
        return catchRate;
    }

    public void setCatchRate(double catchRate) {
        this.catchRate = catchRate;
    }

    public double getSprintSpeed() {
        return sprintSpeed;
    }

    public void setSprintSpeed(double sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }
}