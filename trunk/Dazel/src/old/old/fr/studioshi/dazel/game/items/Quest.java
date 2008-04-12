/*
 * Created on 19 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.items;

import java.util.LinkedList;
import java.util.ListIterator;


/**
 * @author Endy
 */
public class Quest
{
	public Quest(String s)
	{
		nb++;
		if (nb == 1)
		{
			maps_ = new LinkedList();
			name_ = s;
		}
	}
	
	public Quest()
	{
		nb++;
		if (nb == 1)
			maps_ = new LinkedList();
	}
	
	public LinkedList getMaps()
	{
		return maps_;
	}
	
	public Map getMap(String s)
	{
		ListIterator it = maps_.listIterator();

        while (it.hasNext()) {
            Map tmp = (Map) it.next();
            if (s.equals(tmp.getName()))
                return tmp;
        }
        return null;
	}
	
	public String [] getMapNames()
	{
		ListIterator it = maps_.listIterator();
		String s [] = new String [maps_.size()];
		int i = 0;
		
		while (it.hasNext()) {
            Map tmp = (Map) it.next();
            s[i++] = tmp.getName();
        }
        return s;
	}
	
	public void setMap(Map m)
	{
		maps_.add(m);
	}
	
	public String getName()
	{
		return name_;
	}
	
	public void setName(String n)
	{
		name_ = n;
	}
	
	protected
		int 		nb = 0;
		LinkedList	maps_;
		String		name_;
}
