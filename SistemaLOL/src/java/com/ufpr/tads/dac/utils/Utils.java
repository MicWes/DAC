/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcos
 */
public class Utils {

    public static final LocalTime HORA_LIMITE = LocalTime.of(21, 0, 0);
    public static final LocalTime HORA_ABERTURA = LocalTime.of(12, 00, 0);
    public static final int PRAZO_MINIMO = 3;

    public static LocalDateTime addTimeToLDT(int hours, int min, int sec) {
        LocalDateTime newLdt = LocalDateTime.now().plusHours(hours + PRAZO_MINIMO).
                plusMinutes(min).plusSeconds(sec);
        if (!checkHoraEntrega(newLdt)) {
            return amanhaCedo();
        } else {
            return newLdt;
        }
    }

    private static boolean checkHoraEntrega(LocalDateTime ldt) {
        LocalTime lt = LocalTime.of(ldt.getHour(), ldt.getMinute(), ldt.getSecond());
        return lt.isAfter(HORA_LIMITE);
    }

    private static LocalDateTime amanhaCedo() {
        return LocalDateTime.of(LocalDate.now().plusDays(1), HORA_ABERTURA);
    }

    public static boolean isAberto() {
        return LocalTime.now().isBefore(HORA_LIMITE);
    }

    public static Date horaAbertura() {
        return Date.from(amanhaCedo().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void message(String what, String whom) {
        FacesMessage msg = new FacesMessage(what, whom);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public static void message(String what) {
        message(what, "");
    }
}
