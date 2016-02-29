/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-25 下午3:17:50 
 * @version V1.0 
 */
package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.clt.systemmanger.model.TAppSwitch;
import com.clt.systemmanger.model.TCarStylePic;
import com.clt.systemmanger.model.TDriverClass;
import com.clt.systemmanger.model.TGpsrate;
import com.clt.systemmanger.model.TPicPath;
import com.clt.systemmanger.model.TPicType;
import com.clt.systemmanger.model.TShareTag;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserStatus;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.systemmanger.service
 * @Description: TODO(基础信息管理)
 * @author liuwu
 * @date 2015-3-25 下午3:17:50
 * @version V1.0
 */
public interface IBasicManageService
{
	
	/**
	 * @Description: TODO(司机分类List)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:58:01
	 */
	Map< String , Object > findDriverClassList( HqlHelper helper );
	
	/**
	 * @Description: TODO(根据ID查出司机分类)
	 * @param parseInt
	 * @return TDriverClass 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:31:25
	 */
	TDriverClass getDriverClassById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driverClass
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:34:04
	 */
	void saveOrUpdateDriverClass( TDriverClass driverClass );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driverClass
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:34:40
	 */
	void saveDriverClass( TDriverClass driverClass );
	
	/**
	 * @Description: TODO(根据ID查找分享圈分类)
	 * @param parseInt
	 * @return TShareTag 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:42:39
	 */
	TShareTag getShareTagById( int parseInt );
	
	/**
	 * @Description: TODO(查出所有分享圈分类标签)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:44:40
	 */
	Map< String , Object > findShareTagList( HqlHelper helper );
	
	/**
	 * @Description: TODO(保存分享圈分类标签)
	 * @param shareTag
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:53:51
	 */
	void saveOrUpdateShareTag( TShareTag shareTag );
	
	/**
	 * @Description: TODO(新增保存分享圈分类标签)
	 * @param shareTag
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:54:44
	 */
	void saveShareTag( TShareTag shareTag );
	
	/**
	 * @Description: TODO(分享圈标签List)
	 * @return List<TShareTag> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 上午10:24:09
	 */
	List< TShareTag > findAllShareTags();
	
	/**
	 * @Description: TODO(消息推送)
	 * @param type
	 *            类型：0-所有人 1：内部;2:分供方;3:司机;4:金融公司
	 * @param shareTitle
	 *            消息标题
	 * @param shareMessage
	 *            消息内容 void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 下午2:33:41
	 */
	void pushMessageByType( String type , String shareTitle ,
	        String shareMessage );
	
	/**
	 * @Description: TODO(图片路径列表)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 下午5:36:48
	 */
	Map< String , Object > findPicPathList( HqlHelper helper );
	
	/**
	 * @Description: TODO(根据ID获取图片路径)
	 * @param parseInt
	 * @return TPicPath 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 下午5:52:12
	 */
	TPicPath getPicPathById( int parseInt );
	
	/**
	 * @Description: TODO(图片分类)
	 * @return List<TPicType> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午9:50:21
	 */
	List< TPicType > findAllPicTypes();
	
	/**
	 * @Description: TODO(编辑保存图片路径)
	 * @param tPicPath
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:19:07
	 */
	void saveOrUpdateTpicPath( TPicPath tPicPath );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicPath
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:19:52
	 */
	void saveTPicPath( TPicPath tPicPath );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:38:01
	 */
	Map< String , Object > findPicTypeList( HqlHelper helper );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TPicType 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:45:39
	 */
	TPicType getPicTypeById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicType
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:54:12
	 */
	void saveOrUpdateTpicType( TPicType tPicType );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicType
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:54:46
	 */
	void saveTPicType( TPicType tPicType );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 上午11:45:53
	 */
	Map< String , Object > findAppSwitchList( HqlHelper helper );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TAppSwitch 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 下午2:14:04
	 */
	TAppSwitch getAppSwitchById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAppSwitch
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 下午4:16:01
	 */
	void saveOrUpdateAppSwitch( TAppSwitch tAppSwitch );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAppSwitch
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 下午4:16:08
	 */
	void saveAppSwitch( TAppSwitch tAppSwitch );
	
	/**
	 * @Description: TODO(用户状态列表)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午1:48:48
	 */
	Map< String , Object > findUserStatusList( HqlHelper helper );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TUserStatus 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午1:54:50
	 */
	TUserStatus getUserStatusById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUserStatus
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午2:10:39
	 */
	void saveOrUpdateUserStatus( TUserStatus tUserStatus );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUserStatus
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午2:11:14
	 */
	void saveUserStatus( TUserStatus tUserStatus );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午5:38:41
	 */
	Map< String , Object > findTCarStylePicList( HqlHelper helper );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TCarStylePic 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午5:46:26
	 */
	TCarStylePic getCarStylePicById( int parseInt );
	
	/**
	 * @Description: TODO(保存汽车商标图片)
	 * @param tCarStylePic
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午6:07:36
	 */
	void saveOrUpdateCarStylePic( TCarStylePic tCarStylePic );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tCarStylePic
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-30 下午6:08:35
	 */
	void saveCarStylePic( TCarStylePic tCarStylePic );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-21 下午5:33:31
	 */
	Map< String , Object > findGpsRate( HqlHelper helper );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TGpsrate 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 上午9:46:30
	 */
	TGpsrate getGpsRateById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tGpsrate
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 上午10:23:17
	 */
	void updateGpsRate( TGpsrate tGpsrate );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 上午10:34:40
	 */
	void deleteGPSrateById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回值描述
	 * @author liuwu
	 * @param tUser
	 * @param request
	 * @return
	 * @create_date 2015-4-22 下午1:53:44
	 */
	String updateDriverInfo( TUser tUser , HttpServletRequest request );
	

	
}
