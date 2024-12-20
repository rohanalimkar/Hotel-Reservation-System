package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBState {

	DBConfig config=DBConfig.getInstance();
	Connection con=config.getConn();
	PreparedStatement ps=config.getStatement();
	ResultSet rs=config.getResultSet();
}
