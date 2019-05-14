package nl;

/**
 * Holds a String Number that can be added and multiplied
 * @author Nathan Giusti
 *
 */
public class StringNumber {
	
	private String value;

	/**
	 * Constructor for StringNumber object
	 * 
	 * @param value: The string value of the string number
	 */
	public StringNumber(String value) {
		this.setValue(value);

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        StringNumber sn = (StringNumber) obj;
        
		return this.value.equals(sn.value);
	}
	
	/**
	 * 
	 * @return The length of the value string
	 */
	public int getLength() {
		return value.length();
	}
	
	/**
	 * 
	 * @param i
	 * @return The character in the value string at position i
	 */
	public char charAt(int i) {
		return value.charAt(i);
	}
	
	
}