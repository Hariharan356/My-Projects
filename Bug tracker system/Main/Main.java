import java.util.*;

public class Main {
    public static void main(String[] args) {
        BugDAO dao = new BugDAO();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BUG TRACKER SYSTEM =====");
            System.out.println("1. Add Bug");
            System.out.println("2. Admin View All Bugs");
            System.out.println("3. Update Bug Status");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Description: ");
                    String desc = sc.nextLine();

                    System.out.print("Status (Open/In Progress/Closed): ");
                    String status = sc.nextLine();

                    System.out.print("Developer Assigned: ");
                    String dev = sc.nextLine();

                    Bug bug = new Bug(title, desc, status, dev);
                    dao.addBug(bug);
                    break;

                case 2:
                    List<Bug> bugs = dao.getAllBugs();
                    System.out.println("\n=== All Bugs ===");
                    for (Bug b : bugs) {
                        System.out.println("Title: " + b.getTitle());
                        System.out.println("Description: " + b.getDescription());
                        System.out.println("Status: " + b.getStatus());
                        System.out.println("Developer: " + b.getDeveloperAssigned());
                        System.out.println("--------------------------");
                    }
                    break;

                case 3:
                    System.out.print("Enter Bug ID to Update: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Status: ");
                    String newStatus = sc.nextLine();

                    dao.updateBugStatus(id, newStatus);
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}