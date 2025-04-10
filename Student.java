/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student.newpackage;

/**
 *
 * @author EDEN
 */
public class Student {
    int id;
    String name;
    double mark;
    String rank;
    Student left, right;

    Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
        this.rank = assignRank(mark);
        this.left = this.right = null;
    }

    static String assignRank(double mark) {
        return mark <= 5.0 ? "Fail" :
               mark <= 6.5 ? "Medium" :
               mark <= 7.5 ? "Good" :
               mark <= 9.0 ? "Very Good" : "Excellent";
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Mark: " + mark + ", Rank: " + rank);
    }
}
