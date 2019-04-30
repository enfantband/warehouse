package com.samsbeauty.warehouse.picking.report.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.model.PickingJob;
import com.samsbeauty.warehouse.picking.model.PickingJobTimeline;
import com.samsbeauty.warehouse.picking.report.model.PickingJobTimelineReport;

@Repository
public class PickingJobTimelineReportRepositoryImpl implements PickingJobTimelineReportRepositoryCustom {
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<WarehouseEmployee> findAllPickersByStartTimeAndEndTime(Date startTime, Date endTime) {
		return entityManager.createNamedQuery("SELECT r.warehouseEmployee FROM PickingJobTimelineReport r WHERE r.activated = true and r.startTimeline.eventTime >= ?1 and r.endTimeline.eventTime <= ?2  GROUP BY r.warehouseEmployee.warehouseEmployeeId", WarehouseEmployee.class)
				.setParameter(1, startTime)
				.setParameter(2, endTime)
				.getResultList();
	}

	@Override
	public Page<PickingJobTimelineReport> findAllForReport(Date startDate, Date endDate, Pageable pageable) {
		// counting
		BigInteger count = (BigInteger) this.entityManager.createNativeQuery("SELECT count(1) FROM (SELECT sum(total_picked) FROM picking_job_timeline_report r INNER JOIN picking_job_timeline pt1 ON r.start_timeline_id = pt1.timeline_id INNER JOIN picking_job_timeline pt2 ON r.end_timeline_id = pt2.timeline_id WHERE r.activated = true AND pt1.event_time >= ?1 AND pt2.event_time < ?2 GROUP BY r.warehouse_employee_id, date_format(pt1.event_time, '%m%Y')) c")
				.setParameter(1, startDate)
				.setParameter(2, endDate)
				.getSingleResult();
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();	
		CriteriaQuery<PickingJobTimelineReport> q = cb.createQuery(PickingJobTimelineReport.class);
		Root<PickingJobTimelineReport> root = q.from(PickingJobTimelineReport.class);
		q.multiselect(
				root.<PickingJob>get("pickingJob"), 
				root.<WarehouseEmployee>get("warehouseEmployee"),
				root.<PickingJobTimeline>get("startTimeline"), 
				root.<PickingJobTimeline>get("endTimeline"),
				cb.sum(root.get("totalMissed")),
				cb.sum(root.get("totalMissedUnique")),
				cb.sum(root.get("totalPicked")),
				cb.sum(root.get("totalPickedUnique")),
				cb.sum(root.get("totalPickedWithoutScan")),
				cb.sum(root.get("totalPickedWithoutScanUnique")),
				cb.sum(root.get("totalWrongLocation")),
				cb.sum(root.get("totalWrongLocationUnique")),
				cb.sum(root.get("totalSaved")),
				cb.sum(root.get("totalSavedUnique")),
				cb.<Long>sum(root.get("totalTimeSpent")),
				root.<Boolean>get("activated")
				);
		
		q.where(cb.and(cb.lessThan(root.<PickingJobTimeline>get("endTimeline").<Date>get("eventTime"), endDate),
			cb.greaterThanOrEqualTo(root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), startDate),
				cb.equal(root.<Boolean>get("activated"), true)));
		q.groupBy( root.get("warehouseEmployee"), cb.function("date_format", String.class, root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), cb.literal("'%m/%Y'") ));
		
		return new PageImpl<>(entityManager.createQuery(q).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList(), pageable, count.longValue()); 
	}
	@Override
	public PickingJobTimelineReport getReportForPicker(Date startDate, Date endDate, Long warehouseEmployeeId) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();	
		CriteriaQuery<PickingJobTimelineReport> q = cb.createQuery(PickingJobTimelineReport.class);
		Root<PickingJobTimelineReport> root = q.from(PickingJobTimelineReport.class);
		q.multiselect(
				root.<PickingJob>get("pickingJob"), 
				root.<WarehouseEmployee>get("warehouseEmployee"),
				root.<PickingJobTimeline>get("startTimeline"), 
				root.<PickingJobTimeline>get("endTimeline"),
				cb.sum(root.get("totalMissed")),
				cb.sum(root.get("totalMissedUnique")),
				cb.sum(root.get("totalPicked")),
				cb.sum(root.get("totalPickedUnique")),
				cb.sum(root.get("totalPickedWithoutScan")),
				cb.sum(root.get("totalPickedWithoutScanUnique")),
				cb.sum(root.get("totalWrongLocation")),
				cb.sum(root.get("totalWrongLocationUnique")),
				cb.sum(root.get("totalSaved")),
				cb.sum(root.get("totalSavedUnique")),
				cb.<Long>sum(root.get("totalTimeSpent")),
				root.<Boolean>get("activated")
				);
		
		q.where(cb.and(cb.lessThan(root.<PickingJobTimeline>get("endTimeline").<Date>get("eventTime"), endDate),
			cb.greaterThanOrEqualTo(root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), startDate),
				cb.equal(root.<Boolean>get("activated"), true),
				cb.equal(root.<WarehouseEmployee>get("warehouseEmployee").<Long>get("warehouseEmployeeId"), warehouseEmployeeId)
				));
		q.groupBy( cb.function("date_format", String.class, root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), cb.literal("'%m/%Y'") ));
		List<PickingJobTimelineReport> reports = entityManager.createQuery(q).getResultList();
		if(reports.size() > 0) return reports.get(0);
		return null;
	}
	public List<PickingJobTimelineReport> findAllForReport(Date startDate, Date endDate) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();	
		CriteriaQuery<PickingJobTimelineReport> q = cb.createQuery(PickingJobTimelineReport.class);
		Root<PickingJobTimelineReport> root = q.from(PickingJobTimelineReport.class);
		q.multiselect(
				root.<PickingJob>get("pickingJob"), 
				root.<WarehouseEmployee>get("warehouseEmployee"),
				root.<PickingJobTimeline>get("startTimeline"), 
				root.<PickingJobTimeline>get("endTimeline"),
				cb.sum(root.get("totalMissed")),
				cb.sum(root.get("totalMissedUnique")),
				cb.sum(root.get("totalPicked")),
				cb.sum(root.get("totalPickedUnique")),
				cb.sum(root.get("totalPickedWithoutScan")),
				cb.sum(root.get("totalPickedWithoutScanUnique")),
				cb.sum(root.get("totalWrongLocation")),
				cb.sum(root.get("totalWrongLocationUnique")),
				cb.sum(root.get("totalSaved")),
				cb.sum(root.get("totalSavedUnique")),
				cb.<Long>sum(root.get("totalTimeSpent")),
				root.<Boolean>get("activated")
				);
		
		q.where(cb.and(cb.lessThan(root.<PickingJobTimeline>get("endTimeline").<Date>get("eventTime"), endDate),
			cb.greaterThanOrEqualTo(root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), startDate),
				cb.equal(root.<Boolean>get("activated"), true)));
		q.groupBy( root.get("warehouseEmployee"), cb.function("date_format", String.class, root.<PickingJobTimeline>get("startTimeline").<Date>get("eventTime"), cb.literal("'%m/%Y'") ));
		
		return entityManager.createQuery(q).getResultList(); 
	}
}
