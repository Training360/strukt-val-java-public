package meetingrooms.entity;

public class MeetingRoom {

    private long id;
    private String name;
    private int width;
    private int length;

    public MeetingRoom() {
    }

    public MeetingRoom(String name) {
        this.name = name;
    }

    public MeetingRoom(long id, String name) {
        this(name);
        this.id = id;
    }

    public MeetingRoom(String name, int width, int length) {
        this(name);
        this.width = width;
        this.length = length;
    }

    public MeetingRoom(long id, String name, int width, int length) {
        this(name, width, length);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getArea() {
        return width * length;
    }
}
