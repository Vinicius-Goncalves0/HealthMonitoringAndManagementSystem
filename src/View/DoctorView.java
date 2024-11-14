package View;

import java.util.Scanner;

public class DoctorView {

    public void displayDoctorMenu() {
            Scanner scan = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nDoctor Menu:");
                System.out.println("1. Create Doctor");
                System.out.println("2. Modify the doctor's details");
                System.out.println("3. Consult the doctor's details");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                int choice = scan.nextInt();
                scan.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Implementar o código para criar doutor
                        break;
                    case 2:
                        // Implementar o código para modificar os detalhes de doutor
                        break;
                    case 3:
                        // Implementar o código para consultar os detalhes do doutor
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scan.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

}
