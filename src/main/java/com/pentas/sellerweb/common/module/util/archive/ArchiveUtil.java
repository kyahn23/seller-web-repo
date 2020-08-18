package com.pentas.sellerweb.common.module.util.archive;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

public class ArchiveUtil {
	
	/**
	 * src들을 dest로 compress하여 생성합니다.
	 * @param src
	 * @param dest
	 * @return
	 * @throws IOException
	 * @throws ArchiveException
	 */
	static public File makeZip(String[] src, String dest) throws IOException, ArchiveException{
		List<File> fileList = new ArrayList<File>();
		for(String srcPath : src) {
			File srcFile = new File(srcPath);
			if(!srcFile.exists()) {
				throw new IOException("경로가 존재하지 않습니다.");
			}
			fileList.add(srcFile);
		}
		
		File destination = new File(dest);
		
		if(destination.exists() && destination.isFile()) {
			throw new IOException("목적경로에 파일이 이미 존재합니다.");
		} else {
			String[] paths = dest.split(Pattern.quote(File.separator));
			int lastValidIndex = paths.length - 1;
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<lastValidIndex; i++) {
				sb.append(paths[i]);
				sb.append(File.separator);
			}
			File destDirectory = new File(sb.toString());
			if(!destDirectory.exists() || !destDirectory.isDirectory()) {
				destDirectory.mkdirs();
			}
		}
		
		try(OutputStream outputStream = new FileOutputStream(destination);
				ArchiveOutputStream archive = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, outputStream)){
			for(File file : fileList) {
				if(file.getAbsolutePath().equals(dest)) continue;
				
				ZipArchiveEntry entry = new ZipArchiveEntry(file.getName());
				archive.putArchiveEntry(entry);
				
				try(BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
					IOUtils.copy(input, archive);
					archive.closeArchiveEntry();
				}
			}
		}
		
		return destination;
	}

}
