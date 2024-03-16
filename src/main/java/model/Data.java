package model;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{
	private List<User> results;
	
	public Data() {}
	public Data(List<User> results) {
		this.results = results;
	}
	public List<User> getResults() {
		return results;
	}
}
