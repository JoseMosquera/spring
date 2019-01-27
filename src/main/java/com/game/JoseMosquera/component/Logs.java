package com.game.JoseMosquera.component;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("log")
public class Logs {

	public static final Log LOG = LogFactory.getLog(Logs.class);
}
