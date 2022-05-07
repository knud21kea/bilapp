package com.example.demo.sevices;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeOfDayServiceTest
{

    @Test
    void getTimeAsString()
    {
        // Arrange
        TimeOfDayService tods = new TimeOfDayService();

        // Act
        String tod1 = "Morning";
        String tod2 = "Afternoon";
        String tod3 = "Evening";
        String tod4 = "Night";
        String tod5 = "Wrong time format";


        // Assert
        assertEquals(tod4, tods.getTimeAsString(4));
        assertEquals(tod4, tods.getTimeAsString(5)); //partition boundary
        assertEquals(tod1, tods.getTimeAsString(6));

        assertEquals(tod1, tods.getTimeAsString(11));
        assertEquals(tod1, tods.getTimeAsString(12)); //partition boundary
        assertEquals(tod2, tods.getTimeAsString(13));

        assertEquals(tod2, tods.getTimeAsString(15));
        assertEquals(tod2, tods.getTimeAsString(16)); //partition boundary
        assertEquals(tod3, tods.getTimeAsString(17));

        assertEquals(tod3, tods.getTimeAsString(21));
        assertEquals(tod3, tods.getTimeAsString(22)); //partition boundary
        assertEquals(tod4, tods.getTimeAsString(23));

        assertEquals(tod5, tods.getTimeAsString(24)); // wrong data
    }
}
// (depNo%10 == 0 && depNo > 0 && depNo <1000)