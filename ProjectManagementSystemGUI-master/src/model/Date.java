package model;

import java.time.LocalDate;

/**
 * Class for date
 * @author Rokas Paulauskas
 * @version 1.001 2020-12-03
 */
public class Date {
    private int day;
    private int month;
    private int year;

    /**
     * Create date object for a wanted date
     * @param day - day
     * @param month - month
     * @param year - year
     */
    public Date(int day, int month, int year) {
        set(day, month, year);
    }

    /**
     * Create date object for today
     */
    public Date() {
        LocalDate today = LocalDate.now();
        day = today.getDayOfMonth();
        month = today.getMonthValue();
        year = today.getYear();
    }

    /**
     * Set date for the object
     * @param day - day
     * @param month - month
     * @param year - year
     */
    public void set(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        if (year < 0) {
            this.year = Math.abs(year);
        }
        if (month < 0) {
            this.month = 1;
        }
        if (month > 12) {
            this.month = 12;
        }
        if (day < 1) {
            this.day = 1;
        }
        if (day > numberOfDaysInMonth()) {
            this.day = numberOfDaysInMonth();
        }
    }

    /**
     * get the day of the object
     * @return returns day of the object
     */
    public int getDay() {
        return day;
    }

    /**
     * get the month of the object
     * @return returns month of the object
     */
    public int getMonth() {
        return month;
    }

    /**
     * get the year of the object
     * @return returns year of the object
     */
    public int getYear() {
        return year;
    }

    /**
     * Checks if the model.Date given to the object is leap year to know how many days there will be in a year
     * @return false or true
     */
    public boolean isLeapYear() {
        if (this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0) {
            return true;
        } else return false;
    }

    /**
     * Converts month number to the String name for example 1 to January
     * @return returns String type month name
     */
    public String getMonthName() {
        switch (this.month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "There is no such month";
        }
    }

    /**
     * Adds one day to the day swaps month as a result if there are more days than the month has to the next month
     * same with year if month is more than 13 adds 1 year and sets month to 1
     */
    public void stepForwardOneDay() {
        this.day++;
        if (this.day > numberOfDaysInMonth()) {
            this.day = 1;
            this.month++;
        }
        if (this.month > 12) {
            this.month = 1;
            this.year++;
        }
    }

    /**
     * Adds how many days you want to the current day object by looping trough stepforwardOneDay method
     * @param days how many days you want to step forward
     */
    public void stepForward(int days) {
        for (int i = 0; i < days; i++) {
            stepForwardOneDay();
        }
    }

    /**
     * Checks if the current date object is before the date object given in the argument
     * @param date2 another date object
     * @return returns true or false which corresponds to it is before or later then the date given in the argument.
     */
    public boolean isBefore(Date date2) {
        if (this.year * 365 + this.month * 30 + this.day < date2.getYear() * 365 + date2.getMonth() * 30 + date2.getDay()) {
            return true;
        } else return false;
    }

    /**
     * Checks how many days there is in the month that is currently set
     * @return how many days there is in the current month
     */
    public int numberOfDaysInMonth() {
        if ((this.month == 2 && (this.year % 4 == 0 && this.year % 100 != 0)) || (this.month == 2 && this.year % 400 == 0)) {
            return 29;
        } else if (this.month == 2) {
            return 28;
        } else if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
            return 30;
        } else return 31;
    }

    /**
     * checks how many years two dates are apart
     * @param date2
     * @return years apart the two dates
     */
    public int yearsBetween(Date date2) {
        if (this.year > date2.getYear()) {
            return this.year - date2.getYear();
        } else if (date2.getYear() > this.year) {
            return date2.getYear() - this.year;
        } else
            return 0;
    }

    /**
     * Says which day of the week it is now
     * @return day of the week from monday to sunday in string format
     */
    public String dayOfWeek() {
        int h;
        int q = day;
        int m = month;
        if (month == 1 || month == 2) {
            m += 12;
        }
        int y = year;
        int k = y % 100;
        if (m == 13 || m == 14) {
            k = (y - 1) % 100;
        }
        int j = y / 100;
        if (m == 13 || m == 14) {
            j = (y - 1) / 100;
        }
        h = (q + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
        switch (h) {
            case 0:
                return "Saturday";
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            default:
                return "Friday";
        }

    }

    /**
     * copies object.
     * @return a copy of the current date object
     */
    public Date copy() {
        Date other = new Date(day, month, year);
        return other;
    }

    /**
     * checks if the object is a date object then returns if the dates are the same
     * @param obj - any object
     * @return true or false if the dates are the same or not
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Date)) {
            return false;
        }
        Date other = (Date) obj;
        return day == other.day && month == other.month && year == other.year;
    }

    /**
     * converts model.Date object to string with following format DD/MM/YY
     * @return String with the model.Date following the DD/MM/YY format
     */
    @Override
    public String toString() {
        if (day < 10 && month < 10) {
            return "0" + day + "/0" + month + "/" + year;
        } else if (day < 10 && month > 9) {
            return "0" + day + "/" + month + "/" + year;
        } else if (day > 9 && month < 10) {
            return day + "/0" + month + "/" + year;
        } else return day + "/" + month + "/" + year;
    }
}
