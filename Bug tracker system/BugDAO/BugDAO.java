import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BugDAO {

    // Insert new bug
    public void addBug(Bug bug) {
        String sql = "INSERT INTO bugs(title, description, status, developer_assigned) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bug.getTitle());
            stmt.setString(2, bug.getDescription());
            stmt.setString(3, bug.getStatus());
            stmt.setString(4, bug.getDeveloperAssigned());
            stmt.executeUpdate();

            System.out.println("Bug Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Admin view: Get all bugs
    public List<Bug> getAllBugs() {
        List<Bug> list = new ArrayList<>();
        String sql = "SELECT * FROM bugs";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bug bug = new Bug(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("developer_assigned")
                );
                list.add(bug);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Update bug status or developer
    public void updateBugStatus(int id, String newStatus) {
        String sql = "UPDATE bugs SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Status Updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}