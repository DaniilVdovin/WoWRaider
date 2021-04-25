package com.daniilvdovin.wowraider.Token;

import androidx.annotation.NonNull;

public class Token {
    public TokenByRegion
            us,
            eu,
            china,
            korea,
            taiwan;
    public TokenByRegion getToken(@NonNull String region){
        switch (region){
            case "us": return us;
            case "eu": return eu;
            case "china": return china;
            case "korea": return korea;
            case "taiwan": return taiwan;
        }
        return null;
    }
}
