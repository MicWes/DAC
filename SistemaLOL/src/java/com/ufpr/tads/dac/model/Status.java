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

    AGD_LAV(0),
    AGD_PAG(1),
    AGD_ENT(2),
    ENT_SOL(3),
    CONCLU(4),
    CANCEL(5),
    PROBLE(6);

    private final int num;
    private static final String[] STATUS = {
        "Aguardando Lavagem", "Aguardando Pagamento", "Aguardando Entrega",
        "Entrega Solicitada", "Concluído", "Cancelado", "Houve um Problema",};

    Status(int num) {
        this.num = num;
    }

    public int toInt() {
        return num;
    }

    public static Status fromInt(int num) {
        switch (num) {
            case 1:
                return AGD_PAG;
            case 2:
                return AGD_ENT;
            case 3:
                return ENT_SOL;
            case 4:
                return CONCLU;
            case 5:
                return CANCEL;
            case 6:
                return PROBLE;
            default:
                return AGD_LAV;
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
