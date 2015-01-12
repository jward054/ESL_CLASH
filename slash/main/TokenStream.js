
var Token = require("./Token");

/**
 * Stream of tokens,
 * token can be add via TokenStream.add(token).
 */
function TokenStream(){
	this.arr = [];
	this.pos = 0;
}

/**
 * add new token to the end
 */
TokenStream.prototype.add = function(word,type){
	this.arr.push(new Token(word, type));
};

/**
 * add new token at index pos
 */
TokenStream.prototype.insert= function(word,type,pos){
	this.arr.splice(pos, 0, new Token(word,type));
};


/**
 * return true if TokenStream have more token.
 */
TokenStream.prototype.hasNext = function(){
	return this.pos<this.arr.length;
};

/**
 * Code shared by String and StringBuffer in Java to do searches. The
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
TokenStream.prototype.indexOf = function(stream){
	var cast = 
    if (stream.arr. == 0) {
        return fromIndex;
    }

    char first = target[targetOffset];
    int max = sourceOffset + (sourceCount - targetCount);

    for (int i = sourceOffset + fromIndex; i <= max; i++) {
        /* Look for first character. */
        if (source[i] != first) {
            while (++i <= max && source[i] != first);
        }

        /* Found first character, now look at the rest of v2 */
        if (i <= max) {
            int j = i + 1;
            int end = j + targetCount - 1;
            for (int k = targetOffset + 1; j < end && source[j]
                    == target[k]; j++, k++);

            if (j == end) {
                /* Found whole string. */
                return i - sourceOffset;
            }
        }
    }
    return -1;
}
/**
 *  return the next token.
 */
TokenStream.prototype.next = function(){
	return this.arr[this.pos++];
};

TokenStream.prototype.peek = function(){
	if(this.hasNext()){
		return this.arr[this.pos];
	}
	return null;
};

/**
 * reset iterator position to 0;
 */
TokenStream.prototype.resetPos = function(){
	this.pos = 0;
};


module.exports = TokenStream;
