package com.java.moudle.system.dao;

import java.util.List;

import javax.inject.Named;

import com.java.moudle.system.dto.InitDictDto;
import com.java.until.dba.EntityManagerDao;

@Named
public class InitDao extends EntityManagerDao {

	/**
	 * 
	 * @Description 获取 父级字典 code ID
	 * @return
	 * @author sen
	 * @Date 2016年11月23日 下午8:51:25
	 */
	public List<InitDictDto> getDictAllParentList() {

		String sql = " select d.code, d.id from sys_dict d where d.parent_id = '0' and d.delete_flg = '0' ";
		return queryList(sql, null, InitDictDto.class);
	}


	public List<InitDictDto> getDictAllList() {

		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append(" select pd.code parentCode, d.code, d.name, d.remark ");
		sqlSb.append("   from sys_dict d ");
		sqlSb.append("   join sys_dict pd ");
		sqlSb.append("     on d.parent_id = pd.id ");
		sqlSb.append("    and pd.delete_flg = '0' ");
		sqlSb.append("  where d.parent_id != '0' ");
		sqlSb.append("    and d.delete_flg = '0' ");
		sqlSb.append("  order by d.order_num ");

		return queryList(sqlSb.toString(), null, InitDictDto.class);
	}

}
