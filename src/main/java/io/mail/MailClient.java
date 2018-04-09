package io.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailClient {

	protected Session session;
	protected Store store;
	private String sendHost = "";
	private String receiveHost = "imap.exmail.qq.com";
	private int receivePort= 143;
	private String sendProtocol = "smtp";
	private String receiveProtocol = "imap";
	private String username = "cy@taodangpu.com";
	private String password = "Taodangpucy123456";
	private String fromAddr = "cy@taodangpu.com";
	private String toAddr = "cy@taodangpu.com";

	public void init() throws Exception {
		Properties props = new Properties();
		props.put("mail.transport.protocol", sendProtocol);
		props.put("mail.store.protocol", receiveProtocol);
		props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransprot");
		props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
		props.put("mail.smtp.host", sendHost);
	
	    session = Session.getDefaultInstance(props);
	    session.setDebug(false);
	    
	    store = session.getStore(receiveProtocol);
	    store.connect(receiveHost, receivePort, username, password);
	    
	    
	    
	}
	
	public void close() throws MessagingException {
		store.close();
	}
	public void sendMessage(String fromAddr,String toAddr) throws Exception{
		Message msg = createSimpleMessage(fromAddr, toAddr);
		Transport.send(msg);
	}
	public Message createSimpleMessage(String fromAddr,String toAddr) throws Exception{
		
		Message msg = new MimeMessage(session);
		InternetAddress[] internetAddresses = InternetAddress.parse(toAddr,false);
		msg.setRecipients(Message.RecipientType.TO, internetAddresses);
		msg.setSentDate(new Date());
		msg.setSubject("hello");
		msg.setFrom(new InternetAddress(fromAddr));
		msg.setText("How are you");
		return msg;
		
	}
	
	public void receiveMessage() throws Exception {
		browseMessagesFromFolder("inbox");
	}

	private void browseMessagesFromFolder(String string) throws 
	
		Exception {
		Folder folder = store.getFolder(string);
	
	
		if(folder == null) {
			throw new Exception("文件夹不存在");
		}
		
		browseMessagesFromFolder(folder);
	
	
	}

	private void browseMessagesFromFolder(Folder folder) throws MessagingException, IOException {
		folder.open(Folder.READ_ONLY);
		System.out.println(folder.getMessageCount());
		System.out.println(folder.getUnreadMessageCount());
	
		Message message = folder.getMessage(1);
		processMessage(message);
	}
	
	
	private void processMessage(Message msg) throws IOException, MessagingException {
		processMessageHeader(msg);
		 Object content = msg.getContent();
		if(content instanceof Multipart){
			processMultipart((Multipart)content);
		}else {
			processPart(msg);
		}
	}


	private void processMultipart(Multipart content) throws MessagingException, IOException {
		System.out.println("多部件");
		for(int i=0;i<content.getCount();i++){
			processPart(content.getBodyPart(i));
		}
	}

	private void processPart(Part bodyPart) throws MessagingException, IOException {
		System.out.println("消息");
		bodyPart.writeTo(System.out);
	}

	private void processMessageHeader(Message msg) throws MessagingException {
		System.out.println("头部");
		for(Enumeration<Header> allHeaders = msg.getAllHeaders();allHeaders.hasMoreElements();){
			Header header = allHeaders.nextElement();
			System.out.println(header.getName());
			
		}
	}

	public static void main(String[] args) throws Exception {
		MailClient mailClient = new MailClient()
				;
		mailClient.init();
	/*	mailClient.sendMessage(mailClient.fromAddr, mailClient.toAddr);*/
		mailClient.receiveMessage();
		mailClient.close();
	}
	
	
	
	
}
