package obj;

import java.util.List;

public class Token {
	private Object val;
	private POSTag type;
	public Token(String word, POSTag type) {
		if(type == null || type == POSTag.Exception)
			throw new IllegalArgumentException("Single word cannot be exception : "+word);
		this.val = word.trim();
		this.type = type;
	}
	
	public Token(TokenStream tokens){
		if(tokens == null)
			val = new TokenStream();
		else
			val = tokens;
		type = POSTag.Exception;
	}
	
	public int size(){
		if(type == POSTag.Exception)
			return ((List<?>)val).size();
		return 1;
	}

	public String toString(){
		if(type == POSTag.Exception){
			StringBuilder sb = new StringBuilder();
			for(Token token : ((List<Token>)val)){
				sb.append(token.toString()).append(" ");
			}
			return sb.substring(0,	sb.length()-1);// remove trailing space
		}else{
			return val.toString();
		}
	}
	
	
	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
            return true;
        }
        if (anObject instanceof Token) {
        	Token otherToken = (Token)anObject;
            int n = size();
            if(n== otherToken.size()){
            	if(n==1){ // single String
            		return ((String)val).equalsIgnoreCase((String)otherToken.val);
            	}else{
            		return ((TokenStream)val).equals((TokenStream)otherToken.val);
            	}
            }
        }
        return false;
	}

	/**
	 * @return the val
	 */
	public Object getVal() {
		return val;
	}

	/**
	 * @return the type
	 */
	public POSTag getType() {
		return type;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(Object val) {
		this.val = val;
	}

}
