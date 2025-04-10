/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student.newpackage;

import java.util.Scanner;

/**
 *
 * @author EDEN
 */
import java.util.Scanner;

class StudentManager {
    static Student[] students = new Student[100];
    static int size = 0;
    static Scanner scanner = new Scanner(System.in);
    static Student root = null;

     static int inputId() {
        while (true) {
            try {
                System.out.print("Nhap ID: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Loi: ID phai la so nguyen!");
            }
        }
    }


    static String inputName() {
        while (true) {
            System.out.print("Nhap ten: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) return name;
            System.out.println("Loi: Ten khong duoc de trong!");
        }
    }

    static double inputMark() {
        while (true) {
            try {
                System.out.print("Nhap diem: ");
                double mark = Double.parseDouble(scanner.nextLine());
                if (mark < 0 || mark > 10) throw new IllegalArgumentException();
                return mark;
            } catch (Exception e) {
                System.out.println("Loi: Diem phai la so thuc tu 0 den 10!");
            }
        }
    }

    static void addStudent() {
        int id = inputId();
        String name = inputName();
        double mark = inputMark();

        Student newStudent = new Student(id, name, mark);
        students[size++] = newStudent;
        root = insertBST(root, newStudent);
        System.out.println("✅ Da them sinh vien.");
    }

    static Student insertBST(Student root, Student newNode) {
        if (root == null) return newNode;
        if (newNode.mark < root.mark) root.left = insertBST(root.left, newNode);
        else root.right = insertBST(root.right, newNode);
        return root;
    }

    static void displayStudents() {
        if (size == 0) {
            System.out.println("⚠️ Khong co sinh vien nao.");
            return;
        }
        for (int i = 0; i < size; i++) students[i].display();
    }

    static void bubbleSort() {
        if (size < 2) return;
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (students[j].mark > students[j + 1].mark) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
        System.out.println("✅ Da sap xep.");
    }

    static void searchStudent() {
        try {
            System.out.print("Nhap diem can tim: ");
            double target = Double.parseDouble(scanner.nextLine());
            for (int i = 0; i < size; i++)
                if (students[i].mark == target) {
                    students[i].display();
                    return;
                }
            System.out.println("❌ Khong tim thay.");
        } catch (Exception e) {
            System.out.println("Loi: Diem nhap khong hop le!");
        }
    }

    static void traverseBST() {
        System.out.println("\n📌 Pre-order:");
        traverse(root, 1);
        System.out.println("\n📌 In-order:");
        traverse(root, 2);
        System.out.println("\n📌 Post-order:");
        traverse(root, 3);
    }

    static void traverse(Student root, int order) {
        if (root == null) return;
        if (order == 1) root.display();
        traverse(root.left, order);
        if (order == 2) root.display();
        traverse(root.right, order);
        if (order == 3) root.display();
    }

    // 🛠 Chức năng sửa thông tin sinh viên
    static void editStudent() {
        int id = inputId();
        for (int i = 0; i < size; i++) {
            if (students[i].id == id) {
                System.out.println("Sinh vien hien tai:");
                students[i].display();
                
                System.out.println("Nhap ten moi (de trong neu khong doi): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) students[i].name = newName;

                System.out.println("Nhap diem moi (-1 neu khong doi): ");
                try {
                    double newMark = Double.parseDouble(scanner.nextLine());
                    if (newMark >= 0 && newMark <= 10) {
                        students[i].mark = newMark;
                        students[i].rank = Student.assignRank(newMark);
                    }
                } catch (Exception ignored) {}

                System.out.println("✅ Da cap nhat sinh vien.");
                return;
            }
        }
        System.out.println("❌ Khong tim thay sinh vien!");
    }

    // 🛠 Chức năng xóa sinh viên
    static void deleteStudent() {
        int id = inputId();
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (students[i].id == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("❌ Khong tim thay sinh vien!");
            return;
        }

        // Xóa khỏi mảng
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        students[--size] = null;

        // Xóa khỏi BST
        root = deleteFromBST(root, id);

        System.out.println("✅ Da xoa sinh vien.");
    }

    static Student deleteFromBST(Student root, int id) {
        if (root == null) return null;
        if (id < root.id) root.left = deleteFromBST(root.left, id);
        else if (id > root.id) root.right = deleteFromBST(root.right, id);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
        }
        return root;
    }

    public static void main(String[] args) {
    while (true) {
        System.out.println("\n📌 QUAN LY SINH VIEN");
        System.out.println("1. Them sinh vien");
        System.out.println("2. Sua sinh vien");       // 🔄 Đổi lên vị trí số 2
        System.out.println("3. Xoa sinh vien");       // 🔄 Đổi lên vị trí số 3
        System.out.println("4. Tim kiem theo diem");
        System.out.println("5. Duyet BST (Pre, In, Post)");
        System.out.println("6. Hien thi danh sach");  // 🔄 Dời xuống vị trí số 6
        System.out.println("7. Sap xep theo diem");   // 🔄 Dời xuống vị trí số 7
        System.out.println("8. Thoat");
        System.out.print("Chon: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();  // 🔄 Đổi chỗ với hiển thị danh sách
                case 3 -> deleteStudent();  // 🔄 Đổi chỗ với sắp xếp
                case 4 -> searchStudent();
                case 5 -> traverseBST();
                case 6 -> displayStudents();  // 🔄 Dời xuống
                case 7 -> { bubbleSort(); displayStudents(); }  // 🔄 Dời xuống
                case 8 -> { System.out.println("Thoat."); return; }
                default -> System.out.println("Chon tu 1 den 8.");
            }
        } catch (Exception e) {
            System.out.println("Loi: Vui long nhap so tu 1 den 8!");
        }
    }
}
    
}




