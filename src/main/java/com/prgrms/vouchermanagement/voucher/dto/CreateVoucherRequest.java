package com.prgrms.vouchermanagement.voucher.dto;

import com.prgrms.vouchermanagement.voucher.VoucherType;

public class CreateVoucherRequest {
	private final VoucherType type;
	private final long discountInfo;

	public CreateVoucherRequest(VoucherType type, long discountInfo) {
		this.type = type;
		this.discountInfo = discountInfo;
	}

	public VoucherType getType() {
		return type;
	}

	public long getDiscountInfo() {
		return discountInfo;
	}

}
