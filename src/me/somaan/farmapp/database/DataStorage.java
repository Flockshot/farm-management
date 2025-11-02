package me.somaan.farmapp.database;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.animals.Animal;
import me.somaan.farmapp.animals.AnimalType;
import me.somaan.farmapp.animals.Cow;
import me.somaan.farmapp.animals.Sheep;
import me.somaan.farmapp.employees.Employee;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.employees.FarmWorker;
import me.somaan.farmapp.employees.Veterinary;

import java.sql.*;
import java.time.LocalDate;

public class DataStorage
{
    private Connection connection;

    public DataStorage() throws SQLException
    {

       setConnection(DriverManager.getConnection( "jdbc:mysql://localhost:3306/farmappdb", "cng443user","1234"));




    }


    public void readData() throws SQLException
    {
        Statement query = getConnection().createStatement();
        ResultSet rs = query.executeQuery("SELECT tagNo, gender, dateOfBirth, purchased, type, Weight FROM Animal");

        while (rs.next())
        {
            char type = rs.getString("type").charAt(0);
            int tagNo = rs.getInt("tagNo");
            String gender = rs.getString("gender");
            Date dob = rs.getDate("dateOfBirth");
            boolean isPurchased = rs.getBoolean("purchased");

            Animal toAdd = null;
            if(type == 'c')
            {
                int weight = rs.getInt("Weight");

                toAdd = new Cow(tagNo, gender, dob.toLocalDate(), isPurchased, weight);
            }
            else if(type == 's')
            {
                toAdd = new Sheep(tagNo, gender, dob.toLocalDate(), isPurchased);
            }
            FarmApp.addAnimal(toAdd);
        }


        //query = getConnection().createStatement();
        rs = query.executeQuery("SELECT empID, gender, dateOfBirth, type, BScDegree, dateOfGraduation, expertiseLevel, previousFarmName, workExperience FROM Employee");

        while(rs.next())
        {
            char type = rs.getString("type").charAt(0);
            int empID = rs.getInt("empID");
            String gender = rs.getString("gender");
            Date dob = rs.getDate("dateOfBirth");

            Employee toAdd = null;

            if(type == 'v')
            {
                boolean bsc = rs.getBoolean("BScDegree");
                Date dog = rs.getDate("dateOfGraduation");
                int expertiseLevel = rs.getInt("expertiseLevel");

                if(dog==null)
                    toAdd = new Veterinary(empID, gender, dob.toLocalDate(), expertiseLevel);
                else
                    toAdd = new Veterinary(empID, gender, dob.toLocalDate(), expertiseLevel, bsc, dog.toLocalDate());
            }
            else if(type == 'f')
            {
                String previousFarm = rs.getString("previousFarmName");
                int workExp = rs.getInt("workExperience");

                toAdd = new FarmWorker(empID, gender, dob.toLocalDate(), workExp, previousFarm);
            }

            FarmApp.addEmployee(toAdd);
        }
    }

    public void writeData() throws SQLException
    {
        Statement query = getConnection().createStatement();
        int deletion = query.executeUpdate("DELETE FROM Animal");
        query.executeUpdate("DELETE FROM employee");

        PreparedStatement writeQuery = getConnection().prepareStatement("INSERT INTO Animal " +
                "(tagNO, gender, dateOfBirth, purchased, type, Weight) " +
                "VALUES (?, ?, ?, ?, ?, ?)");

        for(Animal animal : FarmApp.animals)
        {
            writeQuery.setInt(1, animal.getTagNo());
            writeQuery.setString(2, animal.getGender().substring(0,1));
            writeQuery.setDate(3, Date.valueOf(animal.getDateOfBirth()));
            writeQuery.setInt(4, animal.isPurchased() ? 1 : 0);
            writeQuery.setString(5, animal.getType().toString().toLowerCase().substring(0,1));
            int weight = 0;
            if(animal.getType().equals(AnimalType.COW))
                weight = (int) ((Cow) animal).getWeight();
            writeQuery.setInt(6, weight);

            writeQuery.executeUpdate();
        }

        PreparedStatement empQuery = getConnection().prepareStatement("INSERT INTO employee " +
                "(empID, gender, dateOfBirth, type, BScDegree, dateOfGraduation, expertiseLevel, previousFarmName, workExperience) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        for(Employee emp : FarmApp.employees)
        {
            empQuery.setInt(1, emp.getEmpID());
            empQuery.setString(2, emp.getGender().substring(0, 1));
            empQuery.setDate(3, Date.valueOf(emp.getDateOfBirth()));
            empQuery.setString(4, emp.getType().toString().toLowerCase().substring(0, 1));
            int bsc = 0;
            Date dog = null;
            int expLvl = 0;
            String prevFarm = "";
            int workExp = 0;

            if(emp.getType().equals(EmployeeType.VETERINARY))
            {
                bsc = ((Veterinary) emp).hasBscDegree() ? 1 : 0;
                if(!((Veterinary) emp).getDateOfGraduation().equals(LocalDate.MAX))
                dog = Date.valueOf(((Veterinary) emp).getDateOfGraduation());
                expLvl = ((Veterinary) emp).getExpertiseLevel();
            }
            else
            {
                prevFarm = ((FarmWorker) emp).getPreviousFarmName();
                workExp = ((FarmWorker) emp).getWorkExperience();
            }

            empQuery.setInt(5, bsc);
            empQuery.setDate(6, dog);
            empQuery.setInt(7, expLvl);
            empQuery.setString(8, prevFarm);
            empQuery.setInt(9, workExp);

            empQuery.executeUpdate();
        }

    }



    private Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
}
