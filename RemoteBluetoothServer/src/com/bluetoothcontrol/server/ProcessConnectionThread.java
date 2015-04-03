package com.bluetoothcontrol.server;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.OutputStream;

import javax.jws.Oneway;
import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable{

	private StreamConnection mConnection;
	
	// Constant that indicate command from devices
	private static final int EXIT_CMD = -1;
	private static final int KEY_RIGHT = 1;
	private static final int KEY_LEFT = 2;
	private static final int UP = 4;
	private static final int DOWN = 5;
	private static final int PPRESS_UP = 6;
	private static final int RELSEASE_UP = 7;
	private static final int PPRESS_DOWN = 8;
	private static final int RELSEASE_DOWN =9;
	private static final int PPRESS_RIGHT = 10;
	private static final int RELSEASE_RIGHT = 11;
	private static final int PPRESS_LEFT = 12;
	private static final int RELSEASE_LEFT = 13;
	private static final int SPACE = 14;
	private static final int ONE = 15;
	private static final int TWO = 16;
	
	public ProcessConnectionThread(StreamConnection connection)
	{
		mConnection = connection;
	}
	
	@Override
	public void run() {
		try {
			
			// prepare to receive data
			InputStream inputStream = mConnection.openInputStream();
			OutputStream outStream = mConnection.openOutputStream();
	        
			System.out.println("waiting for input");
	        
	        while (true) {
	        	int command = inputStream.read();
	        	
	        	if (command == EXIT_CMD)
	        	{	
	        		System.out.println("finish process");
	        		break;
	        	}
	        	
	        	processCommand(command);
	        	outStream.write("commandProcessed".getBytes());
        	}
        } catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	/**
	 * Process the command from client
	 * @param command the command code
	 */
	private void processCommand(int command) {
		try {
			Robot robot = new Robot();
			switch (command) {
	    	case KEY_RIGHT:
	    		robot.keyPress(KeyEvent.VK_RIGHT);
	    		System.out.println("Right");
	    		// release the key after it is pressed. Otherwise the event just keeps getting trigged	    		
	    		robot.keyRelease(KeyEvent.VK_RIGHT);
	    		break;
	    	case KEY_LEFT:
	    		robot.keyPress(KeyEvent.VK_LEFT);
	    		System.out.println("Left");
	    		// release the key after it is pressed. Otherwise the event just keeps getting trigged	    		
	    		robot.keyRelease(KeyEvent.VK_LEFT);
	    		break;
	    	case UP:
	    		robot.keyPress(KeyEvent.VK_UP);
	    		System.out.println("up");
	    		// release the key after it is pressed. Otherwise the event just keeps getting trigged	    		
	    		robot.keyRelease(KeyEvent.VK_UP);
	    		break;
	    	case DOWN:
	    		robot.keyPress(KeyEvent.VK_DOWN);
	    		System.out.println("up");
	    		// release the key after it is pressed. Otherwise the event just keeps getting trigged	    		
	    		robot.keyRelease(KeyEvent.VK_DOWN);
	    		break;
	    	case PPRESS_UP:
	    		robot.keyPress(KeyEvent.VK_UP);
	    		System.out.println("PRESS up");
	    		break;
	      	case RELSEASE_UP:
	    		robot.keyRelease(KeyEvent.VK_UP);
	    		System.out.println("RELEASE up");
	    		break;
	      	case PPRESS_DOWN:
	    		robot.keyPress(KeyEvent.VK_DOWN);
	    		System.out.println("PRESS DOWN");
	    		break;
	      	case RELSEASE_DOWN:
	    		robot.keyRelease(KeyEvent.VK_DOWN);
	    		System.out.println("RELEASE DOWN");
	    		break;
	      	case PPRESS_LEFT:
	    		robot.keyPress(KeyEvent.VK_LEFT);
	    		//System.out.println("PRESS LEFT");
	    		break;
	      	case RELSEASE_LEFT:
	    		robot.keyRelease(KeyEvent.VK_LEFT);
	    		//System.out.println("RELEASE LEFT");
	    		break;
	      	case PPRESS_RIGHT:
	    		robot.keyPress(KeyEvent.VK_RIGHT);
	    		//System.out.println("PRESS RIGHT");
	    		break;
	      	case RELSEASE_RIGHT:
	    		robot.keyRelease(KeyEvent.VK_RIGHT);
	    		//System.out.println("RELEASE RIGHT");
	    		break;
	      	case SPACE:
	    		robot.keyPress(KeyEvent.VK_SPACE);
	    		robot.keyRelease(KeyEvent.VK_SPACE);
	    		System.out.println("SPACE");
	    		break;
	    	case ONE:
	    		robot.keyPress(KeyEvent.VK_1);
	    		robot.keyRelease(KeyEvent.VK_1);
	    		System.out.println("ONE");
	    	case TWO:
	    		robot.keyPress(KeyEvent.VK_2);
	    		robot.keyRelease(KeyEvent.VK_2);
	    		System.out.println("TWO");
	    		break;
	    		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
