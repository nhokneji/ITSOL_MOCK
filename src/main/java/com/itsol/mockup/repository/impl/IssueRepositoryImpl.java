package com.itsol.mockup.repository.impl;

import com.itsol.mockup.entity.IssueEntity;
import com.itsol.mockup.repository.IssueRepositoryCustom;
import com.itsol.mockup.utils.HibernateUtil;
import com.itsol.mockup.utils.PageBuilder;
import com.itsol.mockup.web.dto.issue.IssueDTO;
import com.itsol.mockup.web.dto.request.IdRequestDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueRepositoryImpl implements IssueRepositoryCustom {
    @Override
    public Page<IssueDTO> findIssueByUserId(Integer pageSize, Integer page, Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            NativeQuery query = session.createNativeQuery("with ABC as( select USERS_ID, PROJECT_ID from TEAM " +
                    "inner join TEAM_USERS on TEAM.TEAM_ID = TEAM_USERS.TEAM_ID) " +
                    "select ISSUE.ISSUE_ID issueId,ISSUE.ISSUE_NAME issueName,ISSUE.CREATED_DATE createdDate,ISSUE.STATUS status,ISSUE.CONTENT content, ABC.USERS_ID userId from ISSUE " +
                    "inner join ABC on ISSUE.PROJECT_ID = ABC.PROJECT_ID where USERS_ID= :p_id ");
            query.setParameter("p_id", id);
            query.addScalar("issueId", new LongType());
            query.addScalar("issueName", new StringType());
            query.addScalar("createdDate", new DateType());
            query.addScalar("status", new IntegerType());
            query.addScalar("content", new StringType());
            query.addScalar("userId", new LongType());
            query.setResultTransformer(Transformers.aliasToBean(IssueDTO.class));

            int count = 0;
            List list = query.getResultList();
            if (list.size() > 0) {
                count = query.list().size();
            }

            if (page != null && pageSize != null) {
                IdRequestDTO idRequestDTO = new IdRequestDTO();
                idRequestDTO.setPage(page);
                idRequestDTO.setPageSize(pageSize);
                Pageable pageable = PageBuilder.buildPageable(idRequestDTO);
                if (pageable != null) {
                    query.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
                    query.setMaxResults(pageable.getPageSize());
                }
                List<IssueDTO> data = query.list();
                Page<IssueDTO> dataPage = new PageImpl<>(data, pageable, count);
                return dataPage;
            }

        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return null;
    }


    @Override
    public List<IssueDTO> findByUserId(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            NativeQuery query = session.createNativeQuery("with ABC as( select USERS_ID, PROJECT_ID from TEAM " +
                    "inner join TEAM_USERS on TEAM.TEAM_ID = TEAM_USERS.TEAM_ID) " +
                    "select ISSUE.ISSUE_ID issueId,ISSUE.ISSUE_NAME issueName,ISSUE.CREATED_DATE createdDate,ISSUE.STATUS status,ISSUE.CONTENT content, ABC.USERS_ID userId from ISSUE " +
                    "inner join ABC on ISSUE.PROJECT_ID = ABC.PROJECT_ID where USERS_ID= :p_id ");
            query.setParameter("p_id", id);
            query.addScalar("issueId", new LongType());
            query.addScalar("issueName", new StringType());
            query.addScalar("createdDate", new DateType());
            query.addScalar("status", new IntegerType());
            query.addScalar("content", new StringType());
            query.addScalar("userId", new LongType());
            query.setResultTransformer(Transformers.aliasToBean(IssueDTO.class));
            List<IssueDTO> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return null;
    }


}