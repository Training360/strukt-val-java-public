package meetingrooms;

import meetingrooms.entity.MeetingRoom;
import meetingrooms.repository.InMemoryMeetingRoomsRepository;
import meetingrooms.repository.MariaDbMeetingRoomsRepository;
import meetingrooms.service.ListType;
import meetingrooms.service.MeetingRoomsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MeetingRoomsServiceTest {

    private MeetingRoomsService service = new MeetingRoomsService(new MariaDbMeetingRoomsRepository());

    @BeforeEach
    void init() {
        service.deleteAll();

        service.save("egyes", 2, 3);
        service.save("kettes", 3, 4);
        service.save("hármas", 4, 5);
        service.save("négyes", 4, 3);
        service.save("ötös", 2, 5);
        service.save("hatos", 2, 6);
        service.save("hetes", 5, 7);
    }

    @Test
    void testGetMeetingroomsOrderedByName() {
        List<String> expected = service.getMeetingroomsOrderedByName();

        Assertions.assertEquals(7, expected.size());
        Assertions.assertEquals("egyes", expected.get(0));
        Assertions.assertEquals("hetes", expected.get(3));
        Assertions.assertEquals("négyes", expected.get(5));
    }

    @Test
    void testGetEverySecondMeetingRoom() {
        List<String> expected = service.getEverySecondMeetingRoom();

        Assertions.assertEquals(4, expected.size());
        Assertions.assertEquals("hatos", expected.get(1));
        Assertions.assertEquals("kettes", expected.get(2));
    }

    @Test
    void testGetMeetingRoomsAreas() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.AREAS);

        Assertions.assertEquals(6, expected.get(0).getArea());
        Assertions.assertEquals("ötös", expected.get(1).getName());
        Assertions.assertEquals(20, expected.get(5).getArea());
    }

    @Test
    void testGetMeetingRoomsExact() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.EXACT, "kettes");

        Assertions.assertEquals(1, expected.size());
        Assertions.assertEquals(3, expected.get(0).getWidth());
        Assertions.assertEquals(4, expected.get(0).getLength());
    }

    @Test
    void testGetMeetingRoomsExactNotExisting() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.EXACT, "xyz");

        Assertions.assertEquals(0, expected.size());
    }

    @Test
    void testGetMeetingRoomsPrefix() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.PREFIX, "es");

        Assertions.assertEquals(4, expected.size());
        Assertions.assertEquals("hetes", expected.get(1).getName());
        Assertions.assertEquals("négyes", expected.get(3).getName());
    }

    @Test
    void testGetMeetingRoomsPrefixNotExisting() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.PREFIX, "xyz");

        Assertions.assertEquals(0, expected.size());
    }

    @Test
    void testGetMeetingRoomsAreaGreater() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.AREA_GREATER, 15);

        Assertions.assertEquals(2, expected.size());
        Assertions.assertEquals("hármas", expected.get(0).getName());
        Assertions.assertEquals(35, expected.get(1).getArea());
    }

    @Test
    void testGetMeetingRoomsAreaGreaterTooBigParameterNumber() {
        List<MeetingRoom> expected = service.getMeetingRooms(ListType.AREA_GREATER, 100);

        Assertions.assertEquals(0, expected.size());
    }
}
