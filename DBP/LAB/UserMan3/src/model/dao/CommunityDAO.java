package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Community;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * Community ���̺��� Ŀ�´�Ƽ ������ �߰�, ����, ����, �˻� ���� 
 */
public class CommunityDAO {
	private JDBCUtil jdbcUtil = null;
	
	public CommunityDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * Ŀ�´�Ƽ ���̺� ���ο� �� ���� (PK ���� Sequence�� �̿��Ͽ� �ڵ� ����)
	 */
	public Community create(Community comm) throws SQLException {
		String sql = "INSERT INTO Community VALUES (commId_seq.nextval, ?, ?, SYSDATE, ?)";		
		Object[] param = new Object[] {comm.getName(), comm.getDescription(),
			comm.getChairId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = {"cId"};	// PK �÷��� �̸�     
		try {    
			jdbcUtil.executeUpdate(key);  // insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		comm.setId(generatedKey); 	// id�ʵ忡 ����  
		   	}
		   	return comm;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return null;			
	}

	/**
	 * ������ Ŀ�´�Ƽ ������ ����
	 */
	public int update(Community comm) throws SQLException {
		String sql = "UPDATE Community "
					+ "SET cName=?, descr=?, chairId=? "
					+ "WHERE cId=?";
		String chairId = comm.getChairId();
		if (chairId.equals("")) chairId = null;
		Object[] param = new Object[] {comm.getName(), comm.getDescription(),
				chairId, comm.getId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * Ŀ�´�Ƽ�� ȸ���� ����  
	 */
	public int updateChair(Community comm) {
		String sql = "UPDATE Community "
					+ "SET chairId= ? "
					+ "WHERE cId=?";
		Object[] param = new Object[] {comm.getChairId(), comm.getId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/**
	 * �־��� ID�� �ش��ϴ� Ŀ�´�Ƽ ������ ����.
	 */
	public int remove(String commId) throws SQLException {
		String sql = "DELETE FROM Community WHERE cId=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	/**
	 * �־���  ID�� �ش��ϴ� Ŀ�´�Ƽ ������ �����ͺ��̽����� ã�� Community ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public Community findCommunity(int commId) throws SQLException {
        String sql = "SELECT cName, descr, startDate, chairId, u.name As chairName "
        			+ "FROM Community c LEFT OUTER JOIN UserInfo u ON c.chairId = u.userId "
        			+ "WHERE cId=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil�� query���� �Ű� ���� ����
		Community comm = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				comm = new Community(		// Community ��ü�� �����Ͽ� Ŀ�´�Ƽ ������ ����
					commId,
					rs.getString("cName"),
					rs.getString("descr"),
					rs.getDate("startDate"),
					rs.getString("chairId"),
					rs.getString("chairName"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return comm;
	}

	/**
	 * ��ü Ŀ�´�Ƽ ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Community> findCommunityList() throws SQLException {
        String sql = "SELECT cId, cName, descr, COUNT(u.userId) AS numOfMem "
        		   + "FROM Community c LEFT OUTER JOIN UserInfo u ON c.cId = u.commId "
        		   + "GROUP BY cId, cName, descr "
        		   + "ORDER BY cName";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Community> commList = new ArrayList<Community>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Community comm = new Community(			// Community ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getInt("cId"),
						rs.getString("cName"),
						rs.getString("descr"),
						rs.getInt("numOfMem"));
				commList.add(comm);				// List�� Community ��ü ����
			}		
			return commList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �־���  ID�� �ش��ϴ� Ŀ�´�Ƽ�� �����ϴ��� �˻� 
	 */
	public boolean existingCommunity(String commId) throws SQLException {
		String sql = "SELECT count(*) FROM Community WHRE cId=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
}
