package agenda;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Event {
    /**
     * The myTitle of this event
     */
    private String myTitle;
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;
    /**
     * The durarion of the event
     */
    private Duration myDuration;

    private ChronoUnit myfrequence;
    private LocalDate endDate;
    private List<LocalDate> exceptions = new ArrayList<>();


    /**
     * /**
     *
     * @param title    the title of this event
     * @param start    the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }


    public void setRepetition(ChronoUnit frequency) {
        this.myfrequence = frequency;
    }


    public void addException(LocalDate date) {
        exceptions.add(date);
    }

    public List<LocalDate> getExceptions() {
        return exceptions;
    }


    public void setTermination(LocalDate terminationInclusive) {
        if (myfrequence == null) {
            throw new IllegalStateException("Fréquence de répétition non définie");
        }
        LocalDate startDate = myStart.toLocalDate();
        long durationInUnits = 0;
        if (myfrequence == ChronoUnit.DAYS) {
            durationInUnits = ChronoUnit.DAYS.between(startDate, terminationInclusive);
        } else if (myfrequence == ChronoUnit.WEEKS) {
            durationInUnits = ChronoUnit.WEEKS.between(startDate, terminationInclusive);
        } else if (myfrequence == ChronoUnit.MONTHS) {
            durationInUnits = ChronoUnit.MONTHS.between(startDate, terminationInclusive);
        } else {
            throw new UnsupportedOperationException("Impossible avec cette fréquence : " + myfrequence);
        }
        this.endDate = startDate.plus(durationInUnits, myfrequence);
    }
    public void setTermination(int occurrences) {
        if (myfrequence == null) {
            throw new IllegalStateException("Fréquence de répétition non définie");
        }
        LocalDate startDate = myStart.toLocalDate();
        this.endDate = startDate.plus(occurrences, myfrequence);
    }



    public int getNumberOfOccurrences() {
        if (myfrequence == ChronoUnit.DAYS) {
            return (int) myDuration.toDays();
        }
        else if (myfrequence == ChronoUnit.WEEKS) {
            return (int) myDuration.toDays() / 7;
        }
        else if (myfrequence == ChronoUnit.MONTHS) {
            return (int) myDuration.toDays() / 31;
        }
        return 0;
    }



    public LocalDate getTerminationDate() {
        return this.endDate;
    }



    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */

    public boolean isInDay(LocalDate aDay) {
        LocalDate start = myStart.toLocalDate();
        LocalDateTime endDateTime = myStart.plus(myDuration);
        LocalDate end = endDateTime.toLocalDate();
        return start.equals(aDay) || end.equals(aDay) || (start.isBefore(aDay) && end.isAfter(aDay));
    }




    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    @Override
    public String toString() {
        return "Event{title='%s', start=%s, duration=%s}".formatted(myTitle, myStart, myDuration);
    }
}


