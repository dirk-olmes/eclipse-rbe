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
package com.essiembre.eclipse.rbe.ui;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.BundleContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * The main resource bundle editor plugin class to be used in the desktop.
 */
public class RBEPlugin extends AbstractUIPlugin {

    public static final String ID = "com.essiembre.eclipse.i18n.resourcebundle";
    
    //The shared instance.
	private static RBEPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public RBEPlugin() {
		super();
		plugin = this;
	}
    
	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
        try {
            URL messagesUrl = find(new Path("$nl$/messages.properties"));
            if(messagesUrl != null) {
                resourceBundle = new PropertyResourceBundle(
                        messagesUrl.openStream());
            }
        } catch (IOException x) {
            resourceBundle = null;
        }
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 */
	public static RBEPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getString(String key) {
		ResourceBundle bundle = 
                RBEPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	protected ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
    
    public static ImageDescriptor getImageDescriptor(String name) {
        String iconPath = "icons/";
        try {
            URL installURL = RBEPlugin.getDefault().getBundle().getEntry("/");
            URL url = new URL(installURL, iconPath + name);
            return ImageDescriptor.createFromURL(url);
        } catch (MalformedURLException e) {
            // should not happen
            return ImageDescriptor.getMissingImageDescriptor();
        }
    }
    
}