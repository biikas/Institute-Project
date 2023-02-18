package com.college.campaign.repository.custom.impl;

import com.college.campaign.entities.model.Teacher;
import com.college.campaign.repository.custom.TeacherCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Bikash Shah
 */
@Slf4j
@Repository
@Transactional
public class TeacherRepositoryImpl implements TeacherCustom {
    @PersistenceContext
    private EntityManager em;

    protected Teacher teacher;


    @Override
    public List<Teacher> searchQuery(String firstName,String mobileNumber) {
        try {
            String whereClause = "";

            String sql = "SELECT * FROM TEACHER T"
                    + " WHERE T.FIRST_NAME = '" + firstName
                    + "' OR T.MOBILE_NUMBER1 = '"+mobileNumber+"'";
            sql += whereClause + " ORDER BY T.ID DESC";

            log.info("Sql query : {}",sql);

            Query query = em.createNativeQuery(sql, Teacher.class);
            List<Teacher> teacherList = (List<Teacher>) query.getResultList();
            return teacherList;

        } catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }
}
