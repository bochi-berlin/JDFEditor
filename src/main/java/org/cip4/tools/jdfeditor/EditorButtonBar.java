package org.cip4.tools.jdfeditor;

/*
 *
 * The CIP4 Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2013 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
import org.cip4.jdflib.extensions.XJDF20;
import org.cip4.jdflib.jmf.JDFJMF;
import org.cip4.tools.jdfeditor.dialog.SaveAsJDFDialog;
import org.cip4.tools.jdfeditor.dialog.SaveAsXJDFDialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * 
 *  
 * @author rainer prosi
 * @date Apr 11, 2013
 */
public class EditorButtonBar extends JToolBar implements ActionListener
{

	JButton m_newButton;
	JButton m_openButton;
	JButton m_saveButton;
	JButton m_undoButton;
	JButton m_redoButton;
	JButton m_cutButton;
	JButton m_copyButton;
	JButton m_pasteButton;
    JButton m_convert2Jdf;
    JButton m_convert2XJdf;
	JButton m_validateButton;
	JButton m_upOneLevelButton;
	JButton m_NextButton;
	JButton m_LastButton;
	JButton m_printButton;
	JButton m_zoomInButton;
	JButton m_zoomOutButton;
	JButton m_zoomOrigButton;
	JButton m_zoomBestButton;
	JButton m_refreshButton;
	JButton m_closeButton;
	HashSet<JButton> m_allButtons;

	private final JDFFrame m_frame;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2161156231007579898L;

	/**
	 * 
	 *  
	 * @param frame
	 */
	public EditorButtonBar(JDFFrame frame)
	{
		super(SwingConstants.HORIZONTAL);
		m_frame = frame;
		m_allButtons = new HashSet<JButton>();
	}

	/**
	 * Method drawButtonBar.
	 * Editor.java contains ICONS_PATH. This package can be found under JDFEditor/src/Java/org.cip4.jdfeditor.icons
	 * You can add icons to this packagae by putting your .gif files into the following folder. This folder comes from whereever you
	 * downloaded yoru code from SubVersion. My place (BIskey) is the reference point. You can make it whatever you like. 
	 *  C:\Subversion_Root\My_Root\JDFEditor\src\java\org\cip4\jdfeditor\icons
	 *  After adding the icons to this folder, you need to come here and refresh the package w/in your Java editing software.
	 */
	public void drawButtonBar()
	{

		final ImageIcon imgNew = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/new.png");
		final ImageIcon imgOpen = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/open.png");
		final ImageIcon imgSave = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/save.png");

        final ImageIcon imgPrint = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/print.png");
        final ImageIcon imgRefresh = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/refresh.png");

		final ImageIcon imgCut = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/cut.png");
		final ImageIcon imgCopy = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/copy.png");
		final ImageIcon imgPaste = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/paste.png");

        final ImageIcon imgJDF = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/jdf.png");
        final ImageIcon imgXJDF = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/xjdf.png");

		final ImageIcon imgUndo = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/undo.png");
		final ImageIcon imgRedo = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/redo.png");

		final ImageIcon imgReval = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/validate.png");
		final ImageIcon imgUp = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/arrow_up.png");
		final ImageIcon imgLast = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/arrow_left.png");
		final ImageIcon imgNext = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/arrow_right.png");

		final ImageIcon imgZoomIn = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/zoom-in.png");
		final ImageIcon imgZoomOut = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/zoom-out.png");
		final ImageIcon imgZoomOrig = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/zoom-original.png");
		final ImageIcon imgZoomBest = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/zoom-fit-best.png");

        final ImageIcon imgClose = Editor.getImageIcon(getClass(), Editor.ICONS_PATH + "toolbar/close.png");


		final Dimension d = new Dimension(10, 30);
		setFloatable(false);

        // document block
		m_newButton = createDefaultButton(imgNew, Editor.getString("main.toolbar.document.new"), true, '|');
		m_openButton = createDefaultButton(imgOpen, Editor.getString("main.toolbar.document.open"), true, '|');
		m_saveButton = createDefaultButton(imgSave, Editor.getString("main.toolbar.document.save"), false, '|');
        m_closeButton = createDefaultButton(imgClose, Editor.getString("main.toolbar.document.close"), true, '|');
		addSeparator(d);

        // util block
		m_printButton = createDefaultButton(imgPrint, Editor.getString("main.toolbar.util.print"), false, '|');
		m_refreshButton = createDefaultButton(imgRefresh, Editor.getString("main.toolbar.util.refresh"), false, '|');
        m_validateButton = createDefaultButton(imgReval, Editor.getString("main.toolbar.util.validate"), false, 'A');
		addSeparator(d);

        // edit block
		m_cutButton = createDefaultButton(imgCut, Editor.getString("main.toolbar.edit.cut"), false, '|');
		m_copyButton = createDefaultButton(imgCopy, Editor.getString("main.toolbar.edit.copy"), false, '|');
		m_pasteButton = createDefaultButton(imgPaste, Editor.getString("main.toolbar.edit.paste"), false, '|');
		addSeparator(d);

        // convert block
        m_convert2Jdf = createDefaultButton(imgJDF, Editor.getString("main.toolbar.convert.jdf"), false, '|');
        m_convert2XJdf = createDefaultButton(imgXJDF, Editor.getString("main.toolbar.convert.xjdf"), false, '|');
        addSeparator(d);

        // history block
		m_undoButton = createDefaultButton(imgUndo, Editor.getString("main.toolbar.history.undo"), false, '|');
		m_undoButton.addActionListener(m_frame.undoAction);
		m_redoButton = createDefaultButton(imgRedo, Editor.getString("main.toolbar.history.redo"), false, '|');
		m_redoButton.addActionListener(m_frame.redoAction);
		addSeparator(d);

		// navigation block
		m_upOneLevelButton = createDefaultButton(imgUp, Editor.getString("main.toolbar.nav.up"), false, '|');
		m_LastButton = createDefaultButton(imgLast, Editor.getString("main.toolbar.nav.last"), false, '|');
		m_NextButton = createDefaultButton(imgNext, Editor.getString("main.toolbar.nav.next"), false, '|');
		addSeparator(d);

        // zoom block
		m_zoomInButton = createDefaultButton(imgZoomIn, Editor.getString("main.toolbar.zoom.in"), false, '|');
		m_zoomOutButton = createDefaultButton(imgZoomOut, Editor.getString("main.toolbar.zoom.out"), false, '|');
		m_zoomOrigButton = createDefaultButton(imgZoomOrig, Editor.getString("main.toolbar.zoom.orig"), false, '|');
		m_zoomBestButton = createDefaultButton(imgZoomBest, Editor.getString("main.toolbar.zoom.fit"), false, '|');
		addSeparator(d);

		add(Box.createHorizontalGlue());
		SwingUtilities.updateComponentTreeUI(this);
	}

	/**
	 * Creates a JButton with the default settings.
	 * @param iconStr - The Path to the image file
	 * @param tip     - The tool tip
	 * @param enabled - If the button is enabled or disabled initially
	 * @return The default JButton.
	 */
	private JButton createDefaultButton(ImageIcon icon, String tip, boolean enabled, char mnemonic)
	{
		final JButton button = new JButton(icon);

		if (Editor.getIniFile().getLookAndFeel().equalsIgnoreCase("com.sun.java.swing.plaf.motif.MotifLookAndFeel"))
			button.setPreferredSize(new Dimension(45, 45));
		else
			button.setPreferredSize(new Dimension(30, 30));

		button.addActionListener(this);
		button.addMouseListener(new Button_MouseListener());
		button.setBorderPainted(false);
		button.setEnabled(enabled);
		button.setToolTipText(tip);
		if (mnemonic != '|')
		{
			button.setMnemonic(mnemonic);
		}
		m_allButtons.add(button);
		add(button);
		return button;
	}

	/**
	 * enable or disable zoom buttons depending on the current zoom factor
	 * @param zoom the current zoom factor
	 */
	public void setEnableZoom(double zoom)
	{
		if (Editor.getEditorDoc() == null)
		{
			m_zoomOutButton.setEnabled(false);
			m_zoomInButton.setEnabled(false);
			m_zoomOrigButton.setEnabled(false);
			m_zoomBestButton.setEnabled(false);
			m_upOneLevelButton.setEnabled(false);

		}
		else
		{
			m_zoomOutButton.setEnabled(zoom > 0.2);
			m_zoomInButton.setEnabled(zoom < 2.5);
			m_zoomOrigButton.setEnabled(zoom != 1.0);
			m_zoomBestButton.setEnabled(true);
		}
	}

	/**
	 * 
	 */
	public void setEnableClose()
	{
		m_saveButton.setEnabled(false);
		m_cutButton.setEnabled(false);
		m_copyButton.setEnabled(false);
		m_pasteButton.setEnabled(false);
		m_undoButton.setEnabled(false);
		m_redoButton.setEnabled(false);
		m_upOneLevelButton.setEnabled(false);
        m_convert2Jdf.setEnabled(false);
        m_convert2XJdf.setEnabled(false);
		m_LastButton.setEnabled(false);
		m_NextButton.setEnabled(false);
		m_zoomInButton.setEnabled(false);
		m_zoomOutButton.setEnabled(false);
		m_zoomOrigButton.setEnabled(false);
		m_zoomBestButton.setEnabled(false);
	}

	public void setEnableOpen(boolean mode)
	{
		m_newButton.setEnabled(mode);
		m_closeButton.setEnabled(mode);
		m_saveButton.setEnabled(mode);
		m_cutButton.setEnabled(mode);
		m_copyButton.setEnabled(mode);

		m_redoButton.setEnabled(false);
		m_undoButton.setEnabled(false);
		m_LastButton.setEnabled(true);
		m_NextButton.setEnabled(true);

        m_convert2Jdf.setEnabled(true);
        m_convert2XJdf.setEnabled(true);

		m_validateButton.setEnabled(true);
		m_printButton.setEnabled(true);
		EditorDocument eDoc = Editor.getEditorDoc();
		m_refreshButton.setEnabled(eDoc == null ? true : eDoc.getMimePackage() == null);
	}

	///////////////////////////////////////////////////////////////
	/**
	 * Mother of all action dispatchers
	 * @param e the event that gets checked
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{

		final Object eSrc = e.getSource();
		Editor.setCursor(1, null);
		if (eSrc == m_newButton) // new document
		{
			m_frame.newFile();
		}
		else if (eSrc == m_openButton) // open document
		{
			m_frame.openFile();
		}
		else if (eSrc == m_saveButton) // save document
		{
			if (m_frame.getTitle().equalsIgnoreCase("Untitled.jdf") || m_frame.getTitle().equalsIgnoreCase("Untitled.jmf"))
			{
				m_frame.saveAs();
			}
			else
			{
				m_frame.getJDFDoc().write2File((String) null, 2, false);
				m_frame.getJDFDoc().clearDirtyIDs();
			}
		}
		else if (eSrc == m_validateButton && m_frame.getModel() != null) // validate
		{
			m_frame.getModel().validate();
		}
        else if (eSrc == m_convert2Jdf) // convert 2 JDF
        {
            SaveAsJDFDialog d = new SaveAsJDFDialog();
            if (d.isOK())
            {
                Editor.getModel().saveAsJDF(null, d.getConverter());
            }
        }
        else if (eSrc == m_convert2XJdf) // convert 2 XJDF
        {
            SaveAsXJDFDialog d = new SaveAsXJDFDialog();
            if (d.isOK())
            {
                Editor.getModel().saveAsXJDF(null, d.getXJDFConverter());
            }
        }
		else if (eSrc == m_upOneLevelButton) // navigate up
		{
			m_frame.m_topTabs.m_pArea.goUpOneLevelInProcessView();
		}

		else if (eSrc == m_NextButton) // navigate next
		{
			Editor.getEditorDoc().setNextSelection();
		}
		else if (eSrc == m_LastButton) // navigate last
		{
			Editor.getEditorDoc().setLastSelection();
		}
		else if (eSrc == m_printButton)  // print
		{
			m_frame.printWhat();
		}
		else if (eSrc == m_zoomInButton)  // zoom in
		{
			m_frame.m_topTabs.m_pArea.zoom('+');
		}
		else if (eSrc == m_zoomOutButton) // zoom out
		{
			m_frame.m_topTabs.m_pArea.zoom('-');
		}
		else if (eSrc == m_zoomOrigButton) // zoom to original size
		{
			m_frame.m_topTabs.m_pArea.zoom('o');
		}
		else if (eSrc == m_zoomBestButton) // zoom to best fit
		{
			m_frame.m_topTabs.m_pArea.zoom('b');
		}
		else if (eSrc == m_closeButton) // close document
		{
			m_frame.closeFile(1);
		}
		else if (!Editor.getIniFile().getReadOnly())
		{
			if (eSrc == m_cutButton) // cut
			{
				m_frame.cutSelectedNode();
			}
			else if (eSrc == m_copyButton) // copy
			{
				m_frame.copySelectedNode();
			}
			else if (eSrc == m_pasteButton) // paste
			{
				m_frame.pasteCopiedNode();
			}
			else if (eSrc == m_refreshButton) // refresh
			{
				m_frame.refresh();
			}
		}
		// always clean up!
		Editor.setCursor(0, null);
	}

	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////

	class Button_MouseListener extends MouseAdapter
	{
		@Override
		public void mouseEntered(MouseEvent e)
		{
			final Object source = e.getSource();
			if (m_allButtons.contains(source))
			{
				JButton b = (JButton) source;
				if (b.isEnabled())
					b.setBorderPainted(true);
			}
		}

		///////////////////////////////////////////////////////////////
		@Override
		public void mouseExited(MouseEvent e)
		{
			final Object source = e.getSource();
			if (m_allButtons.contains(source))
			{
				JButton b = (JButton) source;
				if (b.isEnabled())
					b.setBorderPainted(false);
			}
		}
	}
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////

}
