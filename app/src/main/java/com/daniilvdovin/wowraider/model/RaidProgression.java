package com.daniilvdovin.wowraider.model;

import java.util.Arrays;

public class RaidProgression {
    public Raid[] raids;

    @Override
    public String toString() {
        return "RaidProgression{" +
                "raids=" + Arrays.toString(raids) +
                '}';
    }
}
