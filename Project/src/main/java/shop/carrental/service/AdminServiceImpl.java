package shop.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import shop.carrental.domain.AdminVO;
import shop.carrental.domain.Criteria;
import shop.carrental.domain.PageVO;
import shop.carrental.domain.ShortCarDTO;
import shop.carrental.mappers.AdminMapper;
import shop.carrental.mappers.CarMapper;

@Log4j
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

	private CarMapper carMapper;
	private AdminMapper adminMapper;

	@Override
	public void register(AdminVO vo) {
		log.info("ServiceImpl register...");
		adminMapper.register(vo);
	}

	@Override
	public AdminVO getAdmin(AdminVO vo) {
		log.info("ServiceImpl getAdmin...");
		return adminMapper.getAdmin(vo);
	}
	
	@Override
	public void registerShortCar(ShortCarDTO dto, RedirectAttributes redirectAttributes) {
		log.info("Admin==>RegisterShortCar");

		int seq = carMapper.registerShortCar(dto);
		redirectAttributes.addFlashAttribute("result", seq);
	}

	@Override
	public void getBranchList(Criteria cri, Model model) {
		log.info("ServiceImpl getBranchList...");
		model.addAttribute("branchList", adminMapper.getBranchList(cri));
		
		int total = adminMapper.getTotalBranchCount(cri);// 페이지번호, 페이당 건수로 조회
		log.info("total:" + total);

		log.info("PageVO:" + new PageVO(cri, total));
		model.addAttribute("pageMaker", new PageVO(cri, total));
	}

	@Override
	public int getTotalBranchCount(Criteria cri) {
		log.info("ServiceImpl getTotalBranchCount...");
		return adminMapper.getTotalBranchCount(cri);
	}
}