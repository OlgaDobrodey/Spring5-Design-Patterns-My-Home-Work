package com.itrex.chapter3.composite.furniture;

public class Table extends Furniture {

    private Integer costTable;

    public Table(){
        this.costTable = 160;
    }

    @Override
    public Integer getCost() {
        return costTable;
    }

}
