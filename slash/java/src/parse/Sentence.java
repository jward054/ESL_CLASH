package parse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSDictionary;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerFactory;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.TagDictionary;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.StringList;

public class Sentence {
	
	private LinkedList<Entity> words;
	private Sentence() {
		words = new LinkedList<Entity>();
	}

	public Sentence(String sentence){
		this();
		parse(sentence);
	}
	private void parse(String sentence){
		String[] tokens = StaticParser.Tokenize(sentence);
		String[] tags = StaticParser.POSTagM(tokens);
		for(int i=0;i<tags.length;i++){
			String token = tokens[i];
			String tag = TaggerTranslator.getDefinition(tags[i]);
			words.add(new Entity(token, tag));
		}
			
//		for(String s: StaticParser.Tokenize(sentence)){
//			words.add(new Entity(s));
//		}

	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Entity e: words)
			sb.append(e.value()+" ");
		return sb.toString();
	}
}
