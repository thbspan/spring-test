package com.test.security.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class VerifyCodeController {

    public static final String PATH_VC = "/vc.gif";

    private static final Logger LOGGER = LoggerFactory.getLogger(VerifyCodeController.class);

    /**
     * 也可以返回{@link ByteArrayResource}
     */
    @GetMapping(path = PATH_VC, produces = {
            MediaType.IMAGE_GIF_VALUE
    })
    public byte[] genVerifyCode(HttpSession session) {
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(150, 50, 4);
        String vcCode = gifCaptcha.getCode();
        LOGGER.info("current vc code: {}", vcCode);
        session.setAttribute("verify_code", vcCode);
        return gifCaptcha.getImageBytes();
    }
}
