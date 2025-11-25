package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];

        if (args.length != 2) {
            System.out.println("Missing Username and Password to run this application!");
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        Connection connection =null;
        PreparedStatement preparedStatement = null;
        ResultSet results =null;

        try{
            System.out.println("What do you want to do?");
            System.out.println("1) Display all products");
            System.out.println("2) Display all customers");
            System.out.println("0) Exit");
            System.out.println("Select an option:");
            String option = scanner.nextLine().trim();

            switch (option){
                case "1" :
                    displayAllProducts(username, password, connection, preparedStatement,results);
                    break;

                case "2" :
                    displayAllCustomers(username, password, connection, preparedStatement,results);
                    break;

                case "0":
                    break;

                default:
                    System.out.println("please enter a valid option.");

            }


        } catch (Exception e) {
            System.out.println("There's an error!");
            e.printStackTrace();

        }finally {
            scanner.close();
        }



    }

    public static void displayAllProducts (String username, String password, Connection connection,
                                           PreparedStatement preparedStatement, ResultSet results) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );

            String query = """
                    SELECT ProductID AS `Product ID`,\s
                    		ProductName AS `Product Name`,\s
                    		UnitPrice AS `Unit Price`,\s
                    		UnitsInStock AS `Units In Stock`
                    FROM products
                    ORDER BY ProductID;\s
                    """;

            preparedStatement = connection.prepareStatement(query);

            results = preparedStatement.executeQuery();

            System.out.println("ID   Name                                Price       Stock");
            System.out.println("---  ---------------------------------   ---------   -----");
            while (results.next()) {
                int productID = results.getInt(1);
                String productName = results.getString(2);
                double unitPrice = results.getDouble(3);
                int unitsInStock = results.getInt(4);

                System.out.printf("%-4d %-35s $%-10.2f %d\n",
                        productID, productName, unitPrice, unitsInStock);

            }


        } catch (Exception e) {
            System.out.println("There's an error! ");
            e.printStackTrace();

        } finally {

            if (results != null) {
                try{
                    results.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }

    public static void displayAllCustomers (String username, String password, Connection connection,
                                           PreparedStatement preparedStatement, ResultSet results) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );

            String query = """
                    SELECT ContactName, CompanyName, City, Country, Phone
                    FROM customers
                    ORDER BY Country;
                    """;

            preparedStatement = connection.prepareStatement(query);

            results = preparedStatement.executeQuery();

            System.out.println("Contact Name          CompanyName            City       Country     Phone");
            System.out.println("--------------------- ---------------------- --------   ---------   -----");
            while (results.next()) {
                String contactName = results.getString(1);
                String companyName = results.getString(2);
                String city = results.getString(3);
                String country = results.getString(4);
                String phone = results.getString(5);



                System.out.printf("%-25s %-30s $%-20s %-20s %-20s\n",
                        contactName, companyName, city, country, phone);

            }


        } catch (Exception e) {
            System.out.println("There's an error! ");
            e.printStackTrace();

        } finally {

            if (results != null) {
                try{
                    results.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }

}
