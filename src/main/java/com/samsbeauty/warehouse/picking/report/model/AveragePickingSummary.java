package com.samsbeauty.warehouse.picking.report.model;

import java.math.BigDecimal;

public class AveragePickingSummary {
	private BigDecimal avgTotalPicked;
	private BigDecimal avgTotalPickedUnique;
	private BigDecimal avgTotalMissed;
	private BigDecimal avgTotalMissedUnique;
	private BigDecimal avgTotalPickedWithoutScan;
	private BigDecimal avgTotalPickedWithoutScanUnique;
	private BigDecimal avgTotalWrongLocation;
	private BigDecimal avgTotalWrongLocationUnique;
	
	public AveragePickingSummary(BigDecimal avgTotalPicked, BigDecimal avgTotalPickedUnique, BigDecimal avgTotalMissed,
			BigDecimal avgTotalMissedUnique, BigDecimal avgTotalPickedWithoutScan,
			BigDecimal avgTotalPickedWithoutScanUnique, BigDecimal avgTotalWrongLocation,
			BigDecimal avgTotalWrongLocationUnique) {
		super();
		this.avgTotalPicked = avgTotalPicked;
		this.avgTotalPickedUnique = avgTotalPickedUnique;
		this.avgTotalMissed = avgTotalMissed;
		this.avgTotalMissedUnique = avgTotalMissedUnique;
		this.avgTotalPickedWithoutScan = avgTotalPickedWithoutScan;
		this.avgTotalPickedWithoutScanUnique = avgTotalPickedWithoutScanUnique;
		this.avgTotalWrongLocation = avgTotalWrongLocation;
		this.avgTotalWrongLocationUnique = avgTotalWrongLocationUnique;
	}
	public AveragePickingSummary(Double avgTotalPicked, Double avgTotalPickedUnique, Double avgTotalMissed,
			Double avgTotalMissedUnique, Double avgTotalPickedWithoutScan,
			Double avgTotalPickedWithoutScanUnique, Double avgTotalWrongLocation,
			Double avgTotalWrongLocationUnique) {
		super();
		this.avgTotalPicked = BigDecimal.valueOf(avgTotalPicked).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalPickedUnique = BigDecimal.valueOf(avgTotalPickedUnique).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalMissed = BigDecimal.valueOf(avgTotalMissed).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalMissedUnique = BigDecimal.valueOf(avgTotalMissedUnique).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalPickedWithoutScan = BigDecimal.valueOf(avgTotalPickedWithoutScan).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalPickedWithoutScanUnique = BigDecimal.valueOf(avgTotalPickedWithoutScanUnique).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalWrongLocation = BigDecimal.valueOf(avgTotalWrongLocation).setScale(1, BigDecimal.ROUND_HALF_DOWN);
		this.avgTotalWrongLocationUnique = BigDecimal.valueOf(avgTotalWrongLocationUnique).setScale(1, BigDecimal.ROUND_HALF_DOWN);
	}

	public BigDecimal getAvgTotalPicked() {
		return avgTotalPicked;
	}

	public BigDecimal getAvgTotalPickedUnique() {
		return avgTotalPickedUnique;
	}

	public BigDecimal getAvgTotalMissed() {
		return avgTotalMissed;
	}

	public BigDecimal getAvgTotalMissedUnique() {
		return avgTotalMissedUnique;
	}

	public BigDecimal getAvgTotalPickedWithoutScan() {
		return avgTotalPickedWithoutScan;
	}

	public BigDecimal getAvgTotalPickedWithoutScanUnique() {
		return avgTotalPickedWithoutScanUnique;
	}

	public BigDecimal getAvgTotalWrongLocation() {
		return avgTotalWrongLocation;
	}

	public BigDecimal getAvgTotalWrongLocationUnique() {
		return avgTotalWrongLocationUnique;
	}
	
	public static AveragePickingSummaryBuilder builder() {
		return new AveragePickingSummaryBuilder();
	}
	
	public static class AveragePickingSummaryBuilder {
		private Double avgTotalPicked;
		private Double avgTotalPickedUnique;
		private Double avgTotalMissed;
		private Double avgTotalMissedUnique;
		private Double avgTotalPickedWithoutScan;
		private Double avgTotalPickedWithoutScanUnique;
		private Double avgTotalWrongLocation;
		private Double avgTotalWrongLocationUnique;
		
		public AveragePickingSummaryBuilder setAvgTotalPicked(Double avgTotalPicked) {
			this.avgTotalPicked = avgTotalPicked;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalPickedUnique(Double avgTotalPickedUnique) {
			this.avgTotalPickedUnique = avgTotalPickedUnique;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalMissed(Double avgTotalMissed) {
			this.avgTotalMissed = avgTotalMissed;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalMissedUnique(Double avgTotalMissedUnique) {
			this.avgTotalMissedUnique = avgTotalMissedUnique;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalPickedWithoutScan(Double avgTotalPickedWithoutScan) {
			this.avgTotalPickedWithoutScan = avgTotalPickedWithoutScan;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalPickedWithoutScanUnique(Double avgTotalPickedWithoutScanUnique) {
			this.avgTotalPickedWithoutScanUnique = avgTotalPickedWithoutScanUnique;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalWrongLocation(Double avgTotalWrongLocation) {
			this.avgTotalWrongLocation = avgTotalWrongLocation;
			return this;
		}
		public AveragePickingSummaryBuilder setAvgTotalWrongLocationUnique(Double avgTotalWrongLocationUnique) {
			this.avgTotalWrongLocationUnique = avgTotalWrongLocationUnique;
			return this;
		}
		public AveragePickingSummary createAveragePickingSummary() {
			return new AveragePickingSummary(avgTotalPicked, avgTotalPickedUnique, avgTotalMissed, avgTotalMissedUnique, avgTotalPickedWithoutScan,
			avgTotalPickedWithoutScanUnique, avgTotalWrongLocation, avgTotalWrongLocationUnique);
		}
		
	}
}
