package br.com.mega.hack.security.exception;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

	private static final long serialVersionUID = 1L;

	protected CustomOauthExceptionSerializer() {
		super(CustomOauthException.class);
	}

	@Override
	public void serialize(CustomOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("error", value.getOAuth2ErrorCode());
		gen.writeStringField("message", messageConverter(value.getMessage()));
		gen.writeNumberField("timestamp", Integer.valueOf((int) System.currentTimeMillis()));
		gen.writeNumberField("status", 404);
		if (value.getAdditionalInformation() != null) {
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				gen.writeStringField(key, add);
			}
		}
		gen.writeEndObject();
	}

	private String messageConverter(String messagem) {
		if (messagem.equals("Bad credentials")) {
			messagem = "BAD_CREDENTIAL";
		} else if (messagem.equals("User is disabled")){
			messagem = "USER_DISABLED";
		}  
		return messagem;
	}

}