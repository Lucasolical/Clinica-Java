package com.mycompany.clinicaveterinaria;

public class Time 
{
    public long time = 0;
    final long hourInMinutes = 60;
    final long dayInMinutes = 24 * hourInMinutes;
    final long monthInMinutes = 30 * dayInMinutes;
    final long yearInMinutes = 365 * dayInMinutes;

    public Time(){
    }

    public Time(long year, long month, long day, long hour, long minutes){
        setYearInMinutes(year);
        setMonthInMinutes(month);
        setDayInMinutes(day);
        setHourInMinutes(hour);
        setMinute(minutes);
    }

    public Time(Time time){
        this.time = time.time;
    }

    public int cmp(Time time){
        if(this.time > time.getTime()){
            return 1;
        }
        if(this.time < time.getTime()){
            return -1;
        }

        return 0;
    }

    public void add(Time time){
        long result = this.time + time.getTime();
        if(result < 0)
            return;

        this.time = result;
    }

    public void sub(Time time){
        long result = this.time - time.time;
        if(result < 0)
            return;

        this.time = result;
    }

    public void addYears(long years){
        years *= yearInMinutes;
        if(time + years < 0)
            return;
        time += years;
    }

    public void addMonths(long months){
        months *= monthInMinutes;
        if(time + months < 0)
            return;
        time += months;
    }

    public void addDays(long days){
        days *= dayInMinutes;
        if(time + days < 0)
            return;
        time += days;
    }

    public void addMinutes(long minutes){
        if(time + minutes < 0)
            return;
        time += minutes;
    }

    public long getTime(){
        return time;
    }

    public long getYearInMinutes() {
        return time / yearInMinutes;
    }

    public long getMonthInMinutes() {
        return (time % yearInMinutes) / monthInMinutes;
    }

    public long getDayInMinutes() {
        return (time % yearInMinutes % monthInMinutes) / dayInMinutes;
    }

    public long getHourInMinutes() {
        return (time % yearInMinutes % monthInMinutes % dayInMinutes) / hourInMinutes;
    }

    public long getMinute() {
        return time % hourInMinutes;
    }

    public void setYearInMinutes(long year) {
        if(year < 0)
            return;
        System.out.println(year);
        System.out.println("Year in minutes: " + getYearInMinutes());
        time -= getYearInMinutes() * this.yearInMinutes;
        time += year * this.yearInMinutes;
        print();

    }
    public void setMonthInMinutes(long month) {
        if(month < 0)
            return;
        time -= getMonthInMinutes() * this.monthInMinutes;
        time += month* this.monthInMinutes;
    }
    public void setDayInMinutes(long day){
        if(day < 0)
            return;
        time -= getDayInMinutes() * this.dayInMinutes;
        time += day * this.dayInMinutes;
    }
    public void setHourInMinutes(long hour) {
        if(hour < 0)
            return;
        time -= getHourInMinutes() * this.hourInMinutes;
        time += hour * this.hourInMinutes;
    }
    public void setMinute(long minute) {
        if(minute < 0)
            return;
        time -= getMinute();
        time += minute;
    }
    public void print(){
        System.out.println(getYearInMinutes() + "/" + getMonthInMinutes() + "/" + getDayInMinutes() + " " + getHourInMinutes() + ":" + getMinute());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
