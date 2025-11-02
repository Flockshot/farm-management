package me.somaan.farmapp.treatments;

import me.somaan.farmapp.utils.Type;

import java.util.ArrayList;

public enum TreatmentType implements Type
{
    TREATMENT,
    CLEANING_TREATMENT,
    HEALTH_TREATMENT;

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
        for(TreatmentType type : TreatmentType.values())
            values.add(type.toString());
        return values;
    }
}
