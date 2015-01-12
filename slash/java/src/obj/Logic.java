package obj;

import java.util.List;

public class Logic {

	public static final Token slash = new Token("/",POSTag.SLASH);
	
	public static TokenStream mergeException(TokenStream input, List<TokenStream> exception){
		for(TokenStream exc: exception){
			int index = input.indexOf(exc);
			System.out.println("in: "+input);
			System.out.println("search: "+exc);
			System.out.println("index: "+index);
			if(index!=-1){
				input.merge(index, exc.size());
			}
		}
		return input;
	}
	
	
	/**
	 * 	Put a slash after each period, comma, semicolon, colon or question mark.
		Put a slash before each preposition 
		Put a slash before each conjunction.
	 * @param input
	 * @return
	 */
	public static TokenStream putSlash(TokenStream input){
		for(int i=0;i<input.size();i++){
			Token token = input.get(i);
			switch (token.getType()) {
			case Conjunction:
				input.add(i, slash);
				i++;
				break;
			case Preposition:
				input.add(i, slash);
				i++;
				break;
				
			case Exception:
				input.add(i, slash);
				i++;
				break;
			case Punctuation:
				//	Put a slash after each period, comma, semicolon, colon or question mark.
				switch(token.getVal().toString()){
					case ".":
					case ",":
					case ";":
					case ":":
					case "?":
					case "!":
						input.add(i+1,slash);
						i++;
						break;
					default:
						break;
				}
				break;
			default:
				break;
			}
			
		}
		return input;
	}

}
