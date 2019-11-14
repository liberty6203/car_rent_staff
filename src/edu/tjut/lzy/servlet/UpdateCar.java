package edu.tjut.lzy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.util.Sorting;

import edu.tjut.lzy.dao.DBUtil;
import edu.tjut.lzy.util.EncodeString;
import edu.tjut.lzy.util.WebResponse;

@WebServlet("/UpdateCar")
public class UpdateCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String carid = request.getParameter("carid");
		
		if (carid!=null) {
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
			if (statenum==1) {
				WebResponse.returnFailureMsg(response, "不可以修改已经租出的车辆");
				return;
			}
			String sql = "update car set ";
			sql += "carnumber='"+carnumber+"',";
			sql += "name='"+name+"',";
			sql += "color='"+color+"',";
			sql += "type='"+type+"',";
			sql += "state='"+statenum+"',";
			sql += "deposit='"+deposit+"',";
			sql += "dayrent='"+dayrent+"',";
			sql += "weekrent='"+weekrent+"',";
			sql += "monthrent='"+monthrent+"',";
			sql += "overdateprice='"+overdateprice+"' ";
			sql += "where carid='"+carid+"'";
			int resp = DBUtil.execUpdate(sql);
			if (resp>0) {
				WebResponse.returnSuccessMsg(response, "修改成功");
			} else {
				WebResponse.returnFailureMsg(response, "修改失败");
			}
		}else {
			WebResponse.returnFailureMsg(response, "参数错误");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
