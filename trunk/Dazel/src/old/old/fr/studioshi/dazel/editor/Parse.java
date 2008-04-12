/*
 * Created on 2 juil. 2004
 */
 
package old.fr.studioshi.dazel.editor;

import java.io.IOException;

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

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;



/**
 * @author Endy
 */
public class Parse
{
	public Parse(String s)
	{
		s_ = s;
	}
	
	public void parse()
	{
		Digester digester = new Digester();
		digester.setValidating(false);		

		digester.addObjectCreate("quest", Quest.class);
		digester.addSetProperties("quest");
		
		digester.addObjectCreate("quest/map", Map.class);
		digester.addSetProperties("quest/map");
		
		digester.addObjectCreate("quest/map/minimap", Minimap.class);
		digester.addSetProperties("quest/map/minimap");	
		
		digester.addObjectCreate("quest/map/minimap/decor", Element.class);
		digester.addSetProperties("quest/map/minimap/decor");
		
		digester.addObjectCreate("quest/map/minimap/decor/hide", Element.class);
		digester.addSetProperties("quest/map/minimap/decor/hide");
		digester.addCallMethod("quest/map/minimap/decor/hide", "setType", 0);
		digester.addSetNext("quest/map/minimap/decor/hide", "setHide");
		
		digester.addSetNext("quest/map/minimap/decor", "setDecor");
		
		digester.addObjectCreate("quest/map/minimap/block", Block.class);
		digester.addSetProperties("quest/map/minimap/block");
		
		digester.addObjectCreate("quest/map/minimap/block/hide", Element.class);
		digester.addSetProperties("quest/map/minimap/block/hide");
		digester.addCallMethod("quest/map/minimap/block/hide", "setType", 0);
		digester.addSetNext("quest/map/minimap/block/hide", "setHide");
		
		digester.addSetNext("quest/map/minimap/block", "setDecor");
		
		digester.addObjectCreate("quest/map/minimap/door", Door.class);
		digester.addSetProperties("quest/map/minimap/door");
		
		digester.addObjectCreate("quest/map/minimap/door/hide", Element.class);
		digester.addSetProperties("quest/map/minimap/door/hide");
		digester.addCallMethod("quest/map/minimap/door/hide", "setType", 0);
		digester.addSetNext("quest/map/minimap/door/hide", "setHide");
		
		digester.addSetNext("quest/map/minimap/door", "setDecor");
		
		digester.addObjectCreate("quest/map/minimap/element", Element.class);
		digester.addSetProperties("quest/map/minimap/element");
		digester.addCallMethod("quest/map/minimap/element", "setType", 0);
		digester.addSetNext("quest/map/minimap/element", "setElement");
		
		digester.addObjectCreate("quest/map/minimap/spider", Spider.class);
		digester.addSetProperties("quest/map/minimap/spider");
		digester.addSetNext("quest/map/minimap/spider", "setMonsters_");
		
		digester.addObjectCreate("quest/map/minimap/knight", Knight.class);
		digester.addSetProperties("quest/map/minimap/knight");
		digester.addSetNext("quest/map/minimap/knight", "setMonsters_");
		
		digester.addObjectCreate("quest/map/minimap/moblin", Moblin.class);
		digester.addSetProperties("quest/map/minimap/moblin");
		digester.addSetNext("quest/map/minimap/moblin", "setMonsters_");
		
		digester.addObjectCreate("quest/map/minimap/blob", Blob.class);
		digester.addSetProperties("quest/map/minimap/blob");
		digester.addSetNext("quest/map/minimap/blob", "setMonsters_");

		digester.addObjectCreate("quest/map/minimap/magician", Magician.class);
		digester.addSetProperties("quest/map/minimap/magician");
		digester.addSetNext("quest/map/minimap/magician", "setMonsters_");
		
		digester.addObjectCreate("quest/map/minimap/eye_guard", Eye_guard.class);
		digester.addSetProperties("quest/map/minimap/eye_guard");
		digester.addSetNext("quest/map/minimap/eye_guard", "setMonsters_");

		digester.addObjectCreate("quest/map/minimap/pike_bloc", Pike_bloc.class);
		digester.addSetProperties("quest/map/minimap/pike_bloc");
		digester.addSetNext("quest/map/minimap/pike_bloc", "setMonsters_");

		digester.addObjectCreate("quest/map/minimap/giant_spider", Giant_spider.class);
		digester.addSetProperties("quest/map/minimap/giant_spider");

		digester.addObjectCreate("quest/map/minimap/giant_spider/otherleft", Giant_spider.class);
		digester.addSetProperties("quest/map/minimap/giant_spider/otherleft");
		digester.addSetNext("quest/map/minimap/giant_spider/otherleft", "setOtherleft");
		digester.addObjectCreate("quest/map/minimap/giant_spider/otherright", Giant_spider.class);
		digester.addSetProperties("quest/map/minimap/giant_spider/otherright");
		digester.addSetNext("quest/map/minimap/giant_spider/otherright", "setOtherright");

		digester.addSetNext("quest/map/minimap/giant_spider", "setMonsters_");

		digester.addObjectCreate("quest/map/minimap/ganon", Ganon.class);
		digester.addSetProperties("quest/map/minimap/ganon");
		
		digester.addObjectCreate("quest/map/minimap/ganon/up_right", Ganon.class);
		digester.addSetProperties("quest/map/minimap/up_right");
		digester.addSetNext("quest/map/minimap/ganon/up_right", "setUp_right");
		digester.addObjectCreate("quest/map/minimap/ganon/bot_left", Ganon.class);
		digester.addSetProperties("quest/map/minimap/bot_left");
		digester.addSetNext("quest/map/minimap/ganon/bot_left", "setBot_left");
		digester.addObjectCreate("quest/map/minimap/ganon/bot_right", Ganon.class);
		digester.addSetProperties("quest/map/minimap/bot_right");
		digester.addSetNext("quest/map/minimap/ganon/bot_right", "setBot_right");
		
		digester.addSetNext("quest/map/minimap/ganon", "setMonsters_");
				
		digester.addSetNext("quest/map/minimap", "setMinimap");		
		digester.addSetNext("quest/map", "setMap");
		
		try
		{
			q_ = (Quest) digester.parse(s_);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Quest getQuest()
	{
		return q_;
	}
	
	protected
		Map		m_;
		Quest	q_;
		String	s_;
}
