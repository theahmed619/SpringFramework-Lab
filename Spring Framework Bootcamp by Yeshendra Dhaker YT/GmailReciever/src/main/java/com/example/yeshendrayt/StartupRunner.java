package com.example.yeshendrayt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.yeshendrayt.service.GmailReciverService;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    GmailReciverService gmailReciverService;

    @Override
    public void run(String... args) throws Exception {
        // Automatically fetch emails on application startup
        gmailReciverService.fetchAllMails();
    }
}
