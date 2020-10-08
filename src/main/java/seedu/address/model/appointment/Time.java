package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time {

    public static final String MESSAGE_CONSTRAINTS = "Time entered must be in the format of <HHmm>,"
            + " where HH is the 24-hour clock timing, mm is the minutes of the hour.";

    // TODO Need to test if these formatters are correct.
    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter FORMAT_OUTPUT = DateTimeFormatter.ofPattern("h:mm a");

    protected final LocalTime time;

    /**
     * Represents the date stored for any model object.
     * @param timeString The input time by the user.
     */
    public Time(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(timeString, FORMAT_INPUT);
    }

    /**
     * Returns true if a given string is a valid Time format.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test, FORMAT_INPUT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    @Override
    public String toString() {
        return time.format(FORMAT_OUTPUT);
    }
}
