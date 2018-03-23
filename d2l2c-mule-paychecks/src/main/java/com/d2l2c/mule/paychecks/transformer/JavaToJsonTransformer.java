/**
 * 
 */
package com.d2l2c.mule.paychecks.transformer;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

/**
 * @author dayanlazare
 *
 */
public class JavaToJsonTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage muleMessage, String outputEncoding) throws TransformerException {
		try {
			Object payload = muleMessage.getPayload();
			muleMessage.setPayload(payload);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return muleMessage;
	}

}
