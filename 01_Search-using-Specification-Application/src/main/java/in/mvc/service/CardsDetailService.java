package in.mvc.service;

import org.springframework.data.jpa.domain.Specification;

import in.mvc.dto.RequestDto;
import in.mvc.dto.ResponseDto;
import in.mvc.entity.CardsTable;

public interface CardsDetailService<T> {

	Specification<CardsTable> getListSearchSpecification(RequestDto RequestDtos);

	ResponseDto getAllCardsDetail();
	
}
