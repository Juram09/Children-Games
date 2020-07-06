/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Juan
 */
public class textSubType extends textType {
    private String subType;
    private String subDescription;

    public textSubType(String subType, String subDescription, String nombre, String type, String descripcion) {
        super(nombre, type, descripcion);
        this.subType = subType;
        this.subDescription = subDescription;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }  
}
