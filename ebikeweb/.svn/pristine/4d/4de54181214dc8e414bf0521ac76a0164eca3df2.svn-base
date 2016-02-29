package com.clt.systemmanger.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IPicPathDao;
import com.clt.systemmanger.dao.IPicTypeDao;
import com.clt.systemmanger.service.IPictureService;
import com.clt.util.SystemConstants;

@Service
public class PictureServiceImp implements IPictureService
{
	@Autowired
	private IPicTypeDao typeDao;
	@Autowired
	private IPicPathDao pathDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	
	/**
	 * @Description 通过分类名获取图片路径
	 * @author chengwzh
	 * @date 2015/5/21 16:00
	 */
	@Cacheable( value = "picPathCache" , key = "#id" )
	public String getPathById( int id )
	{
		String sql = "select id, VC_PATH,i_pic_type from t_pic_path "
		        + " where n_enable=" + SystemConstants.SYS_ENABLE + " and id=" + id;
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		if ( CollectionUtils.isEmpty( list ) )
		{
			return null;
		}
		Map< String , Object > map = list.get( 0 );
		String vcPath = map.get( "VC_PATH" ).toString();
		return vcPath;
	}
	
}
