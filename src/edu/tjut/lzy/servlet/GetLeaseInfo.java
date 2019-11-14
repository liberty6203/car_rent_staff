package edu.tjut.lzy.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import edu.tjut.lzy.dao.DBUtil;
import edu.tjut.lzy.pojo.Lease;

@WebServlet("/GetLeaseInfo")
public class GetLeaseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "select lease.clientid,lease.carid,car.name,car.carnumber,lease.startdate,lease.rentdays,lease.method,lease.state,car.deposit,car.dayrent,car.weekrent,car.monthrent"
				+ " from car,lease where car.carid=lease.carid";
//		System.out.println(sql);
		ResultSet rSet = DBUtil.execQuery(sql);
		 String clientid;
		 String carid;
		 String name;
		 Date startdate;
		 int rentdays;
		 String method;
		 int state;
		 int deposit;
		 int dayrent;
		 int weekrent;
		 int monthrent;
		 String carnumber;
		 List<Lease> leases = new ArrayList<>();
		try {
			while (rSet.next()) {
				clientid = rSet.getString("clientid");
				carid = rSet.getString("carid");
				name = rSet.getString("name");
				startdate = rSet.getDate("startdate");
				rentdays = rSet.getInt("rentdays");
				method = rSet.getString("method");
				state = rSet.getInt("state");
				deposit = rSet.getInt("deposit");
				dayrent = rSet.getInt("dayrent");
				weekrent = rSet.getInt("weekrent");
				monthrent = rSet.getInt("monthrent");
				carnumber = rSet.getString("carnumber");
				Lease lease = new Lease(clientid, carid, name, startdate, rentdays, method, state, deposit, dayrent, weekrent, monthrent, carnumber);
				leases.add(lease);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resp = JSON.toJSONString(leases);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(resp);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
