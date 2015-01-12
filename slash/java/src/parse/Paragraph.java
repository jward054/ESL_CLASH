package parse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import obj.Logic;
import obj.POSTag;
import obj.Token;
import obj.TokenStream;
import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.util.Span;

public class Paragraph {

	
	private List<Sentence> sentence;
	private Paragraph() {
		sentence = new ArrayList<>();
	}
	
	public Paragraph(String para){
		this();
		parse(para);
	}
	
	private void parse(String para) {
		for(String s: StaticParser.SentenceDetect(para)){
			sentence.add(new Sentence(s));
		}
	}

	/**
	 * @return the sentence
	 */
	public List<Sentence> getSentence() {
		return sentence;
	}
	/**
	 * @param sentence the sentence to set
	 */
	public void setSentence(List<Sentence> sentence) {
		
	}
	
	public static void main(String[] args) throws IOException{
		List<TokenStream> exc = new ArrayList<>();
		exc.add(new TokenStream("from day to day", " "));
		for(String s: Files.readAllLines(new File("sample.txt").toPath())){
			for(String sentence: StaticParser.SentenceDetect(s)){
				String[] tokens = StaticParser.Tokenize(sentence);
				List<String> d_tokens = tokenize(tokens);
				System.out.println(sentence);

				String[] sp = StaticParser.POSTagM(tokens);
				TokenStream stream = new TokenStream();
				for(int i= 0;i<sp.length;i++){
					
					String tmp = TaggerTranslator.getSimple(sp[i]);
					switch (tmp) {
						case "PREP":
							stream.add(new Token(tokens[i],POSTag.Preposition));
							break;
						case "ADJ":
							stream.add(new Token(tokens[i],POSTag.Adjective));
							break;
						case "NOUN":
						case "PNOUN":
							stream.add(new Token(tokens[i],POSTag.Noun));
							break;
						case "ADV":
							stream.add(new Token(tokens[i],POSTag.Adverb));
							break;
						case "CONJ":
							stream.add(new Token(tokens[i],POSTag.Conjunction));
							break;
						case "VERB":
							stream.add(new Token(tokens[i],POSTag.Verb));
							break;
						case "SYMBOL":
							stream.add(new Token(tokens[i],POSTag.Punctuation));
							break;
							default:
								stream.add(new Token(tokens[i],POSTag.OTHER));
								break;
					}
					//System.out.print("["+tokens[i]+" | "+TaggerTranslator.getSimple(sp[i])+"] ");
				}
				Logic.mergeException(stream, exc);
				Logic.putSlash(stream);
				System.out.println(stream.toFormateString());
				System.out.println(stream+"\n");
			}
		}
		
	}
	//. for abbreviation  ' for ownership 
	public static final String PUNCUATION = ".,<>/?;:\'’\"“”[]{}()\\";
	public static List<String> tokenize(String[] vals){
		List<String> val = merge(vals);
		List<String> tmp = new ArrayList<>();
		for(int i = 0; i< val.size();i++){
			String str = val.get(i);
			if(str.length()>1){

				String start = str.substring(0,1);
				while(PUNCUATION.indexOf(start)!=-1){
					tmp.add(start);
					str = str.substring(1);
					start = str.substring(0,1);
				}
				String end = str.substring(str.length()-1);
				int current = tmp.size();
				boolean chunk = true;
				while(PUNCUATION.indexOf(end)!=-1&&chunk){
					switch (end) {
						case ".":
							if(i == val.size()-1){
								chunk= true;
							}else 
								chunk = false;
							break;
						case "\'":
							if(str.charAt(str.length()-2)=='s')
								chunk= false;
							break;
						default:
							break;
					}
					if(chunk){
						tmp.add(end);
						str = str.substring(0,str.length()-1);
						end = str.substring(str.length()-1);
					}
				}
				tmp.add(current, str);
			}else{
				tmp.add(str);
			}
		}
		return tmp;
				
	}

	private static List<String> merge(String[] val) {
		List<String> result = new ArrayList<>();
		for(int i = 0;i<val.length;i++){
			String s = val[i];
			if(s.equalsIgnoreCase("'s")||s.equalsIgnoreCase("’s")|| (s.startsWith("-")&&!s.startsWith("--"))){
				result.add(result.remove(result.size()-1)+s);
			}else{
				result.add(s);
			}
		}
		return result;
	}

}
