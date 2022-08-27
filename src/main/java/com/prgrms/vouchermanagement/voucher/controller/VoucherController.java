package com.prgrms.vouchermanagement.voucher.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prgrms.vouchermanagement.commons.page.Pageable;
import com.prgrms.vouchermanagement.voucher.VoucherService;
import com.prgrms.vouchermanagement.voucher.domain.Voucher;
import com.prgrms.vouchermanagement.voucher.dto.CreateVoucherRequest;
import com.prgrms.vouchermanagement.voucher.dto.VoucherInfo;

@Controller
@RequestMapping("/vouchers")
public class VoucherController {

	private final VoucherService voucherService;

	public VoucherController(VoucherService voucherService) {
		this.voucherService = voucherService;
	}

	@PostMapping("/register")
	public String newVoucher(@Validated @ModelAttribute CreateVoucherRequest request) {

		Voucher createdVoucher = voucherService.create(
			request.getType(),
			request.getDiscountInfo());

		return "redirect:/vouchers";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("request", new VoucherInfo(null, null, 0));

		return "new-voucher";
	}

	@GetMapping
	public String showVouchers(Model model, Pageable page) {
		List<VoucherInfo> vouchers = voucherService.getAll(page).stream()
			.map(VoucherInfo::fromEntity)
			.collect(Collectors.toList());

		model.addAttribute("vouchers", vouchers);

		return "list";
	}
}
