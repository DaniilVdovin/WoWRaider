package com.daniilvdovin.wowraider.model2;

public class Region {
    /*
     "name": "United States & Oceania",
          "slug": "us",
          "short_name": "US"
     */
    String name,slug,short_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }
}
