package eu.sweetlygeek.loto.utils;


public class ImmutableSingletonMap<K, V> {
	
	private K key;
	private V value;
	
	public ImmutableSingletonMap(K key, V value)
	{
		this.key = key;
		this.value = value;
	}

	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}

}
