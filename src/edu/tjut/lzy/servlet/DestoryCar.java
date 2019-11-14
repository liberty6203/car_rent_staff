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

@WebServlet("/DestoryCar")
public class DestoryCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carid = request.getParameter("carid");
		if (carid!=null) {
			String querySql = "select * from car where carid = '"+carid+"'";
			ResultSet rSet = DBUtil.execQuery(querySql);
			try {
				if (rSet.next()) {
					String state = rSet.getString("state");
					if (state.equals("0")) {
						String deleteSql = "delete from car where carid='"+carid+"'";
						int res = DBUtil.execUpdate(deleteSql);
						if (res>0) {
							WebResponse.returnSuccessMsg(response, "删除成功");
						}else {
							WebResponse.returnFailureMsg(response, "删除失败");
						}
					}else{
						WebResponse.returnFailureMsg(response, "车已租出,不能删除");
					}
				}else {
					WebResponse.returnFailureMsg(response, "车辆不存在");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			WebResponse.returnFailureMsg(response, "参数错误");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
