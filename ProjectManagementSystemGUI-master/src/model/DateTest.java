package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void set() {
        Date date = new Date();
        date.set(15,12,2020);
        Assertions.assertEquals(new Date(15,12,2020),date);
        date.set(35,60,2020);
        Assertions.assertEquals(new Date(31,12,2020),date);
        date.set(-1,-2,-2020);
        Assertions.assertEquals(new Date(1,1,2020),date);
    }

    @Test
    void getDay() {
        Date date = new Date(15,12,2020);
        Assertions.assertEquals(15,date.getDay());
    }

    @Test
    void getMonth() {
        Date date = new Date(15,12,2020);
        Assertions.assertEquals(12,date.getMonth());
    }

    @Test
    void getYear() {
        Date date = new Date(15,12,2020);
        Assertions.assertEquals(2020,date.getYear());
    }

    @Test
    void isLeapYear() {
        Date date = new Date(15,12,2020);
        Assertions.assertEquals(true,date.isLeapYear());
        Date date1 = new Date(15,12,2016);
        Assertions.assertEquals(true,date1.isLeapYear());
        Date date2 = new Date(15,12,2012);
        Assertions.assertEquals(true,date2.isLeapYear());
        Date date3 = new Date(15,12,2008);
        Assertions.assertEquals(true,date3.isLeapYear());
        Date date4 = new Date(15,12,2004);
        Assertions.assertEquals(true,date4.isLeapYear());
        Date date5 = new Date(15,12,2000);
        Assertions.assertEquals(true,date5.isLeapYear());
        Date date6 = new Date(15,12,2100);
        Assertions.assertEquals(false,date6.isLeapYear());
        Date date7 = new Date(15,12,2200);
        Assertions.assertEquals(false,date7.isLeapYear());
        Date date8 = new Date(15,12,2009);
        Assertions.assertEquals(false,date8.isLeapYear());
        Date date9 = new Date(15,12,2005);
        Assertions.assertEquals(false,date9.isLeapYear());
        Date date10 = new Date(15,12,2009);
        Assertions.assertEquals(false,date10.isLeapYear());
    }

    @Test
    void getMonthName() {
        Date date8 = new Date(15,12,2009);
        Assertions.assertEquals("December",date8.getMonthName());
        Date date7 = new Date(15,11,2009);
        Assertions.assertEquals("November",date7.getMonthName());
        Date date6 = new Date(15,10,2009);
        Assertions.assertEquals("October",date6.getMonthName());
        Date date5 = new Date(15,9,2009);
        Assertions.assertEquals("September",date5.getMonthName());
        Date date4 = new Date(15,8,2009);
        Assertions.assertEquals("August",date4.getMonthName());
        Date date3 = new Date(15,7,2009);
        Assertions.assertEquals("July",date3.getMonthName());
        Date date2 = new Date(15,6,2009);
        Assertions.assertEquals("June",date2.getMonthName());
        Date date1 = new Date(15,5,2009);
        Assertions.assertEquals("May",date1.getMonthName());
        Date date = new Date(15,4,2009);
        Assertions.assertEquals("April",date.getMonthName());
        Date data = new Date(15,3,2009);
        Assertions.assertEquals("March",data.getMonthName());
        Date data1 = new Date(15,2,2009);
        Assertions.assertEquals("February",data1.getMonthName());
        Date data2 = new Date(15,1,2009);
        Assertions.assertEquals("January",data2.getMonthName());
        Date data3 = new Date(15,-3,2009);
        Assertions.assertEquals("January",data3.getMonthName());
        Date data4 = new Date(15,13,2009);
        Assertions.assertEquals("December",data4.getMonthName());
    }

    @Test
    void stepForwardOneDay() {
        Date data = new Date(15,3,2009);
        data.stepForwardOneDay();
        Assertions.assertEquals(16,data.getDay());
        data.stepForwardOneDay();
        Assertions.assertEquals(17,data.getDay());
    }

    @Test
    void stepForward() {
        Date data = new Date(15,3,2009);
        data.stepForward(16);
        Assertions.assertEquals(31,data.getDay());
        Date data1 = new Date(15,3,2009);
        data1.stepForward(17);
        Assertions.assertEquals(1,data1.getDay());
        Assertions.assertEquals(4,data1.getMonth());
    }

    @Test
    void isBefore() {
        Date data = new Date(15,4,2009);
        Date data1 = new Date(14,4,2009);
        Assertions.assertEquals(true,data1.isBefore(data));
        Assertions.assertEquals(false,data.isBefore(data1));
    }

    @Test
    void numberOfDaysInMonth() {
        Date data = new Date(2,12,2020);
        Assertions.assertEquals(31,data.numberOfDaysInMonth());
    }

    @Test
    void yearsBetween() {
        Date data = new Date(2,12,2020);
        Date data1 = new Date(2,12,2020);
        Assertions.assertEquals(0,data.yearsBetween(data1));
        Date data2 = new Date(1,6,2019);
        Date data3 = new Date(1,12,2020);
        Assertions.assertEquals(1,data2.yearsBetween(data3));

    }

    @Test
    void dayOfWeek() {
        Date data = new Date(18,12,2020);
        Assertions.assertEquals("Friday",data.dayOfWeek());
    }

    @Test
    void copy() {
        Date data = new Date(18,12,2020);
        Date date = data.copy();
        Assertions.assertEquals(data,date);
    }

    @Test
    void testEquals() {
        Date data = new Date(18,12,2020);
        Date date = data.copy();
        Assertions.assertEquals(true,data.equals(date));
        Date data1 = new Date(17,12,2020);
        Assertions.assertEquals(false,data.equals(data1));

    }
}