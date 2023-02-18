package com.college.campaign.repository;

import com.college.campaign.entities.model.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMenuRepository extends JpaRepository<AdminMenu,Long> {
}
