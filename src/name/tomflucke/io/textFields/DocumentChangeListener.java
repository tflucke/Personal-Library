package name.tomflucke.io.textFields;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class DocumentChangeListener implements DocumentListener,
        ChangeListener
{
	@Override
	public void changedUpdate(DocumentEvent de)
	{System.out.println("Hello World!");
		stateChanged(new ChangeEvent(de.getDocument()));
	}
	
	@Override
	public void insertUpdate(DocumentEvent de)
	{System.out.println("Hello World!");
		stateChanged(new ChangeEvent(de.getDocument()));
	}
	
	@Override
	public void removeUpdate(DocumentEvent de)
	{System.out.println("Hello World!");
		stateChanged(new ChangeEvent(de.getDocument()));
	}
	
}
