package com.daniilvdovin.wowraider.model;


public  class MythicPlusRanks {
    public  Rank
            overall,
            tank,
            healer,
            dps,
            classc,
            class_tank,
            class_healer,
            class_dps;

    public Rank getOverall() {
        return overall;
    }

    public void setOverall(Rank overall) {
        this.overall = overall;
    }

    public Rank getTank() {
        return tank;
    }

    public void setTank(Rank tank) {
        this.tank = tank;
    }

    public Rank getHealer() {
        return healer;
    }

    public void setHealer(Rank healer) {
        this.healer = healer;
    }

    public Rank getDps() {
        return dps;
    }

    public void setDps(Rank dps) {
        this.dps = dps;
    }

    public Rank getWclass() {
        return classc;
    }

    public void setWclass(Rank wclass) {
        this.classc = wclass;
    }

    public Rank getClass_tank() {
        return class_tank;
    }

    public void setClass_tank(Rank class_tank) {
        this.class_tank = class_tank;
    }

    public Rank getClass_healer() {
        return class_healer;
    }

    public void setClass_healer(Rank class_healer) {
        this.class_healer = class_healer;
    }

    public Rank getClass_dps() {
        return class_dps;
    }

    public void setClass_dps(Rank class_dps) {
        this.class_dps = class_dps;
    }
}
