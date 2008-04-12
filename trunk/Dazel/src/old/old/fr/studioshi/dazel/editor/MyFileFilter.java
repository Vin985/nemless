/*
 * Created on 18 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.editor;

import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * @author Endy
 */
public class MyFileFilter extends FileFilter
{
	public MyFileFilter()
	{
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept (File f)
	{
		if (f.isDirectory())
	         return true;
		String suffixe = null;
		String s = f.getName().toLowerCase();
		if (s.endsWith(suffixe_))
		    return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription ()
	{
		return null;
	}
	
	protected
		String suffixe_ = "xml";
}
