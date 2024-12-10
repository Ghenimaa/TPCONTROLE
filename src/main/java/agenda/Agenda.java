package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    private List<Event> events;

    public Agenda() {
        this.events = new ArrayList<>();
    }

    public void addEvent(Event e) {
        events.add(e);
    }


    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> resultats = new ArrayList<>();
        for (Event e : events) {
            LocalDate Datedeb = e.getStart().toLocalDate();
            LocalDate Datefin = e.getStart().plus(e.getDuration()).toLocalDate();
            if (Datedeb.equals(day) || Datefin.equals(day)  && (day.isEqual(Datefin) || day.isBefore(Datefin))) {
                resultats.add(e);;
            }
        }
        return resultats;
    }
}



