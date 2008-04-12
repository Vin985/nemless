/*
 * Created on 18 juil. 2004
 *
 */
package old.fr.studioshi.dazel.editor;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ListIterator;

import old.fr.studioshi.dazel.game.items.Block;
import old.fr.studioshi.dazel.game.items.Door;
import old.fr.studioshi.dazel.game.items.Element;
import old.fr.studioshi.dazel.game.items.Map;
import old.fr.studioshi.dazel.game.items.Minimap;
import old.fr.studioshi.dazel.game.items.Quest;
import old.fr.studioshi.dazel.game.perso.Blob;
import old.fr.studioshi.dazel.game.perso.Eye_guard;
import old.fr.studioshi.dazel.game.perso.Ganon;
import old.fr.studioshi.dazel.game.perso.Giant_spider;
import old.fr.studioshi.dazel.game.perso.Knight;
import old.fr.studioshi.dazel.game.perso.Magician;
import old.fr.studioshi.dazel.game.perso.Moblin;
import old.fr.studioshi.dazel.game.perso.Pike_bloc;
import old.fr.studioshi.dazel.game.perso.Spider;


/**
 * @author Endy
 */
public class Write {
	public Write(String s, Quest q) {
		try {
			out_ = new DataOutputStream(new FileOutputStream(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		q_ = q;
		indent_ = "";
	}

	public void decraise() {
		id_ -= 2;
		indent_ = "";
		for (int i = 0; i < id_; i++)
			indent_ = indent_ + " ";
	}

	public void raise() {
		indent_ = indent_ + "  ";
		id_ += 2;
	}

	public void init() {
		try {
			out_.writeBytes("<?xml version=\"1.0\"?>\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tag_quest() {
		ListIterator it = q_.getMaps().listIterator();

		raise();
		try {
			out_.writeBytes(indent_);
			out_.writeBytes("<quest name=\"" + q_.getName() + "\">\n");
			tag_maps();
			out_.writeBytes(indent_);
			out_.writeBytes("</quest>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		decraise();
	}

	public void tag_maps() {
		ListIterator it = q_.getMaps().listIterator();

		raise();
		while (it.hasNext()) {
			Map tmp = (Map) it.next();
			try {
				out_.writeBytes(indent_);
				out_.writeBytes("<map name=\"" + tmp.getName() + "\" l=\""
						+ tmp.getL() + "\" h=\"" + tmp.getH() + "\" startx=\""
						+ tmp.getStartx() + "\" starty=\"" + tmp.getStarty()
						+ "\">\n");
				tag_minimaps(tmp);
				out_.writeBytes(indent_);
				out_.writeBytes("</map>\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		decraise();
	}

	public void tag_minimaps(Map m) {
		ListIterator it = m.getMinis().listIterator();

		raise();
		while (it.hasNext()) {
			Minimap tmp = (Minimap) it.next();
			try {
				out_.writeBytes(indent_);
				out_.writeBytes("<minimap x=\"" + tmp.getCoord().get_x()
						+ "\" y=\"" + tmp.getCoord().get_y() + "\" light=\""
						+ tmp.getLight() + "\">\n");
				tag_decors(tmp);
				tag_elements(tmp);
				tag_monsters(tmp);
				out_.writeBytes(indent_);
				out_.writeBytes("</minimap>\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		decraise();
	}

	/**
	 * 
	 */
	private void tag_monsters(Minimap mini) {
		ListIterator it = mini.getMonsts().listIterator();

		raise();
		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			try {
				out_.writeBytes(indent_);
				if (tmp instanceof Blob)
					out_.writeBytes("<blob x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\"></blob>\n");
				if (tmp instanceof Spider)
					out_.writeBytes("<spider x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\"></spider>\n");
				if (tmp instanceof Moblin)
					out_.writeBytes("<moblin x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\"></moblin>\n");
				if (tmp instanceof Knight)
					out_.writeBytes("<knight x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\"></knight>\n");
				if (tmp instanceof Eye_guard)
					out_.writeBytes("<eye_guard x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\"></eye_guard>\n");
				if (tmp instanceof Magician) {
					Magician t = (Magician) tmp;
					out_.writeBytes("<magician x=\"" + t.getCoord().get_x()
							+ "\" y=\"" + t.getCoord().get_y() + "\" real=\""
							+ t.get_true() + "\"></magician>\n");
				}
				if (tmp instanceof Pike_bloc)
					out_.writeBytes("<pike_bloc x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\"></pike_bloc>\n");
				if (tmp instanceof Ganon) {
					Ganon g = (Ganon) tmp;
					if (g.getBot_left_() != null && g.getBot_right_() != null
							&& g.getUp_right_() != null) {
						out_.writeBytes("<ganon x=\"" + tmp.getCoord().get_x()
								+ "\" y=\"" + tmp.getCoord().get_y() + "\">\n");
						raise();
						out_.writeBytes(indent_);
						out_.writeBytes("<up_right x=\""
								+ (tmp.getCoord().get_x() + 1) + "\" y=\""
								+ tmp.getCoord().get_y() + "\"></up_right>\n");
						out_.writeBytes("<bot_left x=\""
								+ tmp.getCoord().get_x() + "\" y=\""
								+ (tmp.getCoord().get_y() - 1)
								+ "\"></bot_left>\n");
						out_.writeBytes("<bot_right x=\""
								+ (tmp.getCoord().get_x() + 1) + "\" y=\""
								+ (tmp.getCoord().get_y() - 1)
								+ "\"></bot_right>\n");
						decraise();
						out_.writeBytes(indent_);
						out_.writeBytes("</ganon>\n");
					}
				}
				if (tmp instanceof Giant_spider) {
					Giant_spider g = (Giant_spider) tmp;
					if (g.getOtherleft_() != null && g.getOtherright_() != null) {
						out_.writeBytes("<giant_spider x=\""
								+ tmp.getCoord().get_x() + "\" y=\""
								+ tmp.getCoord().get_y() + "\">\n");
						raise();
						out_.writeBytes(indent_);
						out_
								.writeBytes("<other_left x=\""
										+ (tmp.getCoord().get_x() - 1)
										+ "\" y=\"" + tmp.getCoord().get_y()
										+ "\"></other_left>\n");
						out_.writeBytes("<other_right x=\""
								+ (tmp.getCoord().get_x() + 1) + "\" y=\""
								+ tmp.getCoord().get_y()
								+ "\"></other_right>\n");
						decraise();
						out_.writeBytes(indent_);
						out_.writeBytes("</giant_spider>\n");
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		decraise();
	}

	/**
	 * 
	 */
	private void tag_elements(Minimap mini) {
		ListIterator it = mini.getElts().listIterator();

		raise();
		while (it.hasNext()) {
			Element tmp = (Element) it.next();
			try {
				out_.writeBytes(indent_);
				out_.writeBytes("<element x=\"" + tmp.getCoord().get_x()
						+ "\" y=\"" + tmp.getCoord().get_y() + "\" obstacle=\""
						+ tmp.is_obstacle() + "\">" + tmp.getType()
						+ "</element>\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		decraise();
	}

	/**
	 * 
	 */
	private void tag_decors(Minimap mini) {
		ListIterator it = mini.getDecors().listIterator();

		raise();
		while (it.hasNext()) {
			Element tmp = (Element) it.next();

			try {
				out_.writeBytes(indent_);
				if (tmp.getType().equals("entrance")
						|| tmp.getType().startsWith("stairs"))
					out_.writeBytes("<decor x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\" obstacle=\"" + tmp.is_obstacle()
							+ "\" type=\"" + tmp.getType() + "\" link=\""
							+ tmp.getLink() + "\">\n");
				else if (tmp instanceof Door) {
					Door d = (Door) tmp;
					out_.writeBytes("<door x=\"" + d.getCoord().get_x()
							+ "\" y=\"" + d.getCoord().get_y()
							+ "\" obstacle=\"" + d.is_obstacle() + "\" type=\""
							+ d.getType() + "\" open=\"" + d.getOpen()
							+ "\">\n");
				} else if (tmp instanceof Block) {
					Block b = (Block) tmp;
					out_.writeBytes("<block x=\"" + b.getCoord().get_x()
							+ "\" y=\"" + b.getCoord().get_y()
							+ "\" obstacle=\"" + b.is_obstacle() + "\" type=\""
							+ b.getType() + "\" moveable=\"" + b.getMoveable_()
							+ "\">\n");
				} else
					out_.writeBytes("<decor x=\"" + tmp.getCoord().get_x()
							+ "\" y=\"" + tmp.getCoord().get_y()
							+ "\" obstacle=\"" + tmp.is_obstacle()
							+ "\" type=\"" + tmp.getType() + "\">\n");
				raise();
				out_.writeBytes(indent_);
				if (tmp.getHide().getType().equals("entrance")
						|| tmp.getType().equals("stairs_green")
						|| tmp.getType().equals("stairs_white")
						|| tmp.getType().equals("stairs_brown"))
					out_.writeBytes("<hide x=\""
							+ tmp.getHide().getCoord().get_x() + "\" y=\""
							+ tmp.getHide().getCoord().get_y()
							+ "\" obstacle=\"" + tmp.getHide().is_obstacle()
							+ "\">" + tmp.getHide().getType() + "\" link=\""
							+ tmp.getLink() + "</hide>\n");
				else
					out_.writeBytes("<hide x=\""
							+ tmp.getHide().getCoord().get_x() + "\" y=\""
							+ tmp.getHide().getCoord().get_y()
							+ "\" obstacle=\"" + tmp.getHide().is_obstacle()
							+ "\">" + tmp.getHide().getType() + "</hide>\n");
				decraise();
				out_.writeBytes(indent_);
				if (tmp instanceof Block)
					out_.writeBytes("</block>\n");
				else if (tmp instanceof Door)
					out_.writeBytes("</door>\n");
				else
					out_.writeBytes("</decor>\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		decraise();
	}

	protected Quest q_;

	DataOutputStream out_;

	String indent_;

	int id_;

	String tag_;
}
