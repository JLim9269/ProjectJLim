package shop.carrental.service;

import org.springframework.ui.Model;

import shop.carrental.domain.Criteria;

public interface TicketService {

	public void getTicketPage(Long tno, Criteria cri, Model model);

	public void getTicketListWithPaging(Criteria cri,Model model);

	public int getTotal(Criteria cri);
}