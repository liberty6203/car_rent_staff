package edu.tjut.lzy.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import edu.tjut.lzy.dao.DBUtil;
import edu.tjut.lzy.pojo.Car;


@WebServlet("/GetCarInfo")
public class GetCarInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "select * from car";
		ResultSet rSet = DBUtil.execQuery(sql);
		List<Car> cars = new ArrayList<>();
		String carid="";
		String name="";
		int dayrent=0;
		int monthrent=0;
		int weekrent=0;
		int deposit=0;
		String type="";
		String color="";
		int overdateprice=0;
		int state=0;
		String carnumber = "";
		try {
			while (rSet.next()) {
				carid=rSet.getString("carid");
				name=rSet.getString("name");
				dayrent = rSet.getInt("dayrent");
				weekrent = rSet.getInt("weekrent");
				monthrent = rSet.getInt("monthrent");
				deposit = rSet.getInt("deposit");
				type = rSet.getString("type");
				color = rSet.getString("color");
				overdateprice = rSet.getInt("overdateprice");
				state = rSet.getInt("state");
				carnumber = rSet.getString("carnumber");
				Car car = new Car(carid, name, dayrent, weekrent, monthrent, deposit, type, color, overdateprice, state,carnumber);
				cars.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resp = JSON.toJSONString(cars);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(resp);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
