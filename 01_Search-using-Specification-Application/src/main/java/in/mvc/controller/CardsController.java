package in.mvc.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.mvc.dto.RequestDto;
import in.mvc.dto.ResponseDto;
import in.mvc.entity.CardsTable;
import in.mvc.repo.CardsRepository;
import in.mvc.service.CardsDetailService;

@RestController
@CrossOrigin()
public class CardsController {
	
	@Autowired
	private CardsDetailService<CardsTable> cardsDetailService;
	
	@Autowired
	private CardsRepository cardsRepository;
	
	public static final Logger Log = LogManager.getLogger(CardsController.class);

//	@GetMapping("/")
//	public ModelAndView getCardsPage() {
//		Log.info("=========Cards page loaded ========= ");
//		ModelAndView view = new ModelAndView();
//		view.setViewName("index");
//		return view;
//	}
//	@GetMapping("/")
//	public String getview(Model model) {
//		Log.info("getRegistrationform Loaded.... ");
//		CardsTable cards = new CardsTable();
//		model.addAttribute("user", cards);
//		return "index";
//	}

	
//	@GetMapping("/getFilter-data")
//    public List<CardsTable> filterData(
//            @RequestParam(required = false) String username,
//            @RequestParam(required = false) String cardNumber,
//            @RequestParam(required = false) String cardArray,
//            @RequestParam(required = false) String clientName, 
//		@RequestParam(required = false) String country) {
//		Log.info("=========getFilter-data invocked ========= ");
//        // Call service to handle filtering logic
//        return cardsDetailService.getFilteredData(username, cardNumber, cardArray, clientName,country);
//    }
//	@PostMapping("/specs")
//	public List<CardsTable> getCards(){
//		Specification<CardsTable> specification = new Specification<CardsTable>() {
//
//			@Override
//			public Predicate toPredicate(Root<CardsTable> root, CriteriaQuery<?> query,
//					CriteriaBuilder criteriaBuilder) {
//				return criteriaBuilder.equal(root.get("username"),"charlotte_murphy28");
//			}
//		};
//		List<CardsTable> all = cardsRepository.findAll(specification);
//		return all;
//	}

//	@PostMapping("/specs")
//	public List<CardsTable> getCards(@RequestBody RequestDto requestDto){
//		Log.info("=========getCards-data invocked ========= ");
//		Specification<CardsTable> specs =  cardsDetailService.getSearchSpecification(requestDto.getSearchRequestDto());
//		return cardsRepository.findAll(specs);
//		
//	}
	
	@GetMapping("/index")
	public ModelAndView getCardsPage() {
	    Log.info("=========Cards page loaded ========= ");
//	    ModelAndView view = new ModelAndView();
//	    view.setViewName("index"); // Renders the JSP page
	    return new ModelAndView("index");
	}
	
	// API  for Retrive all cards
	@GetMapping("/cards")
	public ResponseEntity<ResponseDto> getAllCardsDetail() {
	    Log.info("========getAllCardsDetail invoked ========= ");
	    ResponseDto response = cardsDetailService.getAllCardsDetail();
	    return ResponseEntity.ok(response);
	}
	
	// API for Card Array and Gender Selection
	@PostMapping("/specs")
	public List<CardsTable> getListCards(@RequestBody RequestDto requestDto){
		Log.info("=========getCards-data invocked ========= ");
		Specification<CardsTable> specs =  cardsDetailService.getListSearchSpecification(requestDto);
		return cardsRepository.findAll(specs);
	}

}
