/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.dac.model;

import java.io.Serializable;

/**
 *
 * @author Marcos
 */
public enum Status implements Serializable {

    PROBLE(0),
    AGD_LAV(1),
    AGD_PAG(2),
    AGD_ENT(3),
    ENT_SOL(4),
    CANCEL(5),
    CONCLU(6);

    private final int num;
    private static final String[] STATUS = {
        "Houve um Problema", "Aguardando Lavagem", "Aguardando Pagamento",
        "Aguardando Entrega", "Solicitada Entrega", "Cancelado", "Concluído",};

    Status(int num) {
        this.num = num;
    }

    public int toInt() {
        return num;
    }

    public static Status fromInt(int num) {
        switch (num) {
            case 1:
                return AGD_LAV;
            case 2:
                return AGD_PAG;
            case 3:
                return AGD_ENT;
            case 4:
                return ENT_SOL;
            case 5:
                return CANCEL;
            case 6:
                return CONCLU;
            default:
                return PROBLE;
        }
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        if (getNum() < STATUS.length) {
            return STATUS[num];
        }
        return "";
    }
    
}
