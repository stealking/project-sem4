package vn.tourism.beta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.tourism.beta.entity.Voucher;
import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
public interface VoucherRepository extends PagingAndSortingRepository<Voucher, Long> {
    Page<Voucher> findAllByEnableEquals(Boolean enable, Pageable pageable);
    List<Voucher> findAllByEnableEquals(Boolean enable);
    Voucher findByIdAndEnableEquals(Long id, Boolean enable);
    Page<Voucher> findByContentContainingOrDiscountPercentContainingOrIntroductionContainingAndEnableTrue(Pageable pageable, String content, String discountPercent, String introduction);
}
