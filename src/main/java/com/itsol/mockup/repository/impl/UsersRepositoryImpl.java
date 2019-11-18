package com.itsol.mockup.repository.impl;

import com.itsol.mockup.entity.RoleEntity;
import com.itsol.mockup.entity.UsersEntity;
import com.itsol.mockup.repository.UsersRepositoryCustom;
import com.itsol.mockup.utils.DataUtils;
import com.itsol.mockup.utils.HibernateUtil;
import com.itsol.mockup.utils.PageBuilder;
import com.itsol.mockup.utils.SQLBuilder;
import com.itsol.mockup.web.dto.request.IdRequestDTO;
import com.itsol.mockup.web.dto.request.SearchUsersRequestDTO;
import com.itsol.mockup.web.dto.users.UsersDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
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
    public Page<UsersDTO> findUsersByUsernameAndEmailAndRoles(SearchUsersRequestDTO requestDTO) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            StringBuilder sb = new StringBuilder();
//            sb.append(SQLBuilder.getSqlQueryById(SQLBuilder.SQL_MODULE_USERS, "select-user"));

            sb.append("SELECT u.users_id userId, u.username userName, u.password passWord, u.full_name fullName, u.email email, u.skype skypeName, u.phone phone, u.education education, u.level_id levelId, u.image_id imageId,  r.role_name roleName FROM USERS u ");
            sb.append(" inner join users_role ur on u.users_id = ur.users_id join role r on ur.role_id = r.role_id WHERE 1 = 1");

            if (!DataUtils.isNullOrEmpty(requestDTO.getUserName())) {
//                sb.append(" AND UPPER(u.full_name) like :p_full_name ");
                sb.append(" AND UPPER(u.username) like :p_user_name");
            }
            if (!DataUtils.isNullOrEmpty(requestDTO.getEmail())) {
                sb.append(" AND UPPER(u.email) like :p_email ");
            }
            if (!DataUtils.isNullOrZero(requestDTO.getRole())) {
                sb.append(" AND ur.role_id  = " + requestDTO.getRole());
            }

            SQLQuery query = session.createSQLQuery(sb.toString());

            if (!DataUtils.isNullOrEmpty(requestDTO.getUserName())) {
                query.setParameter("p_user_name", "%" +
                        requestDTO.getUserName().trim().toUpperCase()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }

            if (!DataUtils.isNullOrEmpty(requestDTO.getEmail())) {
                query.setParameter("p_email", "%" +
                        requestDTO.getEmail().trim().toUpperCase()
                                .replace("\\", "\\\\")
                                .replaceAll("%", "\\%")
                                .replaceAll("_", "\\_")
                        + "%");
            }

            query.addScalar("userId", new LongType());
            query.addScalar("userName", new StringType());
            query.addScalar("passWord", new StringType());
            query.addScalar("fullName", new StringType());
            query.addScalar("email", new StringType());
            query.addScalar("skypeName", new StringType());
            query.addScalar("phone", new StringType());
            query.addScalar("education", new StringType());
            query.addScalar("levelId", new IntegerType());
            query.addScalar("imageId", new IntegerType());
            query.addScalar("roleName", new StringType());
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

    @Override
    public Page<UsersDTO> findUserNotRequest(IdRequestDTO requestDTO) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SQLBuilder.getSqlQueryById(SQLBuilder.SQL_MODULE_USERS, "select-user"));
            for (Long id : requestDTO.getIds()) {
                if (DataUtils.isNullOrZero(id)){
                    logger.error("ID NULL OR EMPTY");
                }
            }
            sb.append("AND USERS_ID NOT IN :p_ids");

            SQLQuery query = session.createSQLQuery(sb.toString());

            query.setParameterList("p_ids", requestDTO.getIds());

            query.addScalar("userId", new LongType());
            query.addScalar("userName", new StringType());
            query.addScalar("passWord", new StringType());
            query.addScalar("fullName", new StringType());
            query.addScalar("email", new StringType());
            query.addScalar("skypeName", new StringType());
            query.addScalar("phone", new StringType());
            query.addScalar("imageId", new IntegerType());
            query.addScalar("levelId", new IntegerType());

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

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return null;
    }
}
