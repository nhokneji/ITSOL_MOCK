package com.itsol.mockup.repository.impl;

import com.itsol.mockup.entity.Users;
import com.itsol.mockup.repository.UsersRepositoryCustom;
import com.itsol.mockup.utils.DataUtils;
import com.itsol.mockup.utils.HibernateUtil;
import com.itsol.mockup.utils.PageBuilder;
import com.itsol.mockup.utils.SQLBuilder;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author anhvd_itsol
 */

@Repository
public class UsersRepositoryImpl implements UsersRepositoryCustom {
    private static final Logger logger = LogManager.getLogger(UsersRepositoryImpl.class);

    @Override
    public Page<UsersDTO> findUsersByFullNameAndUserName(SearchUsersRequestDTO requestDTO) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SQLBuilder.getSqlQueryById(SQLBuilder.SQL_MODULE_USERS, "select-user"));
            if (!DataUtils.isNullOrEmpty(requestDTO.getFullName())) {
                sb.append(" AND UPPER(u.full_name) like :p_full_name ");
            }
            if (!DataUtils.isNullOrEmpty(requestDTO.getUserName())) {
                sb.append(" AND UPPER(u.user_name) like :p_user_name ");
            }

            SQLQuery query = session.createSQLQuery(sb.toString());

            if (!DataUtils.isNullOrEmpty(requestDTO.getFullName())) {
                query.setParameter("p_full_name", "%" +
                        requestDTO.getFullName().trim().toUpperCase()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }

            if (!DataUtils.isNullOrEmpty(requestDTO.getUserName())) {
                query.setParameter("p_user_name", "%" +
                        requestDTO.getUserName().trim().toUpperCase()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }

            query.addScalar("id", new LongType());
            query.addScalar("userName", new StringType());
            query.addScalar("passWord", new StringType());
            query.addScalar("fullName", new StringType());
            query.addScalar("role", new ShortType());

            query.setResultTransformer(Transformers.aliasToBean(UsersDTO.class));
            int count = 0;
            List<UsersDTO> list = query.list();
            if (list.size() > 0) {
                count = query.list().size();
            }

            if (requestDTO.getPage() != null && requestDTO.getPageSize() != null) {
                Pageable pageable = PageBuilder.buildPageable(requestDTO);
                if (pageable != null) {
                    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                    query.setMaxResults(pageable.getPageSize());
                }
                List<UsersDTO> data = query.list();

                Page<UsersDTO> dataPage = new PageImpl<>(data, pageable, count);
                return dataPage;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return null;
    }
}
