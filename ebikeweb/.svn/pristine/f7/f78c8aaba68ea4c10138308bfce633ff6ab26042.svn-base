/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-16 下午2:43:55
 * @version V1.0
 */
package com.clt.sub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.model.TApplyPic;
import com.clt.sub.service.ITApplyPicService;
import com.clt.systemmanger.service.IStaticService;

/**
 * @Package com.clt.sub.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-16 下午2:43:55
 * @version V1.0
 */
@Service
public class ApplyPicServiceImp implements ITApplyPicService
{
	@Autowired
	private IStaticService staticService;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tApplyPic
	 * @author liuwu
	 * @create_date 2015-6-16 下午2:46:56
	 */
	public void parseUrl( TApplyPic tApplyPic , String type )
	{
		String perUrl = staticService.getStringByParame( type );
		if ( tApplyPic != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			
			// tDamage.setVcPicpath( perUrl + tDamage.getVcPicpath() );
			/*String jpgPathStr = tApplyPic.getVcPicPath();
			String[] jpgPaths = jpgPathStr.split( "," );
			String vcImgpath = "";
			for ( int i = 0 ; i < jpgPaths.length ; i++ )
			{
				vcImgpath += perUrl + jpgPaths[i];
				if ( i != jpgPaths.length - 1 )
				{
					vcImgpath += ",";
				}
			}*/
			
			tApplyPic.setVcPicPath( perUrl + tApplyPic.getVcPicName() );
		}
		
	}
}
