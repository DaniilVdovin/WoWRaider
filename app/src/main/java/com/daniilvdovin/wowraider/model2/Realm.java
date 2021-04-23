package com.daniilvdovin.wowraider.model2;

public class Realm {
    /*
            "id": 369,
            "connectedRealmId": 207,
            "name": "Illidan",
            "altName": null,
            "slug": "illidan",
            "altSlug": "illidan",
            "locale": "en_US",
            "isConnected": false
     */
    Integer id, connectedRealmId;
    String name,altName,slug,altSlug,locale;
    boolean isConnected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConnectedRealmId() {
        return connectedRealmId;
    }

    public void setConnectedRealmId(Integer connectedRealmId) {
        this.connectedRealmId = connectedRealmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAltSlug() {
        return altSlug;
    }

    public void setAltSlug(String altSlug) {
        this.altSlug = altSlug;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
