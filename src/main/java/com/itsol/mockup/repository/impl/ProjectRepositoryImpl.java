package com.itsol.mockup.repository.impl;

import com.itsol.mockup.repository.ProjectRepositoryCustom;
import com.itsol.mockup.utils.HibernateUtil;
import com.itsol.mockup.web.dto.request.ReportDTO;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Transformer;
import java.util.List;


@Repository
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
    @Override
    public List<ReportDTO> findProjectNameAndTeamLead(Long projectId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            NativeQuery query = session.createNativeQuery("with TEAM_PROJECT as(select PROJECT_NAME, TEAM_ID,TEAM_NAME from PROJECT p" +
                    " inner join TEAM t on p.PROJECT_ID = t.PROJECT_ID where p.PROJECT_ID = :p_id )," +
                    "TEAM_PROJECT_USER as (select tu.TEAM_ID,tu.USERS_ID,tp.PROJECT_NAME" +
                    " from TEAM_USERS tu " +
                    "inner join TEAM_PROJECT tp on tu.TEAM_ID = tp.TEAM_ID)," +
                    "USER_ROLE_MANAGER as( select u.USERS_ID, u.FULL_NAME from USERS u " +
                    "inner join USERS_ROLE ur on u.USERS_ID = ur.USERS_ID " +
                    "inner join ROLE r on ur.ROLE_ID = r.ROLE_ID where r.ROLE_NAME = 'TEAMLEAD')" +
                    "select urm.USERS_ID userId, urm.FULL_NAME fullName,tpu.PROJECT_NAME projectName from USER_ROLE_MANAGER urm " +
                    "inner join TEAM_PROJECT_USER tpu on tpu.USERS_ID = urm.USERS_ID");
            query.setParameter("p_id", projectId);
            query.addScalar("userId", new LongType());
            query.addScalar("fullName", new StringType());
            query.addScalar("projectName", new StringType());
            query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

            List<ReportDTO> list = query.list();
            return list;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public ReportDTO getTotalMember(Long projectId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            NativeQuery query = session.createNativeQuery("with TEAM_PROJECT as(select PROJECT_NAME, TEAM_ID,TEAM_NAME from PROJECT p" +
                    " inner join TEAM t on p.PROJECT_ID = t.PROJECT_ID where p.PROJECT_ID = :p_id )," +
                    " TEAM_PROJECT_USER as (select tu.TEAM_ID,tu.USERS_ID,tp.PROJECT_NAME" +
                    " from TEAM_USERS tu" +
                    " inner join TEAM_PROJECT tp on tu.TEAM_ID = tp.TEAM_ID) " +
                    "select count(USERS_ID) totalMember from TEAM_PROJECT_USER");
            query.setParameter("p_id", projectId);
            query.addScalar("totalMember", new IntegerType());
            query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));
            List<ReportDTO> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return null;
    }
}