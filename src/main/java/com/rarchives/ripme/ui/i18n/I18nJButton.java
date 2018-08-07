package com.rarchives.ripme.ui.i18n;

import java.util.ResourceBundle;

import javax.swing.*;

/**
 * Extended JButton that auto-changes its text value when a new ResourceBundle is loaded.
 */
public class I18nJButton extends JButton implements ResourceBundleChangeObserver {

    private final String resourceKey;

    public I18nJButton() {
        this(null, null);
    }

    public I18nJButton(Icon icon) {
        this(null, icon);
    }

    public I18nJButton(String s) {
        this(s, null);
    }

    public I18nJButton(Action action) {
        this();
        this.setAction(action);
    }

    public I18nJButton(String s, Icon icon) {
        this.setModel(new DefaultButtonModel());
        this.init(s, icon);

        resourceKey = s;
        ResourceBundleManager.addObserver(this);
    }

    @Override
    public void update(ResourceBundle bundle) {
        if (resourceKey != null) {
            this.setText(bundle.getString(resourceKey));
        }
    }
}
