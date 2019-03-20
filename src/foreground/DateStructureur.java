package foreground;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Alexandre Maul
 * @Version 2.1
 * @since 1.2
 */
public class DateStructureur {
    private Calendar calendar;
    private DateFormatSymbols symbols;

    //-------------------
    //--- Constructor ---
    //-------------------
    /**
     * Constructeur de la class DateStructeur
     * Permet de gerer les dates
     */
    public DateStructureur() {
        calendar = new GregorianCalendar();
        symbols = new DateFormatSymbols(Locale.FRANCE);
    }

    //==============================================================================================================================================================================================

    //-------------------------------------------
    //--- Prise de jour actuel ---
    //-------------------------------------------
    /**
     * @return String de la date bien formatter avec des jours
     */
    public String getActuelDay(){
        String day = symbols.getWeekdays()[calendar.get(Calendar.DAY_OF_WEEK)];
        String month = symbols.getMonths()[calendar.get(Calendar.MONTH)];

        return day.substring(0,1).toUpperCase() + day.substring(1) +
                " " +
                calendar.get(Calendar.DAY_OF_MONTH) +
                "\n" +
                month.substring(0,1).toUpperCase() + month.substring(1);
    }

    //-------------------------------------------
    //--- Prise de mois actuel ---
    //-------------------------------------------
    /**
     * @return String de la date bien formatter avec des mois
     */
    public String getActuelMonth(){
        String month = symbols.getMonths()[calendar.get(Calendar.MONTH)];

        return month.substring(0,1).toUpperCase() + month.substring(1) +
                "\n" +
                calendar.get(Calendar.YEAR);
    }

    //==============================================================================================================================================================================================

    //-------------------------------------------
    //--- Decalage de jour avec string ---
    //-------------------------------------------
    /**
     * @param jourDecal : decalage voulu de jour
     * @return String de la date bien formatter avec des jours et un decalage
     */
    public String getDecalDay(int jourDecal){
        calendar.add(Calendar.DATE, jourDecal);
        String decal = getActuelDay();
        calendar.add(Calendar.DATE, -(jourDecal));
        return decal;
    }

    //-------------------------------------------
    //--- Decalage de mois avec string ---
    //-------------------------------------------
    /**
     * @param month : decalage voulu de mois
     * @return String de la date bien formatter avec des mois et un decalage
     */
    public String getDecalMonth(int monthDecal){
        calendar.add(Calendar.MONTH, monthDecal);
        String decal = getActuelMonth();
        calendar.add(Calendar.MONTH, -(monthDecal));
        return decal;
    }

    //==============================================================================================================================================================================================

    //-------------------------------------------
    //--- Decalage de jour ---
    //-------------------------------------------
    /**
     * Decaler les jours dans la date
     * @param jourDecal : decalage voulu de jour
     */
    public void setDecal(int jourDecal){
        calendar.add(Calendar.DATE, jourDecal);
    }

    //-------------------------------------------
    //--- Decalage de mois ---
    //-------------------------------------------
    /**
     * Decaler les mois dans la date
     * @param jourDecal : decalage voulu de mois
     */
    public void setDecalMonth(int monthDecal){
        calendar.add(Calendar.MONTH, monthDecal);
    }

    //-------------------------------------------
    //--- Prise du premier jour du mois ---
    //-------------------------------------------
    /**
     * Decaler les jours au premier jour du mois
     */
    public void setfirst(){
        calendar.set(Calendar.DAY_OF_MONTH, 1);
    }

    //-------------------------------------------
    //--- Prise du jour de la semaine ---
    //-------------------------------------------
    /**
     * @return int du numero du jour dans la semaine
     */
    public int getWeekDayNumber(){
        int actual = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int result = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (result == 0)
            result = 7;
        calendar.set(Calendar.DAY_OF_MONTH, actual);
        return result-1;
    }

    //-------------------------------------------
    //--- Prise du nombre de jour dans le mois ---
    //-------------------------------------------
    /**
     * @return int du nombre de jour dans le mois
     */
    public int getMonthDayNumber(){
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    //-------------------------------------------
    //--- Prise du mois ---
    //-------------------------------------------
    /**
     * @return int du numero du mois
     */
    public int getMonth(){
        return calendar.get(Calendar.MONTH);
    }

    //-------------------------------------------
    //--- Prise du jours ---
    //-------------------------------------------
    /**
     * @return int du numero du jour
     */
    public int getDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    //-------------------------------------------
    //--- Prise de la date ---
    //-------------------------------------------
    /**
     * @return date de la date actuel
     */
    public Date getDate(){
        return calendar.getTime();
    }

    //-------------------------------------------
    //--- Changer la date actuel ---
    //-------------------------------------------
    /**
     * @param day : numero du jour
     * @param month : numero du mois
     * @param year : numero du jour
     */
    public void setDate(int day, int month, int year){
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
    }
}
