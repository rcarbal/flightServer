package com.example.rcarb.flightservice.utilities;

import com.example.rcarb.flightservice.objects.FlightTimeObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rcarb on 2/21/2018.
 */

public class DataCheckingUtils {

    public static boolean isNumber(String value) {
        try {
            Double v = Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static FlightTimeObject getConvertedTime(int time) {
        FlightTimeObject object = new FlightTimeObject();

        if (time < 99) {
            object.setHour(0);
            object.setMinute(time);
        } else if (time > 99 && time < 999) {

            String timeLength = String.valueOf(time);

            int hour = Integer.parseInt(String.valueOf(time).substring(0, 1));
            object.setHour(hour);

            int minute = Integer.parseInt(timeLength.substring(1, timeLength.length()));
            object.setMinute(minute);

        } else if (time > 999 && time < 2359) {
            String timeLength = String.valueOf(time);


            int hour = Integer.parseInt(timeLength.substring(0, 2));
            object.setHour(hour);
            int minute = Integer.parseInt(timeLength.substring(2, timeLength.length()));
            object.setMinute(minute);
        }else if (time > 2399){
            String timeLength = String.valueOf(time);


            int hour = Integer.parseInt(timeLength.substring(0, 2));
            object.setHour(hour);
            int minute = Integer.parseInt(timeLength.substring(2, timeLength.length()));
            object.setMinute(minute);
        }

        else if (time == 0) {
            object.setHour(0);
            object.setMinute(0);
        } else {
            object.setHour(-1);
            object.setMinute(-1);
        }
        return object;
    }
    public static int convertTimeObjectToInt(FlightTimeObject object){
        int returnedInt = -2;
        if (object!= null){
            String concat = "";
            String hour = String.valueOf(object.getHour());
            String minute = String.valueOf(object.getMinute());
            concat = ""+hour+minute;
            returnedInt = Integer.valueOf(concat);
        }
        return returnedInt;
    }

    public static FlightTimeObject getConvertedTimeWithPm(int time, boolean pm) {
        boolean hasPm = pm;
        FlightTimeObject object = new FlightTimeObject();

        if (time < 99) {
            object.setHour(0);
            object.setMinute(time);
        } else if (time > 99 && time < 999) {
            if (!hasPm) {
                String timeLength = String.valueOf(time);
                int hour = Integer.parseInt(String.valueOf(time).substring(0, 1));
                object.setHour(hour);

                int minute = Integer.parseInt(timeLength.substring(1, timeLength.length()));
                object.setMinute(minute);

            } else if (hasPm) {
                String timeLength = String.valueOf(time);
                int hour = Integer.parseInt(String.valueOf(time).substring(0, 1));
                hour = hour + 12;
                object.setHour(hour);

                int minute = Integer.parseInt(timeLength.substring(1, timeLength.length()));
                object.setMinute(minute);
            }


        } else if (time > 999 && time < 2359) {
            if (!pm) {
                String timeLength = String.valueOf(time);

                int hour = Integer.parseInt(timeLength.substring(0, 2));
                if (hour == 12) {
                    hour = hour - 12;
                }
                object.setHour(hour);
                int minute = Integer.parseInt(timeLength.substring(2, timeLength.length()));
                object.setMinute(minute);
            } else if (pm) {
                //if its 12
                String timeLength = String.valueOf(time);

                int hour = Integer.parseInt(timeLength.substring(0, 2));
                if (hour == 12) {
                    hour = 12;
                } else if (hour == 10 || hour == 11) {
                    hour = hour + 12;
                }
                object.setHour(hour);
                int minute = Integer.parseInt(timeLength.substring(2, timeLength.length()));
                object.setMinute(minute);
            }
        } else if (time == 0) {
            object.setHour(0);
            object.setMinute(0);
        } else {
            object.setHour(-1);
            object.setMinute(-1);
        }
        return object;
    }


    //adds a day to time.
    public static Calendar adjustAlarmTime(Calendar alarmTimeCalendar) {
        Calendar current = Calendar.getInstance();
        Calendar adjustedDateCalendar = Calendar.getInstance();

        int currentHour = current.get(Calendar.HOUR_OF_DAY);
        int currentMinute = current.get(Calendar.MINUTE);

        int hour = alarmTimeCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = alarmTimeCalendar.get(Calendar.MINUTE);

        adjustedDateCalendar.set(Calendar.HOUR_OF_DAY, hour);
        adjustedDateCalendar.set(Calendar.MINUTE, minute);
        adjustedDateCalendar.set(Calendar.SECOND, 0);
        Date currentDate = current.getTime();

        Date alarmDate = adjustedDateCalendar.getTime();
        long difference = alarmDate.getTime() - currentDate.getTime();
        if (difference < 0) {
            adjustedDateCalendar.add(Calendar.DATE, 1);
        }
        int day = adjustedDateCalendar.get(Calendar.DAY_OF_MONTH);
        return adjustedDateCalendar;
    }

    public static int getNumberOfDailyAlarms(int initialHour) {

        int numberOfAlarmsReturned = 0;
        if (initialHour == 0 || initialHour == 1) {
            numberOfAlarmsReturned = 11;
        } else if (initialHour == 2 || initialHour == 3) {
            numberOfAlarmsReturned = 10;
        } else if (initialHour == 4 || initialHour == 5) {
            numberOfAlarmsReturned = 9;
        } else if (initialHour == 6 || initialHour == 7) {
            numberOfAlarmsReturned = 8;
        } else if (initialHour == 8 || initialHour == 9) {
            numberOfAlarmsReturned = 7;
        } else if (initialHour == 10 || initialHour == 11) {
            numberOfAlarmsReturned = 6;
        } else if (initialHour == 12 || initialHour == 13) {
            numberOfAlarmsReturned = 5;
        } else if (initialHour == 14 || initialHour == 15) {
            numberOfAlarmsReturned = 4;
        } else if (initialHour == 16 || initialHour == 17) {
            numberOfAlarmsReturned = 3;
        } else if (initialHour == 18 || initialHour == 19) {
            numberOfAlarmsReturned = 2;
        } else if (initialHour == 20 || initialHour == 21) {
            numberOfAlarmsReturned = 1;
        } else if (initialHour == 22 || initialHour == 23) {
            numberOfAlarmsReturned = 0;
        }
        return numberOfAlarmsReturned-1;
    }

    public static int converCalendarToInt(Calendar calendar, int minutes, int hour, boolean reset) {
        Calendar cal = Calendar.getInstance();
        int instanceHour = cal.get(Calendar.HOUR_OF_DAY);
        int instMinute = cal.get(Calendar.MINUTE);




        int timeInteger= -1;

        if (reset){
            Calendar recal = Calendar.getInstance();
            recal.add(Calendar.HOUR_OF_DAY, hour);
            recal.add(Calendar.MINUTE, minutes);

            int hourPassed = recal.get(Calendar.HOUR_OF_DAY);
            int minutePassed = recal.get(Calendar.MINUTE);



            String resHour = String.valueOf(hourPassed);
            String resMinute = "";
            if (minutePassed < 10){
                resMinute = String.valueOf(minutePassed);
                resMinute = "0"+ resMinute;
            }else {
                resMinute = String.valueOf(minutePassed);
            }

            String a = ""+resHour+resMinute;
            timeInteger = Integer.valueOf(a);

        }else{

            if (minutes > 0) {
                cal.add(Calendar.MINUTE, minutes);
            } else if (hour > 0) {
                cal.add(Calendar.HOUR_OF_DAY, hour);
            }
            int min = cal.get(Calendar.MINUTE);
            int hours = cal.get(Calendar.HOUR_OF_DAY);
            if (hours > 23) {
                if (hours == 0) {
                    hours = 24;
                } else if (hours == 1) {
                    hours = 25;
                } else if (hours == 2) {
                    hours = 26;
                } else if (hour == 3) {
                    hours = 27;
                }
            } else if (hours < 23) {
                if (hours == 0) {
                    hours = 24;
                } else if (hours == 1) {
                    hours = 25;
                } else if (hours == 2) {
                    hours = 26;
                } else if (hour == 3) {
                    hours = 27;
                }
            }
            String hoursString = String.valueOf(hours);
            String minutesString = "";
            if (min <10){
                minutesString = String.valueOf(min);
                minutesString = "0"+minutesString;
            }else {
                minutesString = String.valueOf(min);
            }
            String returnString = "" + hoursString + minutesString;
            timeInteger = Integer.valueOf(returnString);

        }



        return timeInteger;
    }

    public static int convertPassedMidnight(int time) {
        if (time < 60) {
            return 2400 + time;
        } else if (time > 100 && time < 400) {
            time = time % 100;
            return time + 2500;
        }
        return time;
    }

    //Changes time to 24 military time
    public static int convertTimeToMilitary(int time, boolean pm) {
        int returnTime = -2;
        if (!pm) {
            //12:00 am
            if (time < 1300 && time > 1199) {
                returnTime = time % 100;
                //1:00am - 11:59am
            } else if (time > 99 && time < 1200) {
                returnTime = time;
            }
        } else if (pm) {
            //12:00 pm - 12:59
            if (time < 1300 && time > 1199) {
                returnTime = time;
                //1:00 pm - 9:59 pm
            } else if (time > 99 && time < 1200) {
                int remainder = time % 100;
                int adjustTime = time - remainder;
                adjustTime = adjustTime + 1200;
                adjustTime = adjustTime + remainder;
                returnTime = adjustTime;
            }
        }
        return returnTime;
    }

    public static int convertTimeThatPassedMidnightToProperTime(int time){
        int rTime=-1;
        if (time == 24){
            rTime =0;
        }else if (time == 25){
            rTime =1;
        }else if (time == 26){
            rTime = 2;
        }else if (time == 27){
            rTime = 3;
        }
        return rTime;
    }
}
