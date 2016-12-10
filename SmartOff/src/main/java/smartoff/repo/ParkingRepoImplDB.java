package smartoff.repo;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import smartoff.pojo.ParkingPojo;

@Component
public class ParkingRepoImplDB {
		private static Connection con;
		static{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//"jdbc:oracle:thin:@myhost:1521:orcl", "scott", "tiger"
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","","");
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		/*public boolean save(ParkingPojo b) {
			try {
				PreparedStatement saveBlog = con.prepareStatement("INSERT INTO park VALUES(NULL,?,?,?,?)");
				String title = b.getTitle();
				String author = b.getAuthor();
				String text = b.getText();
				java.sql.Date date = new java.sql.Date(b.getDate().getTime());
				saveBlog.setString(1, title);
				saveBlog.setString(2, text);
				saveBlog.setDate(3, date);
				saveBlog.setString(4, author);
				int i=saveBlog.executeUpdate();
				if(i>0){
					return true;
				}
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		public ParkingPojo findOne(int id) {
			try{
				PreparedStatement getBlogById = con.prepareStatement("SELECT * FROM blog WHERE id=?");
				getBlogById.setInt(1, id);
				ResultSet blogFound = getBlogById.executeQuery();
				if(blogFound.first()){
					int blogId = blogFound.getInt(1);
					String title = blogFound.getString(2);
					String text = blogFound.getString(3);
					java.util.Date date = blogFound.getDate(4);
					String author = blogFound.getString(5);
					List<Comment> comments = new ArrayList<Comment>();
					PreparedStatement getComments = con.prepareStatement("SELECT * FROM comments WHERE blog_id=?");
					getComments.setInt(1, blogId);
					ResultSet foundComments = getComments.executeQuery();
					while(foundComments.next()){
						Comment comment = new Comment();
						comment.setId(foundComments.getInt(1));
						comment.setUser(foundComments.getString(2));
						comment.setComment(foundComments.getString(3));
						comment.setUpCount(foundComments.getInt(4));
						comment.setDownCount(foundComments.getInt(5));
						comments.add(comment);
					}
					ParkingPojo blog = new ParkingPojo(blogId);
					blog.setAuthor(author);
					blog.setDate(date);
					blog.setText(text);
					blog.setTitle(title);
					blog.setComments(comments);
					return blog;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		public List<ParkingPojo> findAll() {
			// TODO Auto-generated method stub
			try{
				PreparedStatement getBlogs = con.prepareStatement("SELECT * FROM blog");
				ResultSet blogsFound = getBlogs.executeQuery();
				List<ParkingPojo> blogs= new ArrayList<ParkingPojo>();
				while(blogsFound.next()){
					int blogId = blogsFound.getInt(1);
					String title = blogsFound.getString(2);
					String text = blogsFound.getString(3);
					java.util.Date date = blogsFound.getDate(4);
					String author = blogsFound.getString(5);
					List<Comment> comments = new ArrayList<Comment>();
					PreparedStatement getComments = con.prepareStatement("SELECT * FROM comments WHERE blog_id=?");
					getComments.setInt(1, blogId);
					ResultSet foundComments = getComments.executeQuery();
					while(foundComments.next()){
						Comment comment = new Comment();
						comment.setId(foundComments.getInt(1));
						comment.setUser(foundComments.getString(2));
						comment.setComment(foundComments.getString(3));
						comment.setUpCount(foundComments.getInt(4));
						comment.setDownCount(foundComments.getInt(5));
						comments.add(comment);
					}
					ParkingPojo blog = new ParkingPojo(blogId);
					blog.setAuthor(author);
					blog.setDate(date);
					blog.setText(text);
					blog.setTitle(title);
					blog.setComments(comments);
					blogs.add(blog);
				}
				return blogs;
			}catch(Exception e){
			}
			return null;
		}
		public String addComment(int blogId, Comment comment){
			int i = 0;
			try{
				PreparedStatement saveComment = con.prepareStatement("INSERT INTO comments VALUES(NULL,?,?,?,?,?)");
				saveComment.setString(1, comment.getUser());
				saveComment.setString(2, comment.getComment());
				saveComment.setInt(3, comment.getUpCount());
				saveComment.setInt(4, comment.getDownCount());
				saveComment.setInt(5, blogId);
				i = saveComment.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
			if(i>0){
				return "Added";
			}
			return "Not Added";
		}
*/
		public Map<String, String> showSpace() {
			Map<String, String> showMap = new HashMap<String, String>();		
			try{
				PreparedStatement showSpace = con.prepareStatement("SELECT * FROM slots");
				ResultSet space = showSpace.executeQuery();
				while(space.next()){
					showMap.put(space.getString(1), space.getString(2));
				}
			}catch(Exception e){
				System.out.println("showSpace");
					e.printStackTrace();
			}
			return showMap;
		}

		public Map<String, String> allotSpace(ParkingPojo p) {
			Map<String, String> finalResult =null;
			try{
				String emp_id = p.getEmpID();
				String slot_id= p.getSlotID();
				PreparedStatement saveComment = con.prepareStatement("INSERT INTO Slots (emp_ID,slot_ID) VALUES"
						+ "('"+emp_id+"',' "+slot_id+"')");
				saveComment.execute();
				finalResult = showSpace();
			}catch(Exception e){
				System.out.println("allotSpace");
				e.printStackTrace();
			}
			return finalResult;
		}
		
		public java.util.List<String> displayFreeSpace(){
			java.util.List<String> emptySlots = new ArrayList<String>();
			try{
				PreparedStatement showSpace = con.prepareStatement("SELECT slot_ID FROM slots WHERE emp_ID=''");
				ResultSet space = showSpace.executeQuery();
				while(space.next()){
					emptySlots.add(space.getString(1));
				}
			}catch(Exception e){
				System.out.println("display");
				e.printStackTrace();
		}return emptySlots;
		}
		
		public String deallocateParkingSpace(ParkingPojo p){
			String slot_ID = "";
			try{
				String emp_id = p.getEmpID();
				PreparedStatement saveComment = con.prepareStatement("SELECT slot_ID from slots where emp_ID="+emp_id);
				ResultSet space = saveComment.executeQuery();
				while(space.next()){
					slot_ID = space.getString(1);
				}
				PreparedStatement saveComment2 = con.prepareStatement("DELETE emp_ID FROM slots where emp_ID="+emp_id);
				saveComment2.execute();
		}catch(Exception e){
			System.out.println("display");
			e.printStackTrace();
			return slot_ID+" Not empty - system failure";
		}
			return slot_ID+ " is Empty Now";
		}
}
	
