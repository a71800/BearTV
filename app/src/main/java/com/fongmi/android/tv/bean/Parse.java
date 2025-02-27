package com.fongmi.android.tv.bean;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parse {

    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private Integer type;
    @SerializedName("url")
    private String url;
    @SerializedName("ext")
    private Ext ext;

    public static Parse objectFrom(JsonElement element) {
        return new Gson().fromJson(element, Parse.class);
    }

    public static Parse get(String name) {
        Parse parse = new Parse();
        parse.setName(name);
        return parse;
    }

    public static Parse get(Integer type, String url) {
        Parse parse = new Parse();
        parse.setType(type);
        parse.setUrl(url);
        return parse;
    }

    public String getName() {
        return TextUtils.isEmpty(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type == null ? 0 : type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return TextUtils.isEmpty(url) ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Ext getExt() {
        return ext;
    }

    public boolean hasHeader() {
        return getExt() != null && getExt().getHeader() != null;
    }

    public JsonElement getHeader() {
        return getExt().getHeader();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Parse)) return false;
        Parse it = (Parse) obj;
        return getName().equals(it.getName());
    }

    public static class Ext {

        @SerializedName("flag")
        private List<String> flag;
        @SerializedName("header")
        private JsonElement header;

        public List<String> getFlag() {
            return flag;
        }

        public JsonElement getHeader() {
            return header;
        }
    }
}
