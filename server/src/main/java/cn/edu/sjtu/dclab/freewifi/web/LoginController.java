package cn.edu.sjtu.dclab.freewifi.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.dclab.freewifi.domain.Merchant;
import cn.edu.sjtu.dclab.freewifi.service.IMerchantService;
import cn.edu.sjtu.dclab.freewifi.util.Constants;

@Controller
public class LoginController {
	@Resource(name = "merchantService")
	IMerchantService merchantService;

	@RequestMapping(value = {"/login","/logout"}, method = RequestMethod.GET)
	public ModelAndView loginView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		Merchant merchant = merchantService.login(username, password);
		if (merchant != null) {
			mav.setViewName("manage");
			HttpSession session = request.getSession();
			session.setAttribute(Constants.CURRENT_USER, merchant.getId());
		}else {
			mav.addObject("msg", "username or password error");
		}
		return mav;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerView(){
		return null;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSubmit(Merchant merchant){
		return null;
	}

	
}
