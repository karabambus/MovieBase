package Model.SQL;

public class Actor {
    private int actorID;
    private String name;
    private String lastName;

    public Actor(int actorID, String name, String lastName) {
        this(name, lastName);
        this.actorID = actorID;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorID=" + actorID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public  Actor(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Actor(int actorID) {
        this.actorID = actorID;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
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
}
