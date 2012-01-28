package fi.ringofsnake.ui.menu;

import java.io.File;

/**
 *	FileMenuItem is a menu item that contains reference to java.io.File object. 
 */
public class FileMenuItem extends BasicMenuItem {

	/**
	 * File that is associated with a menu item
	 */
	private File file;
	
	public FileMenuItem(int posX, int posY, String text, File file ) {
		super(posX, posY, text);
		this.file = file;
		
	}

	/**
	 * @return associated file object
	 */
	public File getFile() {
		return file;
	}	
}

