package edu.calstatela;
public class Student{
    private int id;
    private String name;
    private int cin;

    public Student(){
    }
    public Student(int id,String name,int cin){
        this.id=id;
        this.name=name;
        this.cin=cin;
    }
    public int getId(){
        return this.id;
    }
    public int getCin(){
        return this.cin;
    }
    public String getName(){
        return this.name;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setCin(int cin){
        this.cin=cin;
    }
    public void setName(String name){
        this.name=name;
    }
}



