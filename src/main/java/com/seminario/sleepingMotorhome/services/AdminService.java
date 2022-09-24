package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Admin;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdminService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StatusRolService statusRolService;
    @Autowired
    private PersonTypeService personTypeService;


    public List<Admin> getAll (){
        return (List<Admin>) adminRepository.findAll();
    }

    @Transactional
    public void save(Admin admin) {
        Admin a;
        if (admin.getId() == null) {
            a = new Admin();
            a.setDateOfAdmission(new Date());
        }
        else {
            a = getAdminById(admin.getId());
        }

        if (lessThanThirtyCharacters(admin.getPassword()))
            a.setPassword(convertToBCryptPassword(admin.getPassword()));
        a.setUserName(admin.getUserName());
        a.setName(admin.getName());
        a.setSurname(admin.getSurname());
        a.setDocumentNumber(admin.getDocumentNumber());
        a.setAddressName(admin.getAddressName());
        a.setAddressNumber(admin.getAddressNumber());
        a.setFloor(admin.getFloor());
        a.setPhone(admin.getPhone());
        a.setDateOfEgress(admin.getDateOfEgress());
        a.setPersonType(personTypeService.getPersonType("ADMIN"));
        a.setStatusRol(searchStatusRol("Active"));

        adminRepository.save(a);
    }

    public void delete(Long adminId){
        Admin admin = adminRepository.findById(adminId).orElse(null);
        adminRepository.delete(admin);
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElse(null);
    }

    public Admin getAdminByUserName(String username){
        return adminRepository.findByUserName(username);
    }

    public boolean existEmployee (String username){
        return adminRepository.existsAdminByUserName(username);
    }

    public StatusRol searchStatusRol (String status){
        return statusRolService.getStatusRol(status);
    }

    private boolean lessThanThirtyCharacters (String pass){
        return pass.length() < 20;
    }

    private String convertToBCryptPassword (String pass){
        return bCryptPasswordEncoder.encode(pass);
    }



}
