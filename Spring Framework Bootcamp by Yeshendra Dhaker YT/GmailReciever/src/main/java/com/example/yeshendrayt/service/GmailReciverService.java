package com.example.yeshendrayt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Email;

import jakarta.annotation.PostConstruct;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMultipart;

@Service
public class GmailReciverService {
	

	private List<Email> allEmailsList = new ArrayList<>();
	private int lastMessageCount = -1;
	private boolean initialFetch = false;

	// @PostConstruct
	public void initialMails() {
		try {
			fetchAllMails();
			initialFetch = true;
		} catch (Exception e) {
			System.out.println("Error fetching mails on startup:");
			e.printStackTrace();
		}
	}

	@Scheduled(fixedRate = 10000) //10 second
	public void newlyRecievedMails() {

		if (!initialFetch) {
			initialMails();
			return;
		}
		try {
			Properties props = new Properties();
			props.put("mail.store.protocol", "imap");
			props.put("mail.imap.host", "imap.gmail.com");
			props.put("mail.imap.port", "993");
			props.put("mail.imap.ssl.enable", "true");

			Session session = Session.getInstance(props);
			Store store = session.getStore("imap");

			System.out.println("Connecting to Gmail IMAP...");
			store.connect("imap.gmail.com", "theahmed298@gmail.com", "hwkg laag gzra zbsa");
			System.out.println("Connected to Gmail IMAP...");

			Folder inboxFolder = store.getFolder("INBOX");
			inboxFolder.open(Folder.READ_ONLY);
			
			Folder[] folders=store.getDefaultFolder().list("*");
			for(Folder folder:folders){
				System.out.println(folder.getFullName());
			}

			int currentMailsCount = inboxFolder.getMessageCount();
			if (currentMailsCount > lastMessageCount) {
				Message[] newMessages = inboxFolder.getMessages(lastMessageCount + 1, currentMailsCount);
				System.out.println("new mails counts" + newMessages.length);
				for (int i = 0; i < newMessages.length; i++) {
					//System.out.println("Reading message " + (i + 1));
					try {
						Message message = newMessages[i];
						Email email = new Email();

						email.setFrom(
								message.getFrom() != null ? InternetAddress.toString(message.getFrom()) : "Unknown");
						email.setSubject(message.getSubject() != null ? message.getSubject() : "No Subject");
						email.setRecivedDate(message.getReceivedDate());

						try {
							email.setContent(getTextFromMessage(message));
						} catch (Exception e) {
							System.out.println("Error parsing message content.");
							e.printStackTrace();
							email.setContent("Could not read content.");
						}

						allEmailsList.add(0,email);
						//System.out.println("Message added.");
					} catch (Exception e) {
						System.out.println("Error reading message " + (i + 1));
						e.printStackTrace();
					}
					lastMessageCount=currentMailsCount;
				}

			}else {
				System.out.println("no new msg");
			}
			inboxFolder.close(false);
			store.close();
		} catch (Exception e) {
			System.out.println("Error in fetching newly recieved mails"+e.getMessage());
			e.printStackTrace();
		}
	}

	public void fetchAllMails() {
		try {
			System.out.println("Starting to fetch emails...");

			Properties props = new Properties();
			props.put("mail.store.protocol", "imap");
			props.put("mail.imap.host", "imap.gmail.com");
			props.put("mail.imap.port", "993");
			props.put("mail.imap.ssl.enable", "true");

			Session session = Session.getInstance(props);
			Store store = session.getStore("imap");

			System.out.println("Connecting to Gmail IMAP...");
			store.connect("imap.gmail.com", "theahmed298@gmail.com", "hwkg laag gzra zbsa");
			System.out.println("Connected to Gmail IMAP...");

			Folder inboxFolder = store.getFolder("INBOX");
			inboxFolder.open(Folder.READ_ONLY);

			Message[] allMails = inboxFolder.getMessages();
			System.out.println("Initial Fetch total mails count: " + allMails.length);

			Folder[] folders=store.getDefaultFolder().list("*");
			for(Folder folder:folders){
				System.out.println(folder.getFullName());
			}

			for (int i = 0; i < allMails.length; i++) {
				//System.out.println("Reading message " + (i + 1));
				try {
					Message message = allMails[i];
					Email email = new Email();

					email.setFrom(message.getFrom() != null ? InternetAddress.toString(message.getFrom()) : "Unknown");
					email.setSubject(message.getSubject() != null ? message.getSubject() : "No Subject");
					email.setRecivedDate(message.getReceivedDate());

					try {
						email.setContent(getTextFromMessage(message));
					} catch (Exception e) {
						System.out.println("Error parsing message content.");
						e.printStackTrace();
						email.setContent("Could not read content.");
					}

					allEmailsList.add(email);
					//System.out.println("Message added.");
				} catch (Exception e) {
					System.out.println("Error reading message " + (i + 1));
					e.printStackTrace();
				}
			}

			lastMessageCount = inboxFolder.getMessageCount();
			System.out.println("Last Message Count: " + lastMessageCount);

			inboxFolder.close(false);
			store.close();
		} catch (Exception e) {
			System.out.println("Error in fetchAllMails:");
			e.printStackTrace();
		}
	}

	public String getTextFromMessage(Message message) throws Exception {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			return getTextFromMimeMultipart((MimeMultipart) message.getContent());
		}
		return "";
	}

	public String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		StringBuilder result = new StringBuilder();
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result.append(bodyPart.getContent());
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
			}
		}
		return result.toString();
	}

	public List<Email> getEmails() {
		return allEmailsList;
	}
}
