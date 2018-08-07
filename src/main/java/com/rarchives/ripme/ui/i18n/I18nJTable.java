package com.rarchives.ripme.ui.i18n;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Extended JTable that auto-changes its header values when a new ResourceBundle is loaded.
 */
public class I18nJTable extends JTable implements ResourceBundleChangeObserver {

    public I18nJTable() {
    }

    public I18nJTable(TableModel tableModel) {
        this(tableModel, (TableColumnModel)null, (ListSelectionModel)null);
    }

    public I18nJTable(TableModel tableModel, TableColumnModel tableColumnModel) {
        this(tableModel, tableColumnModel, (ListSelectionModel)null);
    }

    public I18nJTable(int i, int i1) {
        this(new DefaultTableModel(i, i1));
    }

    public I18nJTable(Vector vector, Vector vector1) {
        this(new DefaultTableModel(vector, vector1));
    }

    public I18nJTable(Object[][] objects, Object[] objects1) {
        super(objects, objects1);
    }

    public I18nJTable(TableModel tableModel, TableColumnModel tableColumnModel, ListSelectionModel listSelectionModel) {
        super(tableModel, tableColumnModel, listSelectionModel);

        ResourceBundleManager.addObserver(this);
    }

    @Override
    public void update(ResourceBundle bundle) {
        for (int i =0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setHeaderValue(getModel().getColumnName(i));
        }

        getTableHeader().repaint();
    }
}
