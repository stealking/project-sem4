package vn.tourism.beta.service;

import org.springframework.data.domain.Sort;
import vn.tourism.beta.entity.Voucher;

import java.util.List;

public interface VoucherService {
    public Voucher updateVoucherById(Voucher voucher, Long voucherId);
    public Voucher deleteVoucherById(Long voucherId);
    public List<Voucher> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult);
}
