package com.zl.service.Impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zl.mapper.BusinessMapper;
import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.service.IBusinessService;
import com.zl.util.AjaxJson;
import com.zl.util.UserContext;

@Service
public class BusinessServiceImpl implements IBusinessService {

	@Autowired
	private BusinessMapper businessMapper;

	@Override
	public AjaxJson registerBusiness(Business bs, BusinessInfo bsi) {
		AjaxJson aj = new AjaxJson();
		try {
			businessMapper.insertSimpleBusinssInfo(bs);
			// 查询新插入数据的序列
			Business business = businessMapper.querySimpleBusinessInfoByEmail(bs);
			System.err.println(business);
			// 完善信息表所需要的序列信息
			bsi.setBusinessId(business.getBusinessId());
			// 详细注册
			businessMapper.insertBusinssInfo(bsi);
			// 设置返回结果
			aj.setMsg("申请提交成功");
			aj.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			aj.setMsg("申请提交失败");
			aj.setSuccess(false);
			return aj;
		}
		// 简单注册
		return aj;

	}

	@Override
	public AjaxJson loginBusiness(Business bs) {
		AjaxJson aj = new AjaxJson();
		try {
			Business business = businessMapper.querySimpleBusinessInfoByEmail(bs);
			System.err.println(business.toString());
			System.err.println(businessMapper.queryBusinessInfoById(business).toString());
			if (business != null) {
				if (business.getIsDel() == 1) {
					aj.setMsg("该账号已删除");
					aj.setSuccess(false);
				}else {
					if (businessMapper.queryBusinessInfoById(business).getCheckFlag() == 1) {
						aj.setMsg("该账户已提交注册申请，正在审核中，请等待相关人员主动联系");
						aj.setSuccess(false);
					} else if (businessMapper.queryBusinessInfoById(business).getCheckFlag() == 2) {
						aj.setMsg("该账户未通过资料审核，详情请联系人工客服");
						aj.setSuccess(false);
					}else if(businessMapper.queryBusinessInfoById(business).getCheckFlag() == 4) {
						aj.setMsg("该账户已被冻结，详情请联系人工客服");
						aj.setSuccess(false);
					}else if(businessMapper.queryBusinessInfoById(business).getCheckFlag() == 5) {
						aj.setMsg("该账户已被注销，详情请联系人工客服");
						aj.setSuccess(false);
					}else if(businessMapper.queryBusinessInfoById(business).getCheckFlag() == 6) {
						aj.setMsg("该账户已被删除，详情请联系人工客服");
						aj.setSuccess(false);
					}else if(businessMapper.queryBusinessInfoById(business).getCheckFlag() == 3) {
						aj.setMsg("登录成功");
						System.out.println(business.toString());
						UserContext.setLoginBusiness(business);
						aj.setSuccess(true);
					}else {
						aj.setMsg("该账户状态异常，详情请联系人工客服");
						aj.setSuccess(false);
					}
				}
			} else {
				aj.setMsg("账户名密码不匹配");
				aj.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			aj.setMsg("登录失败，请稍后再试");
			aj.setSuccess(false);
		} finally {
			return aj;
		}
	}

}
