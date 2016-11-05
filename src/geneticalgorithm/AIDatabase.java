/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author jirkazbor
 */
public class AIDatabase {
    
    public Connection connection;
    
    public PreparedStatement statement;
    
    public AIDatabase() throws SQLException{
        connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/cdcol?user=root&password=");
        PreparedStatement add = (PreparedStatement) connection.prepareStatement("INSERT INTO slovnik(cesky) VALUES(?)");
        add.setString(1, "test");
        statement = (PreparedStatement) connection.prepareStatement("SELECT * from slovnik");
        ResultSet result = statement.executeQuery();
        
        while(result.next()){
            System.out.println(result.getString("cesky"));
        }
    }
}
