package shop.carrental.service;

import java.util.List;

import org.springframework.ui.Model;

import shop.carrental.domain.Criteria;
import shop.carrental.domain.TicketVO;

public interface TicketService {

	public List<TicketVO> getTicketList(); // no longer used

	public void getTicketPage(Long tno, Criteria cri, Model model);

	public void getTicketListWithPaging(Criteria cri,Model model);

	public int getTotal(Criteria cri);

}