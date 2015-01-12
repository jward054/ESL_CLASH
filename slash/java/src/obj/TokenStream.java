package obj;

import java.util.ArrayList;
import java.util.List;

public class TokenStream extends ArrayList<Token>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874349327460526097L;
	
	/**
	 * for exception list only.
	 * @param sentence
	 * @param deli
	 */
	public TokenStream(String sentence, String deli){
		super();
		for(String sub: sentence.split(deli))
			this.add(new Token(sub,POSTag.OTHER));
	}
	
	public TokenStream() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public TokenStream(List<Token> other) {
		// TODO Auto-generated constructor stub
		super(other);
	}

	/**
	 * 
	 * copy from String.java with few modification, 
	 * 
     * Code shared by String and StringBuffer to do searches. The
     * source is the character array being searched, and the target
     * is the string being searched for.
     *
     * @param   source       the characters being searched.
     * @param   sourceOffset offset of the source string.
     * @param   sourceCount  count of the source string.
     * @param   target       the characters being searched for.
     * @param   targetOffset offset of the target string.
     * @param   targetCount  count of the target string.
     * @param   fromIndex    the index to begin searching from.
     */
    static int indexOf(TokenStream source, int sourceOffset, int sourceCount,
    		TokenStream target, int targetOffset, int targetCount,
            int fromIndex) {
        if (fromIndex >= sourceCount) {
            return (targetCount == 0 ? sourceCount : -1);
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (targetCount == 0) {
            return fromIndex;
        }

        Token first = target.get(targetOffset);
        int max = sourceOffset + (sourceCount - targetCount);

        for (int i = sourceOffset + fromIndex; i <= max; i++) {
            /* Look for first character. */
            if (!source.get(i).equals(first)) {
                while (++i <= max && !source.get(i).equals(first));
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && 
                		source.get(j).equals(target.get(k)); j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i - sourceOffset;
                }
            }
        }
        return -1;
    }
	
	public int indexOf(TokenStream target) {
        return indexOf(this, 0, this.size(), target, 0, target.size(), 0);
    }
	
	public void merge(int from, int length){
		TokenStream subStream = new TokenStream(subList(from, from+length));
		this.removeRange(from, from+length);
		this.add(from,new Token(subStream));
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Token t: this){
			sb.append(t.toString()).append(" ");
		}
		return sb.substring(0,sb.length()-1);
	}

	public String toFormateString(){
		StringBuilder sb = new StringBuilder();
		for(Token t: this){
			sb.append("[ ").append(t.toString()).append(" ,").append(t.getType()).append("] ");
		}
		return sb.substring(0,sb.length()-1);
	}
}
