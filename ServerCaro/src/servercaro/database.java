package servercaro;

import java.sql.*;

public class database {

    Connection con=null;
    PreparedStatement pst;
    ResultSet rs;

    private static final String DB_NAME = "mydb?useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Hh0302@@";

    database() {
        try {
            //CHẮC CHẮN ĐÃ IMPORT DRIVER mysql_connector.jar VÀO java/lib TRƯỚC KHI LÀM ĐIỀU NÀY
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3300/" + DB_NAME, DB_USERNAME, DB_PASSWORD);
            pst = con.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkLogin(String uname, String pwd) {
        try {
            pst.setString(1, uname); //Thay vào tham số "?" đầu tiên trogn query cho username
            pst.setString(2, pwd);    //Thay vào tham số "?" thứ hai trong query cho password
            //Thực thi prepared statement
            rs = pst.executeQuery();

            //Kiểm tra tính tồn tại của tài khoản
            if (rs.next()) {
                //TRUE nếu tìm thấy bản ghi phù hợp
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("error while validating" + e);
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        database d= new database();
        if(d.con!=null){
            System.out.println("hai");
        }
              
    }
}
