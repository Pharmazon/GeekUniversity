package com.td.game.map;

public enum CellType {
    GRASS("0"), ROAD("1"), CROSSROAD("2"), HOME("5");

    private String symb;

    CellType(String symb) {
        this.symb = symb;
    }

    public static CellType getTypeFromString(String str) {
        for (int i = 0; i < CellType.values().length; i++) {
            if (CellType.values()[i].symb.equals(str)) {
                return CellType.values()[i];
            }
        }
        return null;
    }
}