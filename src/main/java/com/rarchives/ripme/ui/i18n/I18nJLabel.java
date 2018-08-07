package com.rarchives.ripme.ui.i18n;

import java.util.ResourceBundle;

import javax.swing.*;

/**
 * Extended JLabel that auto-changes its text value when a new ResourceBundle is loaded.
 */
public class I18nJLabel extends JLabel implements ResourceBundleChangeObserver {

    private final String resourceKey;

    public I18nJLabel(String resourceKey) {
        this(resourceKey, null, 10);
    }

    public I18nJLabel(String resourceKey, Icon icon, int i) {
        super("", icon, i);
        this.resourceKey = resourceKey;
        ResourceBundleManager.addObserver(this);
    }

    public I18nJLabel(String resourceKey, int i) {
        this(resourceKey, null, i);
    }

    @Override
    public void update(ResourceBundle bundle) {
        this.setText(bundle.getString(resourceKey));
    }
}
