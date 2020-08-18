package com.pentas.sellerweb.common.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class DevException extends BaseException{

	private static final long serialVersionUID = -8237588506701654161L;

	/**
	 * default constructor
	 */
	public DevException()
    {
        super();
    }

    /**
	 * constructor
	 * @param 	defaultMessage 기본 예외처리 메시지
	 */
    public DevException(String defaultMessage)
    {
        this(defaultMessage, ((Object []) (null)), null);
    }

    /**
	 * constructor
	 * @param 	defaultMessage 기본 예외처리 메시지
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(String defaultMessage, Exception wrappedException)
    {
    	this(defaultMessage, ((Object []) (null)), wrappedException);
    }

    /**
	 * constructor
	 * @param 	defaultMessage 기본 예외처리 메시지
	 * @param 	messageParameters 예외처리 메시지에 적용될 parameter 배열
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(String defaultMessage, Object messageParameters[], Exception wrappedException)
    {
    	super(defaultMessage, messageParameters, wrappedException);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 */
    public DevException(MessageSource messageSource, String messageKey)
    {
        super(messageSource, messageKey, null, null, Locale.getDefault(), null);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(MessageSource messageSource, String messageKey, Exception wrappedException)
    {
        super(messageSource, messageKey, null, null, Locale.getDefault(), wrappedException);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 * @param 	locale 메시지 적용 언어
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(MessageSource messageSource, String messageKey, Locale locale, Exception wrappedException)
    {
    	super(messageSource, messageKey, null, null, locale, wrappedException);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 * @param 	messageParameters 예외처리 메시지에 적용될 parameter 배열
	 * @param 	locale 메시지 적용 언어
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(MessageSource messageSource, String messageKey, Object messageParameters[], Locale locale, Exception wrappedException)
    {
    	super(messageSource, messageKey, messageParameters, null, locale, wrappedException);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 * @param 	messageParameters 예외처리 메시지에 적용될 parameter 배열
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(MessageSource messageSource, String messageKey, Object messageParameters[], Exception wrappedException)
    {
    	super(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 * @param 	messageParameters 예외처리 메시지에 적용될 parameter 배열
	 * @param 	defaultMessage 기본메시지
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(MessageSource messageSource, String messageKey, Object messageParameters[], String defaultMessage, Exception wrappedException)
    {
    	super(messageSource, messageKey, messageParameters, defaultMessage, Locale.getDefault(), wrappedException);
    }

    /**
	 * constructor
	 * @param 	messageSource MessageSource
	 * @param 	messageKey message-*.properties의 key
	 * @param 	messageParameters 예외처리 메시지에 적용될 parameter 배열
	 * @param 	defaultMessage 기본메시지
	 * @param 	locale 메시지 적용 언어
	 * @param 	wrappedException throw 된 Exception Object
	 */
    public DevException(MessageSource messageSource, String messageKey, Object messageParameters[], String defaultMessage, Locale locale, Exception wrappedException)
    {
    	super(messageSource, messageKey, messageParameters, defaultMessage, locale, wrappedException);
    }
}
