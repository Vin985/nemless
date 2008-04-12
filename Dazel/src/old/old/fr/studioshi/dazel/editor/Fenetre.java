/*
 * Created on 1 juil. 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package old.fr.studioshi.dazel.editor;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ListIterator;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import old.fr.studioshi.dazel.game.items.Block;
import old.fr.studioshi.dazel.game.items.Door;
import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.items.Map;
import old.fr.studioshi.dazel.game.items.Minimap;
import old.fr.studioshi.dazel.game.items.Quest;
import old.fr.studioshi.dazel.game.perso.Blob;
import old.fr.studioshi.dazel.game.perso.Eye_guard;
import old.fr.studioshi.dazel.game.perso.Ganon;
import old.fr.studioshi.dazel.game.perso.Knight;
import old.fr.studioshi.dazel.game.perso.Magician;
import old.fr.studioshi.dazel.game.perso.Moblin;
import old.fr.studioshi.dazel.game.perso.Monstre;
import old.fr.studioshi.dazel.game.perso.Pike_bloc;
import old.fr.studioshi.dazel.game.perso.Spider;

import fr.studioshi.common.game.model.Coordinates;

/**
 * @author Vincent
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class Fenetre extends JFrame implements ListSelectionListener,
		ActionListener, ItemListener, MouseListener, MouseMotionListener {
	/**
	 * Ctor for editor frame
	 */
	public Fenetre() {
		frame_ = new JFrame();
		dim_ = Toolkit.getDefaultToolkit().getScreenSize();

		frame_.setTitle("Editor");
		if (dim_.width - 200 > 1100 && dim_.height - 200 > 700)
			frame_.setSize(dim_.width - 200, dim_.height - 200);
		else
			frame_.setSize(1100, 700);
		frame_.setLocation(dim_.width / 2 - frame_.getWidth() / 2, dim_.height
				/ 2 - frame_.getHeight() / 2);

		frame_.getContentPane().setLayout(new FlowLayout());

		frame_.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});

		q_ = new Quest("Default quest");
		m_ = new Map("main", 1, 1, 1, 1, null);
		q_.setMap(m_);
		current_ = new Minimap(0, 0, true);
		m_.setMinimap(current_);

		MenuBar();
		Label_color();
		Radio_map();
		Radio_color();
		Icon();
		MenuListe();
		Image();
		Questmenu();

		frame_.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame_.setVisible(true);
	}

	/**
	 * 
	 */
	private void Radio_color() {
		ButtonGroup gpe = new ButtonGroup();
		JPanel pradio = new JPanel();
		white_ = new JRadioButton("White");
		brown_ = new JRadioButton("Brown");
		none_ = new JRadioButton("None", true);
		blue_ = new JRadioButton("Blue");
		green_ = new JRadioButton("Green");

		pradio.add(none_);
		gpe.add(none_);
		none_.addActionListener(this);
		pradio.add(white_);
		gpe.add(white_);
		white_.addActionListener(this);
		pradio.add(brown_);
		gpe.add(brown_);
		brown_.addActionListener(this);
		pradio.add(green_);
		gpe.add(green_);
		green_.addActionListener(this);
		pradio.add(blue_);
		gpe.add(blue_);
		blue_.addActionListener(this);

		pradio.setPreferredSize(new Dimension(240, 60));
		frame_.getContentPane().add(pradio);
	}

	/**
	 * 
	 */
	private void Radio_map() {
		ButtonGroup gpe = new ButtonGroup();
		JPanel pradio = new JPanel();
		map_ = new JRadioButton("Map", true);
		hide_ = new JRadioButton("Hidden");
		JLabel maptype = new JLabel("Map type :");

		gpe.add(map_);
		map_.addActionListener(this);
		gpe.add(hide_);
		hide_.addActionListener(this);
		pradio.add(maptype);
		pradio.add(map_);
		pradio.add(hide_);
		pradio.setPreferredSize(new Dimension(900, 30));
		frame_.getContentPane().add(pradio);
	}

	/**
	 * 
	 */
	private void Label_color() {
		JPanel pradio = new JPanel();
		JLabel colortype = new JLabel("Color :");
		pradio.add(colortype);
		pradio.setPreferredSize(new Dimension(140, 30));
		frame_.getContentPane().add(pradio);
	}

	/**
	 * Add the icon to the frame
	 */
	public void Icon() {
		picon_ = new Draw(new ImageIcon("Sprites/Map/plain.png"), "icon");
		picon_.setPreferredSize(new Dimension(800, 60));
		frame_.getContentPane().add(picon_);
	}

	/**
	 * Add the editmap image to the frame
	 */
	public void Image() {
		pimage_ = new Draw(new ImageIcon("Sprites/Map/plain.png"), "");
		pimage_.setPreferredSize(new Dimension(530, 550));
		pimage_.addMouseListener(this);
		pimage_.addMouseMotionListener(this);
		frame_.getContentPane().add(pimage_);
	}

	public void Questmenu() {
		JPanel pmenu = new JPanel();
		Box bbuttons = Box.createVerticalBox();
		properties_ = new JButton("Properties");
		moveto_ = new JButton("Move to");
		new_ = new JButton("New");
		delete_ = new JButton("Delete");
		addcurrent_ = new JButton("Add current");
		// mapcombo_ = new JComboBox(q_.getMapNames());
		ButtonGroup gpe = new ButtonGroup();
		mapradio_ = new JRadioButton("Maps", true);
		minimapradio_ = new JRadioButton("Minimaps");
		maplist_ = new JList(q_.getMapNames());
		JLabel edit = new JLabel("Editor :");

		properties_.addActionListener(this);
		moveto_.addActionListener(this);
		new_.addActionListener(this);
		delete_.addActionListener(this);
		addcurrent_.addActionListener(this);
		// mapcombo_.addActionListener(this);
		mapradio_.addActionListener(this);
		minimapradio_.addActionListener(this);
		maplist_.addListSelectionListener(this);

		edit.setPreferredSize(new Dimension(240, 30));
		pmenu.add(edit);
		gpe.add(mapradio_);
		gpe.add(minimapradio_);
		mapradio_.setPreferredSize(new Dimension(100, 30));
		pmenu.add(mapradio_);
		minimapradio_.setPreferredSize(new Dimension(100, 30));
		pmenu.add(minimapradio_);

		maplist_.setSelectedIndex(0);
		maplist_.setSelectionMode(0);
		JScrollPane defil = new JScrollPane(maplist_);
		maplist_.setPreferredSize(new Dimension(82, 700));
		defil.setPreferredSize(new Dimension(100, 300));
		pmenu.add(defil);

		// mapcombo_.setEnabled(false);
		// mapcombo_.setPreferredSize(new Dimension(120, 20));
		bbuttons.add(Box.createVerticalStrut(20));
		// bbuttons.add(mapcombo_);
		moveto_.setPreferredSize(new Dimension(120, 30));
		// bbuttons.add(Box.createGlue());
		bbuttons.add(moveto_);
		bbuttons.add(Box.createVerticalStrut(15));
		addcurrent_.setPreferredSize(new Dimension(120, 30));
		bbuttons.add(addcurrent_);
		bbuttons.add(Box.createVerticalStrut(20));
		new_.setPreferredSize(new Dimension(120, 30));
		bbuttons.add(new_);
		bbuttons.add(Box.createVerticalStrut(20));
		delete_.setPreferredSize(new Dimension(120, 30));
		bbuttons.add(delete_);
		bbuttons.add(Box.createVerticalStrut(20));
		properties_.setPreferredSize(new Dimension(120, 30));
		bbuttons.add(properties_);
		bbuttons.setPreferredSize(new Dimension(140, 300));
		bbuttons.add(Box.createVerticalStrut(20));
		pmenu.add(bbuttons);

		pmenu.setPreferredSize(new Dimension(250, 550));
		frame_.getContentPane().add(pmenu);
	}

	/**
	 * Add liste for icons' name in the frame
	 */
	public void MenuListe() {
		ButtonGroup gpe = new ButtonGroup();
		String[] elements = { "dark", "dust", "entrance", "grave", "plain",
				"sand", "bridge", "castle_2_up", "castle_down_left",
				"castle_down_right", "castle_tree_down_left",
				"castle_tree_down_right", "castle_tree_up",
				"castle_tree_up_left", "castle_tree_up_right", "castle_up",
				"castle_up_left", "castle_up_right", "decor_down_left",
				"decor_down_right", "decor_up_left", "decor_up_right", "rock",
				"rock_down", "rock_down_left", "rock_down_right", "rock_midle",
				"rock_up_left", "rock_up_right", "scale", "stairs", "stone",
				"tree_big_down_left", "tree_big_down_right",
				"tree_big_up_left", "tree_big_up_right", "tree_small", "water",
				"water_down", "water_down_left", "water_down_right",
				"water_left", "water_right", "water_up", "water_up_left",
				"water_up_right" };
		liste_ = new JList(elements);
		JPanel pliste = new JPanel();
		donjbox_ = new JRadioButton("Dungeon");
		mapbox_ = new JRadioButton("Map", true);
		persobox_ = new JRadioButton("Characters");
		itembox_ = new JRadioButton("Items");
		JLabel listtype = new JLabel("Sprites types :");

		listtype.setPreferredSize(new Dimension(200, 20));
		pliste.add(listtype);

		gpe.add(mapbox_);
		mapbox_.setPreferredSize(new Dimension(100, 20));
		pliste.add(mapbox_);
		mapbox_.addActionListener(this);
		gpe.add(donjbox_);
		donjbox_.setPreferredSize(new Dimension(100, 20));
		pliste.add(donjbox_);
		donjbox_.addActionListener(this);
		gpe.add(persobox_);
		persobox_.setPreferredSize(new Dimension(100, 40));
		pliste.add(persobox_);
		persobox_.addActionListener(this);
		gpe.add(itembox_);
		itembox_.setPreferredSize(new Dimension(100, 40));
		pliste.add(itembox_);
		itembox_.addActionListener(this);

		liste_.setSelectedIndex(4);
		liste_.setVisibleRowCount(20);
		liste_.setSelectionMode(0);
		JScrollPane defil = new JScrollPane(liste_);
		liste_.addListSelectionListener(this);
		pliste.setPreferredSize(new Dimension(240, 550));
		pliste.add(defil);
		frame_.getContentPane().add(pliste);
	}

	/**
	 * Add the menu bar in the frame, with mnemonics and shortcuts
	 */
	public void MenuBar() {
		menubar_ = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu game = new JMenu("Editor");
		quit_ = new JMenuItem("Quit");
		load_ = new JMenuItem("Load quest");
		save_ = new JMenuItem("Save quest");
		clean_ = new JMenuItem("Clean Minimap");

		menubar_.add(file);
		menubar_.add(game);
		file.add(load_);
		load_.addActionListener(this);
		file.add(save_);
		save_.addActionListener(this);
		file.addSeparator();
		file.add(quit_);
		quit_.addActionListener(this);
		game.add(clean_);
		clean_.addActionListener(this);

		file.setMnemonic('F');
		load_.setMnemonic('L');
		save_.setMnemonic('S');
		quit_.setMnemonic('Q');
		game.setMnemonic('E');
		clean_.setMnemonic('C');

		load_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));
		quit_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		save_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		clean_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));

		frame_.setJMenuBar(menubar_);
	}

	/*
	 * public void check_combo() { String s [] = q_.getMapNames(); boolean here =
	 * false;
	 * 
	 * if (s.length != mapcombo_.getItemCount()) for (int i = 0; i < s.length;
	 * i++) { for (int j = 0; j < mapcombo_.getItemCount(); j++) if
	 * (s[i].equals(mapcombo_.getItemAt(j))) here = true; if (here == false)
	 * mapcombo_.addItem(s[i]); here = false; } }
	 */
	public Coordinates Name2Coord() {
		char c[] = selectminimap_.toCharArray();
		String s = new String(c, 5, c.length - 5);
		String x = "", y = "";
		c = s.toCharArray();
		int i = 0;

		while (c[i] != ',') {
			x = x + c[i++];
		}
		i += 2;
		for (; i < c.length; i++)
			y = y + c[i];

		return new Coordinates(Integer.parseInt(x), Integer.parseInt(y));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == quit_) {
			dispose();
			System.exit(0);
		}
		if (source == load_) {
			loadfile_ = new JFileChooser(".");
			loadfile_.showOpenDialog(frame_);

			MyFileFilter filter = new MyFileFilter();
			loadfile_.resetChoosableFileFilters();
			loadfile_.addChoosableFileFilter(filter);
			loadfile_.setFileFilter(filter);
			file_ = loadfile_.getSelectedFile();

			if (file_ != null && file_.getName() != null) {
				Parse p = new Parse(file_.getName());
				p.parse();
				q_ = p.getQuest();
				m_ = q_.getMap("main");
				current_ = m_.getMinimap(new Coordinates(0, 0));
				if (current_ == null) {
					current_ = new Minimap(0, 0, true);
					m_.setMinimap(current_);
				}
				map_.setSelected(true);
				mapbox_.setSelected(true);
				type_ = "Map";
				String[] elements = { "dark", "dust", "entrance", "grave",
						"plain", "sand", "bridge", "castle_2_up",
						"castle_down_left", "castle_down_right",
						"castle_tree_down_left", "castle_tree_down_right",
						"castle_tree_up", "castle_tree_up_left",
						"castle_tree_up_right", "castle_up", "castle_up_left",
						"castle_up_right", "decor_down_left",
						"decor_down_right", "decor_up_left", "decor_up_right",
						"rock", "rock_down", "rock_down_left",
						"rock_down_right", "rock_midle", "rock_up_left",
						"rock_up_right", "scale", "stairs", "stone",
						"tree_big_down_left", "tree_big_down_right",
						"tree_big_up_left", "tree_big_up_right", "tree_small",
						"water", "water_down", "water_down_left",
						"water_down_right", "water_left", "water_right",
						"water_up", "water_up_left", "water_up_right" };
				liste_.setListData(elements);
				liste_.setSelectedIndex(4);
				// mapcombo_.setEnabled(false);
				// check_combo();
				maplist_.setListData(q_.getMapNames());
				maplist_.setSelectedIndex(0);
			}
			current_.print(pimage_);
		}
		if (source == save_) {
			savefile_ = new JFileChooser(".");
			MyFileFilter filter = new MyFileFilter();

			savefile_.showOpenDialog(frame_);
			savefile_.resetChoosableFileFilters();
			savefile_.addChoosableFileFilter(filter);
			savefile_.setFileFilter(filter);
			file_ = savefile_.getSelectedFile();
			if (file_ != null && file_.getName() != null) {
				Write filename = new Write(file_.getName(), q_);
				filename.init();
				filename.tag_quest();
			}
			current_.print(pimage_);
		}
		if (source == clean_) {
			current_ = new Minimap(0, 0, true);
			m_.setMinimap(current_);
			current_.print_decor(pimage_);
			map_.setSelected(true);
		}
		if (source == brown_) {
			color_ = "_brown";
			picon_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
					+ color_ + ".png"), new Coordinates(320, 10));
		}
		if (source == white_) {
			color_ = "_white";
			picon_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
					+ color_ + ".png"), new Coordinates(320, 10));
		}
		if (source == green_) {
			color_ = "_green";
			picon_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
					+ color_ + ".png"), new Coordinates(320, 10));
		}
		if (source == none_) {
			color_ = "";
			picon_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
					+ color_ + ".png"), new Coordinates(320, 10));
		}
		if (source == blue_) {
			color_ = "_blue";
			picon_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
					+ color_ + ".png"), new Coordinates(320, 10));
		}
		if (source == map_)
			current_.print(pimage_);
		if (source == hide_)
			current_.print_hide(pimage_);
		if (source == donjbox_) {
			type_ = "dungeon";
			String[] elements = { "bloc", "corner_bot_left",
					"corner_bot_right", "corner_up_left", "corner_up_right",
					"door_closed_down", "door_closed_down2",
					"door_closed_left", "door_closed_left2",
					"door_closed_right", "door_closed_right2",
					"door_closed_up", "door_closed_up2", "door_open_down",
					"door_open_left", "door_open_right", "door_open_up",
					"ground", "ground2", "hole_down", "hole_left",
					"hole_right", "hole_up", "stairs2", "statue1", "statue2",
					"statue3", "statue4", "wall_down_left1", "wall_down_left2",
					"wall_down_middle", "wall_down_right2", "wall_down_right2",
					"wall_left_down", "wall_left_middle", "wall_left_up",
					"wall_right_down", "wall_right_middle", "wall_right_up",
					"wall_up_left1", "wall_up_left2", "wall_up_middle",
					"wall_up_right1", "wall_up_right2" };
			liste_.setListData(elements);
			liste_.setSelectedIndex(0);
		}
		if (source == mapbox_) {
			type_ = "Map";
			String[] elements = { "dark", "dust", "entrance", "grave", "plain",
					"sand", "bridge", "castle_2_up", "castle_down_left",
					"castle_down_right", "castle_tree_down_left",
					"castle_tree_down_right", "castle_tree_up",
					"castle_tree_up_left", "castle_tree_up_right", "castle_up",
					"castle_up_left", "castle_up_right", "decor_down_left",
					"decor_down_right", "decor_up_left", "decor_up_right",
					"rock", "rock_down", "rock_down_left", "rock_down_right",
					"rock_midle", "rock_up_left", "rock_up_right", "scale",
					"stairs", "stone", "tree_big_down_left",
					"tree_big_down_right", "tree_big_up_left",
					"tree_big_up_right", "tree_small", "water", "water_down",
					"water_down_left", "water_down_right", "water_left",
					"water_right", "water_up", "water_up_left",
					"water_up_right" };
			liste_.setListData(elements);
			liste_.setSelectedIndex(4);
		}
		if (source == persobox_) {
			type_ = "Characters/editor";
			String[] elements = { "blob", "mini_blob", "eye_guard", "ganon",
					"giant_spider", "knight", "magician", "moblin", "pepe",
					"spider" };
			liste_.setListData(elements);
			liste_.setSelectedIndex(3);
		}
		if (source == itembox_) {
			type_ = "Items/editor";
			String[] elements = { "big_key", "bomb", "bow", "bracelet",
					"candle_blue", "candle_red", "clock", "compass", "heart",
					"key", "ladder", "letter_blue", "letter_yellow",
					"little_heart", "meat", "meat_2", "pike_blue", "pike_red",
					"potion_blue", "potion_red", "radeau_1", "radeau_2",
					"radeau_3", "ring_blue", "ring_red", "rupee", "rupee_blue",
					"shield", "shield+little", "spellbook", "staff",
					"suit_blue", "suit_green", "suit_red", "sword",
					"sword_wood", "triforce", "triforce_2", "triforce_to_take",
					"triforce_to_take_2" };
			liste_.setListData(elements);
			liste_.setSelectedIndex(0);
		}
		if (source == properties_) {
			Dialog dial = new Dialog(q_, this);
			if (mapradio_.isSelected())
				dial.map_properties(m_);
			else
				dial.minimap_properties(current_, m_);
			dial.dispose();
			if (map_.isSelected())
				current_.print(pimage_);
			else
				current_.print_hide(pimage_);
		}
		if (source == moveto_) {
			if (mapradio_.isSelected()) {
				m_ = q_.getMap(selectmap_);
				current_ = m_.getMinimap(new Coordinates(0, 0));
			} else
				current_ = m_.getMinimap(Name2Coord());
			current_.print(pimage_);
		}
		if (source == new_) {
			Dialog dial = new Dialog(q_, this);
			if (mapradio_.isSelected())
				dial.new_map();
			else
				dial.new_minimap(m_);
			dial.dispose();
			current_.print(pimage_);
		}
		if (source == delete_) {
			Dialog dial = new Dialog(q_, this);
			if (mapradio_.isSelected())
				dial.delete_map(m_);
			else
				dial.delete_minimap(current_);
			dial.dispose();
			current_.print(pimage_);
		}
		if (source == addcurrent_) {
			Dialog dial = new Dialog(q_, this);
			if (mapradio_.isSelected()) {
				q_.setMap(m_);
				dial.add_map(q_, m_);
			} else {
				m_.setMinimap(current_);
				dial.add_minimap(m_, current_);
			}
			dial.dispose();
			if (map_.isSelected())
				current_.print(pimage_);
			else
				current_.print_hide(pimage_);
		}
		// if (source == mapcombo_)
		// {
		// m_ = q_.getMap((String) mapcombo_.getSelectedItem());
		// current_.print(pimage_);
		// }
		if (source == mapradio_) {
			mp2_ = maplist_.getSelectedIndex();
			// mapcombo_.setEnabled(false);
			maplist_.setListData(q_.getMapNames());
			maplist_.setSelectedIndex(mp_);
		}
		if (source == minimapradio_) {
			mp_ = maplist_.getSelectedIndex();
			// mapcombo_.setEnabled(true);
			maplist_.setListData(m_.getMinimapNames());
			maplist_.setSelectedIndex(mp2_);
			// mapcombo_.setSelected(mp_);
		}
	}

	public void new_map_done(String s, int a, int b, int x, int y) {
		m_ = new Map(s, a, b, x, y, null);
		current_ = new Minimap(0, 0, true);
		q_.setMap(m_);
		m_.setMinimap(current_);
		maplist_.setListData(q_.getMapNames());
		maplist_.setSelectedIndex(0);
		// mapcombo_.addItem(m_.getName());
		current_.print(pimage_);
	}

	public void map_properties_done(String s, int a, int b, int x, int y) {
		m_.setName(s);
		m_.setL(a);
		m_.setH(b);
		m_.setStartx(x);
		m_.setStarty(y);
		if (map_.isSelected())
			current_.print(pimage_);
		else
			current_.print_hide(pimage_);
	}

	public void new_minimap_done(int x, int y, boolean light) {
		current_ = new Minimap(x, y, light);
		m_.setMinimap(current_);
		maplist_.setListData(m_.getMinimapNames());
		maplist_.setSelectedIndex(0);
		current_.print(pimage_);
	}

	public void minimap_properties_done(int x, int y, boolean light) {
		current_.setX(x);
		current_.setY(y);
		current_.setLight(light);
		if (map_.isSelected())
			current_.print(pimage_);
		else
			current_.print_hide(pimage_);
	}

	public void delete_map_done() {
		ListIterator it = q_.getMaps().listIterator();

		while (it.hasNext()) {
			Map tmp = (Map) it.next();
			if (m_.getName().equals(tmp.getName())) {
				q_.getMaps().remove(tmp);
				return;
			}
		}
		m_ = q_.getMap("main");
		maplist_.setListData(q_.getMapNames());
		maplist_.setSelectedIndex(0);
		current_.print(pimage_);
	}

	public void moveable_done(int j, int x, int y) {
		Block b = (Block) current_.getDecor(new Coordinates(x, y));
		b.setMoveable(j);
		if (map_.isSelected())
			current_.print(pimage_);
		else
			current_.print_hide(pimage_);
	}

	public void entrance_done(String s, int x, int y, String hide) {
		if (hide == "")
			current_.getDecor(new Coordinates(x, y)).setLink(s);
		else
			current_.getDecor(new Coordinates(x, y)).getHide().setLink(s);
		if (map_.isSelected())
			current_.print(pimage_);
		else
			current_.print_hide(pimage_);
	}

	public void magician_done(int x, int y, boolean real) {
		Magician mago = (Magician) current_.getMonsters_(new Coordinates(x, y));
		mago.setReal(real);
		current_.print(pimage_);
	}

	// public void door_done(int x, int y, boolean open)
	// {
	// Door d = (Door) current_.getDecor(new Coord(x, y));
	// if (open == false)
	// d.setObstacle(true);
	// d.setOpen(open);
	// current_.print(pimage_);
	// }

	public void delete_minimap_done() {
		ListIterator it = m_.getMinis().listIterator();

		while (it.hasNext()) {
			Minimap tmp = (Minimap) it.next();
			if (current_.getCoord().equals(tmp.getCoord())) {
				m_.getMinis().remove(tmp);
				return;
			}
		}
		current_ = m_.getMinimap(new Coordinates(0, 0));
		maplist_.setListData(m_.getMinimapNames());
		maplist_.setSelectedIndex(0);
		current_.print(pimage_);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	public void itemStateChanged(ItemEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent e) {
		Object source = e.getSource();

		if (source == liste_)
			if (!e.getValueIsAdjusting()) {
				select_ = (String) liste_.getSelectedValue();
				picon_.myDraw(new ImageIcon("Sprites/Map/plain.png"),
						new Coordinates(320, 10));
				picon_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
						+ color_ + ".png"), new Coordinates(320, 10));
			}
		if (source == maplist_) {
			if (mapradio_.isSelected())
				selectmap_ = (String) maplist_.getSelectedValue();
			else
				selectminimap_ = (String) maplist_.getSelectedValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= 10 && e.getY() >= 7 && e.getX() <= 522
				&& e.getY() <= 359) {
			int x = ((e.getX() - 10) - ((e.getX() - 10) % 32)) / 32;
			int y = ((e.getY() - 7) - ((e.getY() - 7) % 32)) / 32;

			if (x >= 0 && x < 16 && y >= 0 && y < 11) {
				pimage_.myDraw(new ImageIcon("Sprites/Map/plain.png"),
						new Coordinates(x * 32 + 10, y * 32 + 7));
				if (itembox_.isSelected())
					pimage_.myDraw(new ImageIcon("Sprites/" + type_ + "/"
							+ current_.getDecor(new Coordinates(x, y)).getType()
							+ ".png"), new Coordinates(x * 32 + 10, y * 32 + 7));
				pimage_.myDraw(new ImageIcon("Sprites/" + type_ + "/" + select_
						+ color_ + ".png"), new Coordinates(x * 32 + 10, y * 32 + 7));
				if (mapbox_.isSelected())
					if (current_.getElement(new Coordinates(x, y)) != null)
						pimage_.myDraw(new ImageIcon("Sprites/Items/editor/"
								+ current_.getElement(new Coordinates(x, y))
										.getType() + ".png"), new Coordinates(
								x * 32 + 10, y * 32 + 7));
				pimage_.myDraw(new ImageIcon("Sprites/selected_16x16.png"),
						new Coordinates(x * 32 + 10, y * 32 + 7));

				if (map_.isSelected()) {
					if (mapbox_.isSelected()) {
						if (select_ == "rock" || select_ == "grave"
								|| select_ == "stone") {
							current_.setDecor(new Block(true, new Coordinates(x, y),
									select_ + color_, 0, false, current_
											.getDecor(new Coordinates(x, y))
											.getHide()));
							Dialog dial = new Dialog(q_, this);
							dial.moveable(x, y);
							dial.dispose();
						} else if (select_ != "dark" && select_ != "sand"
								&& select_ != "plain" && select_ != "entrance"
								&& select_ != "dust" && select_ != "bridge"
								&& select_ != "stairs")
							current_
									.setDecor(new Element(true, x, y, select_
											+ color_, current_.getDecor(
											new Coordinates(x, y)).getHide(), null));
						else
							current_
									.setDecor(new Element(false, x, y, select_
											+ color_, current_.getDecor(
											new Coordinates(x, y)).getHide(), null));
						if (select_ == "entrance" || select_ == "stairs") {
							Dialog dial = new Dialog(q_, this);
							dial.entrance(x, y, "");
							dial.dispose();
						}
					}
					if (itembox_.isSelected())
						current_.setElement(new Element(false, x, y, select_
								+ color_, current_.getDecor(new Coordinates(x, y))
								.getHide(), null));
					if (persobox_.isSelected()) {
						if (select_ == "blob") {
							Blob b = new Blob("blob", 4, 1, true, true,
									new Coordinates(x, y));
							b.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(b);
						}
						if (select_ == "moblin") {
							Moblin m = new Moblin("moblin", 8, 1, false, false,
									new Coordinates(x, y));
							m.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(m);
						}
						if (select_ == "knight") {
							Knight k = new Knight("knight", 16, 1, false,
									false, new Coordinates(x, y));
							k.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(k);
						}
						if (select_ == "spider") {
							Spider s = new Spider("spider", 8, 1, false, false,
									false, new Coordinates(x, y));
							s.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(s);
						}
						if (select_ == "pike_bloc") {
							Pike_bloc p = new Pike_bloc("pike_bloc", 8, 1,
									false, false, new Coordinates(x, y));
							p.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(p);
						}
						if (select_ == "eye_guard") {
							Eye_guard eg = new Eye_guard("eye_guard", 8, 1,
									false, false, new Coordinates(x, y));
							eg.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(eg);
						}
						if (select_ == "magician") {
							Dialog dial = new Dialog(q_, this);
							Magician m = new Magician("magician", 12, 1, false,
									false, new Coordinates(x, y), false);
							m.setHide(current_.getDecor(new Coordinates(x, y)));
							current_.setMonsters_(m);
							dial.magician(x, y);
							dial.dispose();
						}
						if (select_ == "ganon") {
							if (x >= 0 && x < 15 && y >= 0 && y < 10) {
								Ganon g = new Ganon("ganon", 30, 1, false,
										false, new Coordinates(x, y));
								g.setHide(current_.getDecor(new Coordinates(x, y)));
								g.setUp_right(new Ganon("ganon", 30, 1, false,
										false, new Coordinates(x + 1, y)));
								g.setBot_left(new Ganon("ganon", 30, 1, false,
										false, new Coordinates(x, y + 1)));
								g.setBot_right(new Ganon("ganon", 30, 1, false,
										false, new Coordinates(x + 1, y + 1)));
								current_.setMonsters_(g);
								current_.setMonsters_(g.getBot_left_());
								current_.setMonsters_(g.getBot_right_());
								current_.setMonsters_(g.getUp_right_());
							}
						}
						if (select_ == "giant_spider") {
							if (x >= 1 && x < 15 && y >= 0 && y < 11) {
								// Giant_spider s = new
								// Giant_spider("giant_spider", 15, 1, false,
								// false, new Coord(x, y));
								// TODO
								// current_.setMonsters_(new
								// Eye_guard("eye_guard", 8, 1, false, false,
								// new Coord(x, y)));
							}
						}
					}
					if (donjbox_.isSelected()) {
						if (select_ == "bloc" || select_.startsWith("statue")) {
							current_.setDecor(new Block(true, new Coordinates(x, y),
									select_ + color_, 0, false, current_
											.getDecor(new Coordinates(x, y))
											.getHide()));
							Dialog dial = new Dialog(q_, this);
							dial.moveable(x, y);
							dial.dispose();
						} else if (select_.startsWith("door")) {
							boolean open = true;
							if (select_.indexOf("closed") != -1)
								open = false;
							current_.setDecor(new Door(false, new Coordinates(x, y),
									select_ + color_, open, 0, current_
											.getDecor(new Coordinates(x, y))
											.getHide()));
						} else if (select_.startsWith("ground"))
							current_
									.setDecor(new Element(false, x, y, select_
											+ color_, current_.getDecor(
											new Coordinates(x, y)).getHide(), null));
						else
							current_
									.setDecor(new Element(true, x, y, select_
											+ color_, current_.getDecor(
											new Coordinates(x, y)).getHide(), null));
						if (select_ == "stairs2") {
							Dialog dial = new Dialog(q_, this);
							dial.entrance(x, y, "");
							dial.dispose();
						}
					}
				}
				if (hide_.isSelected()) {
					if (type_.equals("Map")) {
						Element elt = current_.getElement(new Coordinates(x, y));
						Element dec = current_.getDecor(new Coordinates(x, y));
						Element mons = current_.getMonsters_(new Coordinates(x, y));

						if (mons == null && elt == null) {
							if (select_ != "dark" && select_ != "sand"
									&& select_ != "plain"
									&& select_ == "entrance"
									&& select_ != "dust" && select_ != "bridge")
								dec.setHide(new Element(true, x, y, select_
										+ color_, null, null));
							else
								dec.setHide(new Element(false, x, y, select_
										+ color_, null, null));
							current_.setDecor(dec);
							if (select_ == "entrance" || select_ == "stairs") {
								Dialog dial = new Dialog(q_, this);
								dial.entrance(x, y, "hide");
								dial.dispose();
							}
							// if (select_ == "rock" || select_ == "grave" ||
							// select_ == "stone")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.moveable(x, y);
							// dial.dispose();
							// }
						} else if (elt == null) {
							if (select_ != "dark" && select_ != "sand"
									&& select_ != "plain"
									&& select_ == "entrance"
									&& select_ != "dust" && select_ != "bridge")
								elt.setHide(new Element(true, x, y, select_
										+ color_, null, null));
							else
								elt.setHide(new Element(false, x, y, select_
										+ color_, null, null));
							// if (select_ == "entrance" || select_ == "stairs")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.entrance(x, y, "hide");
							// dial.dispose();
							// }
						} else if (mons == null) {
							if (select_ != "dark" && select_ != "sand"
									&& select_ != "plain"
									&& select_ == "entrance"
									&& select_ != "dust" && select_ != "bridge")
								elt.setHide(new Element(true, x, y, select_
										+ color_, null, null));
							else
								elt.setHide(new Element(false, x, y, select_
										+ color_, null, null));
							// if (select_ == "entrance" || select_ == "stairs")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.entrance(x, y, "hide");
							// dial.dispose();
							// }
							// if (select_ == "rock" || select_ == "grave" ||
							// select_ == "stone")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.moveable(x, y);
							// dial.dispose();
							// }
						}
					}
					if (type_.equals("Items/editor")) {
					}
					if (type_.equals("Characters/editor")) {
					}
					if (type_.equals("dungeon")) {
						Element elt = current_.getElement(new Coordinates(x, y));
						Element dec = current_.getDecor(new Coordinates(x, y));
						Element mons = current_.getMonsters_(new Coordinates(x, y));

						if (mons == null && elt == null) {
							if (select_ != "ground" && select_ != "ground2"
									&& select_ != "door_open_down"
									&& select_ != "door_open_left"
									&& select_ != "door_open_right"
									&& select_ != "door_open_up"
									&& select_ != "hole_down"
									&& select_ != "hole_left"
									&& select_ != "hole_right"
									&& select_ != "hole_up")
								dec.setHide(new Element(true, x, y, select_
										+ color_, null, null));
							else
								dec.setHide(new Element(false, x, y, select_
										+ color_, null, null));
							current_.setDecor(dec);
							if (select_ == "stairs2") {
								Dialog dial = new Dialog(q_, this);
								dial.entrance(x, y, "hide");
								dial.dispose();
							}
							// if (select_ == "rock" || select_ == "grave" ||
							// select_ == "stone")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.moveable(x, y);
							// dial.dispose();
							// }
						} else if (elt == null) {
							if (select_ != "ground" && select_ != "ground2"
									&& select_ != "door_open_down"
									&& select_ != "door_open_left"
									&& select_ != "door_open_right"
									&& select_ != "door_open_up"
									&& select_ != "hole_down"
									&& select_ != "hole_left"
									&& select_ != "hole_right"
									&& select_ != "hole_up")
								elt.setHide(new Element(true, x, y, select_
										+ color_, null, null));
							else
								elt.setHide(new Element(false, x, y, select_
										+ color_, null, null));
							// if (select_ == "entrance" || select_ == "stairs")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.entrance(x, y, "hide");
							// dial.dispose();
							// }
						} else if (mons == null) {
							if (select_ != "ground" && select_ != "ground2"
									&& select_ != "door_open_down"
									&& select_ != "door_open_left"
									&& select_ != "door_open_right"
									&& select_ != "door_open_up"
									&& select_ != "hole_down"
									&& select_ != "hole_left"
									&& select_ != "hole_right"
									&& select_ != "hole_up")
								elt.setHide(new Element(true, x, y, select_
										+ color_, null, null));
							else
								elt.setHide(new Element(false, x, y, select_
										+ color_, null, null));
							// if (select_ == "entrance" || select_ == "stairs")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.entrance(x, y, "hide");
							// dial.dispose();
							// }
							// if (select_ == "rock" || select_ == "grave" ||
							// select_ == "stone")
							// {
							// Dialog dial = new Dialog(q_, this);
							// dial.moveable(x, y);
							// dial.dispose();
							// }
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		cursor_ = frame_.getCursor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		frame_.setCursor(cursor_);
		if (map_.isSelected()) {
			pimage_.myDraw(new ImageIcon("Sprites/Map/"
					+ current_.getDecor(new Coordinates(x_, y_)).getType() + ".png"),
					new Coordinates(current_.getDecor(new Coordinates(x_, y_)).getCoord()
							.get_x() * 32 + 10, current_.getDecor(
							new Coordinates(x_, y_)).getCoord().get_y() * 32 + 7));
			pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
					+ current_.getDecor(new Coordinates(x_, y_)).getType() + ".png"),
					new Coordinates(current_.getDecor(new Coordinates(x_, y_)).getCoord()
							.get_x() * 32 + 10, current_.getDecor(
							new Coordinates(x_, y_)).getCoord().get_y() * 32 + 7));
		} else {
			pimage_.myDraw(new ImageIcon("Sprites/Map/"
					+ current_.getDecor(new Coordinates(x_, y_)).getHide().getType()
					+ ".png"), new Coordinates(current_.getDecor(new Coordinates(x_, y_))
					.getCoord().get_x() * 32 + 10, current_.getDecor(
					new Coordinates(x_, y_)).getCoord().get_y() * 32 + 7));
			pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
					+ current_.getDecor(new Coordinates(x_, y_)).getHide().getType()
					+ ".png"), new Coordinates(current_.getDecor(new Coordinates(x_, y_))
					.getCoord().get_x() * 32 + 10, current_.getDecor(
					new Coordinates(x_, y_)).getCoord().get_y() * 32 + 7));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() >= 10 && e.getY() >= 7 && e.getX() <= 522
				&& e.getY() <= 359) {
			int x = x_;
			int y = y_;
			x_ = ((e.getX() - 10) - ((e.getX() - 10) % 32)) / 32;
			y_ = ((e.getY() - 7) - ((e.getY() - 7) % 32)) / 32;

			Cursor vide = Toolkit.getDefaultToolkit().createCustomCursor(
					new ImageIcon("Sprites/null.png").getImage(),
					new Point(0, 0), "vide");
			frame_.setCursor(vide);

			Element old = current_.getDecor(new Coordinates(x, y));
			Element dec = current_.getDecor(new Coordinates(x_, y_));
			Element old2 = current_.getElement(new Coordinates(x, y));
			Element elt = current_.getElement(new Coordinates(x_, y_));
			Monstre old3 = current_.getMonsters_(new Coordinates(x, y));
			Monstre mons = current_.getMonsters_(new Coordinates(x_, y_));

			if (x_ >= 0 && x_ < 16 && y_ >= 0 && y_ < 11) {
				if (map_.isSelected()) {
					if (old != null) {
						pimage_.myDraw(new ImageIcon("Sprites/Map/"
								+ old.getType() + ".png"), new Coordinates(old
								.getCoord().get_x() * 32 + 10, old.getCoord()
								.get_y() * 32 + 7));
						pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
								+ old.getType() + ".png"), new Coordinates(old
								.getCoord().get_x() * 32 + 10, old.getCoord()
								.get_y() * 32 + 7));
					}
					if (old2 != null)
						pimage_.myDraw(new ImageIcon("Sprites/Items/editor/"
								+ old2.getType() + ".png"), new Coordinates(old2
								.getCoord().get_x() * 32 + 10, old2.getCoord()
								.get_y() * 32 + 7));
					if (old3 != null)
						pimage_.myDraw(new ImageIcon(
								"Sprites/Characters/editor/" + old3.getType()
										+ ".png"), new Coordinates(old3.getCoord()
								.get_x() * 32 + 10,
								old3.getCoord().get_y() * 32 + 7));
					if (dec != null) {
						pimage_.myDraw(new ImageIcon("Sprites/Map/"
								+ dec.getType() + ".png"), new Coordinates(
								x_ * 32 + 10, y_ * 32 + 7));
						pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
								+ dec.getType() + ".png"), new Coordinates(
								x_ * 32 + 10, y_ * 32 + 7));
					}
					if (elt != null)
						pimage_.myDraw(new ImageIcon("Sprites/Items/editor/"
								+ elt.getType() + ".png"), new Coordinates(
								x_ * 32 + 10, y_ * 32 + 7));
					if (mons != null)
						if (mons != null && mons.getType() == "ganon") {
							Ganon g = (Ganon) mons;
							if (g.getBot_left_() == null
									&& g.getBot_right_() == null
									&& g.getUp_right_() == null)
								pimage_.myDraw(new ImageIcon(
										"Sprites/Characters/editor/"
												+ mons.getType() + ".png"),
										new Coordinates(x_ * 64 + 10, y_ * 64 + 7));
							else {
								Ganon gg = (Ganon) current_
										.getMonsters_(new Coordinates(x_ - 1, y_ - 1));
								// TODO
							}
						} else
							pimage_.myDraw(new ImageIcon(
									"Sprites/Characters/editor/"
											+ mons.getType() + ".png"),
									new Coordinates(x_ * 32 + 10, y_ * 32 + 7));
					pimage_.myDraw(new ImageIcon("Sprites/selected_16x16.png"),
							new Coordinates(x_ * 32 + 10, y_ * 32 + 7));
				}
				if (hide_.isSelected()) {
					if (old != null && old.getHide() != null) {
						pimage_.myDraw(new ImageIcon("Sprites/Map/"
								+ old.getHide().getType() + ".png"), new Coordinates(
								old.getCoord().get_x() * 32 + 10, old
										.getCoord().get_y() * 32 + 7));
						pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
								+ old.getHide().getType() + ".png"), new Coordinates(
								old.getCoord().get_x() * 32 + 10, old
										.getCoord().get_y() * 32 + 7));
					}
					// if (old2 != null && old2.getHide() != null)
					// pimage_.myDraw(new ImageIcon("Sprites/Items/editor/" +
					// old2.getHide().getType() + ".png"), new
					// Coord(old2.getCoord().get_x() * 32 + 10,
					// old2.getCoord().get_y() * 32 + 7));
					// if (old3 != null && old3.getHide() != null)
					// pimage_.myDraw(new ImageIcon("Sprites/Characters/editor/"
					// + old3.getHide().getType() + ".png"), new
					// Coord(old3.getCoord().get_x() * 32 + 10,
					// old3.getCoord().get_y() * 32 + 7));
					if (dec != null && dec.getHide() != null) {
						pimage_.myDraw(new ImageIcon("Sprites/Map/"
								+ dec.getHide().getType() + ".png"), new Coordinates(
								x_ * 32 + 10, y_ * 32 + 7));
						pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
								+ dec.getHide().getType() + ".png"), new Coordinates(
								x_ * 32 + 10, y_ * 32 + 7));
					}
					// if (elt != null && elt.getHide() != null)
					// pimage_.myDraw(new ImageIcon("Sprites/Items/editor/" +
					// elt.getHide().getType() + ".png"), new Coord(x_ * 32 +
					// 10, y_ * 32 + 7));
					// if (mons != null && mons.getHide() != null)
					// pimage_.myDraw(new ImageIcon("Sprites/Characters/editor/"
					// + mons.getHide().getType() + ".png"), new Coord(x_ * 32 +
					// 10, y_ * 32 + 7));
					pimage_.myDraw(new ImageIcon("Sprites/selected_16x16.png"),
							new Coordinates(x_ * 32 + 10, y_ * 32 + 7));
				}
			}
			if (x_ > 15)
				x_ = 15;
			if (y_ > 10)
				y_ = 10;
		} else {
			frame_.setCursor(cursor_);
			if (map_.isSelected()) {
				pimage_.myDraw(new ImageIcon("Sprites/Map/"
						+ current_.getDecor(new Coordinates(x_, y_)).getType()
						+ ".png"),
						new Coordinates(current_.getDecor(new Coordinates(x_, y_))
								.getCoord().get_x() * 32 + 10,
								current_.getDecor(new Coordinates(x_, y_)).getCoord()
										.get_y() * 32 + 7));
				pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
						+ current_.getDecor(new Coordinates(x_, y_)).getType()
						+ ".png"),
						new Coordinates(current_.getDecor(new Coordinates(x_, y_))
								.getCoord().get_x() * 32 + 10,
								current_.getDecor(new Coordinates(x_, y_)).getCoord()
										.get_y() * 32 + 7));
			} else {
				pimage_.myDraw(new ImageIcon("Sprites/Map/"
						+ current_.getDecor(new Coordinates(x_, y_)).getHide()
								.getType() + ".png"),
						new Coordinates(current_.getDecor(new Coordinates(x_, y_))
								.getCoord().get_x() * 32 + 10,
								current_.getDecor(new Coordinates(x_, y_)).getCoord()
										.get_y() * 32 + 7));
				pimage_.myDraw(new ImageIcon("Sprites/dungeon/"
						+ current_.getDecor(new Coordinates(x_, y_)).getHide()
								.getType() + ".png"),
						new Coordinates(current_.getDecor(new Coordinates(x_, y_))
								.getCoord().get_x() * 32 + 10,
								current_.getDecor(new Coordinates(x_, y_)).getCoord()
										.get_y() * 32 + 7));
			}
		}
	}

	protected JFrame frame_;

	Dimension dim_;

	Draw picon_, pimage_;

	JPanel pmenu_;

	JList liste_, maplist_;

	JMenuBar menubar_;

	JMenuItem load_, quit_, save_, clean_;

	JRadioButton white_, green_, brown_, blue_, none_, map_, hide_, donjbox_,
			mapbox_, persobox_, itembox_, mapradio_, minimapradio_;

	JFileChooser loadfile_, savefile_;

	JButton moveto_, new_, delete_, addcurrent_, properties_;

	// JComboBox mapcombo_;
	String color_ = "", select_ = "plain", type_ = "Map", selectmap_,
			selectminimap_;

	int x_ = 0, y_ = 0, mp_ = 0, mp2_ = 0;

	Map m_;

	Minimap current_;

	Cursor cursor_;

	File file_;

	Quest q_;
}
