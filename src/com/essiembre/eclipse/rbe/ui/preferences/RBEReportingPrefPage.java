/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.ui.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.essiembre.eclipse.rbe.ui.RBEPlugin;

/**
 * Plugin preference page for reporting/performance options.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author$ $Revision$ $Date$
 */
public class RBEReportingPrefPage extends AbstractRBEPrefPage {
    
    /* Preference fields. */
    private Button reportMissingVals;
    private Button reportDuplVals;
    private Button reportSimVals;
    private Button[] reportSimValsMode = new Button[2];

    /**
     * Constructor.
     */
    public RBEReportingPrefPage() {
        super();
    }

    /**
     * @see org.eclipse.jface.preference.PreferencePage#createContents(
     *         org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        IPreferenceStore prefs = getPreferenceStore();
        Composite field = null;
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(1, false));
        
        new Label(composite, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.intro1"));
        new Label(composite, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.intro2"));
        new Label(composite, SWT.NONE).setText(" ");
        
        // Report missing values?
        field = createFieldComposite(composite);
        reportMissingVals = new Button(field, SWT.CHECK);
        reportMissingVals.setSelection(
                prefs.getBoolean(RBEPreferences.REPORT_MISSING_VALUES));
        new Label(field, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.missingVals"));

        // Report duplicate values?
        field = createFieldComposite(composite);
        reportDuplVals = new Button(field, SWT.CHECK);
        reportDuplVals.setSelection(
                prefs.getBoolean(RBEPreferences.REPORT_DUPL_VALUES));
        new Label(field, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.duplVals"));
        
        // Report similar values?
        field = createFieldComposite(composite);
        reportSimVals = new Button(field, SWT.CHECK);
        reportSimVals.setSelection(
                prefs.getBoolean(RBEPreferences.REPORT_SIM_VALUES));
        new Label(field, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.simVals"));
        reportSimVals.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                refreshEnabledStatuses();
            }
        });
        
        Composite simValModeGroup = new Composite(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.marginWidth = indentPixels;
        gridLayout.marginHeight = 0;
        gridLayout.verticalSpacing = 0;
        simValModeGroup.setLayout(gridLayout);
        
        // Report similar values: word count
        reportSimValsMode[0] = new Button(simValModeGroup, SWT.RADIO);
        reportSimValsMode[0].setSelection(prefs.getBoolean(
                RBEPreferences.REPORT_SIM_VALUES_WORD_COMPARE));
        new Label(simValModeGroup, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.simVals.wordCount"));
        
        // Report similar values: Levensthein
        reportSimValsMode[1] = new Button(simValModeGroup, SWT.RADIO);
        reportSimValsMode[1].setSelection(prefs.getBoolean(
                RBEPreferences.REPORT_SIM_VALUES_LEVENSTHEIN));
        new Label(simValModeGroup, SWT.NONE).setText(
                RBEPlugin.getString("prefs.perform.simVals.levensthein"));
        
//        Composite newLineRadioGroup = new Composite(field, SWT.NONE); 
//        new Label(newLineRadioGroup, SWT.NONE).setText(
//                RBEPlugin.getString(
//                        "prefs.newline.force"));
//        newLineRadioGroup.setLayout(new RowLayout());
//        newLineTypes[RBEPreferences.NEW_LINE_UNIX] = 
//                new Button(newLineRadioGroup, SWT.RADIO);
//        newLineTypes[RBEPreferences.NEW_LINE_UNIX].setText("UNIX (\\n)");
//        newLineTypes[RBEPreferences.NEW_LINE_WIN] = 
//                new Button(newLineRadioGroup, SWT.RADIO);
//        newLineTypes[RBEPreferences.NEW_LINE_WIN].setText("Windows (\\r\\n)");
//        newLineTypes[RBEPreferences.NEW_LINE_MAC] =
//                new Button(newLineRadioGroup, SWT.RADIO);
//        newLineTypes[RBEPreferences.NEW_LINE_MAC].setText("Mac (\\r)");
//        newLineTypes[prefs.getInt(
//                RBEPreferences.NEW_LINE_TYPE)].setSelection(true);
        
        refreshEnabledStatuses();
        
        return composite;
    }


    /**
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk() {
        IPreferenceStore prefs = getPreferenceStore();
        prefs.setValue(RBEPreferences.REPORT_MISSING_VALUES,
                reportMissingVals.getSelection());
        prefs.setValue(RBEPreferences.REPORT_DUPL_VALUES,
                reportDuplVals.getSelection());
        prefs.setValue(RBEPreferences.REPORT_SIM_VALUES,
                reportSimVals.getSelection());
        prefs.setValue(RBEPreferences.REPORT_SIM_VALUES_WORD_COMPARE,
                reportSimValsMode[0].getSelection());
        prefs.setValue(RBEPreferences.REPORT_SIM_VALUES_LEVENSTHEIN,
                reportSimValsMode[1].getSelection());
        refreshEnabledStatuses();
        return super.performOk();
    }
    
    
    /**
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults() {
        IPreferenceStore prefs = getPreferenceStore();
        reportMissingVals.setSelection(prefs.getDefaultBoolean(
                RBEPreferences.REPORT_MISSING_VALUES));
        reportDuplVals.setSelection(prefs.getDefaultBoolean(
                RBEPreferences.REPORT_DUPL_VALUES));
        reportSimVals.setSelection(prefs.getDefaultBoolean(
                RBEPreferences.REPORT_SIM_VALUES));
        reportSimValsMode[0].setSelection(prefs.getDefaultBoolean(
                RBEPreferences.REPORT_SIM_VALUES_WORD_COMPARE));
        reportSimValsMode[1].setSelection(prefs.getDefaultBoolean(
                RBEPreferences.REPORT_SIM_VALUES_LEVENSTHEIN));
        refreshEnabledStatuses();
        super.performDefaults();
    }

    private void refreshEnabledStatuses() {
        boolean isReportingSimilar = reportSimVals.getSelection();

        for (int i = 0; i < reportSimValsMode.length; i++) {
            reportSimValsMode[i].setEnabled(isReportingSimilar);
        }
    }
    
}
