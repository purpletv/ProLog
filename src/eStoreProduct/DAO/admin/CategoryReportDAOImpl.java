package eStoreProduct.DAO.admin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import eStoreProduct.model.admin.output.CategoryReportViewModel;

@Component
public class CategoryReportDAOImpl implements CategoryReportDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public List<CategoryReportViewModel> getCatRep() {

		String hql = "select  new eStoreProduct.model.admin.output.CategoryReportViewModel(spc.id,spc.prct_title,count(*),sum(sop.price),sum(sop.gst),sum(sop.quantity)) \n"
				+ "	    from orderModel so,OrderProds sop,SlamProduct sp,productCategoryModel spc \n"
				+ "	    where sop.productId=sp.id and sp.productCategory=spc.id group by spc.id";

		List<CategoryReportViewModel> result = entityManager.createQuery(hql, CategoryReportViewModel.class)
				.getResultList();

		System.out.println("in catr dao");
		System.out.println(result);
		return result;

	}

}
