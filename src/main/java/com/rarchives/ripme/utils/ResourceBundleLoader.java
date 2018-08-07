package com.rarchives.ripme.utils;

import static com.rarchives.ripme.utils.Utils.getConfigString;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class ResourceBundleLoader {

    private static ResourceBundle bundle;
    private static final Logger LOGGER = Logger.getLogger(ResourceBundleLoader.class);

    static {
        bundle = getResourceBundle(null);
    }

    /**
     * Loads {@link ResourceBundle} for i18n, should be used to change current application language.
     *
     * @param lang Lang code corresponding to wanted Bundle, ex: "fr_CH"
     * @return loaded {@link ResourceBundle}
     */
    public static ResourceBundle loadBundle(String lang) {
        return (bundle = getResourceBundle(lang));
    }

    /**
     * Get current active bundle.
     *
     * @return current {@link ResourceBundle} with localization strings.
     */
    public static ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * Gets Locale String of current bundle (eg. en_US)
     *
     * @return Locale string representation.
     */
    public static String getBundleLocaleString() {
        return bundle.getLocale().toString();
    }

    /**
     * Gets the ResourceBundle AKA language package.
     * Used for choosing the language of the UI.
     *
     * @return Returns the default resource bundle using the language specified in the config file.
     */
    private static ResourceBundle getResourceBundle(String langSelect) {
        if (langSelect == null) {
            if (!getConfigString("lang", "").equals("")) {
                String[] langCode = getConfigString("lang", "").split("_");
                LOGGER.info("Setting locale to " + getConfigString("lang", ""));
                return ResourceBundle.getBundle("LabelsBundle", new Locale(langCode[0], langCode[1]), new UTF8Control());
            }
        } else {
            String[] langCode = langSelect.split("_");
            LOGGER.info("Setting locale to " + langSelect);
            return ResourceBundle.getBundle("LabelsBundle", new Locale(langCode[0], langCode[1]), new UTF8Control());
        }
        try {
            LOGGER.info("Setting locale to default");
            return ResourceBundle.getBundle("LabelsBundle", Locale.getDefault(), new UTF8Control());
        } catch (MissingResourceException e) {
            LOGGER.info("Setting locale to root");
            return ResourceBundle.getBundle("LabelsBundle", Locale.ROOT);
        }
    }
}
