package com.reeson.learn.demo.enums;

public enum AnnotationTypeEnum {
    CONTROLLER("controller", 1), SERVICE("service", 2);

    private String name;
    private int index;
    private AnnotationTypeEnum(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String getName(){
        return name;
    }
}
