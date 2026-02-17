package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;
import java.util.Date;

public class DAOUtility {
    
    public static final int TERMID_SP26 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
                while(rs.next())
                {
                    int cols = rs.getMetaData().getColumnCount();
                    JsonObject jsonRecord = new JsonObject();
                    for (int k = 1; k <= cols; ++k)
                    {
                        String colName = rs.getMetaData().getColumnName(k);
                        String colType = rs.getMetaData().getColumnTypeName(k);
                        if (colType == "INT UNSIGNED")
                        {
                            int item = rs.getInt(colName);
                            jsonRecord.put(colName, item);
                        }
                        else if (colType == "CHAR" || colType == "VARCHAR")
                        {
                            String item = rs.getString(colName);
                            jsonRecord.put(colName, item);
                        }
                        else if (colType == "TIME")
                        {
                            Time item = rs.getTime(colName);
                            jsonRecord.put(colName, item.toString());
                        }
                    }
                    records.add(jsonRecord);
                }

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
