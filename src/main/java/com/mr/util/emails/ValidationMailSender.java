package com.mr.util.emails;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class ValidationMailSender {
	private JavaMailSender mailSender;
	private String from;
	private String to;
	private String subject;
	private String messageBody;
	
	public ValidationMailSender() {
	}
	
	public ValidationMailSender(JavaMailSender mailSender, String from,
			String to, String subject, String messageBody) {
		this.mailSender = mailSender;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.messageBody = messageBody;
	}
	
	public void sendConfirmationMail() {
		mailSender.send(new MimeMessagePreparator() {
			  public void prepare(MimeMessage mimeMessage) throws MessagingException {
				    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				    message.setFrom(from);
				    message.setTo(to);
				    message.setSubject(subject);
				    message.setText(messageBody, true);
				  }
		});
	}
	//messageBody
	//"<a href=\"http://localhost:8080/Dictionary/login?registered=true&uId="+id+"&p="+pass+"\">click here</a>"
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getSubject() {
		return subject;
	}
	public String getMessageBody() {
		return messageBody;
	}
}
