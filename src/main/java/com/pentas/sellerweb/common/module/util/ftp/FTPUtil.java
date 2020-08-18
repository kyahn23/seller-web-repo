package com.pentas.sellerweb.common.module.util.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.util.FileCopyUtils;

import com.pentas.sellerweb.common.module.MimeTypeConstants;
import com.pentas.sellerweb.common.module.util.CmmnUtil;
import com.pentas.sellerweb.common.module.util.DevMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FTPUtil {

	public void fileDownload(HttpServletRequest request, HttpServletResponse response, DevMap param) throws IOException {
		
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			
			String id = param.getString("id");
			String pw = param.getString("pw");
			String addr = param.getString("addr");
			String path = param.getString("path");
			String fileNm = param.getString("fileNm");
			String port = param.getString("port");
			if(port == null || port.trim().equals("")) {
				port = "21";
			}
			log.debug(">>>> FTP info <<<<");
			log.debug("=> id :" + id);
			log.debug("=> pw :" + pw);
			log.debug("=> addr :" + addr);
			log.debug("=> path :" + path);
			log.debug("=> fileNm :" + fileNm);
			log.debug("=> port :" + port);
			
			ftpClient.connect(addr, Integer.parseInt(port));
			showServerReply(ftpClient);
			
			int replyCode = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(replyCode)) {
				log.error("FTP Connect failed");
			}
			
			boolean success = ftpClient.login(id, pw);
			showServerReply(ftpClient);
			
			if(!success) {
				log.error("Count not login to the FTP server");
			}
			
			success = ftpClient.changeWorkingDirectory(path);
			showServerReply(ftpClient);
			
			if(success) {
				log.debug("Successfully changed working directory!");
			} else {
				log.error("Failed to change working directory. See FTP server's reply.");
			}
			
			try(InputStream is = ftpClient.retrieveFileStream(fileNm);
					BufferedInputStream in = new BufferedInputStream(is);
					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())){
//				String mimetype = "application/x-msdownload";
				String mimetype = MimeTypeConstants.getMimeType(fileNm);
				if(mimetype == null) mimetype = "application/x-msdownload";
				response.setContentType(mimetype);
				CmmnUtil.setContectDispositionResponse(fileNm, request, response);
				FileCopyUtils.copy(in, out);
				out.flush();
			}
		} catch(IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} finally {
			if(ftpClient != null) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		}
	}
	
	private void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if(replies != null && replies.length > 0) {
			for(String aReply : replies) {
				log.debug("FTP SERVER : " + aReply);
			}
		}
	}
}
