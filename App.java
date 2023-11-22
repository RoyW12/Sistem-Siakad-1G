
import java.util.Scanner;

public class App {
    static Scanner userInput = new Scanner(System.in);
    static String[][] userAdmin = { { "admin1", "admin1" }, { "admin2", "admin2" }, { "admin3", "admin3" } };
    static String[][] userMahasiswa = { { "mahasiswa1", "mahasiswa1" }, { "mahasiswa2", "mahasiswa2" },
            { "mahasiswa3", "mahasiswa3" } };
    static String[][] students = {
            { "1234560001", "Roy wijaya", "1A", "Teknik Informatika", "L" },
            { "1234560002", "Jessica Wongso", "1B", "Teknik Informatika", "P" },
            { "1234560003", "Joko Widodo", "1C", "Teknik Informatika", "L" },
            { "1234560004", "Ganjar Pranowo", "1D", "Teknik Informatika", "L" },
            { "1234560005", "Megawati", "1E", "Manajemen", "P" }
    };

    static String[][] course = {
            { "RTI231001", "Pancasila", "2" },
            { "RTI231002", "Konsep Teknologi Informasi", "2" },
            { "RTI231003", "Critical Thinking dan Problem Solving", "2" }
    };

    static String[][][] grades = new String[999][course.length][3];

    public static void main(String[] args) {

        loginView();
    }

    static void loginView() {
        while (true) {
            renderString("SISTEM SIAKAD");
            renderString("1. admin");
            renderString("2. mahasiswa");
            renderString("0. keluar");
            renderString("Pilih login sebagai => 1.admin 2.mahasiswa atau keluar pilih 0");
            int choice = getUserChoiceInt();

            switch (choice) {
                case 1:
                    login(choice);
                    clearConsole();
                    break;
                case 2:
                    login(choice);
                    clearConsole();
                    break;
                case 0:
                    clearConsole();
                    System.exit(choice);
                    break;
                default:
                    System.out.println("user can't be found");
                    continue;
            }
            break;
        }
    }

    static boolean checkUsernamePassword(String username, String password, String[][] userArray) {
        boolean isLoggedIn = true;
        for (int i = 0; i < userArray.length; i++) {
            if (username.equals(userArray[i][0]) && password.equals(userArray[i][1])) {
                isLoggedIn = false;
                clearConsole();
                return true;
            }
        }
        if (isLoggedIn) {
            clearConsole();
            System.out.println("incorrect username and password ");

        }
        return false;
    }

    static boolean adminLogin(String username, String password) {
        return checkUsernamePassword(username, password, userAdmin);
    }

    static boolean mahasiswaLogin(String username, String password) {
        return checkUsernamePassword(username, password, userMahasiswa);
    }

    static void login(int choice) {
        while (true) {
            boolean isBreak = false;
            System.out.println("masukkan username dan password");
            System.out.print("username:");
            String username = userInput.nextLine().trim(); // trim menghapus spasi di awal dan akhir string
            System.out.print("password:");
            String password = userInput.nextLine().trim(); // trim menghapus spasi di awal dan akhir string

            if (choice == 1) {
                isBreak = adminLogin(username, password);
                if (isBreak) {
                    dashboardAdmin(username);
                }
            } else if (choice == 2) {
                isBreak = mahasiswaLogin(username, password);
                if (isBreak) {
                    dashboardMahasiswa(username);
                }
            }

        }
    }

    static void dashboardAdmin(String user) {
        renderString("Selamat Datang " + user);
        while (true) {
            renderString("=== Dashboard Admin ===");
            renderString("1. Input data mahasiswa ke master");
            renderString("2. Update data mahasiswa");
            renderString("3. Input data mata kuliah ke master");
            renderString("4. Input nilai");
            renderString("5. Pencarian Mahasiswa");
            renderString("6. Logout");
            renderString("0. Keluar");
            System.out.print("pilih fitur: ");
            int choice = getUserChoiceInt();
            switch (choice) {
                case 1:
                    clearConsole();
                    inputDataMahasiswa();
                    break;
                case 2:
                    clearConsole();
                    updateDataMahasiswa();
                    break;
                case 3:
                    clearConsole();
                    inputDataMatkul();
                    break;
                case 4:
                    clearConsole();
                    inputNilai();
                    break;
                case 5:
                    clearConsole();
                    pencarian();
                    break;
                case 6:
                    clearConsole();
                    loginView();
                    break;
                case 0:
                    System.exit(choice);
                default:
                    clearConsole();
                    renderString("Fitur tidak tersedia");
                    renderString("press enter to continue...");
                    userInput.nextLine().trim();
                    clearConsole();
                    continue;
            }
        }
    }

    static void inputDataMahasiswa() {
        String nim, fullName, classPlacement, studyProgram, sex;
        boolean isFind = false;
        renderString("press enter to continue...");
        userInput.nextLine().trim();
        clearConsole();
        renderStudentsTable("Data's Student", students);
        while (!isFind) {
            System.out.print("NIM               :");
            nim = userInput.nextLine();
            System.out.print("Full Name         :");
            fullName = userInput.nextLine();
            System.out.print("Class             :");
            classPlacement = userInput.nextLine();
            System.out.print("Study Program     :");
            studyProgram = userInput.nextLine();
            System.out.print("Sex               :");
            sex = userInput.nextLine();
            if (nim.length() == 10) {
                for (int i = 0; i < students.length; i++) {
                    if (nim.equals(students[i][0])) {
                        clearConsole();
                        renderString("Student with the NIM of " + nim + " already exists!");
                        renderString("Masukkan data lagi");
                        isFind = false;
                        break;
                    } else {
                        isFind = true;
                    }
                    ;
                }
            } else {
                clearConsole();
                renderString("NIM harus 10 digit");
            }
            if (isFind) {
                String[][] newStudents = new String[students.length + 1][5];
                for (int i = 0; i < students.length; i++) {
                    newStudents[i] = students[i];
                }
                newStudents[newStudents.length - 1] = new String[] { nim, fullName, classPlacement, studyProgram, sex };
                students = newStudents;
                renderStudentsTable("Data's Student", students);
                renderString("press enter to continue...");
                userInput.nextLine().trim();
                clearConsole();
            }
        }
    }

    static void renderStudentsTable(String title, String[][] students) {
        renderString(title);
        System.out.println("+------+------------+----------------------+-------+----------------------+-----+");
        System.out.println("| No.  |    NIM     |      Full Name       | Class |     Study Program    | Sex |");
        System.out.println("+------+------------+----------------------+-------+----------------------+-----+");
        for (int i = 0; i < students.length; i++) {
            String[] student = students[i];
            System.out.printf("| %-4d | %-10s | %-20s | %-5s | %-20s |  %s  |\n", (i + 1), student[0], student[1],
                    student[2],
                    student[3], student[4]);
        }
        System.out.println("+------+------------+----------------------+-------+----------------------+-----+");
    }

    static void pencarian() {
        String nimInput;
        boolean isFind = false;
        int studentIndex = -1;

        while (!isFind) {
            renderStudentsTable("Data's Student", students);
            renderString("Cari mahasiswa dengan nim   :");
            nimInput = userInput.nextLine();

            if (nimInput.length() == 10) {
                for (int i = 0; i < students.length; i++) {
                    if (nimInput.equals(students[i][0])) {
                        clearConsole();
                        studentIndex = i;
                        isFind = true;
                        break;
                    } else if (nimInput.equals(students[i][0])) {
                        isFind = false;
                    }
                }
            } else {
                clearConsole();
                renderString("NIM harus 10 digit");
            }
            if (isFind) {
                System.out.println("+------+------------+----------------------+-------+----------------------+-----+");
                System.out.println("| No.  |    NIM     |      Full Name       | Class |     Study Program    | Sex |");
                System.out.println("+------+------------+----------------------+-------+----------------------+-----+");
                System.out.printf("| %-4d | %-10s | %-20s | %-5s | %-20s |  %s  |\n", 1,
                        students[studentIndex][0],
                        students[studentIndex][1],
                        students[studentIndex][2],
                        students[studentIndex][3],
                        students[studentIndex][4]);

                System.out.println("+------+------------+----------------------+-------+----------------------+-----+");
                renderString("press enter to continue...");
                userInput.nextLine().trim();
                clearConsole();
            } else {
                renderString("Student with the NIM of " + nimInput + " doesn't exists!");
                renderString("Masukkan data lagi");
            }

        }
    }

    static void inputDataMatkul() {
        String courseCode, courseName, sks;
        boolean isFind = false;
        renderString("Press enter to continue...");
        userInput.nextLine().trim();
        clearConsole();
        renderCourseTable("Course data", course);
        while (!isFind) {
            System.out.print("Course Code       :");
            courseCode = userInput.nextLine();
            System.out.print("Course Name       :");
            courseName = userInput.nextLine();
            System.out.print("SKS               :");
            sks = userInput.nextLine();

            if (courseCode.length() == 9) {
                for (int i = 0; i < course.length; i++) {
                    if (courseCode.equals(course[i][0])) {
                        clearConsole();
                        renderString("Course with the Course code of " + courseCode + " already exists!");
                        renderString("Masukkan data kembali");
                        isFind = false;
                        break;
                    } else {
                        isFind = true;
                    }
                    ;
                }
            } else {
                clearConsole();
                System.out.println("Kode Mata Kuliah harus 9 digit");
            }
            if (isFind) {
                String[][] newCourse = new String[course.length + 1][3];
                for (int i = 0; i < course.length; i++) {
                    newCourse[i] = course[i];
                }
                newCourse[newCourse.length - 1] = new String[] { courseCode, courseName, sks };
                course = newCourse;

                String[][][] newGrades = new String[999][course.length][3];
                for (int i = 0; i < grades.length; i++) {
                    for (int j = 0; j < grades[0].length; j++) {
                        newGrades[i][j] = grades[i][j];
                    }
                }
                grades = newGrades;

                renderCourseTable("Course data", course);
                renderString("press enter to continue...");
                userInput.nextLine().trim();
                clearConsole();
            }
        }
    }

    static void renderCourseTable(String title, String[][] course) {
        renderString(title);
        System.out.println("+------+--------------------+----------------------------------------+-----+");
        System.out.println("| No.  |    Course Code     |               Course Name              | SKS |");
        System.out.println("+------+--------------------+----------------------------------------+-----+");
        for (int i = 0; i < course.length; i++) {
            String[] newCourse = course[i];
            System.out.printf("| %-4d | %-18s | %-38s | %-3s |\n", (i + 1), newCourse[0], newCourse[1],
                    newCourse[2]);
        }
        System.out.println("+------+--------------------+----------------------------------------+-----+");
    }

    static void dashboardMahasiswa(String user) {
        renderString("Selamat Datang " + user);
        while (true) {
            renderString("=== Dashboard Mahasiswa ===");
            renderString("1. Cetak KHS");
            renderString("2. Logout");
            renderString("0. keluar");
            System.out.print("pilih fitur: ");
            int choice = getUserChoiceInt();
            switch (choice) {
                case 1:
                    clearConsole();
                    cetakKHS();
                    break;
                case 2:
                    clearConsole();
                    loginView();
                    break;
                case 0:
                    System.exit(choice);
                default:
                    renderString("Fitur tidak tersedia");
                    continue;
            }
        }

    }

    static void cetakKHS() {

    }

    static void inputNilai() {
        String name = "";
        int indexStudent = -1;

        while (indexStudent == -1) {
            System.out.print("Masukkan nama mahasiswa   : ");
            name = userInput.nextLine();
            for (int i = 0; i < students.length; i++) {
                if (name.equalsIgnoreCase(students[i][1])) {
                    indexStudent = i;
                    renderString("Data ditemukan");
                    System.out.println(
                            "+------+------------+----------------------+-------+----------------------+-----+");
                    System.out.println(
                            "| No.  |    NIM     |      Full Name       | Class |     Study Program    | Sex |");
                    System.out.println(
                            "+------+------------+----------------------+-------+----------------------+-----+");
                    System.out.printf("| %-4d | %-10s | %-20s | %-5s | %-20s |  %s  |\n", 1,
                            students[i][0],
                            students[i][1],
                            students[i][2],
                            students[i][3],
                            students[i][4]);
                    System.out.println(
                            "+------+------------+----------------------+-------+----------------------+-----+");
                    renderString("press enter to continue...");
                    userInput.nextLine().trim();
                    clearConsole();
                    break;
                }
            }
            if (indexStudent == -1) {
                renderString("Data tidak ditemukan. Silakan coba lagi.");
            }
        }

        renderValueTable("Value's Data", indexStudent, course, grades);
        renderString("press enter to continue...");
        userInput.nextLine().trim();
        clearConsole();

        for (int i = 0; i < course.length; i++) {
            if (grades[indexStudent][i][0] == "-") {
                while (i < course.length) {
                    int numericValue;
                    while (true) {
                        System.out.print("Nilai matkul " + course[i][1] + " : ");
                        numericValue = userInput.nextInt();
                        userInput.nextLine();
                        if (numericValue < 0 || numericValue > 100) {
                            renderString("The value must be in the range 1-100. Please try again.....");
                            renderString("press enter to continue...");
                            userInput.nextLine().trim();
                            clearConsole();
                        } else {
                            break;
                        }
                    }
                    String letterValue;
                    String predicate = "";
                    if (numericValue > 80 && numericValue <= 100) {
                        letterValue = "A";
                        predicate = "Sangat baik";
                    } else if (numericValue > 73 && numericValue <= 80) {
                        letterValue = "B+";
                        predicate = "Lebih dari baik";
                    } else if (numericValue > 65 && numericValue <= 73) {
                        letterValue = "B";
                        predicate = "Baik";
                    } else if (numericValue > 60 && numericValue <= 65) {
                        letterValue = "C+";
                        predicate = "Lebih dari cukup";
                    } else if (numericValue > 50 && numericValue <= 60) {
                        letterValue = "C";
                        predicate = "Cukup";
                    } else if (numericValue > 39 && numericValue <= 50) {
                        letterValue = "D";
                        predicate = "Kurang";
                    } else if (numericValue > 0 && numericValue <= 39) {
                        letterValue = "E";
                        predicate = "Gagal";
                    } else {
                        letterValue = "Tidak tersedia";
                    }
                    grades[indexStudent][i][0] = String.valueOf(numericValue);
                    grades[indexStudent][i][1] = letterValue;
                    grades[indexStudent][i][2] = predicate;
                    i++;
                }
            }
        }

        System.out.print("\n");

        System.out.println("-------------------------------------");
        System.out.println("|          Data mahasiswa           |");
        System.out.println("-------------------------------------");
        System.out.println("| Nama        : " + name);
        System.out.println("| NIM         : " + students[indexStudent][0]);
        System.out.println("-------------------------------------");

        renderValueTable("Value's Table", indexStudent, course, grades);
        renderString("press enter to continue...");
        userInput.nextLine().trim();
        clearConsole();

    }

    static void renderValueTable(String title, int indexStudent, String[][] course, String[][][] value) {
        renderString(title);
        System.out.println(
                "+------+--------------------+----------------------------------------+-----+----------------+----------------+------------------+");
        System.out.println(
                "| No.  |    Course Code     |               Course Name              | SKS | Value (Number) | Value (Letter) |     Predicate    |");
        System.out.println(
                "+------+--------------------+----------------------------------------+-----+----------------+----------------+------------------+");
        for (int i = 0; i < course.length; i++) {
            String[] newCourse = course[i];
            String[] values = value[indexStudent][i];
            for (int j = 0; j < values.length; j++) {
                if (values[j] == null) {
                    values[j] = "-";
                }
            }
            System.out.printf("| %-4d | %-18s | %-38s | %-3s | %-14s | %-14s | %-16s |\n", (i + 1), newCourse[0],
                    newCourse[1],
                    newCourse[2], values[0], values[1], values[2]);
        }
        System.out.println(
                "+------+--------------------+----------------------------------------+-----+----------------+----------------+------------------+");
    }

    static void updateDataMahasiswa() {
        String oldNim, fullName, classPlacement, studyProgram, nim;
        boolean isFind = false;
        int studentIndex = -1;
        renderString("press enter to continue...");
        userInput.nextLine().trim();
        clearConsole();
        while (!isFind) {
            renderStudentsTable("Data's Student", students);
            System.out.print("Edit data mahasiswa dengan NIM :");
            oldNim = userInput.nextLine();
            if (oldNim.length() == 10) {
                for (int i = 0; i < students.length; i++) {
                    if (oldNim.equals(students[i][0])) {
                        clearConsole();
                        studentIndex = i;
                        isFind = true;
                        break;
                    }
                }
            } else {
                clearConsole();
                System.out.println("NIM harus 10 digit");
            }
            if (isFind) {
                renderString("New Student Data");
                System.out.print("input new nim             :");
                nim = userInput.nextLine();
                System.out.print("input new name            :");
                fullName = userInput.nextLine();
                System.out.print("input new class           :");
                classPlacement = userInput.nextLine();
                System.out.print("input new study program   :");
                studyProgram = userInput.nextLine();
                students[studentIndex][0] = nim;
                students[studentIndex][1] = fullName;
                students[studentIndex][2] = classPlacement;
                students[studentIndex][3] = studyProgram;
                clearConsole();
                renderString("Students have been succesfully added!");
                renderStudentsTable("Data's Student", students);
                renderString("press enter to continue...");
                userInput.nextLine().trim();
                clearConsole();
            } else {
                renderString("Student with the NIM of " + oldNim + " doesn't exists!");
                renderString("Masukkan data lagi");
                renderString("press enter to continue...");
                userInput.nextLine().trim();
                clearConsole();
            }
        }
    }

    static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void renderString(String string) {
        System.out.println(string);
    }

    static int getUserChoiceInt() {
        int choice;
        choice = userInput.nextInt();
        userInput.nextLine();
        return choice;
    }

    static String getUserString() {
        String input;
        input = userInput.nextLine();
        return input;
    }

}