package parse;

public class Entity {
	public static final String PUNCUATION=" ,./;[]()<>?:\"\'{}\\-";
	private String value;
	private String type;
	
	public Entity(String v,String type){
		value = v;
		this.type= type;
	}
	
	public Entity(char charAt,String type) {
		this(""+charAt,type);
	}

	public  String value(){
		return value+"("+type+")";
	}

}
