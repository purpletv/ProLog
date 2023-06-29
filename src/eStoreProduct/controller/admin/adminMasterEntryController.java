package eStoreProduct.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eStoreProduct.DAO.admin.CategoryDAO;
import eStoreProduct.DAO.admin.stockSummaryDAO;
import eStoreProduct.DAO.common.ProductDAO;
import eStoreProduct.model.admin.input.Category;
import eStoreProduct.model.admin.input.Product;
import eStoreProduct.model.admin.output.stockSummaryModel;

@Controller
public class adminMasterEntryController {

	private stockSummaryDAO ssd;

	private final ProductDAO pdaoimp;
	private final CategoryDAO cdaoimp;
	private static final Logger logger = LoggerFactory.getLogger(adminMasterEntryController.class);

	@Autowired
	adminMasterEntryController(stockSummaryDAO stockdao, ProductDAO productdao, CategoryDAO categorydao) {
		ssd = stockdao;
		pdaoimp = productdao;
		cdaoimp = categorydao;
	}

	@RequestMapping(value = "/showEditableStocks", method = RequestMethod.GET)
	public String showEditableStocks(Model model) {
		logger.info("adminMasterEntryController  url:showEditableStocks  returns:editableStocks.jsp ");
		System.out.println("enter masterEntry controller");
		List<stockSummaryModel> stocks1 = (List<stockSummaryModel>) ssd.getStocks();
		model.addAttribute("stocks1", stocks1);
		return "editableStocks";
	}

	@RequestMapping(value = "/showEditablePrice", method = RequestMethod.GET)
	public String showEditableProductsPrice(Model model) {
		logger.info("adminMasterEntryController  url:showEditablePrice  returns:editablePrice.html ");
		System.out.println("enter masterEntry controller");
		List<stockSummaryModel> stocks1 = (List<stockSummaryModel>) ssd.getStocks();
		model.addAttribute("stocks1", stocks1);
		return "editablePrice.html";
	}

	@GetMapping("/updateMasterEntryTables")
	public String showUpdatedEditableStocks(@Validated stockSummaryModel ssm, Model model) {
		logger.info("adminMasterEntryController  url:updateMasterEntryTables  returns:editableStocks.html ");
		System.out.println("enter updated masterEntry controller");
		ssd.updateStocks(ssm.getId(), ssm.getImageUrl(), ssm.getHsnCode(), ssm.getReorderLevel(), ssm.getStock(),
				ssm.getMrp());
		List<stockSummaryModel> stocks1 = (List<stockSummaryModel>) ssd.getStocks();
		System.out.println("enter updated masterEntry controller23");
		model.addAttribute("stocks1", stocks1);
		return "editableStocks";
	}

	@GetMapping("/addNewProductInTheMasterEntry")
	public String addNewProductInMasterEntry(Model model) {
		System.out.println("enter addNewProductController ");
		logger.info("adminMasterEntryController  url:addNewProductInTheMasterEntry  returns:addNewProduct.jsp ");

		return "addNewProduct";
	}

	@RequestMapping(value = "/createNewProduct", method = RequestMethod.POST)
	public String createProductNew(@Validated Product prod, Model model) {
		logger.info("adminMasterEntryController  url:createNewProduct  returns:AddedProduct.jsp ");

		System.out.print("craeting newww product\n");
		pdaoimp.createProduct(prod);
		System.out.print("created\n");
		return "AddedProduct";

	}

	@GetMapping("/addNewCategorytInTheMasterEntry")
	public String addNewCategorytInMasterEntry(Model model) {
		System.out.println("enter addNewCategoryController controller");
		logger.info("adminMasterEntryController  url:addNewCategorytInTheMasterEntry  returns:addNewCategoryForm.jsp ");

		return "addNewCategoryForm";
	}

	@RequestMapping(value = "/createNewCategory", method = RequestMethod.POST)
	public String createProductNew(@Validated Category catg, Model model) {
		logger.info("adminMasterEntryController  url:createNewCategory  returns:AddedCategory.jsp ");

		cdaoimp.addNewCategory(catg);
		return "AddedCategory";

	}

}