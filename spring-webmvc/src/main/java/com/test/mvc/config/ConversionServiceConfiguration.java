package com.test.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.test.mvc.vo.UserVO;

@Configuration
public class ConversionServiceConfiguration {

    /**
     * springboot中配置Converter的方式
     * 参考 ConversionService
     */
    @Bean
    public Converter<String, UserVO> converter() {
        return new UserVOConverter();
    }

    public static class UserVOConverter implements Converter<String, UserVO> {

        @Override
        public UserVO convert(String source) {
            if (!StringUtils.hasText(source)) {
                return null;
            }
            String[] strings = source.split("_");
            if (strings.length == 3) {
                UserVO userVO = new UserVO();
                userVO.setId(Integer.parseInt(strings[0]));
                userVO.setUserName(strings[1]);
                userVO.setAge(Integer.parseInt(strings[2]));
                return userVO;
            }
            return null;
        }
    }
}
