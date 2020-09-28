package shop.carrental.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.carrental.domain.Criteria;
import shop.carrental.domain.TicketVO;

public interface TicketMapper {

	TicketVO getTicketPage(Long tno);

	List<TicketVO> getTicketListWithPaging(Criteria cri);

	int getTotalCount(Criteria cri);
}