package CodeJavaEx;
/**
 * 
 * @author PhamVanLinh
 *
 */
class KeyPairValue {
	private Integer key;
	private String value;

	public KeyPairValue(Integer key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
