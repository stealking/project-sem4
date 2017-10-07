package vn.tourism.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.tourism.beta.entity.Voucher;
import vn.tourism.beta.repository.VoucherRepository;

import java.util.Date;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService{
    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Voucher updateVoucherById(Voucher voucher, Long voucherId) {
        Voucher VoucherDb = voucherRepository.findOne(voucherId);
        if(voucher.getImage() != null)VoucherDb.setImage(voucher.getImage());
        if(voucher.getIntroduction() != null)VoucherDb.setIntroduction(voucher.getIntroduction());
        if(voucher.getContent() != null)VoucherDb.setContent(voucher.getContent());
        if(voucher.getExpiredOn() != null)VoucherDb.setExpiredOn(voucher.getExpiredOn());
        if(voucher.getUpdatedOn() != null)VoucherDb.setUpdatedOn(new Date());
        if(voucher.getUpdatedBy() != null)VoucherDb.setUpdatedBy(voucher.getUpdatedBy());
        voucherRepository.save(VoucherDb);
        return VoucherDb;
    }

    @Override
    public Voucher deleteVoucherById( Long voucherId) {
        Voucher VoucherDb = voucherRepository.findOne(voucherId);
        VoucherDb.setEnable(false);
        voucherRepository.save(VoucherDb);
        return VoucherDb;
    }
    @Override
    public List<Voucher> getPage(int pageNumber, Sort.Direction sortDirection, String column, int maxResult){
        PageRequest request = new PageRequest(pageNumber - 1, maxResult, sortDirection, column);

        return voucherRepository.findAll(request).getContent();
    }
}
