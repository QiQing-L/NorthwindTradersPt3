package com.pluralsight;

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

        try{
            System.out.println("What do you want to do?");
            System.out.println("1) Display all products");
            System.out.println("2) Display all customers");
            System.out.println("0) Exit");
            System.out.println("Select an option:");
            String option = scanner.nextLine().trim();

            switch (option){
                case "1" :
                    displayAllProducts(username, password);
                    break;

                case "2" :
                    displayAllCustomers(username, password);
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

    public static void displayAllProducts (String username, String password) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
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

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery();

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

            results.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("There's an error! ");
            e.printStackTrace();
        }
    }

    public static void displayAllCustomers (String username, String password) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
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

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery();

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

            results.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("There's an error! ");
            e.printStackTrace();
        }
    }

}
