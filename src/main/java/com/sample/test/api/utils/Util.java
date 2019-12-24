package com.sample.test.api.utils;

import com.sample.test.api.dto.Rango;
import org.apache.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 *
 * @author : Andrés Prado Cruz
 * @email : andresss32157@gmail.com
 * @class : Util.java
 */
public abstract class Util {

    private static Logger LOG = Logger.getLogger(Util.class);

    /**
     * Método para obtener los rangos de fechas por semana a partir de una lista de LocalDate
     * @return List<Rango>
     */
    public static List<Rango> getWeeks(List<LocalDate> localDateList){
        LocalDate fechaInicio = getFridayInicio(localDateList.stream().findFirst().get());
        LocalDate fechaFin = getThursdayFin(localDateList.get(localDateList.size()-1));

        List<Rango> rangos = new ArrayList<>();

        while(fechaFin.isAfter(fechaInicio)){

            LocalDate fechaFinRango = null;
            if(fechaInicio.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                fechaFinRango = fechaInicio.plusWeeks(1).minusDays(1);
            }else{
                fechaFinRango = getThursdayFin(fechaInicio);
            }

            Rango rango = null;
            if(fechaInicio.getMonth().equals(fechaFinRango.getMonth())){
                rango = new Rango(fechaInicio,fechaFinRango);
                if(fechaInicio.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    fechaInicio = fechaInicio.plusWeeks(1);
                }else{
                    fechaInicio = getNextFriday(fechaInicio);
                }
            }else{
                LocalDate end = fechaInicio.with(lastDayOfMonth());
                rango = new Rango(fechaInicio, end);
                fechaInicio = end.plusDays(1);
            }

            rangos.add(rango);
        }
        LOG.info("getWeeks() rangos.size() -> "+rangos.size());
        return rangos;

    }

    /**
     * Método para obtener en LocalDate el día viernes para los rangos de inicio de semana
     * @return LocalDate
     */
    public static LocalDate getFridayInicio(LocalDate localDate){
        if(localDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            return localDate.minusDays(3);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)){
            return localDate.minusDays(4);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
            return localDate.minusDays(5);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)){
            return localDate.minusDays(6);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            return localDate.minusDays(1);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return localDate.minusDays(2);
        }else{
            return localDate;
        }
    }

    /**
     * Método para obtener en LocalDate el día viernes después de un inicio de mes
     * @return LocalDate
     */
    public static LocalDate getNextFriday(LocalDate localDate){
        if(localDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            return localDate.plusDays(4);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)){
            return localDate.plusDays(3);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
            return localDate.plusDays(2);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)){
            return localDate.plusDays(2);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            return localDate.plusDays(6);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return localDate.plusDays(5);
        }else{
            return localDate;
        }
    }

    /**
     * Método para obtener en LocalDate el día jueves para el rango de fin de semana
     * @return LocalDate
     */
    public static LocalDate getThursdayFin(LocalDate localDate){
        if(localDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
            return localDate.plusDays(3);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)){
            return localDate.plusDays(2);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
            return localDate.plusDays(1);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
            return localDate.plusDays(6);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            return localDate.plusDays(5);
        }else if(localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return localDate.plusDays(4);
        }else{
            return localDate;
        }
    }

    /**
     * Método para validar si un LocalDate esta en el rango (start, end) especificado
     * @return boolean
     */
    public static boolean betweenLocalDate(LocalDate date, LocalDate start, LocalDate end){
        if((date.isAfter(start) || date.isEqual(start)) &&
                (date.isBefore(end) || date.isEqual(end) )){
            return true;
        }
        return false;
    }


    /**
     * Método simple que devuelve un numero aleatorio que nunca será mayor al sizeList, usando el operador MOD
     * @return boolean
     */
    public static long random(int sizeList){
        long random = System.currentTimeMillis() % sizeList;
        return random;
    }


}
