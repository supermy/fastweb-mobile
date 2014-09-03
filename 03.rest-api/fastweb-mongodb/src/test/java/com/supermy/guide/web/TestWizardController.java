package com.supermy.guide.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.supermy.guide.domain.Wizard;
import com.supermy.guide.service.WizardRepository;

/**
 * 
 * 向导式的跳转页面效果<br/>
 * 其中起到最大作用的就是@SessionAttributes这个注解，它定义了一个在session范围内的变量，这个变量可以在不同的页面跳转时保持状态。<br/>

第一次访问http:/www./example.com/wizard.htm时进入step1方法，其后提交的表单action后面都要加上相应的参数，如：<br/>

step1页面 <form action="http:/www./example.com/wizard.htm?_step=2"><br/>

step2页面 <form action="http:/www./example.com/wizard.htm?_step=3"><br/>

step3页面 <form action="http:/www./example.com/wizard.htm?_finish"><br/>
 * 
 * @author jamesmo
 *
 */
@Controller
@RequestMapping("/wizard.htm")
//需要保存在session中的变量
@SessionAttributes("wizard")
public class TestWizardController{
  
  @Resource(name = "beanService")
  private WizardRepository beanService;  
  
  @RequestMapping
  public String step1(final ModelMap modelMap){
    modelMap.addAttribute("wizard", new Wizard());
    return "step1";
  }
  
  @RequestMapping(params = "_step=2")
  public String step2(final @ModelAttribute("wizard") Wizard bean,
                      final Errors errors){
    return "step2";
  }
  
  @RequestMapping(params = "_step=3")
  public String step3(final @ModelAttribute("wizard") Wizard bean,
                      final Errors errors){
    return "step3";
  }
  
  @RequestMapping(params = "_finish")
  public String processFinish(@ModelAttribute("wizard")Wizard bean,
                              final Errors errors,
                              final ModelMap modelMap,
                              final SessionStatus status){

    //beanService.add(bean);   
    status.setComplete();
    return "success";
  }
  
  @RequestMapping(params = "_cancel")
  public String processCancel(final HttpServletRequest request,
                              final HttpServletResponse response,
                              final SessionStatus status){
     status.setComplete();
     return "cancel";
  }
    
}