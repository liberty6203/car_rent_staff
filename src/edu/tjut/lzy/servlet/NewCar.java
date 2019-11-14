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
import edu.tjut.lzy.util.EncodeString;
import edu.tjut.lzy.util.WebResponse;

@WebServlet("/NewCar")
public class NewCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carid = request.getParameter("carid");
		if (carid!=null) {
			String querySql = "select * from car where carid='"+carid+"'";
			ResultSet rSet = DBUtil.execQuery(querySql);
			
			try {
				if (rSet.next()) {
					WebResponse.returnFailureMsg(response, "车辆编号已存在");
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			carid = EncodeString.encodeUtf8(carid);
			String carnumber = EncodeString.encodeUtf8(request.getParameter("carnumber"));
			String name = EncodeString.encodeUtf8(request.getParameter("name"));
			String color = EncodeString.encodeUtf8(request.getParameter("color"));
			String type = EncodeString.encodeUtf8(request.getParameter("type"));
			String state = EncodeString.encodeUtf8(request.getParameter("state"));
			String deposit = request.getParameter("deposit");
			String dayrent = request.getParameter("dayrent");
			String weekrent = request.getParameter("weekrent");
			String monthrent = request.getParameter("monthrent");
			String overdateprice = request.getParameter("overdateprice");
			int statenum=0;
			switch (state) {
			case "未租出":
				statenum = 0;
				break;
			case "已租":
				statenum = 1;
				break;
			default:
				break;
			}
			
			String sql = "insert into car("
					+ "carnumber,name,color,type,state,deposit,dayrent,weekrent,monthrent,overdateprice,carid"
					+ ") values( ";
			sql += "'"+carnumber+"',";
			sql += "'"+name+"',";
			sql += "'"+color+"',";
			sql += "'"+type+"',";
			sql += "'"+statenum+"',";
			sql += "'"+deposit+"',";
			sql += "'"+dayrent+"',";
			sql += "'"+weekrent+"',";
			sql += "'"+monthrent+"',";
			sql += "'"+overdateprice+"',";
			sql += "'"+carid+"')";
			int resp = DBUtil.execUpdate(sql);
			if (resp>0) {
				WebResponse.returnSuccessMsg(response, "添加成功");
			} else {
				WebResponse.returnFailureMsg(response, "添加失败");
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
