package com.liftdevelops.homeitems.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EmailMaskingSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String email, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (email != null && email.contains("@")) {
            // Regex to mask characters between first and '@' character
            String maskedEmail = email.replaceAll("(?<=\\b\\w)\\w(?=[^@]*?@)", "x");
            jsonGenerator.writeString(maskedEmail);
        } else {
            jsonGenerator.writeString(email);
        }
    }
}
