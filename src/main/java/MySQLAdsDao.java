import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class MySQLAdsDao implements Ads {
 private Connection connection;

 public MySQLAdsDao(Config config) {
  try {
   DriverManager.registerDriver(new Driver());
   connection = DriverManager.getConnection(
           config.getUrl(),
           config.getUsername(),
           config.getPassword()
   );
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

 @Override
 public List<Ad> all() {
  List<Ad> allads = new ArrayList<>();
  try {
   String select = "SELECT * FROM ads";
   Statement statement = connection.createStatement();
   ResultSet rs = statement.executeQuery(select);

   while (rs.next()) {
    Ad result = new Ad(
            rs.getInt("ad_id"),
            rs.getInt("user_id"),
            rs.getString("ad_title"),
            rs.getString("ad_des")
    );
    allads.add(result);
   }
  }catch (SQLException e) {
   e.printStackTrace();
  }
  return allads;
 }

 @Override
 public Long insert(Ad ad){
  long lastInsertID = 0;
  String adTitle = ad.getTitle();
  String adDes = ad.getDescription();
  Long userId = ad.getUserId();
  String insert = String.format("INSERT INTO ads(user_id, ad_title, ad_des)" + "Values (%d, '%s', '%s')", userId, adTitle, adDes);
  try {
   Statement statement = connection.createStatement();
   statement.executeUpdate(insert, Statement.RETURN_GENERATED_KEYS);
   ResultSet rs = statement.getGeneratedKeys();
   while (rs.next()) {
    lastInsertID = rs.getLong(1);
   }
  }catch (SQLException e) {
   e.printStackTrace();
  }
  return lastInsertID;
 }
}
