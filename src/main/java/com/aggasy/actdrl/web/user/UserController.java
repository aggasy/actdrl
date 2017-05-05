package com.aggasy.actdrl.web.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aggasy.actdrl.util.UserUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// Activiti Identify Service
    private IdentityService identityService;

	@RequestMapping("/index")
	public String userIndex() {
		return "user/index";
	}
	
	@RequestMapping("/")
	public String index() {
		return "user/index";
	}

	/*@RequestMapping("/getUserInfo")
	public String getUserInfo(Integer userId, Map<String, Object> map) {

		logger.info("传递的用户userId=" + userId);
		User user = userService.findUserById(userId);
		map.put("userInfo", user);
		return "user/show";
	}*/

	/*@RequestMapping("/addInfo")
	public String addInfo(String userId, String firstName, String lastName, String password,
            String email, String imageResource, List<String> groups, List<String> userInfo) {
		logger.info("注册用户：" + userId);
		int result = userService.addUser(user);
		map.put("addRes", result);
		return "user/index";
	}*/
	
	/**
	 * 用户登录
     *
     * @param userId
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/logon")
    public String logon(@RequestParam("userid") String userId, @RequestParam("password") String password, HttpSession session) {
        logger.debug("logon request: {username={}, password={}}", userId, password);
        boolean checkPassword = identityService.checkPassword(userId, password);
        logger.debug("Check password result {}", checkPassword);
        
        if (checkPassword) {
            // read user from database
            User user = identityService.createUserQuery().userId(userId).singleResult();
            UserUtil.saveUserToSession(session, user);

            List<Group> groupList = identityService.createGroupQuery().groupMember(userId).list();
            session.setAttribute("groups", groupList);

            String[] groupNames = new String[groupList.size()];
            for (int i = 0; i < groupNames.length; i++) {
                System.out.println(groupList.get(i).getName());
                groupNames[i] = groupList.get(i).getName();
            }

            session.setAttribute("groupNames", ArrayUtils.toString(groupNames));

            return "redirect:/main/index";
        } else {
            return "redirect:/login?error=true";
        }
    }
    
    @Autowired
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "/login";
    }
}
