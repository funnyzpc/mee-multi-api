package com.mee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author funnyzpc
 * @description simple get client IP
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("X-Real-IP");
            if(StringUtils.isEmpty(ip)){
                ip =request.getRemoteAddr();
            }
            if(StringUtils.isEmpty(ip)){
                log.error("client ip is empty!");
                throw new Exception("client IP is empty!");
            }
            String forwardip = request.getHeader("x-forwarded-for");
            if(!ip.equalsIgnoreCase(forwardip)){
                log.error("real IP:{} and forwarded:{},not equal!",ip,forwardip);
            }
            log.info("GET client REAL_IP:{},forwarded_IP:{}",ip,forwardip);
            return ip;
        } catch (Exception e) {
            log.error("Get client IP error!",e);
            return null==ip?"":ip;
        }
    }

}
