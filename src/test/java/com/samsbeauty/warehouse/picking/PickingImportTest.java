package com.samsbeauty.warehouse.picking;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itextpdf.text.DocumentException;
import com.samsbeauty.old.model.OrderInfoForPicking;
import com.samsbeauty.old.model.OrderItem;
import com.samsbeauty.old.model.OrderItem.InventoryStatus;
import com.samsbeauty.old.model.OrderItem.ProductType;
import com.samsbeauty.warehouse.App;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.service.PickingJobService;
import com.samsbeauty.warehouse.util.PackingListExporter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=App.class, loader=SpringApplicationContextLoader.class)
@WebIntegrationTest({"server.port=0"})
public class PickingImportTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private PickingJobService pickingJobService;
	
	@Test
	@WithMockUser(username="tester", roles={"ADMIN"})
	public void orderSortingTest() {
		// Create Order Info
		OrderInfoForPicking op = OrderInfoForPicking.builder()
				.setFirstname("1")
				.setHost("SB")
				.setLastname("tester")
				.setEmail("test1@samsbeauty.com")
				.setMiddlename("m")
				.setOrderNo("1000")
				.setPromotionCode("")
				.setRiDate("2016.11.21")
				.setShippingAddress1("shipping addr1")
				.setShippingAddress2("address 2")
				.setShippingCity("Chicago")
				.setShippingCountry("US")
				.setShippingMethod("00900101")
				.setShippingMethodName("Standard Shipping")
				.setShippingState("FL")
				.setShippingZipcode("60053")
				.createOrderInfoForPicking();
		
		OrderInfoForPicking op2 = OrderInfoForPicking.builder()
				.setFirstname("2")
				.setHost("SB")
				.setLastname("tester")
				.setEmail("test2@samsbeauty.com")
				.setMiddlename("m")
				.setOrderNo("1001")
				.setPromotionCode("")
				.setRiDate("2016.11.21")
				.setShippingAddress1("shipping addr1")
				.setShippingAddress2("address 2")
				.setShippingCity("Chicago")
				.setShippingCountry("US")
				.setShippingMethod("00900101")
				.setShippingMethodName("UPS 1 Day")
				.setShippingState("CA")
				.setShippingZipcode("60053")
				.createOrderInfoForPicking();
		
		OrderInfoForPicking op3 = OrderInfoForPicking.builder()
				.setFirstname("3")
				.setHost("SB")
				.setLastname("tester")
				.setEmail("test3@samsbeauty.com")
				.setMiddlename("m")
				.setOrderNo("1002")
				.setPromotionCode("")
				.setRiDate("2016.11.21")
				.setShippingAddress1("shipping addr1")
				.setShippingAddress2("address 2")
				.setShippingCity("Chicago")
				.setShippingCountry("US")
				.setShippingMethod("00900101")
				.setShippingMethodName("Free Ground Shipping")
				.setShippingState("NY")
				.setShippingZipcode("60053")
				.createOrderInfoForPicking();
		
		OrderInfoForPicking op4 = OrderInfoForPicking.builder()
				.setFirstname("4")
				.setHost("SB")
				.setLastname("tester")
				.setEmail("test4@samsbeauty.com")
				.setMiddlename("m")
				.setOrderNo("1003")
				.setPromotionCode("")
				.setRiDate("2016.11.21")
				.setShippingAddress1("shipping addr1")
				.setShippingAddress2("address 2")
				.setShippingCity("Chicago")
				.setShippingCountry("US")
				.setShippingMethod("00900101")
				.setShippingMethodName("ExPRESs SHIPPING")
				.setShippingState("NY")
				.setShippingZipcode("60053")
				.createOrderInfoForPicking();
		
		List<OrderInfoForPicking> orderList = Arrays.asList(op, op2, op3, op4);
		Map<String, List<OrderItem>> map = new HashMap<>();
		
		OrderItem oi = OrderItem.builder()
				.setCompany("AA")
				.setFirstOption("1B")
				.setGeneratedBarcode("111")
				.setImageUrl("test")
				.setOrderNo("1000")
				.setProductBarcode("1234")
				.setProductGroup("aaa")
				.setProductType(OrderItem.ProductType.HAIR)
				.setQuantity(4)
				.setStock(5)
				.setTitle("item A")
				.createOrderItem();
		map.put("1000", Arrays.asList(oi, oi, oi, oi));
		
		
		OrderItem oi2 = OrderItem.builder()
				.copy(oi)
				.setOrderNo("1001")
				.setProductType(OrderItem.ProductType.GM)
				.setTitle("item A copied")
				.createOrderItem();
		
		OrderItem oi21 = OrderItem.builder()
				.copy(oi)
				.setOrderNo("1001")
				.setProductType(OrderItem.ProductType.GM)
				.setTitle("item A copied")
				.createOrderItem();
		
		
		OrderItem oi3 = OrderItem.builder()
				.copy(oi2)
				.setProductBarcode(OrderItem.ProductType.HAIR)
				.setTitle("item A copied2")
				.createOrderItem();
		
		map.put("1001", Arrays.asList(oi2, oi21, oi3));
		
		OrderItem oi4 = OrderItem.builder()
				.copy(oi3)
				.setProductType(OrderItem.ProductType.HAIR)
				.setTitle("item A copied 3")
				.setOrderNo("1002")
				.createOrderItem();
		
		OrderItem oi5 = OrderItem.builder()
				.copy(oi4)
				.setProductType(OrderItem.ProductType.HAIR)
				.setTitle("item A copied 4")
				.setOrderNo("1002")
				.createOrderItem();
		map.put("1002", Arrays.asList(oi4, oi5));		
		
		List<OrderInfoForPicking> list = pickingJobService.sortOrderForPacking(orderList, map);
		String[] answers = new String[list.size()];
		String[] expected = {"1001", "1003", "1002", "1000"};
		int i=0;
		for(OrderInfoForPicking o : list) {
			answers[i] = o.getOrderNo();
			i++;
		}
		Assert.assertArrayEquals(expected, answers);
	}
	
	@Test
	public void createPackingPdfTest() throws DocumentException, MalformedURLException, IOException {
		OrderInfoForPicking op = OrderInfoForPicking.builder()
				.setFirstname("1")
				.setHost("SB")
				.setLastname("tester")
				.setEmail("test1@samsbeauty.com")
				.setMiddlename("m")
				.setOrderNo("8640550199213488")
				.setPromotionCode("")
				.setRiDate("11/22/2016 17:00")
				.setShippingAddress1("shipping addr1")
				.setShippingAddress2("address 2")
				.setShippingCity("Chicago")
				.setShippingCountry("US")
				.setShippingMethod("00900101")
				.setShippingMethodName("Standard Shipping")
				.setShippingState("FL")
				.setShippingZipcode("60053")
				.setLogoImagePath("/common/host/samslogo.gif")
				.setShippingPrice(BigDecimal.valueOf(0.00))
				.setShippingDiscPrice(BigDecimal.valueOf(10.11))
				.createOrderInfoForPicking();
		
		OrderItem oi = OrderItem.builder()
				.setCompany("A00")
				.setDealGroup("")
				.setFinalPrice(BigDecimal.valueOf(25.98))
				.setFirstOption("F1b/30")
				.setGeneratedBarcode("S12I07001074")
				.setImageUrl("")
				.setInventoryStatus(InventoryStatus.AVAILABLE)
				.setLocationNames(Arrays.asList("HAIR-1A-01-01"))
				.setOrderNo("8696906831667824")
				.setProductBarcode("")
				.setProductGroup("SSHWIW")
				.setProductType(ProductType.HAIR)
				.setQuantity(2)
				.setStock(7)
				.setTitle("Sensationnel Synthetic Half Wig Instant Weave Rio")
				.setUsedPoint(0)
				.setVendorItemCode("IS7RIO")
				.createOrderItem();		

		// add gm
		OrderItem oi2 = OrderItem.builder()
				.setCompany("C08")
				.setDealGroup("")
				.setFinalPrice(BigDecimal.valueOf(0.00))
				.setUsedPoint(200)
				.setFirstOption("")
				.setGeneratedBarcode("S12I07001075")
				.setImageUrl("")
				.setInventoryStatus(InventoryStatus.FINAL_SALE)
				.setLocationNames(Arrays.asList("GM-1A-01-01"))
				.setOrderNo("8636351692524867")
				.setProductBarcode("12345678")
				.setProductGroup("SSHWWW")
				.setProductType(ProductType.GM)
				.setQuantity(2)
				.setStock(3)
				.setTitle("GM free ....tt asdf ")
				.setVendorItemCode("AABABA")
				.createOrderItem();

		op.setOrderItems(Arrays.asList(oi, oi2));
				
		
		// add deal item		
		
		PackingListExporter exporter = new PackingListExporter();
		WarehouseEmployee we = WarehouseEmployee.builder("test", "12345").setName("Myoungho Shin").createWarehouseEmployee();
		exporter.createPackingList("/home/samsdev/uploadFiles/test.pdf", we, Arrays.asList(op));
	}
}
