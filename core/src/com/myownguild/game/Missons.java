package com.myownguild.game;

import java.util.Random;

public class Missons {

    private int lvl = 0;
    private int reward = 0;
    private String name = null;
    private MissionNames missonsName;
    private int noga = 1;

    private Random r;

    private int guildLvl;

    public Missons (int guildLvl){
        this.guildLvl = guildLvl;
        init();
    }

    private void init(){
        lvl = getMissionLvl(guildLvl);
        reward = getMissionReward(lvl);
        name = "Mission "+noga;

    }

    private int getMissionLvl(int guildLvl){
        return guildLvl;
    }

    private Integer getMissionReward(int lvl){
        return lvl * 50;
    }

    public Integer getLvl() {
        return lvl;
    }

    public Integer getReward() {
        return reward;
    }

    public String getName() {
        return name;
    }

    public void pass(){}
}
