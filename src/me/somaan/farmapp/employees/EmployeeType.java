package me.somaan.farmapp.employees;

import me.somaan.farmapp.utils.Type;

import java.util.ArrayList;

public enum EmployeeType implements Type
{
    VETERINARY,
    FARM_WORKER;

    /**
     * Converts the enum value to a string, with first character capitalized and rest lowercase.
     * @return the string value of the enum.
     */
    @Override
    public String toString() {
        return (super.toString().charAt(0)+super.toString().substring(1).toLowerCase()).replaceAll("_", " ");
    }

    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<String>();
        for(EmployeeType type : EmployeeType.values())
            values.add(type.toString());
        return values;
    }
}
