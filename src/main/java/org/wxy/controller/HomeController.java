package org.wxy.controller;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wxy.utils.HttpUtils;

/**
 * @author: wu.xiaoyuan
 * @date: 2018-08-04
 **/
@Controller
public class HomeController {

  @RequestMapping(value = "/login",method = RequestMethod.GET)
  public String login(){
    return "login";
  }

  /**
   * 向授权服务器请求token
   * @param request
   * @return
   */
  @RequestMapping("/main")
  public String main(HttpServletRequest request) {
    String code = request.getParameter("code");
    System.out.println("code=" + code);
    // 封装token请求参数
    Map<String, String> params = new HashMap<String, String>();
    params.put("code", code);
    params.put("grant_type", "authorization_code");
    params.put("client_id", "githubClientId");
    params.put("client_secret", "githubSecret");
    params.put("redirect_uri", "http://localhost:8001/main");
    /**
     * 向认证服务器申请令牌，对应授权码模式中的步骤（D）
     */
    String result = HttpUtils.doPost("http://localhost:8080/accessToken", params);
    System.out.println("返回结果：" + result);
    JSONObject json = JSONObject.parseObject(result);
    String status=json.getString("status");
    if ("success".equals(status)) {
      System.out.print("access_token=" + json.getString("access_token"));
      System.out.print(",token_type=" + json.getString("token_type"));
      System.out.print(",scope=" + json.getString("scope")+"\n");
      // 获取用户信息
      params.clear();
      params.put("access_token", json.getString("access_token"));
      String userInfo = HttpUtils.doPost("http://localhost:8080/getUserInfo", params);
      JSONObject rsp = JSONObject.parseObject(userInfo);
      if ("success".equals(rsp.getString("status"))) {
        System.out.println("user_name=" + rsp.getString("user_name"));
        request.setAttribute("user_name", rsp.getString("user_name"));
      }
      request.setAttribute("access_token", json.getString("access_token"));
      request.setAttribute("token_type", json.getString("token_type"));
      request.setAttribute("scope", json.getString("scope"));
      return "main";
    }
    System.out.println("获取token失败");
    System.out.println("error=" + json.get("error"));
    System.out.println("error_describe=" + json.get("error_describe"));
    return "error";
  }
}
