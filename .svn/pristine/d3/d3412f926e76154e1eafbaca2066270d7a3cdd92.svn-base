package com.samsbeauty.warehouse.picking.report;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.samsbeauty.warehouse.App;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;
import com.samsbeauty.warehouse.picking.report.repository.PickingJobTimelineReportRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=App.class, loader=SpringApplicationContextLoader.class)
@WebIntegrationTest({"server.port=0"})
public class ReportRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired PickingJobTimelineReportRepository pickingJobTimelineReportRepository;
	
	@Test
	public void reportTest() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		Page<PickingJobTimelineReport> page = pickingJobTimelineReportRepository.findAllForReport(cal.getTime(), new Date(), new PageRequest(0, 20));
		for(PickingJobTimelineReport r : page.getContent()) {
			System.out.println("total picked = " + r.getTotalPicked());
		}
		System.out.println(page.getTotalElements());
	}
}
