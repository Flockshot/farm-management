package me.somaan.farmapp.animals;


import java.util.ArrayList;

import me.somaan.farmapp.utils.Type;

/**
 * AnimalType enum to know what type is the animal object.
 * @author Muhammad Somaan
 * @version 1.0
 */
public enum AnimalType implements Type
{
    COW,
    SHEEP;

    /**
     * Converts the enum value to a string, with first character capitalized and rest lowercase.
     * @return the string value of the enum.
     */
    @Override
    public String toString() {
        return super.toString().charAt(0)+super.toString().substring(1).toLowerCase();
    }

    /**
     * Gets the values of the all the animal types as string
     * @return the arraylist containing the name of all animal types.
     */
    @Override
    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<String>();
        for(AnimalType type : AnimalType.values())
            values.add(type.toString());
        return values;
    }
}
