import java.text.DecimalFormatSymbols;


public class Node {
	String sign;
	Node left;
	Node right;
	Node previous;
	
	boolean leaf;
	boolean parent;
	public Node(String s){
		sign = s;
		leaf = false;
		parent = false;
		if(isStringNumeric(s)){
			leaf = true;
		}
		else parent = true;
	}
	public static boolean isStringNumeric( String str )
	{
	    DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
	    char localeMinusSign = currentLocaleSymbols.getMinusSign();

	    if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

	    boolean isDecimalSeparatorFound = false;
	    char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

	    for ( char c : str.substring( 1 ).toCharArray() )
	    {
	        if ( !Character.isDigit( c ) )
	        {
	            if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
	            {
	                isDecimalSeparatorFound = true;
	                continue;
	            }
	            return false;
	        }
	    }
	    return true;
	}
	
	public String toString(){
		return sign + " ";
	}
}
