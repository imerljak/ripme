package com.rarchives.ripme.ui.i18n;

import java.util.ResourceBundle;

import javax.swing.*;

/**
 * Extended JCheckBox that auto-changes its text value when a new ResourceBundle is loaded.
 */
public class I18nJCheckBox extends JCheckBox implements ResourceBundleChangeObserver {

    private final String resourceKey;

    public I18nJCheckBox(String resourceKey, boolean b) {
        super("", b);
        this.resourceKey = resourceKey;
        ResourceBundleManager.addObserver(this);
    }

    @Override
    public void update(ResourceBundle bundle) {
        this.setText(bundle.getString(resourceKey));
    }

}
