package meetingroom;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class MeetingroomDataJpaApplication implements CommandLineRunner {

    private final MeetingRoomsRepository meetingRoomsRepository;

    public static void main(String[] args) {
        SpringApplication.run(MeetingroomDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Spring Boot!");

        meetingRoomsRepository.save(new MeetingRoom("Jupiter"));
        meetingRoomsRepository.save(new MeetingRoom("Uranus"));
        meetingRoomsRepository.save(new MeetingRoom("Pluto"));

        System.out.println(meetingRoomsRepository.findAll());


        System.out.println(meetingRoomsRepository.findAllByNameLike("Ju%"));
    }
}
