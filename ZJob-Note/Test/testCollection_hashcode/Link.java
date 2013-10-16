package testCollection_hashcode;

public class Link<T>{
	
	Entry first;
	Entry follow;
	
	Link(){
		first = new Entry(null);
	}
	
	public void insert(T t){
		
		Entry entry = new Entry(follow);
		follow = entry;
		entry.value = t;
	}
	

	class Entry<K,V>{
		private Entry<K,V> next;
		private Entry<K,V> previous;
		private V value;
		Entry(Entry<K,V> previous){
			this.next = next;
			this.previous = previous;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		
	}
}