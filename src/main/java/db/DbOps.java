package db;

import java.sql.*;

/**
 * Created by user376 on 29.08.2015.
 */
public  class DbOps {
    private static Connection conn;


    public  DbOps() {
    }

    public void signUp(String username, String password, String email) throws ClassNotFoundException, SQLException {

        System.out.println("incep");
        // 1. load driver
        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        System.out.println("am obtinut conex");
        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (username,password,email) VALUES (?,?,?)");
        pSt.setString(1, username);
        pSt.setString(2, password);
        pSt.setString(3, email);


        System.out.println("statement");
        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        System.out.println("executat");
        // 6. close the objects
        pSt.close();
        System.out.println("gata");
    }

    /*
    public void demoRead(MyListOfToDoMock mm, int userId) throws ClassNotFoundException, SQLException {
        // 1. load driver

        System.out.println("in db userid:" + userId);

        if (conn == null) {
            System.out.println("wwwwwwwwwwwww uite conex");
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        String query = "SELECT activitate,stare,id FROM \"mytasklist\" where stare=false and \"userId\" = " + userId;
        System.out.println("q read:" + query);
        ResultSet rs = st.executeQuery(query);

        // 6. iterate the result set and print the values
        while (rs.next()) {
            mm.getList().add(new ToDoBean(rs.getInt("id"), rs.getString("activitate")));
            System.out.println(rs.getString("activitate"));
        }

        // 7. close the objects
        rs.close();
        st.close();

    }

    public void demoDelete(int key) throws ClassNotFoundException, SQLException {

        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("update \"mytasklist\" set stare=? where id=?");
        pSt.setBoolean(1, true);
        pSt.setInt(2, key);

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were updated.");
        // 6. close the objects
        pSt.close();
    }
*/

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Ulmeanu_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void AddPost (String usernamePost, String post) throws ClassNotFoundException, SQLException {

        System.out.println("incep add");
        // 1. load driver
        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        System.out.println("am obtinut conex");

        //selectare id in functie de username
        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        String query = "SELECT id FROM users where username='"+ usernamePost+"'";
        System.out.println("q read:" + query);
        ResultSet rs = st.executeQuery(query);

        // 6. iterate the result set and print the values
        long iduser =0;
        while (rs.next()) {
            iduser = rs.getLong("id");
            System.out.println("id="+iduser);
        }

        // insert
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO messages (iduser,post, data) VALUES (?,?,now())");
        pSt.setLong(1, iduser);
        pSt.setString(2, post);



        System.out.println("statement");
        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        System.out.println("executat add post");
        // 6. close the objects
        pSt.close();
        System.out.println("gata add post");


    }
    public String viewUsers () throws ClassNotFoundException, SQLException {
        System.out.println("incep vizualizarea");
        // 1. load driver
        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        System.out.println("am obtinut conex");

        // 4. create a query statement
            Statement st = conn.createStatement();

        //afisare toti userii
        // 5. execute a query
        String query = "SELECT username FROM users";
        System.out.println("q read:" + query);
        ResultSet rs = st.executeQuery(query);

        // 6. iterate the result set and print the values
        String listauseri = "";
        while (rs.next()) {
            listauseri += rs.getString("username").trim()+", ";
            System.out.println("users="+listauseri);
        }
        return listauseri;
    }


    public long getUserID (String username) throws ClassNotFoundException, SQLException{
        System.out.println("incep vizualizarea");
        // 1. load driver
        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        System.out.println("am obtinut conex");

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        String query = "SELECT id FROM users where username='"+ username+"'";
        System.out.println("q read:" + query);
        ResultSet rs = st.executeQuery(query);

        // 6. iterate the result set and print the values
        long iduser =0;
        while (rs.next()) {
            iduser = rs.getLong("id");
            System.out.println("id="+iduser);
        }
        return iduser;
    }



    public void addFollowers ( String username, String followers)  throws ClassNotFoundException, SQLException {
        String [] follovers_array = followers.split(",");



        System.out.println("incep vizualizarea");
        // 1. load driver
        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        System.out.println("am obtinut conex");

        // 4. create a query statement
        Statement st = conn.createStatement();


        for (int i = 0; i<follovers_array.length; i++){
            PreparedStatement pSt = conn.prepareStatement("INSERT INTO following (\"Follower\",\"Following\") VALUES (?,?)");
            pSt.setLong(1, getUserID(username));
            pSt.setLong(2, getUserID(follovers_array[i]));
            int rowsInserted = pSt.executeUpdate();
            System.out.println("userul "+getUserID(username)+" urmareste "+getUserID(follovers_array[i]));
        }

    }


}