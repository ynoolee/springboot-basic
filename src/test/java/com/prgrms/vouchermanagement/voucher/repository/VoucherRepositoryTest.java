package com.prgrms.vouchermanagement.voucher.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import com.prgrms.vouchermanagement.commons.page.Pageable;
import com.prgrms.vouchermanagement.commons.page.SimplePage;
import com.prgrms.vouchermanagement.voucher.VoucherType;
import com.prgrms.vouchermanagement.voucher.domain.Voucher;

@SpringBootTest
class VoucherRepositoryTest {
	// TODO : test data 를 BeforeEach, BeforeAll 에서 save 하는 것으로 변경 후 afterEach 를 작성하여 동일 결과를 보장할 수 있도록 해야함

	@Autowired
	VoucherRepository voucherRepository;

	private UUID existingId;

	@BeforeEach
	public void setup() {
		existingId = UUID.fromString("a3a65384-c5e8-11ec-8ac0-86fc60ea758b");
	}

	@Test
	@DisplayName("실제로 저장했던 데이터 개수와 같은 사이즈여야한다")
	void given_repository_when_compareSize_thenSuccess() {
		List<Voucher> vouchers = voucherRepository.findAll();

		assertThat(vouchers.size(), is(3));
	}

	@Test
	@DisplayName("중복 ID 를 갖는 voucher 를 새로 저장하려고 할 경우 DuplicateKeyException 을 던진다")
	void given_id_when_findVoucher_thenSuccess() {
		Voucher voucher = VoucherType.FIXED.getVoucher(existingId, 100, LocalDateTime.now());

		Assertions.assertThrows(DuplicateKeyException.class, () -> voucherRepository.insert(voucher));
	}

	@Test
	@DisplayName("디비로부터 가져온 바우처 정보를 사용하여 정상적으로 직렬화된 바우처 객체를 생성한다")
	void given_usingTestData_when_fetchingVoucherData_thenMakingVoucherFromDataSuccess() {
		Optional<Voucher> voucherOptional = voucherRepository.findById(existingId);

		assertThat(voucherOptional.isPresent(), is(true));
		Voucher voucher = voucherOptional.get();

		assertThat(voucher.getDiscountInfo(), is(1110L));
		assertThat(voucher.getType(), is(VoucherType.FIXED));
	}

	@Test
	@DisplayName("데이터를 저장하고 ID 를 사용해서 찾아온 후 ID를 사용해 삭제할 수 있어야 한다")
	void given_repositoryAndData_When_saveData_thenFindingThatDataSuccess() {
		UUID id = UUID.randomUUID();
		Voucher voucher = VoucherType.FIXED.getVoucher(id, 1000L, LocalDateTime.now());

		voucherRepository.insert(voucher);
		Optional<Voucher> voucherOptional = voucherRepository.findById(id);

		assertThat(voucherOptional.isPresent(), is(true));
		assertThat(voucherOptional.get().getVoucherId(), is(voucher.getVoucherId()));

		long deleted = voucherRepository.deleteById(id);

		assertThat(deleted, is(1L));

		Optional<Voucher> deletedVoucher = voucherRepository.findById(id);

		assertThat(deletedVoucher.isPresent(), is(false));
	}

	@Test
	@DisplayName("존재하지 않는 id 로 바우처 삭제 시 0 이 리턴된다")
	void given_notExistingId_when_deleteData_then() {
		UUID id = UUID.randomUUID();

		long deleted = voucherRepository.deleteById(id);

		assertThat(deleted).isEqualTo(0L);
	}

	@Test
	@DisplayName("offset 이 1 이고 limit 이 2 인 경우 크기가 3 인 List 를 리턴한다")
	void given_page_when_findAll_then() {
		Pageable page = new SimplePage(1, 2);

		List<Voucher> allVouchers = voucherRepository.findAll();
		List<Voucher> pagedVouchers = voucherRepository.findAll(page.offset(), page.limit());

		Condition<Integer> sizeCond = new Condition<>(
			s -> s >= 3,
			"전체 사이즈 크기가 3 이상인 경우"
		);

		assertThat(allVouchers.size()).is(sizeCond);
		assertThat(pagedVouchers.size())
			.isEqualTo(2);
	}

	@ParameterizedTest
	@ValueSource(longs = {10,11,Integer.MAX_VALUE})
	@DisplayName("offset 이 전체 데이터보다 큰 수가 주어진 경우 empty List 를 반환한다")
	void given_offsetBiggerThanTotalSize_when_findAll_then(long offset) {
		Pageable page = new SimplePage(offset, 2);

		List<Voucher> allVouchers = voucherRepository.findAll();
		List<Voucher> pagedVouchers = voucherRepository.findAll(page.offset(), page.limit());

		Condition<Integer> sizeCond = new Condition<>(
			s -> s < offset,
			"전체 사이즈 크기가 " + offset + " 보다 작은 경우"
		);

		assertThat(allVouchers.size()).is(sizeCond);
		assertThat(pagedVouchers.size())
			.isEqualTo(0);
	}
}