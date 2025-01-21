package Model.SQL;

public class Director {
    private int directorID;
    private String name;
    private String lastName;

    public Director(int directorID, String name, String lastName) {
        this(name, lastName);
        this.directorID = directorID;
    }

    public Director(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public int getdirectorID() {
        return directorID;
    }

    public void setdirectorID(int directorID) {
        this.directorID = directorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorID=" + directorID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

