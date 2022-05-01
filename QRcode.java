import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.*;  

class QRcode 
{
    	public static boolean createCode(String car,String mobile,String num,String time,String date,String slots,String to)
	{
		String filePath = "";

        	try 
		{         
	    		filePath = "QRCodes\\"+num+".png";
            		String charset = "UTF-8"; // or "ISO-8859-1"
            		String qrCodeData="\nCar Type      :  "+car+"\nMobile No.  :  "+mobile+"\nVehicle No. :  "+num+"\nIn-Time        :  "+time+"\nIn-Date        :  "+date+"\nSlot No        :  "+slots;
            		//SendSMS.sendSMS(qrCodeData,mobile);
			Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset),charset), BarcodeFormat.QR_CODE, 200, 200, hintMap);
            		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
            		return true;
        	} 
		catch (Exception e) 
		{
            		System.err.println(e);
            		return false;
        	}
    	}
}