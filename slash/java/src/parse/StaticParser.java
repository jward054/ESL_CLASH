package parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class StaticParser {


	public static SentenceDetectorME sentencDetector;
	public static Tokenizer tokenizer; 
	public static POSTaggerME maxentTagger,perceptronTagger;
	public static ChunkerME chunker;
	
	public static String[] SentenceDetect(String paragraph){
		if(sentencDetector==null){
			try{
				InputStream is = new FileInputStream(new File("en-sent.bin"));
				SentenceModel model = new SentenceModel(is);
				sentencDetector = new SentenceDetectorME(model);
				is.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, 
						"Error while initialize SentenceDetector, check en-sent.bin. "+e.getMessage());
				return new String[0];
			}
		}

		return sentencDetector.sentDetect(paragraph);		
	}

	public static String[] Tokenize(String sentence){
		if(tokenizer==null){
			try{
				InputStream is = new FileInputStream(
				//		"token-train.out"
						"en-token.bin"
						);
				TokenizerModel model = new TokenizerModel(is);
//				model.
				tokenizer = new TokenizerME(model);
				
				is.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, 
						"Error while initialize Tokenizer, check en-token.bin. "+e.getMessage());
				return new String[0];
			}
		}

		return tokenizer.tokenize(sentence);
	}
	


	public static String[] POSTagM(String[] tokens){
		if(maxentTagger==null){
			try{
			POSModel model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
			maxentTagger = new POSTaggerME(model);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, 
						"Error while initialize POSTagM, check en-pos-maxent.bin. "+e.getMessage());
				return new String[0];
			}
		}
			return maxentTagger.tag(tokens);
	}
	
	public static String[] POSTagP(String[] tokens){
		if(perceptronTagger==null){
			try{
			POSModel model = new POSModelLoader().load(new File("en-pos-perceptron.bin"));
			perceptronTagger = new POSTaggerME(model);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, 
						"Error while initialize POSTagP, check en-pos-perceptron.bin. "+e.getMessage());
				return new String[0];
			}
		}
		
		return perceptronTagger.tag(tokens);
	}
	
	public static Span[] chunkSpan(String[] tokens, String[] tags) {
		if(chunker==null){
			try{
				InputStream is = new FileInputStream("en-chunker.bin");
				ChunkerModel cModel = new ChunkerModel(is);
				chunker = new ChunkerME(cModel);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, 
							"Error while initialize Chunk, check en-chunker.bin. "+e.getMessage());
					return new Span[0];
				}
		}

		return chunker.chunkAsSpans(tokens, tags);
	}
	
	public static int min_size =2;
	
	public static List<String> excList = new ArrayList<>();
	static{
		excList.add("from day to day");
	}
	public static Span[] mergeSpan(Span[] raw, int length){
		List<Span> spans = new ArrayList<Span>();
		int start= 0;
		int end = 0;
		String type="";
		Span last = null;
		boolean isStart = true;
		for(Span s: raw){
			if(isStart){
			//	start= s.getStart();
				type = s.getType();
				end = s.getEnd();
				isStart = false;
			}else{
				switch (s.getType()) {
				case "PP":
				case "VP":	
					if(last!=null&& (s.getStart()-start<min_size||(last.getEnd()-last.getStart()<min_size))){
						last = new Span(last.getStart(), s.getStart(),type);
						spans.set(spans.size()-1, last);
					}else{
						last =new Span(start,s.getStart(),type);
						spans.add(last);
					}
					start= s.getStart();
					type = s.getType();
					end = s.getEnd();
					break;

				default:

					if(s.getStart()!=end){
						{
							if(last!=null&& (s.getStart()-start<min_size||(last.getEnd()-last.getStart()<min_size))){
								last = new Span(last.getStart(), s.getStart(),type);
								spans.set(spans.size()-1, last);
							}else{
								last = new Span(start, s.getStart());
								spans.add(last);

							}
						}
						
						
						start = s.getStart();
						end = s.getEnd();
					}
					end = s.getEnd();
					break;
				}
			}
		}
		spans.add(new Span(start, length,type));
		Span[] result  = new Span[spans.size()];
		for(int i=0;i<spans.size();i++)
			result[i]=spans.get(i);
		return result;
	}
	
}
