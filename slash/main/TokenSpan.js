/**
 * New node file
 */

var Tagger = require("./POSTagger.js");

function TokenSpan(){
	this.arr= [];
	this.type =Tagger.EXC;
	this.length = 0;
}

var method = TokenSpan.prototype;

method.add = function (token){
	this.arr.push(token);
	this.length++;
};

module.exports = TokenSpan;