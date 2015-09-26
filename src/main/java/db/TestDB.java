package db;

import java.sql.SQLException;

/**
 * Created by dj_fl on 9/26/2015.
 */
public class TestDB {
    public static void main(String[] args) {
//        DbOps user1 = new DbOps();
//        try {
//            user1.signUp("user1", "parola1", "email1@yahoo.com");
//            System.out.println("am adaugat un user");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        DbOps user1 = new DbOps();
        try {
            user1.AddPost("Flaviu", "ana are mere, noua ne iese");
            System.out.println("am adaugat o postare");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
