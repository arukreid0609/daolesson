package model;

import java.io.Serializable;

public class User implements Serializable{
	// 名前、年齢の情報
	private int id;
	private String name;
	private int age;
	
	public User() {}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public User(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	
}
