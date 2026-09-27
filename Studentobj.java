package Proj1;

public class Studentobj {
	int id;
	String name;
	int age;
	String city;
	Studentobj(int id,String name,int age,String city){
		this.id=id;
		this.name=name;
		this.age=age;
		this.city=city;
  }
	 @Override
	    public String toString() {
	        return "[ ID: " + id
	                + ", Name: " + name
	                + ", Age: " + age
	                + ", City: " + city +" ]";
	    }
	 
	public int getid() {
		 return id;
	 }
	public String getname() {
		 return name;
	 }
	public int getage() {
		 return age;
	 }
	public String getcity() {
		 return city;
	 }
	public void updname(String name) {
		 this.name=name;
	 }
	public void updcity(String city) {
		 this.city=city;
	 }
	public void updage(int age) {
		 this.age=age;
	 }
}
