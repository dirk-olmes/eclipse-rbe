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
package com.essiembre.eclipse.rbe.ui.editor.i18n.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.essiembre.eclipse.rbe.model.tree.KeyTreeItem;
import com.essiembre.eclipse.rbe.model.tree.visitors.IsCommentedVisitor;
import com.essiembre.eclipse.rbe.ui.RBEPlugin;
import com.essiembre.eclipse.rbe.ui.UIUtils;
import com.essiembre.eclipse.rbe.ui.editor.i18n.tree.decorators.MissingValueDecorator;

/**
 * Label provider for key tree viewer.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author$ $Revision$ $Date$
 */
public class KeyTreeLabelProvider 
        extends LabelProvider implements IFontProvider, IColorProvider {	
    

    MissingValueDecorator missingValueDecorator = new MissingValueDecorator();
    
    
    
    /** Cache for all images used by this provider. */
    private Map imageCache = new HashMap(11);

    //TODO have dynamic caching of Color and Font (like images).
    private Color commentedColor = RBEPlugin.getDefault().getWorkbench()
            .getDisplay().getSystemColor(SWT.COLOR_GRAY);

    /** Group font. */
    private Font groupFontKey = UIUtils.createFont(SWT.BOLD);
    private Font groupFontNoKey = UIUtils.createFont(SWT.BOLD | SWT.ITALIC);

    
	/**
	 * @see ILabelProvider#getImage(Object)
	 */
	public Image getImage(Object element) {
        KeyTreeItem treeItem = ((KeyTreeItem) element);
        ImageDescriptor descriptor = null;
        
        // Base image
        if (treeItem.getKeyTree().getBundleGroup().isKey(treeItem.getId())) {
            IsCommentedVisitor commentedVisitor = new IsCommentedVisitor();
            treeItem.accept(commentedVisitor, null);
            if (commentedVisitor.hasOneCommented()) {
                descriptor = RBEPlugin.getImageDescriptor("keyCommented.gif");
            } else {
                descriptor = RBEPlugin.getImageDescriptor("key.gif");
            }
        } else {
            //TODO use none, or different key?
            descriptor = RBEPlugin.getImageDescriptor("key.gif");
        }
        Image image = UIUtils.getCacheImage(imageCache, descriptor);
        
        // Decorators
        image = missingValueDecorator.decorateImage(image, treeItem);
        
        
        
		return image;
	}

	/**
	 * @see ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
        return ((KeyTreeItem) element).getName(); 
	}

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
	public void dispose() {
		for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
			((Image) i.next()).dispose();
		}
		imageCache.clear();
        groupFontKey.dispose();
        groupFontNoKey.dispose();
	}

    /**
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    public Font getFont(Object element) {
        KeyTreeItem item = (KeyTreeItem) element; 
        if (item.getChildren().size() > 0) {
            if (item.getKeyTree().getBundleGroup().isKey(item.getId())) {
                return groupFontKey;
            } else {
                return groupFontNoKey;
            }
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(Object element) {
        KeyTreeItem treeItem = (KeyTreeItem) element; 
        IsCommentedVisitor commentedVisitor = new IsCommentedVisitor();
        treeItem.accept(commentedVisitor, null);
        if (commentedVisitor.hasOneCommented()) {
            return commentedColor;
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(Object element) {
        // TODO Auto-generated method stub
        return null;
    }
}
