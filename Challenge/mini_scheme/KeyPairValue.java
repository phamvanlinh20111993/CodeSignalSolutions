package Challenge.mini_scheme;

public class KeyPairValue<K, V> {
	K key;
	V value;
	
	public KeyPairValue(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "KeyPairValue [key=" + key + ", value=" + value + "]";
	}
}
