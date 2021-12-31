package com.openclassrooms.mareunion.service;


import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.model.Meeting;

import com.openclassrooms.mareunion.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Salle A", R.color.purple_500),
            new Room("Salle B", R.color.teal_700),
            new Room("Salle C", R.color.purple_700),
            new Room("Salle D", R.color.teal_200),
            new Room("Salle E", R.color.fab_createMeeting)
    );

    public static List<String> PARTICIPANTS_EMAIL = Arrays.asList(
            new String("alex@lamzone.com"),
            new String("ager@lamzone.com"),
            new String("skretel@lamzone.com"),
            new String("hypia@lamzone.com"),
            new String("gerrard@lamzone.com")
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(DUMMY_ROOMS.get(0),"Subject C", "05/01/2022", "14:00", "15:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(1),"Subject A", "07/01/2022", "13:00", "14:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(2),"Subject D", "23/02/2022", "11:00", "13:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(3),"Subject B", "18/05/2022", "12:00", "14:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(4),"Subject E", "07/07/2022", "18:00", "18:30", PARTICIPANTS_EMAIL)
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
