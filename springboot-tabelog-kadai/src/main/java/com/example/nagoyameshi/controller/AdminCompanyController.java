package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nagoyameshi.entity.Company;
import com.example.nagoyameshi.form.CompanyEditForm;
import com.example.nagoyameshi.service.CompanyService;

@Controller
@RequestMapping("/admin/company")
public class AdminCompanyController {
   private final CompanyService companyService;

   public AdminCompanyController(CompanyService companyService) {
       this.companyService = companyService;
   }

   @GetMapping
   public String index(Model model) {
       Company company = companyService.findFirstCompanyByOrderByIdDesc();
       model.addAttribute("company", company);

       return "admin/company/index";
   }

   @GetMapping("/edit")
   public String edit(Model model) {
       Company company = companyService.findFirstCompanyByOrderByIdDesc();
       CompanyEditForm companyEditForm = new CompanyEditForm(company.getName(),
                                                             company.getPostalCode(),
                                                             company.getAddress(),
                                                             company.getRepresentative(),
                                                             company.getEstablishmentDate(),
                                                             company.getCapital(),
                                                             company.getBusiness(),
                                                             company.getNumberOfEmployees());
       model.addAttribute("companyEditForm", companyEditForm);

       return "admin/company/edit";
   }
}
