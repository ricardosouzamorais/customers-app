package com.assignment.customer.config.db.dialect;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * 
 * @author eugenp
 *
 *         This class is responsible for telling Hibernate how to deal with @id
 *         columns. We are considering simple keys that are based on
 *         {@link String}.
 */
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

	@Override
	public boolean supportsIdentityColumns() {
		return true;
	}

	@Override
	public String getIdentitySelectString(String table, String column, int type) throws MappingException {
		return "select last_insert_rowid()";
	}

	@Override
	public String getIdentityColumnString(int type) throws MappingException {
		return "integer";
	}

}
