package me.radicalmeme420.discordbot.memebot.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class MemebotIO {

	private static final String DIR = "dat/";
	private static final String DEFAULT_DAT = "dat/default.dat";
	
	public static String[] getPlayer(String id) {
		
		File f = new File(DIR + id + ".dat");
				
		if(!f.exists()) {
			createPlayer(f);
		}
			
		String[] ret = new String[3];
		Scanner fileScan = null;
		try {
			fileScan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int lineNum = 0;
		while(fileScan.hasNextLine()) {
			
			String line = fileScan.nextLine();
			if(line.charAt(0) == '#') {
				continue;
			}
			
			ret[lineNum] = line.split(":")[1];
			lineNum++;
			
		}
		
		return ret;
	}
	
	private static void createPlayer(File f) {
		try {
			Files.copy(new File(DEFAULT_DAT).toPath(),
			        f.toPath(),
			        StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static Inventory getInventory() {
//		
//		
//	}
}
