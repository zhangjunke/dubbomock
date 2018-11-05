package com.junker.dubbomock.frontend.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.junker.dubbomock.frontend.data.FormData;
import com.junker.dubbomock.frontend.data.MockDetailData;
import com.junker.dubbomock.frontend.servlet.Log4jInitServlet;
import com.junker.dubbomock.frontend.util.PropertiesUtil;
import com.junker.dubbomock.frontend.util.SQLUtil;
import org.apache.log4j.Logger;


public class SearchMockConfDAO {
	private static Logger logger = Logger.getLogger(Log4jInitServlet.class);
	private static String DEPARTMENT_SEARCH = "";
	private static String SERVER_SEARCH = "";
	private static String USER_SEARCH = "";
	private static String MOCKAPI_SEARCH = "";
	private static String MOCKDETAIL_SEARCH = "";
	private static String MOCKCONDITION_SEARCH = "";
	private static String MOCKCONDITION_UPDATE = "";
	private static String MOCKDETAIL_UPDATE = "";
	private static String MOCKDETAIL_CREATE = "";
	private static String MOCKCONDITION_CREATE = "";
	private static String MOCKSETTING_MATCH = "";
	private static String MOCKDETAIL_DELETE ="";
	private static String DETAILAUTHOR_SEARCH="";
	private static String MOCKCONDITION_DELETE="";
	private static String MOCKAPIID_SEARCH="";
	private static String USERID_SEARCH="";
	private static String MOCKDETAILID_SEARCH="";
	private static String AUTHORCREATE="";
	private static String MOCKDETAIL_FUZZYSEARCH="";
	private static String USER_SEARCH_BYID="";
	private static String MOCKAPIPARA_SEARCH="";
	private static String MOCKDETAILID_SEARCH_BYMSGADNAUTHOR="";
	private static String MOCKCONDITIONID_SEARCH="";
	private static PropertiesUtil pu = new PropertiesUtil();


	public HashMap<Integer,String> departmentSearch() throws SQLException, ClassNotFoundException {
		DEPARTMENT_SEARCH = pu.getPropertiesValue("DEPARTMENT_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(DEPARTMENT_SEARCH);
		ResultSet rs = pstmt.executeQuery();
		HashMap<Integer,String> department=new HashMap<Integer,String>();
		FormData fd=new FormData();

		int i=1;
		String aa []={""};
		List<String> value=new ArrayList();
		while(rs.next()){
			String s=rs.getString("department");
			if(!value.contains(s)){
				value.add(s);
				department.put(i,s);
				i++;
			}
		}
		rs.close();
		con.close();
		pstmt.close();
		fd.setDepartment(department);
		return department;
	}


	public HashMap<Integer,String> ServerSearch() throws SQLException, ClassNotFoundException {
		SERVER_SEARCH = pu.getPropertiesValue("SERVER_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(SERVER_SEARCH);
		ResultSet rs = pstmt.executeQuery();
		HashMap<Integer,String> server=new HashMap<Integer,String>();
		FormData fd=new FormData();
		int i=1;
		String aa []={""};
		List<String> value=new ArrayList();
		while(rs.next()){
			String s=rs.getString("mockServer_name");
			if(!value.contains(s)){
				value.add(s);
				server.put(i,s);
				i++;
			}
		}

		rs.close();
		con.close();
		pstmt.close();
		fd.setServer(server);
		return server;
	}

	public HashMap<Integer,String> userSearch(String department) throws SQLException, ClassNotFoundException {
		USER_SEARCH = pu.getPropertiesValue("USER_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(USER_SEARCH);
		pstmt.setString(1,department);
		ResultSet rs = pstmt.executeQuery();
		HashMap<Integer,String> user=new HashMap<Integer,String>();
		FormData fd=new FormData();
		int i=1;
		while(rs.next()){
			String s=rs.getString("name");
			user.put(i,s);
			i++;
		}
		logger.debug("users:"+user);
		rs.close();
		con.close();
		pstmt.close();
		fd.setUser(user);
		return user;
	}

	public String mockDetailIdSearch(String mockMsg,String author_id) throws SQLException, ClassNotFoundException {
		MOCKDETAILID_SEARCH_BYMSGADNAUTHOR = pu.getPropertiesValue("MOCKDETAILID_SEARCH_BYMSGADNAUTHOR");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKDETAILID_SEARCH_BYMSGADNAUTHOR);
		pstmt.setString(1,mockMsg);
		pstmt.setString(2,author_id);
		ResultSet rs = pstmt.executeQuery();
		String detailId="";
		int i=1;
		while(rs.next()){
			detailId=rs.getString("id");
			i++;
		}
		logger.debug("detailId:"+detailId);
		System.out.println("detailId:"+detailId);
		rs.close();
		con.close();
		pstmt.close();
		return detailId;
	}

	public HashMap<Integer,String> mockAPISearch(String servername) throws SQLException, ClassNotFoundException {
		MOCKAPI_SEARCH = pu.getPropertiesValue("MOCKAPI_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKAPI_SEARCH);
		pstmt.setString(1,servername);
		ResultSet rs = pstmt.executeQuery();
		HashMap<Integer,String> mockAPI=new HashMap<Integer,String>();
		FormData fd=new FormData();
		int i=1;
		while(rs.next()){
			String s=rs.getString("mockAPI_name");
			mockAPI.put(i,s);
			i++;
		}
		rs.close();
		con.close();
		pstmt.close();
		fd.setMockAPI(mockAPI);
		return mockAPI;
	}


	public HashMap<Integer, HashMap<String,String>> mockDetailsSearch(String server,String mockAPI_name,String department,String user) throws SQLException, ClassNotFoundException {
		Connection con= SQLUtil.getConnection();
		MOCKDETAIL_SEARCH = pu.getPropertiesValue("MOCKDETAIL_SEARCH");
		MOCKDETAIL_FUZZYSEARCH = pu.getPropertiesValue("MOCKDETAIL_FUZZYSEARCH");
		USER_SEARCH_BYID = pu.getPropertiesValue("USER_SEARCH_BYID");
		PreparedStatement pstmt = con.prepareStatement(MOCKDETAIL_SEARCH);
		PreparedStatement pstmt2 = con.prepareStatement(MOCKDETAIL_FUZZYSEARCH);
		PreparedStatement pstmt3 = con.prepareStatement(USER_SEARCH_BYID);

		MockDetailData mdd = new MockDetailData();
		ResultSet rs = null;
		if (null!=user) {
			pstmt.setString(1, server);
			pstmt.setString(2, mockAPI_name);
			pstmt.setString(3, department);
			pstmt.setString(4, user);
			rs = pstmt.executeQuery();
	}else{
			pstmt2.setString(1, server);
			pstmt2.setString(2, mockAPI_name);
			rs = pstmt2.executeQuery();
		}

		HashMap<Integer, HashMap<String,String>> mockAPIMap = new HashMap<Integer, HashMap<String,String>>();
		String id="";
		String author="";
		String mockType="";
		String mockCaseName="";
		String mock_timeout="";
		String mockResponseMsg="";
		int i=0;
		while (rs.next()) {
			HashMap<String, String> mockAPI = new HashMap<String, String>();
			//待修改
			String authorid=rs.getString("author_id");
			if(authorid.equals("1")){
				author="系统";
			}else{
				pstmt3.setString(1, authorid);
				ResultSet rs2 = pstmt3.executeQuery();
				while (rs2.next()) {
					author = rs2.getString("name");
				}
			}
			id=rs.getString("id");
			mockType=rs.getString("mockType");
			mockCaseName=rs.getString("mockCaseName");
			mock_timeout=rs.getString("mock_timeout");
			mockResponseMsg=rs.getString("mockResponseMsg");
			//String tmp="_"+Integer.toString(i)+"_"+mockType+","+mockCaseName+","+mock_timeout+","+mockCode;
			mockAPI.put("id",id);
			mockAPI.put("author",author);
			mockAPI.put("mockType",mockType);
			mockAPI.put("mockCaseName",mockCaseName);
			mockAPI.put("mock_timeout",mock_timeout);
			mockAPI.put("mockResponseMsg",mockResponseMsg);
			mockAPIMap.put(i,mockAPI);
			i++;
		}
		rs.close();
		con.close();
		pstmt.close();
		mdd.setMockAPIMap(mockAPIMap);
		return mockAPIMap;
	}

	public HashMap<String,String>  mockConditionSearch(String mockDedatil_id) throws SQLException {
		MOCKCONDITION_SEARCH = pu.getPropertiesValue("MOCKCONDITION_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKCONDITION_SEARCH);
		MockDetailData mdd=new MockDetailData();
		pstmt.setString(1,mockDedatil_id);
		ResultSet rs = pstmt.executeQuery();
		HashMap<String,String> conditions=new HashMap<String,String>();
		while(rs.next()){
			String conId=rs.getString("id");
			String conS=rs.getString("mockCondition");
			conditions.put(conId,conS);
		}
		mdd.setConditions(conditions);
		con.close();
		pstmt.close();
		return conditions;
	}

	public int mockConditionUpdate(String conditionId,String newCondition) throws SQLException {
		System.out.println("updateconditionId:"+conditionId);
		System.out.println("updatenewCondition:"+newCondition);
		MOCKCONDITION_UPDATE = pu.getPropertiesValue("MOCKCONDITION_UPDATE");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKCONDITION_UPDATE);
		pstmt.setString(1,newCondition);
		pstmt.setString(2,conditionId);
		int result= pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return result;
	}

	public int mockConditionCreate(String detail_id,String newCondition) throws SQLException {
		MOCKCONDITION_CREATE = pu.getPropertiesValue("MOCKCONDITION_CREATE");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKCONDITION_CREATE);
		pstmt.setString(1,detail_id);
		pstmt.setString(2,newCondition);
		int result=pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return result;
	}

	public int mockConditionDelete(String condition_id) throws SQLException {
		MOCKCONDITION_DELETE = pu.getPropertiesValue("MOCKCONDITION_DELETE");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKCONDITION_DELETE);
		pstmt.setString(1,condition_id);
		int result=pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return result;
	}
	public int mockDetailCreate(String mockAPI_id,String mockType,String mockCaseName,String mock_timeout,String mockResponseMsg,String author_id) throws SQLException {
		MOCKDETAIL_CREATE = pu.getPropertiesValue("MOCKDETAIL_CREATE");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKDETAIL_CREATE);
		pstmt.setString(1,mockAPI_id);
		pstmt.setString(2,mockType);
		pstmt.setString(3,mockCaseName);
		pstmt.setString(4,mock_timeout);
		pstmt.setString(5,mockResponseMsg);
		pstmt.setString(6,author_id);
		int result=pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return result;
	}

	public int mockDetailUpdate(String mockType,String mockCaseName,String mock_timeout,String mockResponseMsg,String mockDetail_id) throws SQLException {
		MOCKDETAIL_UPDATE = pu.getPropertiesValue("MOCKDETAIL_UPDATE");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKDETAIL_UPDATE);
		pstmt.setString(1,mockType);
		pstmt.setString(2,mockCaseName);
		pstmt.setString(3,mock_timeout);
		pstmt.setString(4,mockResponseMsg);
		pstmt.setString(5,mockDetail_id);
		int result= pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return result;
	}

	public int mockDetailDelete(String mockDetail_id) throws SQLException {
		MOCKDETAIL_DELETE = pu.getPropertiesValue("MOCKDETAIL_DELETE");
		DETAILAUTHOR_SEARCH = pu.getPropertiesValue("DETAILAUTHOR_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt1 = con.prepareStatement(MOCKDETAIL_DELETE);
		PreparedStatement pstmt2 = con.prepareStatement(DETAILAUTHOR_SEARCH);
		pstmt2.setString(1,mockDetail_id);
		pstmt1.setString(1,mockDetail_id);
		ResultSet rs = pstmt2.executeQuery();
		String author="";
		while(rs.next()){
			author=rs.getString("author_id");
		}
		int result=0;
		if(author.equals("1")){
			result=10000;
			return result;
		}
		pstmt1.executeUpdate();
		con.close();
		pstmt1.close();
		return result;
	}

	public HashMap<String,String> mockAPIParaSearch(String mockAPI_name) throws SQLException {
		HashMap<String,String> result=new HashMap<String,String>();
		MOCKAPIPARA_SEARCH = pu.getPropertiesValue("MOCKAPIPARA_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKAPIPARA_SEARCH);
		pstmt.setString(1,mockAPI_name);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String input_parameters=rs.getString("input_parameters");
			String output_parameters=rs.getString("output_parameters");
			result.put("input_parameters",input_parameters);
			result.put("output_parameters",output_parameters);
		}
		return result;
	}
	public ArrayList<String> mockSettingMatch(String mockCondition,String mockAPI_name) throws SQLException {
		logger.debug("mockAPI_name:"+mockAPI_name);
		logger.debug("mockCondition:"+mockCondition);
		System.out.println("mockAPI_name:"+mockAPI_name);
		System.out.println("mockCondition:"+mockCondition);
		ArrayList<String> matchList=new ArrayList<String>();
		MOCKSETTING_MATCH = pu.getPropertiesValue("MOCKSETTING_MATCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKSETTING_MATCH);

/*		if(mockCondition.equals("")){
			pstmt.setString(1,"ALL");
			pstmt.setString(2,mockAPI_name);
			ResultSet rs = pstmt.executeQuery();
			int flag=0;
			while (rs.next()&&flag==0) {
				String mock_timeout=rs.getString("mock_timeout");
				String mockResponseMsg=rs.getString("mockResponseMsg");
				System.out.println("mockResponseMsg:"+mockResponseMsg);
				if(null!=mockResponseMsg){
					flag=1;
				}
				matchList.add(mock_timeout);
				matchList.add(mockResponseMsg);
			}
			rs.close();
			con.close();
			pstmt.close();

			return matchList;
		}*/
		String[] eachPara=mockCondition.split("&");
		int count=eachPara.length;
		for(int i=0;i<count;i++){
			String para=eachPara[i];
			pstmt.setString(1,para);
			pstmt.setString(2,mockAPI_name);
			ResultSet rs = pstmt.executeQuery();
			int flag=0;
			while (rs.next()&&flag==0) {
				String mock_timeout=rs.getString("mock_timeout");
				String mockResponseMsg=rs.getString("mockResponseMsg");
				System.out.println("mockResponseMsg:"+mockResponseMsg);
				if(null!=mockResponseMsg){
					flag=1;
				}
				matchList.add(mock_timeout);
				matchList.add(mockResponseMsg);
			}
			rs.close();
		}
		con.close();
		pstmt.close();

		return matchList;
	}

	public String mockAPIidSearch(String APIName) throws SQLException, ClassNotFoundException {
		MOCKAPIID_SEARCH = pu.getPropertiesValue("MOCKAPIID_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKAPIID_SEARCH);
		String APIid="";
		pstmt.setString(1,APIName);

		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			APIid=rs.getString("id");
		}

		rs.close();
		con.close();
		pstmt.close();
		return APIid;
	}

	public String useridSearch(String name) throws SQLException, ClassNotFoundException {
		USERID_SEARCH = pu.getPropertiesValue("USERID_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(USERID_SEARCH);
		String userid="";

			pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			userid=rs.getString("id");
		}
		rs.close();
		con.close();
		pstmt.close();
		return userid;
	}

	public String mockconditionidSearch(String detailId,String condition) throws SQLException, ClassNotFoundException {
		MOCKCONDITIONID_SEARCH = pu.getPropertiesValue("MOCKCONDITIONID_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKCONDITIONID_SEARCH);
		String conditionId="";

		pstmt.setString(1, detailId);
		pstmt.setString(2, condition);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			conditionId=rs.getString("id");
		}
		System.out.println("conditionId"+conditionId);
		rs.close();
		con.close();
		pstmt.close();
		return conditionId;
	}
	public ArrayList<String> modkDetailIdSearch(String mockCondition) throws SQLException, ClassNotFoundException {
		MOCKDETAILID_SEARCH = pu.getPropertiesValue("MOCKDETAILID_SEARCH");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MOCKDETAILID_SEARCH);
		ArrayList<String> modkDetailId=new ArrayList<String>();
		pstmt.setString(1, mockCondition);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			modkDetailId.add(rs.getString("id"));
		}
		rs.close();
		con.close();
		pstmt.close();
		return modkDetailId;
	}
	public int authorCreate(String department,String username) throws SQLException, ClassNotFoundException {
		AUTHORCREATE = pu.getPropertiesValue("AUTHORCREATE");
		Connection con = SQLUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(AUTHORCREATE);
		ArrayList<String> modkDetailId=new ArrayList<String>();
		pstmt.setString(1, department);
		pstmt.setString(2, username);
		int result=pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return result;
	}

	public static void main(String[] args){
		SearchMockConfDAO smcd=new SearchMockConfDAO();
		//HashMap<ArrayList<String>, HashMap<String,String>> userSearch= null;
		ArrayList<String> userSearch=new ArrayList<String>();
		try {
			userSearch = smcd.mockSettingMatch("","");
			//userSearch = smcd.userSearch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(userSearch);
	}
}
