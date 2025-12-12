public class Bug {
    private int id;
    private String title;
    private String description;
    private String status;
    private String developerAssigned;

    public Bug(String title, String description, String status, String developerAssigned) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.developerAssigned = developerAssigned;
    }

    // Getters and setters...
}