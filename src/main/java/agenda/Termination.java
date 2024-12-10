package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Termination extends Repetition{
    private LocalDate endDate;
    private long occurrences;
    private ChronoUnit frequency;


    public LocalDate terminationDateInclusive() {
        return endDate;
    }

    public long numberOfOccurrences() {
        return occurrences;
    }


    /**
     * Constructs a fixed termination event ending at a given date
     * @param start the start time of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     * @see ChronoUnit#between(Temporal, Temporal)
     */
    public Termination(String title, LocalDate start, ChronoUnit frequency, LocalDate terminationInclusive) {
        super(title, start.atStartOfDay(), ChronoUnit.DAYS.getDuration(), frequency);
        this.endDate = terminationInclusive;
        this.frequency = frequency;
        this.occurrences = ChronoUnit.DAYS.between(start, terminationInclusive);
    }



        /**
         * Constructs a fixed termination event ending after a number of iterations
         * @param start the start time of this event
         * @param frequency one of :
         * <UL>
         * <LI>ChronoUnit.DAYS for daily repetitions</LI>
         * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
         * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
         * </UL>
         * @param numberOfOccurrences the number of occurrences of this repetitive event
         */
        public Termination(String title, LocalDate start, ChronoUnit frequency, long numberOfOccurrences) {
            super(title, start.atStartOfDay(), ChronoUnit.DAYS.getDuration(), frequency);
            this.endDate = start.plus(numberOfOccurrences, frequency);
            this.frequency = frequency;
            this.occurrences = numberOfOccurrences;
        }


}
