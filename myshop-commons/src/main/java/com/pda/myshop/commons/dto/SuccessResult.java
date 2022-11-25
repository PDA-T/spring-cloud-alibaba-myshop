package com.pda.myshop.commons.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Date 2022/11/25 20:55
 * @Description 请求成功
 * @since version-1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessResult<T extends AbstractBaseDomain> extends AbstractBaseResult{

	private Links links;

	private List<DataBean> data;

	public SuccessResult(String self,T attributes){
		links = new Links();
		links.setSelf(self);

		createDataBean(null,attributes);
	}

	public SuccessResult(String self,Integer next,Integer last,List<T> attributes){
		links = new Links();
		links.setSelf(self);
		links.setNext(self + "?page=" + next);
		links.setLast(self + "?page=" + last);

		attributes.forEach(attribute -> {
			createDataBean(self,attribute);
		});
	}

	private void createDataBean(String self,T attributes){
		if (data == null){
			data = Lists.newArrayList();
		}
		DataBean dataBean = new DataBean();
		dataBean.setId(attributes.getId());
		dataBean.setType(attributes.getClass().getSimpleName());
		dataBean.setAttributes(attributes);

		if (StringUtils.isNotBlank(self)){
			Links links = new Links();
			links.setSelf(self + "/" + attributes.getId());
			dataBean.setLinks(links);
		}

		data.add(dataBean);
	}
}
