package com.example.demo;


public class Test {

	int id;
	String name;
	
	public Test(int id,String name) {
		this.id=id;
		this.name=name;
	}
	public Test(Test t) {
		this.id=t.id;
		this.name=t.name;
	}
	public void prints(Test t) {
		System.out.println(t.id+" "+t.name);
	}
	public static void main(String[] args)  {
		Test t =new Test(111,"boss");
		t.prints(t);
		 Test t1 =new Test(t);
		 t1.prints(t1);
	}
}
