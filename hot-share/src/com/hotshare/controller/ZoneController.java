/**
 * Program  : ZoneController.java
 * Author   : gkx
 * Create   : 2014-8-7 下午19:06:10
 */

package com.hotshare.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotshare.bean.Zone;
import com.hotshare.constants.Constant;
import com.hotshare.exception.ControllerException;
import com.hotshare.json.bean.DataGrid;
import com.hotshare.json.bean.DataInfo;
import com.hotshare.json.bean.OrderBean;
import com.hotshare.json.bean.PageBean;
import com.hotshare.json.bean.ResultBean;
import com.hotshare.service.ZoneService;
import com.hotshare.util.GsonUtils;


/**
* 地区控制器
* @author   gkx
* @version  1.0.0
* @2014-8-7 下午19:06:10
*/
@Controller
@RequestMapping(value = "/zone")
public class ZoneController extends BaseController{
	
	@Resource(name = "zoneService")
	private ZoneService zoneService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	
	/**
	 * 获得地区列表
	 * @author gkx
	 * @create 2014-8-7 下午19:53:51
	 * @since 
	 * @param request
	 * @param response
	 * @param model
	 * @param page
	 * @param order
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/listZone")
	public void findAllZone(HttpServletRequest request, HttpServletResponse response, Model model, PageBean page, OrderBean order) throws ControllerException {
		DataGrid<Zone> datas = zoneService.findAllZone(page, order);
		String resultJson = GsonUtils.objectToJsonDateSerializer(datas, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	/**
	 * 增加地区
	 * @author gkx
	 * @create 2014-8-7 下午19:59:02
	 * @since 
	 * @param request
	 * @param response
	 * @param model
	 * @param zone
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/addZone")
	public void addZone(HttpServletRequest request, HttpServletResponse response, Model model,Zone zone) throws ControllerException {
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		
		if(province!="1"){
			if(!city.equals("2")){
				if(!area.equals("3")){
					zone.setFatherNo(area);
					Zone areaZone =	zoneService.findMaxZone(area);
					if(areaZone==null){
						String zoneNoNum = area+"00"; 
						long  zoneNoSum =  Long.parseLong(zoneNoNum);
						zone.setZoneNo(String.valueOf(zoneNoSum+1));
						zone.setLevel((short)4);
					}else{
						zone.setFatherNo(area);
						String  zoneNo  =areaZone.getZoneNo();
						long  zoneNoSum = Long.parseLong(zoneNo);
						zone.setZoneNo(String.valueOf(zoneNoSum+1));
						zone.setLevel((short)4);
					}	
				}else{
					zone.setFatherNo(city);
					Zone  cityZone  = zoneService.findMaxZone(city);
					int  zoneNoNum  = 0 ;
					if(cityZone==null){
						zoneNoNum =  Integer.parseInt(city);
					}else{
						String zoneNo = cityZone.getZoneNo();
						zoneNoNum =  Integer.parseInt(zoneNo);
					}	
					String  zoneNoSum = String.valueOf(zoneNoNum + 10);
					zone.setZoneNo(zoneNoSum);
					zone.setLevel((short)3);
				}
			}else{
				zone.setFatherNo(province);
				String  zoneNo  = zoneService.findMaxZone(province).getZoneNo();
				int  zoneNoNum =  Integer.parseInt(zoneNo);
				String  zoneNoSum = String.valueOf(zoneNoNum + 10000);
				zone.setZoneNo(zoneNoSum);
				zone.setLevel((short)2);	
			}
		}
		
		
		int result = zoneService.addZone(zone);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("添加地区成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("添加地区失败");
			resultBean.setSuccess(false);
		} else if(result == 2) {
			resultBean.setCode(2);
			resultBean.setMessage("该地区已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 删除地区
	 * @author gkx
	 * @create 2014-8-7 下午20:04:37
	 * @since 
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/delZone")
	public void delZone(HttpServletRequest request, HttpServletResponse response, Model model, String ids) throws ControllerException {
		int result = zoneService.delZone(ids);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("删除地区成功");
			resultBean.setSuccess(true);
		} else if (result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("删除地区失败");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	/**
	 * 获得详情
	 * @author gkx
	 * @create 2014-8-7 下午20:05:16
	 * @since 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @throws ControllerException
	 * @throws IOException 
	 */
	@RequestMapping(value = "/zoneInfo")
	public String getZoneInfo(HttpServletRequest request, HttpServletResponse response, Model model, String zoneId) throws ControllerException, IOException {
		Zone zone = zoneService.getZone(Integer.parseInt(zoneId));//当前市或区或商圈
		request.setAttribute("zone", zone);
		
		Zone fatherZone = zoneService.getZoneByZoneNo(zone.getFatherNo());  //获取当前父级地区
		List<Zone> provinceList =  zoneService.findNextZone("1","1");  //省集合
		List<Zone> cityList = null;  //市集合
		List<Zone>  areaList = null; //区集合
		List<Zone> circleList = null;//商圈集合
		Zone curProvence = null;
		Zone curCity = null;
		Zone curArea = null;
		if(zone.getLevel()==2){
			cityList =  zoneService.findNextZone("2", fatherZone.getZoneNo());
			areaList = zoneService.findNextZone("3",zone.getZoneNo());
		}
		if(zone.getLevel()==3){
			curProvence = zoneService.getZoneByZoneNo(fatherZone.getFatherNo()); //所属的省
			cityList =  zoneService.findNextZone("2",curProvence.getZoneNo());
			areaList = zoneService.findNextZone("3",fatherZone.getZoneNo());
			request.setAttribute("curProvence", curProvence);
		}
		if(zone.getLevel()==4){
			curCity = zoneService.getZoneByZoneNo(fatherZone.getFatherNo());//所属的市
			curProvence = zoneService.getZoneByZoneNo(curCity.getFatherNo()); // 所属的省
			curArea = zoneService.getZoneByZoneNo(zone.getFatherNo());
			cityList =  zoneService.findNextZone("2",curProvence.getZoneNo());  //省下的所有市
			areaList = zoneService.findNextZone("3",curCity.getZoneNo()); //市下的所有区
			circleList = zoneService.findNextZone("4",fatherZone.getZoneNo());//区下的所有街
			request.setAttribute("curProvence", curProvence);
			request.setAttribute("circleList", circleList);
			request.setAttribute("curCity", curCity);
			request.setAttribute("curArea", curArea);
		}
		request.setAttribute("provinceList", provinceList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("areaList", areaList);
		request.setAttribute("zone", zone);
		request.setAttribute("fatherZone", fatherZone);
		
		return "/jsp/zone/editZone";
	}
	
	
	/**
	 * 修改地区
	 * @author gkx
	 * @create 2014-8-7 下午20:09:58
	 * @since 
	 * @param request
	 * @param response
	 * @param model
	 * @param zone
	 * @throws ControllerException
	 */
	@RequestMapping(value = "/modifyZone")
	public void modifyZone(HttpServletRequest request, HttpServletResponse response, Model model,Zone zone) throws ControllerException {
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String oldName = request.getParameter("oldName");
		int level = zone.getLevel();
		if(level==1){
			zone.setFatherNo("000000000");
		}else if(level==2){
			zone.setFatherNo(province);
		}else if(level==3){
			zone.setFatherNo(city);
		}else if(level==4){
			zone.setFatherNo(area);
		}
		
		int result = zoneService.modifyZone(zone,oldName);
		ResultBean resultBean = new ResultBean();
		if (result == 0) {
			resultBean.setCode(0);
			resultBean.setMessage("修改地区成功");
			resultBean.setSuccess(true);
		} else if(result == 1) {
			resultBean.setCode(1);
			resultBean.setMessage("修改地区失败");
			resultBean.setSuccess(false);
		} else if(result == 2){
			resultBean.setCode(2);
			resultBean.setMessage("该地区已经存在");
			resultBean.setSuccess(false);
		}
		String resultJson = GsonUtils.objectToJsonDateSerializer(resultBean, Constant.DETAIL_DATE_FORMAT);
		addResponseData(response, resultJson);
	}
	
	
	/**
	 * 获得下一级别地区列表
	 * @author gkx
	 * @throws IOException 
	 * @create 2014-8-8 上午11:53:51
	 */
	@RequestMapping(value = "/findProvinceList")
	public void findProvinceList(HttpServletRequest request, HttpServletResponse response) throws ControllerException, IOException {
		String level = request.getParameter("level");
		String zoneNo = request.getParameter("zoneNo");
		List<Zone> provinceList = zoneService.findNextZone(level,zoneNo);
		StringBuffer str = new StringBuffer();
    	str.append("<option value='"+level+"'>--请选择--</option>");
    	for(int i = 0 ; i < provinceList.size();i++){
	    	str.append("<option value='"+provinceList.get(i).getZoneNo()+"' title='"+provinceList.get(i).getZoneId()+"'>"+provinceList.get(i).getZoneName()+"</option>");
    	}
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter writer = response.getWriter();
		writer.write(str.toString());
		writer.flush();
	}
}

