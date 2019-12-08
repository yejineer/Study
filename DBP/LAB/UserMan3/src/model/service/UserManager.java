package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Community;
import model.User;
import model.dao.CommunityDAO;
import model.dao.UserDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private CommunityDAO commDAO;
	private UserAnalysis userAanlysis;

	private UserManager() {
		try {
			userDAO = new UserDAO();
			commDAO = new CommunityDAO();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAO.create(user);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		int oldCommId = findUser(user.getUserId()).getCommId();
		if (user.getCommId() != oldCommId) { 	// �Ҽ� Ŀ��Ƽ�ϰ� �����
			Community comm = commDAO.findCommunity(oldCommId);  // ���� �Ҽ� Ŀ�´�Ƽ
			if (comm != null && user.getUserId().equals(comm.getChairId())) {
				// ����ڰ� ���� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
				comm.setChairId(null);
				commDAO.updateChair(comm);
			}
		}
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException, UserNotFoundException {
		int commId = findUser(userId).getCommId();
		Community comm = commDAO.findCommunity(commId);  // �Ҽ� Ŀ�´�Ƽ
		if (comm != null && userId.equals(comm.getChairId())) {
			// ����ڰ� �Ҽ� Ŀ�´�Ƽ�� ȸ���� ��� -> �� Ŀ�´�Ƽ�� ȸ���� null�� ���� �� ����
			comm.setChairId(null);
			commDAO.updateChair(comm);
		}
		return userDAO.remove(userId);
	}

	public User findUser(String userId)
		throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return user;
	}

	public List<User> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<User> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

	public List<User> makeFriends(String userId) throws Exception {
		return userAanlysis.recommendFriends(userId);
	}
	
	public Community createCommunity(Community comm) throws SQLException {
		return commDAO.create(comm);		
	}

	public int updateCommunity(Community comm) throws SQLException {
		return commDAO.update(comm);				
	}
	
	public Community findCommunity(int commId) throws SQLException {
		Community comm = commDAO.findCommunity(commId); 
		
		List<User> memberList = userDAO.findUsersInCommunity(commId);
		comm.setMemberList(memberList);
		
		int numOfMembers = userDAO.getNumberOfUsersInCommunity(commId);
		comm.setNumOfMembers(numOfMembers);
		return comm;
	}
	
	public List<Community> findCommunityList() throws SQLException {
		return commDAO.findCommunityList();
	}
	
	public List<User> findCommunityMembers(int commId) throws SQLException {
		return userDAO.findUsersInCommunity(commId);
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}
}
