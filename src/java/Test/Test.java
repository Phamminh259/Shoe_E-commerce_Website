
package Test;
import context.DBContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Test {
    public static void main(String[] args) throws Exception {
        DBContext db = new DBContext();
      
            try {
                Connection connect = db.getConnection() ;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex){
                 Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
}