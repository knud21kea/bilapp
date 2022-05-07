package com.example.demo.sevices;

public class TimeOfDayService
{
    public String getTimeAsString(int time)
    {
        String timeOfDay;
        if (time > 23)
        {
            timeOfDay = "Wrong time format";
        } else
        {
            if (time > 5 && time <= 12)
            {
                timeOfDay = "Morning";
            } else
            {
                if (time > 12 && time <= 16)
                {
                    timeOfDay = "Afternoon";
                } else
                {
                    if (time > 16 && time <= 22)
                    {
                        timeOfDay = "Evening";
                    } else
                    {
                        timeOfDay = "Night";
                    }
                }
            }
        }
        return timeOfDay;
    }
}
