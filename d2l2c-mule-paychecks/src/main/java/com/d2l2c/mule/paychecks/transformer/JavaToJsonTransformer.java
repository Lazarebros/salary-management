/**
 * 
 */
package com.d2l2c.mule.paychecks.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.d2l2c.mule.paychecks.bean.PaycheckDB;
import com.d2l2c.mule.paychecks.bean.PaycheckSummary;

/**
 * @author dayanlazare
 *
 */
public class JavaToJsonTransformer extends AbstractMessageTransformer {
	
	private final Mapper mapper = new DozerBeanMapper();

	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage muleMessage, String outputEncoding) throws TransformerException {
		List<PaycheckDB> paycheckDBList = new ArrayList<PaycheckDB>();
		try {
			List<Map<String, Object>> payload = (List<Map<String, Object>>) muleMessage.getPayload();
			
			for(Map<String, Object> map: payload) {
				PaycheckDB paycheckDB = mapper.map(map, PaycheckDB.class);
				paycheckDBList.add(paycheckDB);
			}
			
			muleMessage.setPayload(paycheckDBList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return muleMessage;
	}
	
	private List<PaycheckSummary> computeValues(List<PaycheckDB> paycheckDBList) {
		
		
		
		
		
		return null;
	}

}
