
package dao;

import context.DBContext;
import entity.Account;
import entity.Cart;
import entity.Review;
import entity.SoLuongDaBan;
import entity.TongChiTieuBanHang;
import entity.Supplier;
import entity.Category;
import entity.Invoice;
import entity.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
    public List<SoLuongDaBan> getTop10SanPhamBanChay() {
    List<SoLuongDaBan> list = new ArrayList<>();
    String query = "SELECT * FROM SoLuongDaBan ORDER BY soLuongDaBan DESC LIMIT 10";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            list.add(new SoLuongDaBan(
                    rs.getInt(1),
                    rs.getInt(2)
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
    public List<Invoice> getAllInvoice() {
        List<Invoice> list = new ArrayList<>();
        String query = "select * from Invoice";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4)
                       ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public int countAllProductBySellID(int sell_ID) {
    String query = "SELECT COUNT(*) FROM Product WHERE sell_ID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, sell_ID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

    
   public int getSellIDByProductID(int productID) {
    String query = "SELECT sell_ID FROM Product WHERE id = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, productID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

    
   public double totalMoneyDay(int day) {
    String query = "SELECT SUM(tongGia) " +
                   "FROM Invoice " +
                   "WHERE DAYOFWEEK(ngayXuat) = ? " +
                   "GROUP BY DATE(ngayXuat)";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        ps.setInt(1, day);
        rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getDouble(1);
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return 0;
}

    
    public double totalMoneyMonth(int month) {
    String query = "SELECT SUM(tongGia) FROM Invoice " +
                   "WHERE MONTH(ngayXuat) = ? " +
                   "GROUP BY MONTH(ngayXuat)";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        ps.setInt(1, month);
        rs = ps.executeQuery();
        while (rs.next()) {
           return rs.getDouble(1);
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return 0;
}

    
    public int countAllProduct() {
    String query = "SELECT COUNT(*) FROM Product";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
           return rs.getInt(1);
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return 0;
}

    
   public double sumAllInvoice() {
    String query = "SELECT SUM(tongGia) FROM Invoice";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
           return rs.getDouble(1);
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return 0;
}

    
     public int countAllReview() {
    String query = "SELECT COUNT(*) AS total FROM Review";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            return rs.getInt("total");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}


    
   public int getCateIDByProductID(String id) {
    String query = "SELECT cateID FROM Product WHERE id = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                		rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Supplier> getAllSupplier() {
        List<Supplier> list = new ArrayList<>();
        String query = "select * from Supplier";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Supplier(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                		rs.getInt(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
   
    public List<TongChiTieuBanHang> getTop5KhachHang() {
        List<TongChiTieuBanHang> list = new ArrayList<>();
        String query = "select top(5) *\r\n"
        		+ "from TongChiTieuBanHang\r\n"
        		+ "order by TongChiTieu desc";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TongChiTieuBanHang(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3)
                       ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<TongChiTieuBanHang> getTop5NhanVien() {
    List<TongChiTieuBanHang> list = new ArrayList<>();
    String query = "SELECT * FROM TongChiTieuBanHang ORDER BY TongBanHang DESC LIMIT 5";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new TongChiTieuBanHang(
                rs.getInt(1),
                rs.getDouble(2),
                rs.getDouble(3)
            ));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}


    public List<Product> getTop3() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product LIMIT 3";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    public List<Product> getNext3Product(int amount) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product ORDER BY id LIMIT ?, 3";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, amount);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
   public List<Product> getNext4NikeProduct(int amount) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product WHERE cateID = 2 ORDER BY id DESC LIMIT ?, 4";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, amount);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
   public List<Product> getNext4AdidasProduct(int amount) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product WHERE cateID = 1 ORDER BY id DESC LIMIT ?, 4";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, amount);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
   public List<Product> getProductByCID(String cid) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product WHERE cateID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, cid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    public List<Product> getProductBySellIDAndIndex(int id, int indexPage) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product "
                 + "WHERE sell_ID = ? "
                 + "ORDER BY id "
                 + "LIMIT ?, 5";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ps.setInt(2, (indexPage - 1) * 5);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
  public List<Product> getProductByIndex(int indexPage) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product "
                 + "ORDER BY id "
                 + "LIMIT ?, 9";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, (indexPage - 1) * 9);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setPrice(rs.getDouble("price"));
            product.setTitle(rs.getString("title"));
            product.setDescription(rs.getString("description"));
            product.setModel(rs.getString("model"));
            product.setColor(rs.getString("color"));
            product.setDelivery(rs.getString("delivery"));
            product.setImage2(rs.getString("image2"));
            product.setImage3(rs.getString("image3"));
            product.setImage4(rs.getString("image4"));
            
            list.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


  public List<Product> searchByName(String txtSearch) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product\n"
                 + "WHERE name LIKE ?";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        ps.setString(1, "%" + txtSearch + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14)
            ));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}


    
   public List<Invoice> searchByNgayXuat(String ngayXuat) {
    List<Invoice> list = new ArrayList<>();
    String query = "SELECT * FROM Invoice\n"
                 + "WHERE ngayXuat = ?";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        ps.setString(1, ngayXuat);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Invoice(
                rs.getInt(1),
                rs.getInt(2),
                rs.getDouble(3),
                rs.getDate(4)
            ));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}

    
    public List<Product> searchPriceUnder100() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product\n"
                 + "WHERE price < 100";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14)
            ));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}

    
    public List<Product> searchPrice100To200() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where [price] >= 100 and [price]<=200";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> searchColorWhite() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'White'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> searchColorGray() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'Gray'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> searchColorBlack() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'Black'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Product> searchColorYellow() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where color = 'Yellow'";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<Product> searchByPriceMinToMax(String priceMin,String priceMax) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\r\n"
        		+ "where [price] >= ? and [price]<=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, priceMin);
            ps.setString(2, priceMax);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
   public List<Product> searchPriceAbove200() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product\n"
                 + "WHERE price > 200";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14)
            ));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}

    
    public List<Product> getRelatedProduct(int cateIDProductDetail) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product WHERE cateID = ? ORDER BY id DESC LIMIT 4";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        ps = conn.prepareStatement(query);
        ps.setInt(1, cateIDProductDetail);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14)
            ));
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
    return list;
}

    
    
    public List<Review> getAllReviewByProductID(String productId) {
    List<Review> list = new ArrayList<>();
    String query = "SELECT * FROM Review WHERE productID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, productId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("accountID"),
                        rs.getString("contentReview"),
                        rs.getDate("dateReview")
                ));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}




  public Product getProductByID(String id) {
    String query = "SELECT * FROM Product WHERE id = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Product(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getDouble("price"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("model"),
                    rs.getString("color"),
                    rs.getString("delivery"),
                    rs.getString("image2"),
                    rs.getString("image3"),
                    rs.getString("image4"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
   public List<Cart> getCartByAccountID(int accountID) {
    List<Cart> list = new ArrayList<>();
    String query = "SELECT * FROM Cart WHERE accountID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, accountID);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Cart(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


   public Cart checkCartExist(int accountID, int productID) {
    String query = "SELECT * FROM Cart WHERE accountID = ? AND productID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, accountID);
        ps.setInt(2, productID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Cart(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
    public int checkAccountAdmin(int userID) {
    String query = "SELECT isAdmin FROM Account WHERE uID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("isAdmin");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

    
    public TongChiTieuBanHang checkTongChiTieuBanHangExist(int userID) {
    String query = "SELECT * FROM TongChiTieuBanHang WHERE userID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new TongChiTieuBanHang(
                    rs.getInt(1),
                    rs.getDouble(2),
                    rs.getDouble(3)
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
 public SoLuongDaBan checkSoLuongDaBanExist(int productID) {
    String query = "SELECT * FROM SoLuongDaBan WHERE productID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, productID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new SoLuongDaBan(
                        rs.getInt(1),
                        rs.getInt(2)
                );
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


    
    
    
    public List<Category> getAllCategory() {
    List<Category> list = new ArrayList<>();
    String query = "SELECT * FROM Category";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Category(
                    rs.getInt(1),
                    rs.getString(2)
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
    
//
   public Product getLast() {
    String query = "SELECT * FROM Product\n"
            + "ORDER BY id DESC\n"
            + "LIMIT 1";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            return new Product(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
    public List<Product> get8Last() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product\n"
            + "ORDER BY id DESC\n"
            + "LIMIT 8";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Product(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
    public List<Product> get4NikeLast() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product\n"
            + "WHERE cateID = 2\n"
            + "ORDER BY id DESC\n"
            + "LIMIT 4";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Product(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    
   public List<Product> get4AdidasLast() {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product\n"
            + "WHERE cateID = 1\n"
            + "ORDER BY id DESC\n"
            + "LIMIT 4";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Product(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    public Account login(String user, String pass) {
    String query = "SELECT * FROM Account WHERE `user` = ? AND pass = ?";
    try {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Account account = new Account(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6)
            );
            rs.close();
            ps.close();
            conn.close();
            return account;
        }
        rs.close();
        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


    public Account checkAccountExist(String user) {
    String query = "SELECT * FROM Account\n"
            + "WHERE user = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, user);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
    public Account checkAccountExistByUsernameAndEmail(String username, String email) {
        String query = "select * from Account where [user]=? and [email]=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                		rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
 public Review getNewReview(int accountID, int productID) {
    String query = "SELECT * FROM Review "
            + "WHERE accountID = ? AND productID = ? "
            + "ORDER BY maReview DESC "
            + "LIMIT 1";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, accountID);
        ps.setInt(2, productID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Review(
                        rs.getInt("id"),
                        rs.getInt("accountID"),
                        rs.getString("contentReview"),
                        rs.getDate("dateReview")
                );
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}




public void singup(String user, String pass, String email) {
    String query = "INSERT INTO Account(`user`, pass, isSell, isAdmin, email) VALUES (?, ?, 0, 0, ?)";
    try {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user);
        ps.setString(2, pass);
        ps.setString(3, email);
        ps.executeUpdate();

        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public void deleteInvoiceByAccountId(String id) {
    String query = "DELETE FROM Invoice WHERE accountID = ?";
    try {
        conn = new DBContext().getConnection(); // Mở kết nối với SQL
        ps = conn.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
}

    
    public void deleteTongChiTieuBanHangByUserID(String id) {
        String query = "delete from TongChiTieuBanHang\n"
                + "where [userID] = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
  public void deleteProduct(String pid) {
    String query = "DELETE FROM Product WHERE id = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, pid);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
   public void deleteProductBySellID(String id) {
    String query = "DELETE FROM Product WHERE sell_ID = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void deleteCartByAccountID(int accountID) {
    String query = "DELETE FROM Cart WHERE accountID = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void deleteCartByProductID(String productID) {
    String query = "DELETE FROM Cart WHERE productID = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, productID);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
   public void deleteSoLuongDaBanByProductID(String productID) {
    String query = "DELETE FROM SoLuongDaBan WHERE productID = ?";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, productID);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void deleteReviewByProductID(String productID) {
    String query = "DELETE FROM Review WHERE productID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, productID);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void deleteReviewByAccountID(String id) {
    String query = "DELETE FROM Review WHERE accountID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void deleteAccount(String id) {
    String query = "DELETE FROM Account WHERE uID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, id);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void deleteSupplier(String idSupplier) {
        String query = "delete from Supplier\r\n"
        		+ "where idSupplier=?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, idSupplier);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteCart(int productID) {
        String query = "delete from Cart where productID = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price, String title, String description, String category, int sid, String model, String color, String delivery, String image2, String image3, String image4) {
    String query = "INSERT INTO Product(name, image, price, title, description, cateID, sell_ID, model, color, delivery, image2, image3, image4) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
       
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        
        ps.setString(1, name);
        ps.setString(2, image);
        ps.setString(3, price);
        ps.setString(4, title);
        ps.setString(5, description);
        ps.setString(6, category);
        ps.setInt(7, sid);
        ps.setString(8, model);
        ps.setString(9, color);
        ps.setString(10, delivery);
        ps.setString(11, image2);
        ps.setString(12, image3);
        ps.setString(13, image4);

       
        ps.executeUpdate();

       
        ps.close();
        conn.close();
    } catch (Exception e) {
       
        e.printStackTrace();
    }
}

    
public void insertAccount(String user, String pass, String isSell, String isAdmin, String email) {
    String query = "INSERT INTO Account(`user`, pass, isSell, isAdmin, email) VALUES (?, ?, ?, ?, ?)";
    try {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user);
        ps.setString(2, pass);
        ps.setString(3, isSell);
        ps.setString(4, isAdmin);
        ps.setString(5, email);
        ps.executeUpdate();

        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void insertTongChiTieuBanHang(int userID, double tongChiTieu, double tongBanHang) {
        String query = "insert TongChiTieuBanHang(userID,TongChiTieu,TongBanHang)\r\n"
        		+ "values(?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setDouble(2, tongChiTieu);
            ps.setDouble(3, tongBanHang);
        
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void insertSoLuongDaBan(int productID, int soLuongDaBan) {
        String query = "insert SoLuongDaBan(productID,soLuongDaBan)\r\n"
        		+ "values(?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, soLuongDaBan);
           
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void insertSupplier(String nameSupplier, String phoneSupplier, String emailSupplier, String addressSupplier, String cateID) {
        String query = "insert Supplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID) \r\n"
        		+ "values(?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, nameSupplier);
            ps.setString(2, phoneSupplier);
            ps.setString(3, emailSupplier);
            ps.setString(4, addressSupplier);
            ps.setString(5, cateID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
   private static java.sql.Date getCurrentDate() {
    return java.sql.Date.valueOf(java.time.LocalDate.now());
}

  
  public void insertReview(int accountID, int productID, String contentReview) {
    String query = "INSERT INTO Review (accountID, productID, contentReview, dateReview) VALUES (?, ?, ?, ?)";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, accountID);
        ps.setInt(2, productID);
        ps.setString(3, contentReview);
        ps.setDate(4, getCurrentDate());
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
  
  
 



    
    public void insertInvoice(int accountID, double tongGia) {
        String query = "insert Invoice(accountID,tongGia,ngayXuat)\r\n"
        		+ "values(?,?,?)";

        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setDouble(2, tongGia);
            ps.setDate(3,getCurrentDate());
            ps.executeUpdate();
           
        } catch (Exception e) {	
        	
        }
    }
    
    public void insertCart(int accountID, int productID, int amount, String size) {
        String query = "insert Cart(accountID, productID, amount,size)\r\n"
        		+ "values(?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.setString(4, size);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

   public void editProduct(String pname, String pimage, String pprice, String ptitle, String pdescription, String pcategory, 
                        String pmodel, String pcolor, 
                        String pdelivery, String pimage2, String pimage3, String pimage4, String pid) {
    String query = "UPDATE Product\n"
                 + "SET name = ?,\n"
                 + "image = ?,\n"
                 + "price = ?,\n"
                 + "title = ?,\n"
                 + "description = ?,\n"
                 + "cateID = ?,\n"
                 + "model = ?,\n"
                 + "color = ?,\n"
                 + "delivery = ?,\n"
                 + "image2 = ?,\n"
                 + "image3 = ?,\n"
                 + "image4 = ?\n"
                 + "WHERE id = ?";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        ps = conn.prepareStatement(query);
        ps.setString(1, pname);
        ps.setString(2, pimage);
        ps.setString(3, pprice);
        ps.setString(4, ptitle);
        ps.setString(5, pdescription);
        ps.setString(6, pcategory);
        ps.setString(7, pmodel);
        ps.setString(8, pcolor);
        ps.setString(9, pdelivery);
        ps.setString(10, pimage2);
        ps.setString(11, pimage3);
        ps.setString(12, pimage4);
        ps.setString(13, pid);
        ps.executeUpdate();
    } catch (Exception e) {
        // Xử lý ngoại lệ nếu cần
    }
}

    
    
public void editProfile(String username, String password, String email, int uID) {
    String query = "UPDATE Account SET user = ?, pass = ?, email = ? WHERE uID = ?";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setInt(4, uID);
        ps.executeUpdate();
    } catch (Exception e) {
        
    }
}

    
  public void editTongChiTieu(int accountID, double totalMoneyVAT) {
    String query = "CALL proc_CapNhatTongChiTieu(?, ?)";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, accountID);
        cs.setDouble(2, totalMoneyVAT);
        cs.executeUpdate();
        cs.close(); 
    } catch (Exception e) {
        
    }
}

    
   public void editSoLuongDaBan(int productID, int soLuongBanThem) {
    String query = "CALL proc_CapNhatSoLuongDaBan(?, ?)";
    try {
        conn = new DBContext().getConnection();//mo ket noi voi sql
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, productID);
        cs.setInt(2, soLuongBanThem);
        cs.executeUpdate();
        cs.close(); 
    } catch (Exception e) {
        
    }
}

    
public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
    String query = "CALL proc_CapNhatTongBanHang(?, ?)";
    try {
        conn = new DBContext().getConnection();
        CallableStatement cs = conn.prepareCall(query);
        cs.setInt(1, sell_ID);
        cs.setDouble(2, tongTienBanHangThem);
        cs.executeUpdate();
        cs.close(); 
    } catch (Exception e) {
        
    }
}

    
public void editAmountAndSizeCart(int accountID, int productID, int amount, String size) {
    String query = "UPDATE Cart SET amount=?, size=?\n"
            + "WHERE accountID=? AND productID=?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, amount);
        ps.setString(2, size);
        ps.setInt(3, accountID);
        ps.setInt(4, productID);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    public void editAmountCart(int accountID, int productID, int amount) {
    String query = "UPDATE Cart SET amount=?\n"
            + "WHERE accountID=? AND productID=?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, amount);
        ps.setInt(2, accountID);
        ps.setInt(3, productID);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

   public static void main(String[] args) {
        DAO dao = new DAO();

   }

}
