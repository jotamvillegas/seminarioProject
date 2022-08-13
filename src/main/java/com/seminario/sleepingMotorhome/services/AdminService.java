package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Admin;
import com.seminario.sleepingMotorhome.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll() {
        return (List<Admin>) adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public void delete(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        adminRepository.delete(admin);
    }

    public boolean exist(String username) {
        return adminRepository.existsPersonByUserName(username);
    }

}
