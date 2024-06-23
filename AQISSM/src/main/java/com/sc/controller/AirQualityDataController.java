package com.sc.controller;

import com.sc.pojo.AirQualityData;
import com.sc.service.AirQualityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class AirQualityDataController {
    @Autowired
    AirQualityDataService ps;

    @RequestMapping("/show")
    public String show(HttpSession session){
        //做什么? 调用service 返回List
        List<AirQualityData> list=ps.show();
        //把List存储到作用域
        session.setAttribute("list",list);
        //跳转AirQualityData.jsp
        return "/AirQualityData.jsp";
    }
    @RequestMapping("/add")
    public String add(AirQualityData p) { //小bug 日期
        //做什么?   调用service(p) 新增数据库
        int i=ps.add(p);
        if(i>0){
            //跳转/show 查询最新数据 返回AirQualityData.jsp
            return "redirect:/show";
        }
        //新增失败 返回新增页面
        return "/add.jsp";
    }
    @RequestMapping("/delete")
    public String delete(String date) {
        int i=ps.delete(date);
        return "redirect:/show";
    }
}
