package com.mycompany.clinicaveterinaria;

public class Time 
{
    public long time = 0;
    final long hour = 60;
    final long day = 24 * hour;
    final long month = 30 * day;
    final long year = 365 * day;

    public Time(){
    }

    public Time(int year, int month, int day, int hour, int minutes){
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
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
        years *= year;
        if(time + years < 0)
            return;
        time += years;
    }

    public void addMonths(long months){
        months *= month;
        if(time + months < 0)
            return;
        time += months;
    }

    public void addDays(long days){
        days *= day;
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
    public long getYear() {
        return time/year;
    }
    public long getMonth() {
        return (time - (time/year) * year)/month;
    }
    public long getDay() {
        return (time - (time/month) * month)/day;
    }
    public long getHour() {
        return (time - (time/day) * day)/hour;
    }
    public long getMinute() {
        return (time - (time/hour) * hour);
    }

    public void setYear(int year) {
        if(year < 0)
            return;
        time -= getYear() * this.year;
        time += year * this.year;
    }
    public void setMonth(int month) {
        if(month < 0)
            return;
        time -= getMonth() * this.month;
        time += month* this.month;
    }
    public void setDay(int day){
        if(day < 0)
            return;
        time -= getDay() * this.day;
        time += day * this.day;
    }
    public void setHour(int hour) {
        if(hour < 0)
            return;
        time -= getHour() * this.hour;
        time += hour * this.hour;
    }
    public void setMinute(int minute) {
        if(minute < 0)
            return;
        time -= getMinute();
        time += minute;
    }
    public void print(){
        System.out.println(getYear() + "/" + getMonth() + "/" + getDay() + " " + getHour() + ":" + getMinute());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
