package in.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import in.mvc.dto.CardDTO;
import in.mvc.dto.RequestDto;
import in.mvc.dto.ResponseDto;
import in.mvc.dto.SearchRequestDto;
import in.mvc.entity.CardsTable;
import in.mvc.enums.CardsFieldEnums;
import in.mvc.repo.CardsRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;

@Service
public class CardsDetailServiceImpl implements CardsDetailService<CardsTable> {

	private static final Logger Log = LogManager.getLogger(CardsDetailServiceImpl.class);

	@Autowired
	private CardsRepository cardsRepository;

	// For multiple column search
	public Specification<CardsTable> getListSearchSpecification(RequestDto requestDtos) {
		Log.info("=========getSearchSpecification invocked ========= ");
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();
			for (SearchRequestDto requestDto : requestDtos.getSearchRequestDto()) {
				Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
				predicates.add(equal);
			}
			
			if(!StringUtils.isEmpty(requestDtos.getFirstName())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(CardsFieldEnums.FIRST_NAME.getValue())),requestDtos.getFirstName().toLowerCase()+"%"));
			}
			if(!StringUtils.isEmpty(requestDtos.getUsername())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(CardsFieldEnums.USERNAME.getValue())),requestDtos.getUsername().toLowerCase()+"%"));
			}
		
			if(!StringUtils.isEmpty(requestDtos.getCardNumber())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(CardsFieldEnums.CARD_NUMBER.getValue())),requestDtos.getCardNumber().toLowerCase()+"%"));
			}
			if(!StringUtils.isEmpty(requestDtos.getClientName())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(CardsFieldEnums.CLIENT_NAME.getValue())),requestDtos.getClientName().toLowerCase()+"%"));
			}
			if(!StringUtils.isEmpty(requestDtos.getEmail())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(CardsFieldEnums.EMAIL.getValue())),requestDtos.getEmail().toLowerCase()+"%"));
			}
			if(!StringUtils.isEmpty(requestDtos.getCountry())) {
				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(CardsFieldEnums.COUNTRY.getValue())),requestDtos.getCountry().toLowerCase()+"%"));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	@Override
	public ResponseDto getAllCardsDetail() {
		Log.info("getAllCardsDetail Executed Successfully....... ");
		ResponseDto responseDto = new ResponseDto();
		List<CardDTO> cardDtos =new ArrayList<>();
		try {
			List<CardsTable> cardsDetail = cardsRepository.findAll();
			if (cardsDetail == null || cardsDetail.isEmpty()) {
				Log.info("No cards found or the list is null.");
				responseDto.setStatus("Failed");
				responseDto.setStatusCode(404);
				responseDto.setMessage("No cards found or the list is null.");
				responseDto.setSuccess(false);
				return responseDto;
			} else {
				Log.info("Cards retrieved successfully: " + cardsDetail.size() + " cards.");
				System.out.println(cardsDetail);
				
				for(CardsTable card:cardsDetail) {
					CardDTO cardDto = new CardDTO();
					cardDto.setFirstName(card.getFirstName());
					cardDto.setLastName(card.getLastName());
					cardDto.setUsername(card.getUsername());
					cardDto.setCardNumber(card.getCardNumber());
					cardDto.setCardArray(card.getCardArray());
					cardDto.setClientName(card.getClientName());
					cardDto.setEmail(card.getEmail());
					cardDto.setCountry(card.getCountry());
					cardDto.setGender(card.getGender());
					cardDtos.add(cardDto);
				}
				
				responseDto.setData(cardDtos);
				responseDto.setStatus("Success");
				responseDto.setErrorCode(200);
				responseDto.setMessage("retrived successfully");
				responseDto.setSuccess(true);
				return responseDto;
			}

		} catch (Exception e) {
		     Log.error("Problem occurred while getting data with getAllCardsDetail: ", e);
		        responseDto.setStatus("Failed");
		        responseDto.setStatusCode(500);
		        responseDto.setMessage("An error occurred while retrieving cards.");
		        responseDto.setSuccess(false);
		}
		return responseDto;
	}

}
