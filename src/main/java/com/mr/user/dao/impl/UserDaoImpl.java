package com.mr.user.dao.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.mr.util.CustomHibernateDaoSupport;
import com.mr.util.search.JQGridFilter;
import com.mr.util.search.Rules;
import com.mr.user.dao.UserDao;
import com.mr.user.model.DUser;


@Repository("userDao")
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao{

	@Override
	public void save(DUser user) {
		getHibernateTemplate().save(user);	
	}

	@Override
	public void update(DUser user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void delete(DUser user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public DUser loadUserByUsername(String userName) {
		@SuppressWarnings("unchecked")
		List<DUser> list = getHibernateTemplate().find(
                "from DUser where userName = ?", userName
           );
		if (list.size() == 1){
			return list.get(0);
		} else return null;
	}

	@Override
	public DUser loadUserById(int id) {
		@SuppressWarnings("unchecked")
		List<DUser> list = getHibernateTemplate().find(
                "from DUser where userId = ?", id
           );	
		if (list.size() == 1){
			return list.get(0);
		} else return null;
	}

	@Override
	public List<DUser> loadAllUsers() {
		@SuppressWarnings("unchecked")
		List<DUser> list = getHibernateTemplate().find(
				"from DUser order by userName");
		return list;
	}

	@Override
	public List<DUser> findUserbygivenCriteria(JQGridFilter filter) throws IOException{
		StringBuilder sql = new StringBuilder("SELECT * FROM d_user WHERE ");
		Iterator<Rules> itr = filter.getRules().iterator(); 
		while(itr.hasNext()) {
			Rules rules = itr.next();
			String field = "";
			String compareOption = "= ";
			if (rules.getField().equalsIgnoreCase("userName")){
				field = "user_name ";
			} else if (rules.getField().equalsIgnoreCase("userEmail")){
				field = "user_email ";
			} else {
				throw new IOException("Cann't proccess the rules.field!");
			}
			if(rules.getOp().equalsIgnoreCase("eq")){
				compareOption = "= "; 
			} else if(rules.getOp().equalsIgnoreCase("cn")){
				compareOption = "LIKE ";
			} else{
				throw new IOException("Cann't proccess the rules.option!");
			}
			sql.append(field + compareOption + " :" + rules.getField() + " ");
			if (itr.hasNext()){
				if(filter.getGroupOp().equalsIgnoreCase("AND")){
					sql.append("AND ");
				} else if(filter.getGroupOp().equalsIgnoreCase("OR")){
					sql.append("OR ");
				} else{
					throw new IOException("Cann't proccess the GroupOp");
				}
			} else {
				sql.append(";");
			}
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity(DUser.class);
		for (Rules rules: filter.getRules()){
			if (rules.getOp().equalsIgnoreCase("eq")){
				query.setParameter(rules.getField(), rules.getData());
			} else {
				query.setParameter(rules.getField(), "%"+rules.getData()+"%");
			} 
		}
		@SuppressWarnings("unchecked")
		List<DUser> list = query.list(); 
		return list;
	}
}
