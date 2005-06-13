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

import org.eclipse.core.runtime.Preferences;

import com.essiembre.eclipse.rbe.ui.RBEPlugin;

/**
 * Application preferences, relevant to the resource bundle editor plugin.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author$ $Revision$ $Date$
 */
public final class RBEPreferences {
   
    //TODO move to model layer
    
    /** Key group separator. */
    public static final String KEY_GROUP_SEPARATOR = "keyGroupSeparator";

    /** Should key tree be hiearchical. */
    public static final String KEY_TREE_HIERARCHICAL = "keyTreeHierarchical";
    
    /** Should "Generated by" line be added to files. */
    public static final String SHOW_GENERATOR = "showGenerator";
    /** Should Eclipse "nl" directory structure be supported. */
    public static final String SUPPORT_NL = "supportNL";
    
    /** Should equal signs be aligned. */
    public static final String ALIGN_EQUAL_SIGNS = "alignEqualSigns";
    /** Should spaces be put around equal signs. */
    public static final String SPACES_AROUND_EQUAL_SIGNS = 
            "spacesAroundEqualSigns";

    /** Should keys be grouped. */
    public static final String GROUP_KEYS = "groupKeys";
    /** How many level deep should keys be grouped. */
    public static final String GROUP_LEVEL_DEEP = "groupLevelDeep";
    /** How many line breaks between key groups. */
    public static final String GROUP_LINE_BREAKS = "groupLineBreaks";
    /** Should equal signs be aligned within groups. */
    public static final String GROUP_ALIGN_EQUAL_SIGNS = 
            "groupAlignEqualSigns";

    /** Should lines be wrapped. */
    public static final String WRAP_LINES = "wrapLines";
    /** Maximum number of character after which we should wrap. */
    public static final String WRAP_CHAR_LIMIT = "wrapCharLimit";
    /** Align subsequent lines with equal signs. */
    public static final String WRAP_ALIGN_EQUAL_SIGNS = "wrapAlignEqualSigns";
    /** Number of spaces to indent subsequent lines. */
    public static final String WRAP_INDENT_SPACES = "wrapIndentSpaces";
    
    /** Should unicode values be converted to their encoded equivalent. */
    public static final String CONVERT_UNICODE_TO_ENCODED = 
            "convertUnicodeToEncoded";
    /** Should unicode values be converted to their encoded equivalent. */
    public static final String CONVERT_UNICODE_TO_ENCODED_UPPER = 
            "convertUnicodeToEncodedUppercase";
    /** Should encoded values be converted to their unicode equivalent. */
    public static final String CONVERT_ENCODED_TO_UNICODE = 
            "convertEncodedToUnicode";
    
    /** Impose a given new line type. */
    public static final String FORCE_NEW_LINE_TYPE = "forceNewLineType";
    /** How new lines are represented in resource bundle. */
    public static final String NEW_LINE_TYPE = "newLineType";
    /** Should new lines character produce a line break in properties files. */
    public static final String NEW_LINE_NICE = "newLineNice";
    
    /** New Line Type: UNIX. */
    public static final int NEW_LINE_UNIX = 0;
    /** New Line Type: Windows. */
    public static final int NEW_LINE_WIN = 1;
    /** New Line Type: Mac. */
    public static final int NEW_LINE_MAC = 2;
    
    /** RBEPreferences. */
    private static final Preferences PREFS = 
            RBEPlugin.getDefault().getPluginPreferences();
    
    /**
     * Constructor.
     */
    private RBEPreferences() {
        super();
    }

    /**
     * Gets key group separator.
     * @return key group separator.
     */
    public static String getKeyGroupSeparator() {
        return PREFS.getString(KEY_GROUP_SEPARATOR);
    }

    public static boolean getAlignEqualSigns() {
        return PREFS.getBoolean(ALIGN_EQUAL_SIGNS);
    }

    public static boolean getSpacesAroundEqualSigns() {
        return PREFS.getBoolean(SPACES_AROUND_EQUAL_SIGNS);
    }

    public static boolean getGroupKeys() {
        return PREFS.getBoolean(GROUP_KEYS);
    }

    public static int getGroupLevelDeepness() {
        return PREFS.getInt(GROUP_LEVEL_DEEP);
    }
    
    public static int getGroupLineBreaks() {
        return PREFS.getInt(GROUP_LINE_BREAKS);
    }

    public static boolean getGroupAlignEqualSigns() {
        return PREFS.getBoolean(GROUP_ALIGN_EQUAL_SIGNS);
    }

    public static boolean getKeyTreeHierarchical() {
        return PREFS.getBoolean(KEY_TREE_HIERARCHICAL);
    }

    public static boolean getShowGenerator() {
        return PREFS.getBoolean(SHOW_GENERATOR);
    }

    public static boolean getSupportNL() {
        return PREFS.getBoolean(SUPPORT_NL);
    }
    
    public static boolean getWrapLines() {
        return PREFS.getBoolean(WRAP_LINES);
    }
    
    public static int getWrapCharLimit() {
        return PREFS.getInt(WRAP_CHAR_LIMIT);
    }

    public static boolean getWrapAlignEqualSigns() {
        return PREFS.getBoolean(WRAP_ALIGN_EQUAL_SIGNS);
    }

    public static int getWrapIndentSpaces() {
        return PREFS.getInt(WRAP_INDENT_SPACES);
    }

    public static boolean getConvertEncodedToUnicode() {
        return PREFS.getBoolean(CONVERT_ENCODED_TO_UNICODE);
    }

    public static boolean getConvertUnicodeToEncoded() {
        return PREFS.getBoolean(CONVERT_UNICODE_TO_ENCODED);
    }
    public static boolean getConvertUnicodeToEncodedUpper() {
        return PREFS.getBoolean(CONVERT_UNICODE_TO_ENCODED_UPPER);
    }

    public static boolean getForceNewLineType() {
        return PREFS.getBoolean(FORCE_NEW_LINE_TYPE);
    }

    public static int getNewLineType() {
        return PREFS.getInt(NEW_LINE_TYPE);
    }
    
    public static boolean getNewLineNice() {
        return PREFS.getBoolean(NEW_LINE_NICE);
    }

}
