package com.massita.transformers.api.model;

import com.google.gson.annotations.SerializedName;

public class Transformer {

    private static final int DEFAULT_VALUE = 5;

    private static final String OPTIMUS_PRIME = "Optimus Prime";
    private static final String PREDAKING = "Predaking";

    public static final String TEAM_AUTOBOTS = "A";
    public static final String TEAM_DECEPTICON = "D";

    private static final String DEFAULT_TEAM = TEAM_AUTOBOTS;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("team")
    private String team;

    @SerializedName("strength")
    private int strength;

    @SerializedName("intelligence")
    private int intelligence;

    @SerializedName("speed")
    private int speed;

    @SerializedName("endurance")
    private int endurance;

    @SerializedName("rank")
    private int rank;

    @SerializedName("courage")
    private int courage;

    @SerializedName("firepower")
    private int firepower;

    @SerializedName("skill")
    private int skill;

    @SerializedName("team_icon")
    private String teamIcon;

    public Transformer() {
        this.team = DEFAULT_TEAM;
        this.strength = DEFAULT_VALUE;
        this.intelligence = DEFAULT_VALUE;
        this.speed = DEFAULT_VALUE;
        this.endurance = DEFAULT_VALUE;
        this.rank = DEFAULT_VALUE;
        this.courage = DEFAULT_VALUE;
        this.firepower = DEFAULT_VALUE;
        this.skill = DEFAULT_VALUE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCourage() {
        return courage;
    }

    public void setCourage(int courage) {
        this.courage = courage;
    }

    public int getFirepower() {
        return firepower;
    }

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public String getTeamIcon() {
        return teamIcon;
    }

    public void setTeamIcon(String teamIcon) {
        this.teamIcon = teamIcon;
    }

    public void setIsAutobots(boolean isAutobots) {
        if(isAutobots) {
            setTeam(TEAM_AUTOBOTS);
        } else {
            setTeam(TEAM_DECEPTICON);
        }
    }

    public boolean isAutobots() {
        return TEAM_AUTOBOTS.equals(getTeam());
    }

    public int getOverall() {
        return strength + intelligence + speed + endurance + firepower;
    }

    public boolean isOverpower() {
        return OPTIMUS_PRIME.equalsIgnoreCase(name) || PREDAKING.equalsIgnoreCase(name);
    }
}
