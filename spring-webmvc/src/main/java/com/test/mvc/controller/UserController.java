package com.test.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.test.mvc.service.UserService;
import com.test.mvc.vo.UserEntityModel;
import com.test.mvc.vo.UserVO;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserVO> list() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public UserVO get(@PathVariable("id") Integer id) {
        return userService.selectById(id);
    }

    @GetMapping("/f/**")
    public String testPathVariable(HttpServletRequest request) {
        // Don't repeat a pattern
        // @PathVariable 注解中不能包含特殊字符，比如 / 文件分隔符，这是一种解决版本
        String pattern = (String)
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(pattern,
                request.getServletPath());
    }

    @RequestMapping("/greeting")
    public HttpEntity<UserEntityModel> greeting(@RequestParam Integer id) {
        UserEntityModel userEntityModel = new UserEntityModel(userService.selectById(id));
        userEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).greeting(id)).withSelfRel());
        return new ResponseEntity<>(userEntityModel, HttpStatus.OK);
    }

    @PostMapping
    public Integer add(UserVO user) {
        userService.addUser(user);
        return user.getId();
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable("id") int id) {
        LOGGER.info("delete user id = {}", id);
        return id;
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            LOGGER.info("test mock user id= {}", id);
            // 模拟从数据库获取对象
            UserVO user = new UserVO();
            user.setId(id);
            user.setAge(20);
            user.setUserName("mock name " + id);
            // 参数名key和目标类型UserVO首字母小写相同
            map.put("userVO", user);
        }
    }

    // @ModelAttribute("userVO") UserVO user
    @GetMapping("/testModelAttribute")
    public Object testModelAttribute(UserVO user) {
        LOGGER.info("test model attribute = {}", user);
        return user;
    }

    @GetMapping("/testConversionService")
    public Object testConversionService(@RequestParam("user") UserVO user) {
        return user;
    }

    /**
     * 测试 RequestBody 中 xml/json自动转换成 UserVO
     */
    @PostMapping("/testRequestBody")
    public Object testRequestBody(@RequestBody UserVO userVO) {
        return userVO;
    }
}
