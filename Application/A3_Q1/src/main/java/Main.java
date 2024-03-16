
import java.sql.*;
import java.sql.Date;
import java.util.Scanner;
public class Main
{
    public static void main( String[] args )
    {
        int c = -1;


        while(c != 0){
            c = menu();
            Scanner sc = new Scanner(System.in);

            if(c == 1){
                getAllStudents();
            } else if (c == 2){
//                String first_name, String last_name, String email, Date enrollment_date
                System.out.print("Enter First Name: ");
                String fn = sc.nextLine();
                System.out.print("\nEnter Last Name: ");
                String ls = sc.nextLine();
                System.out.print("\nEnter Email: ");
                String em = sc.nextLine();
                System.out.print("\nEnter Enrollments Date's Year: ");
                int yr = sc.nextInt();
                System.out.print("\nEnter Enrollments Date's Month: ");
                int mn = sc.nextInt();
                System.out.print("\nEnter Enrollments Date's Day: ");
                int dy = sc.nextInt();

                sc.nextLine();

                Date d = new Date ((yr-1900), (mn-1), (dy));
                addStudent(fn, ls, em, d);
            } else if (c == 3){
                System.out.print("Enter Student's ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("\nEnter New Email: ");
                String em = sc.nextLine();


                updateStudentEmail(id, em);
            } else if (c == 4){
                System.out.print("Enter Student's ID: ");
                int id = sc.nextInt();

                sc.nextLine();
                deleteStudent(id);
            }
        }



    }

    public static int menu(){
        System.out.println("\n\n|----------------------STUDENT DATABASE MENU----------------------");
        System.out.println("0.) Exit Program");
        System.out.println("1.) Print All Students");
        System.out.println("2.) Add a Student");
        System.out.println("3.) Update a Student Email");
        System.out.println("4.) Delete a Student");
        System.out.println("\n\nEnter Choice: ");
        Scanner scann = new Scanner(System.in);
        int choice = scann.nextInt();

        scann.nextLine();
        return choice;
    }
    public static void getAllStudents(){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "Google101";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            Statement statement = connection.createStatement();
            statement.executeQuery("select * from students");
            ResultSet resultSet = statement.getResultSet();
            System.out.println("ID  First Name  Last Name   Email                   Date");
            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + "  \t");
                System.out.print(resultSet.getString("first_name") + "     \t");
                System.out.print(resultSet.getString("last_name") + "      \t");
                System.out.print(resultSet.getString("email") + " \t");
                System.out.print(resultSet.getDate("enrollment_date") + " \t");
                System.out.println();
            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }




    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "Google101";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            String insertSQL = "insert into students (first_name, last_name, email, enrollment_date) values (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setString(1, first_name);
                pstmt.setString(2, last_name);
                pstmt.setString(3, email);
                pstmt.setDate(4, enrollment_date);
                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nData inserted successfully!");
                } else {
                    System.out.println("\nData NOT successfully inserted!");
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }




    public static void updateStudentEmail(int student_id, String new_email){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "Google101";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }


            String insertSQL = "update students set email = ? where student_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setString(1, new_email);
                pstmt.setInt(2, student_id);
                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nData updated successfully!");
                } else {
                    System.out.println("\nData NOT successfully updated!");
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }




    public static void deleteStudent(int student_id){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "Google101";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null){
                System.out.println("Connected to the database");
            }else {
                System.out.println("Failed to connect to the database");
            }

            String insertSQL = "delete from students where student_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                pstmt.setInt(1, student_id);
                int rowsinserted = pstmt.executeUpdate();
                if(rowsinserted > 0){
                    System.out.println("\nData deleted successfully!");
                } else {
                    System.out.println("\nData NOT successfully deleted!");
                }

            }

        }
        catch(Exception e){System.out.println("FAILED"); System.out.println(e);}
    }
}

