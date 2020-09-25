package shop.carrental.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import shop.carrental.domain.Criteria;
import shop.carrental.domain.PageVO;
import shop.carrental.domain.TicketVO;
import shop.carrental.mappers.TicketMapper;

@Log4j
@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

	private TicketMapper ticketMapper;

	@Override
	public List<TicketVO> getTicketList() {
		log.info("ServiceImpl getTicketList...");
		return ticketMapper.getTicketList();
	}

	@Override
	public void getTicketPage(Long tno,Criteria cri,Model model) {
		log.info("ServiceImpl getTicketPage...");
		model.addAttribute("ticketPage", ticketMapper.getTicketPage(tno));
		model.addAttribute("pageMaker", new PageVO(cri, getTotal(cri)));
	}

	@Override
	public void getTicketListWithPaging(Criteria cri,Model model) {
		log.info("ServiceImpl getTicketListWithPaging...");
		model.addAttribute("ticketList", ticketMapper.getTicketListWithPaging(cri));
		
		int total = ticketMapper.getTotalCount(cri);// 페이지번호, 페이당 건수로 조회
		log.info("total:" + total);

		log.info("PageVO:" + new PageVO(cri, total));
		model.addAttribute("pageMaker", new PageVO(cri, total));
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("ServiceImpl getTotal...");
		return ticketMapper.getTotalCount(cri);
	}
}