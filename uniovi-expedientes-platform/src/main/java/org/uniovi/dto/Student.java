package org.uniovi.dto;
public class Student {
    public String uuid;
    public String id;
    public String uo;
    public String name;
    public String surname;
    public String email;

    public Student(String uuid,String id, String uo,String name, String surname, String email) {
        this.uuid = uuid;
        this.id = id;
        this.uo = uo;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Student(){}
}
