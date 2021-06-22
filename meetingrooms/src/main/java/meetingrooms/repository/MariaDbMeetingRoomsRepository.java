package meetingrooms.repository;

import meetingrooms.entity.MeetingRoom;
import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository {

    private JdbcTemplate jdbcTemplate;
    private MariaDbDataSource dataSource;

    public MariaDbMeetingRoomsRepository() {
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/meetingrooms?useUnicode=true");
            dataSource.setUser("meetingrooms");
            dataSource.setPassword("meetingrooms");

            Flyway fw = Flyway.configure().dataSource(dataSource).load();
            fw.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot create DataSource.", sqle);
        }
    }

    @Override
    public MeetingRoom save(String name, int width, int length) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into meetingrooms(mr_name, mr_width, mr_length) values (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setInt(2, width);
            ps.setInt(3, length);
            return ps;
        }, keyHolder
        );
        long id = keyHolder.getKey().longValue();
        return new MeetingRoom(id, name, width, length);
    }

    @Override
    public List<String> getMeetingroomsOrderedByName() {
        return jdbcTemplate.query("select mr_name from meetingrooms order by mr_name;",
                (rs, i) -> rs.getString("mr_name"));
    }

    @Override
    public List<String> getEverySecondMeetingRoom() {
        return getMeetingroomsOrderedByName();
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return jdbcTemplate.query("select mr_name, mr_width, mr_length from meetingrooms;",
                this::getMeetingRoomsFromResultSet);
    }

    @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        return jdbcTemplate.query("select mr_name, mr_width, mr_length from meetingrooms where mr_name = ?;",
                this::getMeetingRoomsFromResultSet, name);
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String prefix) {
        return jdbcTemplate.query("select mr_name, mr_width, mr_length from meetingrooms where mr_name like ? order by mr_name;",
                this::getMeetingRoomsFromResultSet, "%" + prefix + "%");
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms;");
    }

    private List<MeetingRoom> getMeetingRoomsFromResultSet(ResultSet rs) {
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        try {
            addMeetingRooms(rs, meetingRooms);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot get data.", sqle);
        }
        return meetingRooms;
    }

    private void addMeetingRooms(ResultSet rs, List<MeetingRoom> meetingRooms) throws SQLException {
        while (rs.next()) {
            meetingRooms.add(new MeetingRoom(rs.getString("mr_name"), rs.getInt("mr_width"), rs.getInt("mr_length")));
        }
    }
}
