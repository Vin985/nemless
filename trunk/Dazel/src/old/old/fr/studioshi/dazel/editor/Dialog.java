/*
 * Created on 4 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import old.fr.studioshi.dazel.game.items.Map;
import old.fr.studioshi.dazel.game.items.Minimap;
import old.fr.studioshi.dazel.game.items.Quest;


/**
 * @author Endy
 */
public class Dialog extends JDialog implements ActionListener
{
	public Dialog(Quest q, Fenetre frame)
	{
		dim_ = Toolkit.getDefaultToolkit().getScreenSize();		
		
		dial_ = new JDialog(frame);
		quest_ = q;
		frame_ = frame;
		ok_ = new JButton("Ok");
		cancel_ = new JButton("Cancel");
		ok_.addActionListener(this);
		cancel_.addActionListener(this);
	}
	
	public void add_minimap(Map m, Minimap mini)
	{
		which_ = "add_minimap";
		JLabel lpresent = new JLabel("Minimap added to " + m.getName() + " at " + mini.getCoord().get_x() + ", " + mini.getCoord().get_y());
		
		dial_.setSize(new Dimension(250, 160));
		dial_.setTitle("Add minimap to " + m.getName());
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(200, 50));
		dial_.getContentPane().add(lpresent);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void add_map(Quest q, Map m)
	{
		which_ = "add_map";
		JLabel lpresent = new JLabel("Map " + m.getName() + " added to " + q.getName());
		
		dial_.setSize(new Dimension(250, 160));
		dial_.setTitle("Add map to " + q.getName());
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(200, 50));
		dial_.getContentPane().add(lpresent);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}

	public void new_map()
	{
		which_ = "new_map";
		name_ = new JTextField(6);
		JLabel lname = new JLabel("Name :");
		l_ = new JTextField(4);
		JLabel ll = new JLabel("Width :");
		h_ = new JTextField(4);
		JLabel lh = new JLabel("Heigth :");
		x_ = new JTextField(4);
		JLabel lx = new JLabel("Start X :");
		y_ = new JTextField(4);
		JLabel ly = new JLabel("Start Y :");
		JLabel lpresent = new JLabel("Map features :");
		JLabel lend = new JLabel(".xml");

		dial_.setSize(new Dimension(200, 300));
		dial_.setTitle("New map in " + quest_.getName());
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(160, 50));
		dial_.getContentPane().add(lpresent);
		
		lname.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(lname);
		name_.setPreferredSize(new Dimension(80, 25));
		dial_.getContentPane().add(name_);
		lend.setPreferredSize(new Dimension(25, 25));
		dial_.getContentPane().add(lend);
		
		ll.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(ll);
		l_.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(l_);
		
		lh.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(lh);
		h_.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(h_);
		
		lx.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(lx);
		x_.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(x_);
		
		ly.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(ly);
		y_.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(y_);
		
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		cancel_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(cancel_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void new_minimap(Map m)
	{
		which_ = "new_minimap";
		String s [] = {"true", "false"};
		dial_.setSize(new Dimension(200, 250));
		dial_.setTitle("New Minimap");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		light_ = new JComboBox(s);
		
		x_ = new JTextField(4);
		JLabel lx = new JLabel("X :");
		y_ = new JTextField(4);
		JLabel ly = new JLabel("Y :");
		JLabel lpresent = new JLabel("Minimap features :");
		JLabel llight = new JLabel("Light :");
		
		dial_.setSize(new Dimension(200, 300));
		dial_.setTitle("New minimap in " + m.getName());
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(160, 50));
		dial_.getContentPane().add(lpresent);
		
		lx.setPreferredSize(new Dimension(90, 40));
		dial_.getContentPane().add(lx);
		x_.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(x_);
		
		ly.setPreferredSize(new Dimension(90, 40));
		dial_.getContentPane().add(ly);
		y_.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(y_);

		llight.setPreferredSize(new Dimension(90, 40));
		dial_.getContentPane().add(llight);
		light_.setPreferredSize(new Dimension(60, 25));
		dial_.getContentPane().add(light_);
		
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		cancel_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(cancel_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
		frame_.getContentPane().add(dial_);
	}

	public void delete_map(Map m)
	{
		which_ = "delete_map";
		JLabel lpresent = new JLabel("Are you sure you want to delete " + m.getName());
		
		dial_.setSize(new Dimension(300, 160));
		dial_.setTitle("Delete map");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(250, 50));
		dial_.getContentPane().add(lpresent);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		cancel_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(cancel_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void delete_minimap(Minimap mini)
	{
		which_ = "delete_minimap";
		JLabel lpresent = new JLabel("Are you sure you want to delete the minimap at " + mini.getCoord().get_x() + ", " + mini.getCoord().get_y());
		
		dial_.setSize(new Dimension(350, 160));
		dial_.setTitle("Delete minimap");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(300, 50));
		dial_.getContentPane().add(lpresent);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		cancel_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(cancel_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void map_properties(Map m)
	{
		which_ = "map_properties";
		name_ = new JTextField(6);
		JLabel lname = new JLabel("Name :");
		l_ = new JTextField(4);
		JLabel ll = new JLabel("Width :");
		h_ = new JTextField(4);
		JLabel lh = new JLabel("Heigth :");
		x_ = new JTextField(4);
		JLabel lx = new JLabel("Start X :");
		y_ = new JTextField(4);
		JLabel ly = new JLabel("Start Y :");
		JLabel lpresent = new JLabel("Map features :");
		JLabel lend = new JLabel(".xml");

		dial_.setSize(new Dimension(200, 300));
		dial_.setTitle("Map properties in " + quest_.getName());
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(160, 50));
		dial_.getContentPane().add(lpresent);
		
		lname.setPreferredSize(new Dimension(40, 25));
		dial_.getContentPane().add(lname);
		name_.setText(m.getName());
		name_.setPreferredSize(new Dimension(80, 25));
		dial_.getContentPane().add(name_);
		lend.setPreferredSize(new Dimension(25, 25));
		dial_.getContentPane().add(lend);
		
		ll.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(ll);
		l_.setPreferredSize(new Dimension(40, 25));
		l_.setText(String.valueOf(m.getL()));
		dial_.getContentPane().add(l_);
		
		lh.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(lh);
		h_.setPreferredSize(new Dimension(40, 25));
		h_.setText(String.valueOf(m.getH()));
		dial_.getContentPane().add(h_);
		
		lx.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(lx);
		x_.setPreferredSize(new Dimension(40, 25));
		x_.setText(String.valueOf(m.getStartx()));
		dial_.getContentPane().add(x_);
		
		ly.setPreferredSize(new Dimension(90, 25));
		dial_.getContentPane().add(ly);
		y_.setPreferredSize(new Dimension(40, 25));
		y_.setText(String.valueOf(m.getStarty()));
		dial_.getContentPane().add(y_);
		
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		cancel_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(cancel_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void minimap_properties(Minimap mini, Map m)
	{		
		which_ = "minimap_properties";
		String s [] = {"true", "false"};
		dial_.setSize(new Dimension(200, 250));
		dial_.setTitle("New Minimap");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		light_ = new JComboBox(s);
		
		x_ = new JTextField(4);
		JLabel lx = new JLabel("X :");
		y_ = new JTextField(4);
		JLabel ly = new JLabel("Y :");
		JLabel lpresent = new JLabel("Minimap features :");
		JLabel llight = new JLabel("Light :");
		
		dial_.setSize(new Dimension(200, 300));
		dial_.setTitle("Minimap properties in " + m.getName());
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(160, 50));
		dial_.getContentPane().add(lpresent);
		
		lx.setPreferredSize(new Dimension(90, 40));
		dial_.getContentPane().add(lx);
		x_.setPreferredSize(new Dimension(40, 25));
		x_.setText(String.valueOf(mini.getCoord().get_x()));
		dial_.getContentPane().add(x_);
		
		ly.setPreferredSize(new Dimension(90, 40));
		dial_.getContentPane().add(ly);
		y_.setPreferredSize(new Dimension(40, 25));
		x_.setText(String.valueOf(mini.getCoord().get_y()));
		dial_.getContentPane().add(y_);

		llight.setPreferredSize(new Dimension(90, 40));
		dial_.getContentPane().add(llight);
		light_.setPreferredSize(new Dimension(60, 25));
		light_.setSelectedItem(String.valueOf(mini.getLight()));
		dial_.getContentPane().add(light_);
		
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		cancel_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(cancel_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
		frame_.getContentPane().add(dial_);
	}
	
	public void moveable(int x, int y)
	{
		String s [] = {"not moveable", "up", "right", "down", "left"};
		xx_ = x;
		yy_ = y;
		which_ = "moveable";
		JLabel lpresent = new JLabel("Moveable :");
		movebox_ = new JComboBox(s);
		dial_.setSize(new Dimension(300, 160));
		dial_.setTitle("Specify bloc moveable");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(150, 50));
		dial_.getContentPane().add(lpresent);
		dial_.getContentPane().add(movebox_);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void magician(int x, int y)
	{
		String s [] = {"true", "false"};
		xx_ = x;
		yy_ = y;
		which_ = "magician";
		JLabel lpresent = new JLabel("Real :");
		magician_ = new JComboBox(s);
		dial_.setSize(new Dimension(300, 160));
		dial_.setTitle("Specify magician real");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(150, 50));
		dial_.getContentPane().add(lpresent);
		dial_.getContentPane().add(magician_);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
//	public void door(int x, int y)
//	{
//		String s [] = {"open", "closed"};
//		xx_ = x;
//		yy_ = y;
//		which_ = "door";
//		JLabel lpresent = new JLabel("Door is :");
//		door_ = new JComboBox(s);
//		dial_.setSize(new Dimension(300, 160));
//		dial_.setTitle("Specify door");
//		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
//		dial_.getContentPane().setLayout(new FlowLayout());
//		
//		lpresent.setPreferredSize(new Dimension(150, 50));
//		dial_.getContentPane().add(lpresent);
//		dial_.getContentPane().add(door_);
//		ok_.setPreferredSize(new Dimension(90, 30));
//		dial_.getContentPane().add(ok_);
//		
//		dial_.requestFocus();
//		dial_.setVisible(true);
//	}
	
	public void entrance(int x, int y, String s)
	{
		xx_ = x;
		yy_ = y;
		hide_ = s;
		which_ = "entrance";
		JLabel lpresent = new JLabel("Linked Map :");
		link_ = new JTextField(10);
		dial_.setSize(new Dimension(300, 160));
		dial_.setTitle("Specify linked map to entrance");
		dial_.setLocation(dim_.width / 2 - dial_.getWidth() / 2,	dim_.height / 2 - dial_.getHeight() / 2);
		dial_.getContentPane().setLayout(new FlowLayout());
		
		lpresent.setPreferredSize(new Dimension(150, 50));
		dial_.getContentPane().add(lpresent);
		dial_.getContentPane().add(link_);
		ok_.setPreferredSize(new Dimension(90, 30));
		dial_.getContentPane().add(ok_);
		
		dial_.requestFocus();
		dial_.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		Object source = e.getSource();
		if (source == ok_)
		{
			if (which_ == "new_map")
			{
				if (name_.getText().length() == 0)
					JOptionPane.showMessageDialog(dial_, "No name filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
				else
					if (l_.getText().length() == 0)
						JOptionPane.showMessageDialog(dial_, "No width filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
					else
						if (h_.getText().length() == 0)
							JOptionPane.showMessageDialog(dial_, "No heigth filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
						else
							if (x_.getText().length() == 0)
								JOptionPane.showMessageDialog(dial_, "No startx filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
							else
								if (y_.getText().length() == 0)
									JOptionPane.showMessageDialog(dial_, "No starty filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
								else
								{
									frame_.new_map_done(name_.getText(), Integer.parseInt(l_.getText()), Integer.parseInt(h_.getText()), Integer.parseInt(x_.getText()), Integer.parseInt(y_.getText()));
									dial_.setVisible(false);
								}
			}
			if (which_ == "new_minimap")
			{
				if (x_.getText().length() == 0)
					JOptionPane.showMessageDialog(dial_, "No x coord filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
				else
					if (y_.getText().length() == 0)
						JOptionPane.showMessageDialog(dial_, "No y coord filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
					else
					{
						boolean b = false;
						if ((String) light_.getSelectedItem() == "true")
							b = true;
						frame_.new_minimap_done(Integer.parseInt(x_.getText()), Integer.parseInt(y_.getText()), b);
						dial_.setVisible(false);
					}
			}
			if (which_ == "add_minimap" || which_ == "add_map")
			{
				dial_.setVisible(false);
			}
			if (which_ == "map_properties")
			{
				if (name_.getText().length() == 0)
					JOptionPane.showMessageDialog(dial_, "No name filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
				else
					if (l_.getText().length() == 0)
						JOptionPane.showMessageDialog(dial_, "No width filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
					else
						if (h_.getText().length() == 0)
							JOptionPane.showMessageDialog(dial_, "No heigth filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
						else
							if (x_.getText().length() == 0)
								JOptionPane.showMessageDialog(dial_, "No startx filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
							else
								if (y_.getText().length() == 0)
									JOptionPane.showMessageDialog(dial_, "No starty filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
								else
								{
									frame_.map_properties_done(name_.getText(), Integer.parseInt(l_.getText()), Integer.parseInt(h_.getText()), Integer.parseInt(x_.getText()), Integer.parseInt(y_.getText()));
									dial_.setVisible(false);
								}
			}
			if (which_ == "minimap_properties")
			{
				if (x_.getText().length() == 0)
					JOptionPane.showMessageDialog(dial_, "No x coord filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
				else
					if (y_.getText().length() == 0)
						JOptionPane.showMessageDialog(dial_, "No y coord filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
					else
					{
						boolean b = false;
						if ((String) light_.getSelectedItem() == "true")
							b = true;
						frame_.minimap_properties_done(Integer.parseInt(x_.getText()), Integer.parseInt(y_.getText()), b);
						dial_.setVisible(false);
					}
			}
			if (which_ == "delete_map")
			{
				frame_.delete_map_done();
				dial_.setVisible(false);
			}
			if (which_ == "delete_minimap")
			{
				frame_.delete_minimap_done();
				dial_.setVisible(false);
			}
			if (which_ == "entrance")
			{
				if (link_.getText().length() == 0)
					JOptionPane.showMessageDialog(dial_, "No map filled", "Invalid field", JOptionPane.ERROR_MESSAGE);
				else
				{
					dial_.setVisible(false);
					frame_.entrance_done(link_.getText(), xx_, yy_, hide_);
				}
			}
			if (which_ == "magician")
			{
				boolean b = false;
				if (((String) magician_.getSelectedItem()) == "true")
					b = true;
				dial_.setVisible(false);
				frame_.magician_done(xx_, yy_, b);
			}			
//			if (which_ == "door")
//			{
//				boolean b = false;
//				if (((String) door_.getSelectedItem()) == "open")
//					b = true;
//				dial_.setVisible(false);
//				frame_.door_done(xx_, yy_, b);
//			}
			if (which_ == "moveable")
			{
				int i = movebox_.getSelectedIndex();
				int j = 0;
				
		        switch (i) {
		            case 0:
		                j = 0;
		                break;
		            case 1:
		                j = 1000;
		                break;
		            case 2:
		            	j = 100; 
		                break;
		            case 3:
		            	j = 10;
		                break;
		            case 4:
		            	j = 1;
		                break;		      
		            }
				dial_.setVisible(false);
				frame_.moveable_done(j, xx_, yy_);
			}
		}
		if (source == cancel_)
			dial_.setVisible(false);
		if (source == light_)
			select_ = (String) light_.getSelectedItem();
	}
	
	public Quest getQuest()
	{
		return quest_;
	}
	
	protected
		Quest		quest_;
		Fenetre		frame_;
		JDialog 	dial_;
		JButton 	ok_, cancel_;
		JTextField	name_, l_, h_, x_, y_, link_;
		JComboBox	light_, movebox_, magician_, door_;
		Dimension	dim_;
		String		which_, select_, hide_;
		int			xx_, yy_;
}
