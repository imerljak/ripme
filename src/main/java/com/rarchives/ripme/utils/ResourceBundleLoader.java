package com.rarchives.ripme.utils;

import java.util.ResourceBundle;

public class ResourceBundleLoader {

    private static ResourceBundle bundle = Utils.getResourceBundle(null);

    public static ResourceBundle loadBundle(String lang) {
        return (bundle = Utils.getResourceBundle(lang));
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static String getBundleLocaleString() {
        return bundle.getLocale().toString();
    }
}
