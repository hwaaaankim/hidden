package com.dev.HiddenBATH.config;

public interface EmailSendable {
	void send(String[] to, String subject, String message) throws InterruptedException;
}