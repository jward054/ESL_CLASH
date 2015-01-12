package parse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class TaggerTranslator {

	private static Map<String, String> dict = new HashMap<>();
	private static Map<String, String> wrapper = new HashMap<>();
	static{
		try {
			for(String s: Files.readAllLines(new File("tag.txt").toPath())){
				try{
					String[] sp = s.split(" ",2);
					dict.put(sp[0], sp[1]);
				//	System.out.println(sp[0]+" ==> "+sp[1]);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			for(String s: Files.readAllLines(new File("tagwapper.txt").toPath())){
				try{
					String[] sp = s.split(" ",2);
					for(String old: sp[0].split(",")){
						wrapper.put(old, sp[1]);
					}
				//	System.out.println(sp[0]+" ==> "+sp[1]);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Tag definition not found. check tag.txt and tagwapper.txt");
			e.printStackTrace();
		}
	}
	
	public static String getDefinition(String tag){
		String result = dict.get(tag);
		return result==null?"Undefined-"+tag:result;
	}
	
	public static String getSimple(String tag){
		String result = wrapper.get(tag);
		if(result ==null){
			String checkSymbol= getDefinition(tag);
			if(checkSymbol.startsWith("Undefined")){
				return "SYMBOL";
			}else{
				return "OTHER";
			}
		}
		
		return result;
	}
}
