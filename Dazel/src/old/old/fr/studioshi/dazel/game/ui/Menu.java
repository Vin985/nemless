/*
 * Created on 3 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package old.fr.studioshi.dazel.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import old.fr.studioshi.dazel.editor.Parse;
import old.fr.studioshi.dazel.game.items.Map;
import old.fr.studioshi.dazel.game.items.Quest;

import fr.studioshi.common.video.GameWindow;
import fr.studioshi.dazel.game.sound.PlayThread;
import fr.studioshi.dazel.game.sound.SoundList;

/**
 * @author Propriétaire
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class Menu extends Interface {

	private Game_screen game_;

	private int position_;

	/**
	 * 
	 */
	public Menu(JFrame fr, Game_screen game, SoundList sl, Options opt) {
		super(fr);
		PlayThread t;
		position_ = 0;
		soundList_ = sl;
		setBackground(Color.black);
		setForeground(Color.white);
		if (opt == null)
			option_ = new Options();
		else
			option_ = opt;
		if (soundList_ == null)
			startLoadingSounds();
		p = new PrintThread();
		game_ = game;
		p.start();
		if (option_.isSound_on_())
			t = new PlayThread(this, 1);
	}

	/**
	 * @return
	 */
	public int get_pos() {
		return position_;
	}

	/**
	 * @return Returns the game_.
	 */
	public Game_screen getGame_() {
		return this.game_;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			key_down_pressed_ = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			key_up_pressed_ = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (position_ == 0)
				new_game();
			if (position_ == 4)
				quit();
			if (position_ == 3)
				options();
			if (position_ == 2 && game_ != null)
				save();
			if (position_ == 1)
				load();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent arg0) {
	}

	/**
	 * 
	 */
	private void load() {
		Load_screen load = new Load_screen(frame_, this);
		load.soundList_ = soundList_;
		load.option_ = option_;
		frame_.setContentPane(load);
		frame_.removeKeyListener(this);
		frame_.addKeyListener(load);
		frame_.validate();
	}

	/**
	 * 
	 */
	public void move_cursor() {

		if (key_down_pressed_) {
			if (position_ < 4)
				position_++;
			else
				position_ = 0;
			key_down_pressed_ = false;
		}
		if (key_up_pressed_) {
			if (position_ > 0)
				position_--;
			else
				position_ = 4;
			key_up_pressed_ = false;
		}
	}

	/**
	 * 
	 */
	public void new_game() {
		if (game_ == null) {
			// final Map map = new Map("map", 3, 3, 1, 1, null);
			Quest q = null;
			Parse p = new Parse("resources/quests/quest.xml");
			p.parse();
			q = p.getQuest();
			Map map = q.getMap("main");
			final Game_screen game = new Game_screen(frame_, map, option_,
					soundList_);
			game_ = game;
			game_.setQuest_(q);
		}
		frame_.setContentPane(game_);
		frame_.removeKeyListener(this);
		frame_.addKeyListener(game_);
		frame_.validate();
		if (audio_ != null)
			audio_.stop();
		if (option_.isSound_on_()) {
			PlayThread t = new PlayThread(game_, 3);
		}
		p.setDone_(true);
	}

	/**
	 * 
	 */
	private void options() {
		Options_screen op = new Options_screen(frame_, option_, this);
		frame_.setContentPane(op);
		frame_.removeKeyListener(this);
		frame_.addKeyListener(op);
		frame_.validate();
	}

	/**
	 * 
	 */
	public void print() {
		if (soundList_.getClip(songs_[0]) == null
				|| soundList_.getClip(songs_[1]) == null
				|| soundList_.getClip(songs_[2]) == null
				|| soundList_.getClip(songs_[3]) == null
				|| soundList_.getClip(songs_[4]) == null) {
			g2_.drawString("Loading...", 6 * 16 + 8, 7 * 16 + 8);
			return;
		}
		move_cursor();
		String new_game = "New Game";
		String load = "Load";
		String save = "Save";
		String options = "Options";
		String exit = "Quit";
		String restore = "Restore Game";
		this.setFont_(new Font("Arial", Font.BOLD, 15));
		g2_.setFont(this.getFont_());

		if (game_ == null)
			g2_.drawString(new_game, 110, 120);
		else
			g2_.drawString(restore, 110, 120);
		g2_.drawString(load, 110, 140);
		if (game_ != null)
			g2_.drawString(save, 110, 160);
		else
			g2_.drawString("# Save #", 110, 160);
		g2_.drawString(options, 110, 180);
		g2_.drawString(exit, 110, 200);
		if (position_ < 6)
			g2_.drawImage(
					getDemoImage("/resources/sprites/items/triforce_menu.png", this), 80,
					110 + 20 * position_, this);
	}

	/**
	 * 
	 */
	public void quit() {
		p.setDone_(true);
		frame_.dispose();
		System.exit(0);
	}

	public void save() {
		Save_screen sav = new Save_screen(frame_, this, game_);
		frame_.setContentPane(sav);
		frame_.removeKeyListener(this);
		frame_.addKeyListener(sav);
		frame_.validate();
	}

	/**
	 * @param pos
	 */
	public void set_pos(int pos) {
		position_ = pos;
	}

	/**
	 * @param game_
	 *            The game_ to set.
	 */
	public void setGame_(Game_screen game_) {
		this.game_ = game_;
	}
}
