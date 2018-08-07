package com.rarchives.ripme.ui.i18n;

import static com.rarchives.ripme.utils.Utils.getConfigString;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.rarchives.ripme.utils.UTF8Control;

public class ResourceBundleManager {

    private static ResourceBundle bundle;
    private static final Logger LOGGER = Logger.getLogger(ResourceBundleManager.class);
    private static List<ResourceBundleChangeObserver> observers = new ArrayList<>();

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
        bundle = getResourceBundle(lang);
        notifyResourceBundleChange();
        return bundle;
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

    public static void addObserver(ResourceBundleChangeObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public static void removeObserver(ResourceBundleChangeObserver observer) {
        observers.remove(observer);
    }

    public static void notifyResourceBundleChange() {
        observers.forEach(o -> o.update(getBundle()));
    }
}
