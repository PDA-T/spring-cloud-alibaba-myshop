package com.pda.myshop.service.email.service;

import com.pda.myshop.commons.domain.TbUser;
import com.pda.myshop.commons.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @Date 2022/11/26 20:31
 * @Description 邮件Service
 * @since version-1.0
 */
@Service
public class EmailService {

	// 动态加载配置
	@Autowired
	private ConfigurableApplicationContext applicationContext;

	// 发送邮件
	@Autowired
	private JavaMailSender javaMailSender;

	// html邮件模板
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * @Date 2022/11/26 20:34
	 * @Description 发送邮件
	 * @Param [json]
	 * @return void
	 * @since version-1.0
	 */
	@StreamListener("input")// 监听input
	public void receive(String json){
		try {
			// 发送普通邮件
			TbUser tbUser = MapperUtils.json2pojo(json, TbUser.class);
			sendEmail("欢迎注册", "欢迎 " + tbUser.getUsername() + " 加入", tbUser.getEmail());

			// 发送 HTML 模板邮件
			Context context = new Context();
			context.setVariable("username", tbUser.getUsername());
			String emailTemplate = templateEngine.process("reg", context);
			sendTemplateEmail("欢迎注册", emailTemplate, tbUser.getEmail());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Date 2022/11/26 20:36
	 * @Description 发送普通邮件
	 * @Param [subject, body, to]
	 * @return void
	 * @since version-1.0
	 */
	@Async
	public void sendEmail(String subject, String body, String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

	/**
	 * @Date 2022/11/26 20:36
	 * @Description 发送 HTML 模板邮件
	 * @Param [subject, body, to]
	 * @return void
	 * @since version-1.0
	 */
	@Async
	public void sendTemplateEmail(String subject, String body, String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			javaMailSender.send(message);
		} catch (Exception e) {
		}
	}
}
