package name.tomflucke.components;

import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/* From http://java.sun.com/docs/books/tutorial/index.html */

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

public class AutoCompleteComboBox extends JComboBox<String>
{
	private static final long serialVersionUID = -3088949476698182393L;

	private class AutoTextFieldEditor extends BasicComboBoxEditor
	{
		private AutoCompleteTextField getAutoTextFieldEditor()
		{
			return (AutoCompleteTextField) editor;
		}

		AutoTextFieldEditor(List<String> list)
		{
			editor = new AutoCompleteTextField(list, AutoCompleteComboBox.this);
		}
	}

	public AutoCompleteComboBox(List<String> list)
	{
		isFired = false;
		autoTextFieldEditor = new AutoTextFieldEditor(list);
		setEditable(true);
		setModel(new DefaultComboBoxModel<String>(list.toArray(new String[0]))
		{
			private static final long serialVersionUID = 2268177209092062174L;

			protected void fireContentsChanged(Object obj, int i, int j)
			{
				if (!isFired)
					super.fireContentsChanged(obj, i, j);
			}

		});
		setEditor(autoTextFieldEditor);
	}

	public boolean isCaseSensitive()
	{
		return autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
	}

	public void setCaseSensitive(boolean flag)
	{
		autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
	}

	public boolean isStrict()
	{
		return autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
	}

	public void setStrict(boolean flag)
	{
		autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
	}

	public List<String> getDataList()
	{
		return autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
	}

	public void setDataList(List<String> list)
	{
		autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
		setModel(new DefaultComboBoxModel<String>(list.toArray(new String[0])));
	}

	void setSelectedValue(Object obj)
	{
		if (isFired)
		{
			return;
		}
		else
		{
			isFired = true;
			setSelectedItem(obj);
			fireItemStateChanged(new ItemEvent(this, 701, selectedItemReminder, 1));
			isFired = false;
			return;
		}
	}

	protected void fireActionEvent()
	{
		if (!isFired)
			super.fireActionEvent();
	}

	private AutoTextFieldEditor autoTextFieldEditor;

	private boolean isFired;
}