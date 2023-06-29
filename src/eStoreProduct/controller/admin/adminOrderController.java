package eStoreProduct.controller.admin;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eStoreProduct.DAO.common.OrderDAO;
import eStoreProduct.model.admin.entities.orderModel;

@Controller
public class adminOrderController {

	private OrderDAO od;
	private orderModel om;
	private static final Logger logger = LoggerFactory.getLogger(adminOrderController.class);

	@Autowired
	adminOrderController(OrderDAO ord, orderModel omd) {
		od = ord;
		om = omd;
	}

	@GetMapping("/listOrders")
	public String showOrders(Model model) {
		logger.info("adminOrderController  url: listOrders  returns: orderList.jsp ");

		List<orderModel> orders = od.getAllOrders();
		model.addAttribute("orders", orders);
		return "orderList";
	}

	@GetMapping("/processOrders")
	public String processOrders(@RequestParam(value = "orderId") long orderId,
			@RequestParam(value = "adminId") int adminId, Model model) {
		logger.info("adminOrderController  url: processOrders  returns: filteredOrderList.jsp ");

		System.out.println("procvessing");
		System.out.println(orderId + "" + adminId);
		od.updateOrderProcessedBy(orderId, adminId);
		System.out.println("processed");
		List<orderModel> orders = od.getAllOrders();
		model.addAttribute("orders", orders);
		return "filteredOrderList";
	}

	@GetMapping("/loadOrdersByDate")
	public String loadOrders(@RequestParam(value = "selectDateFilter") String selectDateFilter, Model model) {

		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;

		if (selectDateFilter.equals("daily")) {
			// Set the start and end date for daily filter
			startDate = currentDate.withHour(0).withMinute(0).withSecond(0);
			endDate = currentDate.withHour(23).withMinute(59).withSecond(59);
		} else if (selectDateFilter.equals("weekly")) {
			// Set the start and end date for weekly filter (assuming a week starts on Monday)
			startDate = currentDate.withHour(0).withMinute(0).withSecond(0)
					.minusDays(currentDate.getDayOfWeek().getValue() - 1);
			endDate = startDate.plusDays(6).withHour(23).withMinute(59).withSecond(59);
		} else if (selectDateFilter.equals("monthly")) {
			// Set the start and end date for monthly filter
			startDate = currentDate.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
			endDate = startDate.plusMonths(1).minusDays(1).withHour(23).withMinute(59).withSecond(59);
		} else {
			// No filter selected, load all orders
			logger.info("adminOrderController  url: loadOrdersByDate  returns: orderList.jsp ");

			List<orderModel> orders = od.getAllOrders();
			model.addAttribute("orders", orders);
			return "orderList";
		}
		logger.info("adminOrderController  url: loadOrdersByDate  returns: filteredOrderList.jsp ");

		List<orderModel> orders = od.loadOrdersByDate(Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
		model.addAttribute("orders", orders);
		return "filteredOrderList";
	}

}