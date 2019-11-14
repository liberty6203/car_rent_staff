package edu.tjut.lzy.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tjut.lzy.dao.DBUtil;
import edu.tjut.lzy.util.WebResponse;

/**
 * Servlet implementation class ReturnCar
 */
@WebServlet("/ReturnCar")
public class ReturnCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientid = request.getParameter("clientid");
		String carid = request.getParameter("carid");
		
		String sqlQuery = "select * from lease where "
				+ "carid='"+carid+"' and "
				+ "clientid = '"+clientid+"'";
		ResultSet rSet = DBUtil.execQuery(sqlQuery);
		try {
			if (rSet.next()) {
				String sqlDelete = "delete from lease where "
						+ "carid='"+carid+"' and "
						+ "clientid = '"+clientid+"'";
				System.out.println(sqlDelete);
				String sqlUpdate = "update car set state = 0 where carid='"+carid+"'";
				System.out.println(sqlUpdate);
				int del = DBUtil.execUpdate(sqlDelete);
				int upd = DBUtil.execUpdate(sqlUpdate);
				if (del>0&&upd>0) {
					WebResponse.returnSuccessMsg(response, "还车成功");
				}else{
					WebResponse.returnFailureMsg(response, "还车失败");
				}
			}else {
				WebResponse.returnFailureMsg(response, "车辆已还，或未租出,或租给别人了");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
