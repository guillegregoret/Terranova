import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;

public class QueryToTable{
	private static QueryToTable single;
	
	private QueryToTable() {}
	
	public static QueryToTable getSingle() {
		if(!(single != null))	single = new QueryToTable();	
		return single;
	}
	
	public Object[] queryToTable(ResultSet rs){
		try{
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			Vector<String> columnNames = new Vector<String>();
			for(int i = 1 ; i <= columnCount ; i++) {
				columnNames.add(metaData.getColumnName(i));
			}
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while(rs.next()) {
				Vector<Object> arrayAux = new Vector<Object>();
				for(int i = 1 ; i <= columnCount ; i++) {
					arrayAux.add(rs.getObject(i));
				}
				data.add(arrayAux);
			}
			
			Object [] result = new Object[2];
			result[0] = data;
			result[1] = columnNames;
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}