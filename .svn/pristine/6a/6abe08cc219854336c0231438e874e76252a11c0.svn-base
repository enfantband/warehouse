package com.samsbeauty.warehouse.picking.model.converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.samsbeauty.warehouse.picking.model.PickingJob;

@Converter
public class PickingJobStatusConverter implements AttributeConverter<String, Integer >{
	public static final Map<Integer, String> pickingJobStatusValue;
	public static final Map<String, Integer> pickingJobStatusKey;
	
	static {
		pickingJobStatusValue = new ConcurrentHashMap<>();
		pickingJobStatusValue.put(PickingJob.PickingJobStatus.READY_CODE, PickingJob.PickingJobStatus.READY);
		pickingJobStatusValue.put(PickingJob.PickingJobStatus.ASSIGNED_CODE, PickingJob.PickingJobStatus.ASSIGNED);
		pickingJobStatusValue.put(PickingJob.PickingJobStatus.PAUSED_CODE, PickingJob.PickingJobStatus.PAUSED);
		pickingJobStatusValue.put(PickingJob.PickingJobStatus.STARTED_CODE, PickingJob.PickingJobStatus.STARTED);
		pickingJobStatusValue.put(PickingJob.PickingJobStatus.FINISHED_CODE, PickingJob.PickingJobStatus.FINISHED);
		pickingJobStatusValue.put(PickingJob.PickingJobStatus.PASSED_CODE, PickingJob.PickingJobStatus.PASSED);
		
		pickingJobStatusKey = new ConcurrentHashMap<>();
		pickingJobStatusKey.put(PickingJob.PickingJobStatus.READY, PickingJob.PickingJobStatus.READY_CODE);
		pickingJobStatusKey.put(PickingJob.PickingJobStatus.ASSIGNED, PickingJob.PickingJobStatus.ASSIGNED_CODE);
		pickingJobStatusKey.put(PickingJob.PickingJobStatus.PAUSED, PickingJob.PickingJobStatus.PAUSED_CODE);
		pickingJobStatusKey.put(PickingJob.PickingJobStatus.STARTED, PickingJob.PickingJobStatus.STARTED_CODE);
		pickingJobStatusKey.put(PickingJob.PickingJobStatus.FINISHED, PickingJob.PickingJobStatus.FINISHED_CODE);
		pickingJobStatusKey.put(PickingJob.PickingJobStatus.PASSED, PickingJob.PickingJobStatus.PASSED_CODE);
	}
	public Integer convertToDatabaseColumn(String attribute) {
		return pickingJobStatusKey.get(attribute);
	}
	public String convertToEntityAttribute(Integer dbData) {
		
		return pickingJobStatusValue.get(dbData);
	}
}
