package com.java.carProject.controller;

import com.java.carProject.entity.Cars;
import com.java.carProject.entity.Customers;
import com.java.carProject.model.CustomerBindingModel;
import com.java.carProject.repository.CustomerRepository;
import com.java.carProject.service.CarsService;
import com.java.carProject.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@Controller
public class CustomerController {

    @InitBinder("user")
    public void customizeBinding(WebDataBinder binder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "birthDate",
                new CustomDateEditor(dateFormatter, true));
    }

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("/customers/all/ascending")
    public String getCustomersByBirthDateAsc(Model model){
       List<Customers> customersListAsc = customerService.getCustomersByBirthDateAsc();
       model.addAttribute("customersListAsc",customersListAsc);
        return "customersAsc";
    }
    @GetMapping("/customers/all/descending")
    public String getCustomersByBirthDateDesc(Model model){
        List<Customers> customersListDesc = customerService.getCustomersByBirthDateDesc();
        model.addAttribute("customersListDesc",customersListDesc);
        return "customersDesc";
    }

    @PostMapping("/customers")
    public String addCustomer(@Valid @ModelAttribute CustomerBindingModel customerBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "AddCustomer";
        }else {
            ModelMapper modelMapper = new ModelMapper();
            Customers customers =  modelMapper.map(customerBindingModel, Customers.class);
            String returnPage = "";
            LocalDate today = LocalDate.now();
            Instant instant = customers.getBirthDate().toInstant();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate customerLocalDate = instant.atZone(defaultZoneId).toLocalDate();
            Period period = Period.between(customerLocalDate, today);
            if (period.getYears() >= 18) {
                customers.setYoungDriver(false);
            } else {
                customers.setYoungDriver(true);
            }
            Customers savedCustomer = customerRepository.save(customers);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedCustomer.getId()).toUri();
            if (ResponseEntity.created(location).build().getStatusCode().equals(HttpStatus.CREATED)) {
                Long customerId = savedCustomer.getId();
                returnPage = "redirect:/customers/" + customerId;
            } else {
                // error page redirect
            }
            return returnPage;
        }

    }

    @GetMapping("/customers")
    public String addCustomer(CustomerBindingModel customerBindingModel){
        return "AddCustomer";
    }
    @GetMapping("/customers/{id}")
    public String getCustomerById(@PathVariable Long id, Model model){
        Customers customers =customerService.getCustomerById(id);
        model.addAttribute("customers",customers);
        return "customerById";
    }

    @PutMapping("/editCustomer")
    public String editCustomer( @Valid @ModelAttribute CustomerBindingModel customerBindingModel,BindingResult bindingResult){
        Long id =  customerBindingModel.getId();
        if(bindingResult.hasErrors()){
            return "editCustomer";
        }else{
            ModelMapper modelMapper= new ModelMapper();
            Customers customer = modelMapper.map(customerBindingModel,Customers.class);
            customer.setId(id);
            customerService.updateCustomer(customer);
            return "redirect:customers/" + customer.getId();
        }

    }
    @GetMapping("/editCustomer/{id}")
    public String editCustomerById(@PathVariable Long id, CustomerBindingModel customerBindingModel, Model model){
        Customers customers = customerService.getCustomerById(id);
        ModelMapper modelMapper = new ModelMapper();
        customerBindingModel = modelMapper.map(customers,CustomerBindingModel.class);
        model.addAttribute("customerBindingModel",customerBindingModel);
        return "editCustomer";
    }

}
