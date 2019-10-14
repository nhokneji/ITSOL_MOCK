package com.itsol.mockup.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author anhvd_itsol
 */
public class SQLBuilder {
    public static final String SQL_MODULE_USERS = "users";
    public static final String SQL_MODULE_EMPLOYEES = "employees";

    public static String getSqlQueryById(String module,
                                         String queryId) {
        File folder = null;
        try {
            folder = new ClassPathResource(
                    "sql" + File.separator + module + File.separator + queryId + ".sql").getFile();

            // Read file
            if (folder.isFile()) {
                String sql = new String(Files.readAllBytes(Paths.get(folder.getAbsolutePath())));
                return sql;
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}
