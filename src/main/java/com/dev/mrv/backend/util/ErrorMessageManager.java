package com.dev.mrv.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageManager {

    private final MessageSource messageSource;

    @Autowired
    public ErrorMessageManager(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getErrorMessage(String key, Object... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }
}
