package com.rarchives.ripme.utils;

import java.util.ResourceBundle;

/**
 * Holds a static reference to a ResourceBundle.
 * Enables use user selected language for application wide translation.
 */
public class ResourceBundleLoader {

    private static ResourceBundle resourceBundle = Utils.getResourceBundle(null);

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static ResourceBundle loadBundle(String lang) {
        return (resourceBundle = Utils.getResourceBundle(lang));
    }
}
