package shop.carrental.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import shop.carrental.domain.AdminVO;
import shop.carrental.domain.BranchDTO;
import shop.carrental.domain.Criteria;
import shop.carrental.domain.PageVO;
import shop.carrental.domain.ShortCarDTO;
import shop.carrental.service.AdminService;
import shop.carrental.service.TicketService;

@Controller
@RequestMapping("/admin/")
@Log4j
@AllArgsConstructor
public class AdminController {

	private AdminService adminService;
	private TicketService ticketService;

	// ***Index***//

	@GetMapping("/index")
	public void home() {
		log.info("Get: index.jsp");
	}

	@GetMapping("/register")
	public void registerPage() {
		log.info("Get: register.jsp");
	}

	@PostMapping("/register")
	public String register(AdminVO vo, RedirectAttributes rttr) {
		log.info("Controller register executed(vo): " + vo);
		adminService.register(vo);
		rttr.addFlashAttribute("email", vo.getEmail());
		return "redirect:/admin/login";
	}

	@GetMapping("/login")
	public void loginPage() {
		log.info("Get: login.jsp");
	}

	@PostMapping("/login")
	public String login(AdminVO vo, HttpSession session) {
		if (vo.getEmail() == null || vo.getEmail().equals("")) {
			throw new IllegalArgumentException("Must write your Email");
		}

		AdminVO admin = adminService.getAdmin(vo);
		if (admin != null) {
			session.setAttribute("adminEmail", admin.getEmail());
			session.setAttribute("adminName", admin.getFirstName());
			log.info("Session Email: " + admin.getEmail());
			log.info("Session Name: " + admin.getFirstName());
			return "redirect:/admin/index";
		} else {
			return "redirect:/admin/login";
		}
	}

	@GetMapping("/logout")
	public String logoutPage(HttpSession session) {
		log.info("Get: logout.jsp");
		session.invalidate();
		return "redirect:/admin/index";
	}
	
	@GetMapping("/loginAdmin")
	public void loginAdminPage() {
		log.info("Get: loginAdmin.jsp");
	}
	
	@PostMapping("/loginFail")
	public String loginFail(RedirectAttributes rttr) {
		log.info("Get: loginFail");
		rttr.addFlashAttribute("error",1);
		return "redirect:/admin/index";
	}

	// ***Ticket***//

	@GetMapping("/ticket/list")
	public void ticketList(Criteria cri, Model model) {
		log.info("Controller ticketList executed");
		ticketService.getTicketListWithPaging(cri,model);
	}

	@GetMapping("/ticket/page")
	public void getTicketPage(@RequestParam("tno") Long tno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("Controller ticketPage executed");
		ticketService.getTicketPage(tno,cri,model);
	}

	//***shortcar***//
	
	@GetMapping("/shortcar/register")
	public void registerShortCar() {
		log.info("/shortcar/register");
	}

	@PostMapping("/shortcar/register")
	public String registerShortCar(ShortCarDTO dto, RedirectAttributes redirectAttributes) {
		log.info("RegisterShortCar");

		adminService.registerShortCar(dto, redirectAttributes);
		return "redirect:/admin/index";
	}
	
	//***branch***//
	
	@GetMapping("/branch/list")
	public void branchList(Criteria cri,Model model) {
		log.info("/branch/list");
		adminService.getBranchList(cri,model);
	}
	
	@GetMapping("/branch/register")
	public void branchRegisterPage() {
		log.info("/branch/register");
	}
	
	@PostMapping("/branch/register")
	public String registerBranch(BranchDTO dto) {
		log.info("/branch/register");
		adminService.registerBranch(dto);
		return "redirect:/admin/branch/list";
	}
}