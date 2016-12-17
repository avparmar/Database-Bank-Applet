package Front;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by avparmar on 11/30/16.
 */
public class Main {

  public static void main(String args[]) {

    Connection c = getConnection();

    int id = validID(c);
    int passcount = 0;
    if (id != 0) {
      if (id < 0) {

        while(!(loginEmployee(0 - id, c))) {
          if (passcount == 3) { System.out.println("Access denied"); throw new IllegalArgumentException("bad");}
          System.out.println("Incorrect password. Try again.");
          passcount++;

        }
        employeePanel(0-id, c);

      }
      else {
        while(!(loginCustomer(id, c))) {
          if (passcount == 3) { System.out.println("Access denied"); throw new IllegalArgumentException("bad");}
          System.out.println("Incorrect pin. Try again.");
          passcount++;
        }
        customerPanel(id, c);
      }
    }
    else {
      System.out.println("Unrecognized user");
    }

  }

  private static void employeePanel(int id, Connection c) {

    Statement stmt = null;
    Scanner in = new Scanner(System.in);

    String sql = "SELECT first_name FROM users WHERE id = " + id + ";";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res.next();
      System.out.println("welcome, " + res.getString("first_name") +
              "\nPlease Enter a Command: " +
              "\nCreate ... ---------- Creates an account for a customer, with fields following" +
              "\nDelete ------------ Deletes a customer's account by ID" +
              "\nFreeze ------------ Freeze's customer's account by ID " +
              "\nModify ------------ Modify account by ID " +
              "\nCheck ------------- Check account by ID " +
              "\nOverview ------------ See all accounts" +
              "\nLeave --------------- Exit with no further action");
      boolean breaker = true;
      while (breaker) {
        String command = in.next();

        if (command.equals("Create")) {
          empCreate(c);
        } else if (command.equals("Delete")) {
          empDel(c);
        } else if (command.equals("Freeze")) {

          empFreeze(c);
        } else if (command.equals("Modify")) {
          empMod(c);
        } else if (command.equals("Check")) {

          empCheck(c);
        } else if (command.equals("Overview")) {
          empOver(c);
        } else if (command.equals("Leave")) {
          System.out.println("Goodbye.");
          break;
        } else throw new IllegalArgumentException("Invalid command");
        System.out.println("Would you like to do anything else? (Yes/No)");
        String decision = in.next();
        if (decision.equals("No")) {
          breaker = false;
          System.out.println("Goodbye.");
        }

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void empCreate(Connection c) {
    Scanner in = new Scanner(System.in);
    System.out.print("Please print ID of new Customer: ");
    int ID = in.nextInt();
    System.out.print("Please print first name: ");
    String fn = in.next();
    System.out.print("Please print last name: ");
    String ln = in.next();
    System.out.print("Please print customer's pin: ");
    int pin = in.nextInt();
    System.out.print("Please print account number: ");
    long acct = in.nextLong();
    System.out.print("Please enter starting balance: ");
    double bal = in.nextDouble();
    System.out.print("If the customer has a street address, print it, or print XXX if not");
    String addr = in.nextLine();
    System.out.print("If the customer has a city, print it, or print XXX if not");
    String city = in.next();
    System.out.print("If the customer has a zip code, print it, or print XXX if not");
    String zip = in.next();
    System.out.print("If the customer has a country, print it, or print XXX if not");
    String country = in.next();
    System.out.print("If the customer has a region, print it, or print XXX if not");
    String region = in.next();
    System.out.print("If the customer has a salary, print it, or print XXX if not");
    String salary = in.next();

    String cols = "ID,first_name,last_name";
    String vals = "" + ID + ", '" + fn + "', '" + ln + "'";
    if (!addr.equals("XXX")) { cols = cols + ", street_address"; vals = vals + ", '" + addr + "'"; }
    if (!city.equals("XXX")) { cols = cols + ", city"; vals = vals + ", '" + city + "'"; }
    if (!zip.equals("XXX")) { cols = cols + ", zip"; vals = vals + ", '" + zip + "'"; }
    if (!country.equals("XXX")) { cols = cols + ", country"; vals = vals + ", '" + country +"'"; }
    if (!region.equals("XXX")) { cols = cols + ", region"; vals = vals + ", '" + region + "'"; }
    if (!salary.equals("XXX")) { cols = cols + ", salary"; vals = vals + ", '" + salary +"'"; }

    Statement stmt = null;
    String sql = "INSERT INTO users (" + cols + ") VALUES (" + vals + ");";


    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    cols = "ID,pin,account_number,balance";
    vals = "" + ID + "," +pin + "," + acct + "," + bal;
    stmt = null;

    sql = "INSERT INTO customers (" + cols + ") VALUES (" + vals + ");";

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  private static void empDel(Connection c) {
    Scanner in = new Scanner(System.in);
    System.out.print("Print account ID: ");

    int ID = in.nextInt();

    Statement stmt = null;

    String sql = "DELETE FROM users WHERE id = " + ID + ";";

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sql = "DELETE FROM users WHERE id = " + ID + ";";

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void empFreeze(Connection c) {
    Statement stmt = null;
    Scanner in = new Scanner(System.in);
    System.out.print("Print account ID: ");

    int ID = in.nextInt();

    String sql = "UPDATE customers SET account_status = " + "'frozen' WHERE id = " + ID + ";" ;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void empMod(Connection c) {
    Scanner in = new Scanner(System.in);

    System.out.print("Print account ID: ");

    int ID = in.nextInt();

    System.out.print("Please print new first name, or XXX if you don't want to update it: ");
    String fn = in.next();
    System.out.print("Please print new last name, or XXX if you don't want to update it: ");
    String ln = in.next();
    System.out.print("Please print customer's new pin, or -1 if you don't want to update it: ");
    int pin = in.nextInt();
    System.out.print("Please print new account number, or -1 if you don't want to update it: ");
    long acct = in.nextLong();
    System.out.print("Please enter new starting balance, or -1 if you don't want to update it: ");
    double bal = in.nextDouble();
    System.out.print("Please print new city, or XXX if you don't want to update it: ");
    String city = in.nextLine();
    System.out.println(" c");
    System.out.print("Please print new zip code, or XXX if you don't want to update it: ");
    String zip = in.next();
    System.out.print("Please print new country, or XXX if you don't want to update it: ");
    String country = in.nextLine();
    System.out.print("Please print new region, or XXX if you don't want to update it: ");
    String region = in.nextLine();
    System.out.print("Please print new salary, or XXX if you don't want to update it: ");
    String salary = in.next();
    System.out.print("Please print new account status, or XXX if you don't want to update it: ");
    String status = in.next();
    System.out.print("Please print new street address, or XXX if you don't want to update it: ");
    String addr = in.nextLine().trim();
    System.out.print("----");

    Statement stmt = null;
    String set = "UPDATE users SET ";
    String sql = "";
    if (!fn.equals("XXX"))  sql = sql + "first_name = '" + fn + "', ";
    if (!ln.equals("XXX"))  sql = sql + "last_name = '" + ln + "', ";
    if (!addr.equals("XXX"))  sql = sql + "street_address = '" + addr + "', ";
    if (!city.equals("XXX")) sql = sql + "city = '" + city + "', ";
    if (!zip.equals("XXX")) sql = sql + "zip = '" + zip + "', ";
    if (!country.equals("XXX")) sql = sql + "country = '" + country + "', ";
    if (!region.equals("XXX")) sql = sql + "region = '" + region + "', ";
    if (!salary.equals("XXX")) sql = sql + "salary = '" + salary + "', ";

    sql = sql.substring(0, sql.length()-2) + " WHERE id = " + ID;
    set = set + sql;


    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(set);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    set = "UPDATE customers SET ";
    sql = "";
    if (pin != -1)  sql = sql + "pin = '" + pin + "', ";
    if (acct != -1)  sql = sql + "account_number = '" + acct + "', ";
    if (bal != -1)  sql = sql + "balance = '" + bal + "', ";
    if (!status.equals("XXX")) sql = sql + "account_status = '" + status + "', ";

    sql = sql.substring(0, sql.length()-2) + " WHERE id = " + ID;
    set = set + sql;


    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(set);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void empCheck(Connection c) {
    Scanner in = new Scanner(System.in);

    System.out.print("Print account ID: ");
    int ID = in.nextInt();

    Statement stmt = null;

    String sql = "SELECT * FROM users WHERE id = " + ID;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    ResultSet res = null;

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }


    try {
      res.next();
      System.out.println("first name: " + res.getString("first_name"));
      System.out.println("last name: " + res.getString("last_name"));
      System.out.println("ID: " + res.getString("id"));
      System.out.println("street address: " + res.getString("street_address"));
      System.out.println("city: " + res.getString("city"));
      System.out.println("zip code: " + res.getString("zip"));
      System.out.println("country: " + res.getString("country"));
      System.out.println("region: " + res.getString("region"));
      System.out.println("salary: " + res.getString("salary"));
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sql = "SELECT * FROM customers WHERE id = " + ID;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }


    try {
      res.next();
      System.out.println("pin: " + res.getInt("pin"));
      System.out.println("account number: " + res.getLong("account_number"));
      System.out.println("balance: " + res.getDouble("balance"));
      System.out.println("last transaction: " + res.getString("last_trans"));
      System.out.println("last transaction amount: " + res.getString("last_amount_trans"));
      System.out.println("account status: " + res.getString("account_status"));
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  private static void empOver(Connection c) {
    Statement stmt = null;

    String sql = "SELECT * FROM users u JOIN customers c ON u.id = c.id;";

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    ResultSet res = null;

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      while(res.next()) {
        System.out.println("first name: " + res.getString("first_name"));
        System.out.println("last name: " + res.getString("last_name"));
        System.out.println("ID: " + res.getString("id"));
        System.out.println("street address: " + res.getString("street_address"));
        System.out.println("city: " + res.getString("city"));
        System.out.println("zip code: " + res.getString("zip"));
        System.out.println("country: " + res.getString("country"));
        System.out.println("region: " + res.getString("region"));
        System.out.println("salary: " + res.getString("salary"));
        System.out.println("pin: " + res.getInt("pin"));
        System.out.println("account number: " + res.getLong("account_number"));
        System.out.println("balance: " + res.getDouble("balance"));
        System.out.println("last transaction: " + res.getString("last_trans"));
        System.out.println("last transaction amount: " + res.getString("last_amount_trans"));
        System.out.println("account status: " + res.getString("account_status"));
        System.out.println("========================================");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void customerPanel(int id, Connection c) {

    Statement stmt = null;
    Scanner in = new Scanner(System.in);

    String sql = "SELECT first_name FROM users WHERE id = " + id + ";";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      while (res.next()) {
        System.out.println("welcome, " + res.getString("first_name") +
                "\nPlease Enter a Command: " +
                "\nStatement ------------- Shows your statement" +
                "\nWithdraw X ------------ Withdraws X from your account" +
                "\nDeposit X ------------- Deposits X to your account" +
                "\nTransfer X ---------- Transfers X from your account to another account of your choice" +
                "\nLeave ----------------- Leave with no further action");
      }
      boolean breaker = true;
      while (breaker) {
        String command = in.next();
        double amt = 0;
        if (command.equals("Statement")) {
          custState(c, id);
        } else if (command.equals("Withdraw")) {
          amt = in.nextInt();
          custWith(c, id, amt);
        } else if (command.equals("Deposit")) {
          amt = in.nextInt();
          custDep(c, id, amt);
        } else if (command.equals("Transfer")) {
          amt = in.nextInt();
          custTran(c, id, amt);
        } else if (command.equals("Leave")) {
          System.out.println("Goodbye.");
          break;
        } else throw new IllegalArgumentException("Invalid command");
        System.out.println("Would you like to do anything else? (Yes/No)");
        String decision = in.next();
        if (decision.equals("No")) {
          breaker = false;
          System.out.println("Goodbye.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static void custState(Connection c, int ID) {
    Statement stmt = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    String sql = "SELECT * FROM transactions WHERE customer_id = " + ID + ";";

    ResultSet res = null;

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      while(res.next()) {
        System.out.println("transaction number: " + res.getInt("transaction_number"));
        System.out.println("customer ID: " + res.getInt("customer_id"));
        System.out.println("transaction date: " + res.getDate("transaction_date"));
        System.out.println("transaction time: " + res.getDate("transaction_date"));
        System.out.println("transaction type: " + res.getString("transaction_type"));
        System.out.println("old balance: " + res.getDouble("old_balance"));
        System.out.println("amount transacted: " + res.getDouble("amount_transacted"));
        System.out.println("new balance: " + res.getDouble("new_balance"));
        System.out.println("======================================");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  private static boolean custWith(Connection c, int id, double amt) {

    Statement stmt = null;

    String sql = "SELECT account_status FROM customers WHERE id = " + id + ";";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      while (res.next()) {
        String t = res.getString("account_status");
        if (t != null && t.equals("frozen")) {
          System.out.println("Account is frozen. Transaction did not occur");
          return false;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    stmt = null;

    sql = "SELECT balance FROM customers WHERE id = " + id + ";";
    res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    double bal = 0;
    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      while(res.next()) {
        bal = res.getDouble("balance");
        if (bal - amt < 0) {
          System.out.println("Insufficient funds. Transaction did not occur");
          return false;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    stmt = null;

    sql = "call withdraw(" + id + ", " + amt + ");";
    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return true;
  }

  private static boolean custDep(Connection c, int id, double amt) {

    Statement stmt = null;

    String sql = "SELECT account_status FROM customers WHERE id = " + id + ";";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      while(res.next()) {
        String t = res.getString("account_status");
        if (t != null && t.equals("frozen")) {
          System.out.println("Account is frozen. Transaction did not occur ");
          return false;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sql = "call deposit(" + id + ", " + amt + ");";

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return true;
  }

  private static boolean custTran(Connection c, int id, double amt) {
    Scanner in = new Scanner(System.in);
    Statement stmt = null;
    System.out.print("Print recipient ID: ");
    int twoid = in.nextInt();

    String sql = "SELECT id FROM customers;";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    boolean valid = false;
    try {
      while(res.next()) {
        if (twoid == res.getInt("id")) valid = true;

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (!valid) { System.out.println("invalid ID"); return false; }

    stmt = null;

    sql = "SELECT account_status FROM customers WHERE id = " + id + ";";
    res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      while (res.next()) {
        String t = res.getString("account_status");
        if (t != null && t.equals("frozen")) {
          System.out.println("Account is frozen. Transaction did not occur  ");
          return false;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    stmt = null;

    sql = "SELECT balance FROM customers WHERE id = " + id + ";";
    res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    double bal = 0;
    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      while(res.next()) {
        bal = res.getDouble("balance");
        if (bal - amt < 0) {
          System.out.println("Insufficient funds. Transaction did not occur ");
          return false;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sql = "call transfer(" + id + "," + twoid + "," + amt + ");";

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return true;

  }

  private static boolean loginEmployee(int id, Connection c) {
    Statement stmt = null;
    Scanner in = new Scanner(System.in);

    String sql = "SELECT login_password FROM employees WHERE id = " + id + ";";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    String pass = "";

    try {
      while(res.next()) {
        pass = res.getString("login_password");

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.print("Please print your password: ");
    if (in.next().equals(pass)) return true;
    else return false;

  }

  private static boolean loginCustomer(int id, Connection c) {
    Statement stmt = null;
    Scanner in = new Scanner(System.in);

    String sql = "SELECT pin FROM customers WHERE id = " + id + ";";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    int pass = 0;

    try {
      while(res.next()) {
        pass = res.getInt("pin");

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.print("Please print your pin: ");
    if (in.nextInt() == pass) return true;
    else return false;

  }

  public static Connection getConnection() {
    Connection c = null;

    try {
      c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","root");
      System.out.println("Database accessed successfully");

    } catch (SQLException sqle) {
      System.out.println("sql exception:" + sqle.getStackTrace());
    }

    //in.close();

    return c;

  }

  public static int validID(Connection c) {
    Statement stmt = null;
    Scanner in = new Scanner(System.in);

    System.out.print("Please print your ID: ");
    int ID = in.nextInt();

    String sql = "SELECT id FROM employees;";
    ResultSet res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  //  int temp = 0;
    try {
      while(res.next()) {
        if (ID == res.getInt("id")) return 0 - ID;

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sql = "SELECT id FROM customers;";
    res = null;

    try {
      stmt = c.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      res = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    try {
      while(res.next()) {
        if (ID == res.getInt("id")) return ID;

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }


    return 0;

  }
}