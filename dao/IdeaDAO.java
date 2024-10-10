package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.IdeaDTO;

public class IdeaDAO {

	private String username = "root";
	private String password = "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	private Connection conn = null;

	public static IdeaDAO ideadao = null;

	public static IdeaDAO getInstance() {
		if (ideadao == null) {
			ideadao = new IdeaDAO();
		}
		return ideadao;
	}

	private IdeaDAO() {
		init();
	}

	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("11");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private boolean conn() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public void insert(IdeaDTO idea) {
		if (conn()) {
			try {
				String sql = "insert into openidea values (idea_seq.nextval,?,?,?)";
				// sql에서와 다르게 java에서 시퀀스 사용은 시퀀스명.nextval
				PreparedStatement prmt = conn.prepareStatement(sql);
				prmt.setString(1, idea.getTitle());
				prmt.setString(2, idea.getMemo());
				prmt.setString(3, idea.getName());

				int resultInt = prmt.executeUpdate();
				if (resultInt > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
				}
			}
		} else {
			System.out.println("입력 오류");
		}
	}

	public void delete(int delNum) {
		if (conn()) {
			try {
				String sql = "delete from openidea where num = ?";
				PreparedStatement prmt = conn.prepareStatement(sql);
				prmt.setInt(1, delNum);

				int resultInt = prmt.executeUpdate();
				if (resultInt > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}

			} catch (Exception e) {

			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
				}
			}
		}
	}

	public ArrayList<IdeaDTO> selectOne(String idea) {
		ArrayList<IdeaDTO> ilist = new ArrayList<IdeaDTO>();
		if (conn()) {
			try {
				String sql = "select * from openidea where title like '%?%'";
				PreparedStatement prmt = conn.prepareStatement(sql);
				prmt.setString(1, idea);
				ResultSet rs = prmt.executeQuery();
				while (rs.next()) {
					IdeaDTO idto = new IdeaDTO();
					idto.setNum(rs.getInt("num"));
					idto.setTitle(rs.getString("title"));
					idto.setMemo(rs.getString("memo"));
					idto.setName(rs.getString("name"));
					ilist.add(idto);
				}
			} catch (Exception e) {
				System.out.println("21");
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
				}
			}
		}
		return ilist;
	}

	public ArrayList<IdeaDTO> selectNumTitle() {
		ArrayList<IdeaDTO> ilist = new ArrayList<IdeaDTO>();
		if (conn()) {
			try {
				String sql = "select num, title from openidea";
				PreparedStatement prmt = conn.prepareStatement(sql);
				ResultSet rs = prmt.executeQuery();

				while (rs.next()) {
					IdeaDTO idto = new IdeaDTO();
					idto.setNum(rs.getInt("num"));
					idto.setTitle(rs.getString("title"));
					ilist.add(idto);
				}

			} catch (Exception e) {
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
				}
			}
		}
		return ilist;
	}

	public ArrayList<IdeaDTO> selectAll() {
		ArrayList<IdeaDTO> ilist = new ArrayList<IdeaDTO>();
		if (conn()) {
			try {
				String sql = "select * from openidea";
				PreparedStatement prmt = conn.prepareStatement(sql);
				ResultSet rs = prmt.executeQuery();

				while (rs.next()) {
					IdeaDTO idto = new IdeaDTO();
					idto.setNum(rs.getInt("num"));
					idto.setTitle(rs.getString("title"));
					idto.setMemo(rs.getString("memo"));
					idto.setName(rs.getString("name"));
					ilist.add(idto);
				}

			} catch (Exception e) {
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e2) {
				}
			}
		}
		return ilist;
	}

}
